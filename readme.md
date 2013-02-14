# ScalaFlavor4J

ScalaFlavor4J provides you Scala flavored useful API in Java.

# Setup

## Maven

ScalaFlavor4J is available on the maven central repository. 
Add the following dependency:

```xml
<dependencies>
  <dependency>
    <groupId>com.m3</groupId>
    <artifactId>scalaflavor4j</artifactId>
    <version>[1.1,)</version>
  </dependency>
</dependencies>
```

# Migration from version 1.0.x

- `apply(xxx)` methods are removed because the name is deprecated on Java 8. Please use `apply(xxx)` instead.

- FunctionN are changed from abstract class to interface due to JSR-335 specification. If you want to use `compose` or `andThen`, please decorate the instance with `RichFunctionN`.


# Introduction

In the beginning, please view this presentation. 
Sorry, We can't put iframe here.

<a href="http://www.slideshare.net/seratch/scalaflavor4j-10953097" title="ScalaFlavor4J" target="_blank">ScalaFlavor4J(slideshare)</a>


# Overview

First of all, just import the following package.

```java
import com.m3.scalaflavor4j.*;
import static com.m3.scalaflavor4j.Predef.*; // optional
```

## Function

Provides `scala.Function*`.

### Creation

Scala:

```scala
val getLength: Function1[String, Int] = (v: String) => v.length
val getLength: (String) => Int = (v: String) => v.length
val len = getLength("foo")
val len = getLength.apply("foo")
```

ScalaFlavor4J on Java 8:

```java
F1<String, Integer> getLength = (v) -> v.length();
int len = getLength.apply("foo"); // -> len : 3
```

### VoidFunction 

VoidFunction* does not exist in Scala.

```java
VoidFunction1<Object> print = (v) -> System.out.println(v);
print.apply("foo"); // "foo"
```

## Option

Provides `scala.Option`.

Scala:

```scala
val some = Option(3)
some.isDefined
some.orNull(null)
some.getOrElse(0)
some map { i => "found : " + i } getOrElse { "not found" }
some fold ("not found"){ i => "found : " + i }

val none: Option[Int] = Option.empty
none.isDefined
none.getOrElse(0)
none map { i => "found : " + i } getOrElse { "not found" }
none fold ("not found"){ i => "found : " + i }
```

ScalaFlavor4J on Java 8:

```java
Option<Integer> some = Option.apply(3);
some.isDefined(); // true
some.getOrNull(); // 3
some.getOrElse(0); // 3

some.map(i -> "found : " + i).getOrElse(() -> "not found");
// -> "found : 3"

some.fold("not found").apply(i -> "found : " + i);
// -> "found : 3"

Option<Integer> none = Option.apply(null); // or Option.none();
none.isDefined(); // false
none.getOrNull(); // null
none.getOrElse(0); // 0

none.map(i -> "found : " + i).getOrElse(() -> "not found");
// -> "not found"

none.fold("not found").apply(i -> "found : " + i);
// -> "not found"
```


## Seq

Provides `scala.collection.Seq`.

### creation

Scala:

```scala
val words = Seq("foo", "bar", "baz")
```

ScalaFlavor4J on Java 8:

Using variable arguments:

```java
import java.util.*;
Seq<String> words = Seq.apply("foo", "bar", "baz");
```

Or apply existing `java.util.List` object:

```java
List<String> wordList = Arrays.asList("foo", "bar", "baz");
Seq<String> words = Seq.apply(wordList);
```

Back to `java.util.List`:

```java
List<String> wordList = words.toList();
```

### map / flatMap

Scala:

```scala
Seq(1, 2, 3, 4, 5) map { (i: Int) => (i * 2).toLong }

Seq(1, 2, 3, 4, 5) flatMap { (i: Int) => 1 to i } mkString(",")
Seq(Some(1), Some(2), None, Some(3), None, Some(4), Some(5)) flatMap { (e: Option[Int]) => e }
```

ScalaFlavor4J on Java 8:

