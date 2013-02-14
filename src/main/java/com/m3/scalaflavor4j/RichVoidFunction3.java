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
 * A function of 3 parameters (return type void).
 * 
 * @see "http://www.scala-lang.org/api/2.9.1/index.html#scala.Function3"
 */
public class RichVoidFunction3<T1, T2, T3> implements VoidFunction3<T1,T2,T3> {

    private final VoidFunction3<T1,T2,T3> underlying;

    public RichVoidFunction3(VoidFunction3<T1,T2,T3>  f) {
        this.underlying = f;
    }

    /**
     * Apply the body of this function to the arguments.
     */
    public void apply(T1 v1, T2 v2, T3 v3) throws Exception {
        underlying.apply(v1, v2, v3);
    }

    /**
     * Creates a tupled version of this function: instead of 3 arguments, it
     * accepts a single Tuple3 argument.
     */
    public VoidFunction1<Tuple3<T1, T2, T3>> tupled() {
        final RichVoidFunction3<T1, T2, T3> _this = this;
        return new VoidF1<Tuple3<T1, T2, T3>>() {
            public void apply(Tuple3<T1, T2, T3> tuple) throws Exception {
                _this.apply(tuple._1(), tuple._2(), tuple._3());
            }
        };
    }

    /**
     * Creates a String representation of this object.
     */
    @Override
    public String toString() {
        return "<void-function3>";
    }

}
