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

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Number (BigDecimal wrapper)
 */
public class SNum {

    private final BigDecimal value;

    private SNum(BigDecimal i) {
        if (i == null) {
            throw new IllegalArgumentException("Integer value should not be null.");
        }
        this.value = i;
    }

    public static SNum apply(Integer i) {
        return new SNum(new BigDecimal(i));
    }

    public static SNum apply(BigDecimal i) {
        return new SNum(i);
    }

    public BigDecimal toBigDecimal() {
        return value;
    }

    public BigInteger toBigInteger() {
        return value.toBigInteger();
    }

    public Double toDouble() {
        return value.doubleValue();
    }

    public Float toFloat() {
        return value.floatValue();
    }

    public Integer toInt() {
        return value.intValue();
    }

    public Long toLong() {
        return value.longValue();
    }

    public SInt toSInt() {
        return SInt.apply(value.intValue());
    }

    public SLong toSLong() {
        return SLong.apply(value.longValue());
    }

    @Override
    public String toString() {
        return "SNum(" + value + ")";
    }

}
