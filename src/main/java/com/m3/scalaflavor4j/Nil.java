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
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@link Seq} implementation
 */
public class Nil<T> extends Seq<T> {

    private static final long serialVersionUID = 1L;

    private Nil() {
    }

    public static <T> Nil<T> apply() {
        return new Nil<T>();
    }

    @Override
    public List<T> toList() {
        return new ArrayList<T>();
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public <U> Seq<U> map(Function1<T, U> f) {
        return new Nil<U>();
    }

    @Override
    public void foreach(VoidFunction1<T> f) {
        // nothing to do
    }

    @Override
    public Seq<T> filter(Function1<T, Boolean> f) {
        return new Nil<T>();
    }

    @Override
    public <U> Seq<U> flatMap(Function1<T, CollectionLike<U>> f) {
        return new Nil<U>();
    }

    @Override
    public T head() {
        return null;
    }

    @Override
    public Option<T> headOption() {
        return Option.none();
    }

    @Override
    public T last() {
        return null;
    }

    @Override
    public Option<T> lastOption() {
        return Option.none();
    }

    @Override
    public Seq<T> tail() {
        return new Nil<T>();
    }

    @Override
    public String mkString() {
        return mkString("", "", "");
    }

    @Override
    public String mkString(String sep) {
        return mkString("", sep, "");
    }

    @Override
    public String mkString(String start, String sep, String end) {
        return start + "" + end;
    }

    @Override
    public Seq<T> sortWith(Function2<T, T, Boolean> lt) {
        return new Nil<T>();
    }

    @Override
    public Seq<T> take(int n) {
        return new Nil<T>();
    }

    @Override
    public <U> U foldLeft(U z, Function2<U, T, U> op) {
        return z;
    }

    @Override
    public <U> Function1<Function2<U, T, U>, U> foldLeft(final U z) {
        return new Function1<Function2<U, T, U>, U>() {
            public U apply(Function2<U, T, U> f) {
                return z;
            }
        };
    }

    @Override
    public <U> U foldRight(U z, Function2<T, U, U> op) {
        return z;
    }

    @Override
    public <U> Function1<Function2<T, U, U>, U> foldRight(final U z) {
        return new Function1<Function2<T, U, U>, U>() {
            public U apply(Function2<T, U, U> f) {
                return z;
            }
        };
    }

    @Override
    public <U> Function1<Function2<T, U, Boolean>, Boolean> corresponds(final Seq<U> that) {
        return new F1<Function2<T, U, Boolean>, Boolean>() {
            public Boolean apply(Function2<T, U, Boolean> f) throws Exception {
                return that.isEmpty();
            }
        };
    }

    @Override
    public <U> boolean corresponds(Seq<U> that, Function2<T, U, Boolean> p) {
        return that.isEmpty();
    }

    @Override
    public Seq<T> distinct() {
        return new Nil<T>();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public <U> SMap<U, Seq<T>> groupBy(Function1<T, U> f) {
        return SMap.apply(new ConcurrentHashMap<U, Seq<T>>());
    }

    @Override
    public Seq<Tuple2<T, Integer>> zipWithIndex() {
        return new Nil<Tuple2<T, Integer>>();
    }

    @Override
    public Option<T> find(Function1<T, Boolean> p) {
        return Option.none();
    }

    @Override
    public Tuple2<Seq<T>, Seq<T>> partition(Function1<T, Boolean> p) {
        Seq<T> nil = Seq.apply((List<T>) null);
        return Tuple.apply(nil, nil);
    }

    @Override
    public Tuple2<Seq<T>, Seq<T>> splitAt(int n) {
        Seq<T> nil = Seq.apply((List<T>) null);
        return Tuple.apply(nil, nil);
    }

    @Override
    public Seq<T> reverse() {
        return Seq.apply((List<T>) null);
    }

    @Override
    public Seq<T> slice(int from, int until) {
        return Seq.apply((List<T>) null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Seq<Seq<T>> sliding(int size) {
        return Seq.apply(Seq.apply((List<T>) null));
    }

    @Override
    @SuppressWarnings("unchecked")
    public Seq<Seq<T>> sliding(int size, int step) {
        return Seq.apply(Seq.apply((List<T>) null));
    }

    @Override
    public Tuple2<Seq<T>, Seq<T>> span(Function1<T, Boolean> p) {
        Seq<T> nil = Seq.apply((List<T>) null);
        return Tuple.apply(nil, nil);
    }

    @Override
    public Seq<T> takeRight(int n) {
        return Seq.apply((List<T>) null);
    }

    @Override
    public Seq<T> takeWhile(Function1<T, Boolean> p) {
        return Seq.apply((List<T>) null);
    }

    @Override
    public boolean exists(Function1<T, Boolean> p) {
        return false;
    }

    @Override
    public boolean forall(Function1<T, Boolean> p) {
        return true;
    }

    @Override
    public int count(Function1<T, Boolean> p) {
        return 0;
    }

    @Override
    public Seq<T> drop(int n) {
        return Seq.apply((List<T>) null);
    }

    @Override
    public Seq<T> dropRight(int n) {
        return Seq.apply((List<T>) null);
    }

    @Override
    public Seq<T> dropWhile(Function1<T, Boolean> p) {
        return Seq.apply((List<T>) null);
    }

    @Override
    public <U> Seq<Tuple2<T, U>> zip(Seq<U> that) {
        return Seq.apply((List<Tuple2<T, U>>) null);
    }

    @Override
    public boolean contains(T elem) {
        return false;
    }

    @Override
    public Seq<T> diff(Seq<T> that) {
        return Seq.apply((List<T>) null);
    }

    @Override
    public boolean startsWith(Seq<T> that) {
        return false;
    }

    @Override
    public boolean startsWith(Seq<T> that, int offset) {
        return false;
    }

    @Override
    public boolean endsWith(Seq<T> that) {
        return false;
    }

    @Override
    public int indexOf(T elem) {
        return -1;
    }

    @Override
    public boolean isDefinedAt(int idx) {
        return false;
    }

    @Override
    public Seq<Integer> indices() {
        return Seq.apply((List<Integer>) null);
    }

    @Override
    public <U> Seq<U> reverseMap(Function1<T, U> f) {
        return Seq.apply((List<U>) null);
    }

    @Override
    public Seq<T> patch(int from, Seq<T> patch, int replaced) {
        return patch;
    }

    @Override
    public Seq<T> updated(int index, T elem) {
        throw new UnsupportedOperationException("empty.tail");
    }

    @Override
    public Seq<T> union(Seq<T> that) {
        return that;
    }

    @Override
    public SNum sum() {
        return SNum.apply(0);
    }

    @Override
    public SNum max() {
        return SNum.apply(0);
    }

    @Override
    public SNum min() {
        return SNum.apply(0);
    }

    @Override
    public boolean sameElements(Seq<T> that) {
        return that.isEmpty();
    }

    @Override
    public Seq<T> intersect(Seq<T> that) {
        return Seq.apply((List<T>) null);
    }

    @Override
    public Seq<T> padTo(int len, final T elem) {
        return (Seq<T>) SInt.apply(0).until(len).map(new F1<Integer, T>() {
            public T apply(Integer i) {
                return elem;
            }
        });
    }

    @Override
    public <U> Seq<U> scanLeft(U z, Function2<U, T, U> op) {
        return Nil.apply();
    }

    @Override
    public <U> Function1<Function2<U, T, U>, Seq<U>> scanLeft(U z) {
        return new F1<Function2<U, T, U>, Seq<U>>() {
            public Seq<U> apply(Function2<U, T, U> f) {
                return Nil.apply();
            }
        };
    }

    @Override
    public <U> Seq<U> scanRight(U z, Function2<T, U, U> op) {
        return Nil.apply();
    }

    @Override
    public <U> Function1<Function2<T, U, U>, Seq<U>> scanRight(U z) {
        return new F1<Function2<T, U, U>, Seq<U>>() {
            public Seq<U> apply(Function2<T, U, U> f) {
                return Nil.apply();
            }
        };
    }

    @Override
    public Seq<T> filterNot(Function1<T, Boolean> f) {
        return Nil.apply();
    }

    @Override
    public Seq<T> append(T... that) {
        return Seq.apply(that);
    }

    @Override
    public String toString() {
        return "Seq()";
    }

    @Override
    public Seq<T> dropNull() {
        return Nil.apply();
    }

    @Override
    public <U> U reduceLeft(Function2<U, T, U> op) {
        return null;
    }

    @Override
    public <U> Option<U> reduceLeftOption(Function2<U, T, U> op) {
        return Option.none();
    }

    @Override
    public <U> U reduceRight(Function2<T, U, U> op) {
        return null;
    }

    @Override
    public <U> Option<U> reduceRightOption(Function2<T, U, U> op) {
        return Option.none();
    }

    @Override
    public Seq<T> init() {
        throw new UnsupportedOperationException("empty.init");
    }

    @Override
    @SuppressWarnings("unchecked")
    public ParSeq<T> par() {
        return ParSeq.<T>apply();
    }

    @Override
    public Seq<T> transpose() {
        return Nil.apply();
    }

}
