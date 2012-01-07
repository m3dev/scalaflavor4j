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

import java.io.Serializable;
import java.util.List;

/**
 * scala.collection.* or scala.Option
 */
public interface CollectionLike<T> extends Serializable {

    /**
     * [Original] Produces as java.util.List object
     */
    List<T> toList();

    /**
     * Tests whether the sequence is empty.
     */
    boolean isEmpty();

    /**
     * Applies a function f to all elements of this sequence.
     */
    void foreach(VoidFunction1<T> f);

}
