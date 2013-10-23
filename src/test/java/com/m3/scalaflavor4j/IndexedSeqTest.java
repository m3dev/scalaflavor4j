package com.m3.scalaflavor4j;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class IndexedSeqTest {

    List<String> emptyList = new ArrayList<String>();

    List<String> list = Arrays.asList("foo", null, "bar");

    @Test
    public void type() throws Exception {
        assertThat(IndexedSeq.class, notNullValue());
    }

    @Test
    public void toList_A$_Nil() throws Exception {
        IndexedSeq<String> seq = IndexedSeq.apply(emptyList);
        List<String> actual = seq.toList();
        assertThat(actual.isEmpty(), is(true));
        assertThat(actual.size(), is(0));
    }

    @Test
    public void toList_A$() throws Exception {
        IndexedSeq<String> seq = IndexedSeq.apply(list);
        List<String> actual = seq.toList();
        assertThat(actual.isEmpty(), is(false));
        assertThat(actual.size(), is(3));
    }

    @Test
    public void isEmpty_A$_Nil() throws Exception {
        IndexedSeq<String> seq = IndexedSeq.apply(emptyList);
        boolean actual = seq.isEmpty();
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void isEmpty_A$() throws Exception {
        IndexedSeq<String> seq = IndexedSeq.apply(list);
        boolean actual = seq.isEmpty();
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void map_A$Function1_Nil() throws Exception {
        IndexedSeq<String> seq = IndexedSeq.apply(emptyList);
        Seq<Integer> actual = seq.map(new F1<String, Integer>() {
            public Integer apply(String v1) {
                return v1.length();
            }
        });
        assertThat(actual.isEmpty(), is(true));
        assertThat(actual.toList().size(), is(0));
    }

    @Test
    public void map_A$Function1() throws Exception {
        F1<String, Integer> f = new F1<String, Integer>() {
            public Integer apply(String v1) {
                return v1 == null ? 0 : v1.length();
            }
        };
        IndexedSeq<String> seq = IndexedSeq.apply(list);
        Seq<Integer> actual = seq.map(f);
        assertThat(actual.isEmpty(), is(false));
        assertThat(actual.toList().size(), is(3));
    }

    @Test
    public void foreach_A$VoidFunction1() throws Exception {
        VoidF1<String> f = new VoidF1<String>() {
            public void apply(String v1) {
                System.out.println(v1);
            }
        };
        IndexedSeq.apply(emptyList).foreach(f);
        IndexedSeq.apply(list).foreach(f);
    }

    @Test
    public void flatMap_A$Function1_emptyList() throws Exception {
        F1<String, CollectionLike<Integer>> f = new F1<String, CollectionLike<Integer>>() {
            public Option<Integer> apply(String v1) {
                return Option.apply(v1.length());
            }
        };
        Seq<Integer> actual = IndexedSeq.apply(emptyList).flatMap(f);
        assertThat(actual.isEmpty(), is(true));
        assertThat(actual.toList().size(), is(0));
    }

    @Test
    public void flatMap_A$Function1() throws Exception {
        F1<String, CollectionLike<Integer>> f = new F1<String, CollectionLike<Integer>>() {
            public Option<Integer> apply(String v1) {
                return v1 == null ? Option.apply(0) : Option.apply(v1.length());
            }
        };
        Seq<Integer> actual = IndexedSeq.apply(list).flatMap(f);
        assertThat(actual.isEmpty(), is(false));
        assertThat(actual.toList().size(), is(3));
    }

    @Test
    public void flatMap_A$Function1_FlatMapF1() throws Exception {
        Seq<Integer> actual = IndexedSeq.apply(list).flatMap(new FlatMapF1<String, Integer>() {
            public Option<Integer> apply(String str) {
                return str == null ? Option.apply(0) : Option.apply(str.length());
            }
        });
        assertThat(actual.isEmpty(), is(false));
        assertThat(actual.toList().size(), is(3));
    }

    @Test
    public void filter_A$Function1_Nil() throws Exception {
        F1<String, Boolean> f = new F1<String, Boolean>() {
            public Boolean apply(String v1) {
                return v1.contains("f");
            }
        };
        Seq<String> actual = IndexedSeq.apply(emptyList).filter(f);
        assertThat(actual.toList().size(), is(equalTo(0)));
    }

    @Test
    public void filter_A$Function1() throws Exception {
        F1<String, Boolean> f = new F1<String, Boolean>() {
            public Boolean apply(String v1) {
                return v1 == null ? false : v1.contains("f");
            }
        };
        Seq<String> actual = IndexedSeq.apply(list).filter(f);
        assertThat(actual.toList().size(), is(equalTo(1)));
    }

    @Test
    public void filterNot_A$Function1_Nil() throws Exception {
        F1<String, Boolean> f = new F1<String, Boolean>() {
            public Boolean apply(String v1) {
                return v1.contains("b");
            }
        };
        Seq<String> actual = IndexedSeq.apply(emptyList).filterNot(f);
        assertThat(actual.toList().size(), is(equalTo(0)));
    }

    @Test
    public void filterNot_A$Function1() throws Exception {
        F1<String, Boolean> f = new F1<String, Boolean>() {
            public Boolean apply(String v1) {
                return v1.contains("b");
            }
        };
        Seq<String> actual = IndexedSeq.apply("aaa", "bbb", "ccc").filterNot(f);
        assertThat(actual.toList().size(), is(equalTo(2)));
    }

    @Test
    public void head_A$_Nil() throws Exception {
        String actual = IndexedSeq.apply(emptyList).head();
        assertThat(actual, is(nullValue()));
    }

    @Test
    public void head_A$() throws Exception {
        String actual = IndexedSeq.apply(list).head();
        String expected = "foo";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void tail_A$_Nil() throws Exception {
        Seq<String> actual = IndexedSeq.apply(emptyList).tail();
        assertThat(actual.isEmpty(), is(true));
        assertThat(actual.toList().size(), is(0));
    }

    @Test
    public void tail_A$() throws Exception {
        Seq<String> actual = IndexedSeq.apply(list).tail();
        assertThat(actual.isEmpty(), is(false));
        assertThat(actual.toList().size(), is(2));
    }

    @Test
    public void mkString_A$String_Nil() throws Exception {
        Seq<String> seq = IndexedSeq.apply(emptyList);
        String sep = "-";
        String actual = seq.mkString(sep);
        String expected = "";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void mkString_A$String() throws Exception {
        Seq<String> seq = IndexedSeq.apply(list);
        String sep = "-";
        String actual = seq.mkString(sep);
        String expected = "foo-null-bar";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void sortWith_A$Function2_Nil() throws Exception {
        F2<Integer, Integer, Boolean> f = new F2<Integer, Integer, Boolean>() {
            public Boolean apply(Integer v1, Integer v2) {
                return v1 < v2;
            }
        };
        Seq<Integer> seq = IndexedSeq.apply();
        List<Integer> sorted = seq.sortWith(f).toList();
        assertThat(sorted.isEmpty(), is(true));
        assertThat(sorted.size(), is(equalTo(0)));
    }

    @Test
    public void sortWith_A$Function2() throws Exception {
        F2<Integer, Integer, Boolean> f = new F2<Integer, Integer, Boolean>() {
            public Boolean apply(Integer v1, Integer v2) {
                return v1 < v2;
            }
        };
        Seq<Integer> seq = IndexedSeq.apply(3, 4, 2, 0, 5, 2, 1);
        List<Integer> sorted = seq.sortWith(f).toList();
        assertThat(sorted.get(0), is(equalTo(0)));
        assertThat(sorted.get(1), is(equalTo(1)));
        assertThat(sorted.get(2), is(equalTo(2)));
        assertThat(sorted.get(3), is(equalTo(2)));
        assertThat(sorted.get(4), is(equalTo(3)));
        assertThat(sorted.get(5), is(equalTo(4)));
        assertThat(sorted.get(6), is(equalTo(5)));
    }

    @Test
    public void take_A$int_Nil() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply();
        int n = 3;
        Seq<Integer> actual = seq.take(n);
        assertThat(actual.size(), is(equalTo(0)));
    }

    @Test
    public void take_A$int() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(3, 4, 2, 0, 5, 1);
        int n = 3;
        Seq<Integer> actual = seq.take(n);
        assertThat(actual.toList().size(), is(equalTo(3)));
        assertThat(actual.toList().get(0), is(equalTo(3)));
        assertThat(actual.toList().get(1), is(equalTo(4)));
        assertThat(actual.toList().get(2), is(equalTo(2)));
    }

    @Test
    public void foldLeft_A$Object$Function2_None() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply();
        String actual = seq.foldLeft("foo", new F2<String, Integer, String>() {
            public String apply(String v1, Integer v2) {
                return v1 + v2;
            }
        });
        assertThat(actual, is("foo"));
        String actual2 = seq.foldLeft("foo").apply(new F2<String, Integer, String>() {
            public String apply(String v1, Integer v2) {
                return v1 + v2;
            }
        });
        assertThat(actual2, is("foo"));
    }

    @Test
    public void foldLeft_A$Object$Function2_1() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(3, 4, 2, 0, 5, 1);
        String actual = seq.foldLeft("foo", new F2<String, Integer, String>() {
            public String apply(String v1, Integer v2) {
                return v1 + v2;
            }
        });
        assertThat(actual, is("foo342051"));
        String actual2 = seq.foldLeft("foo").apply(new F2<String, Integer, String>() {
            public String apply(String v1, Integer v2) {
                return v1 + v2;
            }
        });
        assertThat(actual2, is("foo342051"));
    }

    @Test
    public void foldLeft_A$Object$Function2_2() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(3, 4, 2, 0, 5, 1);
        Integer actual = seq.foldLeft(0, new F2<Integer, Integer, Integer>() {
            public Integer apply(Integer v1, Integer v2) {
                return v1 + v2;
            }
        });
        assertThat(actual, is(15));
        Integer result = seq.foldLeft(0).apply(new FoldLeftF2<Integer, Integer>() {
            public Integer apply(Integer z, Integer i) throws Exception {
                return z + i;
            }
        });
        assertThat(result, is(equalTo(15)));
    }

    @Test
    public void foldLeft_A$Object() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(3, 4, 2, 0, 5, 1);
        Integer result = seq.foldLeft(0).apply(new FoldLeftF2<Integer, Integer>() {
            public Integer apply(Integer z, Integer i) throws Exception {
                return z + i;
            }
        });
        assertThat(result, is(equalTo(15)));
    }

    @Test
    public void zipWithIndex_A$_Nil() throws Exception {
        Seq<String> seq = IndexedSeq.apply();
        Seq<Tuple2<String, Integer>> actual = seq.zipWithIndex();
        assertThat(actual.isEmpty(), is(true));
        assertThat(actual.size(), is(0));
    }

    @Test
    public void zipWithIndex_A$() throws Exception {
        Seq<String> seq = IndexedSeq.apply("A", "B", "C");
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
    public void foldRight_A$Object() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply();
        String actual = seq.foldRight("foo").apply(new F2<Integer, String, String>() {
            public String apply(Integer v1, String v2) {
                return v1 + v2;
            }
        });
        assertThat(actual, is("foo"));
    }

    @Test
    public void foldRight_A$Object$Function2_Nil() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply();
        String actual = seq.foldRight("foo", new F2<Integer, String, String>() {
            public String apply(Integer v1, String v2) {
                return v1 + v2;
            }
        });
        assertThat(actual, is("foo"));
        String actual2 = seq.foldRight("foo").apply(new F2<Integer, String, String>() {
            public String apply(Integer v1, String v2) {
                return v1 + v2;
            }
        });
        assertThat(actual2, is("foo"));
    }

    @Test
    public void foldRight_A$Object$Function2() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(3, 4, 2, 0, 5, 1);
        String actual = seq.foldRight("foo", new F2<Integer, String, String>() {
            public String apply(Integer v1, String v2) {
                return v1 + v2;
            }
        });
        assertThat(actual, is("342051foo"));
        String actual2 = seq.foldRight("foo").apply(new F2<Integer, String, String>() {
            public String apply(Integer v1, String v2) {
                return v1 + v2;
            }
        });
        assertThat(actual2, is("342051foo"));
    }

    @Test
    public void headOption_A$_Nil() throws Exception {
        Seq<String> seq = IndexedSeq.apply();
        Option<String> actual = seq.headOption();
        assertThat(actual.isDefined(), is(false));
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void headOption_A$_none() throws Exception {
        Seq<String> seq = IndexedSeq.apply(new ArrayList<String>());
        Option<String> actual = seq.headOption();
        assertThat(actual.isDefined(), is(false));
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void headOption_A$() throws Exception {
        List<String> list = new ArrayList<String>();
        list.add("foo");
        IndexedSeq<String> seq = IndexedSeq.apply(list);
        Option<String> actual = seq.headOption();
        assertThat(actual.isDefined(), is(true));
        assertThat(actual.getOrNull(), is(equalTo("foo")));
    }

    @Test
    public void distinct_A$_Nil() throws Exception {
        IndexedSeq<Integer> seq = IndexedSeq.apply();
        Seq<Integer> actual = seq.distinct();
        assertThat(actual.size(), is(equalTo(0)));
    }

    @Test
    public void distinct_A$() throws Exception {
        List<Integer> list = Arrays.asList(1, 3, 2, 2, 3, 4, 5, 2, 3, 2, 5);
        IndexedSeq<Integer> seq = IndexedSeq.apply(list);
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
        Seq<String> seq = IndexedSeq.apply();
        Integer actual = seq.size();
        Integer expected = 0;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void size_A$() throws Exception {
        List<String> list = Arrays.asList("a", "b", "c");
        Seq<String> seq = IndexedSeq.apply(list);
        Integer actual = seq.size();
        Integer expected = 3;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void groupBy_A$Function1_Nil() throws Exception {
        Seq<String> seq = IndexedSeq.apply();
        SMap<String, Seq<String>> actual = seq.groupBy(new F1<String, String>() {
            public String apply(String v1) {
                return v1;
            }
        });
        assertThat(actual, is(notNullValue()));
        assertThat(actual.toMap().size(), is(equalTo(0)));
    }

    @Test
    public void groupBy_A$Function1_1() throws Exception {
        List<String> list = Arrays.asList("a", "b", "a", "a", "c", "b");
        Seq<String> seq = IndexedSeq.apply(list);
        SMap<String, Seq<String>> actual = seq.groupBy(new F1<String, String>() {
            public String apply(String v1) {
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
        Seq<String> seq = IndexedSeq.apply(list);
        SMap<Integer, Seq<String>> actual = seq.groupBy(new F1<String, Integer>() {
            public Integer apply(String v1) {
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
        Seq<String> seq = IndexedSeq.apply();
        String actual = seq.last();
        assertThat(actual, is(nullValue()));
    }

    @Test
    public void last_A$() throws Exception {
        List<String> list = Arrays.asList("a", "b", "a", "a", "c", "z");
        Seq<String> seq = IndexedSeq.apply(list);
        String actual = seq.last();
        String expected = "z";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void lastOption_A$_Nil() throws Exception {
        Seq<String> seq = IndexedSeq.apply();
        Option<String> actual = seq.lastOption();
        assertThat(actual.isDefined(), is(false));
    }

    @Test
    public void lastOption_A$() throws Exception {
        List<String> list = Arrays.asList("a", "b", "a", "a", "c", "z");
        Seq<String> seq = IndexedSeq.apply(list);
        Option<String> actual = seq.lastOption();
        assertThat(actual.getOrNull(), is(equalTo("z")));
    }

    @Test
    public void find_A$Function1_Nil() throws Exception {
        Seq<String> seq = IndexedSeq.apply();
        F1<String, Boolean> p = new F1<String, Boolean>() {
            public Boolean apply(String v1) {
                return v1.equals("c");
            }
        };
        Option<String> actual = seq.find(p);
        assertThat(actual.isDefined(), is(false));
    }

    @Test
    public void find_A$Function1() throws Exception {
        List<String> list = Arrays.asList("a", "b", "a", "a", "c", "z");
        Seq<String> seq = IndexedSeq.apply(list);
        F1<String, Boolean> p = new F1<String, Boolean>() {
            public Boolean apply(String v1) {
                return v1.equals("c");
            }
        };
        Option<String> actual = seq.find(p);
        assertThat(actual.getOrNull(), is(equalTo("c")));
    }

    @Test
    public void partition_A$Function1_Nil() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply();
        Tuple2<Seq<Integer>, Seq<Integer>> actual = seq.partition(new F1<Integer, Boolean>() {
            public Boolean apply(Integer v1) {
                return v1 < 3;
            }
        });
        assertThat(actual._1().mkString(""), is(equalTo("")));
        assertThat(actual._2().mkString(""), is(equalTo("")));
    }

    @Test
    public void partition_A$Function1() throws Exception {
        List<Integer> list = Arrays.asList(1, 5, 2, 3, 4);
        Seq<Integer> seq = IndexedSeq.apply(list);
        Tuple2<Seq<Integer>, Seq<Integer>> actual = seq.partition(new F1<Integer, Boolean>() {
            public Boolean apply(Integer v1) {
                return v1 < 3;
            }
        });
        assertThat(actual._1().mkString(""), is(equalTo("12")));
        assertThat(actual._2().mkString(""), is(equalTo("534")));
    }

    @Test
    public void reverse_A$_Nil() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply();
        Seq<Integer> actual = seq.reverse();
        assertThat(actual.mkString(""), is(equalTo("")));
    }

    @Test
    public void reverse_A$() throws Exception {
        List<Integer> list = Arrays.asList(1, 5, 2, 3, 4);
        Seq<Integer> seq = IndexedSeq.apply(list);
        Seq<Integer> actual = seq.reverse();
        assertThat(actual.mkString(""), is(equalTo("43251")));
    }

    @Test
    public void slice_A$int$int_Nil() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply();
        Integer from = 2;
        Integer until = 4;
        Seq<Integer> actual = seq.slice(from, until);
        assertThat(actual.size(), is(equalTo(0)));
    }

    @Test
    public void slice_A$int$int() throws Exception {
        List<Integer> list = Arrays.asList(1, 5, 2, 3, 4);
        Seq<Integer> seq = IndexedSeq.apply(list);
        Integer from = 2;
        Integer until = 4;
        Seq<Integer> actual = seq.slice(from, until);
        assertThat(actual.size(), is(equalTo(2)));
        assertThat(actual.toList().get(0), is(equalTo(2)));
        assertThat(actual.toList().get(1), is(equalTo(3)));
    }

    @Test
    public void slice_A$int$int_largerUntilValue() throws Exception {
        List<Integer> list = Arrays.asList(1, 5, 2, 3, 4);
        Seq<Integer> seq = IndexedSeq.apply(list);
        Integer from = 2;
        Integer until = 100;
        Seq<Integer> actual = seq.slice(from, until);
        assertThat(actual.size(), is(equalTo(3)));
        assertThat(actual.toList().get(0), is(equalTo(2)));
        assertThat(actual.toList().get(1), is(equalTo(3)));
        assertThat(actual.toList().get(2), is(equalTo(4)));
    }

    @Test
    public void sliding_A$int_Nil() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply();
        Integer size = 3;
        Seq<Seq<Integer>> actual = seq.sliding(size);
        assertThat(actual.size(), is(equalTo(0)));
    }

    @Test
    public void sliding_A$int_3() throws Exception {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Seq<Integer> seq = IndexedSeq.apply(list);
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
        Seq<Integer> seq = IndexedSeq.apply(list);
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
        Seq<Integer> seq = IndexedSeq.apply(list);
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
        Seq<Integer> seq = IndexedSeq.apply(list);
        Integer size = 6;
        Integer step = 3;
        Seq<Seq<Integer>> actual = seq.sliding(size, step);
        assertThat(actual.size(), is(equalTo(1)));
        assertThat(actual.toList().get(0).mkString(""), is(equalTo("12345")));
    }

    @Test
    public void span_A$Function1_Nil() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply();
        Tuple2<Seq<Integer>, Seq<Integer>> actual = seq.span(new F1<Integer, Boolean>() {
            public Boolean apply(Integer v1) {
                return v1 < 3;
            }
        });
        assertThat(actual._1().mkString(""), is(equalTo("")));
        assertThat(actual._2().mkString(""), is(equalTo("")));
    }

    @Test
    public void span_A$Function1() throws Exception {
        List<Integer> list = Arrays.asList(1, 5, 2, 3, 4);
        Seq<Integer> seq = IndexedSeq.apply(list);
        Tuple2<Seq<Integer>, Seq<Integer>> actual = seq.span(new F1<Integer, Boolean>() {
            public Boolean apply(Integer v1) {
                return v1 < 3;
            }
        });
        assertThat(actual._1().mkString(""), is(equalTo("1")));
        assertThat(actual._2().mkString(""), is(equalTo("5234")));
    }

    @Test
    public void takeRight_A$int_Nil() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply();
        int n = 3;
        Seq<Integer> actual = seq.takeRight(n);
        assertThat(actual.mkString(""), is(equalTo("")));
    }

    @Test
    public void takeRight_A$int() throws Exception {
        List<Integer> list = Arrays.asList(1, 5, 2, 3, 4);
        Seq<Integer> seq = IndexedSeq.apply(list);
        int n = 3;
        Seq<Integer> actual = seq.takeRight(n);
        assertThat(actual.mkString(""), is(equalTo("234")));
    }

    @Test
    public void takeWhile_A$Function1_Nil() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply();
        Seq<Integer> actual = seq.takeWhile(new F1<Integer, Boolean>() {
            public Boolean apply(Integer v1) {
                return v1 < 3;
            }
        });
        assertThat(actual.mkString(""), is(equalTo("")));
    }

    @Test
    public void takeWhile_A$Function1() throws Exception {
        List<Integer> list = Arrays.asList(1, 2, 5, 3, 4);
        Seq<Integer> seq = IndexedSeq.apply(list);
        Seq<Integer> actual = seq.takeWhile(new F1<Integer, Boolean>() {
            public Boolean apply(Integer v1) {
                return v1 <= 3;
            }
        });
        assertThat(actual.mkString(""), is(equalTo("12")));
    }

    @Test
    public void splitAt_A$int_Nil() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply();
        Integer n = 3;
        Tuple2<Seq<Integer>, Seq<Integer>> actual = seq.splitAt(n);
        assertThat(actual._1().mkString(""), is(equalTo("")));
        assertThat(actual._2().mkString(""), is(equalTo("")));
    }

    @Test
    public void splitAt_A$int() throws Exception {
        List<Integer> list = Arrays.asList(1, 5, 2, 3, 4);
        Seq<Integer> seq = IndexedSeq.apply(list);
        Integer n = 3;
        Tuple2<Seq<Integer>, Seq<Integer>> actual = seq.splitAt(n);
        assertThat(actual._1().mkString(""), is(equalTo("152")));
        assertThat(actual._2().mkString(""), is(equalTo("34")));
    }

    @Test
    public void exists_A$Function1_Nil() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply();
        boolean actual = seq.exists(new F1<Integer, Boolean>() {
            public Boolean apply(Integer v1) {
                return v1 < 3;
            }
        });
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void exists_A$Function1_1() throws Exception {
        List<Integer> list = Arrays.asList(1, 5, 2, 3, 4);
        Seq<Integer> seq = IndexedSeq.apply(list);
        boolean actual = seq.exists(new F1<Integer, Boolean>() {
            public Boolean apply(Integer v1) {
                return v1 < 3;
            }
        });
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void exists_A$Function1_2() throws Exception {
        List<Integer> list = Arrays.asList(5, 2, 3, 4, 1);
        Seq<Integer> seq = IndexedSeq.apply(list);
        boolean actual = seq.exists(new F1<Integer, Boolean>() {
            public Boolean apply(Integer v1) {
                return v1 < 3;
            }
        });
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void exists_A$Function1_false() throws Exception {
        List<Integer> list = Arrays.asList(1, 5, 2, 3, 4);
        Seq<Integer> seq = IndexedSeq.apply(list);
        boolean actual = seq.exists(new F1<Integer, Boolean>() {
            public Boolean apply(Integer v1) {
                return v1 > 6;
            }
        });
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void forall_A$Function1_Nil() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply();
        boolean actual = seq.forall(new F1<Integer, Boolean>() {
            public Boolean apply(Integer v1) {
                return v1 < 3;
            }
        });
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void forall_A$Function1() throws Exception {
        List<Integer> list = Arrays.asList(1, 5, 2, 3, 4);
        Seq<Integer> seq = IndexedSeq.apply(list);
        boolean actual = seq.forall(new F1<Integer, Boolean>() {
            public Boolean apply(Integer v1) {
                return v1 < 3;
            }
        });
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void count_A$Function1_Nil() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply();
        Integer actual = seq.count(new F1<Integer, Boolean>() {
            public Boolean apply(Integer v1) {
                return v1 < 3;
            }
        });
        Integer expected = 0;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void count_A$Function1() throws Exception {
        List<Integer> list = Arrays.asList(1, 5, 2, 3, 4);
        Seq<Integer> seq = IndexedSeq.apply(list);
        Integer actual = seq.count(new F1<Integer, Boolean>() {
            public Boolean apply(Integer v1) {
                return v1 < 3;
            }
        });
        Integer expected = 2;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void drop_A$int_Nil() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply();
        int n = 3;
        Seq<Integer> actual = seq.drop(n);
        assertThat(actual.size(), is(equalTo(0)));
    }

    @Test
    public void drop_A$int() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(3, 4, 2, 0, 5, 1);
        int n = 3;
        Seq<Integer> actual = seq.drop(n);
        assertThat(actual.toList().size(), is(equalTo(3)));
        assertThat(actual.toList().get(0), is(equalTo(0)));
        assertThat(actual.toList().get(1), is(equalTo(5)));
        assertThat(actual.toList().get(2), is(equalTo(1)));
    }

    @Test
    public void dropRight_A$int_Nil() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply();
        int n = 3;
        Seq<Integer> actual = seq.dropRight(n);
        assertThat(actual.size(), is(equalTo(0)));
    }

    @Test
    public void dropRight_A$int() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(3, 4, 2, 0, 5, 1);
        int n = 3;
        Seq<Integer> actual = seq.dropRight(n);
        assertThat(actual.toList().size(), is(equalTo(3)));
        assertThat(actual.toList().get(0), is(equalTo(3)));
        assertThat(actual.toList().get(1), is(equalTo(4)));
        assertThat(actual.toList().get(2), is(equalTo(2)));
    }

    @Test
    public void dropWhile_A$Function1_Nil() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply();
        Seq<Integer> actual = seq.dropWhile(new F1<Integer, Boolean>() {
            public Boolean apply(Integer v1) {
                return v1 >= 3;
            }
        });
        assertThat(actual.size(), is(equalTo(0)));
    }

    @Test
    public void dropWhile_A$Function1() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(3, 4, 2, 0, 5, 1);
        Seq<Integer> actual = seq.dropWhile(new F1<Integer, Boolean>() {
            public Boolean apply(Integer v1) {
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
        Seq<Integer> seq = IndexedSeq.apply();
        Seq<String> that = IndexedSeq.apply("a");
        Seq<Tuple2<Integer, String>> actual = seq.zip(that);
        assertThat(actual.size(), is(equalTo(0)));
    }

    @Test
    public void zip_A$Seq_thisIsLonger() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(1, 2, 3, 4, 5);
        Seq<String> that = IndexedSeq.apply("a", "b", "c");
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
        Seq<Integer> seq = IndexedSeq.apply(1, 2, 3);
        Seq<String> that = IndexedSeq.apply("a", "b", "c", "d", "e");
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
        Seq<Integer> seq = IndexedSeq.apply();
        Integer elem = 2;
        boolean actual = seq.contains(elem);
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void contains_A$Object_containsNull() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(null, 1, 2, null, 3);
        Integer elem = 2;
        boolean actual = seq.contains(elem);
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void contains_A$Object_true() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(1, 2, 3);
        Integer elem = 2;
        boolean actual = seq.contains(elem);
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void contains_A$Object_false() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(1, 2, 3);
        Integer elem = 4;
        boolean actual = seq.contains(elem);
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void diff_A$Seq_Nil() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply();
        Seq<Integer> that = IndexedSeq.apply(2, 4);
        Seq<Integer> actual = seq.diff(that);
        assertThat(actual.size(), is(equalTo(0)));
    }

    @Test
    public void diff_A$Seq() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(1, 2, 3);
        Seq<Integer> that = IndexedSeq.apply(2, 4);
        Seq<Integer> actual = seq.diff(that);
        assertThat(actual.size(), is(equalTo(2)));
        assertThat(actual.toList().get(0), is(equalTo(1)));
        assertThat(actual.toList().get(1), is(equalTo(3)));
    }

    @Test
    public void diff_A$Seq_containsNull1() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(null, 1, 2, 3);
        Seq<Integer> that = IndexedSeq.apply(2, 4);
        Seq<Integer> actual = seq.diff(that);
        assertThat(actual.size(), is(equalTo(3)));
        assertThat(actual.toList().get(0), is(equalTo(null)));
        assertThat(actual.toList().get(1), is(equalTo(1)));
        assertThat(actual.toList().get(2), is(equalTo(3)));
    }

    @Test
    public void diff_A$Seq_containsNull2() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(null, 1, 2, 3);
        Seq<Integer> that = IndexedSeq.apply(2, 4, null);
        Seq<Integer> actual = seq.diff(that);
        assertThat(actual.size(), is(equalTo(2)));
        assertThat(actual.toList().get(0), is(equalTo(1)));
        assertThat(actual.toList().get(1), is(equalTo(3)));
    }

    @Test
    public void startsWith_A$Seq_Nil() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply();
        Seq<Integer> that = IndexedSeq.apply(1, 2);
        boolean actual = seq.startsWith(that);
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void startsWith_A$Seq_true() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(1, 2, 3);
        Seq<Integer> that = IndexedSeq.apply(1, 2);
        boolean actual = seq.startsWith(that);
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void startsWith_A$Seq_containsNull1() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(1, null, 2, 3);
        Seq<Integer> that = IndexedSeq.apply(1, 2);
        boolean actual = seq.startsWith(that);
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void startsWith_A$Seq_containsNull2() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(1, null, 2, 3);
        Seq<Integer> that = IndexedSeq.apply(1, null, 2);
        boolean actual = seq.startsWith(that);
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void startsWith_A$Seq_false() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(1, 2, 3);
        Seq<Integer> that = IndexedSeq.apply(1, 4);
        boolean actual = seq.startsWith(that);
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void startsWith_A$Seq$int_Nil() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply();
        Seq<Integer> that = IndexedSeq.apply(2, 3);
        int offset = 1;
        boolean actual = seq.startsWith(that, offset);
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void startsWith_A$Seq$int_true() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(1, 2, 3);
        Seq<Integer> that = IndexedSeq.apply(2, 3);
        int offset = 1;
        boolean actual = seq.startsWith(that, offset);
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void startsWith_A$Seq$int_false() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(1, 2, 3);
        Seq<Integer> that = IndexedSeq.apply(2, 3);
        int offset = 0;
        boolean actual = seq.startsWith(that, offset);
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void startsWith_A$Seq$int_outOfIndex() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(1, 2, 3);
        Seq<Integer> that = IndexedSeq.apply(1, 2);
        int offset = 100;
        boolean actual = seq.startsWith(that, offset);
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void endsWith_A$Seq_Nil() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply();
        Seq<Integer> that = IndexedSeq.apply(2, 3);
        boolean actual = seq.endsWith(that);
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void endsWith_A$Seq_true() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(1, 2, 3);
        Seq<Integer> that = IndexedSeq.apply(2, 3);
        boolean actual = seq.endsWith(that);
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void endsWith_A$Seq_false() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(1, 2, 3);
        Seq<Integer> that = IndexedSeq.apply(1, 2);
        boolean actual = seq.endsWith(that);
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void indexOf_A$Object_Nil() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply();
        Integer elem = 2;
        int actual = seq.indexOf(elem);
        int expected = -1;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void indexOf_A$Object_exists() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(1, 2, 3);
        Integer elem = 2;
        int actual = seq.indexOf(elem);
        int expected = 1;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void indexOf_A$Object_notExists() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(1, 2, 3);
        Integer elem = 1000;
        int actual = seq.indexOf(elem);
        int expected = -1;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void isDefinedAt_A$int_Nil() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply();
        for (int i = -3; i < 3; i++) {
            boolean actual = seq.isDefinedAt(i);
            boolean expected = false;
            assertThat(actual, is(equalTo(expected)));
        }
    }

    @Test
    public void isDefinedAt_A$int() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(1, 2, 3);
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
        Seq<Integer> seq = IndexedSeq.apply();
        Seq<Integer> actual = seq.indices();
        assertThat(actual.size(), is(equalTo(0)));
    }

    @Test
    public void indices_A$() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(1, 2, 3);
        Seq<Integer> actual = seq.indices();
        assertThat(actual.size(), is(equalTo(3)));
        assertThat(actual.toList().get(0), is(equalTo(0)));
        assertThat(actual.toList().get(1), is(equalTo(1)));
        assertThat(actual.toList().get(2), is(equalTo(2)));
    }

    @Test
    public void reverseMap_A$Function1_Nil() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply();
        Seq<String> actual = seq.reverseMap(new F1<Integer, String>() {
            public String apply(Integer v1) {
                return v1.toString();
            }
        });
        assertThat(actual.size(), is(equalTo(0)));
    }

    @Test
    public void reverseMap_A$Function1() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(1, 2, 3);
        Seq<String> actual = seq.reverseMap(new F1<Integer, String>() {
            public String apply(Integer v1) {
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
        Seq<Integer> seq = IndexedSeq.apply();
        int from = 1;
        Seq<Integer> patch = IndexedSeq.apply(7, 8, 9);
        int replaced = 1;
        Seq<Integer> actual = seq.patch(from, patch, replaced);
        assertThat(actual.size(), is(equalTo(3)));
        assertThat(actual.toList().get(0), is(equalTo(7)));
        assertThat(actual.toList().get(1), is(equalTo(8)));
        assertThat(actual.toList().get(2), is(equalTo(9)));
    }

    @Test
    public void patch_A$int$Seq$int() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(1, 2, 3);
        int from = 1;
        Seq<Integer> patch = IndexedSeq.apply(7, 8, 9);
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
        Seq<Integer> seq = IndexedSeq.apply();
        int index = 0;
        Integer elem = 4;
        seq.updated(index, elem);
    }

    @Test
    public void updated_A$int$Object() throws Exception {
        Seq<Integer> seq = IndexedSeq.apply(1, 2, 3);
        int index = 0;
        Integer elem = 4;
        Seq<Integer> actual = seq.updated(index, elem);
        assertThat(actual.size(), is(equalTo(3)));
        assertThat(actual.toList().get(0), is(equalTo(4)));
        assertThat(actual.toList().get(1), is(equalTo(2)));
        assertThat(actual.toList().get(2), is(equalTo(3)));
    }

    @Test
    public void apply_A$ObjectArray() throws Exception {
        IndexedSeq<Integer> actual = IndexedSeq.apply(1, 2, 3);
        assertThat(actual.size(), is(equalTo(3)));
    }

    @Test
    public void apply_A$Collection() throws Exception {
        List<Integer> list_ = new ArrayList<Integer>();
        list_.add(1);
        list_.add(2);
        list_.add(3);
        IndexedSeq<Integer> actual = IndexedSeq.apply(list_);
        assertThat(actual.size(), is(equalTo(3)));
    }

    @Test
    public void __A$ObjectArray() throws Exception {
        IndexedSeq<Integer> actual = IndexedSeq.apply(1, 2, 3);
        assertThat(actual.size(), is(equalTo(3)));
    }

    @Test
    public void __A$Collection_List() throws Exception {
        List<Integer> list_ = new ArrayList<Integer>();
        list_.add(1);
        list_.add(2);
        list_.add(3);
        IndexedSeq<Integer> actual = IndexedSeq.apply(list_);
        assertThat(actual.size(), is(equalTo(3)));
    }

    @Test
    public void __A$Collection_Set() throws Exception {
        Set<Integer> list_ = new HashSet<Integer>();
        list_.add(1);
        list_.add(2);
        list_.add(3);
        IndexedSeq<Integer> actual = IndexedSeq.apply(list_);
        assertThat(actual.size(), is(equalTo(3)));
    }

    @Test
    public void union_A$Seq() throws Exception {
        IndexedSeq<Integer> seq = IndexedSeq.apply(1, 2, 3);
        Seq<Integer> that = IndexedSeq.apply(2, 3, 4);
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
        IndexedSeq<Integer> seq = IndexedSeq.apply(1, 2, 3);
        SNum actual = seq.sum();
        assertThat(actual.toInt(), is(equalTo(6)));
    }

    @Test
    public void sum_A$_Int_containsNull() throws Exception {
        IndexedSeq<Integer> seq = IndexedSeq.apply(1, 2, null, 3);
        SNum actual = seq.sum();
        assertThat(actual.toInt(), is(equalTo(6)));
    }

    @Test
    public void sum_A$_Double() throws Exception {
        IndexedSeq<Double> seq = IndexedSeq.apply(0.1D, 0.2D, 0.3D);
        SNum actual = seq.sum();
        assertThat(actual.toDouble(), is(equalTo(0.6D)));
    }

    @Test
    public void sum_A$_Long() throws Exception {
        IndexedSeq<Long> seq = IndexedSeq.apply(1L, 2L, 3L);
        SNum actual = seq.sum();
        assertThat(actual.toLong(), is(equalTo(6L)));
    }

    @Test(expected = ScalaFlavor4JException.class)
    public void sum_A$_String() throws Exception {
        IndexedSeq<String> seq = IndexedSeq.apply("a", "b", "c");
        SNum actual = seq.sum();
        assertThat(actual.toInt(), is(equalTo(6)));
    }

    @Test
    public void max_A$() throws Exception {
        IndexedSeq<Integer> seq = IndexedSeq.apply(1, 4, 5, 2, 3);
        SNum actual = seq.max();
        assertThat(actual.toInt(), is(equalTo(5)));
    }

    @Test
    public void max_A$_containsNull() throws Exception {
        IndexedSeq<Integer> seq = IndexedSeq.apply(1, 4, 5, null, 2, 3);
        SNum actual = seq.max();
        assertThat(actual.toInt(), is(equalTo(5)));
    }

    @Test(expected = ScalaFlavor4JException.class)
    public void max_A$_String() throws Exception {
        IndexedSeq<String> seq = IndexedSeq.apply("a", "b", "c");
        SNum actual = seq.max();
        assertThat(actual.toInt(), is(equalTo(5)));
    }

    @Test
    public void min_A$() throws Exception {
        IndexedSeq<Integer> seq = IndexedSeq.apply(4, 5, 2, 1, 3);
        SNum actual = seq.min();
        assertThat(actual.toInt(), is(equalTo(1)));
    }

    @Test
    public void min_A$_containsNull() throws Exception {
        IndexedSeq<Integer> seq = IndexedSeq.apply(4, 5, 2, null, 1, 3);
        SNum actual = seq.min();
        assertThat(actual.toInt(), is(equalTo(1)));
    }

    @Test(expected = ScalaFlavor4JException.class)
    public void min_A$_String() throws Exception {
        IndexedSeq<String> seq = IndexedSeq.apply("a", "b", "c");
        SNum actual = seq.min();
        assertThat(actual.toInt(), is(equalTo(1)));
    }

    @Test
    public void sameElements_A$Nil() throws Exception {
        IndexedSeq<Integer> seq = IndexedSeq.apply();
        {
            boolean actual = seq.sameElements(IndexedSeq.apply(1, 2, 3));
            assertThat(actual, is(equalTo(false)));
        }
        {
            IndexedSeq<Integer> nil = IndexedSeq.apply();
            boolean actual = seq.sameElements(nil);
            assertThat(actual, is(equalTo(true)));
        }
    }

    @Test
    public void sameElements_A$Seq_true() throws Exception {
        IndexedSeq<Integer> seq = IndexedSeq.apply(1, 2, 3);
        IndexedSeq<Integer> that = IndexedSeq.apply(1, 2, 3);
        boolean actual = seq.sameElements(that);
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void sameElements_A$Seq_containsNull() throws Exception {
        IndexedSeq<Integer> seq = IndexedSeq.apply(1, 2, null, 3);
        IndexedSeq<Integer> that = IndexedSeq.apply(1, 2, null, 3);
        boolean actual = seq.sameElements(that);
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void sameElements_A$Seq_false() throws Exception {
        IndexedSeq<Integer> seq = IndexedSeq.apply(1, 2, 3);
        {
            IndexedSeq<Integer> that = IndexedSeq.apply(1, 2, 3, 4);
            assertThat(seq.sameElements(that), is(false));
        }
        {
            IndexedSeq<Integer> that = IndexedSeq.apply(3, 2, 1);
            assertThat(seq.sameElements(that), is(false));
        }
    }

    @Test
    public void intersect_A$Seq_Nil() throws Exception {
        IndexedSeq<Integer> seq = IndexedSeq.apply();
        IndexedSeq<Integer> that = IndexedSeq.apply(3, 4, 5);
        Seq<Integer> actual = seq.intersect(that);
        assertThat(actual.size(), is(equalTo(0)));
    }

    @Test
    public void intersect_A$Seq() throws Exception {
        IndexedSeq<Integer> seq = IndexedSeq.apply(1, 2, 3, 4, 5);
        IndexedSeq<Integer> that = IndexedSeq.apply(3, 4, 5, 6, 7);
        Seq<Integer> actual = seq.intersect(that);
        assertThat(actual.size(), is(equalTo(3)));
        assertThat(actual.toList().get(0), is(equalTo(3)));
        assertThat(actual.toList().get(1), is(equalTo(4)));
        assertThat(actual.toList().get(2), is(equalTo(5)));
    }

    @Test
    public void mkString_A$String$String$String() throws Exception {
        IndexedSeq<String> seq = IndexedSeq.apply("090", "1111", "2222");
        String start = "Tel:";
        String sep = "-";
        String end = "";
        String actual = seq.mkString(start, sep, end);
        String expected = "Tel:090-1111-2222";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void mkString_A$() throws Exception {
        IndexedSeq<Integer> seq = IndexedSeq.apply(1, 2, 3, 4, 5);
        String actual = seq.mkString();
        String expected = "12345";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void padTo_A$int$Object_minusValue() throws Exception {
        IndexedSeq<Integer> seq = IndexedSeq.apply(1, 2, 3);
        int len = -3;
        Seq<Integer> actual = seq.padTo(len, 9);
        assertThat(actual.size(), is(equalTo(3)));
        assertThat(actual.mkString(), is(equalTo("123")));
    }

    @Test
    public void padTo_A$int$Object_lessLength() throws Exception {
        IndexedSeq<Integer> seq = IndexedSeq.apply(1, 2, 3);
        int len = 2;
        Seq<Integer> actual = seq.padTo(len, 9);
        assertThat(actual.size(), is(equalTo(3)));
        assertThat(actual.mkString(), is(equalTo("123")));
    }

    @Test
    public void padTo_A$int$Object_sameLength() throws Exception {
        IndexedSeq<Integer> seq = IndexedSeq.apply(1, 2, 3);
        int len = 3;
        Seq<Integer> actual = seq.padTo(len, 9);
        assertThat(actual.size(), is(equalTo(3)));
        assertThat(actual.mkString(), is(equalTo("123")));
    }

    @Test
    public void padTo_A$int$Object() throws Exception {
        IndexedSeq<Integer> seq = IndexedSeq.apply(1, 2, 3);
        int len = 5;
        Seq<Integer> actual = seq.padTo(len, 9);
        assertThat(actual.size(), is(equalTo(5)));
        assertThat(actual.mkString(), is(equalTo("12399")));
    }

    @Test
    public void scanLeft_A$Object$Function2() throws Exception {
        IndexedSeq<Integer> seq = IndexedSeq.apply(1, 2, 3);
        Seq<Integer> actual = seq.scanLeft(0, new F2<Integer, Integer, Integer>() {
            public Integer apply(Integer acm, Integer i) {
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
    public void scanLeft_A$Object() throws Exception {
        IndexedSeq<Integer> seq = IndexedSeq.apply(1, 2, 3);
        Seq<Integer> actual = seq.scanLeft(0).apply(new F2<Integer, Integer, Integer>() {
            public Integer apply(Integer acm, Integer i) {
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
        IndexedSeq<Integer> seq = IndexedSeq.apply(1, 2, 3);
        Seq<Integer> actual = seq.scanRight(0, new F2<Integer, Integer, Integer>() {
            public Integer apply(Integer acm, Integer i) {
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
    public void scanRight_A$Object() throws Exception {
        IndexedSeq<Integer> seq = IndexedSeq.apply(1, 2, 3);
        Seq<Integer> actual = seq.scanRight(0).apply(new F2<Integer, Integer, Integer>() {
            public Integer apply(Integer acm, Integer i) {
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
        IndexedSeq<Integer> seq = IndexedSeq.apply(1, 2, 3);
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
    public void toString_A$() throws Exception {
        Seq<String> seq = IndexedSeq.apply("foo", "bar", "baz");
        String actual = seq.toString();
        String expected = "Seq(foo,bar,baz)";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void dropNull_A$() throws Exception {
        Seq<String> seq = IndexedSeq.apply("foo", null, null, "bar", null, "baz", null);
        Seq<String> withoutNull = seq.dropNull();
        assertThat(withoutNull.size(), is(equalTo(3)));
        assertThat(withoutNull.toString(), is(equalTo("Seq(foo,bar,baz)")));
    }

    @Test
    public void reduceLeft_A$Function2() throws Exception {
        Seq<String> seq = IndexedSeq.apply("foo", "bar", "baz");
        Integer actual = seq.reduceLeft(new FoldLeftF2<Integer, String>() {
            public Integer apply(Integer z, String e) throws Exception {
                return z != null ? z + e.length() : e.length();
            }
        });
        assertThat(actual, is(equalTo(9)));
    }

    @Test
    public void reduceLeftOption_A$Function2() throws Exception {
        Seq<String> seq = IndexedSeq.apply("foo", "bar", "baz");
        Option<Integer> actual = seq.reduceLeftOption(new FoldLeftF2<Integer, String>() {
            public Integer apply(Integer z, String e) throws Exception {
                return z != null ? z + e.length() : e.length();
            }
        });
        assertThat(actual.isDefined(), is(true));
        assertThat(actual.getOrNull(), is(equalTo(9)));
    }

    @Test
    public void reduceRight_A$Function2() throws Exception {
        Seq<String> seq = IndexedSeq.apply("foo", "bar", "baz");
        Integer actual = seq.reduceRight(new FoldRightF2<String, Integer>() {
            public Integer apply(String e, Integer z) throws Exception {
                return z != null ? z + e.length() : e.length();
            }
        });
        assertThat(actual, is(equalTo(9)));
    }

    @Test
    public void reduceRightOption_A$Function2() throws Exception {
        Seq<String> seq = IndexedSeq.apply("foo", "bar", "baz");
        Option<Integer> actual = seq.reduceRightOption(new FoldRightF2<String, Integer>() {
            public Integer apply(String e, Integer z) throws Exception {
                return z != null ? z + e.length() : e.length();
            }
        });
        assertThat(actual.isDefined(), is(true));
        assertThat(actual.getOrNull(), is(equalTo(9)));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void init_A$_Nil() throws Exception {
        Seq<String> seq = IndexedSeq.apply();
        seq.init();
    }

    @Test
    public void init_A$() throws Exception {
        Seq<String> seq = IndexedSeq.apply("foo", "bar", "baz");
        Seq<String> actual = seq.init();
        assertThat(actual.size(), is(equalTo(2)));
        assertThat(actual.mkString(","), is(equalTo("foo,bar")));
    }

    @Test
    public void par_A$() throws Exception {
        Seq<Integer> seq = SInt.apply(1).to(10000);
        ParSeq<Integer> parSeq = seq.par();
        assertThat(parSeq, notNullValue());
        parSeq.foreach(new VoidF1<Integer>() {
            public void apply(Integer v1) throws Exception {
            }
        });
    }

    @Test
    public void instantiation() throws Exception {
        Collection<Integer> list = Arrays.asList(1, 2, 3);
        IndexedSeq<Integer> seq = new IndexedSeq<Integer>(list);
        assertThat(seq, notNullValue());
    }

    @Test
    public void corresponds_A$Seq() throws Exception {
        Seq<String> seq = IndexedSeq.apply("foo", "bar", "baz");
        F2<String, String, Boolean> predicate = new F2<String, String, Boolean>() {
            public Boolean apply(String v1, String v2) throws Exception {
                return v1.equals(v2);
            }
        };
        assertThat(seq.corresponds(IndexedSeq.apply("foo", "bar", "baz")).apply(predicate), is(true));
        assertThat(seq.corresponds(IndexedSeq.apply("foo", "bar", "bar")).apply(predicate), is(false));
        assertThat(seq.corresponds(IndexedSeq.apply("foo", "bar")).apply(predicate), is(false));
        assertThat(seq.corresponds(IndexedSeq.apply("foo")).apply(predicate), is(false));
        assertThat(seq.corresponds(IndexedSeq.apply("bar", "foo", "baz")).apply(predicate), is(false));
    }

    @Test
    public void corresponds_A$Seq$Function2() throws Exception {
        Seq<String> seq = IndexedSeq.apply("foo", "bar", "baz");
        F2<String, String, Boolean> predicate = new F2<String, String, Boolean>() {
            public Boolean apply(String v1, String v2) throws Exception {
                return v1.equals(v2);
            }
        };
        assertThat(seq.corresponds(IndexedSeq.apply("foo", "bar", "baz"), predicate), is(true));
        assertThat(seq.corresponds(IndexedSeq.apply("foo", "bar", "bar"), predicate), is(false));
        assertThat(seq.corresponds(IndexedSeq.apply("foo", "bar"), predicate), is(false));
        assertThat(seq.corresponds(IndexedSeq.apply("foo"), predicate), is(false));
        assertThat(seq.corresponds(IndexedSeq.apply("bar", "foo", "baz"), predicate), is(false));
    }

    @Test
    public void transpose_A$_3_2() throws Exception {
        Seq<Seq<Integer>> seq = Seq.apply(Seq.apply(1, 2, 3), Seq.apply(4, 5, 6));
        Seq<Seq<Integer>> transposed = seq.transpose();
        assertThat(transposed.head().mkString(","), is("1,4"));
        assertThat(transposed.tail().head().mkString(","), is("2,5"));
        assertThat(transposed.tail().tail().head().mkString(","), is("3,6"));
    }

    @Test
    public void transpose_A$_3_3() throws Exception {
        Seq<Seq<Integer>> seq = Seq.apply(Seq.apply(1, 2, 3), Seq.apply(4, 5, 6), Seq.apply(7, 8, 9));
        Seq<Seq<Integer>> transposed = seq.transpose();
        assertThat(transposed.head().mkString(","), is("1,4,7"));
        assertThat(transposed.tail().head().mkString(","), is("2,5,8"));
        assertThat(transposed.tail().tail().head().mkString(","), is("3,6,9"));
    }

    @Test
    public void transpose_A$_4_3() throws Exception {
        Seq<Seq<Integer>> seq = Seq.apply(Seq.apply(1, 2, 3, 4), Seq.apply(5, 6, 7, 8), Seq.apply(9, 10, 11, 12));
        Seq<Seq<Integer>> transposed = seq.transpose();
        assertThat(transposed.head().mkString(","), is("1,5,9"));
        assertThat(transposed.tail().head().mkString(","), is("2,6,10"));
        assertThat(transposed.tail().tail().head().mkString(","), is("3,7,11"));
        assertThat(transposed.tail().tail().tail().head().mkString(","), is("4,8,12"));
    }

}
