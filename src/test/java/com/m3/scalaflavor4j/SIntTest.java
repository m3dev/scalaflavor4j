package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class SIntTest {

    @Test
    public void type() throws Exception {
        assertThat(SInt.class, notNullValue());
    }

    @Test
    public void apply_A$Integer() throws Exception {
        SInt actual = SInt.apply(123);
        assertThat(actual.getOrElse(0), is(equalTo(123)));
    }

    @Test
    public void __A$Integer() throws Exception {
        SInt actual = SInt._(123);
        assertThat(actual.getOrElse(0), is(equalTo(123)));
    }

    @Test
    public void to_A$Integer() throws Exception {
        Seq<Integer> oneToThree = SInt._(1).to(3);
        assertThat(oneToThree.size(), is(equalTo(3)));
    }

    @Test
    public void to_A$Integer$Integer() throws Exception {
        Seq<Integer> seq = SInt._(1).to(10, 3);
        assertThat(seq.size(), is(equalTo(4)));
    }

    @Test
    public void until_A$Integer() throws Exception {
        Seq<Integer> oneUntilThree = SInt._(1).until(3);
        assertThat(oneUntilThree.size(), is(equalTo(2)));
    }

    @Test
    public void until_A$Integer$Integer() throws Exception {
        Seq<Integer> seq = SInt._(1).until(10, 3);
        assertThat(seq.size(), is(equalTo(3)));
    }

    @Test
    public void getOrElse_A$Integer() throws Exception {
        Integer actual = SInt._(123).getOrElse(0);
        Integer expected = 123;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void toString_A$() throws Exception {
        SInt target = SInt._(123);
        String actual = target.toString();
        String expected = "SInt(123)";
        assertThat(actual, is(equalTo(expected)));
    }

}
