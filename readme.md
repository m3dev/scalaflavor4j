# ScalaFlavor4J

ScalaFlavor4J provides you Scala flavored useful API in Java.

# pom.xml

```xml
<repositories>
  <repository>
    <id>m3dev.github.com releases</id>
    <name>m3dev.github.com releases</name>
    <url>http://m3dev.github.com/mvn-repo/releases</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>com.m3.scalaflavor4j</groupId>
    <artifactId>scalaflavor4j</artifactId>
    <version>0.1</version>
  </dependency>
</dependencies>
```

# Download 

http://m3dev.github.com/mvn-repo/releases/com/m3/scalaflavor4j/scalaflavor4j/0.1/scalaflavor4j-0.1.jar

# Snippets

https://github.com/m3dev/scalaflavor4j/blob/master/src/test/java/com/m3/scalaflavor4j/Snippets.java

# Overview

First of all, just import the following package.

```java
import com.m3.scalaflavor4j.*;
```

## Function

Provides `scala.Function*`.

### creation

```java
// val getLength: Function1[String, Int] = (v: String) => v.length
// val getLength: (String) => Int = (v: String) => v.length
// getLength("foo")
// getLength.apply("foo")

Function1<String, Integer> getLength = new Function1<String, Integer>() {
  public Integer _(String v) { 
    return v.length(); 
  }
};
int len = getLength.apply("foo"); // -> len : 3
int len = getLength._("foo"); // -> len : 3
```

`F*` are shorter aliases for `Function*`.

```java
F1<String, Integer> getLength = new F1<String, Integer>() {
  public Integer _(String v) { 
    return v.length(); 
  }
};
int len = getLength.apply("foo"); // -> len : 3
```

`_` means `apply` method. In Function classes, only `_` is abstract method. `apply` calls `_` internally.

```java
Function1<String, Integer> getLength = new F1<String, Integer>() {
  public Integer _(String v) { 
    return v.length(); 
  }
}
getLength.apply("foo"); // -> 3
getLength._("foo"); // -> 3
```

If your function might throw checked exceptions, add `throws Exception` or try/catch.

```java
// import java.io._
// val getStream: (String) => InputStream = (filename: String) => { new FileInputStream(new File(filename)) }

import java.io.*;
new F1<String, InputStream>() {
  public InputStream _(String filename) throws IOException {
    return new FileInputStream(new File(filename));
  }
}; // IOException might be thrown 
```

### andThen

```java
// val intToLong: (Int) => Long = (v: Int) => v.toLong
// val getLengthAsLong: (String) => Long = getLength andThen intToLong
// val len = getLengthAsLong.apply("bar")

F1<Integer, Long> intToLong = new F1<Integer, Long>() { 
  public Long _(Integer v) { 
    return v.longValue(); 
  } 
};
F1<String, Long> getLengthAsLong = getLength.andThen(intToLong);
long len = getLengthAsLong.apply("bar"); // -> len : 3L
```

### compose

```java
// val intToString: (Int) => String = (v: Int) => v.toString
// val getDigit: (Int) => Int = getLength compose intToString
// getDigit.apply(12345)

F1<Integer, String> intToString = new F1<Integer, String>() { 
  public String _(Integer v) { 
    return v.toString(); 
  } 
};
F1<Integer, Integer> getDigit = getLength.compose(intToString);
int digit = getDigit.apply(12345); // -> digit : 5
```

### tupled

```java
// val lengthChecker: (String, Int) => Boolean = (v1: String, v2: Int) => v1.length == v2
// val tupledChecker = lengthChecker.tupled
// val isValid = tupledChecker.apply(("foo", 3))

F2<String, Integer, Boolean> lengthChecker = new F2<String, Integer, Boolean>() {
  public Boolean _(String v1, Integer v2) { 
    return v1.length() == v2; 
  }
};
F1<Tuple2<String, Integer>, Boolean> tupledChecker = lengthChecker.tupled();
boolean isValid = tupledChecker.apply(Tuple2._("foo", 3)); 
// -> isValid : true
```

### curried

