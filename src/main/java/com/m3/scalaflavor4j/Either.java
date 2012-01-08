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
