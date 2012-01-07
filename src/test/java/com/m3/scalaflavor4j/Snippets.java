package com.m3.scalaflavor4j;

import static com.m3.scalaflavor4j.ExceptionControl.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.m3.scalaflavor4j.ExceptionControl.Catch;

/**
 * How to use scalaflavor4j
 */
public class Snippets {

    final VoidFunction1<Object> print = new VoidFunction1<Object>() {
        public void _(Object v1) {
            System.out.println(v1);
        }
    };

    /**
     * {@link com.m3.scalaflavor4j.Seq}
     */
    @Test
    public void seq() {

        // same type parameters
        Seq<Integer> oneTwoThree1 = Seq._(1, 2, 3);
        assertThat(oneTwoThree1.mkString(","), is(equalTo("1,2,3")));

        // _ java.util.List
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        Seq<Integer> oneTwoThree2 = Seq._(list);
        assertThat(oneTwoThree2.mkString(","), is(equalTo("1,2,3")));

        // to java.util.List
        List<Integer> javaList = oneTwoThree1.toList();
        assertThat(javaList.get(0), is(equalTo(1)));
    }

    /**
     * {@link com.m3.scalaflavor4j.Function1},
     * {@link com.m3.scalaflavor4j.Function2},
     * {@link com.m3.scalaflavor4j.Function3},
     * {@link com.m3.scalaflavor4j.Function4},
     * {@link com.m3.scalaflavor4j.Function5}
     */
    @Test
    public void function() throws Exception {

        // instantiate Function1
        Function1<String, Integer> getLength = new Function1<String, Integer>() {
            public Integer _(String v1) {
                return v1.length();
            }
        };
        Integer len1 = getLength._("foo");
        assertThat(len1, is(equalTo(3)));

        // instantiate and then _
        int len2 = new Function1<String, Integer>() {
            public Integer _(String v1) {
                return v1.length();
            }
        }.apply("fooo");
        assertThat(len2, is(equalTo(4)));

        // F1 is a shorter alias
        int len3 = new F1<String, Integer>() {
            public Integer _(String v1) {
                return v1.length();
            }
        }.apply("foooo");
        assertThat(len3, is(equalTo(5)));

        // andThen
        F1<Integer, Long> intToLong = new F1<Integer, Long>() {
            public Long _(Integer v1) {
                return v1.longValue();
            }
        };
        Function1<String, Long> getLengthAsLong = getLength.andThen(intToLong);
        Long len4 = getLengthAsLong.apply("fooooo");
        assertThat(len4, is(equalTo(6L)));

        // compose
        F1<Integer, String> intToString = new F1<Integer, String>() {
            public String _(Integer v1) {
                return v1.toString();
            }
        };
        Function1<Integer, Integer> getDigit = getLength.compose(intToString);
        Integer digit = getDigit.apply(12345);
        assertThat(digit, is(equalTo(5)));

        F2<String, Integer, Boolean> lengthChecker = new F2<String, Integer, Boolean>() {
            public Boolean _(String v1, Integer v2) {
                return v1.length() == v2;
            }
        };
        // tupled
        F1<Tuple2<String, Integer>, Boolean> lengthCheckerTupled = lengthChecker.tupled();
        boolean res0 = lengthCheckerTupled.apply(Tuple2._("foo", 3));
        assertThat(res0, is(true));

        // currying
        boolean res1 = lengthChecker._("foo", 3);
        Function1<String, Function1<Integer, Boolean>> curriedLengthChecker = lengthChecker.curried();
        boolean res2 = curriedLengthChecker.apply("foo").apply(3);
        assertThat(res1 == res2, is(true));

        // void function
        VoidFunction1<String> printTwice = new VoidFunction1<String>() {
            public void _(String v1) {
                System.out.println(v1 + v1);
            }
        };
        printTwice.apply("foo");

        // VoidF1 is a shorter alias
        new VoidF1<String>() {
            public void _(String v1) throws Exception {
                print.apply(v1);
            }
        }.apply("fooo");
    }

