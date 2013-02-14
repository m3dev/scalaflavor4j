package com.m3.scalaflavor4j;

import static com.m3.scalaflavor4j.ExceptionControl.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.m3.scalaflavor4j.ExceptionControl.Catch;

public class ExceptionControlTest {

    @Test
    public void type() throws Exception {
        assertThat(ExceptionControl.class, notNullValue());
    }

    class Counter {
        int i = 0;
    }

    @Test
    @SuppressWarnings("unchecked")
    public void catching_A$ClassArray() throws Exception {

        final Counter counter = new Counter();

        Catch<String> NPE_AE = catching(NullPointerException.class, ArithmeticException.class).withApply(
                new F1<Throwable, String>() {
                    public String apply(Throwable t) {
                        return t.getMessage();
                    }
                }).andFinally(new VoidF0() {
            public void apply() throws Exception {
                counter.i++;
            }
        });

        String ok = NPE_AE.apply(new F0<String>() {
            public String apply() {
                return "ok";
            }
        });
        assertThat(ok, is(equalTo("ok")));

        String npe = NPE_AE.apply(new F0<String>() {
            public String apply() {
                throw new NullPointerException("npe");
            }
        });
        assertThat(npe, is(equalTo("npe")));

        String ae = NPE_AE.apply(new F0<String>() {
            public String apply() {
                throw new ArithmeticException("ae");
            }
        });
        assertThat(ae, is(equalTo("ae")));

        try {
            NPE_AE.apply(new F0<String>() {
                public String apply() throws Exception {
                    throw new IOException("foo");
                }
            });
        } catch (ScalaFlavor4JException e) {
            assertThat(e.getCause().getClass().getName(), is(equalTo("java.io.IOException")));
        }

        // finally handler called count
        assertThat(counter.i, is(equalTo(4)));

        // or
        Catch<String> OR = catching(NullPointerException.class).withApply(new F1<Throwable, String>() {
            public String apply(Throwable t) {
                return t.getMessage();
            }
        }).or(catching(ArithmeticException.class).withApply(new F1<Throwable, String>() {
            public String apply(Throwable v1) throws Exception {
                return "---";
            }
        }));

        assertThat(OR.apply(new F0<String>() {
            public String apply() {
                throw new NullPointerException("npe");
            }
        }), is(equalTo("npe")));
        assertThat(OR.apply(new F0<String>() {
            public String apply() {
                throw new ArithmeticException("ae");
            }
        }), is(equalTo("---")));

        Catch<String> npeCatcher = catching(NullPointerException.class).withApply(new F1<Throwable, String>() {
            public String apply(Throwable t) {
                return t.getMessage();
            }
        });

        // opt
        assertThat(npeCatcher.opt(new F0<String>() {
            public String apply() throws Exception {
                return "foo";
            }
        }).getOrNull(), is(equalTo("foo")));
        assertThat(npeCatcher.opt(new F0<String>() {
            public String apply() throws Exception {
                throw new NullPointerException();
            }
        }).isDefined(), is(false));
        try {
            npeCatcher.opt(new F0<String>() {
                public String apply() throws Exception {
                    throw new Exception();
                }
            });
            fail();
        } catch (Exception e) {
        }

        // either
        assertThat(npeCatcher.either(new F0<String>() {
            public String apply() throws Exception {
                return "foo";
            }
        }).isRight(), is(true));
        assertThat(npeCatcher.either(new F0<String>() {
            public String apply() throws Exception {
                throw new NullPointerException();
            }
        }).isLeft(), is(true));
        try {
            npeCatcher.either(new F0<String>() {
                public String apply() throws Exception {
                    throw new Exception();
                }
            });
            fail();
        } catch (Exception e) {
        }

        try {
            catching(Exception.class).withApply(new F1<Throwable, String>() {
                public String apply(Throwable v1) {
                    return null;
                }
            }).apply(new F0<String>() {
                public String apply() throws Exception {
                    throw new InterruptedException();
                }
            });
            fail();
        } catch (Exception e) {
        }

    }

    @Test
    public void allCatch_A$() throws Exception {
        String result = allCatch().withApply(new F1<Throwable, String>() {
            public String apply(Throwable v1) throws Exception {
                return "catched!";
            }
        }).apply(new F0<String>() {
            public String apply() throws Exception {
                throw new Exception();
            }
        });
        assertThat(result, is(equalTo("catched!")));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void ignoring_A$ClassArray() throws Exception {
        Catch<String> ignore = ignoring(Exception.class);
        String result = ignore.apply(new F0<String>() {
            public String apply() throws Exception {
                throw new RuntimeException();
            }
        });
        assertThat(result, is(equalTo(null)));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void catchingPromiscuously_A$ClassArray() throws Exception {
        try {
            catchingPromiscuously(Exception.class).withApply(new F1<Throwable, String>() {
                public String apply(Throwable v1) {
                    return null;
                }
            }).apply(new F0<String>() {
                public String apply() throws Exception {
                    throw new InterruptedException();
                }
            });
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void handling_A$ClassArray() throws Exception {
        String message = handling(NullPointerException.class).by(new F1<Throwable, String>() {
            public String apply(Throwable t) {
                return t.getMessage();
            }
        }).apply(new F0<String>() {
            public String apply() throws Exception {
                throw new NullPointerException("foo");
            }
        });
        assertThat(message, is(equalTo("foo")));
    }

    @Test
    public void ultimately_A$VoidFunction0() throws Exception {
        final Counter counter = new Counter();
        Catch<String> ultimately = ultimately(new VoidF0() {
            public void apply() throws Exception {
                counter.i++;
            }
        });
        String result = ultimately.apply(new F0<String>() {
            public String apply() throws Exception {
                return "foo";
            }
        });
        assertThat(result, is(equalTo("foo")));
        assertThat(counter.i, is(equalTo(1)));
    }

}
