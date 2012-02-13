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
public class ForComprehension3<T1, T2, T3> {

    private final Generator<T1> xs1;
    private final Generator<T2> xs2;
    private final Generator<T3> xs3;

    public ForComprehension3(CollectionLike<T1> xs1, CollectionLike<T2> xs2, CollectionLike<T3> xs3) {
        this.xs1 = Generator._(xs1);
        this.xs2 = Generator._(xs2);
        this.xs3 = Generator._(xs3);
    }

    @SuppressWarnings("unchecked")
    public <U, V extends CollectionLike<U>> V yield(final Function1<Tuple3<T1, T2, T3>, U> f) {
        return (V) xs1.flatMap(new FlatMapF1<T1, U>() {
            public CollectionLike<U> _(final T1 t1) {
                return xs2.flatMap(new FlatMapF1<T2, U>() {
                    public CollectionLike<U> _(final T2 t2) {
                        return xs3.map(new F1<T3, U>() {
                            public U _(T3 t3) throws Exception {
                                return f.apply(Tpl._(t1, t2, t3));
                            }
                        });
                    }
                });
            }
        });
    }

    public void _(final VoidFunction1<Tuple3<T1, T2, T3>> f) {
        xs1.foreach(new VoidF1<T1>() {
            public void _(final T1 t1) {
                xs2.foreach(new VoidF1<T2>() {
                    public void _(final T2 t2) throws Exception {
                        xs3.foreach(new VoidF1<T3>() {
                            public void _(T3 t3) throws Exception {
                                f.apply(Tpl._(t1, t2, t3));
                            }
                        });
                    }
                });
            }
        });
    }

}
