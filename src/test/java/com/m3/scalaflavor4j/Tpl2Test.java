package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class Tpl2Test {

    @Test
    public void type() throws Exception {
        assertThat(Tpl2.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        String _1 = "abc";
        Integer _2 = 123;
        Tpl2<String, Integer> tpl = new Tpl2<String, Integer>(_1, _2);
        assertThat(tpl._1(), is(equalTo(_1)));
        assertThat(tpl._2(), is(equalTo(_2)));
    }

    @Test
    public void apply_A$Object$Object() throws Exception {
        String _1 = "abc";
        Integer _2 = 123;
        Tpl2<String, Integer> tpl = Tpl2.apply(_1, _2);
        assertThat(tpl._1(), is(equalTo(_1)));
        assertThat(tpl._2(), is(equalTo(_2)));
    }

    @Test
    public void __A$Object$Object() throws Exception {
        String _1 = "abc";
        Integer _2 = 123;
        Tpl2<String, Integer> tpl = Tpl2._(_1, _2);
        assertThat(tpl._1(), is(equalTo(_1)));
        assertThat(tpl._2(), is(equalTo(_2)));
    }

    @Test
    public void unapply_A$Tpl2() throws Exception {
        Tpl2<String, Integer> tuple = Tpl._("a", 1);
        Option<Tpl2<String, Integer>> opt = Tpl2.unapply(tuple);
        assertThat(opt.isDefined(), is(true));
    }

}
