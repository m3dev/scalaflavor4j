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

        /**
         * Handler definition
         */
        private class HandlerDef {

            private Seq<Class<? extends Throwable>> classesToHandle;
            private Function1<Throwable, R> withApply;

            public HandlerDef(Seq<Class<? extends Throwable>> classesToHandle, Function1<Throwable, R> withApply) {
                this.classesToHandle = classesToHandle;
                this.withApply = withApply;
            }
        }

        private final Seq<HandlerDef> handlerDefinitions;

        private VoidFunction0 finallyHandler = defaultFinallyHandler;

        private boolean promiscuously = false;

        public Catch(Seq<HandlerDef> handlerDefinitions, boolean promiscuously) {
            this.handlerDefinitions = handlerDefinitions;
            this.promiscuously = promiscuously;
        }

        public Catch(Seq<Class<? extends Throwable>> classes, Function1<Throwable, R> withApply) {
            this(classes, withApply, false);
        }

        @SuppressWarnings("unchecked")
        public Catch(Seq<Class<? extends Throwable>> classes, Function1<Throwable, R> withApply, boolean promiscuously) {
            this.handlerDefinitions = Seq.<HandlerDef> _(new HandlerDef(classes, withApply));
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
                Applied applied = _apply(block);
                if (applied.handled.isDefined()) {
                    return Left._(applied.handled);
                } else {
                    return Right._(applied.result);
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
                Applied applied = _apply(block);
                if (applied.handled.isDefined()) {
                    return Option.none();
                } else {
                    return Option._(applied.result);
                }
            } catch (Exception e) {
                throw new ScalaFlavor4JException(e);
            }
        }

        /**
         * @see {@link Catch#_apply(Function0)}
         */
        private class Applied {
            R result;
            Option<Throwable> handled = Option.none();

            public Applied(R result) {
                this.result = result;
            }

            public Applied(R result, Throwable handled) {
                this.result = result;
                this.handled = Option._(handled);
            }
        }

        private Applied _apply(Function0<R> block) throws Exception {
            try {
                return new Applied(block.apply());

            } catch (final Exception e) {
                if (!promiscuously && e instanceof InterruptedException) {
                    throw e;
                }
                Option<HandlerDef> handlerDefinition = handlerDefinitions.find(new PredicateF1<HandlerDef>() {
                    public Boolean _(HandlerDef handlerDef) {
                        return handlerDef.classesToHandle.find(new PredicateF1<Class<? extends Throwable>>() {
                            public Boolean _(Class<? extends Throwable> targetClass) throws Exception {
                                try {
                                    return targetClass.cast(e) != null;
                                } catch (ClassCastException cce) {
                                    return false;
                                }
                            }
                        }).isDefined();
                    }
                });
                if (!handlerDefinition.isDefined()) {
                    throw e;
                }
                Seq<Class<? extends Throwable>> classesToHandle = handlerDefinition.getOrNull().classesToHandle;
                Function1<Throwable, R> catchHandler = handlerDefinition.getOrNull().withApply;
                try {
                    Class<? extends Throwable> classToHandle = classesToHandle.find(
                            new PredicateF1<Class<? extends Throwable>>() {
                                public Boolean _(Class<? extends Throwable> classToHandle) {
                                    try {
                                        return classToHandle.cast(e) != null;
                                    } catch (ClassCastException cce) {
                                        return false;
                                    }
                                }
                            }).getOrNull();
                    try {
                        R handlerApplied = catchHandler.apply(classToHandle.cast(e));
                        return new Applied(handlerApplied, e);
                    } catch (Exception e2) {
                        throw e2;
                    }
                } catch (ClassCastException cce) {
                    // if failed to cast, throw the original exception
                    throw e;
                }
            } finally {
                finallyHandler.apply();
            }
        }

        @Override
        public R _(Function0<R> block) throws Exception {
            return _apply(block).result;
        }

        /**
         * Create a new Catch with additional exception handling logic.
         */
        public Catch<R> or(Catch<R> that) {
            return new Catch<R>(this.handlerDefinitions.union(that.handlerDefinitions), promiscuously);
        }
    }

}
