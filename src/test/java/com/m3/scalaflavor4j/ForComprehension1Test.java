package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ForComprehension1Test {

    @Test
    public void type() throws Exception {
        assertThat(ForComprehension1.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        CollectionLike<String> xs = Seq._("foo", "barr");
        ForComprehension1<String> lc = new ForComprehension1<String>(xs);
        assertThat(lc, notNullValue());
    }

    @Test
    public void yield_A$Function1() throws Exception {
        CollectionLike<String> xs = Seq._("foo", "barr");
        Seq<Integer> rs = For._(xs).yield(new F1<String, Integer>() {
            public Integer _(String s) {
                return s.length();
            }
        });
        assertThat(rs.head(), is(equalTo(3)));
        assertThat(rs.tail().head(), is(equalTo(4)));
    }

    static class Called {
        int count = 0;
    }

    @Test
    public void __A$VoidFunction1() throws Exception {
        CollectionLike<String> xs = Seq._("foo", "barr");
        final Called c = new Called();
        For._(xs)._(new VoidF1<String>() {
            public void _(String s) {
                c.count++;
            }
        });
        assertThat(c.count, is(equalTo(2)));
    }

}
