package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class FoldLeftF2Test {

    @Test
    public void type() throws Exception {
        assertThat(FoldLeftF2.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        int sum = Seq._("aaa", "bbbb", "ccc").foldLeft(0, new FoldLeftF2<Integer, String>() {
            public Integer _(Integer sum, String str) {
                return sum + str.length();
            }
        });
        assertThat(sum, is(equalTo(10)));
    }

}
