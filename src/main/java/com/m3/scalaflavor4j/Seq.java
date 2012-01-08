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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * scala.collection.Seq
 * 
 * @see "http://www.scala-lang.org/api/2.9.1/index.html#scala.collection.Seq"
 */
public abstract class Seq<T> implements CollectionLike<T> {

    private static final long serialVersionUID = 1L;

    public static <T> Seq<T> apply(T... values) {
        return _(values);
    }

    public static <T> Seq<T> _(T... values) {
        List<T> list = new ArrayList<T>();
        for (T value : values) {
            list.add(value);
        }
        return apply(list);
    }

    public static <T> Seq<T> apply(Collection<T> col) {
        return _(col);
    }

    public static <T> Seq<T> _(Collection<T> col) {
        if (col == null || col.size() == 0) {
            return Nil.apply();
        }
        return IndexedSeq._(new ArrayList<T>(col));
    }

    /**
     * [Original] Appends the passed elements
     * 
     * This method is similar to scala.collectoin.Seq#+:(A)
     */
    public abstract Seq<T> append(T... that);

    /**
     * Tests whether this sequence contains a given value as an element.
     */
    public abstract boolean contains(T elem);

    /**
     * Counts the number of elements in the sequence which satisfy a predicate.
     */
    public abstract int count(Function1<T, Boolean> p);

    /**
     * Computes the multiset difference between this sequence and another
     * sequence.
     */
    public abstract Seq<T> diff(Seq<T> that);

    /**
     * Builds a new sequence from this sequence without any duplicate elements.
     */
    public abstract Seq<T> distinct();

    /**
     * Selects all elements except first n ones.
     */
    public abstract Seq<T> drop(int n);

    /**
     * [Original] Drops null elements
     */
    public abstract Seq<T> dropNull();

    /**
     * Selects all elements except last n ones.
     */
    public abstract Seq<T> dropRight(int n);

    /**
     * Drops longest prefix of elements that satisfy a predicate.
     */
    public abstract Seq<T> dropWhile(Function1<T, Boolean> p);

    /**
     * Tests whether this sequence ends with the given sequence.
     */
    public abstract boolean endsWith(Seq<T> that);

    /**
     * Tests whether a predicate holds for some of the elements of this
     * sequence.
     */
    public abstract boolean exists(Function1<T, Boolean> p);

    /**
     * Selects all elements of this sequence which satisfy a predicate.
     */
    public abstract Seq<T> filter(Function1<T, Boolean> f);

    /**
     * Selects all elements of this immutable sequence which do not satisfy a
     * predicate.
     */
    public abstract Seq<T> filterNot(Function1<T, Boolean> f);

    /**
     * Finds the first element of the sequence satisfying a predicate, if any.
     */
    public abstract Option<T> find(Function1<T, Boolean> p);

    /**
     * Builds a new collection by applying a function to all elements of this
     * sequence and concatenating the results.
     */
    public abstract <U> Seq<U> flatMap(Function1<T, CollectionLike<U>> f);

    /**
     * Applies a binary operator to a start value and all elements of this
     * sequence, going left to right.
     */
    public abstract <U> U foldLeft(U z, Function2<U, T, U> op);

    /**
     * Applies a binary operator to all elements of this sequence and a start
     * value, going right to left.
     */
    public abstract <U> U foldRight(U z, Function2<T, U, U> op);

    /**
     * Tests whether a predicate holds for all elements of this sequence.
     */
    public abstract boolean forall(Function1<T, Boolean> p);

    /**
     * Partitions this sequence into a map of sequences according to some
     * discriminator function.
     */
    public abstract <U> SMap<U, Seq<T>> groupBy(Function1<T, U> f);

    /**
     * Selects the first element of this sequence.
     */
    public abstract T head();

    /**
     * Optionally selects the first element.
     */
    public abstract Option<T> headOption();

    /**
     * Finds index of first occurrence of some value in this sequence after or
     * at some start index.
     */
    public abstract int indexOf(T elem);

    /**
     * Produces the range of all indices of this sequence.
     */
    public abstract Seq<Integer> indices();

    /**
     * Computes the multiset intersection between this sequence and another
     * sequence.
     */
    public abstract Seq<T> intersect(Seq<T> that);

    /**
     * Tests whether this sequence contains given index.
     */
    public abstract boolean isDefinedAt(int idx);

    /**
     * Selects the last element.
     */
    public abstract T last();

    /**
     * Optionally selects the last element.
     */
    public abstract Option<T> lastOption();

    /**
     * Builds a new collection by applying a function to all elements of this
     * sequence.
     */
    public abstract <U> Seq<U> map(Function1<T, U> f);

    /**
     * Finds the largest element.
     */
    public abstract SNum max();

    /**
     * Finds the smallest element.
     */
    public abstract SNum min();

    /**
     * Displays all elements of this sequence in a string.
     */
    public abstract String mkString();

