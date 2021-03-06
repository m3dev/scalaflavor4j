package com.m3.scalaflavor4j;

import org.junit.Test;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.m3.scalaflavor4j.ConcurrentOps.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class ConcurrentOpsTest {

    @Test
    public void type() throws Exception {
        assertThat(ConcurrentOps.class, notNullValue());
    }

    @Test
    public void future_A$Function0() throws Exception {
        Future<String> f = future(new F0<String>() {
            public String apply() throws Exception {
                Thread.sleep(1000L);
                return "foo";
            }
        });
        assertThat(f.get(), is(equalTo("foo")));
    }

    @Test(expected = TimeoutException.class)
    public void future_A$Function0_timeout() throws Exception {
        Future<String> f = future(new F0<String>() {
            public String apply() throws Exception {
                Thread.sleep(1000L);
                return "foo";
            }
        });
        f.get(1L, TimeUnit.MICROSECONDS);
    }

    @Test
    public void par_A$Function0$Function0() throws Exception {
        Tuple2<String, Integer> tuple = par(new F0<String>() {
                                                public String apply() throws Exception {
                                                    Thread.sleep(500L);
                                                    return "foo";
                                                }
                                            }, new F0<Integer>() {
                                                public Integer apply() throws Exception {
                                                    Thread.sleep(1000L);
                                                    return 123;
                                                }
                                            }
        );
        assertThat(tuple._1(), is(equalTo("foo")));
        assertThat(tuple._2(), is(equalTo(123)));
    }

    class Flag {
        boolean value = false;
    }

    @Test
    public void spawn_A$VoidFunction0() throws Exception {
        final Flag called = new Flag();
        spawn(new VoidF0() {
            public void apply() throws Exception {
                called.value = true;
            }
        });
        Thread.sleep(200L);
        assertThat(called.value, is(true));
    }

}