    /**
     * {@link com.m3.scalaflavor4j.Seq#foreach(VoidFunction1)}
     */
    @Test
    public void foreach() {
        Seq._(1, 2, 3).foreach(new VoidFunction1<Integer>() {
            public void _(Integer i) throws Exception {
                print.apply(i);
            }
        });
        Seq._(1, 2, 3).foreach(new VoidF1<Integer>() {
            public void _(Integer i) throws Exception {
                print.apply(i);
            }
        });
    }

    /**
     * {@link com.m3.scalaflavor4j.Seq#filter(Function1)}
     */
    @Test
    public void filter() {
        assertThat(Seq._(1, 2, 3, 4, 5).filter(new F1<Integer, Boolean>() {
            public Boolean _(Integer i) {
                return i > 2;
            }
        }).mkString(","), is(equalTo("3,4,5")));
    }

    /**
     * {@link com.m3.scalaflavor4j.Seq#map(Function1)}
     */
    @Test
    public void map() {
        assertThat(Seq._(1, 2, 3, 4, 5).map(new F1<Integer, Long>() {
            public Long _(Integer i) {
                return Long.valueOf(i * 2);
            }
        }).mkString(","), is(equalTo("2,4,6,8,10")));
    }

    /**
     * {@link com.m3.scalaflavor4j.Seq#flatMap(Function1)}
     */
    @Test
    public void flatMap() {
        assertThat(Seq._(1, 2, 3, 4, 5).flatMap(new F1<Integer, CollectionLike<Integer>>() {
            public Seq<Integer> _(Integer i) {
                return SInt._(1).to(i);
            }
        }).mkString(","), is(equalTo("1,1,2,1,2,3,1,2,3,4,1,2,3,4,5")));

        assertThat(Seq._(1, 2, null, 3, null, null, 4, 5).flatMap(new F1<Integer, CollectionLike<Integer>>() {
            public Option<Integer> _(Integer i) {
                return Option._(i);
            }
        }).mkString(","), is(equalTo("1,2,3,4,5")));
    }

    /**
     * {@link com.m3.scalaflavor4j.Seq#foldLeft(Object, Function2)}
     */
    @Test
    public void foldLeft() {
        assertThat(Seq._(1, 2, 3, 4, 5).foldLeft(0, new FoldLeftF2<Integer, Integer>() {
            public Integer _(Integer sum, Integer i) {
                return sum + i;
            }
        }), is(equalTo(15)));
    }

    /**
     * {@link com.m3.scalaflavor4j.Seq#union(Seq)}
     */
    @Test
    public void union() {
        assertThat(Seq._(1, 2, 3).union(Seq._(2, 3, 4)).mkString(","), is(equalTo("1,2,3,2,3,4")));
    }

    /**
     * {@link com.m3.scalaflavor4j.Option}, {@link com.m3.scalaflavor4j.Some},
     * {@link com.m3.scalaflavor4j.None}
     */
    @Test
    public void option() {
        // Some
        Option<Integer> some = Option._(3);
        assertThat(some.isDefined(), is(true));
        assertThat(some.getOrNull(), is(3));
        some.foreach(new VoidFunction1<Integer>() {
            public void _(Integer i) throws Exception {
                print.apply("The value " + i + " will be printed.");
            }
        });

        // None
        Option<Integer> none = Option._(null);
        assertThat(none.isDefined(), is(false));
        assertThat(none.getOrNull(), is(nullValue()));
        none.foreach(new VoidF1<Integer>() {
            public void _(Integer i) throws Exception {
                print.apply("The value " + i + " will be printed.");
            }
        });
    }

    /**
     * {@link com.m3.scalaflavor4j.Seq#sortWith(Function2)}
     */
    @Test
    public void sortWith() {
        Seq<Integer> sorted = Seq._(2, 1, 4, 3, 5).sortWith(new F2<Integer, Integer, Boolean>() {
            public Boolean _(Integer v1, Integer v2) {
                return v1 < v2;
            }
        });
        assertThat(sorted.mkString(","), is(equalTo("1,2,3,4,5")));
    }