```java
Seq.apply(1, 2, 3, 4, 5).map(i -> Long.valueOf(i * 2));
// -> Seq.apply(2L, 4L, 6L, 8L, 10L)

Seq.apply(1, 2, 3, 4, 5).flatMap(i -> SInt.apply(1).to(i)).mkString(","); 
// -> "1,1,2,1,2,3,1,2,3,4,1,2,3,4,5"

Seq.apply(1, 2, null, 3, null, 4, 5).flatMap(i -> Option.apply(i)).mkString(","); 
// -> "1,2,3,4,5"
```

### foldLeft / foldRight

Scala:

```scala
Seq('b', 'c', 'd').foldLeft("a"){ (z: String, c: Char) => z + c }
Seq('b', 'c', 'd').foldRight("a"){ (c: Char, z: String) => z + c }
```

ScalaFlavor4J on Java 8:

```java
String s = Seq.apply('b', 'c', 'd').foldLeft("a", (z, c) -> z + c);
// -> s : "abcd"

String s = Seq.apply('b', 'c', 'd').foldRight("a", (c, z) -> z + c);
// -> s : "adcb"
```

### filter

Scala:

```scala
Seq(1, 2, 3, 4, 5) filter { (i: Int) => i > 2 }
```

ScalaFlavor4J on Java 8:

```java
Seq.apply(1, 2, 3, 4, 5).filter(i -> i > 2);
// -> Seq.apply(3, 4, 5)
```

### foreach

Scala:

```scala
Seq(1, 2, 3, 4, 5) foreach { (i: Int) => println(i) }
```

ScalaFlavor4J on Java 8:

```java
Seq.apply(1, 2, 3, 4, 5).foreach(i -> println(i));
// -> "1" "2" "3" "4" "5"
```

## Parallel Collection

Provides `scala.collection.parallel.ParSeq`. ScalaFlavor4J does not support all the methods that are defined in Scala because some methods are not efficient.

Scala:

```scala
(1 to 1000).par.foreach { (i) => print(Thread.currentThread.getId + ",") }
(1 to 1000).par.map { (i) => print(Thread.currentThread.getId + ","); i * i }
(1 to 1000).par.flatMap { (i) => print(Thread.currentThread.getId + ","); 1 to i }
```

ScalaFlavor4J on Java 8:

```java
SInt.apply(1).to(1000).par().foreach(i -> println(Thread.currentThread.getId() + ","));

SInt.apply(1).to(1000).par().map(i -> {
  println(Thread.currentThread.getId() + ",");
  return i * i;
});

SInt.apply(1).to(1000).par().flatMap(i -> {
  println(Thread.currentThread.getId() + ",");
  return SInt.apply(1).to(i);
});
```


## For Comprehension

Provides functions that are similar to for-comprehension.

Scala:

```scala
val xs1 = Seq("abc", "abcd", "abcde")
val xs2 = Seq(3, 4, 5)
val bs = for (a <- xs1; b <- xs2) yield a.length == b
```

ScalaFlavor4J on Java 8:

```java
Seq<String> xs1 = Seq.apply("abc", "abcd", "abcde");
Seq<Integer> xs2 = Seq.apply(3, 4, 5);

Seq<Boolean> bs = For.apply(xs1, xs2).yield(tpl -> tpl._1().length() == tpl._2());
// true, false, false, false, true, false, false, false, true
```


## SMap

Provides `scala.collection.Map`.

### creation

Scala:

```scala
val sMap = Map("Andy" -> 21, "Brian" -> 18, "Charley" -> 27)
```

ScalaFlavor4J on Java 8:

Apply an existing `java.util.Map` object:

```java
import java.util.*;
Map<String, Integer> ageList = new HashMap<String, Integer>();
ageList.put("Andy", 21);
ageList.put("Brian", 18);
ageList.put("Charley", 27);

SMap<String, Integer> sMap = SMap.apply(ageList);
```

Back to `java.util.Map`:

```java
Map<String, Integer> javaMap = sMap.toMap();
```

### getOrElse

Scala:

```scala
val age: Int = sMap.getOrElse("Denis", -1)
```

ScalaFlavor4J on Java 8:

