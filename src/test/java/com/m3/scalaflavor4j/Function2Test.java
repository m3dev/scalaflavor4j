package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class Function2Test {

    @Test
    public void type() throws Exception {
        assertThat(Function2.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        Function2<String, Integer, Boolean> f = new F2<String, Integer, Boolean>() {
            public Boolean apply(String v1, Integer v2) {
                return v1.length() == v2;
            }
        };
        assertThat(f, notNullValue());
    }

    @Test
    public void tupled_A$() throws Exception {
        F2<String, Integer, Boolean> f = new F2<String, Integer, Boolean>() {
            public Boolean apply(String v1, Integer v2) {
                return v1.length() == v2;
            }
        };
        Function1<Tuple2<String, Integer>, Boolean> f2 = new RichFunction2(f).tupled();
        boolean result = f2.apply(Tuple.apply("foo", 3));
        assertThat(result, is(true));
    }

    @Test
    public void toString_A$() throws Exception {
        RichFunction2<String, Integer, Boolean> f = new RichFunction2(new F2<String, Integer, Boolean>() {
            public Boolean apply(String v1, Integer v2) {
                return v1.length() == v2;
            }
        });
        String actual = f.toString();
        String expected = "<function2>";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void curried_A$() throws Exception {
        RichFunction2<String, Integer, Boolean> f = new RichFunction2(new F2<String, Integer, Boolean>() {
            public Boolean apply(String v1, Integer v2) {
                return v1.length() == v2;
            }
        });
        Function1<String, Function1<Integer, Boolean>> curried = new RichFunction2(f).curried();
        boolean result = curried.apply("foo").apply(3);
        assertThat(result, is(true));
    }

}
