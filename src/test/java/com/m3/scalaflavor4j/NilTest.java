package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class NilTest {

    @Test
    public void type() throws Exception {
        assertThat(Nil.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        Nil<String> target = Nil.<String> apply();
        assertThat(target, notNullValue());
    }

    @Test
    public void __A$() throws Exception {
        Nil<Object> actual = Nil.apply();
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void toList_A$() throws Exception {
        Nil<String> target = Nil.<String> apply();
        List<String> actual = target.toList();
        assertThat(actual, is(notNullValue()));
    }

    @Test
    public void isEmpty_A$() throws Exception {
        Nil<String> target = Nil.<String> apply();
        boolean actual = target.isEmpty();
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void map_A$F1() throws Exception {
        Nil<String> target = Nil.<String> apply();
        F1<String, Object> f = new F1<String, Object>() {
            public Object apply(String v1) {
                return null;
            }
        };
        CollectionLike<Object> actual = target.map(f);
        assertThat(actual, is(notNullValue()));
    }

    @Test
    public void foreach_A$F1() throws Exception {
        Nil<String> target = Nil.<String> apply();
        target.foreach(new VoidF1<String>() {
            public void apply(String v1) {
            }
        });
    }

    @Test
    public void filter_A$F1() throws Exception {
        Nil<String> target = Nil.<String> apply();
        F1<String, Boolean> predicate = new F1<String, Boolean>() {
            public Boolean apply(String v1) {
                return true;
            }
        };
        CollectionLike<String> actual = target.filter(predicate);
        assertThat(actual, is(notNullValue()));
    }

    @Test
    public void flatMap_A$F1() throws Exception {
        Nil<String> target = Nil.<String> apply();
        F1<String, CollectionLike<Integer>> f = new F1<String, CollectionLike<Integer>>() {
            public CollectionLike<Integer> apply(String v1) {
                return null;
            }
        };
        Seq<Integer> actual = target.flatMap(f);
        assertThat(actual, is(notNullValue()));
    }

    @Test
    public void head_A$() throws Exception {
        Nil<String> target = Nil.<String> apply();
        Object actual = target.head();
        assertThat(actual, is(nullValue()));
    }

    @Test
    public void tail_A$() throws Exception {
        Nil<String> target = Nil.<String> apply();
        Seq<String> actual = target.tail();
        assertThat(actual, is(notNullValue()));
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void map_A$Function1() throws Exception {
        Nil<String> target = Nil.<String> apply();
        F1<String, String> f = new F1<String, String>() {
            public String apply(String v1) {
                return null;
            }
        };
        CollectionLike<String> actual = target.map(f);
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void foreach_A$VoidFunction1() throws Exception {
        Nil<String> target = Nil.<String> apply();
        target.foreach(new VoidF1<String>() {
            public void apply(String v1) {
            }
        });
    }

    @Test
    public void filter_A$Function1() throws Exception {
        Nil<String> target = Nil.<String> apply();
        F1<String, Boolean> f = new F1<String, Boolean>() {
            public Boolean apply(String v1) {
                return true;
            }
        };
        CollectionLike<String> actual = target.filter(f);
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void flatMap_A$Function1() throws Exception {
        Nil<String> target = Nil.<String> apply();
        F1<String, CollectionLike<String>> f = new F1<String, CollectionLike<String>>() {
            public CollectionLike<String> apply(String v1) {
                return null;
            }
        };
        Seq<String> actual = target.flatMap(f);
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void headOption_A$() throws Exception {
        Nil<String> target = Nil.<String> apply();
        Option<String> actual = target.headOption();
        assertThat(actual.isDefined(), is(false));
    }

    @Test
    public void mkString_A$String() throws Exception {
        Nil<String> target = Nil.<String> apply();
        String sep = "-";
        String actual = target.mkString(sep);
        String expected = "";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void sortWith_A$Function2() throws Exception {
        Nil<String> target = Nil.<String> apply();
        F2<String, String, Boolean> lt = new F2<String, String, Boolean>() {
            public Boolean apply(String v1, String v2) {
                return null;
            }
        };
        Seq<String> actual = target.sortWith(lt);
        assertThat(actual.isEmpty(), is(true));
    }

    public void take_A$int_0() throws Exception {
        Nil<String> target = Nil.<String> apply();
        int n = 0;
        Seq<String> actual = target.take(n);
        assertThat(actual.isEmpty(), is(true));
    }

    public void take_A$int_1() throws Exception {
        Nil<String> target = Nil.<String> apply();
        int n = 0;
        Seq<String> actual = target.take(n);
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void foldLeft_A$Object$Function2() throws Exception {
        Nil<String> target = Nil.<String> apply();
        String z = "";
        F2<String, String, String> op = new F2<String, String, String>() {
            public String apply(String v1, String v2) {
                return v1 + v2;
            }
        };
        String actual = target.foldLeft(z, op);
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void foldRight_A$Object$Function2() throws Exception {
        Nil<String> target = Nil.<String> apply();
        String z = "";
        F2<String, String, String> op = new F2<String, String, String>() {
            public String apply(String v1, String v2) {
                return v1 + v2;
            }
        };
        String actual = target.foldRight(z, op);
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void foldLeft_A$Object() throws Exception {
        Nil<String> target = Nil.<String> apply();
        String z = "";
        F2<String, String, String> op = new F2<String, String, String>() {
            public String apply(String v1, String v2) {
                return v1 + v2;
            }
        };
        String actual = target.foldLeft(z).apply(op);
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void foldRight_A$Object() throws Exception {
        Nil<String> target = Nil.<String> apply();
        String z = "";
        F2<String, String, String> op = new F2<String, String, String>() {
            public String apply(String v1, String v2) {
                return v1 + v2;
            }
        };
        String actual = target.foldRight(z).apply(op);
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void distinct_A$() throws Exception {
        Nil<String> target = Nil.<String> apply();
        Seq<String> actual = target.distinct();
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void zipWithIndex_A$() throws Exception {
        Nil<String> target = Nil.<String> apply();
        Seq<Tuple2<String, Integer>> actual = target.zipWithIndex();
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void size_A$() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        Integer actual = nil.size();
        Integer expected = 0;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void groupBy_A$Function1() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        SMap<String, Seq<String>> actual = nil.groupBy(new F1<String, String>() {
            public String apply(String v1) {
                return v1;
            }
        });
        assertThat(actual, is(notNullValue()));
    }

    @Test
    public void last_A$() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        String actual = nil.last();
        Object expected = null;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void lastOption_A$() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        Option<String> actual = nil.lastOption();
        assertThat(actual.isDefined(), is(false));
    }

    @Test
    public void find_A$Function1() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        F1<String, Boolean> p = new F1<String, Boolean>() {
            public Boolean apply(String v1) {
                return true;
            }
        };
        Option<String> actual = nil.find(p);
        assertThat(actual.isDefined(), is(false));
    }

    @Test
    public void partition_A$Function1() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        F1<String, Boolean> p = new F1<String, Boolean>() {
            public Boolean apply(String v1) {
                return true;
            }
        };
        Tuple2<Seq<String>, Seq<String>> actual = nil.partition(p);
        assertThat(actual._1().isEmpty(), is(true));
        assertThat(actual._2().isEmpty(), is(true));
    }

    @Test
    public void reverse_A$() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        Seq<String> actual = nil.reverse();
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void slice_A$int$int() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        Integer from = 1;
        Integer until = 2;
        Seq<String> actual = nil.slice(from, until);
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void sliding_A$int() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        Integer size = 3;
        Seq<Seq<String>> actual = nil.sliding(size);
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void sliding_A$int$int() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        Integer size = 2;
        Integer step = 2;
        Seq<Seq<String>> actual = nil.sliding(size, step);
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void span_A$Function1() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        F1<String, Boolean> p = new F1<String, Boolean>() {
            public Boolean apply(String v1) {
                return true;
            }
        };
        Tuple2<Seq<String>, Seq<String>> actual = nil.span(p);
        assertThat(actual._1().isEmpty(), is(true));
        assertThat(actual._2().isEmpty(), is(true));
    }

    @Test
    public void takeRight_A$int() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        int n = 3;
        Seq<String> actual = nil.takeRight(n);
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void takeWhile_A$Function1() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        F1<String, Boolean> p = new F1<String, Boolean>() {
            public Boolean apply(String v1) {
                return true;
            }
        };
        Seq<String> actual = nil.takeWhile(p);
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void splitAt_A$int() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        Integer n = 3;
        Tuple2<Seq<String>, Seq<String>> actual = nil.splitAt(n);
        assertThat(actual._1().isEmpty(), is(true));
        assertThat(actual._2().isEmpty(), is(true));
    }

    @Test
    public void exists_A$Function1() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        boolean actual = nil.exists(new F1<String, Boolean>() {
            public Boolean apply(String v1) {
                return true;
            }
        });
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void forall_A$Function1() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        boolean actual = nil.forall(new F1<String, Boolean>() {
            public Boolean apply(String v1) {
                return true;
            }
        });
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void count_A$Function1() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        Integer actual = nil.count(new F1<String, Boolean>() {
            public Boolean apply(String v1) {
                return true;
            }
        });
        Integer expected = 0;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void drop_A$int() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        int n = 3;
        Seq<String> actual = nil.drop(n);
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void dropRight_A$int() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        int n = 3;
        Seq<String> actual = nil.dropRight(n);
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void dropWhile_A$Function1() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        Seq<String> actual = nil.dropWhile(new F1<String, Boolean>() {
            public Boolean apply(String v1) {
                return v1.equals("foo");
            }
        });
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void zip_A$Seq() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        Seq<String> that = Seq.apply("a", "b", "c");
        Seq<Tuple2<String, String>> actual = nil.zip(that);
        assertThat(actual.size(), is(equalTo(0)));
    }

    @Test
    public void contains_A$Object() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        String elem = "foo";
        boolean actual = nil.contains(elem);
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void diff_A$Seq() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        Seq<String> that = Seq.apply("foo", "bar");
        Seq<String> actual = nil.diff(that);
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void startsWith_A$Seq() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        Seq<String> that = Seq.apply("a", "b", "c");
        boolean actual = nil.startsWith(that);
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void startsWith_A$Seq$int() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        Seq<String> that = Seq.apply("a", "b", "c");
        int offset = 0;
        boolean actual = nil.startsWith(that, offset);
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void endsWith_A$Seq() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        Seq<String> that = Seq.apply("a", "b", "c");
        boolean actual = nil.endsWith(that);
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void indexOf_A$Object() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        String elem = "foo";
        int actual = nil.indexOf(elem);
        int expected = -1;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void isDefinedAt_A$int() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        for (int i = -3; i < 3; i++) {
            boolean actual = nil.isDefinedAt(i);
            boolean expected = false;
            assertThat(actual, is(equalTo(expected)));
        }
    }

    @Test
    public void indices_A$() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        Seq<Integer> actual = nil.indices();
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void reverseMap_A$Function1() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        Seq<Integer> actual = nil.reverseMap(new F1<String, Integer>() {
            public Integer apply(String v1) {
                return v1.length();
            }
        });
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void patch_A$int$Seq$int() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        int from = 2;
        Seq<String> patch = Seq.apply("a", "b", "c");
        int replaced = 3;
        Seq<String> actual = nil.patch(from, patch, replaced);
        assertThat(actual.size(), is(equalTo(3)));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void updated_A$int$Object() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        int index = 0;
        String elem = "foo";
        Seq<String> actual = nil.updated(index, elem);
        assertThat(actual.size(), is(equalTo(1)));
    }

    @Test
    public void union_A$Seq() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        Seq<String> that = Seq.apply("a", "b", "c");
        Seq<String> actual = nil.union(that);
        assertThat(actual.size(), is(equalTo(3)));
    }

    @Test
    public void sum_A$() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        SNum actual = nil.sum();
        assertThat(actual.toInt(), is(equalTo(0)));
    }

    @Test
    public void max_A$() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        SNum actual = nil.max();
        assertThat(actual.toInt(), is(equalTo(0)));
    }

    @Test
    public void min_A$() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        SNum actual = nil.min();
        assertThat(actual.toInt(), is(equalTo(0)));
    }

    @Test
    public void sameElements_A$Seq() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        Seq<String> that = Seq.apply("a", "b");
        boolean actual = nil.sameElements(that);
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void sameElements_A$Seq_true() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        Seq<String> that = Seq.apply();
        boolean actual = nil.sameElements(that);
        boolean expected = true;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void intersect_A$Seq() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        Seq<String> that = Seq.apply();
        Seq<String> actual = nil.intersect(that);
        assertThat(actual.isEmpty(), is(equalTo(true)));
    }

    @Test
    public void mkString_A$String$String$String() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        String start = "a";
        String sep = "";
        String end = "b";
        String actual = nil.mkString(start, sep, end);
        String expected = "ab";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void mkString_A$() throws Exception {
        Nil<String> nil = Nil.<String> apply();
        String actual = nil.mkString();
        String expected = "";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void padTo_A$int$Object_minus() throws Exception {
        Nil<String> nil = Nil.apply();
        int len = -1;
        Seq<String> actual = nil.padTo(len, "a");
        assertThat(actual.size(), is(equalTo(0)));
    }

    @Test
    public void padTo_A$int$Object() throws Exception {
        Nil<String> nil = Nil.apply();
        int len = 2;
        Seq<String> actual = nil.padTo(len, "a");
        assertThat(actual.size(), is(equalTo(2)));
    }

    @Test
    public void scanLeft_A$Object$Function2() throws Exception {
        Seq<Integer> seq = Nil.apply();
        Seq<Integer> actual = seq.scanLeft(0, new F2<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer acm, Integer i) {
                return acm + i;
            }
        });
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void scanLeft_A$Object() throws Exception {
        Seq<Integer> seq = Nil.apply();
        Seq<Integer> actual = seq.scanLeft(0).apply(new F2<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer acm, Integer i) {
                return acm + i;
            }
        });
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void scanRight_A$Object$Function2() throws Exception {
        Seq<Integer> seq = Nil.apply();
        Seq<Integer> actual = seq.scanRight(0, new F2<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer acm, Integer i) {
                return acm + i;
            }
        });
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void scanRight_A$Object() throws Exception {
        Seq<Integer> seq = Nil.apply();
        Seq<Integer> actual = seq.scanRight(0).apply(new F2<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer acm, Integer i) {
                return acm + i;
            }
        });
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void filterNot_A$Function1() throws Exception {
        Seq<Integer> seq = Nil.apply();
        Seq<Integer> actual = seq.filterNot(new Function1<Integer, Boolean>() {
            public Boolean apply(Integer v1) {
                return true;
            }
        });
        assertThat(actual.isEmpty(), is(true));
    }

    @Test
    public void apply_A$() throws Exception {
        Nil<Object> actual = Nil.apply();
        assertThat(actual, is(notNullValue()));
    }

    @Test
    public void append_A$ObjectArray() throws Exception {
        Seq<Integer> seq = Nil.apply();
        Seq<Integer> actual = seq.append(1, 2, 3);
        assertThat(actual.size(), is(equalTo(3)));
        assertThat(actual.toList().get(0), is(equalTo(1)));
        assertThat(actual.toList().get(1), is(equalTo(2)));
        assertThat(actual.toList().get(2), is(equalTo(3)));
    }

    @Test
    public void toString_A$() throws Exception {
        Seq<Integer> seq = Nil.apply();
        String actual = seq.toString();
        String expected = "Seq()";
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void dropNull_A$() throws Exception {
        Seq<Integer> seq = Nil.apply();
        Seq<Integer> actual = seq.dropNull();
        String expected = "Seq()";
        assertThat(actual.toString(), is(equalTo(expected)));
    }

    @Test
    public void reduceLeft_A$Function2() throws Exception {
        Seq<Integer> seq = Nil.apply();
        Integer actual = seq.reduceLeft(new FoldLeftF2<Integer, Integer>() {
            public Integer apply(Integer z, Integer e) throws Exception {
                return z;
            }
        });
        assertThat(actual, is(nullValue()));
    }

    @Test
    public void reduceLeftOption_A$Function2() throws Exception {
        Seq<Integer> seq = Nil.apply();
        Option<Integer> actual = seq.reduceLeftOption(new FoldLeftF2<Integer, Integer>() {
            public Integer apply(Integer z, Integer e) throws Exception {
                return z;
            }
        });
        assertThat(actual.isDefined(), is(false));
    }

    @Test
    public void reduceRight_A$Function2() throws Exception {
        Seq<Integer> seq = Nil.apply();
        Integer actual = seq.reduceRight(new FoldRightF2<Integer, Integer>() {
            public Integer apply(Integer z, Integer e) throws Exception {
                return z;
            }
        });
        assertThat(actual, is(nullValue()));
    }

    @Test
    public void reduceRightOption_A$Function2() throws Exception {
        Seq<Integer> seq = Nil.apply();
        Option<Integer> actual = seq.reduceRightOption(new FoldRightF2<Integer, Integer>() {
            public Integer apply(Integer z, Integer e) throws Exception {
                return z;
            }
        });
        assertThat(actual.isDefined(), is(false));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void init_A$() throws Exception {
        Seq<Integer> seq = Nil.apply();
        seq.init();
    }

    @Test
    public void par_A$() throws Exception {
        Seq<Integer> seq = Nil.apply();
        ParSeq<Integer> actual = seq.par();
        assertThat(actual, notNullValue());
    }

    @Test
    public void corresponds_A$Seq() throws Exception {
        Seq<Integer> seq = Nil.apply();
        assertThat(seq.corresponds(Nil.<Integer> apply()).apply(new F2<Integer, Integer, Boolean>() {
            public Boolean apply(Integer v1, Integer v2) throws Exception {
                return false;
            }
        }), is(true));
    }

    @Test
    public void corresponds_A$Seq$Function2() throws Exception {
        Seq<Integer> seq = Nil.apply();
        assertThat(seq.corresponds(Nil.<Integer> apply(), new F2<Integer, Integer, Boolean>() {
            public Boolean apply(Integer v1, Integer v2) throws Exception {
                return false;
            }
        }), is(true));
    }
}
