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
import java.util.Collection;

/**
 * scala.collection.immutable.StringLike
 * 
 * @see "http://www.scala-lang.org/api/2.9.1/index.html#scala.collection.immutable.StringLike"
 */
public class StringLike extends IndexedSeq<Character> {

    private static final long serialVersionUID = 1L;

    protected StringLike(Collection<Character> list) {
        super(list);
    }

    public static StringLike apply(String str) {
        Collection<Character> cs = new ArrayList<Character>();
        if (str != null) {
            for (char c : str.toCharArray()) {
                cs.add(c);
            }
        }
        return new StringLike(cs);
    }

    @Override
    public String toString() {
        return mkString();
    }

}