```java
// val curriedChecker: (String) => (Int) => Boolean = lengthChecker.curried
// val isValid = curriedChecker.apply("foo").apply(3)
// val isValid = curriedChecker("foo")(3)

boolean isValid = lengthChecker.apply("foo", 3); 
// -> isValid : true

F1<String, Function1<Integer, Boolean>> curriedChecker = lengthChecker.curried();
boolean isValid = curriedChecker.apply("foo").apply(3); 
boolean isValid = curriedChecker._("foo")._(3);
// -> isValid : true
```

### VoidFunction 

VoidFunction* does not exist in Scala.

```java
VoidFunction1<Object> print = new VoidF1<Object>() {
  public void _(Object v) { 
    System.out.println(v); 
  }
};
print.apply("foo"); // "foo"
print._("foo"); // "foo"
```

## Option

Provides `scala.Option`.

### Good-bye NPE

```java
// val some = Option(3)
// some.isDefined
// some.orNull(null)
// some.getOrElse(0)
// some foreach { (i: Int) => println("The value " + i + " will be printed.") }

Option<Integer> some = Option._(3);
some.isDefined(); // true
some.getOrNull(); // 3
some.getOrElse(0); // 3
some.foreach(new VoidF1<Integer>() {
  public void _(Integer i) { 
    System.out.println("The value " + i + " will be printed."); 
    // -> "The value 3 will be printed."
  }
}); 

Option<Integer> none = Option._(null); // or Option.none();
none.isDefined(); // false
none.getOrNull(); // null
none.getOrElse(0); // 0
none.foreach(new VoidF1<Integer>() {
  public void _(Integer i) { 
    System.out.println("The value " + i + " will be printed."); 
    // slient...
  }
});
```

## Seq

Provides `scala.collection.Seq`.

### creation

```java
// val words = Seq("foo", "bar", "baz")

import java.util.*;
Seq<String> words = Seq._("foo", "bar", "baz");
List<String> wordList = Arrays.asList("foo", "bar", "baz");
Seq<String> words = Seq._(wordList);
List<String> wordList = words.toList();
```

### map

```java
// Seq(1, 2, 3, 4, 5) map { (i: Int) => (i * 2).toLong }

Seq._(1, 2, 3, 4, 5).map(new F1<Integer, Long>() {
  public Long _(Integer i) { 
    return Long.valueOf(i * 2); 
  }
}); 
// -> Seq._(2L, 4L, 6L, 8L, 10L)
```

### flatMap

```java
// Seq(1, 2, 3, 4, 5) flatMap { (i: Int) => 1 to i } mkString(",")
// Seq(Some(1), Some(2), None, Some(3), None, Some(4), Some(5)) flatMap { (e: Option[Int]) => e }

Seq._(1, 2, 3, 4, 5).flatMap(new F1<Integer, CollectionLike<Integer>>() {
  public Seq<Integer> _(Integer i) { 
    return SInt._(1).to(i); 
  }
}).mkString(","); 
// -> "1,1,2,1,2,3,1,2,3,4,1,2,3,4,5"

Seq._(1, 2, null, 3, null, 4, 5).flatMap(new F1<Integer, CollectionLike<Integer>>() {
  public Option<Integer> _(Integer i) { 
    return Option._(i); 
  }
}).mkString(","); 
// -> "1,2,3,4,5"
```

### filter

```java
// Seq(1, 2, 3, 4, 5) filter { (i: Int) => i > 2 }

Seq._(1, 2, 3, 4, 5).filter(new F1<Integer, Boolean>() {
  public Boolean _(Integer i) { 
    return i > 2; 
  }
}); 
// -> Seq._(3, 4, 5)

Seq._(1, 2, 3, 4, 5).filter(new PredicateF1<Integer>() {
  public Boolean _(Integer i) { 
    return i > 2; 
  }
}); 
// -> Seq._(3, 4, 5)
```

### foreach

