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
        SMap<String, Integer> smap = SMap._(map);
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
        SMap<String, Integer> smap = SMap._(map);
        assertThat(smap.isEmpty(), is(false));
    }

    @Test
    public void toList_A$() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("foo", 123);
        map.put("bar", 456);
        SMap<String, Integer> smap = SMap._(map);
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
        SMap<String, Integer> smap = SMap._(map);
        boolean actual = smap.isEmpty();
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void filter_A$Function1() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("foo", 123);
        map.put("bar", 456);
        SMap<String, Integer> smap = SMap._(map);
        SMap<String, Integer> filtered = smap.filter(new F1<Tuple2<String, Integer>, Boolean>() {
            public Boolean _(Tuple2<String, Integer> v1) {
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
        SMap<String, Integer> smap = SMap._(map);
        smap.foreach(new VoidF1<Tuple2<String, Integer>>() {
            public void _(Tuple2<String, Integer> v1) {
                System.out.println(v1._1() + ":" + v1._2());
            }
        });
    }

    @Test
    public void map_A$Function1() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("foo", 123);
        map.put("bar", 456);
        SMap<String, Integer> smap = SMap._(map);
        SMap<String, String> newMap = smap.map(new F1<Tuple2<String, Integer>, Tuple2<String, String>>() {
            public Tuple2<String, String> _(Tuple2<String, Integer> v1) {
                return Tuple2._(v1._1(), v1._2().toString());
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
        SMap<String, Integer> smap = SMap._(map);
        Map<String, Integer> actual = smap.toMap();
        assertThat(actual, is(notNullValue()));
    }

    @Test
    public void iterator_A$() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("foo", 123);
        map.put("bar", 456);
        SMap<String, Integer> smap = SMap._(map);
        Iterator<Tuple2<String, Integer>> actual = smap.iterator();
        assertThat(actual, is(notNullValue()));
    }

    @Test
    public void getOrElse_A$Object$Object() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("foo", 123);
        map.put("bar", 456);
        SMap<String, Integer> smap = SMap._(map);
        Integer actual = smap.getOrElse("foo", 999);
        Integer expected = 123;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void unzip_A$() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("foo", 123);
        map.put("bar", 456);
        SMap<String, Integer> smap = SMap._(map);
        Tuple2<Seq<String>, Seq<Integer>> actual = smap.unzip();
        assertThat(actual._1().mkString("-"), is(equalTo("foo-bar")));
        assertThat(actual._2().mkString("-"), is(equalTo("123-456")));
    }

    @Test
    public void apply_A$Seq() throws Exception {
        @SuppressWarnings("unchecked")
        SMap<String, Integer> actual = SMap.apply(Seq._(Tuple2._("foo", 123), Tuple2._("bar", 234),
                Tuple2._("baz", 345)));
        assertThat(actual.toMap().get("foo"), is(equalTo(123)));
        assertThat(actual.toMap().get("bar"), is(equalTo(234)));
        assertThat(actual.toMap().get("baz"), is(equalTo(345)));
    }

    @Test
    public void __A$Seq() throws Exception {
        @SuppressWarnings("unchecked")
        SMap<String, Integer> actual = SMap._(Seq._(Tuple2._("foo", 123), Tuple2._("bar", 234), Tuple2._("baz", 345)));
        assertThat(actual.toMap().get("foo"), is(equalTo(123)));
        assertThat(actual.toMap().get("bar"), is(equalTo(234)));
        assertThat(actual.toMap().get("baz"), is(equalTo(345)));
    }

    @Test
    public void toSeq_A$() throws Exception {
        @SuppressWarnings("unchecked")
        SMap<String, Integer> map = SMap._(Seq._(Tuple2._("foo", 123), Tuple2._("bar", 234), Tuple2._("baz", 345)));
        Seq<Tuple2<String, Integer>> actual = map.toSeq();
        assertThat(actual.toList().size(), is(equalTo(3)));
    }

    @Test
    public void __A$() throws Exception {
        SMap<String, Integer> actual = SMap._();
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
    public void update_A$Object$Object() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("foo", 123);
        map.put("bar", 234);
        map.put("baz", 345);
        SMap<String, Integer> smap = SMap.apply(map);
        String key = "bar";
        Integer value = 999;
        SMap<String, Integer> after = smap.update(key, value);
        assertThat(after.getOrElse("foo", 0), is(equalTo(123)));
        assertThat(after.getOrElse("bar", 0), is(equalTo(999)));
        assertThat(after.getOrElse("baz", 0), is(equalTo(345)));
    }

    @Test
    public void toString_A$() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("foo", 123);
        map.put("bar", 456);
        SMap<String, Integer> smap = SMap._(map);
        String actual = smap.toString();
        String expected = "SMap(foo -> 123, bar -> 456)";
        assertThat(actual, is(equalTo(expected)));
    }

}
