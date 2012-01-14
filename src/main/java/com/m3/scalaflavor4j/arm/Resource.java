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

import static com.m3.scalaflavor4j.ExceptionControl.*;

import java.lang.reflect.Method;

import com.m3.scalaflavor4j.F0;
import com.m3.scalaflavor4j.F1;
import com.m3.scalaflavor4j.ScalaFlavor4JException;

/**
 * Resource (Scala ARM like, not exactly)
 * 
 * @see "https://github.com/jsuereth/scala-arm/blob/master/src/main/scala/resource/package.scala"
 */
public class Resource {

    private Object closable;

    public Resource(Object closable) {
        this.closable = closable;
    }

    public Object getResource() {
        return this.closable;
    }

    public void close() {
        try {
            Class<?> clazz = closable.getClass();
            Method close = clazz.getDeclaredMethod("close", (Class<?>[]) null);
            close.invoke(closable, (Object[]) null);
        } catch (IllegalAccessException overridedInputStream) {
        } catch (Exception e) {
            throw new ScalaFlavor4JException(e);
        }
    }

    @SuppressWarnings("unchecked")
    static void ensureClosable(final Object closable) {
        catching(SecurityException.class, NoSuchMethodException.class).withApply(new F1<Throwable, Object>() {
            public Object _(Throwable t) throws Exception {
                throw new IllegalArgumentException(closable.getClass().getCanonicalName()
                        + " does not have close method.");
            }
        }).apply(new F0<Object>() {
            public Object _() throws SecurityException, NoSuchMethodException {
                Class<?> clazz = closable.getClass();
                clazz.getDeclaredMethod("close", (Class<?>[]) null);
                return closable;
            }
        });
    }

    public static <R> ManagedResource<R> managed(R closable) {
        ensureClosable(closable);
        return new ManagedResource<R>(new Resource(closable));
    }

}