    /**
     * {@link com.m3.scalaflavor4j.Seq#head()},
     * {@link com.m3.scalaflavor4j.Seq#headOption()}
     */
    @Test
    public void head() {
        // head
        Seq<Integer> seq = Seq._(1, 2, 3);
        Integer h = seq.head();
        assertThat(h, is(equalTo(1)));

        // headOption
        Option<Integer> hopt = seq.headOption();
        assertThat(hopt.isDefined(), is(true));
        assertThat(hopt.getOrNull(), is(equalTo(1)));
    }

    /**
     * {@link com.m3.scalaflavor4j.Seq#tail()}
     */
    @Test
    public void tail() {
        Seq<Integer> t = Seq._(1, 2, 3).tail();
        assertThat(t.mkString(","), is(equalTo("2,3")));
    }

    /**
     * {@link com.m3.scalaflavor4j.Seq#last()},
     * {@link com.m3.scalaflavor4j.Seq#lastOption()}
     */
    @Test
    public void last() {
        Seq<Integer> seq = Seq._(1, 2, 3);
        Integer l = seq.last();
        assertThat(l, is(equalTo(3)));
        Option<Integer> lopt = seq.lastOption();
        assertThat(lopt.isDefined(), is(true));
        assertThat(lopt.getOrNull(), is(equalTo(3)));
    }

    /**
     * {@link com.m3.scalaflavor4j.SInt#to(Integer)},
     * {@link com.m3.scalaflavor4j.SInt#until(Integer)},
     * {@link com.m3.scalaflavor4j.SLong#to(Long)},
     * {@link com.m3.scalaflavor4j.SLong#until(Long)}
     */
    @Test
    public void range() {
        // SInt
        Seq<Integer> oneToFive = SInt._(1).to(5);
        assertThat(oneToFive.mkString(","), is(equalTo("1,2,3,4,5")));
        Seq<Integer> oneUntilFive = SInt._(1).until(5);
        assertThat(oneUntilFive.mkString(","), is(equalTo("1,2,3,4")));

        // SLong
        Seq<Long> oneToFiveL = SLong._(1L).to(5L);
        assertThat(oneToFiveL.mkString(","), is(equalTo("1,2,3,4,5")));
        Seq<Long> oneUntilFiveL = SLong._(1L).until(5L);
        assertThat(oneUntilFiveL.mkString(","), is(equalTo("1,2,3,4")));
    }

    /**
     * {@link com.m3.scalaflavor4j.Seq#reverse()},
     * {@link com.m3.scalaflavor4j.Seq#reverseMap(Function1)}
     */
    @Test
    public void reverse() {
        // reverse
        Seq<Integer> reversed = Seq._(1, 2, 3).reverse();
        assertThat(reversed.mkString(","), is(equalTo("3,2,1")));

        // reverseMap
        Seq<Long> reversedLong = Seq._(1, 2, 3).reverseMap(new Function1<Integer, Long>() {
            public Long _(Integer v1) {
                return v1.longValue();
            }
        });
        assertThat(reversedLong.mkString(","), is(equalTo("3,2,1")));
    }

    /**
     * {@link com.m3.scalaflavor4j.Seq#distinct()}
     */
    @Test
    public void distinct() {
        Seq<Integer> distinct = Seq._(1, 3, 2, 2, 2, 1, 3, 3).distinct();
        assertThat(distinct.mkString(","), is(equalTo("1,3,2")));
    }

    /**
     * {@link com.m3.scalaflavor4j.Seq#take(int)},
     * {@link com.m3.scalaflavor4j.Seq#takeRight(int)},
     * {@link com.m3.scalaflavor4j.Seq#takeWhile(Function1)}
     */
    @Test
    public void take() {
        // take
        Seq<Integer> take3 = Seq._(1, 2, 3, 4, 5).take(3);
        assertThat(take3.mkString(","), is(equalTo("1,2,3")));

        // takeRight
        Seq<Integer> takeRight3 = Seq._(1, 2, 3, 4, 5).takeRight(3);
        assertThat(takeRight3.mkString(","), is(equalTo("3,4,5")));

        // takeWhile
        Seq<Integer> takeWhile = Seq._(1, 3, 5, 2, 4).takeWhile(new F1<Integer, Boolean>() {
            public Boolean _(Integer v1) {
                return v1 < 5;
            }
        });
        assertThat(takeWhile.mkString(","), is(equalTo("1,3")));

    }

