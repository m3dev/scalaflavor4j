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

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import jsr166y.ForkJoinPool;

/**
 * scala.concurrent.ops
 * 
 * @see "http://www.scala-lang.org/api/current/index.html#scala.concurrent.ops$"
 */
public class ConcurrentOps {

    private ConcurrentOps() {
    }

    private static final Logger logger = Logger.getLogger(ConcurrentOps.class.getCanonicalName());

    private static ForkJoinPool forkJoinPool = new ForkJoinPool();

    /**
     * Evaluates an expression asynchronously, and returns a closure for
     * retrieving the result.
     */
    public static <R> F0<R> future(final Function0<R> p) {
        final FutureTask<R> future = new FutureTask<R>(new Callable<R>() {
            public R call() throws Exception {
                return p.apply();
            }
        });
        forkJoinPool.execute(future);
        return new F0<R>() {
            public R _() throws InterruptedException, ExecutionException {
                return future.get();
            }
        };
    }

    /**
     * Evaluates two expressions in parallel.
     */
    public static <A, B> Tuple2<A, B> par(final Function0<A> xp, final Function0<B> yp) throws InterruptedException,
            ExecutionException {
        final FutureTask<A> xpFuture = new FutureTask<A>(new Callable<A>() {
            public A call() throws Exception {
                return xp.apply();
            }
        });
        final FutureTask<B> ypFuture = new FutureTask<B>(new Callable<B>() {
            public B call() throws Exception {
                return yp.apply();
            }
        });
        forkJoinPool.execute(xpFuture);
        forkJoinPool.execute(ypFuture);
        return Tuple._(xpFuture.get(), ypFuture.get());
    }

    /**
     * Evaluates an expression asynchronously.
     */
    public static void spawn(final VoidFunction0 p) {
        forkJoinPool.execute(new Runnable() {
            public void run() {
                try {
                    p.apply();
                } catch (Exception t) {
                    logger.log(Level.WARNING, "Exception is thrown on a spawn thread.", t);
                }
            }
        });
    }

}
