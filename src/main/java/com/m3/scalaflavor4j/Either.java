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
 * scala.Either (not exactly)
 * 
 * @see "http://www.scala-lang.org/api/2.9.1/index.html#scala.Either"
 */
public abstract class Either<L, R> {

    /**
     * The left side of the disjoint union, as opposed to the `Right` side.
     */
    public static class Left<L, R> extends Either<L, R> {

        public static <L, R> Left<L, R> apply(L value) {
            return _(value);
        }

        public static <L, R> Left<L, R> apply(Option<L> value) {
            return _(value);
        }

        public static <L, R> Left<L, R> _(L value) {
            return new Left<L, R>(value);
        }

        public static <L, R> Left<L, R> _(Option<L> value) {
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

    }

    /**
     * The right side of the disjoint union, as opposed to the `Left` side.
     */
    public static class Right<L, R> extends Either<L, R> {

        public static <L, R> Right<L, R> apply(R value) {
            return _(value);
        }

        public static <L, R> Right<L, R> apply(Option<R> value) {
            return _(value);
        }

        public static <L, R> Right<L, R> _(R value) {
            return new Right<L, R>(value);
        }

        public static <L, R> Right<L, R> _(Option<R> value) {
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

    /**
     * Returns true if this is a Left, false otherwise.
     */
    public abstract boolean isLeft();

    /**
     * Returns true if this is a Right, false otherwise.
     */
    public abstract boolean isRight();

    /**
     * Projects this Either as a Left.
     * 
     * [NOTE] Different from Scala
     */
    public abstract Option<L> left();

    /**
     * Projects this Either as a Right.
     * 
     * [NOTE] Different from Scala
     */
    public abstract Option<R> right();

    /**
     * If this is a Left, then return the left value in Right or vice versa.
     */
    public abstract Either<R, L> swap();

}
