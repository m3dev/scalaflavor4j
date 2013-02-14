package com.m3.scalaflavor4j;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class Function3Test {

    @Test
    public void type() throws Exception {
        assertThat(Function3.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        Function3<String, String, String, Integer> f = new F3<String, String, String, Integer>() {
            public Integer apply(String v1, String v2, String v3) {
                return (v1 + v2 + v3).length();
            }
        };
        assertThat(f, notNullValue());
    }

    @Test
    public void tupled_A$() throws Exception {
        Function3<String, String, String, Integer> f = new F3<String, String, String, Integer>() {
            public Integer apply(String v1, String v2, String v3) {
                return (v1 + v2 + v3).length();
            }
        };
        Function1<Tuple3<String, String, String>, Integer> f2 = new RichFunction3(f).tupled();
        Integer result = f2.apply(Tuple.apply("a", "bc", "def"));
        assertThat(result, is(equalTo(6)));
    }

    @Test
    public void curried_A$() throws Exception {
        RichFunction3<String, String, String, Integer> f = new RichFunction3(new F3<String, String, String, Integer>() {
            public Integer apply(String v1, String v2, String v3) {
                return (v1 + v2 + v3).length();
            }
        });
        int result = f.curried().apply("a").apply("bc").apply("def");
        assertThat(result, is(equalTo(6)));
    }

    @Test
    public void toString_A$() throws Exception {
        RichFunction3<String, String, String, Integer> f = new RichFunction3(new F3<String, String, String, Integer>() {
            public Integer apply(String v1, String v2, String v3) {
                return (v1 + v2 + v3).length();
            }
        });
        String actual = f.toString();
        String expected = "<function3>";
        assertThat(actual, is(equalTo(expected)));
    }

}
