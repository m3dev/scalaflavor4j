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
 * for-comprehension factory
 */
public class For {

    private For() {
    }

    /**
     * Provides {@link ForComprehension1} instance
     */
    public static <T1> ForComprehension1<T1> _(CollectionLike<T1> xs) {
        return new ForComprehension1<T1>(xs);
    }

    /**
     * Provides {@link ForComprehension2} instance
     */
    public static <T1, T2> ForComprehension2<T1, T2> _(CollectionLike<T1> xs1, CollectionLike<T2> xs2) {
        return new ForComprehension2<T1, T2>(xs1, xs2);
    }

    /**
     * Provides {@link ForComprehension3} instance
     */
    public static <T1, T2, T3> ForComprehension3<T1, T2, T3> _(CollectionLike<T1> xs1, CollectionLike<T2> xs2,
            CollectionLike<T3> xs3) {
        return new ForComprehension3<T1, T2, T3>(xs1, xs2, xs3);
    }

    /**
     * Provides {@link ForComprehension4} instance
     */
    public static <T1, T2, T3, T4> ForComprehension4<T1, T2, T3, T4> _(CollectionLike<T1> xs1, CollectionLike<T2> xs2,
            CollectionLike<T3> xs3, CollectionLike<T4> xs4) {
        return new ForComprehension4<T1, T2, T3, T4>(xs1, xs2, xs3, xs4);
    }

    /**
     * Provides {@link ForComprehension5} instance
     */
    public static <T1, T2, T3, T4, T5> ForComprehension5<T1, T2, T3, T4, T5> _(CollectionLike<T1> xs1,
            CollectionLike<T2> xs2, CollectionLike<T3> xs3, CollectionLike<T4> xs4, CollectionLike<T5> xs5) {
        return new ForComprehension5<T1, T2, T3, T4, T5>(xs1, xs2, xs3, xs4, xs5);
    }

}
