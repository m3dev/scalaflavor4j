package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class Function4Test {

    @Test
    public void type() throws Exception {
        assertThat(Function4.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        Function4<String, String, String, String, Integer> target = new F4<String, String, String, String, Integer>() {
            public Integer _(String v1, String v2, String v3, String v4) {
                return null;
            }
        };
        assertThat(target, notNullValue());
    }

    @Test
    public void tupled_A$() throws Exception {
        Function4<String, String, String, String, Integer> f = new F4<String, String, String, String, Integer>() {
            public Integer _(String v1, String v2, String v3, String v4) {
                return (v1 + v2 + v3 + v4).length();
            }
        };
        Function1<Tuple4<String, String, String, String>, Integer> f2 = f.tupled();
        int result = f2._(Tuple4._("a", "bc", "def", "gihj"));
        assertThat(result, is(equalTo(10)));
    }

    @Test
    public void curried_A$() throws Exception {
        Function4<String, String, String, String, Integer> f = new F4<String, String, String, String, Integer>() {
            public Integer _(String v1, String v2, String v3, String v4) {
                return (v1 + v2 + v3 + v4).length();
            }
        };
        int result = f.curried()._("a")._("bc")._("def")._("gihj");
        assertThat(result, is(equalTo(10)));
    }

    @Test
    public void toString_A$() throws Exception {
        Function4<String, String, String, String, Integer> f = new F4<String, String, String, String, Integer>() {
            public Integer _(String v1, String v2, String v3, String v4) {
                return (v1 + v2 + v3 + v4).length();
            }
        };
        String actual = f.toString();
        String expected = "<function4>";
        assertThat(actual, is(equalTo(expected)));
    }

}
