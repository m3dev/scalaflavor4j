package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class LeftTest {

    @Test
    public void type() throws Exception {
        assertThat(Left.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        Either<String, Integer> target = new Left<String, Integer>("foo");
        assertThat(target, notNullValue());
    }

    @Test
    public void apply_A$Object() throws Exception {
        Either<String, Integer> target = Left.apply("foo");
        assertThat(target, notNullValue());
    }

    @Test
    public void apply_A$Option() throws Exception {
        Either<String, Integer> target = Left.apply(Option._("foo"));
        assertThat(target, notNullValue());
    }

    @Test
    public void __A$Object() throws Exception {
        Either<String, Integer> target = Left._("foo");
        assertThat(target, notNullValue());
    }

    @Test
    public void __A$Option() throws Exception {
        Either<String, Integer> target = Left._(Option._("foo"));
        assertThat(target, notNullValue());
    }

    @Test
    public void isLeft_A$() throws Exception {
        Either<String, Integer> target = Left._("foo");
        boolean actual = target.isLeft();
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void isRight_A$() throws Exception {
        Either<String, Integer> target = Left._("foo");
        boolean actual = target.isRight();
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void left_A$() throws Exception {
        Either<String, Integer> target = Left._("foo");
        Option<String> actual = target.left();
        assertThat(actual.isDefined(), is(true));
        assertThat(actual.getOrNull(), is(equalTo("foo")));
    }

    @Test
    public void right_A$() throws Exception {
        Either<String, Integer> target = Left._("foo");
        Option<Integer> actual = target.right();
        assertThat(actual.isDefined(), is(false));
        assertThat(actual.getOrNull(), is(nullValue()));
    }

    @Test
    public void swap_A$() throws Exception {
        Either<String, Integer> target = Left._("foo");
        Either<Integer, String> swapped = target.swap();
        Option<String> right = swapped.right();
        assertThat(right.isDefined(), is(true));
        assertThat(right.getOrNull(), is(equalTo("foo")));
    }

    @Test
    public void toString_A$() throws Exception {
        Either<String, Integer> target = Left._("foo");
        String actual = target.toString();
        String expected = "Left(foo)";
        assertThat(actual, is(equalTo(expected)));
    }

}
