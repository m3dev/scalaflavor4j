package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class RightTest {

    @Test
    public void type() throws Exception {
        assertThat(Right.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        Either<String, Integer> target = new Right<String, Integer>(123);
        assertThat(target, notNullValue());
    }

    @Test
    public void apply_A$Object() throws Exception {
        Either<String, Integer> target = Right.apply(123);
        assertThat(target, notNullValue());
    }

    @Test
    public void apply_A$Option() throws Exception {
        Either<String, Integer> target = Right.apply(Option._(123));
        assertThat(target, notNullValue());
    }

    @Test
    public void __A$Object() throws Exception {
        Either<String, Integer> target = Right._(123);
        assertThat(target, notNullValue());
    }

    @Test
    public void __A$Option() throws Exception {
        Either<String, Integer> target = Right.apply(Option._(123));
        assertThat(target, notNullValue());
    }

    @Test
    public void isLeft_A$() throws Exception {
        Either<String, Integer> target = Right._(123);
        boolean actual = target.isLeft();
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void isRight_A$() throws Exception {
        Either<String, Integer> target = Right._(123);
        boolean actual = target.isRight();
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void left_A$() throws Exception {
        Either<String, Integer> target = Right._(123);
        Option<String> actual = target.left();
        assertThat(actual.isDefined(), is(false));
        assertThat(actual.getOrNull(), is(nullValue()));
    }

    @Test
    public void right_A$() throws Exception {
        Either<String, Integer> target = Right._(123);
        Option<Integer> actual = target.right();
        assertThat(actual.isDefined(), is(true));
        assertThat(actual.getOrNull(), is(equalTo(123)));
    }

    @Test
    public void swap_A$() throws Exception {
        Either<String, Integer> target = Right._(123);
        Either<Integer, String> swapped = target.swap();
        assertThat(swapped.isLeft(), is(true));
    }

    @Test
    public void toString_A$() throws Exception {
        Either<String, Integer> target = Right._(123);
        String actual = target.toString();
        String expected = "Right(123)";
        assertThat(actual, is(equalTo(expected)));
    }

}
