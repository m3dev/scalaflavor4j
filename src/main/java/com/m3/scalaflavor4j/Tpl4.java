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
 * {@link Tuple4} alias
 */
public class Tpl4<T1, T2, T3, T4> extends Tuple4<T1, T2, T3, T4> {

    public static <T1, T2, T3, T4> Tpl4<T1, T2, T3, T4> apply(T1 _1, T2 _2, T3 _3, T4 _4) {
        return new Tpl4<T1, T2, T3, T4>(_1, _2, _3, _4);
    }

    public static <T1, T2, T3, T4> Option<Tpl4<T1, T2, T3, T4>> unapply(Tpl4<T1, T2, T3, T4> tuple) {
        return Option.apply(tuple);
    }

    protected Tpl4(T1 _1, T2 _2, T3 _3, T4 _4) {
        super(_1, _2, _3, _4);
    }

}
