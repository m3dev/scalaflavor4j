package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class SomeTest {

    @Test
    public void type() throws Exception {
        assertThat(Some.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        String value = "vvv";
        Some<String> target = new Some<String>(value);
        assertThat(target, notNullValue());
    }

    @Test
    public void getOrNull_A$() throws Exception {
        String value = "vvv";
        Some<String> target = new Some<String>(value);
        String actual = target.getOrNull();
        assertThat(actual, is(equalTo("vvv")));
    }

    @Test
    public void getOrNull_A$_null() throws Exception {
        String value = null;
        Some<String> target = new Some<String>(value);
        String actual = target.getOrNull();
        assertThat(actual, is(nullValue()));
    }

    @Test
    public void getOrElse_A$Object() throws Exception {
        String value = "vvv";
        Some<String> target = new Some<String>(value);
        String defaultValue = null;
        String actual = target.getOrElse(defaultValue);
        String expected = "vvv";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void isDefined_A$() throws Exception {
        String value = "vvv";
        Some<String> target = new Some<String>(value);
        boolean actual = target.isDefined();
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void isDefined_A$_false() throws Exception {
        String value = null;
        Some<String> target = new Some<String>(value);
        boolean actual = target.isDefined();
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void equals_A$Object() throws Exception {
        String value = "vvv";
        Some<String> target = new Some<String>(value);
        Object obj = new Some<String>(value);
        boolean actual = target.equals(obj);
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void equals_A$Object_false() throws Exception {
        String value = "vvv";
        Some<String> target = new Some<String>(value);
        Object obj = new Some<String>("bar");
        boolean actual = target.equals(obj);
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void toString_A$() throws Exception {
        String value = "vvv";
        Some<String> target = new Some<String>(value);
        String actual = target.toString();
        String expected = "Some(vvv)";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void hashCode_A$() throws Exception {
        String value = "vvv";
        Some<String> target = new Some<String>(value);
        int actual = target.hashCode();
        int expected = value.hashCode();
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void map_A$F1() throws Exception {
        String value = "vvv";
        Some<String> target = new Some<String>(value);
        F1<String, Integer> f = new F1<String, Integer>() {
            @Override
            public Integer apply(String v1) {
                return v1.length();
            }
        };
        Option<Integer> actual = target.map(f);
        assertThat(actual.getOrNull(), is(equalTo(3)));
    }

    @Test
    public void foreach_A$VoidFunction1() throws Exception {
        final Flag flag = new Flag();
        int value = 1;
        Some<Integer> target = new Some<Integer>(value);
        target.foreach(new VoidF1<Integer>() {
            public void apply(Integer arg) {
                flag.toggle();
            }
        });
        assertTrue(flag.getValue());
    }

    @Test
    public void filter_A$F1() throws Exception {
        String value = "vvv";
        Some<String> target = new Some<String>(value);
        F1<String, Boolean> isStartsWithv = new F1<String, Boolean>() {
            @Override
            public Boolean apply(String v1) {
                return v1.startsWith("v");
            }
        };
        F1<String, Boolean> isStartsWithw = new F1<String, Boolean>() {
            @Override
            public Boolean apply(String v1) {
                return v1.startsWith("w");
            }
        };
        Option<String> actual = target.filter(isStartsWithv);
        assertThat(actual.getOrNull(), is(equalTo("vvv")));

        Option<String> actual2 = target.filter(isStartsWithw);
        assertNull(actual2.getOrNull());
    }

    @Test
    public void flatMap_A$F1() throws Exception {
        String value = "vvv";
        Some<String> target = new Some<String>(value);
        F1<String, Option<Integer>> f = new F1<String, Option<Integer>>() {
            @Override
            public Option<Integer> apply(String v1) {
                return Option.apply(v1.length());
            }
        };
        Option<Integer> actual = target.flatMap(f);
        assertThat(actual.getOrNull(), is(equalTo(3)));
    }

    @Test
    public void toList_A$() throws Exception {
        String value = "vvv";
        Some<String> target = new Some<String>(value);
        List<String> actual = target.toList();
        assertThat(actual, is(notNullValue()));
        assertThat(actual.size(), is(equalTo(1)));
    }

    @Test
    public void isEmpty_A$() throws Exception {
        String value = "vvv";
        Some<String> target = new Some<String>(value);
        boolean actual = target.isEmpty();
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void map_A$Function1() throws Exception {
        String value = "vvv";
        Some<String> target = new Some<String>(value);
        F1<String, String> f = new F1<String, String>() {
            @Override
            public String apply(String v1) {
                return v1;
            }
        };
        Option<String> actual = target.map(f);
        assertThat(actual.getOrNull(), is(equalTo("vvv")));
    }

    @Test
    public void foreach_A$Function1() throws Exception {
        String value = "vvv";
        Some<String> target = new Some<String>(value);
        target.foreach(new VoidF1<String>() {
            public void apply(String v1) {
            }
        });
    }

    @Test
    public void filter_A$Function1() throws Exception {
        String value = "vvv";
        Some<String> target = new Some<String>(value);
        F1<String, Boolean> f = new F1<String, Boolean>() {
            @Override
            public Boolean apply(String v1) {
                return true;
            }
        };
        Option<String> actual = target.filter(f);
        assertThat(actual.isDefined(), is(true));
    }

    @Test
    public void flatMap_A$Function1() throws Exception {
        String value = "vvv";
        Some<String> target = new Some<String>(value);
        F1<String, Option<String>> f = new F1<String, Option<String>>() {
            @Override
            public Option<String> apply(String v1) {
                return Option.apply(v1);
            }
        };
        Option<String> actual = target.flatMap(f);
        assertThat(actual.isDefined(), is(true));
    }

    @Test
    public void fold_A$Object$Function1() throws Exception {
        String value = "vvv";
        Some<String> opt = new Some<String>(value);
        Integer ifEmpty = -1;
        Integer actual = opt.fold(ifEmpty, new F1<String, Integer>() {
            public Integer apply(String s) {
                return s.length();
            }
        });
        assertThat(actual, is(equalTo(3)));
    }

    @Test
    public void fold_A$Object() throws Exception {
        String value = "vvv";
        Some<String> opt = new Some<String>(value);
        Integer ifEmpty = -1;
        Integer actual = opt.fold(ifEmpty).apply(new F1<String, Integer>() {
            public Integer apply(String s) {
                return s.length();
            }
        });
        assertThat(actual, is(equalTo(3)));
    }

}
