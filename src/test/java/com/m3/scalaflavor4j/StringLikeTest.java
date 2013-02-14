package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

public class StringLikeTest {

    @Test
    public void type() throws Exception {
        assertThat(StringLike.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        Collection<Character> list = Arrays.asList('a', 'b', 'c');
        StringLike sl = new StringLike(list);
        assertThat(sl, notNullValue());
    }

    @Test
    public void apply_A$String() throws Exception {
        String str = "abcdef";
        StringLike sl = StringLike.apply(str);
        assertThat(sl.size(), is(equalTo(6)));
        assertThat(sl.head(), is(equalTo('a')));
    }

    @Test
    public void __A$String_Null() throws Exception {
        StringLike sl = StringLike.apply((String) null);
        assertThat(sl.size(), is(equalTo(0)));
        assertThat(sl.headOption().isDefined(), is(false));
    }

    @Test
    public void __A$String_Empty() throws Exception {
        StringLike sl = StringLike.apply("");
        assertThat(sl.size(), is(equalTo(0)));
        assertThat(sl.headOption().isDefined(), is(false));
    }

    @Test
    public void __A$String() throws Exception {
        String str = "あかさたな";
        StringLike sl = StringLike.apply(str);
        assertThat(sl.size(), is(equalTo(5)));
        assertThat(sl.head(), is(equalTo('あ')));
    }

    @Test
    public void toString_A$() throws Exception {
        Collection<Character> list = Arrays.asList('a', 'b', 'c', '１');
        StringLike sl = new StringLike(list);
        String actual = sl.toString();
        String expected = "abc１";
        assertThat(actual, is(equalTo(expected)));
    }

}
