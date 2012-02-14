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

    @Test
    public void empty_A$() throws Exception {
        Option<String> actual = Option.empty();
        assertThat(actual.isDefined(), is(false));
        assertThat(actual.getOrNull(), is(nullValue()));
    }

    @Test
    public void getOrElse_A$Function0_defined() throws Exception {
        int result = Option._("foo").map(new F1<String, Integer>() {
            public Integer _(String str) {
                return str.length();
            }
        }).getOrElse(new F0<Integer>() {
            public Integer _() {
                return 0;
            }
        });
        assertThat(result, is(equalTo(3)));
    }

    @Test
    public void getOrElse_A$Function0_undefined() throws Exception {
        int result = Option.<String> _(null).map(new F1<String, Integer>() {
            public Integer _(String str) {
                return str.length();
            }
        }).getOrElse(new F0<Integer>() {
            public Integer _() {
                return 0;
            }
        });
        assertThat(result, is(equalTo(0)));
    }

}
