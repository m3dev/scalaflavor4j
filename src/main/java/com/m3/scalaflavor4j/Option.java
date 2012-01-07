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
 * scala.Option
 * 
 * @see "http://www.scala-lang.org/api/2.9.1/index.html#scala.Option"
 */
public abstract class Option<T> implements CollectionLike<T> {

    private static final long serialVersionUID = 1L;

    public static <T> Option<T> _(T value) {
        return apply(value);
    }

    public static <T> Option<T> apply(T value) {
        if (value == null) {
            return new None<T>();
        } else {
            return new Some<T>(value);
        }
    }

    public static <T> Option<T> none() {
        return apply(null);
    }

    /**
     * scala.Option#orNull()
     * 
     * Returns the option's value if it is nonempty, or null if it is empty.
     */
    public abstract T getOrNull();

    /**
     * Returns the option's value if the option is nonempty, otherwise return
     * the result of evaluating default.
     */
    public abstract T getOrElse(T defaultValue);

    /**
     * Returns true if the option is an instance of Some, false otherwise.
     */
    public abstract boolean isDefined();

    /**
     * Selects all elements of this sequence which satisfy a predicate.
     */
    public abstract Option<T> filter(Function1<T, Boolean> f);

    /**
     * Returns the result of applying f to this Option's value if this Option is
     * nonempty.
     */
    public abstract <U> Option<U> flatMap(Function1<T, Option<U>> f);

    /**
     * Builds a new collection by applying a function to all elements of this
     * sequence.
     */
    public abstract <U> Option<U> map(Function1<T, U> f);

}
