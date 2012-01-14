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

import static com.m3.scalaflavor4j.arm.Resource.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * This object provides convenience methods to create an iterable representation
 * of a source file.
 * 
 * @see "http://www.scala-lang.org/api/current/index.html#scala.io.BufferedSource"
 */
public class BufferedSource {

    private final InputStream stream;

    private final String encoding;

    public BufferedSource(File file, String enc) throws FileNotFoundException {
        this.stream = new FileInputStream(file);
        this.encoding = enc;
    }

    public BufferedSource(InputStream is, String enc) throws FileNotFoundException {
        this.stream = is;
        this.encoding = enc;
    }

    public InputStreamReader getInputStreamReader() throws UnsupportedEncodingException {
        if (encoding == null) {
            return new InputStreamReader(stream);
        } else {
            return new InputStreamReader(stream, encoding);
        }
    }

    /**
     * Reads as a byte list
     */
    public Seq<Byte> toByteSeq() throws IOException {
        return managed(stream).map(new F1<InputStream, Seq<Byte>>() {
            public Seq<Byte> _(InputStream stream) throws Exception {
                return managed(getInputStreamReader()).map(new F1<InputStreamReader, Seq<Byte>>() {
                    public Seq<Byte> _(InputStreamReader isr) throws Exception {
                        List<Byte> bs = new ArrayList<Byte>();
                        int b;
                        while ((b = isr.read()) != -1) {
                            bs.add((byte) b);
                        }
                        return Seq._(bs);
                    }
                });
            }
        });
    }

    /**
     * Reads as a char list
     */
    public Seq<Character> toCharSeq() throws IOException {
        return managed(stream).map(new F1<InputStream, Seq<Character>>() {
            public Seq<Character> _(InputStream stream) throws Exception {
                return managed(getInputStreamReader()).map(new F1<InputStreamReader, Seq<Character>>() {
                    public Seq<Character> _(InputStreamReader isr) throws Exception {
                        List<Character> cs = new ArrayList<Character>();
                        int c;
                        while ((c = isr.read()) != -1) {
                            cs.add((char) c);
                        }
                        return Seq._(cs);
                    }
                });
            }
        });
    }

    /**
     * Reads as a string list
     */
    public Seq<String> toStringSeq() throws IOException {
        return getLines();
    }

    /**
     * Reads input lines
     */
    public Seq<String> getLines() throws IOException {
        return managed(stream).map(new F1<InputStream, Seq<String>>() {
            public Seq<String> _(InputStream stream) throws Exception {
                return managed(getInputStreamReader()).map(new F1<InputStreamReader, Seq<String>>() {
                    public Seq<String> _(InputStreamReader isr) throws Exception {
                        return managed(new BufferedReader(isr)).map(new F1<BufferedReader, Seq<String>>() {
                            public Seq<String> _(BufferedReader br) throws Exception {
                                List<String> lines = new ArrayList<String>();
                                String line;
                                while ((line = br.readLine()) != null) {
                                    lines.add(line);
                                }
                                return Seq._(lines);
                            }
                        });
                    }
                });
            }
        });
    }

}