    /**
     * Displays all elements of this sequence in a string using a separator
     * string.
     */
    public abstract String mkString(String sep);

    /**
     * Displays all elements of this sequence in a string using start, end, and
     * separator strings.
     */
    public abstract String mkString(String start, String sep, String end);

    /**
     * Appends an element value to this sequence until a given target length is
     * reached.
     */
    public abstract Seq<T> padTo(int len, T elem);

    /**
     * Partitions this sequence in two sequences according to a predicate.
     */
    public abstract Tuple2<Seq<T>, Seq<T>> partition(Function1<T, Boolean> p);

    /**
     * Produces a new sequence where a slice of elements in this sequence is
     * replaced by another sequence.
     */
    public abstract Seq<T> patch(int from, Seq<T> patch, int replaced);

    /**
     * Applies a binary operator to all elements of this immutable sequence,
     * going left to right.
     */
    public abstract <U> U reduceLeft(Function2<U, T, U> op);

    /**
     * Optionally applies a binary operator to all elements of this immutable
     * sequence, going left to right.
     */
    public abstract <U> Option<U> reduceLeftOption(Function2<U, T, U> op);

    /**
     * Applies a binary operator to all elements of this immutable sequence,
     * going right to left.
     */
    public abstract <U> U reduceRight(Function2<T, U, U> op);

    /**
     * Optionally applies a binary operator to all elements of this immutable
     * sequence, going right to left.
     */
    public abstract <U> Option<U> reduceRightOption(Function2<T, U, U> op);

    /**
     * Returns new sequence wih elements in reversed order.
     */
    public abstract Seq<T> reverse();

    /**
     * Builds a new collection by applying a function to all elements of this
     * sequence and collecting the results in reversed order.
     */
    public abstract <U> Seq<U> reverseMap(Function1<T, U> f);

    /**
     * Checks if the other iterable collection contains the same elements in the
     * same order as this sequence.
     */
    public abstract boolean sameElements(Seq<T> that);

    /**
     * Produces a collection containing cummulative results of applying the
     * operator going left to right.
     */
    public abstract <U> Seq<U> scanLeft(U z, Function2<U, T, U> op);

    /**
     * Produces a collection containing cummulative results of applying the
     * operator going right to left.
     */
    public abstract <U> Seq<U> scanRight(U z, Function2<T, U, U> op);

    /**
     * The size of this sequence, equivalent to length.
     */
    public abstract int size();

    /**
     * Selects an interval of elements.
     */
    public abstract Seq<T> slice(int from, int until);

    /**
     * Groups elements in fixed size blocks by passing a "sliding window" over
     * them (as opposed to partitioning them, as is done in grouped.
     */
    public abstract Seq<Seq<T>> sliding(int size);

    /**
     * Groups elements in fixed size blocks by passing a "sliding window" over
     * them (as opposed to partitioning them, as is done in grouped.
     */
    public abstract Seq<Seq<T>> sliding(int size, int step);

    /**
     * Sorts this sequence according to a comparison function.
     */
    public abstract Seq<T> sortWith(Function2<T, T, Boolean> lt);

    /**
     * Splits this sequence into a prefix/suffix pair according to a predicate.
     */
    public abstract Tuple2<Seq<T>, Seq<T>> span(Function1<T, Boolean> p);

    /**
     * Splits this sequence into two at a given position.
     */
    public abstract Tuple2<Seq<T>, Seq<T>> splitAt(int n);

    /**
     * Tests whether this sequence contains the given sequence at a given index.
     */
    public abstract boolean startsWith(Seq<T> that);

    /**
     * Tests whether this sequence contains the given sequence at a given index.
     */
    public abstract boolean startsWith(Seq<T> that, int offset);

    /**
     * Sums up the elements of this collection.
     */
    public abstract SNum sum();

    /**
     * Selects all elements except the first.
     */
    public abstract Seq<T> tail();

    /**
     * Selects first n elements.
     */
    public abstract Seq<T> take(int n);

    /**
     * Selects last n elements.
     */
    public abstract Seq<T> takeRight(int n);

    /**
     * Takes longest prefix of elements that satisfy a predicate.
     */
    public abstract Seq<T> takeWhile(Function1<T, Boolean> p);

    /**
     * Produces a new sequence which contains all elements of this sequence and
     * also all elements of a given sequence.
     */
    public abstract Seq<T> union(Seq<T> that);

    /**
     * A copy of this sequence with one single replaced element.
     */
    public abstract Seq<T> updated(int index, T elem);

    /**
     * Returns a sequence formed from this sequence and another iterable
     * collection by combining corresponding elements in pairs.
     */
    public abstract <U> Seq<Tuple2<T, U>> zip(Seq<U> that);

    /**
     * Zips this sequence with its indices.
     */
    public abstract Seq<Tuple2<T, Integer>> zipWithIndex();

}