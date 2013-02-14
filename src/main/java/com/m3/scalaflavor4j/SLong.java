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
 * scala.runtime.RichLong
 *
 * @see "http://www.scala-lang.org/api/2.9.1/index.html#scala.runtime.RichLong"
 */
public class SLong {

    private final Long value;

    public static SLong apply(Long i) {
        return new SLong(i);
    }

    public Long getOrElse(Long defaultValue) {
        if (value == null) {
            return defaultValue;
        } else {
            return value;
        }
    }

    private SLong(Long i) {
        if (i == null) {
            throw new IllegalArgumentException("Long value should not be null.");
        }
        this.value = i;
    }

    public IndexedSeq<Long> to(Long j) {
        List<Long> range = new ArrayList<Long>();
        for (long i = value; i <= j; i++) {
            range.add(i);
        }
        return IndexedSeq.apply(range);
    }

    public IndexedSeq<Long> to(Long j, Long step) {
        List<Long> range = new ArrayList<Long>();
        for (long i = value; i <= j; i += step) {
            range.add(i);
        }
        return IndexedSeq.apply(range);
    }

    public IndexedSeq<Long> until(Long j) {
        List<Long> range = new ArrayList<Long>();
        for (long i = value; i < j; i++) {
            range.add(i);
        }
        return IndexedSeq.apply(range);
    }

    public IndexedSeq<Long> until(Long j, Long step) {
        List<Long> range = new ArrayList<Long>();
        for (long i = value; i < j; i += step) {
            range.add(i);
        }
        return IndexedSeq.apply(range);
    }

    @Override
    public String toString() {
        return "SLong(" + value + ")";
    }

}
