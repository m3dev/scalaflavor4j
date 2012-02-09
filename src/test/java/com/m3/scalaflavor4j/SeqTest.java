package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.junit.Test;

public class SeqTest {

    List<String> emptyList = new ArrayList<String>();

    List<String> list = Arrays.asList("foo", null, "bar");

    @Test
    public void type() throws Exception {
        assertThat(Seq.class, notNullValue());
    }

    @Test
    public void toList_A$_Nil() throws Exception {
        Seq<String> seq = Seq._(emptyList);
        List<String> actual = seq.toList();
        assertThat(actual.isEmpty(), is(true));
        assertThat(actual.size(), is(0));
    }

    @Test
    public void toList_A$() throws Exception {
        Seq<String> seq = Seq._(list);
        List<String> actual = seq.toList();
        assertThat(actual.isEmpty(), is(false));
        assertThat(actual.size(), is(3));
    }

    @Test
    public void isEmpty_A$_Nil() throws Exception {
        Seq<String> seq = Seq._(emptyList);
        boolean actual = seq.isEmpty();
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void isEmpty_A$() throws Exception {
        Seq<String> seq = Seq._(list);
        boolean actual = seq.isEmpty();
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void map_A$Function1_Nil() throws Exception {
        Seq<String> seq = Seq._(emptyList);
        Seq<Integer> actual = seq.map(new F1<String, Integer>() {
            public Integer _(String v1) {
                return v1.length();
            }
        });
        assertThat(actual.isEmpty(), is(true));
        assertThat(actual.toList().size(), is(0));
    }

    @Test
    public void map_A$Function1() throws Exception {
        F1<String, Integer> f = new F1<String, Integer>() {
            public Integer _(String v1) {
                return v1 == null ? 0 : v1.length();
            }
        };
        Seq<String> seq = Seq._(list);
        Seq<Integer> actual = seq.map(f);
        assertThat(actual.isEmpty(), is(false));
        assertThat(actual.toList().size(), is(3));
    }

    @Test
    public void foreach_A$VoidFunction1() throws Exception {
        VoidF1<String> f = new VoidF1<String>() {
            public void _(String v1) {
                System.out.println(v1);
            }
        };
        Seq._(emptyList).foreach(f);
        Seq._(list).foreach(f);
    }

    @Test
    public void flatMap_A$Function1_i() throws Exception {
        F1<String, CollectionLike<Integer>> f = new F1<String, CollectionLike<Integer>>() {
            public Option<Integer> _(String v1) {
                return Option._(v1.length());
            }
        };
        Seq<Integer> actual = Seq._(emptyList).flatMap(f);
        assertThat(actual.isEmpty(), is(true));
        assertThat(actual.toList().size(), is(0));
    }

    @Test
    public void flatMap_A$Function1() throws Exception {
        F1<String, CollectionLike<Integer>> f = new F1<String, CollectionLike<Integer>>() {
            public Option<Integer> _(String v1) {
                return v1 == null ? Option._(0) : Option._(v1.length());
            }
        };
        Seq<Integer> actual = Seq._(list).flatMap(f);
        assertThat(actual.isEmpty(), is(false));
        assertThat(actual.toList().size(), is(3));
    }

    @Test
    public void filter_A$Function1_Nil() throws Exception {
        F1<String, Boolean> f = new F1<String, Boolean>() {
            public Boolean _(String v1) {
                return v1.contains("f");
            }
        };
        Seq<String> actual = Seq._(emptyList).filter(f);
        assertThat(actual.toList().size(), is(equalTo(0)));
    }

    @Test
    public void filter_A$Function1() throws Exception {
        F1<String, Boolean> f = new F1<String, Boolean>() {
            public Boolean _(String v1) {
                return v1 == null ? false : v1.contains("f");
            }
        };
        Seq<String> actual = Seq._(list).filter(f);
        assertThat(actual.toList().size(), is(equalTo(1)));
    }

    @Test
    public void filterNot_A$Function1_Nil() throws Exception {
        F1<String, Boolean> f = new F1<String, Boolean>() {
            public Boolean _(String v1) {
                return v1.contains("b");
            }
        };
        Seq<String> actual = Seq._(emptyList).filterNot(f);
        assertThat(actual.toList().size(), is(equalTo(0)));
    }

    @Test
    public void filterNot_A$Function1() throws Exception {
        F1<String, Boolean> f = new F1<String, Boolean>() {
            public Boolean _(String v1) {
                return v1.contains("b");
            }
        };
        Seq<String> actual = Seq._("aaa", "bbb", "ccc").filterNot(f);
        assertThat(actual.toList().size(), is(equalTo(2)));
    }

    @Test
    public void head_A$_Nil() throws Exception {
        String actual = Seq._(emptyList).head();
        assertThat(actual, is(nullValue()));
    }

    @Test
    public void head_A$() throws Exception {
        String actual = Seq._(list).head();
        String expected = "foo";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void tail_A$_Nil() throws Exception {
        Seq<String> actual = Seq._(emptyList).tail();
        assertThat(actual.isEmpty(), is(true));
        assertThat(actual.toList().size(), is(0));
    }

    @Test
    public void tail_A$() throws Exception {
        Seq<String> actual = Seq._(list).tail();
        assertThat(actual.isEmpty(), is(false));
        assertThat(actual.toList().size(), is(2));
    }

    @Test
    public void mkString_A$String_Nil() throws Exception {
        Seq<String> seq = Seq._(emptyList);
        String sep = "-";
        String actual = seq.mkString(sep);
        String expected = "";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void mkString_A$String() throws Exception {
        Seq<String> seq = Seq._(list);
        String sep = "-";
        String actual = seq.mkString(sep);
        String expected = "foo-null-bar";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void sortWith_A$Function2_Nil() throws Exception {
        F2<Integer, Integer, Boolean> f = new F2<Integer, Integer, Boolean>() {
            public Boolean _(Integer v1, Integer v2) {
                return v1 < v2;
            }
        };
        Seq<Integer> seq = Seq._();
        List<Integer> sorted = seq.sortWith(f).toList();
        assertThat(sorted.isEmpty(), is(true));
        assertThat(sorted.size(), is(equalTo(0)));
    }

    @Test
    public void sortWith_A$Function2() throws Exception {
        F2<Integer, Integer, Boolean> f = new F2<Integer, Integer, Boolean>() {
            public Boolean _(Integer v1, Integer v2) {
                return v1 < v2;
            }
        };
        Seq<Integer> seq = Seq._(3, 4, 2, 0, 5, 1);
        List<Integer> sorted = seq.sortWith(f).toList();
        assertThat(sorted.get(0), is(equalTo(0)));
        assertThat(sorted.get(1), is(equalTo(1)));
        assertThat(sorted.get(2), is(equalTo(2)));
        assertThat(sorted.get(3), is(equalTo(3)));
        assertThat(sorted.get(4), is(equalTo(4)));
        assertThat(sorted.get(5), is(equalTo(5)));
    }

    @Test
    public void take_A$int_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        int n = 3;
        Seq<Integer> actual = seq.take(n);
        assertThat(actual.size(), is(equalTo(0)));
    }

    @Test
    public void take_A$int() throws Exception {
        Seq<Integer> seq = Seq._(3, 4, 2, 0, 5, 1);
        int n = 3;
        Seq<Integer> actual = seq.take(n);
        assertThat(actual.toList().size(), is(equalTo(3)));
        assertThat(actual.toList().get(0), is(equalTo(3)));
        assertThat(actual.toList().get(1), is(equalTo(4)));
        assertThat(actual.toList().get(2), is(equalTo(2)));
    }

    @Test
    public void foldLeft_A$Object$Function2_None() throws Exception {
        Seq<Integer> seq = Seq._();
        String actual = seq.foldLeft("foo", new F2<String, Integer, String>() {
            public String _(String v1, Integer v2) {
                return v1 + v2;
            }
        });
        assertThat(actual, is("foo"));
    }

    @Test
    public void foldLeft_A$Object$Function2_1() throws Exception {
        Seq<Integer> seq = Seq._(3, 4, 2, 0, 5, 1);
        String actual = seq.foldLeft("foo", new F2<String, Integer, String>() {
            public String _(String v1, Integer v2) {
                return v1 + v2;
            }
        });
        assertThat(actual, is("foo342051"));
    }

    @Test
    public void foldLeft_A$Object$Function2_2() throws Exception {
        Seq<Integer> seq = Seq._(3, 4, 2, 0, 5, 1);
        Integer actual = seq.foldLeft(0, new F2<Integer, Integer, Integer>() {
            public Integer _(Integer v1, Integer v2) {
                return v1 + v2;
            }
        });
        assertThat(actual, is(15));
    }

    @Test
    public void zipWithIndex_A$_Nil() throws Exception {
        Seq<String> seq = Seq._();
        Seq<Tuple2<String, Integer>> actual = seq.zipWithIndex();
        assertThat(actual.isEmpty(), is(true));
        assertThat(actual.size(), is(0));
    }

    @Test
    public void zipWithIndex_A$() throws Exception {
        Seq<String> seq = Seq._("A", "B", "C");
        Seq<Tuple2<String, Integer>> actual = seq.zipWithIndex();
        assertThat(actual.toList().size(), is(3));
        assertThat(actual.toList().get(0)._1(), is("A"));
        assertThat(actual.toList().get(0)._2(), is(0));
        assertThat(actual.toList().get(1)._1(), is("B"));
        assertThat(actual.toList().get(1)._2(), is(1));
        assertThat(actual.toList().get(2)._1(), is("C"));
        assertThat(actual.toList().get(2)._2(), is(2));
    }

    @Test
    public void foldRight_A$Object$Function2_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        String actual = seq.foldRight("foo", new F2<Integer, String, String>() {
            public String _(Integer v1, String v2) {
                return v1 + v2;
            }
        });
        assertThat(actual, is("foo"));
    }

    @Test
    public void foldRight_A$Object$Function2() throws Exception {
        Seq<Integer> seq = Seq._(3, 4, 2, 0, 5, 1);
        String actual = seq.foldRight("foo", new F2<Integer, String, String>() {
            public String _(Integer v1, String v2) {
                return v1 + v2;
            }
        });
        assertThat(actual, is("342051foo"));
    }

    @Test
    public void headOption_A$_Nil() throws Exception {
        Seq<String> seq = Seq._();
        Option<String> actual = seq.headOption();
        assertThat(actual.isDefined(), is(false));
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void headOption_A$_none() throws Exception {
        Seq<String> seq = Seq._(new ArrayList<String>());
        Option<String> actual = seq.headOption();
        assertThat(actual.isDefined(), is(false));
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void headOption_A$() throws Exception {
        List<String> list = new ArrayList<String>();
        list.add("foo");
        Seq<String> seq = Seq._(list);
        Option<String> actual = seq.headOption();
        assertThat(actual.isDefined(), is(true));
        assertThat(actual.getOrNull(), is(equalTo("foo")));
    }

    @Test
    public void distinct_A$_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        Seq<Integer> actual = seq.distinct();
        assertThat(actual.size(), is(equalTo(0)));
    }

    @Test
    public void distinct_A$() throws Exception {
        List<Integer> list = Arrays.asList(1, 3, 2, 2, 3, 4, 5, 2, 3, 2, 5);
        Seq<Integer> seq = Seq._(list);
        Seq<Integer> actual = seq.distinct();
        assertThat(actual.size(), is(equalTo(5)));
        assertThat(actual.toList().get(0), is(equalTo(1)));
        assertThat(actual.toList().get(1), is(equalTo(3)));
        assertThat(actual.toList().get(2), is(equalTo(2)));
        assertThat(actual.toList().get(3), is(equalTo(4)));
        assertThat(actual.toList().get(4), is(equalTo(5)));
    }

    @Test
    public void size_A$_Nil() throws Exception {
        Seq<String> seq = Seq._();
        Integer actual = seq.size();
        Integer expected = 0;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void size_A$() throws Exception {
        List<String> list = Arrays.asList("a", "b", "c");
        Seq<String> seq = Seq._(list);
        Integer actual = seq.size();
        Integer expected = 3;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void groupBy_A$Function1_Nil() throws Exception {
        Seq<String> seq = Seq._();
        SMap<String, Seq<String>> actual = seq.groupBy(new F1<String, String>() {
            public String _(String v1) {
                return v1;
            }
        });
        assertThat(actual, is(notNullValue()));
        assertThat(actual.toMap().size(), is(equalTo(0)));
    }

    @Test
    public void groupBy_A$Function1_1() throws Exception {
        List<String> list = Arrays.asList("a", "b", "a", "a", "c", "b");
        Seq<String> seq = Seq._(list);
        SMap<String, Seq<String>> actual = seq.groupBy(new F1<String, String>() {
            public String _(String v1) {
                return v1;
            }
        });
        assertThat(actual, is(notNullValue()));
        assertThat(actual.toMap().size(), is(equalTo(3)));
        assertThat(actual.toMap().get("a").size(), is(equalTo(3)));
        assertThat(actual.toMap().get("b").size(), is(equalTo(2)));
        assertThat(actual.toMap().get("c").size(), is(equalTo(1)));
    }

    @Test
    public void groupBy_A$Function1_2() throws Exception {
        List<String> list = Arrays.asList("foo", "bar", "foofoo");
        Seq<String> seq = Seq._(list);
        SMap<Integer, Seq<String>> actual = seq.groupBy(new F1<String, Integer>() {
            public Integer _(String v1) {
                return v1.length();
            }
        });
        assertThat(actual, is(notNullValue()));
        assertThat(actual.toMap().size(), is(equalTo(2)));
        assertThat(actual.toMap().get(3).size(), is(equalTo(2)));
        assertThat(actual.toMap().get(6).size(), is(equalTo(1)));
    }

    @Test
    public void last_A$_Nil() throws Exception {
        Seq<String> seq = Seq._();
        String actual = seq.last();
        assertThat(actual, is(nullValue()));
    }

    @Test
    public void last_A$() throws Exception {
        List<String> list = Arrays.asList("a", "b", "a", "a", "c", "z");
        Seq<String> seq = Seq._(list);
        String actual = seq.last();
        String expected = "z";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void lastOption_A$_Nil() throws Exception {
        Seq<String> seq = Seq._();
        Option<String> actual = seq.lastOption();
        assertThat(actual.isDefined(), is(false));
    }

    @Test
    public void lastOption_A$() throws Exception {
        List<String> list = Arrays.asList("a", "b", "a", "a", "c", "z");
        Seq<String> seq = Seq._(list);
        Option<String> actual = seq.lastOption();
        assertThat(actual.getOrNull(), is(equalTo("z")));
    }

    @Test
    public void find_A$Function1_Nil() throws Exception {
        Seq<String> seq = Seq._();
        F1<String, Boolean> p = new F1<String, Boolean>() {
            public Boolean _(String v1) {
                return v1.equals("c");
            }
        };
        Option<String> actual = seq.find(p);
        assertThat(actual.isDefined(), is(false));
    }

    @Test
    public void find_A$Function1() throws Exception {
        List<String> list = Arrays.asList("a", "b", "a", "a", "c", "z");
        Seq<String> seq = Seq._(list);
        F1<String, Boolean> p = new F1<String, Boolean>() {
            public Boolean _(String v1) {
                return v1.equals("c");
            }
        };
        Option<String> actual = seq.find(p);
        assertThat(actual.getOrNull(), is(equalTo("c")));
    }

    @Test
    public void partition_A$Function1_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        Tuple2<Seq<Integer>, Seq<Integer>> actual = seq.partition(new F1<Integer, Boolean>() {
            public Boolean _(Integer v1) {
                return v1 < 3;
            }
        });
        assertThat(actual._1().mkString(""), is(equalTo("")));
        assertThat(actual._2().mkString(""), is(equalTo("")));
    }

    @Test
    public void partition_A$Function1() throws Exception {
        List<Integer> list = Arrays.asList(1, 5, 2, 3, 4);
        Seq<Integer> seq = Seq._(list);
        Tuple2<Seq<Integer>, Seq<Integer>> actual = seq.partition(new F1<Integer, Boolean>() {
            public Boolean _(Integer v1) {
                return v1 < 3;
            }
        });
        assertThat(actual._1().mkString(""), is(equalTo("12")));
        assertThat(actual._2().mkString(""), is(equalTo("534")));
    }

    @Test
    public void reverse_A$_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        Seq<Integer> actual = seq.reverse();
        assertThat(actual.mkString(""), is(equalTo("")));
    }

    @Test
    public void reverse_A$() throws Exception {
        List<Integer> list = Arrays.asList(1, 5, 2, 3, 4);
        Seq<Integer> seq = Seq._(list);
        Seq<Integer> actual = seq.reverse();
        assertThat(actual.mkString(""), is(equalTo("43251")));
    }

    @Test
    public void slice_A$int$int_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        Integer from = 2;
        Integer until = 4;
        Seq<Integer> actual = seq.slice(from, until);
        assertThat(actual.size(), is(equalTo(0)));
    }

    @Test
    public void slice_A$int$int() throws Exception {
        List<Integer> list = Arrays.asList(1, 5, 2, 3, 4);
        Seq<Integer> seq = Seq._(list);
        Integer from = 2;
        Integer until = 4;
        Seq<Integer> actual = seq.slice(from, until);
        assertThat(actual.size(), is(equalTo(2)));
        assertThat(actual.toList().get(0), is(equalTo(2)));
        assertThat(actual.toList().get(1), is(equalTo(3)));
    }

    @Test
    public void sliding_A$int_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        Integer size = 3;
        Seq<Seq<Integer>> actual = seq.sliding(size);
        assertThat(actual.size(), is(equalTo(0)));
    }

    @Test
    public void sliding_A$int_3() throws Exception {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Seq<Integer> seq = Seq._(list);
        Integer size = 3;
        Seq<Seq<Integer>> actual = seq.sliding(size);
        assertThat(actual.size(), is(equalTo(3)));
        assertThat(actual.toList().get(0).mkString(""), is(equalTo("123")));
        assertThat(actual.toList().get(1).mkString(""), is(equalTo("234")));
        assertThat(actual.toList().get(2).mkString(""), is(equalTo("345")));
    }

    @Test
    public void sliding_A$int$int_3_2() throws Exception {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Seq<Integer> seq = Seq._(list);
        Integer size = 3;
        Integer step = 2;
        Seq<Seq<Integer>> actual = seq.sliding(size, step);
        assertThat(actual.size(), is(equalTo(2)));
        assertThat(actual.toList().get(0).mkString(""), is(equalTo("123")));
        assertThat(actual.toList().get(1).mkString(""), is(equalTo("345")));
    }

    @Test
    public void sliding_A$int$int_3_3() throws Exception {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Seq<Integer> seq = Seq._(list);
        Integer size = 3;
        Integer step = 3;
        Seq<Seq<Integer>> actual = seq.sliding(size, step);
        assertThat(actual.size(), is(equalTo(2)));
        assertThat(actual.toList().get(0).mkString(""), is(equalTo("123")));
        assertThat(actual.toList().get(1).mkString(""), is(equalTo("45")));
    }

    @Test
    public void sliding_A$int$int_6_3() throws Exception {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Seq<Integer> seq = Seq._(list);
        Integer size = 6;
        Integer step = 3;
        Seq<Seq<Integer>> actual = seq.sliding(size, step);
        assertThat(actual.size(), is(equalTo(1)));
        assertThat(actual.toList().get(0).mkString(""), is(equalTo("12345")));
    }

    @Test
    public void span_A$Function1_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        Tuple2<Seq<Integer>, Seq<Integer>> actual = seq.span(new F1<Integer, Boolean>() {
            public Boolean _(Integer v1) {
                return v1 < 3;
            }
        });
        assertThat(actual._1().mkString(""), is(equalTo("")));
        assertThat(actual._2().mkString(""), is(equalTo("")));
    }

    @Test
    public void span_A$Function1() throws Exception {
        List<Integer> list = Arrays.asList(1, 5, 2, 3, 4);
        Seq<Integer> seq = Seq._(list);
        Tuple2<Seq<Integer>, Seq<Integer>> actual = seq.span(new F1<Integer, Boolean>() {
            public Boolean _(Integer v1) {
                return v1 < 3;
            }
        });
        assertThat(actual._1().mkString(""), is(equalTo("1")));
        assertThat(actual._2().mkString(""), is(equalTo("5234")));
    }

    @Test
    public void takeRight_A$int_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        int n = 3;
        Seq<Integer> actual = seq.takeRight(n);
        assertThat(actual.mkString(""), is(equalTo("")));
    }

    @Test
    public void takeRight_A$int() throws Exception {
        List<Integer> list = Arrays.asList(1, 5, 2, 3, 4);
        Seq<Integer> seq = Seq._(list);
        int n = 3;
        Seq<Integer> actual = seq.takeRight(n);
        assertThat(actual.mkString(""), is(equalTo("234")));
    }

    @Test
    public void takeWhile_A$Function1_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        Seq<Integer> actual = seq.takeWhile(new F1<Integer, Boolean>() {
            public Boolean _(Integer v1) {
                return v1 < 3;
            }
        });
        assertThat(actual.mkString(""), is(equalTo("")));
    }

