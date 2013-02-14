package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ForComprehension5Test {

    @Test
    public void type() throws Exception {
        assertThat(ForComprehension5.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        CollectionLike<String> xs1 = Seq.apply("a", "b");
        CollectionLike<Integer> xs2 = Seq.apply(1, 2, 3, 4, 5);
        CollectionLike<Long> xs3 = Seq.apply(10L, 20L);
        CollectionLike<Boolean> xs4 = Option.apply(false);
        CollectionLike<Double> xs5 = Seq.apply(0.1D, 0.2D, 0.3D);
        ForComprehension5<String, Integer, Long, Boolean, Double> target = new ForComprehension5<String, Integer, Long, Boolean, Double>(
                xs1, xs2, xs3, xs4, xs5);
        assertThat(target, notNullValue());
    }

    @Test
    public void yield_A$Function1() throws Exception {
        CollectionLike<String> xs1 = Seq.apply("a", "b");
        CollectionLike<Integer> xs2 = Seq.apply(1, 2, 3, 4, 5);
        CollectionLike<Long> xs3 = Seq.apply(10L, 20L);
        CollectionLike<Boolean> xs4 = Option.apply(false);
        CollectionLike<Double> xs5 = Seq.apply(0.1D, 0.2D, 0.3D);
        Seq<String> rs = For.apply(xs1, xs2, xs3, xs4, xs5).yield(
                new F1<Tuple5<String, Integer, Long, Boolean, Double>, String>() {
                    public String apply(Tuple5<String, Integer, Long, Boolean, Double> t) {
                        return t._1() + t._2() + t._3() + t._4() + t._5();
                    }
                });
        assertThat(rs.size(), is(equalTo(60)));
    }

    @Test
    public void yield_A$Function1_emptyGenerator() throws Exception {
        CollectionLike<String> xs1 = Seq.apply("a", "b");
        CollectionLike<Integer> xs2 = Seq.apply(1, 2, 3, 4, 5);
        CollectionLike<Long> xs3 = Seq.apply(10L, 20L);
        CollectionLike<Boolean> xs4 = Option.none();
        CollectionLike<Double> xs5 = Seq.apply(0.1D, 0.2D, 0.3D);
        Seq<String> rs = For.apply(xs1, xs2, xs3, xs4, xs5).yield(
                new F1<Tuple5<String, Integer, Long, Boolean, Double>, String>() {
                    public String apply(Tuple5<String, Integer, Long, Boolean, Double> t) {
                        return t._1() + t._2() + t._3() + t._4() + t._5();
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
        CollectionLike<Double> xs5 = Seq.apply(0.1D, 0.2D, 0.3D);
        final Called c = new Called();
        For.apply(xs1, xs2, xs3, xs4, xs5).apply(new VoidF1<Tuple5<String, Integer, Long, Boolean, Double>>() {
            public void apply(Tuple5<String, Integer, Long, Boolean, Double> t) {
                c.count++;
            }
        });
        assertThat(c.count, is(equalTo(60)));
    }

    @Test
    public void __A$VoidFunction1_emptyGenerator() throws Exception {
        CollectionLike<String> xs1 = Seq.apply("a", "b");
        CollectionLike<Integer> xs2 = Seq.apply(1, 2, 3, 4, 5);
        CollectionLike<Long> xs3 = Seq.apply(10L, 20L);
        CollectionLike<Boolean> xs4 = Option.none();
        CollectionLike<Double> xs5 = Seq.apply(0.1D, 0.2D, 0.3D);
        final Called c = new Called();
        For.apply(xs1, xs2, xs3, xs4, xs5).apply(new VoidF1<Tuple5<String, Integer, Long, Boolean, Double>>() {
            public void apply(Tuple5<String, Integer, Long, Boolean, Double> t) {
                c.count++;
            }
        });
        assertThat(c.count, is(equalTo(0)));
    }

}
