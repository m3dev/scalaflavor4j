package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class PairTest {

    @Test
    public void type() throws Exception {
        assertThat(Pair.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        Pair<String, Integer> target = new Pair<String, Integer>("1", 2);
        assertThat(target, notNullValue());
    }

    @Test
    public void apply_A$Object$Object() throws Exception {
        Pair<String, Integer> actual = Pair.apply("1", 2);
        assertThat(actual, notNullValue());
    }

    @Test
    public void __A$Object$Object() throws Exception {
        Pair<String, Integer> actual = Pair.apply("1", 2);
        assertThat(actual, notNullValue());
    }

    @Test
    public void unapply_A$Tuple2() throws Exception {
        Pair<String, Integer> tuple = Pair.apply("1", 2);
        Option<Tuple2<String, Integer>> actual = Pair.unapply(tuple);
        assertThat(actual.isDefined(), is(true));
    }

}