```java
// Seq(1, 2, 3, 4, 5) foreach { (i: Int) => println(i) }

Seq._(1, 2, 3, 4, 5).foreach(new VoidF1<Integer>() {
  public void _(Integer i) { 
    System.out.println(i); 
    // -> "1" "2" "3" "4" "5"
  }
}); 
```

### foldLeft / foldRight

```java
// Seq('b', 'c', 'd').foldLeft("a"){ (z: String, c: Char) => z + c }

String s = Seq._('b', 'c', 'd').foldLeft("a", new Function2<String, Character, String>() { // or FoldLeftF2<String, Character>
  public String _(String z, Character c) {
    return z + c;
  }
});
// -> s : "abcd"

// Seq('b', 'c', 'd').foldRight("a"){ (c: Char, z: String) => z + c }

String s = Seq._('b', 'c', 'd').foldRight("a", new Function2<Character, String, String>() { // or FoldRightF2<Character, String>
  public String _(Character c, String z) {
    return z + c;
  }
});
// -> s : "adcb"
```

### reduceLeft / reduceRight

```java
// Seq('b', 'c', 'd').reduceLeft{ (z: Any, c: Char) => z + c.toString }

String s = Seq._('b', 'c', 'd').reduceLeft(new Function2<String, Character, String>() {
  public String _(String z, Character c) {
    return z != null ? z + i : c.toString();
  }
});
// -> s : "bcd"

// Seq('b', 'c', 'd').reduceRight{ (c: Char, z: Any) => z + c.toString }

Seq._('b', 'c', 'd').reduceRight(new Function2<Character, String, String>() {
  public String _(Character c, String z) {
    return z != null ? z + c : c.toString();
  }
});
// -> s : "dcb"
```

### union

```java
// Seq(1, 2, 3) union Seq(2, 3, 4)

Seq._(1, 2, 3).union(Seq._(2, 3, 4)); 
// -> Seq._(1, 2, 3, 2, 3, 4)
```

### append

`append` does not exist in Scala. This method is similar to `Seq#+:(elem)`.

```java
Seq<Integer> newSeq = Seq._(1, 2, 3).append(4); 
// -> Seq._(1, 2, 3, 4)
Seq<Integer> newSeq = Seq._(1, 2, 3).append(4, 5); 
// -> Seq._(1, 2, 3, 4, 5)
```

### sortWith

```java
// Seq(2, 1, 4, 3, 5) sortWith { (v1: Int, v2: Int) => v1 < v2 }

Seq._(2, 1, 4, 3, 5).sortWith(new F2<Integer, Integer, Boolean>() {
  public Boolean _(Integer v1, Integer v2) { 
    return v1 < v2; 
  }
});
// -> Seq._(1, 2, 3, 4, 5)
```

### head

```java
// Seq(1, 2, 3) head
// Seq(1, 2, 3) headOption

Integer h = Seq._(1, 2, 3).head(); 
// -> h : 1

Option<Integer> opt = Seq._(1, 2, 3).headOption(); 
// -> opt : Option._(1)
```

### tail

```java
// Seq(1, 2, 3) tail

Seq<Integer> t = Seq._(1, 2, 3).tail(); 
// -> t : Seq._(2, 3)
```

### last

```java
// Seq(1, 2, 3) last
// Seq(1, 2, 3) lastOption

Integer l = Seq._(1, 2, 3).last(); 
// -> l : 3

Option<Integer> opt = Seq._(1, 2, 3).lastOption(); 
// -> opt : Option._(3)
```

### reverse

```java
// Seq(1, 2, 3) reverse

Seq._(1, 2, 3).reverse(); 
// -> Seq._(3, 2, 1)
```

### distinct

```java
// Seq(1, 3, 2, 2, 2, 1, 3, 3) distinct

Seq._(1, 3, 2, 2, 2, 1, 3, 3).distinct(); 
// -> Seq._(1, 3, 2)
```

### take

