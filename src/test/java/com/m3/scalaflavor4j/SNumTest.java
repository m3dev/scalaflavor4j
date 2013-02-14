package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

public class SNumTest {

    @Test
    public void type() throws Exception {
        assertThat(SNum.class, notNullValue());
    }

    @Test
    public void apply_A$Integer() throws Exception {
        Integer i_ = 123;
        SNum actual = SNum.apply(i_);
        assertThat(actual.toInt(), is(equalTo(123)));
    }

    @Test
    public void __A$Integer() throws Exception {
        Integer i_ = 123;
        SNum actual = SNum.apply(i_);
        assertThat(actual.toInt(), is(equalTo(123)));
    }

    @Test
    public void toBigDecimal_A$() throws Exception {
        {
            SNum target = SNum.apply(123);
            BigDecimal actual = target.toBigDecimal();
            assertThat(actual.toString(), is(equalTo("123")));
        }
        {
            BigDecimal i = new BigDecimal(Long.MAX_VALUE);
            SNum actual = SNum.apply(i);
            assertThat(actual.toLong(), is(notNullValue()));
        }
    }

    @Test
    public void toBigInteger_A$() throws Exception {
        SNum target = SNum.apply(123);
        BigInteger actual = target.toBigInteger();
        assertThat(actual.toString(), is(equalTo("123")));
    }

    @Test
    public void toDouble_A$() throws Exception {
        SNum target = SNum.apply(123);
        Double actual = target.toDouble();
        assertThat(actual, is(equalTo(123.0D)));
    }

    @Test
    public void toFloat_A$() throws Exception {
        SNum target = SNum.apply(123);
        Float actual = target.toFloat();
        assertThat(actual, is(equalTo(123.0F)));
    }

    @Test
    public void toInt_A$() throws Exception {
        SNum target = SNum.apply(123);
        Integer actual = target.toInt();
        assertThat(actual, is(equalTo(123)));
    }

    @Test
    public void toLong_A$() throws Exception {
        SNum target = SNum.apply(123);
        Long actual = target.toLong();
        assertThat(actual, is(equalTo(123L)));
    }

    @Test
    public void toSInt_A$() throws Exception {
        SNum target = SNum.apply(123);
        SInt actual = target.toSInt();
        assertThat(actual.getOrElse(0), is(equalTo(123)));
    }

    @Test
    public void toSLong_A$() throws Exception {
        SNum target = SNum.apply(123);
        SLong actual = target.toSLong();
        assertThat(actual.getOrElse(0L), is(equalTo(123L)));
    }

    @Test
    public void apply_A$BigDecimal() throws Exception {
        BigDecimal i_ = new BigDecimal(123);
        SNum actual = SNum.apply(i_);
        assertThat(actual.toInt(), is(equalTo(123)));
    }

    @Test
    public void __A$BigDecimal() throws Exception {
        BigDecimal i_ = new BigDecimal(123);
        SNum actual = SNum.apply(i_);
        assertThat(actual.toInt(), is(equalTo(123)));
    }

    @Test
    public void toString_A$() throws Exception {
        BigDecimal i_ = new BigDecimal(123);
        SNum snum = SNum.apply(i_);
        String actual = snum.toString();
        String expected = "SNum(123)";
        assertThat(actual, is(equalTo(expected)));
    }

}
