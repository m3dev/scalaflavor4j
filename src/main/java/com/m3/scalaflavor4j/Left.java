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
 * The left side of the disjoint union, as opposed to the Right side.
 * 
 * @see "http://www.scala-lang.org/api/2.9.1/index.html#scala.Left"
 */
public class Left<L, R> extends Either<L, R> {

    public static <L, R> Either<L, R> apply(L value) {
        return _(value);
    }

    public static <L, R> Either<L, R> apply(Option<L> value) {
        return _(value);
    }

    public static <L, R> Left<L, R> _(L value) {
        return new Left<L, R>(value);
    }

    public static <L, R> Either<L, R> _(Option<L> value) {
        return new Left<L, R>(value);
    }

    private Option<L> value;

    public Left(L value) {
        this.value = Option._(value);
    }

    public Left(Option<L> value) {
        this.value = value;
    }

    @Override
    public boolean isLeft() {
        return true;
    }

    @Override
    public boolean isRight() {
        return false;
    }

    @Override
    public Option<L> left() {
        return value;
    }

    @Override
    public Option<R> right() {
        return Option.none();
    }

    @Override
    public Either<R, L> swap() {
        return new Right<R, L>(value);
    }

    @Override
    public String toString() {
        return "Left(" + value.getOrNull() + ")";
    }

    @Override
    public L mergeToLeft(Function1<R, L> rightToLeft) {
        return value.getOrNull();
    }

    @Override
    public R mergeToRight(Function1<L, R> leftToRight) {
        try {
            return leftToRight.apply(value.getOrNull());
        } catch (Throwable t) {
            throw new ScalaFlavor4JException(t);
        }
    }

}