    @Test
    public void takeWhile_A$Function1() throws Exception {
        List<Integer> list = Arrays.asList(1, 2, 5, 3, 4);
        Seq<Integer> seq = Seq._(list);
        Seq<Integer> actual = seq.takeWhile(new F1<Integer, Boolean>() {
            public Boolean _(Integer v1) {
                return v1 <= 3;
            }
        });
        assertThat(actual.mkString(""), is(equalTo("12")));
    }

    @Test
    public void splitAt_A$int_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        Integer n = 3;
        Tuple2<Seq<Integer>, Seq<Integer>> actual = seq.splitAt(n);
        assertThat(actual._1().mkString(""), is(equalTo("")));
        assertThat(actual._2().mkString(""), is(equalTo("")));
    }

    @Test
    public void splitAt_A$int() throws Exception {
        List<Integer> list = Arrays.asList(1, 5, 2, 3, 4);
        Seq<Integer> seq = Seq._(list);
        Integer n = 3;
        Tuple2<Seq<Integer>, Seq<Integer>> actual = seq.splitAt(n);
        assertThat(actual._1().mkString(""), is(equalTo("152")));
        assertThat(actual._2().mkString(""), is(equalTo("34")));
    }

    @Test
    public void exists_A$Function1_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        boolean actual = seq.exists(new F1<Integer, Boolean>() {
            public Boolean _(Integer v1) {
                return v1 < 3;
            }
        });
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void exists_A$Function1() throws Exception {
        List<Integer> list = Arrays.asList(1, 5, 2, 3, 4);
        Seq<Integer> seq = Seq._(list);
        boolean actual = seq.exists(new F1<Integer, Boolean>() {
            public Boolean _(Integer v1) {
                return v1 < 3;
            }
        });
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void exists_A$Function1_false() throws Exception {
        List<Integer> list = Arrays.asList(1, 5, 2, 3, 4);
        Seq<Integer> seq = Seq._(list);
        boolean actual = seq.exists(new F1<Integer, Boolean>() {
            public Boolean _(Integer v1) {
                return v1 > 6;
            }
        });
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void forall_A$Function1_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        boolean actual = seq.forall(new F1<Integer, Boolean>() {
            public Boolean _(Integer v1) {
                return v1 < 3;
            }
        });
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void forall_A$Function1() throws Exception {
        List<Integer> list = Arrays.asList(1, 5, 2, 3, 4);
        Seq<Integer> seq = Seq._(list);
        boolean actual = seq.forall(new F1<Integer, Boolean>() {
            public Boolean _(Integer v1) {
                return v1 < 3;
            }
        });
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void count_A$Function1_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        Integer actual = seq.count(new F1<Integer, Boolean>() {
            public Boolean _(Integer v1) {
                return v1 < 3;
            }
        });
        Integer expected = 0;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void count_A$Function1() throws Exception {
        List<Integer> list = Arrays.asList(1, 5, 2, 3, 4);
        Seq<Integer> seq = Seq._(list);
        Integer actual = seq.count(new F1<Integer, Boolean>() {
            public Boolean _(Integer v1) {
                return v1 < 3;
            }
        });
        Integer expected = 2;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void drop_A$int_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        int n = 3;
        Seq<Integer> actual = seq.drop(n);
        assertThat(actual.size(), is(equalTo(0)));
    }

    @Test
    public void drop_A$int() throws Exception {
        Seq<Integer> seq = Seq._(3, 4, 2, 0, 5, 1);
        int n = 3;
        Seq<Integer> actual = seq.drop(n);
        assertThat(actual.toList().size(), is(equalTo(3)));
        assertThat(actual.toList().get(0), is(equalTo(0)));
        assertThat(actual.toList().get(1), is(equalTo(5)));
        assertThat(actual.toList().get(2), is(equalTo(1)));
    }

    @Test
    public void dropRight_A$int_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        int n = 3;
        Seq<Integer> actual = seq.dropRight(n);
        assertThat(actual.size(), is(equalTo(0)));
    }

    @Test
    public void dropRight_A$int() throws Exception {
        Seq<Integer> seq = Seq._(3, 4, 2, 0, 5, 1);
        int n = 3;
        Seq<Integer> actual = seq.dropRight(n);
        assertThat(actual.toList().size(), is(equalTo(3)));
        assertThat(actual.toList().get(0), is(equalTo(3)));
        assertThat(actual.toList().get(1), is(equalTo(4)));
        assertThat(actual.toList().get(2), is(equalTo(2)));
    }

    @Test
    public void dropWhile_A$Function1_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        Seq<Integer> actual = seq.dropWhile(new F1<Integer, Boolean>() {
            public Boolean _(Integer v1) {
                return v1 >= 3;
            }
        });
        assertThat(actual.size(), is(equalTo(0)));
    }

    @Test
    public void dropWhile_A$Function1() throws Exception {
        Seq<Integer> seq = Seq._(3, 4, 2, 0, 5, 1);
        Seq<Integer> actual = seq.dropWhile(new F1<Integer, Boolean>() {
            public Boolean _(Integer v1) {
                return v1 >= 3;
            }
        });
        assertThat(actual.toList().size(), is(equalTo(4)));
        assertThat(actual.toList().get(0), is(equalTo(2)));
        assertThat(actual.toList().get(1), is(equalTo(0)));
        assertThat(actual.toList().get(2), is(equalTo(5)));
        assertThat(actual.toList().get(3), is(equalTo(1)));
    }

    @Test
    public void zip_A$Seq_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        Seq<String> that = Seq._("a");
        Seq<Tuple2<Integer, String>> actual = seq.zip(that);
        assertThat(actual.size(), is(equalTo(0)));
    }

    @Test
    public void zip_A$Seq_thisIsLonger() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3, 4, 5);
        Seq<String> that = Seq._("a", "b", "c");
        Seq<Tuple2<Integer, String>> actual = seq.zip(that);
        assertThat(actual.size(), is(equalTo(3)));
        assertThat(actual.toList().get(0)._1(), is(equalTo(1)));
        assertThat(actual.toList().get(0)._2(), is(equalTo("a")));
        assertThat(actual.toList().get(1)._1(), is(equalTo(2)));
        assertThat(actual.toList().get(1)._2(), is(equalTo("b")));
        assertThat(actual.toList().get(2)._1(), is(equalTo(3)));
        assertThat(actual.toList().get(2)._2(), is(equalTo("c")));
    }

    @Test
    public void zip_A$Seq_thatIsLonger() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3);
        Seq<String> that = Seq._("a", "b", "c", "d", "e");
        Seq<Tuple2<Integer, String>> actual = seq.zip(that);
        assertThat(actual.size(), is(equalTo(3)));
        assertThat(actual.toList().get(0)._1(), is(equalTo(1)));
        assertThat(actual.toList().get(0)._2(), is(equalTo("a")));
        assertThat(actual.toList().get(1)._1(), is(equalTo(2)));
        assertThat(actual.toList().get(1)._2(), is(equalTo("b")));
        assertThat(actual.toList().get(2)._1(), is(equalTo(3)));
        assertThat(actual.toList().get(2)._2(), is(equalTo("c")));
    }

    @Test
    public void contains_A$Object_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        Integer elem = 2;
        boolean actual = seq.contains(elem);
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void contains_A$Object_containsNull() throws Exception {
        Seq<Integer> seq = Seq._(null, 1, 2, null, 3);
        Integer elem = 2;
        boolean actual = seq.contains(elem);
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void contains_A$Object_true() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3);
        Integer elem = 2;
        boolean actual = seq.contains(elem);
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void contains_A$Object_false() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3);
        Integer elem = 4;
        boolean actual = seq.contains(elem);
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void diff_A$Seq_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        Seq<Integer> that = Seq._(2, 4);
        Seq<Integer> actual = seq.diff(that);
        assertThat(actual.size(), is(equalTo(0)));
    }

    @Test
    public void diff_A$Seq() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3);
        Seq<Integer> that = Seq._(2, 4);
        Seq<Integer> actual = seq.diff(that);
        assertThat(actual.size(), is(equalTo(2)));
        assertThat(actual.toList().get(0), is(equalTo(1)));
        assertThat(actual.toList().get(1), is(equalTo(3)));
    }

    @Test
    public void diff_A$Seq_containsNull1() throws Exception {
        Seq<Integer> seq = Seq._(null, 1, 2, 3);
        Seq<Integer> that = Seq._(2, 4);
        Seq<Integer> actual = seq.diff(that);
        assertThat(actual.size(), is(equalTo(3)));
        assertThat(actual.toList().get(0), is(equalTo(null)));
        assertThat(actual.toList().get(1), is(equalTo(1)));
        assertThat(actual.toList().get(2), is(equalTo(3)));
    }

    @Test
    public void diff_A$Seq_containsNull2() throws Exception {
        Seq<Integer> seq = Seq._(null, 1, 2, 3);
        Seq<Integer> that = Seq._(2, 4, null);
        Seq<Integer> actual = seq.diff(that);
        assertThat(actual.size(), is(equalTo(2)));
        assertThat(actual.toList().get(0), is(equalTo(1)));
        assertThat(actual.toList().get(1), is(equalTo(3)));
    }

    @Test
    public void startsWith_A$Seq_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        Seq<Integer> that = Seq._(1, 2);
        boolean actual = seq.startsWith(that);
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void startsWith_A$Seq_true() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3);
        Seq<Integer> that = Seq._(1, 2);
        boolean actual = seq.startsWith(that);
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void startsWith_A$Seq_containsNull1() throws Exception {
        Seq<Integer> seq = Seq._(1, null, 2, 3);
        Seq<Integer> that = Seq._(1, 2);
        boolean actual = seq.startsWith(that);
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void startsWith_A$Seq_containsNull2() throws Exception {
        Seq<Integer> seq = Seq._(1, null, 2, 3);
        Seq<Integer> that = Seq._(1, null, 2);
        boolean actual = seq.startsWith(that);
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void startsWith_A$Seq_false() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3);
        Seq<Integer> that = Seq._(1, 4);
        boolean actual = seq.startsWith(that);
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void startsWith_A$Seq$int_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        Seq<Integer> that = Seq._(2, 3);
        int offset = 1;
        boolean actual = seq.startsWith(that, offset);
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void startsWith_A$Seq$int_true() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3);
        Seq<Integer> that = Seq._(2, 3);
        int offset = 1;
        boolean actual = seq.startsWith(that, offset);
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void startsWith_A$Seq$int_false() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3);
        Seq<Integer> that = Seq._(2, 3);
        int offset = 0;
        boolean actual = seq.startsWith(that, offset);
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void startsWith_A$Seq$int_outOfIndex() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3);
        Seq<Integer> that = Seq._(1, 2);
        int offset = 100;
        boolean actual = seq.startsWith(that, offset);
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void endsWith_A$Seq_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        Seq<Integer> that = Seq._(2, 3);
        boolean actual = seq.endsWith(that);
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void endsWith_A$Seq_true() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3);
        Seq<Integer> that = Seq._(2, 3);
        boolean actual = seq.endsWith(that);
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void endsWith_A$Seq_false() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3);
        Seq<Integer> that = Seq._(1, 2);
        boolean actual = seq.endsWith(that);
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void indexOf_A$Object_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        Integer elem = 2;
        int actual = seq.indexOf(elem);
        int expected = -1;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void indexOf_A$Object_exists() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3);
        Integer elem = 2;
        int actual = seq.indexOf(elem);
        int expected = 1;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void indexOf_A$Object_notExists() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3);
        Integer elem = 1000;
        int actual = seq.indexOf(elem);
        int expected = -1;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void isDefinedAt_A$int_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        for (int i = -3; i < 3; i++) {
            boolean actual = seq.isDefinedAt(i);
            boolean expected = false;
            assertThat(actual, is(equalTo(expected)));
        }
    }

    @Test
    public void isDefinedAt_A$int() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3);
        for (int i = -3; i < 0; i++) {
            boolean actual = seq.isDefinedAt(i);
            boolean expected = false;
            assertThat(actual, is(equalTo(expected)));
        }
        for (int i = 0; i < 3; i++) {
            boolean actual = seq.isDefinedAt(i);
            boolean expected = true;
            assertThat(actual, is(equalTo(expected)));
        }
        for (int i = 3; i < 5; i++) {
            boolean actual = seq.isDefinedAt(i);
            boolean expected = false;
            assertThat(actual, is(equalTo(expected)));
        }
    }

    @Test
    public void indices_A$_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        Seq<Integer> actual = seq.indices();
        assertThat(actual.size(), is(equalTo(0)));
    }

    @Test
    public void indices_A$() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3);
        Seq<Integer> actual = seq.indices();
        assertThat(actual.size(), is(equalTo(3)));
        assertThat(actual.toList().get(0), is(equalTo(0)));
        assertThat(actual.toList().get(1), is(equalTo(1)));
        assertThat(actual.toList().get(2), is(equalTo(2)));
    }

    @Test
    public void reverseMap_A$Function1_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        Seq<String> actual = seq.reverseMap(new F1<Integer, String>() {
            public String _(Integer v1) {
                return v1.toString();
            }
        });
        assertThat(actual.size(), is(equalTo(0)));
    }

    @Test
    public void reverseMap_A$Function1() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3);
        Seq<String> actual = seq.reverseMap(new F1<Integer, String>() {
            public String _(Integer v1) {
                return v1.toString();
            }
        });
        assertThat(actual.size(), is(equalTo(3)));
        assertThat(actual.toList().get(0), is(equalTo("3")));
        assertThat(actual.toList().get(1), is(equalTo("2")));
        assertThat(actual.toList().get(2), is(equalTo("1")));
    }

    @Test
    public void patch_A$int$Seq$int_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        int from = 1;
        Seq<Integer> patch = Seq._(7, 8, 9);
        int replaced = 1;
        Seq<Integer> actual = seq.patch(from, patch, replaced);
        assertThat(actual.size(), is(equalTo(3)));
        assertThat(actual.toList().get(0), is(equalTo(7)));
        assertThat(actual.toList().get(1), is(equalTo(8)));
        assertThat(actual.toList().get(2), is(equalTo(9)));
    }

    @Test
    public void patch_A$int$Seq$int() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3);
        int from = 1;
        Seq<Integer> patch = Seq._(7, 8, 9);
        int replaced = 1;
        Seq<Integer> actual = seq.patch(from, patch, replaced);
        assertThat(actual.size(), is(equalTo(5)));
        assertThat(actual.toList().get(0), is(equalTo(1)));
        assertThat(actual.toList().get(1), is(equalTo(7)));
        assertThat(actual.toList().get(2), is(equalTo(8)));
        assertThat(actual.toList().get(3), is(equalTo(9)));
        assertThat(actual.toList().get(4), is(equalTo(3)));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void updated_A$int$Object_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        int index = 0;
        Integer elem = 4;
        seq.updated(index, elem);
    }

    @Test
    public void updated_A$int$Object() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3);
        int index = 0;
        Integer elem = 4;
        Seq<Integer> actual = seq.updated(index, elem);
        assertThat(actual.size(), is(equalTo(3)));
        assertThat(actual.toList().get(0), is(equalTo(4)));
        assertThat(actual.toList().get(1), is(equalTo(2)));
        assertThat(actual.toList().get(2), is(equalTo(3)));
    }

    @Test
    public void apply_A$ObjectArray_Array() throws Exception {
        Seq<String> actual = Seq.apply(new String[] { "a", "b", "c" });
        assertThat(actual.size(), is(equalTo(3)));
    }

    @Test
    public void apply_A$ObjectArray() throws Exception {
        Seq<Integer> actual = Seq.apply(1, 2, 3);
        assertThat(actual.size(), is(equalTo(3)));
    }

    @Test
    public void apply_A$Collection() throws Exception {
        List<Integer> list_ = new ArrayList<Integer>();
        list_.add(1);
        list_.add(2);
        list_.add(3);
        Seq<Integer> actual = Seq.apply(list_);
        assertThat(actual.size(), is(equalTo(3)));
    }

    @Test
    public void __A$ObjectArray() throws Exception {
        Seq<Integer> actual = Seq._(1, 2, 3);
        assertThat(actual.size(), is(equalTo(3)));
    }

    @Test
    public void __A$Collection_List() throws Exception {
        List<Integer> list_ = new ArrayList<Integer>();
        list_.add(1);
        list_.add(2);
        list_.add(3);
        Seq<Integer> actual = Seq._(list_);
        assertThat(actual.size(), is(equalTo(3)));
    }

    @Test
    public void __A$Collection_Set() throws Exception {
        Set<Integer> list_ = new HashSet<Integer>();
        list_.add(1);
        list_.add(2);
        list_.add(3);
        Seq<Integer> actual = Seq._(list_);
        assertThat(actual.size(), is(equalTo(3)));
    }

    @Test
    public void union_A$Seq() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3);
        Seq<Integer> that = Seq._(2, 3, 4);
        Seq<Integer> actual = seq.union(that);
        assertThat(actual.size(), is(equalTo(6)));
        assertThat(actual.toList().get(0), is(equalTo(1)));
        assertThat(actual.toList().get(1), is(equalTo(2)));
        assertThat(actual.toList().get(2), is(equalTo(3)));
        assertThat(actual.toList().get(3), is(equalTo(2)));
        assertThat(actual.toList().get(4), is(equalTo(3)));
        assertThat(actual.toList().get(5), is(equalTo(4)));
    }

    @Test
    public void sum_A$_Int() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3);
        SNum actual = seq.sum();
        assertThat(actual.toInt(), is(equalTo(6)));
    }

    @Test
    public void sum_A$_Int_containsNull() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, null, 3);
        SNum actual = seq.sum();
        assertThat(actual.toInt(), is(equalTo(6)));
    }

    @Test
    public void sum_A$_Double() throws Exception {
        Seq<Double> seq = Seq._(0.1D, 0.2D, 0.3D);
        SNum actual = seq.sum();
        assertThat(actual.toDouble(), is(equalTo(0.6D)));
    }

    @Test
    public void sum_A$_Long() throws Exception {
        Seq<Long> seq = Seq._(1L, 2L, 3L);
        SNum actual = seq.sum();
        assertThat(actual.toLong(), is(equalTo(6L)));
    }

    @Test(expected = ScalaFlavor4JException.class)
    public void sum_A$_String() throws Exception {
        Seq<String> seq = Seq._("a", "b", "c");
        SNum actual = seq.sum();
        assertThat(actual.toInt(), is(equalTo(6)));
    }

    @Test
    public void max_A$() throws Exception {
        Seq<Integer> seq = Seq._(1, 4, 5, 2, 3);
        SNum actual = seq.max();
        assertThat(actual.toInt(), is(equalTo(5)));
    }

    @Test
    public void max_A$_containsNull() throws Exception {
        Seq<Integer> seq = Seq._(1, 4, 5, null, 2, 3);
        SNum actual = seq.max();
        assertThat(actual.toInt(), is(equalTo(5)));
    }

    @Test(expected = ScalaFlavor4JException.class)
    public void max_A$_String() throws Exception {
        Seq<String> seq = Seq._("a", "b", "c");
        SNum actual = seq.max();
        assertThat(actual.toInt(), is(equalTo(5)));
    }

    @Test
    public void min_A$() throws Exception {
        Seq<Integer> seq = Seq._(4, 5, 2, 1, 3);
        SNum actual = seq.min();
        assertThat(actual.toInt(), is(equalTo(1)));
    }

    @Test
    public void min_A$_containsNull() throws Exception {
        Seq<Integer> seq = Seq._(4, 5, 2, null, 1, 3);
        SNum actual = seq.min();
        assertThat(actual.toInt(), is(equalTo(1)));
    }

    @Test(expected = ScalaFlavor4JException.class)
    public void min_A$_String() throws Exception {
        Seq<String> seq = Seq._("a", "b", "c");
        SNum actual = seq.min();
        assertThat(actual.toInt(), is(equalTo(1)));
    }

    @Test
    public void sameElements_A$Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        {
            boolean actual = seq.sameElements(Seq._(1, 2, 3));
            assertThat(actual, is(equalTo(false)));
        }
        {
            Seq<Integer> nil = Seq._();
            boolean actual = seq.sameElements(nil);
            assertThat(actual, is(equalTo(true)));
        }
    }

    @Test
    public void sameElements_A$Seq_true() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3);
        Seq<Integer> that = Seq._(1, 2, 3);
        boolean actual = seq.sameElements(that);
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void sameElements_A$Seq_containsNull() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, null, 3);
        Seq<Integer> that = Seq._(1, 2, null, 3);
        boolean actual = seq.sameElements(that);
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void sameElements_A$Seq_false() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3);
        {
            Seq<Integer> that = Seq._(1, 2, 3, 4);
            assertThat(seq.sameElements(that), is(false));
        }
        {
            Seq<Integer> that = Seq._(3, 2, 1);
            assertThat(seq.sameElements(that), is(false));
        }
    }

    @Test
    public void intersect_A$Seq_Nil() throws Exception {
        Seq<Integer> seq = Seq._();
        Seq<Integer> that = Seq._(3, 4, 5);
        Seq<Integer> actual = seq.intersect(that);
        assertThat(actual.size(), is(equalTo(0)));
    }

    @Test
    public void intersect_A$Seq() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3, 4, 5);
        Seq<Integer> that = Seq._(3, 4, 5, 6, 7);
        Seq<Integer> actual = seq.intersect(that);
        assertThat(actual.size(), is(equalTo(3)));
        assertThat(actual.toList().get(0), is(equalTo(3)));
        assertThat(actual.toList().get(1), is(equalTo(4)));
        assertThat(actual.toList().get(2), is(equalTo(5)));
    }

    @Test
    public void mkString_A$String$String$String() throws Exception {
        Seq<String> seq = Seq._("090", "1111", "2222");
        String start = "Tel:";
        String sep = "-";
        String end = "";
        String actual = seq.mkString(start, sep, end);
        String expected = "Tel:090-1111-2222";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void mkString_A$() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3, 4, 5);
        String actual = seq.mkString();
        String expected = "12345";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void padTo_A$int$Object_minusValue() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3);
        int len = -3;
        Seq<Integer> actual = seq.padTo(len, 9);
        assertThat(actual.size(), is(equalTo(3)));
        assertThat(actual.mkString(), is(equalTo("123")));
    }

    @Test
    public void padTo_A$int$Object_lessLength() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3);
        int len = 2;
        Seq<Integer> actual = seq.padTo(len, 9);
        assertThat(actual.size(), is(equalTo(3)));
        assertThat(actual.mkString(), is(equalTo("123")));
    }

    @Test
    public void padTo_A$int$Object_sameLength() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3);
        int len = 3;
        Seq<Integer> actual = seq.padTo(len, 9);
        assertThat(actual.size(), is(equalTo(3)));
        assertThat(actual.mkString(), is(equalTo("123")));
    }

    @Test
    public void padTo_A$int$Object() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3);
        int len = 5;
        Seq<Integer> actual = seq.padTo(len, 9);
        assertThat(actual.size(), is(equalTo(5)));
        assertThat(actual.mkString(), is(equalTo("12399")));
    }

    @Test
    public void scanLeft_A$Object$Function2() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3);
        Seq<Integer> actual = seq.scanLeft(0, new F2<Integer, Integer, Integer>() {
            public Integer _(Integer acm, Integer i) {
                return acm + i;
            }
        });
        assertThat(actual.size(), is(equalTo(4)));
        assertThat(actual.toList().get(0), is(equalTo(0)));
        assertThat(actual.toList().get(1), is(equalTo(1)));
        assertThat(actual.toList().get(2), is(equalTo(3)));
        assertThat(actual.toList().get(3), is(equalTo(6)));
    }

    @Test
    public void scanRight_A$Object$Function2() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3);
        Seq<Integer> actual = seq.scanRight(0, new F2<Integer, Integer, Integer>() {
            public Integer _(Integer acm, Integer i) {
                return acm + i;
            }
        });
        assertThat(actual.size(), is(equalTo(4)));
        assertThat(actual.toList().get(0), is(equalTo(6)));
        assertThat(actual.toList().get(1), is(equalTo(5)));
        assertThat(actual.toList().get(2), is(equalTo(3)));
        assertThat(actual.toList().get(3), is(equalTo(0)));
    }

    @Test
    public void append_A$ObjectArray() throws Exception {
        Seq<Integer> seq = Seq._(1, 2, 3);
        Seq<Integer> actual = seq.append(4, 5, 6);
        assertThat(actual.size(), is(equalTo(6)));
        assertThat(actual.toList().get(0), is(equalTo(1)));
        assertThat(actual.toList().get(1), is(equalTo(2)));
        assertThat(actual.toList().get(2), is(equalTo(3)));
        assertThat(actual.toList().get(3), is(equalTo(4)));
        assertThat(actual.toList().get(4), is(equalTo(5)));
        assertThat(actual.toList().get(5), is(equalTo(6)));
    }

    @Test
    public void apply_A$Enumeration() throws Exception {
        Enumeration<Object> e = new StringTokenizer("a,b, c", ",");
        Seq<Object> seq = Seq.apply(e);
        assertThat(seq.size(), is(equalTo(3)));
    }

    @Test
    public void apply_A$Iterator() throws Exception {
        Iterator<Integer> iter = Arrays.asList(1, 2, 3).iterator();
        Seq<Integer> seq = Seq.apply(iter);
        assertThat(seq.size(), is(equalTo(3)));
    }

    @Test
    public void apply_A$Iterable() throws Exception {
        Iterable<Integer> iterable = Arrays.asList(2, 3, 4);
        Seq<Integer> seq = Seq.apply(iterable);
        assertThat(seq.size(), is(equalTo(3)));
    }

    @Test
    public void __A$Enumeration() throws Exception {
        Enumeration<Object> e = new StringTokenizer("日 本 語");
        Seq<Object> seq = Seq._(e);
        assertThat(seq.size(), is(equalTo(3)));
    }

    @Test
    public void __A$Iterator() throws Exception {
        Iterator<Character> iter = Arrays.asList('a', 'b', 'c', 'd', 'e').iterator();
        Seq<Character> seq = Seq.apply(iter);
        assertThat(seq.size(), is(equalTo(5)));
    }

    @Test
    public void __A$Iterable() throws Exception {
        Iterable<Character> iterable = Arrays.asList('f', 'g');
        Seq<Character> seq = Seq.apply(iterable);
        assertThat(seq.size(), is(equalTo(2)));
    }

    @Test
    public void applyCollection_A$Collection() throws Exception {
        Collection<Integer> col = Arrays.asList(0, 1, 2, 3);
        Seq<Integer> seq = Seq.applyCollection(col);
        assertThat(seq.size(), is(equalTo(4)));
    }

}
