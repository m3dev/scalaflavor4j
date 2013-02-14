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
 * A function of 4 parameters (return type void).
 *
 * @see "http://www.scala-lang.org/api/2.9.1/index.html#scala.Function4"
 */
public class RichVoidFunction4<T1, T2, T3, T4> implements VoidFunction4<T1, T2, T3, T4> {

    private final VoidFunction4<T1, T2, T3, T4> underlying;

    public RichVoidFunction4(VoidFunction4<T1, T2, T3, T4> f) {
        this.underlying = f;
    }

    /**
     * Apply the body of this function to the arguments.
     */
    public void apply(T1 v1, T2 v2, T3 v3, T4 v4) throws Exception {
        underlying.apply(v1, v2, v3, v4);
    }

    /**
     * Creates a tupled version of this function: instead of 4 arguments, it
     * accepts a single Tuple4 argument.
     */
    public VoidFunction1<Tuple4<T1, T2, T3, T4>> tupled() {
        final RichVoidFunction4<T1, T2, T3, T4> _this = this;
        return new VoidF1<Tuple4<T1, T2, T3, T4>>() {
            public void apply(Tuple4<T1, T2, T3, T4> tuple) throws Exception {
                _this.apply(tuple._1(), tuple._2(), tuple._3(), tuple._4());
            }
        };
    }

    /**
     * Creates a String representation of this object.
     */
    @Override
    public String toString() {
        return "<void-function4>";
    }

}
