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
 * A function of 1 parameter.
 *
 * @see "http://www.scala-lang.org/api/2.9.1/index.html#scala.Function1"
 */
public class RichFunction1<T1, R> implements Function1<T1, R> {

    private final Function1<T1, R> underlying;

    public RichFunction1() {
        underlying = null;
    }

    public RichFunction1(Function1<T1, R> f) {
        underlying = f;
    }

    /**
     * Apply the body of this function to the arguments.
     */
    public R apply(T1 v1) throws Exception {
        if (underlying == null) {
            throw new IllegalStateException("Function is empty.");
        }
        return underlying.apply(v1);
    }

    /**
     * Composes two instances of Function1 in a new Function1, with this
     * function applied last.
     */
    public <A> RichFunction1<A, R> compose(RichFunction1<A, T1> g) {
        final RichFunction1<T1, R> f = this;
        final RichFunction1<A, T1> _g = g;
        return new RichFunction1<A, R>() {
            public R apply(A v1) throws Exception {
                return f.apply(_g.apply(v1));
            }
        };
    }

    /**
     * Composes two instances of Function1 in a new Function1, with this
     * function applied first.
     */
    public <A> RichFunction1<T1, A> andThen(RichFunction1<R, A> g) {
        final RichFunction1<T1, R> f = this;
        final RichFunction1<R, A> _g = g;
        return new RichFunction1<T1, A>() {
            @Override
            public A apply(T1 v1) throws Exception {
                return _g.apply(f.apply(v1));
            }
        };
    }

    /**
     * Creates a String representation of this object.
     */
    @Override
    public String toString() {
        return "<function1>";
    }

}
