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
            public Integer apply(String t) {
                return t.length();
            };
        };
        assertThat(count, notNullValue());
    }

    @Test
    public void __A$() throws Exception {
        assertThat(new Function1<String, Integer>() {
            public Integer apply(String t) {
                return t.length();
            };
        }.apply("foo"), is(equalTo(3)));
    }

    static class Something {
        void doSomething() throws Exception {
        }
    }

    @Test
    public void __A$_withCheckedException() throws Exception {

        assertThat(new Function1<String, Integer>() {
            public Integer apply(String t) throws Exception {
                new Something().doSomething();
                return t.length();
            };
        }.apply("foo"), is(equalTo(3)));
    }

    @Test
    public void compose_A$Function1() throws Exception {
        Function1<Long, Integer> f = new Function1<Long, Integer>() {
            public Integer apply(Long v1) {
                return v1.intValue();
            }
        };
        RichFunction1<String, Long> g = new RichFunction1(new Function1<String, Long>() {
            public Long apply(String v1) {
                return Long.valueOf(v1.length());
            }
        });
        RichFunction1<String, Integer> composed = new RichFunction1<Long, Integer>(f).compose(g);
        assertThat(composed.apply("foo"), is(equalTo(3)));
    }

    @Test
    public void andThen_A$Function1() throws Exception {
        Function1<String, Long> f = new Function1<String, Long>() {
            @Override
            public Long apply(String v1) {
                return Long.valueOf(v1.length());
            }
        };
        RichFunction1<Long, Integer> g = new RichFunction1(new Function1<Long, Integer>() {
            public Integer apply(Long v1) {
                return v1.intValue();
            }
        });
        RichFunction1<String, Integer> fAndThenG = new RichFunction1(f).andThen(g);
        assertThat(fAndThenG.apply("foo"), is(equalTo(3)));
    }

    @Test
    public void toString_A$() throws Exception {
        RichFunction1<String, Integer> count = new RichFunction1(new Function1<String, Integer>() {
            public Integer apply(String t) {
                return t.length();
            };
        });
        String actual = count.toString();
        String expected = "<function1>";
        assertThat(actual, is(equalTo(expected)));
    }

}
