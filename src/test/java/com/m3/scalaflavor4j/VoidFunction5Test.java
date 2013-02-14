package com.m3.scalaflavor4j;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class VoidFunction5Test {

    @Test
    public void type() throws Exception {
        assertThat(VoidFunction5.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        VoidF5<String, String, String, String, String> vf = new VoidF5<String, String, String, String, String>() {
            public void apply(String v1, String v2, String v3, String v4, String v5) {
                System.out.println(v1 + v2 + v3 + v4 + v5);
            }
        };
        assertThat(vf, notNullValue());
    }

    @Test
    public void tupled_A$() throws Exception {
        RichVoidFunction5<String, String, String, String, String> vf = new RichVoidFunction5(new VoidF5<String, String, String, String, String>() {
            public void apply(String v1, String v2, String v3, String v4, String v5) {
                System.out.println(v1 + v2 + v3 + v4 + v5);
            }
        });
        VoidFunction1<Tuple5<String, String, String, String, String>> actual = vf.tupled();
        assertThat(actual, is(notNullValue()));
        actual.apply(Tuple5.apply("a", "b", "c", "d", "e"));
    }

    @Test
    public void toString_A$() throws Exception {
        RichVoidFunction5<String, String, String, String, String> vf = new RichVoidFunction5(new VoidF5<String, String, String, String, String>() {
            public void apply(String v1, String v2, String v3, String v4, String v5) {
                System.out.println(v1 + v2 + v3 + v4 + v5);
            }
        });
        String actual = vf.toString();
        String expected = "<void-function5>";
        assertThat(actual, is(equalTo(expected)));
    }

}
