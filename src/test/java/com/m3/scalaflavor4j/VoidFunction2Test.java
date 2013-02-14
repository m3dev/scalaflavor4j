package com.m3.scalaflavor4j;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class VoidFunction2Test {

    @Test
    public void type() throws Exception {
        assertThat(VoidFunction2.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        VoidFunction2<String, Integer> vf = new VoidFunction2<String, Integer>() {
            public void apply(String v1, Integer v2) {
                System.out.println(v1 + ":" + v2);
            }
        };
        assertThat(vf, notNullValue());
    }

    @Test
    public void tupled_A$() throws Exception {
        RichVoidFunction2<String, Integer> vf = new RichVoidFunction2(new VoidF2<String, Integer>() {
            public void apply(String v1, Integer v2) {
                System.out.println(v1 + ":" + v2);
            }
        });
        VoidFunction1<Tuple2<String, Integer>> actual = vf.tupled();
        assertThat(actual, is(notNullValue()));
        actual.apply(Tuple2.apply("aaa", 123));
    }

    @Test
    public void toString_A$() throws Exception {
        RichVoidFunction2<String, Integer> vf = new RichVoidFunction2(new VoidF2<String, Integer>() {
            public void apply(String v1, Integer v2) {
                System.out.println(v1 + ":" + v2);
            }
        });
        String actual = vf.toString();
        String expected = "<void-function2>";
        assertThat(actual, is(equalTo(expected)));
    }

}
