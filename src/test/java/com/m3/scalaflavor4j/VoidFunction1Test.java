package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class VoidFunction1Test {

    @Test
    public void type() throws Exception {
        assertThat(VoidFunction1.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        VoidFunction1<String> vf = new VoidFunction1<String>() {
            public void _(String v1) {
                System.out.println(v1 + v1);
            }
        };
        assertThat(vf, notNullValue());
        vf._("foo");
    }

    @Test
    public void toString_A$() throws Exception {
        VoidF1<String> vf = new VoidF1<String>() {
            public void _(String v1) {
            }
        };
        String actual = vf.toString();
        String expected = "<void-function1>";
        assertThat(actual, is(equalTo(expected)));
    }

}
