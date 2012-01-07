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
 * A partial function of type PartialFunction[A, B] is a unary function where
 * the domain does not necessarily include all values of type A. The function
 * isDefinedAt allows to test dynamically if a value is in the domain of the
 * function.
 */
public class PartialFunction<R> extends Function1<Object, R> {

    private final Seq<CaseClause<?, R>> caseClauses;

    public static <R> PartialF<R> _(CaseClause<?, R>... caseClauses) {
        return new PartialF<R>(caseClauses);
    }

    public static <R> PartialF<R> _(Seq<CaseClause<?, R>> caseClauses) {
        return new PartialF<R>(caseClauses);
    }

    PartialFunction(CaseClause<?, R>... caseClauses) {
        this.caseClauses = Seq._(caseClauses);
    }

    PartialFunction(Seq<CaseClause<?, R>> caseClauses) {
        this.caseClauses = caseClauses;
    }

    public Seq<CaseClause<?, R>> getCaseClauses() {
        return this.caseClauses;
    }

    @Override
    public R _(final Object v) throws Exception {
        Option<R> matched = getCaseClauses().foldLeft(Option.<R> none(),
                new F2<Option<R>, CaseClause<?, R>, Option<R>>() {
                    public Option<R> _(Option<R> alreadyMatched, CaseClause<?, R> caseClause) throws Exception {
                        if (alreadyMatched.isDefined()) {
                            return alreadyMatched;
                        } else {
                            return caseClause.apply(v);
                        }
                    }
                });
        if (matched.isDefined()) {
            return matched.getOrNull();
        } else {
            if (v == null) {
                throw new MatchError("null");
            } else {
                throw new MatchError(v.toString());
            }
        }
    }

    /**
     * Checks if a value is contained in the function's domain.
     */
    public boolean isDefinedAt(final Object v) {
        return getCaseClauses().foldLeft(false, new F2<Boolean, CaseClause<?, R>, Boolean>() {
            public Boolean _(Boolean isDefinedAt, CaseClause<?, R> caseClause) throws Exception {
                if (isDefinedAt) {
                    return true;
                }
                return caseClause.isDefinedAt(v);
            }
        });
    }

    /**
     * Composes this partial function with a fallback partial function which
     * gets applied where this partial function is not defined.
     */
    public PartialFunction<R> orElse(PartialFunction<R> that) {
        return new PartialFunction<R>(getCaseClauses().union(that.getCaseClauses()));
    }
}