    /**
     * {@link com.m3.scalaflavor4j.Seq#drop(int)},
     * {@link com.m3.scalaflavor4j.Seq#dropRight(int)},
     * {@link com.m3.scalaflavor4j.Seq#dropWhile(Function1)},
     */
    @Test
    public void drop() {
        // drop
        Seq<Integer> drop3 = Seq._(1, 2, 3, 4, 5).drop(3);
        assertThat(drop3.mkString(","), is(equalTo("4,5")));

        // dropRight
        Seq<Integer> dropRight3 = Seq._(1, 2, 3, 4, 5).dropRight(3);
        assertThat(dropRight3.mkString(","), is(equalTo("1,2")));

        // dropWhile
        Seq<Integer> dropWhile = Seq._(1, 3, 5, 2, 4).dropWhile(new F1<Integer, Boolean>() {
            public Boolean _(Integer v1) {
                return v1 < 5;
            }
        });
        assertThat(dropWhile.mkString(","), is(equalTo("5,2,4")));
    }

    /**
     * {@link com.m3.scalaflavor4j.SMap}
     */
    @Test
    public void smap() {
        // _ java.util.Map
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("Andy", 21);
        map.put("Brian", 18);
        map.put("Charley", 27);
        SMap<String, Integer> nameAndAge = SMap._(map);
        assertThat(nameAndAge.toList().size(), is(equalTo(3)));

        // foreach
        nameAndAge.foreach(new VoidF1<Tuple2<String, Integer>>() {
            public void _(Tuple2<String, Integer> v1) {
                System.out.println(v1._1());
            }
        });

        // filter
        SMap<String, Integer> withoutCharley = nameAndAge.filter(new Function1<Tuple2<String, Integer>, Boolean>() {
            public Boolean _(Tuple2<String, Integer> v1) {
                return v1._1().contains("n");
            }
        });
        assertThat(withoutCharley.toList().size(), is(equalTo(2)));

        // to java.util.Map
        Map<String, Integer> javaMap = nameAndAge.toMap();
        assertThat(javaMap.get("Andy"), is(equalTo(21)));
        assertThat(javaMap.get("Brian"), is(equalTo(18)));
        assertThat(javaMap.get("Charley"), is(equalTo(27)));
    }

    /**
     * {@link com.m3.scalaflavor4j.Seq#slice(int, int)}
     */
    @Test
    public void slice() {
        Seq<Integer> seq = Seq._(1, 2, 3, 4, 5);
        Seq<Integer> sliced = seq.slice(2, 4);
        assertThat(sliced.mkString(","), is(equalTo("3,4")));
    }

    /**
     * {@link com.m3.scalaflavor4j.Seq#partition(Function1)},
     * {@link com.m3.scalaflavor4j.Seq#span(Function1)}
     */
    @Test
    public void partitionAndSpan() {
        // partition
        Seq<Integer> seq = Seq._(2, 3, 5, 1, 4);
        Tuple2<Seq<Integer>, Seq<Integer>> partition = seq.partition(new F1<Integer, Boolean>() {
            public Boolean _(Integer v1) {
                return v1 < 3;
            }
        });
        assertThat(partition._1().mkString(","), is(equalTo("2,1")));
        assertThat(partition._2().mkString(","), is(equalTo("3,5,4")));

        // span
        Tuple2<Seq<Integer>, Seq<Integer>> span = seq.span(new F1<Integer, Boolean>() {
            public Boolean _(Integer v1) {
                return v1 < 3;
            }
        });
        assertThat(span._1().mkString(","), is(equalTo("2")));
        assertThat(span._2().mkString(","), is(equalTo("3,5,1,4")));
    }

