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

import static com.m3.scalaflavor4j.ConcurrentOps.*;
import static com.m3.scalaflavor4j.ExceptionControl.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.Future;

/**
 * {@link ParSeq} implementation
 */
public class ForkJoinParSeq<T> extends ParSeq<T> {

    private static final long serialVersionUID = 1L;

    protected final Collection<T> collection;

    public static <T> ForkJoinParSeq<T> apply(T... values) {
        return _(values);
    }

    public static <T> ForkJoinParSeq<T> _(T... values) {
        LinkedList<T> list = new LinkedList<T>();
        Collections.addAll(list, values);
        return _(list);
    }

    public static <T> ForkJoinParSeq<T> apply(Collection<T> collection) {
        return _(collection);
    }

    public static <T> ForkJoinParSeq<T> _(Collection<T> collection) {
        return new ForkJoinParSeq<T>(collection);
    }

    ForkJoinParSeq(Collection<T> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("List should not be null.");
        }
        this.collection = collection;
    }

    @SuppressWarnings("unchecked")
    private static <T> T doBlocking(final Future<T> future) {
        return handling(Exception.class).by(new F1<Throwable, T>() {
            public T _(Throwable t) throws Exception {
                throw new ScalaFlavor4JException(t);
            }
        }).apply(new F0<T>() {
            public T _() throws Exception {
                return future.get();
            }

        });
    }

    @Override
    public int count(final Function1<T, Boolean> predicate) {
        return map(new F1<T, Integer>() {
            public Integer _(T element) throws Exception {
                return predicate.apply(element) ? 1 : 0;
            }
        }).toSeq().foldLeft(0, new FoldLeftF2<Integer, Integer>() {
            public Integer _(Integer sum, Integer i) throws Exception {
                return sum + i;
            }
        });
    }

    @Override
    public boolean exists(final Function1<T, Boolean> predicate) {
        return map(new F1<T, Boolean>() {
            public Boolean _(T element) throws Exception {
                return predicate.apply(element);
            }
        }).toSeq().contains(true);
    }

    @Override
    public ParSeq<T> filter(final Function1<T, Boolean> predicate) {
        return flatMap(new F1<T, CollectionLike<T>>() {
            public CollectionLike<T> _(T element) throws Exception {
                if (predicate.apply(element)) {
                    return Option._(element);
                }
                return Option.none();
            }
        });
    }

    @Override
    public ParSeq<T> filterNot(final Function1<T, Boolean> predicate) {
        return flatMap(new F1<T, CollectionLike<T>>() {
            public CollectionLike<T> _(T element) throws Exception {
                if (!predicate.apply(element)) {
                    return Option._(element);
                }
                return Option.none();
            }
        });
    }

    @Override
    public <U> ParSeq<U> flatMap(final Function1<T, CollectionLike<U>> f) {
        LinkedList<Future<CollectionLike<U>>> futures = new LinkedList<Future<CollectionLike<U>>>();
        for (final T element : collection) {
            futures.add(future(new F0<CollectionLike<U>>() {
                public CollectionLike<U> _() throws Exception {
                    return f.apply(element);
                }
            }));
        }
        LinkedList<U> results = new LinkedList<U>();
        for (Future<CollectionLike<U>> future : futures) {
            results.addAll(doBlocking(future).toList());
        }
        return ParSeq._(results);
    }

    @Override
    public boolean forall(final Function1<T, Boolean> predicate) {
        Seq<Boolean> result = map(new F1<T, Boolean>() {
            public Boolean _(T element) throws Exception {
                return predicate.apply(element);
            }
        }).toSeq().distinct();
        return result.size() == 1 && result.head();
    }

    class GroupEntry<U> {
        U groupName;
        T member;

        public GroupEntry(U name, T value) {
            this.groupName = name;
            this.member = value;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <U> SMap<U, Seq<T>> groupBy(final Function1<T, U> getGroupName) {
        ParSeq<GroupEntry<U>> groupMembers = map(new F1<T, GroupEntry<U>>() {
            public GroupEntry<U> _(T element) throws Exception {
                U name = getGroupName.apply(element);
                return new GroupEntry<U>(name, element);
            }
        });
        return groupMembers.toSeq().foldLeft(SMap.<U, Seq<T>> _(), new FoldLeftF2<SMap<U, Seq<T>>, GroupEntry<U>>() {
            public SMap<U, Seq<T>> _(SMap<U, Seq<T>> map, GroupEntry<U> member) throws Exception {
                Seq<T> groupMembers = map.getOrElse(member.groupName, Seq.<T> _());
                return map.update(member.groupName, groupMembers.append(member.member));
            };
        });
    }

    @Override
    public <U> ParSeq<U> map(final Function1<T, U> f) {
        LinkedList<Future<U>> futures = new LinkedList<Future<U>>();
        for (final T element : collection) {
            futures.add(future(new F0<U>() {
                public U _() throws Exception {
                    return f.apply(element);
                }
            }));
        }
        LinkedList<U> results = new LinkedList<U>();
        for (Future<U> future : futures) {
            results.add(doBlocking(future));
        }
        return ParSeq._(results);
    }

    @Override
    public Seq<T> toSeq() {
        return Seq._(new ArrayList<T>(collection));
    }

}
