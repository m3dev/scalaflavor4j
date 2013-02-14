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

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * scala.collection.Map
 *
 * @see "http://www.scala-lang.org/api/2.9.1/index.html#scala.collection.Map"
 */
public class SMap<K, V> {

    private final Map<K, V> map;

    private SMap() {
        this.map = new ConcurrentHashMap<K, V>();
    }

    private SMap(Map<K, V> map) {
        this.map = map;
    }

    /**
     * Retrieves the value which is associated with the given key.
     */
    public static <K, V> SMap<K, V> apply() {
        return new SMap<K, V>();
    }

    public static <K, V> SMap<K, V> apply(Map<K, V> map) {
        return new SMap<K, V>(map);
    }

    public static <K, V> SMap<K, V> apply(Seq<Tuple2<K, V>> tuples) {
        Map<K, V> map = tuples.foldLeft(new ConcurrentHashMap<K, V>(), new F2<Map<K, V>, Tuple2<K, V>, Map<K, V>>() {
            public Map<K, V> apply(Map<K, V> map, Tuple2<K, V> tuple) {
                map.put(tuple._1(), tuple._2());
                return map;
            }
        });
        return apply(map);
    }

    /**
     * Retrieves the value which is associated with the given key.
     */
    public V apply(K key) {
        return map.get(key);
    }

    /**
     * [Original] Provides as java.util.Map object
     */
    public Map<K, V> toMap() {
        return map;
    }

    /**
     * [Original] Provides as java.util.List object
     */
    public List<Tuple2<K, V>> toList() {
        List<Tuple2<K, V>> list = new ArrayList<Tuple2<K, V>>();
        Set<K> keys = map.keySet();
        for (K key : keys) {
            list.add(Tuple.apply(key, map.get(key)));
        }
        return list;
    }

    /**
     * [Original] Copy Map instance (elements are not copied)
     */
    public SMap<K, V> copy() {
        return map(new F1<Tuple2<K, V>, Tuple2<K, V>>() {
            public Tuple2<K, V> apply(Tuple2<K, V> e) {
                return e;
            }
        });
    }

    public Seq<Tuple2<K, V>> toSeq() {
        return Seq.apply(toList());
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public Iterator<Tuple2<K, V>> iterator() {
        return toList().iterator();
    }

    /**
     * Returns the value associated with a key, or a default value if the key is
     * not contained in the map.
     */
    public V getOrElse(K key, V defaultValue) {
        if (key == null) {
            return defaultValue;
        }
        V value = map.get(key);
        if (value == null) {
            return defaultValue;
        } else {
            return value;
        }
    }

    /**
     * Selects all elements of this immutable map which satisfy a predicate.
     */
    public SMap<K, V> filter(final Function1<Tuple2<K, V>, Boolean> f) {
        Map<K, V> map = toSeq().foldLeft(new ConcurrentHashMap<K, V>(), new F2<Map<K, V>, Tuple2<K, V>, Map<K, V>>() {
            public Map<K, V> apply(Map<K, V> map, Tuple2<K, V> tuple) throws Exception {
                if (f.apply(tuple)) {
                    map.put(tuple._1(), tuple._2());
                }
                return map;
            }
        });
        return SMap.apply(map);
    }

    /**
     * Applies a function f to all elements of this immutable map.
     */
    public void foreach(VoidFunction1<Tuple2<K, V>> f) {
        toSeq().foreach(f);
    }

    /**
     * Builds a new collection by applying a function to all elements of this
     * immutable map.
     */
    public <L, M> SMap<L, M> map(final Function1<Tuple2<K, V>, Tuple2<L, M>> f) {
        Map<L, M> map = toSeq().foldLeft(new ConcurrentHashMap<L, M>(), new F2<Map<L, M>, Tuple2<K, V>, Map<L, M>>() {
            public Map<L, M> apply(Map<L, M> map, Tuple2<K, V> tuple) throws Exception {
                Tuple2<L, M> mapped = f.apply(tuple);
                map.put(mapped._1(), mapped._2());
                return map;
            }
        });
        return SMap.apply(map);
    }

    /**
     * Creates a new immutable map from this immutable map with some elements
     * removed.
     */
    public SMap<K, V> minus(K... keys) {
        final SMap<K, V> copied = copy();
        Seq.apply(keys).dropNull().foreach(new VoidF1<K>() {
            public void apply(K key) {
                copied.toMap().remove(key);
            }
        });
        return copied;
    }

    /**
     * Adds two or more elements to this collection and returns a new
     * collection.
     */
    public SMap<K, V> plus(Tuple2<K, V>... elems) {
        final SMap<K, V> copied = copy();
        Seq.apply(elems).dropNull().filter(new PredicateF1<Tuple2<K, V>>() {
            public Boolean apply(Tuple2<K, V> elem) {
                return elem._1() != null && elem._2() != null;
            }
        }).foreach(new VoidF1<Tuple2<K, V>>() {
            public void apply(Tuple2<K, V> elem) {
                copied.toMap().put(elem._1(), elem._2());
            }
        });
        return copied;
    }

    /**
     * A new immutable map containing updating this map with a given key/value
     * mapping.
     */
    public SMap<K, V> updated(K key, V value) {
        SMap<K, V> copied = copy();
        if (key != null && value != null) {
            copied.toMap().put(key, value);
        }
        return copied;
    }

    /**
     * Converts this immutable map of pairs into two collections of the first
     * and second half of each pair.
     */
    public Tuple2<Seq<K>, Seq<V>> unzip() {
        final List<K> ks = new ArrayList<K>();
        final List<V> vs = new ArrayList<V>();
        toSeq().foreach(new VoidF1<Tuple2<K, V>>() {
            public void apply(Tuple2<K, V> tuple) {
                ks.add(tuple._1());
                vs.add(tuple._2());
            }
        });
        return Tuple.apply(Seq.apply(ks), Seq.apply(vs));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SMap(");
        sb.append(toSeq().map(new F1<Tuple2<K, V>, String>() {
            public String apply(Tuple2<K, V> e) {
                return e._1() + " -> " + e._2();
            }
        }).mkString(", "));
        sb.append(")");
        return sb.toString();
    }

}
