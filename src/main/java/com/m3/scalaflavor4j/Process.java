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
 * scala.sys.process.Process
 * 
 * @see "http://www.scala-lang.org/api/2.9.1/index.html#scala.sys.process.Process"
 * @see "http://www.scala-lang.org/api/2.9.1/index.html#scala.sys.process.Process$"
 * @see "http://www.scala-lang.org/api/2.9.1/index.html#scala.sys.process.ProcessBuilder"
 */
public class Process {

    private String command;

    public static Process apply(String command) {
        return _(command);
    }

    public static Process _(String command) {
        return new Process(command);
    }

    public Process(String command) {
        this.command = command;
    }

    /**
     * scala.sys.process.ProcessBuilder#!
     * 
     * Starts the process represented by this builder, blocks until it exits,
     * and returns the exit code.
     */
    public int run() throws Exception {
        java.lang.Process process = Runtime.getRuntime().exec(command);
        process.waitFor();
        return process.exitValue();
    }

    /**
     * scala.sys.process.ProcessBuilder#!!
     * 
     * Starts the process represented by this builder, blocks until it exits,
     * and returns the output as a String.
     */
    public Seq<String> runAndGetStdout() throws Exception {
        java.lang.Process process = Runtime.getRuntime().exec(command);
        process.waitFor();
        return Source.fromInputStream(process.getInputStream()).getLines();
    }

}
