package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class PredicateF1Test {

    @Test
    public void type() throws Exception {
        assertThat(PredicateF1.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        Seq<Integer> filtered = Seq._(1, 2, 3, 4, 5).filter(new PredicateF1<Integer>() {
            public Boolean _(Integer i) {
                return i <= 3;
            }
        });
        assertThat(filtered.mkString(","), is(equalTo("1,2,3")));
    }

}