```java
// Seq(1, 2, 3, 4, 5) take(3)
// Seq(1, 2, 3, 4, 5) takeRight(3)
// Seq(1, 3, 5, 2, 4) takeWhile { (i: Int) => i < 5 }

Seq._(1, 2, 3, 4, 5).take(3); 
// -> Seq._(1, 2 ,3)

Seq._(1, 2, 3, 4, 5).takeRight(3); 
// -> Seq._(3, 4, 5)

Seq._(1, 3, 5, 2, 4).takeWhile(new F1<Integer, Boolean>() {
  public Boolean _(Integer i) { 
    return i < 5; 
  }
}); 
// -> Seq._(1, 3)
```

### drop

```java
// Seq(1, 2, 3, 4, 5) drop(3)
// Seq(1, 2, 3, 4, 5) dropRight(3)
// Seq(1, 3, 5, 2, 4) dropWhile { (i: Int) => i < 5 }

Seq._(1, 2, 3, 4, 5).drop(3); 
// -> Seq._(4, 5)

Seq._(1, 2, 3, 4, 5).dropRight(3); 
// -> Seq._(1, 2)

Seq._(1, 3, 5, 2, 4).dropWhile(new F1<Integer, Boolean>() {
  public Boolean _(Integer i) { 
    return i < 5; 
  }
}); 
// -> Seq._(5, 2, 4)
```

### zip

```java
// val zipped = Seq(1, 2, 3) zip Seq(4L, 5L)
// zipped foreach { case (t1, t2) => println(t1 + " -> " + t2) }

Seq<Tuple2<Integer, Long>> zipped = Seq._(1, 2, 3).zip(Seq._(4L, 5L));
// -> Seq._((1, 4L), (2, 5L))

zipped.foreach(new VoidF1<Tuple2<Integer, Long>>() {
  public void _(Tuple2<Integer, Long> t) { 
    System.out.println(t._1() + " -> " + t._2()); 
    // "1 -> 4L" "2 -> 5L"
  }
});

// val zippedWithIndex = Seq(1, 2, 3) zipWithIndex

Seq<Tuple2<Integer, Integer>> zippedWithIndex = Seq._(1, 2, 3).zipWithIndex();
// -> Seq._((1, 0), (2, 1), (3, 2))
```

### groupBy

```java
// val domains = Seq("yahoo.com", "google.com", "amazon.com", "facebook.com", "linkedin.com", "twitter.com")
// val domainLengths: Map[Int, Seq[String]] = domains.groupBy { (d: String) => d.length }

Seq<String> domains = Seq._("yahoo.com", "google.com", "amazon.com", "facebook.com", "linkedin.com", "twitter.com");
SMap<Integer, Seq<String>> domainLengths = domains.groupBy(new F1<String, Integer>() {
  public Integer _(String d) { 
    return d.length(); 
  }
});

// domainLengths foreach { case (len, ds) => println(len + " -> " + ds.mkString(",")) } 

domainLegths.foreach(new VoidF1<Tuple2<Integer, Seq<String>>() {
  public void _(Tuple2<Integer, Seq<String> e) { 
    System.out.println(e._1() + " -> " + e._2().mkString(",")); 
  }
});
// "9 -> yahoo.com"
// "10 -> google.com,amazon.com"
// "11 -> twitter.com"
// "12 -> facebook.com,linkedin.com"
```

## SMap

Provides `scala.collection.Map`.

### creation

```java
// val sMap = Map("Andy" -> 21, "Brian" -> 18, "Charley" -> 27)

import java.util.*;
Map<String, Integer> ageList = new HashMap<String, Integer>();
ageList.put("Andy", 21);
ageList.put("Brian", 18);
ageList.put("Charley", 27);
SMap<String, Integer> sMap = SMap._(ageList);
Map<String, Integer> javaMap = sMap.toMap();
```

### getOrElse

```java
// val age: Int = sMap.getOrElse("Denis", -1)

int age = sMap.getOrElse("Denis", -1); 
// -> age : -1
```

### foreach

```java
// sMap foreach { case (k, v) => println(k) }

sMap.foreach(new VoidF1<Tuple2<String, Integer>>() {
  public void _(Tuple2<String, Integer> v1) { 
    System.out.println(v1._1()); 
    // -> "Andy" "Brian" "Charley"
  }
}); 
```

