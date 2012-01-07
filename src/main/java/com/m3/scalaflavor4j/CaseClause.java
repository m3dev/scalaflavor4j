/*
 * Copyright 2012 M3, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.m3.scalaflavor4j;

/**
 * CaseClause
 * 
 * <pre>
 * Expr ::= PostfixExpr ‘match’ ‘{’ CaseClauses ‘}’
 * 
 * CaseClauses ::= CaseClause {CaseClause}
 * 
 * CaseClause ::= ‘case’ Pattern [Guard] ‘=>’ Block
 * </pre>
 */
public class CaseClause<M, R> extends Function1<Object, Option<R>> {

    private final Extractor<M> extractor;

    private final Guard<M> guard;

    private final Function1<M, R> block;

    public static <M> CaseClauseBuilder<M> _case(Class<M> clazz) {
        return new CaseClauseBuilder<M>(new ClassCastExtractor<M>(clazz));
    }

    public static <M> CaseClauseBuilder<M> _case(Extractor<M> extractor) {
        return new CaseClauseBuilder<M>(extractor);
    }

    /**
     * CaseClause builder
     */
    public static class CaseClauseBuilder<M> {

        private Extractor<M> extractor;

        private Guard<M> guard = new Guard<M>() {
            public Boolean _(M v) {
                return v != null;
            }
        };

        private CaseClauseBuilder(Extractor<M> extractor) {
            this.extractor = extractor;
        }

        public CaseClauseBuilder<M> _if(Guard<M> guard) {
            this.guard = guard;
            return this;
        }

        public <R> CaseClause<M, R> _arrow(Function1<M, R> block) {
            return CaseClause._(extractor, guard, block);
        }

    }

    private CaseClause(Extractor<M> extractor, Guard<M> guard, Function1<M, R> block) {
        this.extractor = extractor;
        this.guard = guard;
        this.block = block;
    }

    private static <M, R> CaseClause<M, R> _(Extractor<M> extractor, Guard<M> guard, Function1<M, R> block) {
        return new CaseClause<M, R>(extractor, guard, block);
    }

    @Override
    public Option<R> _(Object v) throws Exception {
        Option<M> extracted = extractor.unapply(v);
        if (extracted.isDefined()) {
            if (guard.apply(extracted.getOrNull())) {
                return Option._(block.apply(extracted.getOrNull()));
            }
        }
        return Option.none();
    }

    /**
     * Checks if a value is contained in the function's domain.
     */
    public <A> boolean isDefinedAt(A v) throws Exception {
        Option<M> extracted = extractor.unapply(v);
        if (extracted.isDefined()) {
            return guard.apply(extracted.getOrNull());
        }
        return false;
    }

}
