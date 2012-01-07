package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class Tuple4Test {

    @Test
    public void type() throws Exception {
        assertThat(Tuple4.class, notNullValue());
    }

    @Test
    public void _1_A$() throws Exception {
        String _1 = "foo";
        Integer _2 = 123;
        Long _3 = 456L;
        Boolean _4 = false;
        Tuple4<String, Integer, Long, Boolean> target = Tuple4.apply(_1, _2, _3, _4);
        String actual = target._1();
        String expected = "foo";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void _2_A$() throws Exception {
        String _1 = "foo";
        Integer _2 = 123;
        Long _3 = 456L;
        Boolean _4 = false;
        Tuple4<String, Integer, Long, Boolean> target = Tuple4.apply(_1, _2, _3, _4);
        Integer actual = target._2();
        Integer expected = 123;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void _3_A$() throws Exception {
        String _1 = "foo";
        Integer _2 = 123;
        Long _3 = 456L;
        Boolean _4 = false;
        Tuple4<String, Integer, Long, Boolean> target = Tuple4.apply(_1, _2, _3, _4);
        Long actual = target._3();
        Long expected = 456L;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void _4_A$() throws Exception {
        String _1 = "foo";
        Integer _2 = 123;
        Long _3 = 456L;
        Boolean _4 = true;
        Tuple4<String, Integer, Long, Boolean> target = Tuple4.apply(_1, _2, _3, _4);
        Boolean actual = target._4();
        Boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void apply_A$Object$Object$Object$Object() throws Exception {
        Tuple4<String, Integer, Long, Double> actual = Tuple4.apply("a", 1, 2L, 3.0D);
        assertThat(actual, is(notNullValue()));
    }

    @Test
    public void __A$Object$Object$Object$Object() throws Exception {
        Tuple4<String, Integer, Long, Double> actual = Tuple4._("a", 1, 2L, 3.0D);
        assertThat(actual, is(notNullValue()));
    }

    @Test
    public void unapply_A$Tuple4() throws Exception {
        Tuple4<String, Integer, Long, Double> tuple = Tuple4._("a", 1, 2L, 3.0D);
        Option<Tuple4<String, Integer, Long, Double>> actual = Tuple4.unapply(tuple);
        assertThat(actual.isDefined(), is(true));
    }

    @Test
    public void toString_A$() throws Exception {
        String actual = Tuple4._(1, 2, 3, 4).toString();
        String expected = "(1,2,3,4)";
        assertThat(actual, is(equalTo(expected)));
    }

}
