package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class OptionTest {

    @Test
    public void type() throws Exception {
        assertThat(Option.class, notNullValue());
    }

    @Test
    public void apply_A$Object() throws Exception {
        String value = "askdoctors";
        Option<String> actual = Option._(value);
        Object expected = "askdoctors";
        assertThat(actual.isDefined(), is(true));
        assertThat(actual.getOrNull(), is(equalTo(expected)));
    }

    @Test
    public void none_A$() throws Exception {
        Option<String> actual = Option.none();
        assertThat(actual.isDefined(), is(false));
        assertThat(actual.getOrNull(), is(nullValue()));
    }

}
