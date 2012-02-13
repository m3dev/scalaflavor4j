package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class Tpl4Test {

    @Test
    public void type() throws Exception {
        assertThat(Tpl4.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        String _1 = "abc";
        Integer _2 = 123;
        Long _3 = 1L;
        Double _4 = 0.1D;
        Tpl4<String, Integer, Long, Double> tpl = new Tpl4<String, Integer, Long, Double>(_1, _2, _3, _4);
        assertThat(tpl._1(), is(equalTo(_1)));
        assertThat(tpl._2(), is(equalTo(_2)));
        assertThat(tpl._3(), is(equalTo(_3)));
        assertThat(tpl._4(), is(equalTo(_4)));
    }

    @Test
    public void apply_A$Object$Object$Object$Object() throws Exception {
        String _1 = "abc";
        Integer _2 = 123;
        Long _3 = 1L;
        Double _4 = 0.1D;
        Tpl4<String, Integer, Long, Double> tpl = Tpl4.apply(_1, _2, _3, _4);
        assertThat(tpl._1(), is(equalTo(_1)));
        assertThat(tpl._2(), is(equalTo(_2)));
        assertThat(tpl._3(), is(equalTo(_3)));
        assertThat(tpl._4(), is(equalTo(_4)));
    }

    @Test
    public void __A$Object$Object$Object$Object() throws Exception {
        String _1 = "abc";
        Integer _2 = 123;
        Long _3 = 1L;
        Double _4 = 0.1D;
        Tpl4<String, Integer, Long, Double> tpl = Tpl4._(_1, _2, _3, _4);
        assertThat(tpl._1(), is(equalTo(_1)));
        assertThat(tpl._2(), is(equalTo(_2)));
        assertThat(tpl._3(), is(equalTo(_3)));
        assertThat(tpl._4(), is(equalTo(_4)));
    }

    @Test
    public void unapply_A$Tpl4() throws Exception {
        Tpl4<String, Integer, Long, Double> tuple = Tpl4._("a", 1, 2L, 3.0D);
        Option<Tpl4<String, Integer, Long, Double>> actual = Tpl4.unapply(tuple);
        assertThat(actual.isDefined(), is(true));
    }

}