    /**
     * {@link com.m3.scalaflavor4j.Seq#sliding(int, int)}
     */
    @Test
    public void sliding() {
        Seq<Integer> seq = Seq._(1, 2, 3, 4, 5);
        Seq<Seq<Integer>> sliding = seq.sliding(3, 2);
        assertThat(sliding.size(), is(equalTo(2)));
        assertThat(sliding.toList().get(0).mkString("[", ",", "]"), is(equalTo("[1,2,3]")));
        assertThat(sliding.toList().get(1).mkString("[", ",", "]"), is(equalTo("[3,4,5]")));
    }

    /**
     * {@link com.m3.scalaflavor4j.Seq#sum()},
     * {@link com.m3.scalaflavor4j.Seq#min()},
     * {@link com.m3.scalaflavor4j.Seq#max()}
     */
    @Test
    public void number() {
        Seq<Integer> seq = Seq._(2, 3, 5, 1, 4);
        SNum sum = seq.sum();
        assertThat(sum.toInt(), is(equalTo(15)));
        SNum min = seq.min();
        assertThat(min.toInt(), is(equalTo(1)));
        SNum max = seq.max();
        assertThat(max.toInt(), is(equalTo(5)));
    }

    /**
     * {@link com.m3.scalaflavor4j.Seq#zip(Seq)},
     * {@link com.m3.scalaflavor4j.Seq#zipWithIndex()}
     */
    @Test
    public void zip() {
        // zip
        Seq<Integer> seq = Seq._(1, 2, 3);
        Seq<Tuple2<Integer, Long>> zipped = seq.zip(Seq._(4L, 5L));

        assertThat(zipped.size(), is(equalTo(2)));
        assertThat(zipped.toList().get(0)._1(), is(equalTo(1)));
        assertThat(zipped.toList().get(0)._2(), is(equalTo(4L)));
        assertThat(zipped.toList().get(1)._1(), is(equalTo(2)));
        assertThat(zipped.toList().get(1)._2(), is(equalTo(5L)));

        // zipWithIndex
        Seq<Tuple2<Integer, Integer>> zippedWithIndex = seq.zipWithIndex();

        assertThat(zippedWithIndex.size(), is(equalTo(3)));
        assertThat(zippedWithIndex.toList().get(0)._1(), is(equalTo(1)));
        assertThat(zippedWithIndex.toList().get(0)._2(), is(equalTo(0)));
        assertThat(zippedWithIndex.toList().get(1)._1(), is(equalTo(2)));
        assertThat(zippedWithIndex.toList().get(1)._2(), is(equalTo(1)));
        assertThat(zippedWithIndex.toList().get(2)._1(), is(equalTo(3)));
        assertThat(zippedWithIndex.toList().get(2)._2(), is(equalTo(2)));
    }

