package com.m3.scalaflavor4j;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class VoidFunction3Test {

    @Test
    public void type() throws Exception {
        assertThat(VoidFunction3.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        VoidFunction3<String, String, String> vf = new VoidFunction3<String, String, String>() {
            public void apply(String v1, String v2, String v3) {
                System.out.println(v1 + v2 + v3);
            }
        };
        assertThat(vf, notNullValue());
    }

    @Test
    public void tupled_A$() throws Exception {
        RichVoidFunction3<String, String, String> vf = new RichVoidFunction3(new VoidFunction3<String, String, String>() {
            public void apply(String v1, String v2, String v3) {
                System.out.println(v1 + v2 + v3);
            }
        });
        VoidFunction1<Tuple3<String, String, String>> actual = vf.tupled();
        assertThat(actual, is(notNullValue()));
        actual.apply(Tuple3.apply("a", "b", "c"));
    }

    @Test
    public void toString_A$() throws Exception {
        RichVoidFunction3<String, String, String> vf = new RichVoidFunction3(new VoidFunction3<String, String, String>() {
            public void apply(String v1, String v2, String v3) {
                System.out.println(v1 + v2 + v3);
            }
        });
        String actual = vf.toString();
        String expected = "<void-function3>";
        assertThat(actual, is(equalTo(expected)));
    }

}