### filter

```java
// val withoutCharley = sMap filter { case (k, v) => k.contains("n") }

SMap<String, Integer> withoutCharley = sMap.filter(new F1<Tuple2<String, Integer>, Boolean>() {
    public Boolean _(Tuple2<String, Integer> v1) { 
      return v1._1().contains("n"); 
    }
}); 
// -> withoutCharley : ("Andy" -> 21, "Brian" -> 18)
```

### update

`update` does not exist in Scala. This method is similar to `Map#+(kv)`.

```java
SMap<String, Integer> newMap = sMap.update("Denis", 24); 
// -> newMap : ("Andy" -> 21, "Brian" -> 18, "Charley" -> 27, "Denis" -> 24)
```


## SInt

```java
// val oneToFile: Seq[Int] = 1 to 5
// val oneUntilFive: Seq[Int] = 1 until 5

Seq<Integer> oneToFive = SInt._(1).to(5); 
// -> oneToFive : Seq._(1, 2, 3, 4, 5)

Seq<Integer> oneUntilFive = SInt._(1).until(5); 
// oneUntilFive : Seq._(1, 2, 3, 4)
```


## ExceptionControl

Provides `scala.util.control.Exception.*`.

### allCatch

```java
// import scala.util.control.Exception._
// allCatch withApply { (t) => null } apply { throw new Exception }

import static com.m3.scalaflavor4j.ExceptionControl.*;
String result = allCatch()
  .withApply(new F1<Throwable, String>() {
    public String _(Throwable t) {
      return null;
    }
  })
  .apply(new F0<String>() {
    public String _() {
      throw new Exception(); // -> result : null
      // return "ok"; -> result : "ok"
    }
  });
```

### catching

```java
// import scala.util.control.Exception._
// val result: String = 
//   catching(classOf[RuntimeException]) withApply { 
//     (t) => "catched" 
//   } apply { 
//     throw new RuntimeException 
//   }

import static com.m3.scalaflavor4j.ExceptionControl.*;
String result = catching(RuntimeException.class)
  .withApply(new F1<Throwable, String>() {
    public String _(Throwable t) {
      return "catched";
    }
  })
  .apply(new Function0<String>(){
    public String _() {
      throw new RuntimeException(); // -> result : "catched"
      // return "ok"; -> result : "ok"
      // throw new IOException(); -> will be thrown
    }
  });
```

Re-use `Catch` object.

```java
// val catchingNPE = 
//   catching(classOf[NullPointerException]) withApply {
//     (t) => "npe"
//   } andFinally {
//     println("finally called")
//   }

Catch<String> catchingNPE = catching(NullPointerException.class)
  .withApply(new F1<Throwable, String>() {
    public String _(Throwable t) {
      return "npe";
    }
  })
  .andFinally(new VoidF0() {
    public void _() {
      System.out.println("finally called");
    }
  });

// val result = catchingNPE.apply { throw new NullPointerException("will be catched") }

String result = catchingNPE.apply(new F0<String>() {
  public String _() {
    throw new NullPointerException("will be catched"); // result -> "npe"
  }
});
```

Returns Option or Either. Either implementation is little bit different from Scala.

```java
// val opt = catchingNPE opt { "foo" }

Option<String> opt = catchingNPE.opt(new F0<String>() {
  public String _() {
    return "foo";
  }
});

// val either = catchingNPE either { "foo" }

Either<Throwbale, String> either = catchginNPE.either(new F0<String>() {
  public String _() {
    return "foo";
  }
});
either.isLeft(); // -> false
either.isRight(); // -> true
either.left(); // -> Option.none()
either.right(); // Option._("foo")
```

Create a new Catch with additional exception handling logic by using `or`.

```java
// val result = catchingNPE or (catching(classOf[IOException]) withApply { (t) => "ioe" }) apply {
//   throw new IOException("file not found")
// }

String result = 
  catchingNPE.or(
    catching(IOException.class)
    .withApply(new F1<Throwable, String>(){
      public String _(Throwable t) {
        return "ioe";
      }
    })
    .apply(new F0<String>(){
      public String _() {
        throw new IOException("file not found");  // -> result : "ioe"
        // throw new NullPointerException("will be catched"); -> result: "npe"
      }
    })
  );
```

