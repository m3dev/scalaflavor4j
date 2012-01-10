package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ParSeqTest {

    @Test
    public void type() throws Exception {
        assertThat(ForkJoinParSeq.class, notNullValue());
    }

    @Test
    public void apply_A$ObjectArray() throws Exception {
        ParSeq<String> actual = ParSeq.apply("foo", "bar");
        assertThat(actual, notNullValue());
    }

    @Test
    public void __A$ObjectArray() throws Exception {
        ParSeq<String> actual = ParSeq.apply("foo", "bar");
        assertThat(actual, notNullValue());
    }

}
