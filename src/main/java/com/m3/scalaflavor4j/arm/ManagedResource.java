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
package com.m3.scalaflavor4j.arm;

import com.m3.scalaflavor4j.Function1;
import com.m3.scalaflavor4j.ScalaFlavor4JException;
import com.m3.scalaflavor4j.VoidFunction1;

/**
 * Managed Resource (scala-arm)
 * 
 * @see "https://github.com/jsuereth/scala-arm/blob/master/src/main/scala/resource/ManagedResource.scala"
 */
public class ManagedResource<R> {

    private static final long serialVersionUID = 1L;

    private final Resource resource;

    public ManagedResource(Resource resource) {
        this.resource = resource;
    }

    @SuppressWarnings("unchecked")
    public <B> B map(final Function1<R, B> f) {
        try {
            return f.apply((R) resource.getResource());
        } catch (Exception e) {
            throw new ScalaFlavor4JException(e);
        } finally {
            resource.close();
        }
    }

    @SuppressWarnings("unchecked")
    public void foreach(VoidFunction1<R> f) {
        try {
            f.apply((R) resource.getResource());
        } catch (Exception e) {
            throw new ScalaFlavor4JException(e);
        } finally {
            resource.close();
        }
    }

}
