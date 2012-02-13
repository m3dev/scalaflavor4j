package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class ForTest {

    @Test
    public void type() throws Exception {
        assertThat(For.class, notNullValue());
    }

    @Test
    public void __A$CollectionLike() throws Exception {
        CollectionLike<String> xs = Seq._(Arrays.asList("a", "b", "c"));
        ForComprehension1<String> actual = For._(xs);
        assertThat(actual, is(notNullValue()));
    }

    @Test
    public void __A$CollectionLike$CollectionLike() throws Exception {
        CollectionLike<String> xs1 = Seq._(Arrays.asList("a", "b", "c"));
        CollectionLike<Integer> xs2 = Seq._(Arrays.asList(1, 2, 3));
        ForComprehension2<String, Integer> actual = For._(xs1, xs2);
        assertThat(actual, is(notNullValue()));
    }

    @Test
    public void __A$CollectionLike$CollectionLike$CollectionLike() throws Exception {
        CollectionLike<String> xs1 = Seq._(Arrays.asList("a", "b", "c"));
        CollectionLike<Integer> xs2 = Seq._(Arrays.asList(1, 2, 3));
        CollectionLike<Boolean> xs3 = Option._(true);
        ForComprehension3<String, Integer, Boolean> actual = For._(xs1, xs2, xs3);
        assertThat(actual, is(notNullValue()));
    }

    @Test
    public void __A$CollectionLike$CollectionLike$CollectionLike$CollectionLike() throws Exception {
        CollectionLike<String> xs1 = Seq._(Arrays.asList("a", "b", "c"));
        CollectionLike<Integer> xs2 = Seq._(Arrays.asList(1, 2, 3));
        CollectionLike<Boolean> xs3 = Option._(true);
        CollectionLike<Long> xs4 = Option.none();
        ForComprehension4<String, Integer, Boolean, Long> actual = For._(xs1, xs2, xs3, xs4);
        assertThat(actual, is(notNullValue()));
    }

    @Test
    public void __A$CollectionLike$CollectionLike$CollectionLike$CollectionLike$CollectionLike() throws Exception {
        CollectionLike<String> xs1 = Seq._(Arrays.asList("a", "b", "c"));
        CollectionLike<Integer> xs2 = Seq._(Arrays.asList(1, 2, 3));
        CollectionLike<Boolean> xs3 = Option._(true);
        CollectionLike<Long> xs4 = Option.none();
        CollectionLike<Double> xs5 = Seq._(Arrays.asList(1.0D, 0.3D));
        ForComprehension5<String, Integer, Boolean, Long, Double> actual = For._(xs1, xs2, xs3, xs4, xs5);
        assertThat(actual, is(notNullValue()));
    }

}
