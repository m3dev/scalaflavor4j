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
 * {@link Tuple2} alias
 */
public class Tpl2<T1, T2> extends Tuple2<T1, T2> {

    public static <T1, T2> Tpl2<T1, T2> apply(T1 _1, T2 _2) {
        return new Tpl2<T1, T2>(_1, _2);
    }

    public static <T1, T2> Option<Tpl2<T1, T2>> unapply(Tpl2<T1, T2> tuple) {
        return Option.apply(tuple);
    }

    protected Tpl2(T1 _1, T2 _2) {
        super(_1, _2);
    }

}
