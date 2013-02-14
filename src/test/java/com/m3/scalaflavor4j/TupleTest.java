package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class TupleTest {

    @Test
    public void type() throws Exception {
        assertThat(Tuple.class, notNullValue());
    }

    @Test
    public void apply_A$Object$Object() throws Exception {
        String _1 = "scala";
        Integer _2 = 291;
        Tuple2<String, Integer> tpl = Tuple.apply(_1, _2);
        assertThat(tpl._1(), is(equalTo(_1)));
        assertThat(tpl._2(), is(equalTo(_2)));
    }

    @Test
    public void __A$Object$Object() throws Exception {
        String _1 = "scala";
        Integer _2 = 291;
        Tuple2<String, Integer> tpl = Tuple.apply(_1, _2);
        assertThat(tpl._1(), is(equalTo(_1)));
        assertThat(tpl._2(), is(equalTo(_2)));
    }

    @Test
    public void apply_A$Object$Object$Object() throws Exception {
        String _1 = "scala";
        Integer _2 = 291;
        Long _3 = 1L;
        Tuple3<String, Integer, Long> tpl = Tuple.apply(_1, _2, _3);
        assertThat(tpl._1(), is(equalTo(_1)));
        assertThat(tpl._2(), is(equalTo(_2)));
        assertThat(tpl._3(), is(equalTo(_3)));
    }

    @Test
    public void __A$Object$Object$Object() throws Exception {
        String _1 = "scala";
        Integer _2 = 291;
        Long _3 = 1L;
        Tuple3<String, Integer, Long> tpl = Tuple.apply(_1, _2, _3);
        assertThat(tpl._1(), is(equalTo(_1)));
        assertThat(tpl._2(), is(equalTo(_2)));
        assertThat(tpl._3(), is(equalTo(_3)));
    }

    @Test
    public void apply_A$Object$Object$Object$Object() throws Exception {
        String _1 = "scala";
        Integer _2 = 291;
        Long _3 = 1L;
        Double _4 = 0.1D;
        Tuple4<String, Integer, Long, Double> tpl = Tuple.apply(_1, _2, _3, _4);
        assertThat(tpl._1(), is(equalTo(_1)));
        assertThat(tpl._2(), is(equalTo(_2)));
        assertThat(tpl._3(), is(equalTo(_3)));
        assertThat(tpl._4(), is(equalTo(_4)));
    }

    @Test
    public void __A$Object$Object$Object$Object() throws Exception {
        String _1 = "scala";
        Integer _2 = 291;
        Long _3 = 1L;
        Double _4 = 0.1D;
        Tuple4<String, Integer, Long, Double> tpl = Tuple.apply(_1, _2, _3, _4);
        assertThat(tpl._1(), is(equalTo(_1)));
        assertThat(tpl._2(), is(equalTo(_2)));
        assertThat(tpl._3(), is(equalTo(_3)));
        assertThat(tpl._4(), is(equalTo(_4)));
    }

    @Test
    public void apply_A$Object$Object$Object$Object$Object() throws Exception {
        String _1 = "scala";
        Integer _2 = 291;
        Long _3 = 1L;
        Double _4 = 0.1D;
        Boolean _5 = false;
        Tuple5<String, Integer, Long, Double, Boolean> tpl = Tuple.apply(_1, _2, _3, _4, _5);
        assertThat(tpl._1(), is(equalTo(_1)));
        assertThat(tpl._2(), is(equalTo(_2)));
        assertThat(tpl._3(), is(equalTo(_3)));
        assertThat(tpl._4(), is(equalTo(_4)));
        assertThat(tpl._5(), is(equalTo(_5)));
    }

    @Test
    public void __A$Object$Object$Object$Object$Object() throws Exception {
        String _1 = "scala";
        Integer _2 = 291;
        Long _3 = 1L;
        Double _4 = 0.1D;
        Boolean _5 = false;
        Tuple5<String, Integer, Long, Double, Boolean> tpl = Tuple.apply(_1, _2, _3, _4, _5);
        assertThat(tpl._1(), is(equalTo(_1)));
        assertThat(tpl._2(), is(equalTo(_2)));
        assertThat(tpl._3(), is(equalTo(_3)));
        assertThat(tpl._4(), is(equalTo(_4)));
        assertThat(tpl._5(), is(equalTo(_5)));
    }

}