### handling by

```java
// handling(classOf[RuntimeException]) by { (t) => "" } apply { throw new RuntimeException }

import static com.m3.scalaflavor4j.ExceptionControl.*;
String result = handling(RuntimeException.class)
  .by(new F1<Throwable, String>() {
    public String _(Throwable t) {
      return "";
    }
  })
  .apply(new F0<String>(){
    public String _() {
      throw new RuntimeException(); // -> result : ""
      // return "ok"; // -> result : "ok"
    }
  });
```

### ignoring

```java
// ignoring(classOf[Exception]) apply { throw new RuntimeException }

import static com.m3.scalaflavor4j.ExceptionControl.*;
Catch<String> ignoring = ignoring(Exception.class);
String result = ignoring.apply(new F0<String>() {
  public String _() {
    throw new RuntimeException(); // -> result : null
  }
});
```

### ultimately

```java
// ultimately { println("every time finally called") } apply { "foo" } 

import static com.m3.scalaflavor4j.ExceptionControl.*;
Catch<String> ultimately = ultimately(new VoidF0() {
  public void _() throws Exception {
    System.out.println("every time finally called");
  }
});
String result = ultimately.apply(new F0<String>() {
  public String _() {
    return "foo"; 
  }
});
// -> result : "foo"
```


## Appendix

### Pattern Matching

```java
// case class Name(first: String, last: String)
// def example(arg: Any) = {
//   arg match {
//     case i: Int => println("int value")
//     case str: String if str.length > 100 => println("large str")
//     case name: Name => println("name object")
//     case _ => println("object")
//   }
// }
// example(123) 
// example("aaaa....")
// example(Name("Martin", "Odersky")) 

CaseClause<Integer, Void> intCase = 
  CaseClause
    ._case(Integer.class)
    ._arrow(new F1<Integer, Void>() {
      public Void _(Integer i) {
        System.out.println("int value");
        return null;
      }
    });

CaseClause<String, Void> largeStrCase = 
  CaseClause
    ._case(String.class)
    ._if(new Guard<String>() {
      public Boolean _(String str) {
        return str.length() > 100;
      }
    })
    ._arrow(new F1<String, Void>() {
      public Void _(String v1) {
        System.out.println("large str");
        return null;
      }
    });

CaseClause<Name, Void> nameCase =
  CaseClause
    ._case(new Extractor<Name>() {
      public Name extract(Object v) {
        if (v instanceof Name) {
          return (Name)v;
        }
        return null;
      }
    })
    ._arrow(new F1<Name, Void>() {
      public Void _(Name v) {
        System.out.println("name object");
        return null;
      }
    });

CaseClause<Object, Void> objectCase = 
  CaseClause
    ._case(Object.class)
    ._arrow(new F1<Object, Void>() {
      public Void _(Object v) {
        System.out.println("object");
        return null;
      } 
    });

PartialFunction<Void> intOnly = PartialF.<Void> _(intCase);
intOnly.apply(123); // "int value"
intOnly.apply("aaaa......"); // MatchError (RuntimeException)

PartialFunction<Void> largeStrAndName = PartialF.<Void>_(largeStrCase, nameCase);
largeStrAndName.apply(123); // MatchError (RuntimeException)
largeStrAndName.apply("aaaa......"); // "large str"
largeStrAndName.apply(new Name("Martin", "Odersky")); // "name object"

PartialFunction<Void> intAndLargeStrAndName = intOnly.orElse(largeStrAndName);
PartialFunction<Void> all = intAndLargeStrAndName.orElse(PartialF.<Void>_(objectCase));
```


# License

Apache License, Version 2.0 

http://www.apache.org/licenses/LICENSE-2.0.html


# Contributers

- Kazuhiro Sera <@seratch>
- Toshiyuki Takahashi <@tototoshi>
- Takayuki Murata <@takayuki-tk>

