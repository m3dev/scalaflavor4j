package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class FoldRightF2Test {

    @Test
    public void type() throws Exception {
        assertThat(FoldRightF2.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        int sum = Seq._("aaa", "bbbb", "ccc").foldRight(0, new FoldRightF2<String, Integer>() {
            public Integer _(String str, Integer sum) {
                return sum + str.length();
            }
        });
        assertThat(sum, is(equalTo(10)));
    }

}
