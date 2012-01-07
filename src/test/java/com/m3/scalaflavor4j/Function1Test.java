package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class Function1Test {

    @Test
    public void type() throws Exception {
        assertThat(Function1.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        Function1<String, Integer> count = new Function1<String, Integer>() {
            public Integer _(String t) {
                return t.length();
            };
        };
        assertThat(count, notNullValue());
    }

    @Test
    public void __A$() throws Exception {
        assertThat(new Function1<String, Integer>() {
            public Integer _(String t) {
                return t.length();
            };
        }._("foo"), is(equalTo(3)));
    }

    static class Something {
        void doSomething() throws Exception {
        }
    }

    @Test
    public void __A$_withCheckedException() throws Exception {

        assertThat(new Function1<String, Integer>() {
            public Integer _(String t) throws Exception {
                new Something().doSomething();
                return t.length();
            };
        }._("foo"), is(equalTo(3)));
    }

    @Test
    public void compose_A$Function1() throws Exception {
        Function1<Long, Integer> f = new Function1<Long, Integer>() {
            public Integer _(Long v1) {
                return v1.intValue();
            }
        };
        Function1<String, Long> g = new Function1<String, Long>() {
            @Override
            public Long _(String v1) {
                return Long.valueOf(v1.length());
            }
        };
        Function1<String, Integer> composed = f.compose(g);
        assertThat(composed._("foo"), is(equalTo(3)));
    }

    @Test
    public void andThen_A$Function1() throws Exception {
        Function1<String, Long> f = new Function1<String, Long>() {
            @Override
            public Long _(String v1) {
                return Long.valueOf(v1.length());
            }
        };
        Function1<Long, Integer> g = new Function1<Long, Integer>() {
            @Override
            public Integer _(Long v1) {
                return v1.intValue();
            }
        };
        Function1<String, Integer> fAndThenG = f.andThen(g);
        assertThat(fAndThenG._("foo"), is(equalTo(3)));
    }

    @Test
    public void toString_A$() throws Exception {
        Function1<String, Integer> count = new Function1<String, Integer>() {
            public Integer _(String t) {
                return t.length();
            };
        };
        String actual = count.toString();
        String expected = "<function1>";
        assertThat(actual, is(equalTo(expected)));
    }

}
