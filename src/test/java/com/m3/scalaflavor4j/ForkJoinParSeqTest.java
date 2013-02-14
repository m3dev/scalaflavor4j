package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class ForkJoinParSeqTest {

    List<String> emptyList = new LinkedList<String>();

    List<String> list = new LinkedList<String>(Arrays.asList("foo", null, "bar", "fooo", null, "barbar", "baz",
            "bazbaz", "fuuu", "mmmmm", "dddddd", "m3", "Japan", "foooooo", "dfsdfzzzz", "foo", null, "bar", "fooo",
            null, "barbar", "baz", "bazbaz", "fuuu", "mmmmm", "dddddd", "m3", "Japan", "foooooo", "dfsdfzzzz", "foo",
            null, "bar", "fooo", null, "barbar", "baz", "bazbaz", "fuuu", "mmmmm", "dddddd", "m3", "Japan", "foooooo",
            "dfsdfzzzz", "foo", null, "bar", "fooo", null, "barbar", "baz", "bazbaz", "fuuu", "mmmmm", "dddddd", "m3",
            "Japan", "foooooo", "dfsdfzzzz"));

    @Test
    public void type() throws Exception {
        assertThat(ForkJoinParSeq.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        ForkJoinParSeq<String> target = new ForkJoinParSeq<String>(list);
        assertThat(target, notNullValue());
    }

    @Test
    public void apply_A$ObjectArray() throws Exception {
        ForkJoinParSeq<String> actual = ForkJoinParSeq.apply("foo", "bar");
        assertThat(actual, is(notNullValue()));
    }

    @Test
    public void __A$ObjectArray() throws Exception {
        ForkJoinParSeq<String> actual = ForkJoinParSeq.apply("foo", "bar");
        assertThat(actual, is(notNullValue()));
    }

    @Test
    public void apply_A$Collection() throws Exception {
        Collection<String> collection_ = list;
        ForkJoinParSeq<String> actual = ForkJoinParSeq.apply(collection_);
        assertThat(actual, is(notNullValue()));
    }

    @Test
    public void __A$Collection() throws Exception {
        Collection<String> collection_ = list;
        ForkJoinParSeq<String> actual = ForkJoinParSeq.apply(collection_);
        assertThat(actual, is(notNullValue()));
    }

    @Test
    public void count_A$Function1_Nil() throws Exception {
        ForkJoinParSeq<String> target = ForkJoinParSeq.apply(emptyList);
        int actual = target.count(new F1<String, Boolean>() {
            public Boolean apply(String v1) {
                return v1 != null && v1.contains("f");
            }
        });
        assertThat(actual, is(equalTo(0)));
    }

    @Test
    public void count_A$Function1() throws Exception {
        ForkJoinParSeq<String> target = ForkJoinParSeq.apply(list);
        int actual = target.count(new F1<String, Boolean>() {
            public Boolean apply(String v1) {
                return v1 != null && v1.contains("f");
            }
        });
        assertThat(actual, is(equalTo(20)));
    }

    @Test
    public void exists_A$Function1_Nil() throws Exception {
        ForkJoinParSeq<String> target = ForkJoinParSeq.apply(emptyList);
        boolean actual = target.exists(new F1<String, Boolean>() {
            public Boolean apply(String v1) {
                return v1 != null && v1.contains("f");
            }
        });
        assertThat(actual, is(false));
    }

    @Test
    public void exists_A$Function1() throws Exception {
        ForkJoinParSeq<String> target = ForkJoinParSeq.apply(list);
        boolean actual = target.exists(new F1<String, Boolean>() {
            public Boolean apply(String v1) {
                return v1 != null && v1.contains("f");
            }
        });
        assertThat(actual, is(true));
    }

    @Test
    public void filter_A$Function1_Nil() throws Exception {
        ForkJoinParSeq<String> target = ForkJoinParSeq.apply(emptyList);
        ParSeq<String> actual = target.filter(new F1<String, Boolean>() {
            public Boolean apply(String v1) {
                return v1 != null && v1.contains("f");
            }
        });
        assertThat(actual.toSeq().size(), is(equalTo(0)));
    }

    @Test
    public void filter_A$Function1() throws Exception {
        ForkJoinParSeq<String> target = ForkJoinParSeq.apply(list);
        ParSeq<String> actual = target.filter(new F1<String, Boolean>() {
            public Boolean apply(String v1) {
                return v1 != null && v1.contains("f");
            }
        });
        assertThat(actual.toSeq().size(), is(equalTo(20)));
    }

    @Test
    public void filterNot_A$Function1_Nil() throws Exception {
        ForkJoinParSeq<String> target = ForkJoinParSeq.apply(emptyList);
        ParSeq<String> actual = target.filterNot(new F1<String, Boolean>() {
            public Boolean apply(String v1) {
                return v1 != null && v1.contains("f");
            }
        });
        assertThat(actual.toSeq().size(), is(equalTo(0)));
    }

    @Test
    public void filterNot_A$Function1() throws Exception {
        ForkJoinParSeq<String> target = ForkJoinParSeq.apply(list);
        ParSeq<String> actual = target.filterNot(new F1<String, Boolean>() {
            public Boolean apply(String v1) {
                return v1 != null && v1.contains("f");
            }
        });
        assertThat(actual.toSeq().size(), is(equalTo(32)));
    }

    @Test
    public void flatMap_A$Function1_Nil() throws Exception {
        ForkJoinParSeq<String> target = ForkJoinParSeq.apply(emptyList);
        ParSeq<String> actual = target.flatMap(new FlatMapF1<String, String>() {
            public CollectionLike<String> apply(String v1) throws Exception {
                return Option.apply(v1);
            }
        });
        assertThat(actual.toSeq().size(), is(equalTo(0)));
    }

    @Test
    public void flatMap_A$Function1() throws Exception {
        ForkJoinParSeq<String> target = ForkJoinParSeq.apply(list);
        ParSeq<String> actual = target.flatMap(new FlatMapF1<String, String>() {
            public CollectionLike<String> apply(String v1) throws Exception {
                return Option.apply(v1);
            }
        });
        assertThat(actual.toSeq().size(), is(equalTo(52)));
    }

    @Test
    public void forall_A$Function1_Nil() throws Exception {
        ForkJoinParSeq<String> target = ForkJoinParSeq.apply(emptyList);
        boolean actual = target.forall(new F1<String, Boolean>() {
            public Boolean apply(String v1) {
                return v1 != null && v1.contains("f");
            }
        });
        assertThat(actual, is(true));
    }

    @Test
    public void forall_A$Function1() throws Exception {
        {
            ForkJoinParSeq<String> target = ForkJoinParSeq.apply(list);
            boolean actual = target.forall(new F1<String, Boolean>() {
                public Boolean apply(String v1) {
                    return v1 != null && v1.contains("f");
                }
            });
            assertThat(actual, is(false));
        }
        {
            ForkJoinParSeq<String> target = ForkJoinParSeq.apply("foo", "fuga", "foofoo");
            boolean actual = target.forall(new F1<String, Boolean>() {
                public Boolean apply(String v1) {
                    return v1 != null && v1.contains("f");
                }
            });
            assertThat(actual, is(true));
        }
    }

    @Test
    public void groupBy_A$Function1_Nil() throws Exception {
        ForkJoinParSeq<String> target = ForkJoinParSeq.apply(emptyList);
        SMap<Integer, Seq<String>> actual = target.groupBy(new F1<String, Integer>() {
            public Integer apply(String v1) throws Exception {
                return v1 == null ? 0 : v1.length();
            }
        });
        Map<Integer, Seq<String>> map = actual.toMap();
        assertThat(map, notNullValue());
    }

    @Test
    public void groupBy_A$Function1() throws Exception {
        ForkJoinParSeq<String> target = ForkJoinParSeq.apply(list);
        SMap<Integer, Seq<String>> actual = target.groupBy(new F1<String, Integer>() {
            public Integer apply(String v1) throws Exception {
                return v1 == null ? 0 : v1.length();
            }
        });
        Map<Integer, Seq<String>> map = actual.toMap();
        assertThat(map.get(0).mkString(","), is(equalTo("null,null,null,null,null,null,null,null")));
        assertThat(map.get(2).mkString(","), is(equalTo("m3,m3,m3,m3")));
        assertThat(map.get(3).mkString(","), is(equalTo("foo,bar,baz,foo,bar,baz,foo,bar,baz,foo,bar,baz")));
        assertThat(map.get(4).mkString(","), is(equalTo("fooo,fuuu,fooo,fuuu,fooo,fuuu,fooo,fuuu")));
        assertThat(map.get(7).mkString(","), is(equalTo("foooooo,foooooo,foooooo,foooooo")));
        assertThat(map.get(5).mkString(","), is(equalTo("mmmmm,Japan,mmmmm,Japan,mmmmm,Japan,mmmmm,Japan")));
        assertThat(map.get(6).mkString(","),
                is(equalTo("barbar,bazbaz,dddddd,barbar,bazbaz,dddddd,barbar,bazbaz,dddddd,barbar,bazbaz,dddddd")));
        assertThat(map.get(9).mkString(","), is(equalTo("dfsdfzzzz,dfsdfzzzz,dfsdfzzzz,dfsdfzzzz")));
    }

    @Test
    public void map_A$Function1_Nil() throws Exception {
        ForkJoinParSeq<String> target = ForkJoinParSeq.apply(emptyList);
        ParSeq<Integer> actual = target.map(new F1<String, Integer>() {
            public Integer apply(String v1) throws Exception {
                return v1 == null ? 0 : v1.length();
            }
        });
        assertThat(actual.toSeq().mkString(","), is(equalTo("")));
    }

    @Test
    public void map_A$Function1() throws Exception {
        ForkJoinParSeq<String> target = ForkJoinParSeq.apply(list);
        ParSeq<Integer> actual = target.map(new F1<String, Integer>() {
            public Integer apply(String v1) throws Exception {
                return v1 == null ? 0 : v1.length();
            }
        });
        assertThat(
                actual.toSeq().mkString(","),
                is(equalTo("3,0,3,4,0,6,3,6,4,5,6,2,5,7,9,3,0,3,4,0,6,3,6,4,5,6,2,5,7,9,3,0,3,4,0,6,3,6,4,5,6,2,5,7,9,3,0,3,4,0,6,3,6,4,5,6,2,5,7,9")));
    }

    @Test
    public void toSeq_A$_Nil() throws Exception {
        ForkJoinParSeq<String> target = ForkJoinParSeq.apply(emptyList);
        Seq<String> actual = target.toSeq();
        assertThat(actual.mkString(","), is(equalTo("")));
    }

    @Test
    public void toSeq_A$() throws Exception {
        ForkJoinParSeq<String> target = ForkJoinParSeq.apply(list);
        Seq<String> actual = target.toSeq();
        assertThat(
                actual.mkString(","),
                is(equalTo("foo,null,bar,fooo,null,barbar,baz,bazbaz,fuuu,mmmmm,dddddd,m3,Japan,foooooo,dfsdfzzzz,foo,null,bar,fooo,null,barbar,baz,bazbaz,fuuu,mmmmm,dddddd,m3,Japan,foooooo,dfsdfzzzz,foo,null,bar,fooo,null,barbar,baz,bazbaz,fuuu,mmmmm,dddddd,m3,Japan,foooooo,dfsdfzzzz,foo,null,bar,fooo,null,barbar,baz,bazbaz,fuuu,mmmmm,dddddd,m3,Japan,foooooo,dfsdfzzzz")));
    }

    @Test
    public void foreach_A$VoidFunction1_Nil() throws Exception {
        Seq.<Integer> apply().par().foreach(new VoidF1<Integer>() {
            public void apply(Integer each) throws Exception {
                Thread.sleep(1L);
                System.out.print(Thread.currentThread().getId() + ",");
            }
        });
        Thread.sleep(100L);
    }

    @Test
    public void foreach_A$VoidFunction1() throws Exception {
        System.out.println("-----");
        SInt.apply(1).to(100).par().foreach(new VoidF1<Integer>() {
            public void apply(Integer each) throws Exception {
                Thread.sleep(1L);
                System.out.print(Thread.currentThread().getId() + ",");
            }
        });
        Thread.sleep(500L);
        System.out.println("");
        System.out.println("-----");
    }

    @Test
    public void isEmpty_A$() throws Exception {
        assertThat(ForkJoinParSeq.apply(emptyList).isEmpty(), is(true));
        assertThat(ForkJoinParSeq.apply(list).isEmpty(), is(false));
    }

    @Test
    public void toList_A$() throws Exception {
        assertThat(ForkJoinParSeq.apply(emptyList).toList().size(), is(equalTo(0)));
        assertThat(ForkJoinParSeq.apply(list).toList().size() > 0, is(true));
    }

}
