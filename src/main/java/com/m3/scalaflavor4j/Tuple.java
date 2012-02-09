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
 * Factory for Tuple[n]
 */
public class Tuple {

    private Tuple() {
    }

    public static <T1, T2> Tuple2<T1, T2> apply(T1 _1, T2 _2) {
        return _(_1, _2);
    }

    public static <T1, T2> Tuple2<T1, T2> _(T1 _1, T2 _2) {
        return new Tuple2<T1, T2>(_1, _2);
    }

    public static <T1, T2, T3> Tuple3<T1, T2, T3> apply(T1 _1, T2 _2, T3 _3) {
        return _(_1, _2, _3);
    }

    public static <T1, T2, T3> Tuple3<T1, T2, T3> _(T1 _1, T2 _2, T3 _3) {
        return new Tuple3<T1, T2, T3>(_1, _2, _3);
    }

    public static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> apply(T1 _1, T2 _2, T3 _3, T4 _4) {
        return _(_1, _2, _3, _4);
    }

    public static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> _(T1 _1, T2 _2, T3 _3, T4 _4) {
        return new Tuple4<T1, T2, T3, T4>(_1, _2, _3, _4);
    }

    public static <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> apply(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5) {
        return _(_1, _2, _3, _4, _5);
    }

    public static <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> _(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5) {
        return new Tuple5<T1, T2, T3, T4, T5>(_1, _2, _3, _4, _5);
    }

}
