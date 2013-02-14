package com.m3.scalaflavor4j;

import static com.m3.scalaflavor4j.arm.Resource.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class ForComprehension2Test {

    @Test
    public void type() throws Exception {
        assertThat(ForComprehension2.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        CollectionLike<String> xs1 = Seq.apply("aaa");
        CollectionLike<Integer> xs2 = Seq.apply(123);
        ForComprehension2<String, Integer> target = new ForComprehension2<String, Integer>(xs1, xs2);
        assertThat(target, notNullValue());
    }

    @Test
    public void yield_A$Function1() throws Exception {

        Seq<String> xs1 = Seq.apply("abc", "abcd", "abcde");
        Seq<Integer> xs2 = Seq.apply(3, 4, 5);
        Seq<Boolean> bs = For.apply(xs1, xs2).yield(new F1<Tuple2<String, Integer>, Boolean>() {
            public Boolean apply(Tuple2<String, Integer> tpl) {
                return tpl._1().length() == tpl._2();
            }
        });

        assertThat(bs.size(), is(equalTo(9)));
        assertThat(bs.toList().get(0), is(true));
        assertThat(bs.toList().get(1), is(false));
        assertThat(bs.toList().get(2), is(false));
        assertThat(bs.toList().get(3), is(false));
        assertThat(bs.toList().get(4), is(true));
        assertThat(bs.toList().get(5), is(false));
        assertThat(bs.toList().get(6), is(false));
        assertThat(bs.toList().get(7), is(false));
        assertThat(bs.toList().get(8), is(true));
    }

    @Test
    public void yield_A$Function1_emptyGenerator() throws Exception {
        Seq<String> xs1 = Seq.apply("abc", "abcd", "abcde");
        Seq<Integer> xs2 = Seq.apply();
        Seq<Boolean> bs = For.apply(xs1, xs2).yield(new F1<Tuple2<String, Integer>, Boolean>() {
            public Boolean apply(Tuple2<String, Integer> tpl) {
                return tpl._1().length() == tpl._2();
            }
        });
        assertThat(bs.size(), is(equalTo(0)));
    }

    static class Called {
        boolean value = false;
    }

    static class CalledCount {
        int value = 0;
    }

    @Test
    public void __A$VoidFunction1() throws Exception {

        final CalledCount c1 = new CalledCount();
        For.apply(Seq.apply("abc", "abcd", "abcde"), Seq.apply(3, 4, 5)).apply(new VoidF1<Tuple2<String, Integer>>() {
            public void apply(Tuple2<String, Integer> tpl) {
                c1.value++;
            }
        });
        assertThat(c1.value, is(equalTo(9)));

        final Called called = new Called();
        For.apply(managed((InputStream) new ByteArrayInputStream(new byte[] { 83, 99, 97, 108, 97 }) {
            public void close() throws IOException {
                called.value = true;
                super.close();
            }
        }), Seq.apply(123)).apply(new VoidF1<Tuple2<InputStream, Integer>>() {
            public void apply(Tuple2<InputStream, Integer> tpl) {
            }
        });
        assertThat(called.value, is(true));
    }

    @Test
    public void __A$VoidFunction1_emptyGenerator() throws Exception {
        final CalledCount c = new CalledCount();
        For.apply(Seq.apply("abc", "abcd", "abcde"), Seq.<Integer> apply()).apply(new VoidF1<Tuple2<String, Integer>>() {
            public void apply(Tuple2<String, Integer> tpl) {
                c.value++;
            }
        });
        assertThat(c.value, is(equalTo(0)));
    }

}
