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
 * A function of 5 parameters (return type void).
 *
 * @see "http://www.scala-lang.org/api/2.9.1/index.html#scala.Function5"
 */
public class RichVoidFunction5<T1, T2, T3, T4, T5> implements VoidFunction5<T1, T2, T3, T4, T5> {

    private final VoidFunction5<T1, T2, T3, T4, T5> underlying;

    public RichVoidFunction5(VoidFunction5<T1, T2, T3, T4, T5> f) {
        this.underlying = f;
    }

    /**
     * Apply the body of this function to the arguments.
     */
    public void apply(T1 v1, T2 v2, T3 v3, T4 v4, T5 v5) throws Exception {
        underlying.apply(v1, v2, v3, v4, v5);
    }

    /**
     * Creates a tupled version of this function: instead of 5 arguments, it
     * accepts a single Tuple5 argument.
     */
    public VoidFunction1<Tuple5<T1, T2, T3, T4, T5>> tupled() {
        final RichVoidFunction5<T1, T2, T3, T4, T5> _this = this;
        return new VoidF1<Tuple5<T1, T2, T3, T4, T5>>() {
            public void apply(Tuple5<T1, T2, T3, T4, T5> tuple) throws Exception {
                _this.apply(tuple._1(), tuple._2(), tuple._3(), tuple._4(), tuple._5());
            }
        };
    }

    /**
     * Creates a String representation of this object.
     */
    @Override
    public String toString() {
        return "<void-function5>";
    }

}
