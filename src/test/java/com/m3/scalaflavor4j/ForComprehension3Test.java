package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ForComprehension3Test {

    @Test
    public void type() throws Exception {
        assertThat(ForComprehension3.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        CollectionLike<String> xs1 = Seq._("a", "b");
        CollectionLike<Integer> xs2 = Seq._(1, 2, 3, 4, 5);
        CollectionLike<Long> xs3 = Seq._(10L, 20L);
        ForComprehension3<String, Integer, Long> target = new ForComprehension3<String, Integer, Long>(xs1, xs2, xs3);
        assertThat(target, notNullValue());
    }

    @Test
    public void yield_A$Function1() throws Exception {
        CollectionLike<String> xs1 = Seq._("a", "b");
        CollectionLike<Integer> xs2 = Seq._(1, 2, 3, 4, 5);
        CollectionLike<Long> xs3 = Seq._(10L, 20L);
        Seq<String> rs = For._(xs1, xs2, xs3).yield(new F1<Tuple3<String, Integer, Long>, String>() {
            public String _(Tuple3<String, Integer, Long> t) {
                return t._1() + t._2() + t._3();
            }
        });
        assertThat(rs.size(), is(equalTo(20)));
    }

    @Test
    public void yield_A$Function1_emptyGenerator() throws Exception {
        CollectionLike<String> xs1 = Seq._("a", "b");
        CollectionLike<Integer> xs2 = Seq._();
        CollectionLike<Long> xs3 = Seq._(10L, 20L);
        Seq<String> rs = For._(xs1, xs2, xs3).yield(new F1<Tuple3<String, Integer, Long>, String>() {
            public String _(Tuple3<String, Integer, Long> t) {
                return t._1() + t._2() + t._3();
            }
        });
        assertThat(rs.size(), is(equalTo(0)));
    }

    static class Called {
        int count = 0;
    }

    @Test
    public void __A$VoidFunction1() throws Exception {
        CollectionLike<String> xs1 = Seq._("a", "b");
        CollectionLike<Integer> xs2 = Seq._(1, 2, 3, 4, 5);
        CollectionLike<Long> xs3 = Seq._(10L, 20L);
        final Called c = new Called();
        For._(xs1, xs2, xs3)._(new VoidF1<Tuple3<String, Integer, Long>>() {
            public void _(Tuple3<String, Integer, Long> t) {
                c.count++;
            }
        });
        assertThat(c.count, is(equalTo(20)));
    }

    @Test
    public void __A$VoidFunction1_emptyGenerator() throws Exception {
        CollectionLike<String> xs1 = Seq._("a", "b");
        CollectionLike<Integer> xs2 = Seq._(1, 2, 3, 4, 5);
        CollectionLike<Long> xs3 = Seq._();
        final Called c = new Called();
        For._(xs1, xs2, xs3)._(new VoidF1<Tuple3<String, Integer, Long>>() {
            public void _(Tuple3<String, Integer, Long> t) {
                c.count++;
            }
        });
        assertThat(c.count, is(equalTo(0)));
    }

}
