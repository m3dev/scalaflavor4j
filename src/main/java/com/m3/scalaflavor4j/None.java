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
 * scala.None
 * 
 * This case object represents non-existent values.
 * 
 * @see "http://www.scala-lang.org/api/2.9.1/index.html#scala.None$"
 */
public class None<T> extends Option<T> {

    private static final long serialVersionUID = 1L;

    public None() {
    }

    @Override
    public List<T> toList() {
        return new ArrayList<T>();
    }

    @Override
    public T getOrNull() {
        return null;
    }

    @Override
    public T getOrElse(T defaultValue) {
        return defaultValue;
    }

    @Override
    public boolean isDefined() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public <U> Option<U> map(Function1<T, U> f) {
        return Option.none();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof None<?>;
    }

    @Override
    public String toString() {
        return "None";
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public void foreach(VoidFunction1<T> f) {
        // nothing to do
    }

    @Override
    public Option<T> filter(Function1<T, Boolean> f) {
        return Option.none();
    }

    @Override
    public <U> Option<U> flatMap(Function1<T, Option<U>> f) {
        return Option.none();
    }

    @Override
    public <U> U fold(U ifEmpty, Function1<T, U> f) {
        return ifEmpty;
    }

    @Override
    public <U> Function1<Function1<T, U>, U> fold(final U ifEmpty) {
        return new F1<Function1<T, U>, U>() {
            public U _(Function1<T, U> f) {
                return ifEmpty;
            }
        };
    }

}