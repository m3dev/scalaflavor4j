package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class Tpl5Test {

    @Test
    public void type() throws Exception {
        assertThat(Tpl5.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        String _1 = "abc";
        Integer _2 = 123;
        Long _3 = 1L;
        Double _4 = 0.1D;
        Boolean _5 = false;
        Tpl5<String, Integer, Long, Double, Boolean> tpl = new Tpl5<String, Integer, Long, Double, Boolean>(_1, _2, _3,
                _4, _5);
        assertThat(tpl._1(), is(equalTo(_1)));
        assertThat(tpl._2(), is(equalTo(_2)));
        assertThat(tpl._3(), is(equalTo(_3)));
        assertThat(tpl._4(), is(equalTo(_4)));
        assertThat(tpl._5(), is(equalTo(_5)));
    }

    @Test
    public void apply_A$Object$Object$Object$Object$Object() throws Exception {
        String _1 = "abc";
        Integer _2 = 123;
        Long _3 = 1L;
        Double _4 = 0.1D;
        Boolean _5 = false;
        Tpl5<String, Integer, Long, Double, Boolean> tpl = Tpl5.apply(_1, _2, _3, _4, _5);
        assertThat(tpl._1(), is(equalTo(_1)));
        assertThat(tpl._2(), is(equalTo(_2)));
        assertThat(tpl._3(), is(equalTo(_3)));
        assertThat(tpl._4(), is(equalTo(_4)));
        assertThat(tpl._5(), is(equalTo(_5)));
    }

    @Test
    public void __A$Object$Object$Object$Object$Object() throws Exception {
        String _1 = "abc";
        Integer _2 = 123;
        Long _3 = 1L;
        Double _4 = 0.1D;
        Boolean _5 = false;
        Tpl5<String, Integer, Long, Double, Boolean> tpl = Tpl5.apply(_1, _2, _3, _4, _5);
        assertThat(tpl._1(), is(equalTo(_1)));
        assertThat(tpl._2(), is(equalTo(_2)));
        assertThat(tpl._3(), is(equalTo(_3)));
        assertThat(tpl._4(), is(equalTo(_4)));
        assertThat(tpl._5(), is(equalTo(_5)));
    }

    @Test
    public void unapply_A$Tpl5() throws Exception {
        String _1 = "foo";
        Integer _2 = 123;
        Long _3 = 456L;
        Boolean _4 = true;
        Double _5 = 7.8D;
        Tpl5<String, Integer, Long, Boolean, Double> tuple = Tpl5.apply(_1, _2, _3, _4, _5);
        Option<Tpl5<String, Integer, Long, Boolean, Double>> actual = Tpl5.unapply(tuple);
        assertThat(actual.isDefined(), is(true));
    }

}
