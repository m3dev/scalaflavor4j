package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GeneratorTest {

    @Test
    public void type() throws Exception {
        assertThat(Generator.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        CollectionLike<String> xs = Option._("foo");
        Generator<String> target = new Generator<String>(xs);
        assertThat(target, notNullValue());
    }

    @Test
    public void apply_A$CollectionLike() throws Exception {
        CollectionLike<String> xs = Option._("foo");
        Generator<String> actual = Generator.apply(xs);
        assertThat(actual, notNullValue());
    }

    @Test
    public void __A$CollectionLike() throws Exception {
        CollectionLike<String> xs = Option._("foo");
        Generator<String> actual = Generator._(xs);
        assertThat(actual, notNullValue());
    }

    @Test
    public void toCollectionLike_A$List_Option() throws Exception {
        CollectionLike<String> xs = Option._("foo");
        Generator<String> gen = Generator._(xs);
        List<String> xs_ = new ArrayList<String>();
        CollectionLike<String> cl = gen.toCollectionLike(xs_);
        assertThat(cl.isEmpty(), is(true));
    }

    @Test
    public void toCollectionLike_A$List_Seq() throws Exception {
        CollectionLike<String> xs = Seq._("foo", "bar");
        Generator<String> gen = Generator._(xs);
        List<String> xs_ = new ArrayList<String>();
        CollectionLike<String> cl = gen.toCollectionLike(xs_);
        assertThat(cl.isEmpty(), is(true));
    }

    @Test
    public void map_A$Function1_Option() throws Exception {
        CollectionLike<String> xs = Option._("foo");
        Generator<String> gen = Generator._(xs);
        CollectionLike<Integer> cl = gen.map(new F1<String, Integer>() {
            public Integer _(String s) throws Exception {
                return s.length();
            }
        });
        assertThat(cl.isEmpty(), is(false));
        assertThat(cl.toList().get(0), is(equalTo(3)));
    }

    @Test
    public void map_A$Function1_Seq() throws Exception {
        CollectionLike<String> xs = Seq._("foo", "barr");
        Generator<String> gen = Generator._(xs);
        CollectionLike<Integer> cl = gen.map(new F1<String, Integer>() {
            public Integer _(String s) throws Exception {
                return s.length();
            }
        });
        assertThat(cl.isEmpty(), is(false));
        assertThat(cl.toList().get(0), is(equalTo(3)));
        assertThat(cl.toList().get(1), is(equalTo(4)));
    }

    @Test
    public void flatMap_A$Function1_Option() throws Exception {
        CollectionLike<String> xs = Option._("foo");
        Generator<String> gen = Generator._(xs);
        CollectionLike<Integer> cl = gen.flatMap(new FlatMapF1<String, Integer>() {
            public CollectionLike<Integer> _(String s) throws Exception {
                return SInt._(0).until(s.length());
            }
        });
        assertThat(cl.toList().size(), is(equalTo(3)));
    }

    @Test
    public void flatMap_A$Function1_Seq() throws Exception {
        CollectionLike<String> xs = Seq._("foo", "barr");
        Generator<String> gen = Generator._(xs);
        CollectionLike<Integer> cl = gen.flatMap(new FlatMapF1<String, Integer>() {
            public CollectionLike<Integer> _(String s) throws Exception {
                return SInt._(0).until(s.length());
            }
        });
        assertThat(cl.toList().size(), is(equalTo(7)));
    }

    static class Called {
        int count = 0;
    }

    @Test
    public void foreach_A$VoidFunction1_Option() throws Exception {
        CollectionLike<String> xs = Option._("foo");
        Generator<String> gen = Generator._(xs);

        final Called called = new Called();
        gen.foreach(new VoidF1<String>() {
            public void _(String s) throws Exception {
                called.count++;
            }
        });
        assertThat(called.count, is(equalTo(1)));
    }

    @Test
    public void foreach_A$VoidFunction1_Seq() throws Exception {
        CollectionLike<String> xs = Seq._("foo", "barr");
        Generator<String> gen = Generator._(xs);

        final Called called = new Called();
        gen.foreach(new VoidF1<String>() {
            public void _(String s) throws Exception {
                called.count++;
            }
        });
        assertThat(called.count, is(equalTo(2)));
    }

}
