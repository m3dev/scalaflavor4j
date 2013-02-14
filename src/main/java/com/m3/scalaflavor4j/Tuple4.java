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
 * A tuple of 4 elements; the canonical representation of a Product4.
 * 
 * @see "http://www.scala-lang.org/api/2.9.1/index.html#scala.Tuple4"
 */
public class Tuple4<T1, T2, T3, T4> {

    private T1 _1;
    private T2 _2;
    private T3 _3;
    private T4 _4;

    public static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> apply(T1 _1, T2 _2, T3 _3, T4 _4) {
        return new Tuple4<T1, T2, T3, T4>(_1, _2, _3, _4);
    }

    public static <T1, T2, T3, T4> Option<Tuple4<T1, T2, T3, T4>> unapply(Tuple4<T1, T2, T3, T4> tuple) {
        return Option.apply(tuple);
    }

    protected Tuple4(T1 _1, T2 _2, T3 _3, T4 _4) {
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
        this._4 = _4;
    }

    /**
     * Element 1 of this Tuple4
     */
    public T1 _1() {
        return _1;
    }

    /**
     * Element 2 of this Tuple4
     */
    public T2 _2() {
        return _2;
    }

    /**
     * Element 3 of this Tuple4
     */
    public T3 _3() {
        return _3;
    }

    /**
     * Element 4 of this Tuple4
     */
    public T4 _4() {
        return _4;
    }

    /**
     * Element 1 of this Tuple4
     */
    public T1 getFirst() {
        return _1();
    }

    /**
     * Element 2 of this Tuple4
     */
    public T2 getSecond() {
        return _2();
    }

    /**
     * Element 3 of this Tuple4
     */
    public T3 getThird() {
        return _3();
    }

    /**
     * Element 4 of this Tuple4
     */
    public T4 getFourth() {
        return _4();
    }

    @Override
    public String toString() {
        return "(" + _1 + "," + _2 + "," + _3 + "," + _4 + ")";
    }

}
