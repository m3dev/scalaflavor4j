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
 * A function of 2 parameters.
 *
 * @see "http://www.scala-lang.org/api/2.9.1/index.html#scala.Function2"
 */
public class RichFunction2<T1, T2, R> implements Function2<T1, T2, R> {

    private final Function2<T1, T2, R> underlying;

    public RichFunction2(Function2<T1, T2, R> f) {
        underlying = f;
    }

    /**
     * Apply the body of this function to the arguments.
     */
    public R apply(T1 v1, T2 v2) throws Exception {
        if (underlying == null) {
            throw new IllegalStateException("Function is empty.");
        }
        return underlying.apply(v1, v2);
    }

    /**
     * Creates a tupled version of this function: instead of 2 arguments, it
     * accepts a single Tuple2 argument.
     */
    public F1<Tuple2<T1, T2>, R> tupled() {
        final RichFunction2<T1, T2, R> _this = this;
        return new F1<Tuple2<T1, T2>, R>() {
            public R apply(Tuple2<T1, T2> tuple) throws Exception {
                return _this.apply(tuple._1(), tuple._2());
            }
        };
    }

    /**
     * Creates a curried version of this function.
     */
    public F1<T1, Function1<T2, R>> curried() {
        final RichFunction2<T1, T2, R> _this = this;
        return new F1<T1, Function1<T2, R>>() {
            public F1<T2, R> apply(final T1 v1) {
                return new F1<T2, R>() {
                    public R apply(T2 v2) throws Exception {
                        return _this.apply(v1, v2);
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
        return "<function2>";
    }

}
