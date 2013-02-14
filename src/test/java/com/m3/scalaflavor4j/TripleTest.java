package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class TripleTest {

    @Test
    public void type() throws Exception {
        assertThat(Triple.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        Triple<String, Integer, Long> target = new Triple<String, Integer, Long>("1", 2, 3L);
        assertThat(target, notNullValue());
    }

    @Test
    public void apply_A$Object$Object$Object() throws Exception {
        Triple<String, Integer, Long> target = Triple.apply("1", 2, 3L);
        assertThat(target, notNullValue());
    }

    @Test
    public void __A$Object$Object$Object() throws Exception {
        Triple<String, Integer, Long> target = Triple.apply("1", 2, 3L);
        assertThat(target, notNullValue());
    }

    @Test
    public void unapply_A$Tuple3() throws Exception {
        Triple<String, Integer, Long> tuple = Triple.apply("1", 2, 3L);
        Option<Tuple3<String, Integer, Long>> actual = Triple.unapply(tuple);
        assertThat(actual.isDefined(), is(true));
    }

}
