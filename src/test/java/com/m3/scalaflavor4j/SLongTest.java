package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class SLongTest {

    @Test
    public void type() throws Exception {
        assertThat(SLong.class, notNullValue());
    }

    @Test
    public void apply_A$Long() throws Exception {
        SLong actual = SLong.apply(123L);
        assertThat(actual.getOrElse(0L), is(equalTo(123L)));
    }

    @Test
    public void __A$Long() throws Exception {
        SLong actual = SLong._(123L);
        assertThat(actual.getOrElse(0L), is(equalTo(123L)));
    }

    @Test
    public void getOrElse_A$Long() throws Exception {
        Long actual = SLong._(123L).getOrElse(0L);
        assertThat(actual, is(equalTo(123L)));
    }

    @Test
    public void to_A$Long() throws Exception {
        Seq<Long> actual = SLong._(1L).to(3L);
        assertThat(actual.size(), is(equalTo(3)));
    }

    @Test
    public void to_A$Long$Long() throws Exception {
        Seq<Long> actual = SLong._(1L).to(10L, 3L);
        assertThat(actual.size(), is(equalTo(4)));
    }

    @Test
    public void until_A$Long() throws Exception {
        Seq<Long> actual = SLong._(1L).until(3L);
        assertThat(actual.size(), is(equalTo(2)));
    }

    @Test
    public void until_A$Long$Long() throws Exception {
        Seq<Long> actual = SLong._(1L).until(10L, 3L);
        assertThat(actual.size(), is(equalTo(3)));
    }

    @Test
    public void toString_A$() throws Exception {
        SLong target = SLong._(123L);
        String actual = target.toString();
        String expected = "SLong(123)";
        assertThat(actual, is(equalTo(expected)));
    }

}
