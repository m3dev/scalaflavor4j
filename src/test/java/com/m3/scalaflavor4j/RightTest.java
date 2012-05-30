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

    @Test
    public void mergeToLeft_A$Function1() throws Exception {
        Integer value = 123;
        Either<String, Integer> target = Right._(value);
        Function1<Integer, String> rightToLeft = new F1<Integer, String>() {
            public String _(Integer v) {
                return v.toString();
            }
        };
        String actual = target.mergeToLeft(rightToLeft);
        String expected = "123";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void mergeToRight_A$Function1() throws Exception {
        Integer value = 123;
        Either<String, Integer> target = Right._(value);
        Function1<String, Integer> leftToRight = new F1<String, Integer>() {
            public Integer _(String v) throws Exception {
                return v.length();
            }
        };
        Integer actual = target.mergeToRight(leftToRight);
        Integer expected = 123;
        assertThat(actual, is(equalTo(expected)));
    }

}