    /**
     * {@link com.m3.scalaflavor4j.Seq#groupBy(Function1)}
     */
    @Test
    public void groupBy() {
        Seq<String> domains = Seq._("yahoo.com", "google.com", "amazon.com", "facebook.com", "linkedin.com",
                "twitter.com");
        SMap<Integer, Seq<String>> domainLengthList = domains.groupBy(new F1<String, Integer>() {
            public Integer _(String v1) {
                return v1.length();
            }
        });
        Map<Integer, Seq<String>> lenAndList = domainLengthList.toMap();
        assertThat(lenAndList.get(9).mkString(","), is(equalTo("yahoo.com")));
        assertThat(lenAndList.get(10).mkString(","), is(equalTo("google.com,amazon.com")));
        assertThat(lenAndList.get(11).mkString(","), is(equalTo("twitter.com")));
        assertThat(lenAndList.get(12).mkString(","), is(equalTo("facebook.com,linkedin.com")));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void exceptionControl_catching() throws Exception {
        // catching
        String result = catching(Exception.class).withApply(new F1<Throwable, String>() {
            public String _(Throwable t) {
                return "catched";
            }
        }).andFinally(new VoidFunction0() {
            public void _() {
                System.out.println("finally called");
            }
        }).apply(new Function0<String>() {
            public String _() {
                throw new RuntimeException();// -> result : "catched"
            }
        });
        assertThat(result, is(equalTo("catched")));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void exceptionControl_handlingBy() throws Exception {
        // catching
        String result = handling(Exception.class).by(new F1<Throwable, String>() {
            public String _(Throwable t) {
                return "catched";
            }
        }).apply(new Function0<String>() {
            public String _() {
                throw new RuntimeException();// -> result : "catched"
            }
        });
        assertThat(result, is(equalTo("catched")));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void exceptionControl_ignoring() throws Exception {
        Catch<String> ignoring = ignoring(Exception.class);
        String result = ignoring.apply(new Function0<String>() {
            public String _() {
                throw new RuntimeException();// -> result : null
            }
        });
        assertThat(result, is(notNullValue()));
    }

    @Test
    public void exceptionControl_ultimately() throws Exception {
        Catch<String> ultimately = ultimately(new VoidF0() {
            public void _() throws Exception {
                System.out.println("every time finally called");
            }
        });
        String result = ultimately.apply(new F0<String>() {
            public String _() throws Exception {
                return "foo"; // -> result : "foo"
            }
        });
        assertThat(result, is(notNullValue()));
    }

    @Test
    public void exceptionControl_allCatch() throws Exception {
        String result = allCatch().withApply(new F1<Throwable, String>() {
            public String _(Throwable t) {
                return "catched";
            }
        }).apply(new Function0<String>() {
            public String _() {
                return "ok"; // -> result : "ok"
                // throw new AException(); -> result : "catched"
                // throw new IOException(); -> will be thrown
            }
        });
        assertThat(result, is(equalTo("ok")));
    }

    class Name {
        String first;
        String last;

        Name(String first, String last) {
            this.first = first;
            this.last = last;
        }
    }

    @Test
    @SuppressWarnings({ "unused", "unchecked" })
    public void patternMatching() throws Exception {
        /**
         * <pre>
         * str match {
         *   case str: String if str.legnth > 100 => println("large")
         *   case str: String if str.matches(".*[0-9]+.*") => println("has num")
         *   case str: String => println("anyway string")
         * }
         * </pre>
         */

        CaseClause<Integer, Void> intCase = CaseClause._case(Integer.class)._arrow(new F1<Integer, Void>() {
            public Void _(Integer i) throws Exception {
                System.out.println("int value");
                return null;
            }
        });

        CaseClause<String, Void> largeStrCase = CaseClause._case(String.class)._if(new Guard<String>() {
            public Boolean _(String str) {
                return str.length() > 100;
            }
        })._arrow(new F1<String, Void>() {
            public Void _(String v1) throws Exception {
                System.out.println("large str");
                return null;
            }
        });

        CaseClause<Name, Void> nameCase = CaseClause._case(new Extractor<Name>() {
            public Name extract(Object v) {
                if (v instanceof Name) {
                    return (Name) v;
                }
                return null;
            }
        })._arrow(new F1<Name, Void>() {
            public Void _(Name v) {
                System.out.println("name object");
                return null;
            }
        });

        CaseClause<Object, Void> objectCase = CaseClause._case(Object.class)._arrow(new F1<Object, Void>() {
            public Void _(Object v) {
                System.out.println("object");
                return null;
            }
        });

        PartialFunction<Void> intOnly = PartialF.<Void> _(intCase);
        intOnly.apply(123); // "int value"
        try {
            intOnly.apply("aaaa......"); // MatchError (RuntimeException)
            fail();
        } catch (MatchError e) {
        }

        PartialFunction<Void> largeStrAndName = PartialF.<Void> _(largeStrCase, nameCase);
        try {
            largeStrAndName.apply(123); // MatchError (RuntimeException)
            fail();
        } catch (MatchError e) {
        }
        largeStrAndName.apply("aaaa......"); // "large str"
        largeStrAndName.apply(new Name("Martin", "Odersky")); // "name object"

        PartialFunction<Void> intAndLargeStrAndName = intOnly.orElse(largeStrAndName);
        PartialFunction<Void> all = intAndLargeStrAndName.orElse(PartialF.<Void> _(objectCase));

    }
}
