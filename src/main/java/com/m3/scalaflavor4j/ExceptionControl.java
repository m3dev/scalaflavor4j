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
 * scala.util.control.Exception
 * 
 * @see "http://www.scala-lang.org/api/2.9.1/index.html#scala.util.control.Exception$"
 */
public class ExceptionControl {

    private ExceptionControl() {
    }

    /**
     * Creates a Catch object which will catch any of the supplied exceptions.
     */
    public static CatchBuilder catching(Class<? extends Throwable>... catcher) {
        CatchBuilder builder = new CatchBuilder(Seq._(catcher));
        builder.setPromiscuously(false);
        return builder;
    }

    /**
     * Creates a Catch object which will catch any of the supplied exceptions.
     */
    public static CatchBuilder catchingPromiscuously(Class<? extends Throwable>... catcher) {
        CatchBuilder builder = new CatchBuilder(Seq._(catcher));
        builder.setPromiscuously(true);
        return builder;
    }

    /**
     * Creates a Catch object which will catch any of the supplied exceptions.
     */
    public static HandlingByBuilder handling(Class<? extends Throwable>... catcher) {
        HandlingByBuilder builder = new HandlingByBuilder(Seq._(catcher));
        builder.setPromiscuously(false);
        return builder;
    }

    /**
     * Creates a Catch object which catches and ignores any of the supplied
     * exceptions.
     */
    public static <R> Catch<R> ignoring(Class<? extends Throwable>... catcher) {
        return new CatchBuilder(Seq._(catcher)).withApply(new F1<Throwable, R>() {
            public R _(Throwable v1) throws Exception {
                return null;
            }
        });
    }

    /**
     * Returns a Catch object with no catch logic and the argument as Finally.
     */
    @SuppressWarnings("unchecked")
    public static <R> Catch<R> ultimately(VoidFunction0 body) {
        Catch<R> c = new Catch<R>(Seq.<Class<? extends Throwable>> _(), null, false);
        c.andFinally(body);
        return c;
    }

    /**
     * A Catch object which catches everything.
     */
    @SuppressWarnings("unchecked")
    public static CatchBuilder allCatch() {
        return new CatchBuilder(Seq.<Class<? extends Throwable>> _(Throwable.class));
    }

    public static class HandlingByBuilder extends CatchBuilder {

        protected HandlingByBuilder(Seq<Class<? extends Throwable>> classesToCatch) {
            super(classesToCatch);
        }

        public <R> Catch<R> by(Function1<Throwable, R> f) {
            return super.withApply(f);
        }

    }

    /**
     * @see {@link Catch}
     */
    public static class CatchBuilder {

        protected boolean promiscuously = false;
        protected Seq<Class<? extends Throwable>> classesToCatch;

        protected CatchBuilder(Seq<Class<? extends Throwable>> classesToCatch) {
            this.classesToCatch = classesToCatch;
        }

        public boolean isPromiscuously() {
            return promiscuously;
        }

        public void setPromiscuously(boolean promiscuously) {
            this.promiscuously = promiscuously;
        }

        /**
         * Create a new Catch with the same isDefinedAt logic as this one, but
         * with the supplied apply method replacing the current one.
         */
        public <R> Catch<R> withApply(Function1<Throwable, R> f) {
            return new Catch<R>(classesToCatch, f, promiscuously);
        }

    }

    /**
     * scala.util.control.Exception.Catch
     * 
     * @see "http://www.scala-lang.org/api/2.9.1/index.html#scala.util.control.Exception$$Catch"
     */
    public static class Catch<R> extends F1<Function0<R>, R> {

        private VoidF0 defaultFinallyHandler = new VoidF0() {
            public void _() {
            }
        };

        private boolean promiscuously = false;
        private final SMap<Seq<Class<? extends Throwable>>, Function1<Throwable, R>> catchHandlers;
        private VoidFunction0 finallyHandler = defaultFinallyHandler;

        public Catch(SMap<Seq<Class<? extends Throwable>>, Function1<Throwable, R>> catchHandlers, boolean promiscuously) {
            this.catchHandlers = catchHandlers;
            this.promiscuously = promiscuously;
        }