```java
int age = sMap.getOrElse("Denis", -1); 
// -> age : -1
```

### foreach / filter

Scala:

```scala
sMap foreach { case (k, v) => println(k) }

val withoutCharley = sMap filter { case (k, v) => k.contains("n") }
```

ScalaFlavor4J on Java 8:

```java
sMap.foreach(v1 -> println(v1._1()));
// -> "Andy" "Brian" "Charley"

SMap<String, Integer> withoutCharley = sMap.filter(v1 -> v1._1().contains("n"));
// -> withoutCharley : ("Andy" -> 21, "Brian" -> 18)
```

### updated / plus / minus

```java
SMap<String, Integer> newMap = sMap.updated("Denis", 24); 
// -> newMap : ("Andy" -> 21, "Brian" -> 18, "Charley" -> 27, "Denis" -> 24)

SMap<String, Integer> newMap = sMap.plus(Pair.apply("Denis", 24), Pair.apply("Elle", 19)); 
// -> newMap : ("Andy" -> 21, "Brian" -> 18, "Charley" -> 27, "Denis" -> 24, "Elle" -> 19)

SMap<String, Integer> newMap = sMap.minus("Charley", "Andy"); 
// -> newMap : ("Brian" -> 18)
```


## SInt / SLong

Scala:

```scala
val oneToFile: Seq[Int] = 1 to 5
val oneUntilFive: Seq[Int] = 1 until 5
```

Java:

```java
Seq<Integer> oneToFive = SInt.apply(1).to(5); 
// -> oneToFive : Seq.apply(1, 2, 3, 4, 5)

Seq<Integer> oneUntilFive = SInt.apply(1).until(5); 
// oneUntilFive : Seq.apply(1, 2, 3, 4)
```


## ConcurrentOps

Provides `scala.concurrent.ops._`

### spawn

Scala:

```scala
import scala.concurrent.ops._
spawn { println("on a different thread!") }
```

ScalaFlavor4J on Java 8:

```java
import static com.m3.scalaflavor4j.ConcurrentOps.*;
spawn(() -> println("on a different thread!"));
```

### future

Scala:

```scala
val f = future { Thread.sleep(1000L); "foo" }
```

ScalaFlavor4J on Java 8:

```java
Function0<String> f = future(() -> {
  Thread.sleep(1000L);
  return "foo";
});
f.apply(); // -> "foo"
```


## ExceptionControl

Provides `scala.util.control.Exception._`.

### catching

Scala:

```scala
import scala.util.control.Exception._
val result: String = 
  catching(classOf[RuntimeException]) withApply { 
    (t) => "catched" 
  } apply { 
    throw new RuntimeException 
  }
```

ScalaFlavor4J on Java 8:

```java
import static com.m3.scalaflavor4j.ExceptionControl.*;
String result = catching(RuntimeException.class)
  .withApply(t -> "catched")
  .apply(() -> {
    // return "ok"; -> result : "ok"
    // throw new IOException(); -> will be thrown
    throw new RuntimeException();
  });
```


## Scala ARM like

Inspired by ["Scala Automatic Resource Management"](https://github.com/jsuereth/scala-arm)(Scala Incubator project), but not implemeneted exactly.

```java
import static com.m3.scalaflavor4j.arm.Resource.*;
String content = managed(new FileInputStream("input.txt")).map(is -> "content");
// finally FileInputStream will be closed
```


## Source

Provides `scala.io.Source`.

```java
BufferedSource source = Source.fromFile("input.txt", "UTF-8");
BufferedSource source = Source.fromURL("http://docs.scala-lang.org/cheatsheets/", "UTF-8");

Seq<Byte> bs = source.toByteSeq():
Seq<Character> cs = source.toCharSeq():
Seq<String> lines = source.getLines();
```


# Cookbook for Java 6,7


You can read more details abouts ScalaFlavor4J in [Cookbook](https://github.com/m3dev/scalaflavor4j/wiki/Cookbook).


# License


Apache License, Version 2.0 

http://www.apache.org/licenses/LICENSE-2.0.html


