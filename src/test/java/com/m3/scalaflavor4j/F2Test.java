package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class F2Test {

    @Test
    public void type() throws Exception {
        assertThat(F2.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        F2<String, Integer, Long> target = new F2<String, Integer, Long>() {
            public Long apply(String v1, Integer v2) {
                return 1L;
            }
        };
        assertThat(target, notNullValue());
    }

}
