package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ForComprehension4Test {

    @Test
    public void type() throws Exception {
        assertThat(ForComprehension4.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        CollectionLike<String> xs1 = Seq.apply("a", "b");
        CollectionLike<Integer> xs2 = Seq.apply(1, 2, 3, 4, 5);
        CollectionLike<Long> xs3 = Seq.apply(10L, 20L);
        CollectionLike<Boolean> xs4 = Option.apply(false);
        ForComprehension4<String, Integer, Long, Boolean> target = new ForComprehension4<String, Integer, Long, Boolean>(
                xs1, xs2, xs3, xs4);
        assertThat(target, notNullValue());
    }

    @Test
    public void yield_A$Function1() throws Exception {
        CollectionLike<String> xs1 = Seq.apply("a", "b");
        CollectionLike<Integer> xs2 = Seq.apply(1, 2, 3, 4, 5);
        CollectionLike<Long> xs3 = Seq.apply(10L, 20L);
        CollectionLike<Boolean> xs4 = Option.apply(false);
        Seq<String> rs = For.apply(xs1, xs2, xs3, xs4).yield(new F1<Tuple4<String, Integer, Long, Boolean>, String>() {
            public String apply(Tuple4<String, Integer, Long, Boolean> t) throws Exception {
                return t._1() + t._2() + t._3() + t._4();
            }
        });
        assertThat(rs.size(), is(equalTo(20)));
    }

    @Test
    public void yield_A$Function1_emptyGenerator() throws Exception {
        CollectionLike<String> xs1 = Seq.apply("a", "b");
        CollectionLike<Integer> xs2 = Seq.apply(1, 2, 3, 4, 5);
        CollectionLike<Long> xs3 = Seq.apply();
        CollectionLike<Boolean> xs4 = Option.apply(false);
        Seq<String> rs = For.apply(xs1, xs2, xs3, xs4).yield(new F1<Tuple4<String, Integer, Long, Boolean>, String>() {
            public String apply(Tuple4<String, Integer, Long, Boolean> t) throws Exception {
                return t._1() + t._2() + t._3() + t._4();
            }
        });
        assertThat(rs.size(), is(equalTo(0)));
    }

    static class Called {
        int count = 0;
    }

    @Test
    public void __A$VoidFunction1() throws Exception {
        CollectionLike<String> xs1 = Seq.apply("a", "b");
        CollectionLike<Integer> xs2 = Seq.apply(1, 2, 3, 4, 5);
        CollectionLike<Long> xs3 = Seq.apply(10L, 20L);
        CollectionLike<Boolean> xs4 = Option.apply(false);
        final Called c = new Called();
        For.apply(xs1, xs2, xs3, xs4).apply(new VoidF1<Tuple4<String, Integer, Long, Boolean>>() {
            public void apply(Tuple4<String, Integer, Long, Boolean> t) {
                c.count++;
            }
        });
        assertThat(c.count, is(equalTo(20)));
    }

    @Test
    public void __A$VoidFunction1_emptyGenerator() throws Exception {
        CollectionLike<String> xs1 = Seq.apply("a", "b");
        CollectionLike<Integer> xs2 = Seq.apply();
        CollectionLike<Long> xs3 = Seq.apply(10L, 20L);
        CollectionLike<Boolean> xs4 = Option.apply(false);
        final Called c = new Called();
        For.apply(xs1, xs2, xs3, xs4).apply(new VoidF1<Tuple4<String, Integer, Long, Boolean>>() {
            public void apply(Tuple4<String, Integer, Long, Boolean> t) {
                c.count++;
            }
        });
        assertThat(c.count, is(equalTo(0)));
    }

}
