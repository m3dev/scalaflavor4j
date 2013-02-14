package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class StrTest {

    @Test
    public void type() throws Exception {
        assertThat(Str.class, notNullValue());
    }

    @Test
    public void apply_A$String() throws Exception {
        String str = "abc";
        Seq<Character> cs = Str.apply(str);
        assertThat(cs.size(), is(equalTo(3)));
        assertThat(cs.head(), is(equalTo('a')));
    }

    @Test
    public void apply_A$String_2() throws Exception {
        String str = "日本語１２３abc";
        Seq<Character> cs = Str.apply(str);
        assertThat(cs.size(), is(equalTo(9)));
        assertThat(cs.head(), is(equalTo('日')));
    }

}
