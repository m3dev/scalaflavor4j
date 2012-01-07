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
 * A function of 1 parameter (return type void).
 * 
 * @see "http://www.scala-lang.org/api/2.9.1/index.html#scala.Function1"
 */
public abstract class VoidFunction1<T1> {

    public void apply(T1 v1) throws Exception {
        _(v1);
    }

    /**
     * Apply the body of this function to the argument.
     */
    public abstract void _(T1 v1) throws Exception;

    /**
     * Creates a String representation of this object.
     */
    @Override
    public String toString() {
        return "<void-function1>";
    }

}
