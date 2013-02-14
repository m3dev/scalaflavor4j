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
 * {@link PartialFunction} alias
 */
public class PartialF<R> extends PartialFunction<R> {

    public static <R> PartialF<R> apply(CaseClause<?, R>... caseClauses) {
        return new PartialF<R>(caseClauses);
    }

    public static <R> PartialF<R> apply(Seq<CaseClause<?, R>> caseClauses) {
        return new PartialF<R>(caseClauses);
    }

    PartialF(CaseClause<?, R>... caseClauses) {
        super(caseClauses);
    }

    PartialF(Seq<CaseClause<?, R>> caseClauses) {
        super(caseClauses);
    }

}
