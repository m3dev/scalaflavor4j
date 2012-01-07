package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class NoneTest {

    @Test
    public void type() throws Exception {
        assertThat(None.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        None<Object> none = new None<Object>();
        assertThat(none, notNullValue());
    }

    @Test
    public void getOrNull_A$() throws Exception {
        None<Object> none = new None<Object>();
        Object actual = none.getOrNull();
        Object expected = null;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void getOrElse_A$Object() throws Exception {
        None<Object> none = new None<Object>();
        Object defaultValue = null;
        Object actual = none.getOrElse(defaultValue);
        Object expected = null;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void isDefined_A$() throws Exception {
        None<Object> none = new None<Object>();
        boolean actual = none.isDefined();
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void equals_A$Object() throws Exception {
        None<Object> none = new None<Object>();
        Object obj = null;
        boolean actual = none.equals(obj);
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void toString_A$() throws Exception {
        None<Object> none = new None<Object>();
        String actual = none.toString();
        String expected = "None";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void hashCode_A$() throws Exception {
        None<Object> none = new None<Object>();
        int actual = none.hashCode();
        int expected = 0;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void map_A$Function1() throws Exception {
        None<Integer> target = new None<Integer>();
        F1<Integer, Integer> f = new F1<Integer, Integer>() {
            @Override
            public Integer _(Integer arg) {
                return arg * 2;
            }
        };
        Option<Integer> actual = target.map(f);
        assertNull(actual.getOrNull());
    }

    @Test
    public void foreach_A$VoidFunction1() throws Exception {
        final Flag flag = new Flag();
        None<Integer> target = new None<Integer>();
        target.foreach(new VoidF1<Integer>() {
            public void _(Integer arg) {
                flag.toggle();
            }
        });
        assertFalse(flag.getValue());
    }

    @Test
    public void filter_A$Function1() throws Exception {
        None<String> target = new None<String>();
        F1<String, Boolean> isStartsWithv = new F1<String, Boolean>() {
            @Override
            public Boolean _(String v1) {
                return v1.startsWith("v");
            }
        };
        Option<String> actual = target.filter(isStartsWithv);
        assertNull(actual.getOrNull());
    }

    @Test
    public void flatMap_A$Function1() throws Exception {
        None<String> target = new None<String>();
        F1<String, Option<Integer>> f = new F1<String, Option<Integer>>() {
            @Override
            public Option<Integer> _(String v1) {
                return Option._(v1.length());
            }
        };
        Option<Integer> actual = target.flatMap(f);
        assertNull(actual.getOrNull());
    }

    @Test
    public void toList_A$() throws Exception {
        None<String> target = new None<String>();
        List<String> actual = target.toList();
        assertThat(actual, is(notNullValue()));
    }

    @Test
    public void isEmpty_A$() throws Exception {
        None<String> target = new None<String>();
        boolean actual = target.isEmpty();
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

}
