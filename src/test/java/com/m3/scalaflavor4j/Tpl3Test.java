package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class Tpl3Test {

    @Test
    public void type() throws Exception {
        assertThat(Tpl3.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        String _1 = "abc";
        Integer _2 = 123;
        Long _3 = 1L;
        Tpl3<String, Integer, Long> tpl = new Tpl3<String, Integer, Long>(_1, _2, _3);
        assertThat(tpl._1(), is(equalTo(_1)));
        assertThat(tpl._2(), is(equalTo(_2)));
        assertThat(tpl._3(), is(equalTo(_3)));
    }

    @Test
    public void apply_A$Object$Object$Object() throws Exception {
        String _1 = "abc";
        Integer _2 = 123;
        Long _3 = 1L;
        Tpl3<String, Integer, Long> tpl = Tpl3.apply(_1, _2, _3);
        assertThat(tpl._1(), is(equalTo(_1)));
        assertThat(tpl._2(), is(equalTo(_2)));
        assertThat(tpl._3(), is(equalTo(_3)));
    }

    @Test
    public void __A$Object$Object$Object() throws Exception {
        String _1 = "abc";
        Integer _2 = 123;
        Long _3 = 1L;
        Tpl3<String, Integer, Long> tpl = Tpl3._(_1, _2, _3);
        assertThat(tpl._1(), is(equalTo(_1)));
        assertThat(tpl._2(), is(equalTo(_2)));
        assertThat(tpl._3(), is(equalTo(_3)));
    }

    @Test
    public void unapply_A$Tpl3() throws Exception {
        Tpl3<String, Integer, Long> tuple = Tpl3._("a", 1, 2L);
        Option<Tpl3<String, Integer, Long>> opt = Tpl3.unapply(tuple);
        assertThat(opt.isDefined(), is(true));
    }

}
