package com.m3.scalaflavor4j;

import static com.m3.scalaflavor4j.ConcurrentOps.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ConcurrentOpsTest {

    @Test
    public void type() throws Exception {
        assertThat(ConcurrentOps.class, notNullValue());
    }

    @Test
    public void future_A$Function0() throws Exception {
        F0<String> f = future(new F0<String>() {
            public String _() throws Exception {
                Thread.sleep(1000L);
                return "foo";
            }
        });
        assertThat(f._(), is(equalTo("foo")));
    }

    @Test
    public void par_A$Function0$Function0() throws Exception {
        Tuple2<String, Integer> tuple = par(new F0<String>() {
            public String _() throws Exception {
                Thread.sleep(500L);
                return "foo";
            }
        }, new F0<Integer>() {
            public Integer _() throws Exception {
                Thread.sleep(1000L);
                return 123;
            }
        });
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
            public void _() throws Exception {
                called.value = true;
            }
        });
        Thread.sleep(200L);
        assertThat(called.value, is(true));
    }

}
