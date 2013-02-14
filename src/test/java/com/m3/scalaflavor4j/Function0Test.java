package com.m3.scalaflavor4j;

import org.junit.Test;

import java.util.concurrent.Future;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class Function0Test {

    @Test
    public void type() throws Exception {
        assertThat(Function0.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        Function0<String> target = new Function0<String>() {
            public String apply() {
                return "foo";
            }
        };
        assertThat(target, notNullValue());
    }

    @Test
    public void apply_A$() throws Exception {
        Function0<String> target = new Function0<String>() {
            public String apply() {
                return "foo";
            }
        };
        String actual = target.apply();
        Object expected = "foo";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void toString_A$() throws Exception {
        RichFunction0<String> target = new RichFunction0(new Function0<String>() {
            public String apply() {
                return "foo";
            }
        });
        String actual = target.toString();
        String expected = "<function0>";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void toJucFuture_A$() throws Exception {
        Function0<String> f0 = new Function0<String>() {
            public String apply() {
                return "foo";
            }
        };
        Future<String> f = new RichFunction0(f0).toJucFuture();
        assertThat(f.isCancelled(), is(false));
        assertThat(f.get(), is(equalTo("foo")));
    }

}
