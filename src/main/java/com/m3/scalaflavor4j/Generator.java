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

public class Generator<T> {

    private final CollectionLike<T> _xs;

    public Generator(CollectionLike<T> xs) {
        this._xs = xs;
    }

    public static <T> Generator<T> apply(CollectionLike<T> xs) {
        return _(xs);
    }

    public static <T> Generator<T> _(CollectionLike<T> xs) {
        return new Generator<T>(xs);
    }

    <U> CollectionLike<U> toCollectionLike(List<U> xs) {
        if (_xs instanceof Option) {
            if (xs != null && xs.size() == 0) {
                return Option.none();
            } else if (xs != null && xs.size() == 1) {
                return Option._(xs.get(0));
            }
        }
        return Seq._(xs);
    }

    public <U> CollectionLike<U> map(final Function1<T, U> f) {
        final List<U> xs = new ArrayList<U>();
        _xs.foreach(new VoidF1<T>() {
            public void _(T x) throws Exception {
                xs.add(f.apply(x));
            }
        });
        return toCollectionLike(xs);
    }

    public <U> CollectionLike<U> flatMap(final Function1<T, CollectionLike<U>> f) {
        final List<CollectionLike<U>> unflatten = new ArrayList<CollectionLike<U>>();
        _xs.foreach(new VoidF1<T>() {
            public void _(T x) throws Exception {
                unflatten.add(f.apply(x));
            }
        });
        final List<U> xs = new ArrayList<U>();
        Seq._(unflatten).foreach(new VoidF1<CollectionLike<U>>() {
            public void _(CollectionLike<U> col) throws Exception {
                xs.addAll(col.toList());
            }
        });
        return toCollectionLike(xs);
    }

    public void foreach(VoidFunction1<T> vf) {
        _xs.foreach(vf);
    }

}
