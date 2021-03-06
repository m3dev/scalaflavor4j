package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class SMapTest {

    @Test
    public void type() throws Exception {
        assertThat(SMap.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("foo", 123);
        map.put("bar", 456);
        SMap<String, Integer> smap = SMap.apply(map);
        assertThat(smap, notNullValue());
    }

    @Test
    public void apply_A$Map() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("foo", 123);
        map.put("bar", 456);
        SMap<String, Integer> smap = SMap.apply(map);
        assertThat(smap.isEmpty(), is(false));
    }

    @Test
    public void __A$Map() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("foo", 123);
        map.put("bar", 456);
        SMap<String, Integer> smap = SMap.apply(map);
        assertThat(smap.isEmpty(), is(false));
    }

    @Test
    public void toList_A$() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("foo", 123);
        map.put("bar", 456);
        SMap<String, Integer> smap = SMap.apply(map);
        List<Tuple2<String, Integer>> actual = smap.toList();
        assertThat(actual.isEmpty(), is(false));
        assertThat(actual.get(0)._1(), is(equalTo("foo")));
        assertThat(actual.get(1)._1(), is(equalTo("bar")));
    }

    @Test
    public void isEmpty_A$() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("foo", 123);
        map.put("bar", 456);
        SMap<String, Integer> smap = SMap.apply(map);
        boolean actual = smap.isEmpty();
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void filter_A$Function1() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("foo", 123);
        map.put("bar", 456);
        SMap<String, Integer> smap = SMap.apply(map);
        SMap<String, Integer> filtered = smap.filter(new F1<Tuple2<String, Integer>, Boolean>() {
            public Boolean apply(Tuple2<String, Integer> v1) {
                return !v1._1().contains("f");
            }
        });
        assertThat(filtered.toList().size(), is(equalTo(1)));
        assertThat(filtered.toList().get(0)._1(), is(equalTo("bar")));
    }

    @Test
    public void foreach_A$VoidFunction1() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("foo", 123);
        map.put("bar", 456);
        SMap<String, Integer> smap = SMap.apply(map);
        smap.foreach(new VoidF1<Tuple2<String, Integer>>() {
            public void apply(Tuple2<String, Integer> v1) {
                System.out.println(v1._1() + ":" + v1._2());
            }
        });
    }

    @Test
    public void map_A$Function1() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("foo", 123);
        map.put("bar", 456);
        SMap<String, Integer> smap = SMap.apply(map);
        SMap<String, String> newMap = smap.map(new F1<Tuple2<String, Integer>, Tuple2<String, String>>() {
            public Tuple2<String, String> apply(Tuple2<String, Integer> v1) {
                return Tuple.apply(v1._1(), v1._2().toString());
            }
        });
        assertThat(newMap.toList().get(0)._2(), is(equalTo("123")));
        assertThat(newMap.toList().get(1)._2(), is(equalTo("456")));
    }

    @Test
    public void toMap_A$() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("foo", 123);
        map.put("bar", 456);
        SMap<String, Integer> smap = SMap.apply(map);
        Map<String, Integer> actual = smap.toMap();
        assertThat(actual, is(notNullValue()));
    }

    @Test
    public void iterator_A$() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("foo", 123);
        map.put("bar", 456);
        SMap<String, Integer> smap = SMap.apply(map);
        Iterator<Tuple2<String, Integer>> actual = smap.iterator();
        assertThat(actual, is(notNullValue()));
    }

    @Test
    public void getOrElse_A$Object$Object() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("foo", 123);
        map.put("bar", 456);
        SMap<String, Integer> smap = SMap.apply(map);
        Integer actual = smap.getOrElse("foo", 999);
        Integer expected = 123;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void unzip_A$() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("foo", 123);
        map.put("bar", 456);
        SMap<String, Integer> smap = SMap.apply(map);
        Tuple2<Seq<String>, Seq<Integer>> actual = smap.unzip();
        assertThat(actual._1().mkString("-"), is(equalTo("foo-bar")));
        assertThat(actual._2().mkString("-"), is(equalTo("123-456")));
    }

    @Test
    public void apply_A$Seq() throws Exception {
        @SuppressWarnings("unchecked")
        SMap<String, Integer> actual = SMap.apply(Seq.apply(Tuple2.apply("foo", 123), Tuple2.apply("bar", 234),
                Tuple2.apply("baz", 345)));
        assertThat(actual.toMap().get("foo"), is(equalTo(123)));
        assertThat(actual.toMap().get("bar"), is(equalTo(234)));
        assertThat(actual.toMap().get("baz"), is(equalTo(345)));
    }

    @Test
    public void __A$Seq() throws Exception {
        @SuppressWarnings("unchecked")
        SMap<String, Integer> actual = SMap.apply(Seq.apply(Tuple.apply("foo", 123), Tuple.apply("bar", 234), Tuple.apply("baz", 345)));
        assertThat(actual.toMap().get("foo"), is(equalTo(123)));
        assertThat(actual.toMap().get("bar"), is(equalTo(234)));
        assertThat(actual.toMap().get("baz"), is(equalTo(345)));
    }

    @Test
    public void toSeq_A$() throws Exception {
        @SuppressWarnings("unchecked")
        SMap<String, Integer> map = SMap.apply(Seq.apply(Tuple.apply("foo", 123), Tuple.apply("bar", 234), Tuple.apply("baz", 345)));
        Seq<Tuple2<String, Integer>> actual = map.toSeq();
        assertThat(actual.toList().size(), is(equalTo(3)));
    }

    @Test
    public void __A$() throws Exception {
        SMap<String, Integer> actual = SMap.apply();
        assertThat(actual, is(notNullValue()));
    }

    @Test
    public void apply_A$() throws Exception {
        SMap<String, Integer> actual = SMap.apply();
        assertThat(actual, is(notNullValue()));
    }

    @Test
    public void copy_A$() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("foo", 123);
        map.put("bar", 234);
        map.put("baz", 345);
        SMap<String, Integer> original = SMap.apply(map);
        SMap<String, Integer> copied = original.copy();
        assertThat(copied.toMap() == original.toMap(), is(false));
        assertThat(copied.getOrElse("foo", 0), is(equalTo(123)));
        assertThat(copied.getOrElse("bar", 0), is(equalTo(234)));
        assertThat(copied.getOrElse("baz", 0), is(equalTo(345)));
    }

    @Test
    public void toString_A$() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("foo", 123);
        map.put("bar", 456);
        SMap<String, Integer> smap = SMap.apply(map);
        String actual = smap.toString();
        String expected = "SMap(foo -> 123, bar -> 456)";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void minus_A$ObjectArray() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("foo", 123);
        map.put("bar", 456);
        map.put("baz", 789);
        SMap<String, Integer> smap = SMap.apply(map);
        SMap<String, Integer> actual = smap.minus("foo", "bar");
        assertThat(actual.toMap().size(), is(1));
        assertThat(actual.getOrElse("baz", -1), is(789));

        assertThat(map.size(), is(3));
    }

    @Test
    public void minus_A$ObjectArray_SkipNullKeys() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("foo", 123);
        map.put("bar", 456);
        map.put("baz", 789);
        SMap<String, Integer> smap = SMap.apply(map);
        SMap<String, Integer> actual = smap.minus("foo", null, "bar");
        assertThat(actual.toMap().size(), is(1));
        assertThat(actual.getOrElse("baz", -1), is(789));

        assertThat(map.size(), is(3));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void plus_A$Tuple2Array_SkipNullTupleAndNullKeyOrValue() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("foo", 123);
        SMap<String, Integer> smap = SMap.apply(map);
        Tuple2<String, Integer>[] elems = new Tuple2[] { Tpl.apply("bar", 456), null, Tpl.apply(null, 888), Tpl.apply("aaa", null),
                Tpl.apply("baz", 789) };
        SMap<String, Integer> actual = smap.plus(elems);
        assertThat(actual.toMap().size(), is(3));
        assertThat(actual.getOrElse("foo", -1), is(123));
        assertThat(actual.getOrElse("bar", -1), is(456));
        assertThat(actual.getOrElse("baz", -1), is(789));

        assertThat(map.size(), is(1));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void plus_A$Tuple2Array() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("foo", 123);
        SMap<String, Integer> smap = SMap.apply(map);
        SMap<String, Integer> actual = smap.plus(Tpl.apply("bar", 456), Tpl.apply("baz", 789));
        assertThat(actual.toMap().size(), is(3));
        assertThat(actual.getOrElse("foo", -1), is(123));
        assertThat(actual.getOrElse("bar", -1), is(456));
        assertThat(actual.getOrElse("baz", -1), is(789));

        assertThat(map.size(), is(1));
    }

    @Test
    public void updated_A$Object$Object() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("foo", 123);
        SMap<String, Integer> smap = SMap.apply(map);
        SMap<String, Integer> actual = smap.updated("foo", 234).updated("bar", 456);
        assertThat(actual.toMap().size(), is(2));
        assertThat(actual.getOrElse("foo", -1), is(234));
        assertThat(actual.getOrElse("bar", -1), is(456));

        assertThat(map.size(), is(1));
    }

    @Test
    public void apply_A$Object_Exists() throws Exception {
        SMap<String, Integer> map = SMap.<String, Integer> apply().updated("foo", 123).updated("bar", 234);
        Integer v = map.apply("foo");
        assertThat(v, is(equalTo(123)));
    }

    @Test
    public void apply_A$Object_NotExists() throws Exception {
        SMap<String, Integer> map = SMap.<String, Integer> apply().updated("foo", 123).updated("bar", 234);
        Integer v = map.apply("baz");
        assertThat(v, is(nullValue()));
    }

    @Test
    public void __A$Object_Exists() throws Exception {
        SMap<String, Integer> map = SMap.<String, Integer> apply().updated("foo", 123).updated("bar", 234);
        Integer v = map.apply("foo");
        assertThat(v, is(equalTo(123)));
    }

    @Test
    public void __A$Object_NotExists() throws Exception {
        SMap<String, Integer> map = SMap.<String, Integer> apply().updated("foo", 123).updated("bar", 234);
        Integer v = map.apply("baz");
        assertThat(v, is(nullValue()));
    }

}
