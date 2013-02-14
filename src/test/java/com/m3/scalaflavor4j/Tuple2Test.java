package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class Tuple2Test {

    @Test
    public void type() throws Exception {
        assertThat(Tuple2.class, notNullValue());
    }

    @Test
    public void _1_A$() throws Exception {
        String _1 = "foo";
        Integer _2 = 123;
        Tuple2<String, Integer> target = Tuple2.apply(_1, _2);
        String actual = target._1();
        String expected = "foo";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void _2_A$() throws Exception {
        String _1 = "foo";
        Integer _2 = 123;
        Tuple2<String, Integer> target = Tuple2.apply(_1, _2);
        Integer actual = target._2();
        Integer expected = 123;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void apply_A$Object$Object() throws Exception {
        Tuple2<String, Integer> actual = Tuple2.apply("a", 1);
        assertThat(actual, is(notNullValue()));
    }

    @Test
    public void __A$Object$Object() throws Exception {
        Tuple2<String, Integer> actual = Tuple2.apply("a", 1);
        assertThat(actual, is(notNullValue()));
    }

    @Test
    public void swap_A$() throws Exception {
        Tuple2<String, Integer> target = Tuple2.apply("a", 1);
        Tuple2<Integer, String> actual = target.swap();
        assertThat(actual._1(), is(equalTo(1)));
        assertThat(actual._2(), is(equalTo("a")));
    }

    @Test
    public void instantiation() throws Exception {
        Tuple2<String, Integer> target = new Tuple2<String, Integer>("a", 1);
        assertThat(target, notNullValue());
    }

    @Test
    public void unapply_A$Tuple2() throws Exception {
        Tuple2<String, Integer> tuple = new Tuple2<String, Integer>("a", 1);
        Option<Tuple2<String, Integer>> actual = Tuple2.unapply(tuple);
        assertThat(actual.isDefined(), is(true));
    }

    @Test
    public void toString_A$() throws Exception {
        String actual = Tuple2.apply(1, 2).toString();
        String expected = "(1,2)";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void getFirst_A$() throws Exception {
        Tuple2<Integer, Long> tuple = Tuple2.apply(1, 2L);
        Integer actual = tuple.getFirst();
        Integer expected = 1;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void getSecond_A$() throws Exception {
        Tuple2<Integer, Long> tuple = Tuple2.apply(1, 2L);
        Long actual = tuple.getSecond();
        Long expected = 2L;
        assertThat(actual, is(equalTo(expected)));
    }

}
