package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class VoidFunction4Test {

    @Test
    public void type() throws Exception {
        assertThat(VoidFunction4.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        VoidF4<String, String, String, String> vf = new VoidF4<String, String, String, String>() {
            public void _(String v1, String v2, String v3, String v4) {
                System.out.println(v1 + v2 + v3 + v4);
            }
        };
        assertThat(vf, notNullValue());
    }

    @Test
    public void tupled_A$() throws Exception {
        VoidF4<String, String, String, String> vf = new VoidF4<String, String, String, String>() {
            public void _(String v1, String v2, String v3, String v4) {
                System.out.println(v1 + v2 + v3 + v4);
            }
        };
        VoidFunction1<Tuple4<String, String, String, String>> actual = vf.tupled();
        assertThat(actual, is(notNullValue()));
        actual._(Tuple4._("a", "b", "c", "d"));
    }

    @Test
    public void toString_A$() throws Exception {
        VoidF4<String, String, String, String> vf = new VoidF4<String, String, String, String>() {
            public void _(String v1, String v2, String v3, String v4) {
                System.out.println(v1 + v2 + v3 + v4);
            }
        };
        String actual = vf.toString();
        String expected = "<void-function4>";
        assertThat(actual, is(equalTo(expected)));
    }

}
