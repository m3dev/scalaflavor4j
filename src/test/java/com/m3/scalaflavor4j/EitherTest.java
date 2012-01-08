package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class EitherTest {

    @Test
    public void type() throws Exception {
        assertThat(Either.class, notNullValue());
    }

    Either<String, Integer> right = Right.apply(123);
    Either<String, Integer> left = Left.apply("abc");

    @Test
    public void instantiation() throws Exception {
        Either<String, Integer> right = Right.apply(123);
        assertThat(right, notNullValue());
        Either<String, Integer> left = Left.apply("abc");
        assertThat(left, notNullValue());
    }

    @Test
    public void isLeft_A$() throws Exception {
        assertThat(left.isLeft(), is(true));
        assertThat(right.isLeft(), is(false));
    }

    @Test
    public void isRight_A$() throws Exception {
        assertThat(left.isRight(), is(false));
        assertThat(right.isRight(), is(true));
    }

    @Test
    public void left_A$() throws Exception {
        assertThat(left.left().isDefined(), is(true));
        assertThat(right.left().isDefined(), is(false));
    }

    @Test
    public void right_A$() throws Exception {
        assertThat(left.right().isDefined(), is(false));
        assertThat(right.right().isDefined(), is(true));
    }

    @Test
    public void swap_A$() throws Exception {
        assertThat(left.swap().right().isDefined(), is(true));
        assertThat(left.swap().left().isDefined(), is(false));
        assertThat(right.swap().left().isDefined(), is(true));
        assertThat(right.swap().right().isDefined(), is(false));
    }

    @Test
    public void toString_A$() throws Exception {
        assertThat(left.toString(), is("Left(abc)"));
        assertThat(right.toString(), is("Right(123)"));
    }

}
