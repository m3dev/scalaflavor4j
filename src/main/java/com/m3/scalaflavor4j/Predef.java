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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Iterator;

import com.m3.scalaflavor4j.Seq;
import static com.m3.scalaflavor4j.arm.Resource.*;

/**
 * * The Predef object provides definitions that are accessible in all Scala *
 * compilation units without explicit qualification.
 */
public class Predef {
    private Predef() {
    }

    public static <T> void print(T x) {
        System.out.print(x);
    }

    public static <T> void println(T x) {
        System.out.println(x);
    }

    public static void println() {
        System.out.println();
    }

    public static void printf(String text, Object... xs) {
        System.out.printf(text, xs);
    }

    public static String readLine() {
        try {
            return managed(System.in).map(new F1<InputStream, String>() {
                public String apply(InputStream is) throws IOException {
                    return managed(new InputStreamReader(is)).map(new F1<InputStreamReader, String>() {
                        public String apply(InputStreamReader isr) throws IOException {
                            return managed(new BufferedReader(isr)).map(new F1<BufferedReader, String>() {
                                public String apply(BufferedReader br) throws IOException {
                                    return br.readLine();
                                }
                            });
                        }
                    });
                }
            });
        } catch (Throwable e) {
            throw new ScalaFlavor4JException(e);
        }
    }

    public static Boolean readBoolean() {
        try {
            return Boolean.valueOf(readLine());
        } catch (Throwable e) {
            return false;
        }
    }

    public static Byte readByte() {
        return Byte.valueOf(readLine());
    }

    public static Character readChar() {
        return readLine().charAt(0);
    }

    public static Double readDouble() {
        return Double.valueOf(readLine());
    }

    public static Float readFloat() {
        return Float.valueOf(readLine());
    }

    public static Integer readInt() {
        return Integer.valueOf(readLine());
    }

    public static Long readLong() {
        return Long.valueOf(readLine());
    }

    public static Short readShort() {
        return Short.valueOf(readLine());
    }

    public static <T> Seq<T> seq(T... values) {
        return Seq.apply(values);
    }

    public static <T> Seq<T> seq(Enumeration<T> e) {
        return Seq.apply(e);
    }

    public static <T> Seq<T> seq(Iterator<T> iter) {
        return Seq.apply(iter);
    }
   
    public static <T> Seq<T> seq(Iterable<T> iterable) {
        return Seq.apply(iterable);
    }
}
