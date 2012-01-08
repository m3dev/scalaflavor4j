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
 * The right side of the disjoint union, as opposed to the `Left` side.
 * 
 * @see "http://www.scala-lang.org/api/2.9.1/index.html#scala.Right"
 */
public class Right<L, R> extends Either<L, R> {

    public static <L, R> Either<L, R> apply(R value) {
        return _(value);
    }

    public static <L, R> Either<L, R> apply(Option<R> value) {
        return _(value);
    }

    public static <L, R> Either<L, R> _(R value) {
        return new Right<L, R>(value);
    }

    public static <L, R> Either<L, R> _(Option<R> value) {
        return new Right<L, R>(value);
    }

    private Option<R> value;

    public Right(R value) {
        this.value = Option._(value);
    }

    public Right(Option<R> value) {
        this.value = value;
    }

    @Override
    public boolean isLeft() {
        return false;
    }

    @Override
    public boolean isRight() {
        return true;
    }

    @Override
    public Option<L> left() {
        return Option.none();
    }

    @Override
    public Option<R> right() {
        return value;
    }

    @Override
    public Either<R, L> swap() {
        return new Left<R, L>(value);
    }

    @Override
    public String toString() {
        return "Right(" + value.getOrNull() + ")";
    }

}
