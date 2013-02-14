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
 * A function of 2 parameters (return type void).
 *
 * @see "http://www.scala-lang.org/api/2.9.1/index.html#scala.Function2"
 */
public class RichVoidFunction2<T1, T2> implements VoidFunction2<T1, T2> {

    private final VoidFunction2<T1, T2> underlying;

    public RichVoidFunction2(VoidFunction2<T1, T2> f) {
        this.underlying = f;
    }

    /**
     * Apply the body of this function to the arguments.
     */
    public void apply(T1 v1, T2 v2) throws Exception {
        underlying.apply(v1, v2);
    }

    /**
     * Creates a tupled version of this function: instead of 2 arguments, it
     * accepts a single Tuple2 argument.
     */
    public VoidFunction1<Tuple2<T1, T2>> tupled() {
        final RichVoidFunction2<T1, T2> _this = this;
        return new VoidF1<Tuple2<T1, T2>>() {
            public void apply(Tuple2<T1, T2> tuple) throws Exception {
                _this.apply(tuple._1(), tuple._2());
            }
        };
    }

    /**
     * Creates a String representation of this object.
     */
    @Override
    public String toString() {
        return "<void-function2>";
    }

}
