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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * This object provides convenience methods to create an iterable representation
 * of a source file.
 * 
 * @see "http://www.scala-lang.org/api/current/index.html#scala.io.Source$"
 * @see "http://www.scala-lang.org/api/current/index.html#scala.io.Source"
 */
public class Source<T> {

    private Source() {
    }

    /**
     * Creates Source from file, using given character encoding, setting its
     * description to filename.
     */
    public static BufferedSource fromFile(String name, String enc) throws IOException {
        return new BufferedSource(new File(name), enc);
    }

    /**
     * Creates Source from file, using given character encoding, setting its
     * description to filename.
     */
    public static BufferedSource fromFile(File file, String enc) throws IOException {
        return new BufferedSource(file, enc);
    }

    /**
     * Creates Source from InputStream.
     */
    public static BufferedSource fromInputStream(InputStream is) throws IOException {
        return new BufferedSource(is, null);
    }

    /**
     * Creates Source from InputStream.
     */
    public static BufferedSource fromInputStream(InputStream is, String enc) throws IOException {
        return new BufferedSource(is, enc);
    }

    /**
     * Create a Source from URL
     */
    public static BufferedSource fromURL(URL url, String enc) throws IOException {
        return new BufferedSource(url.openStream(), enc);
    }

}
