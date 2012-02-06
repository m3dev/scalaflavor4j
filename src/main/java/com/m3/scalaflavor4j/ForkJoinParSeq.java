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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jsr166y.ForkJoinPool;

/**
 * {@link ParSeq} implementation
 */
public class ForkJoinParSeq<T> extends ParSeq<T> {

    private static final long serialVersionUID = 1L;

    private final Nil<T> NIL = Nil._();

    private static final Logger logger = Logger.getLogger(ForkJoinParSeq.class.getCanonicalName());

    private static final ForkJoinPool forkJoinPool = new ForkJoinPool();

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
    private static <T> T doBlocking(final F0<T> future) {
        return handling(Exception.class).by(new F1<Throwable, T>() {
            public T _(Throwable t) throws Exception {
                throw new ScalaFlavor4JException(t);
            }
        }).apply(new F0<T>() {
            public T _() throws Exception {
                return future._();
            }

        });
    }

    @Override
    public int count(final Function1<T, Boolean> predicate) {
        if (isEmpty()) {
            return NIL.count(predicate);
        }
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
        if (isEmpty()) {
            return NIL.exists(predicate);
        }
        return map(new F1<T, Boolean>() {
            public Boolean _(T element) throws Exception {
                return predicate.apply(element);
            }
        }).toSeq().contains(true);
    }

    @Override
    public ParSeq<T> filter(final Function1<T, Boolean> predicate) {
        if (isEmpty()) {
            return ParSeq._(NIL.filter(predicate).toList());
        }
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
        if (isEmpty()) {
            return ParSeq._(NIL.filterNot(predicate).toList());
        }
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
        if (isEmpty()) {
            return ParSeq._(NIL.flatMap(f).toList());
        }
        LinkedList<F0<CollectionLike<U>>> futures = new LinkedList<F0<CollectionLike<U>>>();
        for (final T element : collection) {
            futures.add(future(new F0<CollectionLike<U>>() {
                public CollectionLike<U> _() throws Exception {
                    return f.apply(element);
                }
            }));
        }
        LinkedList<U> results = new LinkedList<U>();
        for (F0<CollectionLike<U>> future : futures) {
            results.addAll(doBlocking(future).toList());
        }
        return ParSeq._(results);
    }

    @Override
    public boolean forall(final Function1<T, Boolean> predicate) {
        if (isEmpty()) {
            return NIL.forall(predicate);
        }
        Seq<Boolean> result = map(new F1<T, Boolean>() {
            public Boolean _(T element) throws Exception {
                return predicate.apply(element);
            }
        }).toSeq().distinct();
        return result.size() == 1 && result.head();
    }

    @Override
    public void foreach(final VoidFunction1<T> f) {
        if (isEmpty()) {
            NIL.foreach(f);
            return;
        }
        for (final T element : collection) {
            forkJoinPool.execute(new Runnable() {
                public void run() {
                    try {
                        f.apply(element);
                    } catch (Exception t) {
                        logger.log(Level.WARNING, "Exception is thrown on a spawn thread.", t);
                    }
                }
            });
        }
    }

    private class GroupEntry<U> {
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
        if (isEmpty()) {
            return NIL.groupBy(getGroupName);
        }
        ParSeq<GroupEntry<U>> entries = map(new F1<T, GroupEntry<U>>() {
            public GroupEntry<U> _(T element) throws Exception {
                U name = getGroupName.apply(element);
                return new GroupEntry<U>(name, element);
            }
        });
        return entries.toSeq().foldLeft(SMap.<U, Seq<T>> _(), new FoldLeftF2<SMap<U, Seq<T>>, GroupEntry<U>>() {
            public SMap<U, Seq<T>> _(SMap<U, Seq<T>> map, GroupEntry<U> entry) throws Exception {
                Seq<T> groupMembers = map.getOrElse(entry.groupName, Seq.<T> _());
                return map.updated(entry.groupName, groupMembers.append(entry.member));
            };
        });
    }

    @Override
    public boolean isEmpty() {
        return collection == null || collection.size() == 0;
    }

    @Override
    public <U> ParSeq<U> map(final Function1<T, U> f) {
        if (isEmpty()) {
            return ParSeq._(NIL.map(f).toList());
        }
        LinkedList<F0<U>> futures = new LinkedList<F0<U>>();
        for (final T element : collection) {
            futures.add(future(new F0<U>() {
                public U _() throws Exception {
                    return f.apply(element);
                }
            }));
        }
        LinkedList<U> results = new LinkedList<U>();
        for (F0<U> future : futures) {
            results.add(doBlocking(future));
        }
        return ParSeq._(results);
    }

    @Override
    public List<T> toList() {
        return new ArrayList<T>(collection);
    }

    @Override
    public Seq<T> toSeq() {
        return Seq._(new ArrayList<T>(collection));
    }

}
