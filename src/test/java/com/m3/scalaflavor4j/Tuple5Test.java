package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class Tuple5Test {

    @Test
    public void type() throws Exception {
        assertThat(Tuple5.class, notNullValue());
    }

    @Test
    public void _1_A$() throws Exception {
        String _1 = "foo";
        Integer _2 = 123;
        Long _3 = 456L;
        Boolean _4 = true;
        Double _5 = 7.8D;
        Tuple5<String, Integer, Long, Boolean, Double> target = Tuple5.apply(_1, _2, _3, _4, _5);
        String actual = target._1();
        String expected = "foo";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void _2_A$() throws Exception {
        String _1 = "foo";
        Integer _2 = 123;
        Long _3 = 456L;
        Boolean _4 = true;
        Double _5 = 7.8D;
        Tuple5<String, Integer, Long, Boolean, Double> target = Tuple5.apply(_1, _2, _3, _4, _5);
        Integer actual = target._2();
        Integer expected = 123;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void _3_A$() throws Exception {
        String _1 = "foo";
        Integer _2 = 123;
        Long _3 = 456L;
        Boolean _4 = true;
        Double _5 = 7.8D;
        Tuple5<String, Integer, Long, Boolean, Double> target = Tuple5.apply(_1, _2, _3, _4, _5);
        Long actual = target._3();
        Long expected = 456L;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void _4_A$() throws Exception {
        String _1 = "foo";
        Integer _2 = 123;
        Long _3 = 456L;
        Boolean _4 = true;
        Double _5 = 7.8D;
        Tuple5<String, Integer, Long, Boolean, Double> target = Tuple5.apply(_1, _2, _3, _4, _5);
        Boolean actual = target._4();
        Boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void _5_A$() throws Exception {
        String _1 = "foo";
        Integer _2 = 123;
        Long _3 = 456L;
        Boolean _4 = true;
        Double _5 = 7.8D;
        Tuple5<String, Integer, Long, Boolean, Double> target = Tuple5.apply(_1, _2, _3, _4, _5);
        Double actual = target._5();
        Double expected = 7.8D;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void apply_A$Object$Object$Object$Object$Object() throws Exception {
        String _1 = "foo";
        Integer _2 = 123;
        Long _3 = 456L;
        Boolean _4 = true;
        Double _5 = 7.8D;
        Tuple5<String, Integer, Long, Boolean, Double> actual = Tuple5.apply(_1, _2, _3, _4, _5);
        assertThat(actual, is(notNullValue()));
    }

    @Test
    public void __A$Object$Object$Object$Object$Object() throws Exception {
        String _1 = "foo";
        Integer _2 = 123;
        Long _3 = 456L;
        Boolean _4 = true;
        Double _5 = 7.8D;
        Tuple5<String, Integer, Long, Boolean, Double> actual = Tuple5._(_1, _2, _3, _4, _5);
        assertThat(actual, is(notNullValue()));
    }

    @Test
    public void unapply_A$Tuple5() throws Exception {
        String _1 = "foo";
        Integer _2 = 123;
        Long _3 = 456L;
        Boolean _4 = true;
        Double _5 = 7.8D;
        Tuple5<String, Integer, Long, Boolean, Double> tuple = Tuple5._(_1, _2, _3, _4, _5);
        Option<Tuple5<String, Integer, Long, Boolean, Double>> actual = Tuple5.unapply(tuple);
        assertThat(actual.isDefined(), is(true));
    }

    @Test
    public void toString_A$() throws Exception {
        String actual = Tuple5._(1, 2, 3, 4, 5).toString();
        String expected = "(1,2,3,4,5)";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void instantiation() throws Exception {
        String _1 = "foo";
        Integer _2 = 123;
        Long _3 = 456L;
        Boolean _4 = true;
        Double _5 = 7.8D;
        Tuple5<String, Integer, Long, Boolean, Double> target = new Tuple5<String, Integer, Long, Boolean, Double>(_1,
                _2, _3, _4, _5);
        assertThat(target, notNullValue());
    }

    @Test
    public void getFirst_A$() throws Exception {
        String _1 = "foo";
        Integer _2 = 123;
        Long _3 = 456L;
        Boolean _4 = true;
        Double _5 = 7.8D;
        Tuple5<String, Integer, Long, Boolean, Double> tuple = Tuple._(_1, _2, _3, _4, _5);
        String actual = tuple.getFirst();
        String expected = "foo";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void getSecond_A$() throws Exception {
        String _1 = "foo";
        Integer _2 = 123;
        Long _3 = 456L;
        Boolean _4 = true;
        Double _5 = 7.8D;
        Tuple5<String, Integer, Long, Boolean, Double> tuple = Tuple._(_1, _2, _3, _4, _5);
        Integer actual = tuple.getSecond();
        Integer expected = 123;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void getThird_A$() throws Exception {
        String _1 = "foo";
        Integer _2 = 123;
        Long _3 = 456L;
        Boolean _4 = true;
        Double _5 = 7.8D;
        Tuple5<String, Integer, Long, Boolean, Double> tuple = Tuple._(_1, _2, _3, _4, _5);
        Long actual = tuple.getThird();
        Long expected = 456L;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void getFourth_A$() throws Exception {
        String _1 = "foo";
        Integer _2 = 123;
        Long _3 = 456L;
        Boolean _4 = true;
        Double _5 = 7.8D;
        Tuple5<String, Integer, Long, Boolean, Double> tuple = Tuple._(_1, _2, _3, _4, _5);
        Boolean actual = tuple.getFourth();
        Boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void getFifth_A$() throws Exception {
        String _1 = "foo";
        Integer _2 = 123;
        Long _3 = 456L;
        Boolean _4 = true;
        Double _5 = 7.8D;
        Tuple5<String, Integer, Long, Boolean, Double> tuple = Tuple._(_1, _2, _3, _4, _5);
        Double actual = tuple.getFifth();
        Double expected = 7.8D;
        assertThat(actual, is(equalTo(expected)));
    }

}
