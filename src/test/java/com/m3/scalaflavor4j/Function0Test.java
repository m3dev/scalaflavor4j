package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.concurrent.Future;

import org.junit.Test;

public class Function0Test {

    @Test
    public void type() throws Exception {
        assertThat(Function0.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        Function0<String> target = new Function0<String>() {
            public String _() {
                return "foo";
            }
        };
        assertThat(target, notNullValue());
    }

    @Test
    public void apply_A$() throws Exception {
        Function0<String> target = new Function0<String>() {
            public String _() {
                return "foo";
            }
        };
        String actual = target.apply();
        Object expected = "foo";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void toString_A$() throws Exception {
        Function0<String> target = new Function0<String>() {
            public String _() {
                return "foo";
            }
        };
        String actual = target.toString();
        String expected = "<function0>";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void toJucFuture_A$() throws Exception {
        Function0<String> f0 = new Function0<String>() {
            public String _() {
                return "foo";
            }
        };
        Future<String> f = f0.toJucFuture();
        assertThat(f.isCancelled(), is(false));
        assertThat(f.get(), is(equalTo("foo")));
    }

}
