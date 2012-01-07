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
 * A function of 3 parameters.
 * 
 * @see "http://www.scala-lang.org/api/2.9.1/index.html#scala.Function3"
 */
public abstract class Function3<T1, T2, T3, R> {

    public R apply(T1 v1, T2 v2, T3 v3) throws Exception {
        return _(v1, v2, v3);
    }

    /**
     * Apply the body of this function to the arguments.
     */
    public abstract R _(T1 v1, T2 v2, T3 v3) throws Exception;

    /**
     * Creates a tupled version of this function: instead of 3 arguments, it
     * accepts a single Tuple3 argument.
     */
    public F1<Tuple3<T1, T2, T3>, R> tupled() {
        final Function3<T1, T2, T3, R> _this = this;
        return new F1<Tuple3<T1, T2, T3>, R>() {
            public R _(Tuple3<T1, T2, T3> tuple) throws Exception {
                return _this.apply(tuple._1(), tuple._2(), tuple._3());
            }
        };
    }

    /**
     * Creates a curried version of this function.
     */
    public F1<T1, Function1<T2, Function1<T3, R>>> curried() {
        final Function3<T1, T2, T3, R> _this = this;
        return new F1<T1, Function1<T2, Function1<T3, R>>>() {
            public Function1<T2, Function1<T3, R>> _(final T1 v1) {
                return new F1<T2, Function1<T3, R>>() {
                    public Function1<T3, R> _(final T2 v2) {
                        return new F1<T3, R>() {
                            public R _(T3 v3) throws Exception {
                                return _this.apply(v1, v2, v3);
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
        return "<function3>";
    }

}
