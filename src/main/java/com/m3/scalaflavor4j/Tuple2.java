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
 * A tuple of 2 elements; the canonical representation of a Product2.
 * 
 * @see "http://www.scala-lang.org/api/2.9.1/index.html#scala.Tuple2"
 */
public class Tuple2<T1, T2> {

    private T1 _1;
    private T2 _2;

    public static <T1, T2> Tuple2<T1, T2> apply(T1 _1, T2 _2) {
        return _(_1, _2);
    }

    public static <T1, T2> Tuple2<T1, T2> _(T1 _1, T2 _2) {
        return new Tuple2<T1, T2>(_1, _2);
    }

    public static <T1, T2> Option<Tuple2<T1, T2>> unapply(Tuple2<T1, T2> tuple) {
        return Option._(tuple);
    }

    protected Tuple2(T1 _1, T2 _2) {
        this._1 = _1;
        this._2 = _2;
    }

    /**
     * Element 1 of this Tuple2
     */
    public T1 _1() {
        return _1;
    }

    /**
     * Element 2 of this Tuple2
     */
    public T2 _2() {
        return _2;
    }

    /**
     * Swaps the elements of this Tuple.
     */
    public Tuple2<T2, T1> swap() {
        return Tuple2.apply(_2, _1);
    }

    @Override
    public String toString() {
        return "(" + _1 + "," + _2 + ")";
    }

}
