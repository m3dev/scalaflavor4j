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
 * A function of 5 parameters.
 *
 * @see "http://www.scala-lang.org/api/2.9.1/index.html#scala.Function5"
 */
public class RichFunction5<T1, T2, T3, T4, T5, R> implements Function5<T1, T2, T3, T4, T5, R> {

    private final Function5<T1, T2, T3, T4, T5, R> underlying;

    public RichFunction5(Function5<T1, T2, T3, T4, T5, R> f) {
        this.underlying = f;
    }

    /**
     * Apply the body of this function to the arguments.
     */
    public R apply(T1 v1, T2 v2, T3 v3, T4 v4, T5 v5) throws Exception {
        return underlying.apply(v1, v2, v3, v4, v5);
    }

    /**
     * Creates a tupled version of this function: instead of 5 arguments, it
     * accepts a single Tuple5 argument.
     */
    public F1<Tuple5<T1, T2, T3, T4, T5>, R> tupled() {
        final RichFunction5<T1, T2, T3, T4, T5, R> _this = this;
        return new F1<Tuple5<T1, T2, T3, T4, T5>, R>() {
            public R apply(Tuple5<T1, T2, T3, T4, T5> tuple) throws Exception {
                return _this.apply(tuple._1(), tuple._2(), tuple._3(), tuple._4(), tuple._5());
            }
        };
    }

    /**
     * Creates a curried version of this function.
     */
    public F1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, R>>>>> curried() {
        final RichFunction5<T1, T2, T3, T4, T5, R> _this = this;
        return new F1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, R>>>>>() {
            public Function1<T2, Function1<T3, Function1<T4, Function1<T5, R>>>> apply(final T1 v1) {
                return new F1<T2, Function1<T3, Function1<T4, Function1<T5, R>>>>() {
                    public Function1<T3, Function1<T4, Function1<T5, R>>> apply(final T2 v2) {
                        return new F1<T3, Function1<T4, Function1<T5, R>>>() {
                            public Function1<T4, Function1<T5, R>> apply(final T3 v3) {
                                return new F1<T4, Function1<T5, R>>() {
                                    public Function1<T5, R> apply(final T4 v4) {
                                        return new F1<T5, R>() {
                                            public R apply(T5 v5) throws Exception {
                                                return _this.apply(v1, v2, v3, v4, v5);
                                            }
                                        };
                                    }
                                };
                            }
                        };
                    }
                };
            }
        };
    }

    /**
     * Creates a String representation of this object.
     */
    @Override
    public String toString() {
        return "<function5>";
    }

}
