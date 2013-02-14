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

/**
 * scala.Some
 * 
 * Class Some[A] represents existing values of type A.
 * 
 * @seee "http://www.scala-lang.org/api/2.9.1/index.html#scala.Some"
 */
public class Some<T> extends Option<T> {

    private static final long serialVersionUID = 1L;

    private final T value;

    public Some(T value) {
        this.value = value;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<T>();
        list.add(value);
        return list;
    }

    @Override
    public T getOrNull() {
        return value;
    }

    @Override
    public T getOrElse(T defaultValue) {
        return value;
    }

    @Override
    public boolean isDefined() {
        return value != null;
    }

    @Override
    public boolean isEmpty() {
        return !isDefined();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Option<?>) {
            Option<?> opt = (Option<?>) obj;
            return opt.isDefined() && opt.getOrNull().equals(this.value);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Some(" + value + ")";
    }

    @Override
    public int hashCode() {
        if (value != null) {
            return value.hashCode();
        } else {
            return 0;
        }
    }

    @Override
    public <U> Option<U> map(Function1<T, U> f) {
        try {
            return Option.apply(f.apply(value));
        } catch (Throwable e) {
            throw new ScalaFlavor4JException(e);
        }
    }

    @Override
    public void foreach(VoidFunction1<T> f) {
        try {
            f.apply(value);
        } catch (Throwable e) {
            throw new ScalaFlavor4JException(e);
        }
    }

    @Override
    public Option<T> filter(Function1<T, Boolean> f) {
        try {
            if (f.apply(value)) {
                return this;
            } else {
                return Option.none();
            }
        } catch (Throwable e) {
            throw new ScalaFlavor4JException(e);
        }
    }

    @Override
    public <U> Option<U> flatMap(Function1<T, Option<U>> f) {
        try {
            if (isDefined()) {
                return f.apply(value);
            } else {
                return Option.none();
            }
        } catch (Throwable e) {
            throw new ScalaFlavor4JException(e);
        }
    }

    @Override
    public <U> U fold(U ifEmpty, Function1<T, U> f) {
        try {
            if (isEmpty()) {
                return ifEmpty;
            } else {
                return f.apply(getOrNull());
            }
        } catch (Throwable e) {
            throw new ScalaFlavor4JException(e);
        }
    }

    @Override
    public <U> Function1<Function1<T, U>, U> fold(final U ifEmpty) {
        return new F1<Function1<T, U>, U>() {
            public U apply(Function1<T, U> f) throws Exception {
                if (isEmpty()) {
                    return ifEmpty;
                } else {
                    return f.apply(getOrNull());
                }
            }
        };
    }

}
