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
 * for-comprehension
 * 
 * @see "http://www.scala-lang.org/node/111"
 */
public class ForComprehension1<T> {

    private final Generator<T> xs;

    public ForComprehension1(CollectionLike<T> xs) {
        this.xs = Generator.apply(xs);
    }

    @SuppressWarnings("unchecked")
    public ForComprehension1(T x) {
        this.xs = Generator.apply(Seq.<T> apply(x));
    }

    @SuppressWarnings("unchecked")
    public <U, V extends CollectionLike<U>> V yield(final Function1<T, U> f) {
        return (V) xs.map(new F1<T, U>() {
            public U apply(T x) throws Exception {
                return f.apply(x);
            }
        });
    }

    public void apply(final VoidFunction1<T> f) {
        xs.foreach(new VoidF1<T>() {
            public void apply(final T x) throws Exception {
                f.apply(x);
            }
        });
    }

}
