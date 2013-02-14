package com.m3.scalaflavor4j;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class Function5Test {

    @Test
    public void type() throws Exception {
        assertThat(Function5.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        F5<String, String, String, String, String, Integer> target = new F5<String, String, String, String, String, Integer>() {
            public Integer apply(String v1, String v2, String v3, String v4, String v5) {
                return (v1 + v2 + v3 + v4 + v5).length();
            }
        };
        assertThat(target, notNullValue());
    }

    @Test
    public void tupled_A$() throws Exception {
        RichFunction5<String, String, String, String, String, Integer> f = new RichFunction5(new F5<String, String, String, String, String, Integer>() {
            public Integer apply(String v1, String v2, String v3, String v4, String v5) {
                return (v1 + v2 + v3 + v4 + v5).length();
            }
        });
        int result = f.tupled().apply(Tuple.apply("a", "b", "c", "d", "e"));
        assertThat(result, is(equalTo(5)));
    }

    @Test
    public void curried_A$() throws Exception {
        RichFunction5<String, String, String, String, String, Integer> f = new RichFunction5(new F5<String, String, String, String, String, Integer>() {
            public Integer apply(String v1, String v2, String v3, String v4, String v5) {
                return (v1 + v2 + v3 + v4 + v5).length();
            }
        });
        int result = f.curried().apply("a").apply("b").apply("c").apply("d").apply("e");
        assertThat(result, is(equalTo(5)));
    }

    @Test
    public void toString_A$() throws Exception {
        RichFunction5<String, String, String, String, String, Integer> f = new RichFunction5(new F5<String, String, String, String, String, Integer>() {
            public Integer apply(String v1, String v2, String v3, String v4, String v5) {
                return (v1 + v2 + v3 + v4 + v5).length();
            }
        });
        String actual = f.toString();
        String expected = "<function5>";
        assertThat(actual, is(equalTo(expected)));
    }

}