        public Catch(Seq<Class<? extends Throwable>> classes, Function1<Throwable, R> withApply, boolean promiscuously) {
            catchHandlers = SMap.<Seq<Class<? extends Throwable>>, Function1<Throwable, R>> _().update(classes,
                    withApply);
            this.promiscuously = promiscuously;
        }

        public Catch<R> andFinally(VoidFunction0 body) {
            this.finallyHandler = body;
            return this;
        }

        @Override
        public R apply(Function0<R> f) {
            try {
                return super.apply(f);
            } catch (Exception e) {
                throw new ScalaFlavor4JException(e);
            }
        }

        /**
         * Apply this catch logic to the supplied body, mapping the result into
         * Either[Throwable, T] - Left(exception) if an exception was caught,
         * Right(T) otherwise.
         */
        public Either<Throwable, R> either(Function0<R> block) {
            try {
                Tuple2<R, Option<Throwable>> applied = _apply(block);
                if (applied._2().isDefined()) {
                    return Left._(applied._2());
                } else {
                    return Right._(applied._1());
                }
            } catch (Exception e) {
                throw new ScalaFlavor4JException(e);
            }
        }

        /**
         * Apply this catch logic to the supplied body, mapping the result into
         * Option[T] - None if any exception was caught, Some(T) otherwise.
         */
        public Option<R> opt(Function0<R> block) {
            try {
                Tuple2<R, Option<Throwable>> applied = _apply(block);
                if (applied._2().isDefined()) {
                    return Option.none();
                } else {
                    return Option._(applied._1());
                }
            } catch (Exception e) {
                throw new ScalaFlavor4JException(e);
            }
        }

        Tuple2<R, Option<Throwable>> _apply(Function0<R> block) throws Exception {
            try {
                return Tuple2._(block.apply(), Option.<Throwable> none());
            } catch (final Exception e) {
                if (!promiscuously && e instanceof InterruptedException) {
                    throw e;
                }
                Option<Tuple2<Seq<Class<? extends Throwable>>, Function1<Throwable, R>>> catchHandlerDef = catchHandlers
                        .toSeq().find(
                                new PredicateF1<Tuple2<Seq<Class<? extends Throwable>>, Function1<Throwable, R>>>() {
                                    public Boolean _(
                                            Tuple2<Seq<Class<? extends Throwable>>, Function1<Throwable, R>> catchHandlerDef) {
                                        return catchHandlerDef._1().find(new PredicateF1<Class<? extends Throwable>>() {
                                            public Boolean _(Class<? extends Throwable> clazz) throws Exception {
                                                try {
                                                    return clazz.cast(e) != null;
                                                } catch (ClassCastException cce) {
                                                    return false;
                                                }
                                            }
                                        }).isDefined();
                                    }
                                });
                if (!catchHandlerDef.isDefined()) {
                    throw e;
                }
                Seq<Class<? extends Throwable>> classesToCatch = catchHandlerDef.getOrNull()._1();
                Function1<Throwable, R> catchHandler = catchHandlerDef.getOrNull()._2();
                try {
                    Option<Class<? extends Throwable>> toCatch = classesToCatch
                            .find(new PredicateF1<Class<? extends Throwable>>() {
                                public Boolean _(Class<? extends Throwable> clazz) throws Exception {
                                    try {
                                        return clazz.cast(e) != null;
                                    } catch (ClassCastException cce) {
                                        return false;
                                    }
                                }
                            });
                    try {
                        return Tuple2._(catchHandler.apply(toCatch.getOrNull().cast(e)), Option.<Throwable> _(e));
                    } catch (Exception e2) {
                        throw e2;
                    }
                } catch (ClassCastException cce) {
                    throw e;
                }
            } finally {
                finallyHandler.apply();
            }
        }

        public R _(Function0<R> block) throws Exception {
            return _apply(block)._1();
        }

        /**
         * Create a new Catch with additional exception handling logic.
         */
        public Catch<R> or(Catch<R> that) {
            return new Catch<R>(SMap._(this.catchHandlers.toSeq().union(that.catchHandlers.toSeq())), promiscuously);
        }
    }

}
