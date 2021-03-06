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
 * Extractor for Pattern
 * 
 * <pre>
 * Expr ::= PostfixExpr ‘match’ ‘{’ CaseClauses ‘}’
 * 
 * CaseClauses ::= CaseClause {CaseClause}
 * 
 * CaseClause ::= ‘case’ Pattern [Guard] ‘=>’ Block
 * </pre>
 */
public abstract class Extractor<M> {

    public abstract M extract(Object v) throws Exception;

    /**
     * Extract a value
     */
    public Option<M> unapply(Object v) throws Exception {
        return Option.apply(extract(v));
    }

}
