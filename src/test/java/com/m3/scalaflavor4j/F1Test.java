package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class F1Test {

    @Test
    public void type() throws Exception {
        assertThat(F1.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        F1<String, Integer> f = new F1<String, Integer>() {
            public Integer _(String v1) {
                return v1.length();
            }
        };
        assertThat(f, notNullValue());
    }

}
