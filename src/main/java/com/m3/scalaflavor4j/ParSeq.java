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

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 * A template trait for parallel sequences.
 */
public abstract class ParSeq<T> implements CollectionLike<T> {

    private static final long serialVersionUID = 1L;

    public static <T> ParSeq<T> apply(T... values) {
        return _(values);
    }

    public static <T> ParSeq<T> _(T... values) {
        LinkedList<T> collection = new LinkedList<T>();
        Collections.addAll(collection, values);
        return new ForkJoinParSeq<T>(collection);
    }

    public static <T> ParSeq<T> apply(Collection<T> collection) {
        return _(collection);
    }

    public static <T> ParSeq<T> _(Collection<T> collection) {
        return new ForkJoinParSeq<T>(collection);
    }

    /**
     * Counts the number of elements in the sequence which satisfy a predicate.
     */
    public abstract int count(final Function1<T, Boolean> predicate);

    /**
     * Tests whether a predicate holds for some of the elements of this
     * sequence.
     */
    public abstract boolean exists(Function1<T, Boolean> p);

    /**
     * Selects all elements of this sequence which satisfy a predicate.
     */
    public abstract ParSeq<T> filter(Function1<T, Boolean> f);

    /**
     * Selects all elements of this immutable sequence which do not satisfy a
     * predicate.
     */
    public abstract ParSeq<T> filterNot(Function1<T, Boolean> f);

    /**
     * Builds a new collection by applying a function to all elements of this
     * sequence and concatenating the results.
     */
    public abstract <U> ParSeq<U> flatMap(Function1<T, CollectionLike<U>> f);

    /**
     * Tests whether a predicate holds for all elements of this sequence.
     */
    public abstract boolean forall(Function1<T, Boolean> p);

    /**
     * Applies a function f to all elements of this sequence.
     */
    public abstract void foreach(VoidFunction1<T> f);

    /**
     * Partitions this sequence into a map of sequences according to some
     * discriminator function.
     */
    public abstract <U> SMap<U, Seq<T>> groupBy(Function1<T, U> f);

    /**
     * Builds a new collection by applying a function to all elements of this
     * sequence.
     */
    public abstract <U> ParSeq<U> map(Function1<T, U> f);

    /**
     * Converts to {@link Seq}
     */
    public abstract Seq<T> toSeq();

}
