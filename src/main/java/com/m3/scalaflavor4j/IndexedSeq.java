/*
 * Copyright 2012 M3, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.m3.scalaflavor4j;

import java.math.BigDecimal;
import java.util.*;

/**
 * {@link Seq} implementation
 */
public class IndexedSeq<T> extends Seq<T> {

    private static final long serialVersionUID = 1L;

    /**
     * use Nil when this is empty (because the elements in the list are mutable)
     */
    private final Nil<T> NIL = Nil.apply();

    protected final List<T> list;

    public static <T> IndexedSeq<T> apply(T... values) {
        List<T> list = new ArrayList<T>();
        for (T value : values) {
            list.add(value);
        }
        return apply(list);
    }

    public static <T> IndexedSeq<T> apply(Collection<T> list) {
        return new IndexedSeq<T>(list);
    }

    protected IndexedSeq(Collection<T> list) {
        if (list == null) {
            throw new IllegalArgumentException("List should not be null.");
        }
        this.list = new ArrayList<T>(list);
    }

    @Override
    public IndexedSeq<T> append(T... that) {
        List<T> newList = new ArrayList<T>();
        newList.addAll(toList());
        newList.addAll(Arrays.asList(that));
        return IndexedSeq.apply(newList);
    }

    @Override
    public boolean contains(final T elem) {
        return dropNull().foldLeft(false, new FoldLeftF2<Boolean, T>() {
            public Boolean apply(Boolean contains, T exist) {
                if (!contains) {
                    return exist.equals(elem);
                }
                return contains;
            }
        });
    }

    @Override
    public <U> Function1<Function2<T, U, Boolean>, Boolean> corresponds(final Seq<U> that) {
        if (size() != that.size()) {
            return new F1<Function2<T, U, Boolean>, Boolean>() {
                public Boolean apply(final Function2<T, U, Boolean> p) {
                    return false;
                }
            };
        }
        return new F1<Function2<T, U, Boolean>, Boolean>() {
            public Boolean apply(final Function2<T, U, Boolean> p) {
                return zip(that).forall(new F1<Tuple2<T, U>, Boolean>() {
                    public Boolean apply(Tuple2<T, U> e) throws Exception {
                        return p.apply(e._1(), e._2());
                    }
                });
            }
        };
    }

    @Override
    public <U> boolean corresponds(Seq<U> that, final Function2<T, U, Boolean> p) {
        if (size() != that.size()) {
            return false;
        }
        return zip(that).forall(new F1<Tuple2<T, U>, Boolean>() {
            public Boolean apply(Tuple2<T, U> e) throws Exception {
                return p.apply(e._1(), e._2());
            }
        });
    }

    @Override
    public int count(final Function1<T, Boolean> predicate) {
        return foldLeft(0, new FoldLeftF2<Integer, T>() {
            public Integer apply(Integer count, T element) throws Exception {
                if (predicate.apply(element)) {
                    return count + 1;
                }
                return count;
            }
        });
    }

    @Override
    public IndexedSeq<T> diff(final Seq<T> that) {
        return this.filterNot(new PredicateF1<T>() {
            public Boolean apply(final T thisValue) {
                return that.foldLeft(false, new FoldLeftF2<Boolean, T>() {
                    public Boolean apply(Boolean isFound, T thatValue) {
                        if (!isFound) {
                            if (thisValue == null) {
                                return thatValue == null;
                            }
                            if (thisValue.equals(thatValue)) {
                                return true;
                            }
                        }
                        return isFound;
                    }
                });
            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public IndexedSeq<T> distinct() {
        return foldLeft(IndexedSeq.<T>apply(), new FoldLeftF2<IndexedSeq<T>, T>() {
            public IndexedSeq<T> apply(IndexedSeq<T> distinct, T element) {
                if (!distinct.contains(element)) {
                    return distinct.append(element);
                }
                return distinct;
            }
        });
    }

    @Override
    public IndexedSeq<T> drop(int n) {
        return slice(n, size());
    }

    @Override
    public Seq<T> dropNull() {
        return filter(new PredicateF1<T>() {
            public Boolean apply(T element) {
                return element != null;
            }
        });
    }

    @Override
    public IndexedSeq<T> dropRight(int n) {
        return reverse().drop(n).reverse();
    }

    @Override
    @SuppressWarnings("unchecked")
    public IndexedSeq<T> dropWhile(Function1<T, Boolean> predicate) {
        if (isEmpty()) {
            return IndexedSeq.apply();
        }
        return (IndexedSeq<T>) span(predicate)._2();
    }

    @Override
    public boolean endsWith(Seq<T> that) {
        return reverse().startsWith(that.reverse());
    }

    @Override
    public boolean exists(Function1<T, Boolean> predicate) {
        return span(predicate)._1().size() > 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public IndexedSeq<T> filter(final Function1<T, Boolean> isOk) {
        return foldLeft(IndexedSeq.<T>apply(), new FoldLeftF2<IndexedSeq<T>, T>() {
            public IndexedSeq<T> apply(IndexedSeq<T> filtered, T element) throws Exception {
                if (isOk.apply(element)) {
                    return filtered.append(element);
                }
                return filtered;
            }

            ;
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public IndexedSeq<T> filterNot(final Function1<T, Boolean> isOk) {
        return foldLeft(IndexedSeq.<T>apply(), new FoldLeftF2<IndexedSeq<T>, T>() {
            public IndexedSeq<T> apply(IndexedSeq<T> filtered, T element) throws Exception {
                if (!isOk.apply(element)) {
                    return filtered.append(element);
                }
                return filtered;
            }

            ;
        });
    }

    @Override
    public Option<T> find(final Function1<T, Boolean> predicate) {
        return foldLeft(Option.<T>none(), new FoldLeftF2<Option<T>, T>() {
            public Option<T> apply(Option<T> found, T element) throws Exception {
                if (!found.isDefined() && predicate.apply(element)) {
                    return Option.apply(element);
                }
                return found;
            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public <U> IndexedSeq<U> flatMap(final Function1<T, CollectionLike<U>> f) {
        return foldLeft(IndexedSeq.<U>apply(), new FoldLeftF2<IndexedSeq<U>, T>() {
            public IndexedSeq<U> apply(final IndexedSeq<U> seq, final T element) throws Exception {
                CollectionLike<U> col = f.apply(element);
                return seq.union(IndexedSeq.apply(col.toList()));
            }
        });
    }

    @Override
    public <U> U foldLeft(U z, Function2<U, T, U> operator) {
        try {
            for (T element : list) {
                z = operator.apply(z, element);
            }
            return z;
        } catch (Throwable e) {
            throw new ScalaFlavor4JException(e);
        }
    }

    @Override
    public <U> Function1<Function2<U, T, U>, U> foldLeft(final U z) {
        return new F1<Function2<U, T, U>, U>() {
            public U apply(Function2<U, T, U> operator) {
                try {
                    U result = z;
                    for (T element : list) {
                        result = operator.apply(result, element);
                    }
                    return result;
                } catch (Throwable e) {
                    throw new ScalaFlavor4JException(e);
                }
            }
        };
    }

    @Override
    public <U> U foldRight(U z, Function2<T, U, U> operator) {
        if (isEmpty()) {
            return NIL.foldRight(z, operator);
        }
        try {
            int lastIndex = list.size() - 1;
            for (int i = lastIndex; i >= 0; i--) {
                z = operator.apply(list.get(i), z);
            }
            return z;
        } catch (Throwable e) {
            throw new ScalaFlavor4JException(e);
        }
    }

    @Override
    public <U> Function1<Function2<T, U, U>, U> foldRight(final U z) {
        return new F1<Function2<T, U, U>, U>() {
            public U apply(Function2<T, U, U> operator) {
                try {
                    U result = z;
                    int lastIndex = list.size() - 1;
                    for (int i = lastIndex; i >= 0; i--) {
                        result = operator.apply(list.get(i), result);
                    }
                    return result;
                } catch (Throwable e) {
                    throw new ScalaFlavor4JException(e);
                }
            }
        };
    }

    @Override
    public boolean forall(Function1<T, Boolean> predicate) {
        return span(predicate)._2().size() == 0;
    }

    @Override
    public void foreach(VoidFunction1<T> f) {
        try {
            for (T element : list) {
                f.apply(element);
            }
        } catch (Throwable e) {
            throw new ScalaFlavor4JException(e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <U> SMap<U, Seq<T>> groupBy(final Function1<T, U> getGroupName) {
        if (isEmpty()) {
            return NIL.groupBy(getGroupName);
        }
        return foldLeft(SMap.<U, Seq<T>>apply(), new FoldLeftF2<SMap<U, Seq<T>>, T>() {
            public SMap<U, Seq<T>> apply(SMap<U, Seq<T>> map, T element) throws Exception {
                U groupName = getGroupName.apply(element);
                Seq<T> groupMembers = map.getOrElse(groupName, Seq.<T>apply());
                return map.updated(groupName, groupMembers.append(element));
            }

            ;
        });
    }

    @Override
    public T head() {
        if (isEmpty()) {
            return NIL.head();
        }
        return list.get(0);
    }

    @Override
    public Option<T> headOption() {
        return Option.apply(head());
    }

    @Override
    public int indexOf(final T elem) {
        if (isEmpty()) {
            return NIL.indexOf(elem);
        }
        final int DEFAULT = -1;
        return SInt.apply(0).until(size()).foldLeft(DEFAULT, new FoldLeftF2<Integer, Integer>() {
            public Integer apply(Integer found, Integer i) {
                if (found == DEFAULT) {
                    T element = list.get(i);
                    if (element != null && element.equals(elem)) {
                        return i;
                    }
                }
                return found;
            }
        });
    }

    @Override
    public Seq<Integer> indices() {
        return SInt.apply(0).until(size());
    }

    @Override
    public Seq<T> init() {
        if (isEmpty()) {
            return NIL.init();
        }
        return slice(0, size() - 1);
    }

    @Override
    public IndexedSeq<T> intersect(final Seq<T> that) {
        return flatMap(new FlatMapF1<T, T>() {
            public CollectionLike<T> apply(T thisElement) {
                if (that.contains(thisElement)) {
                    return Option.apply(thisElement);
                } else {
                    return Option.none();
                }
            }
        });
    }

    @Override
    public boolean isDefinedAt(int idx) {
        if (isEmpty()) {
            return NIL.isDefinedAt(idx);
        }
        try {
            return list.get(idx) != null;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public T last() {
        if (isEmpty()) {
            return NIL.last();
        }
        return list.get(list.size() - 1);
    }

    @Override
    public Option<T> lastOption() {
        return Option.apply(last());
    }

    @Override
    @SuppressWarnings("unchecked")
    public <U> IndexedSeq<U> map(final Function1<T, U> f) {
        return foldLeft(IndexedSeq.<U>apply(), new FoldLeftF2<IndexedSeq<U>, T>() {
            public IndexedSeq<U> apply(IndexedSeq<U> mapped, T element) throws Exception {
                return mapped.append(f.apply(element));
            }
        });
    }

    @Override
    public SNum max() {
        if (isEmpty()) {
            return NIL.max();
        }
        try {
            return dropNull().foldLeft(SNum.apply(0), new FoldLeftF2<SNum, T>() {
                public SNum apply(SNum max, T element) {
                    BigDecimal bd = new BigDecimal(element.toString());
                    if (max.toBigDecimal().compareTo(bd) == 1) {
                        return max;
                    } else {
                        return SNum.apply(bd);
                    }
                }
            });
        } catch (NumberFormatException e) {
            throw new UnsupportedOperationException("This operation is not supported.");
        }
    }

    @Override
    public SNum min() {
        if (isEmpty()) {
            return NIL.min();
        }
        try {
            return dropNull().foldLeft(SNum.apply(Integer.MAX_VALUE), new FoldLeftF2<SNum, T>() {
                public SNum apply(SNum min, T v) {
                    BigDecimal bd = new BigDecimal(v.toString());
                    if (min.toBigDecimal().compareTo(bd) == -1) {
                        return min;
                    } else {
                        return SNum.apply(bd);
                    }
                }
            });
        } catch (NumberFormatException e) {
            throw new UnsupportedOperationException("This operation is not supported.");
        }
    }

    @Override
    public String mkString() {
        return mkString("", "", "");
    }

    @Override
    public String mkString(String sep) {
        return mkString("", sep, "");
    }

    @Override
    public String mkString(String start, final String sep, String end) {
        if (isEmpty()) {
            return NIL.mkString(start, sep, end);
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(start);
        int lastIndex = list.size() - 1;
        SInt.apply(0).until(lastIndex).foreach(new VoidF1<Integer>() {
            public void apply(Integer i) {
                sb.append(list.get(i));
                sb.append(sep);
            }
        });
        sb.append(list.get(lastIndex));
        sb.append(end);
        return sb.toString();
    }

    @Override
    public IndexedSeq<T> padTo(int len, final T elem) {
        return this.union(SInt.apply(size()).until(len).map(new F1<Integer, T>() {
            public T apply(Integer i) {
                return elem;
            }
        }));
    }

    @Override
    public ParSeq<T> par() {
        return ParSeq.<T>apply(list);
    }

    /**
     * @see {@link IndexedSeq#partition(Function1)}
     */
    private class PartitionZ {

        Seq<T> matched;
        Seq<T> unmatched;

        @SuppressWarnings("unchecked")
        public PartitionZ() {
            this.matched = IndexedSeq.<T>apply();
            this.unmatched = IndexedSeq.<T>apply();
        }

        public PartitionZ(Seq<T> matched, Seq<T> unmatched) {
            this.matched = matched;
            this.unmatched = unmatched;
        }

        public Tuple2<Seq<T>, Seq<T>> toResult() {
            return Tuple.apply(matched, unmatched);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Tuple2<Seq<T>, Seq<T>> partition(final Function1<T, Boolean> predicate) {
        return foldLeft(new PartitionZ(), new FoldLeftF2<PartitionZ, T>() {
            public PartitionZ apply(PartitionZ z, T element) throws Exception {
                if (predicate.apply(element)) {
                    return new PartitionZ(z.matched.append(element), z.unmatched);
                }
                return new PartitionZ(z.matched, z.unmatched.append(element));
            }

            ;
        }).toResult();
    }

    @Override
    public IndexedSeq<T> patch(int from, Seq<T> patch, int replaced) {
        IndexedSeq<T> prefix = slice(0, from);
        IndexedSeq<T> suffix = drop(from + replaced);
        return prefix.union(patch).union(suffix);
    }

    @Override
    public <U> U reduceLeft(final Function2<U, T, U> operator) {
        return foldLeft(null, new FoldLeftF2<U, T>() {
            public U apply(U z, T element) throws Exception {
                return operator.apply(z, element);
            }
        });
    }

    @Override
    public <U> Option<U> reduceLeftOption(Function2<U, T, U> operator) {
        return Option.apply(reduceLeft(operator));
    }

    @Override
    public <U> U reduceRight(final Function2<T, U, U> operator) {
        return foldRight(null, new FoldRightF2<T, U>() {
            public U apply(T element, U z) throws Exception {
                return operator.apply(element, z);
            }
        });
    }

    @Override
    public <U> Option<U> reduceRightOption(Function2<T, U, U> operator) {
        return Option.apply(reduceRight(operator));
    }

    @Override
    @SuppressWarnings("unchecked")
    public IndexedSeq<T> reverse() {
        return foldRight(IndexedSeq.<T>apply(), new FoldRightF2<T, IndexedSeq<T>>() {
            public IndexedSeq<T> apply(T element, IndexedSeq<T> reversed) {
                return reversed.append(element);
            }

            ;
        });
    }

    @Override
    public <U> IndexedSeq<U> reverseMap(Function1<T, U> f) {
        return reverse().map(f);
    }

    @Override
    public boolean sameElements(final Seq<T> that) {
        if (isEmpty()) {
            return NIL.sameElements(that);
        }
        if (size() != that.size()) {
            return false;
        }
        return zip(that).foldLeft(true, new FoldLeftF2<Boolean, Tuple2<T, T>>() {
            public Boolean apply(Boolean sameElements, Tuple2<T, T> thisAndThat) {
                if (sameElements) {
                    T thisOne = thisAndThat._1();
                    T thatOne = thisAndThat._2();
                    if (thisOne == null && thatOne == null) {
                        return true;
                    }
                    if (!thisOne.equals(thatOne)) {
                        return false;
                    }
                }
                return sameElements;
            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public <U> IndexedSeq<U> scanLeft(U z, final Function2<U, T, U> operator) {
        return foldLeft(IndexedSeq.apply(z), new FoldLeftF2<IndexedSeq<U>, T>() {
            public IndexedSeq<U> apply(IndexedSeq<U> scanResult, T element) throws Exception {
                U result = operator.apply(scanResult.last(), element);
                return scanResult.append(result);
            }
        });
    }

    @Override
    public <U> Function1<Function2<U, T, U>, Seq<U>> scanLeft(final U z) {
        return new F1<Function2<U, T, U>, Seq<U>>() {
            @SuppressWarnings("unchecked")
            public Seq<U> apply(final Function2<U, T, U> operator) throws Exception {
                return foldLeft(IndexedSeq.apply(z), new FoldLeftF2<IndexedSeq<U>, T>() {
                    public IndexedSeq<U> apply(IndexedSeq<U> scanResult, T element) throws Exception {
                        U result = operator.apply(scanResult.last(), element);
                        return scanResult.append(result);
                    }
                });
            }
        };
    }

    @Override
    @SuppressWarnings("unchecked")
    public <U> IndexedSeq<U> scanRight(U z, final Function2<T, U, U> operator) {
        return foldRight(IndexedSeq.apply(z), new FoldRightF2<T, IndexedSeq<U>>() {
            public IndexedSeq<U> apply(T element, IndexedSeq<U> scanResult) throws Exception {
                U result = operator.apply(element, scanResult.last());
                return scanResult.append(result);
            }
        }).reverse();
    }

    @Override
    public <U> Function1<Function2<T, U, U>, Seq<U>> scanRight(final U z) {
        return new F1<Function2<T, U, U>, Seq<U>>() {
            @SuppressWarnings("unchecked")
            public Seq<U> apply(final Function2<T, U, U> operator) throws Exception {
                return foldRight(IndexedSeq.apply(z), new FoldRightF2<T, IndexedSeq<U>>() {
                    public IndexedSeq<U> apply(T element, IndexedSeq<U> scanResult) throws Exception {
                        U result = operator.apply(element, scanResult.last());
                        return scanResult.append(result);
                    }
                }).reverse();
            }
        };
    }

    @Override
    public int size() {
        if (list.size() == 1 && list.get(0) instanceof Seq<?>) {
            Seq<?> head = (Seq<?>) list.get(0);
            return head.isEmpty() ? 0 : 1;
        }
        return list.size();
    }

    @Override
    @SuppressWarnings("unchecked")
    public IndexedSeq<T> slice(int from, int until) {
        if (from >= size()) {
            return IndexedSeq.apply();
        }
        if (until > size()) {
            until = size();
        }
        return SInt.apply(from).until(until).foldLeft(IndexedSeq.<T>apply(), new FoldLeftF2<IndexedSeq<T>, Integer>() {
            public IndexedSeq<T> apply(IndexedSeq<T> sliced, Integer i) {
                return sliced.append(list.get(i));
            }
        });
    }

    @Override
    public IndexedSeq<Seq<T>> sliding(int size) {
        return sliding(size, 1);
    }

    @Override
    @SuppressWarnings("unchecked")
    public IndexedSeq<Seq<T>> sliding(final int blockSize, int step) {
        if (isEmpty()) {
            return IndexedSeq.<Seq<T>>apply();
        }
        final int thisAllSize = size();
        int finalStartIdx = thisAllSize - 1;
        if (blockSize >= thisAllSize) {
            finalStartIdx = 0;
        } else if (step == 1 || thisAllSize % blockSize == step) {
            finalStartIdx = thisAllSize - blockSize;
        }
        Seq<Integer> blockStartIndices = SInt.apply(0).to(finalStartIdx, step);
        return (IndexedSeq<Seq<T>>) blockStartIndices.map(new F1<Integer, Seq<T>>() {
            public Seq<T> apply(Integer startIdx) {
                int lastIdx = startIdx + blockSize - 1;
                Seq<Integer> indicesInBlock = SInt.apply(startIdx).to(lastIdx).filter(new PredicateF1<Integer>() {
                    public Boolean apply(Integer idx) {
                        return idx < thisAllSize;
                    }
                });
                return indicesInBlock.foldLeft(IndexedSeq.<T>apply(), new FoldLeftF2<IndexedSeq<T>, Integer>() {
                    public IndexedSeq<T> apply(IndexedSeq<T> seq, Integer idx) {
                        return seq.append(list.get(idx));
                    }
                });
            }
        });
    }

    @Override
    public IndexedSeq<T> sortWith(final Function2<T, T, Boolean> lessThan) {
        List<T> copied = foldLeft(new ArrayList<T>(), new FoldLeftF2<List<T>, T>() {
            public List<T> apply(List<T> copied, T element) {
                copied.add(element);
                return copied;
            }
        });
        Collections.sort(copied, new Comparator<T>() {
            public int compare(T o1, T o2) {
                try {
                    return lessThan.apply(o1, o2) ? -1 : 1;
                } catch (Throwable e) {
                    throw new ScalaFlavor4JException(e);
                }
            }

            ;
        });
        return IndexedSeq.apply(copied);
    }

    /**
     * @see {@link IndexedSeq#span(Function1)}
     */
    private class SpanZ {

        Boolean unmatchFound;
        Seq<T> stillMatched;
        Seq<T> others;

        @SuppressWarnings("unchecked")
        public SpanZ() {
            this.unmatchFound = false;
            this.stillMatched = IndexedSeq.<T>apply();
            this.others = IndexedSeq.<T>apply();
        }

        public SpanZ(Boolean unmatchFound, Seq<T> stillMatched, Seq<T> others) {
            this.unmatchFound = unmatchFound;
            this.stillMatched = stillMatched;
            this.others = others;
        }

        public Pair<Seq<T>, Seq<T>> toResult() {
            return Pair.apply(stillMatched, others);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Tuple2<Seq<T>, Seq<T>> span(final Function1<T, Boolean> predicate) {
        return foldLeft(new SpanZ(), new FoldLeftF2<SpanZ, T>() {
            public SpanZ apply(SpanZ z, T element) throws Exception {
                if (z.unmatchFound) {
                    return new SpanZ(z.unmatchFound, z.stillMatched, z.others.append(element));
                } else if (!predicate.apply(element)) {
                    return new SpanZ(true, z.stillMatched, z.others.append(element));
                }
                return new SpanZ(z.unmatchFound, z.stillMatched.append(element), z.others);
            }

            ;
        }).toResult();
    }

    @Override
    public Tuple2<Seq<T>, Seq<T>> splitAt(int n) {
        return Tuple.<Seq<T>, Seq<T>>apply(take(n), drop(n));
    }

    @Override
    public boolean startsWith(Seq<T> that) {
        return startsWith(that, 0);
    }

    @Override
    public boolean startsWith(final Seq<T> that, final int offset) {
        if (isEmpty()) {
            return NIL.startsWith(that, offset);
        }
        int max = offset + that.size();
        if (max > size()) {
            return false;
        }
        return SInt.apply(offset).until(max).foldLeft(true, new FoldLeftF2<Boolean, Integer>() {
            public Boolean apply(Boolean stillSame, Integer i) {
                if (stillSame) {
                    T thisOne = list.get(i);
                    T thatOne = that.toList().get(i - offset);
                    if (thisOne == null) {
                        return thatOne == null;
                    }
                    return thisOne.equals(thatOne);
                }
                return false;
            }
        });
    }

    @Override
    public SNum sum() {
        if (isEmpty()) {
            return NIL.sum();
        }
        try {
            return dropNull().foldLeft(SNum.apply(0), new FoldLeftF2<SNum, T>() {
                public SNum apply(SNum sum, T element) {
                    BigDecimal added = sum.toBigDecimal().add(new BigDecimal(element.toString()));
                    return SNum.apply(added);
                }
            });
        } catch (NumberFormatException e) {
            throw new UnsupportedOperationException("This operation is not supported.");
        }
    }

    @Override
    public IndexedSeq<T> tail() {
        return slice(1, size());
    }

    @Override
    public IndexedSeq<T> take(int n) {
        return slice(0, n);
    }

    @Override
    public IndexedSeq<T> takeRight(int n) {
        return reverse().take(n).reverse();
    }

    @Override
    @SuppressWarnings("unchecked")
    public IndexedSeq<T> takeWhile(Function1<T, Boolean> predicate) {
        if (isEmpty()) {
            return IndexedSeq.apply();
        }
        return (IndexedSeq<T>) span(predicate)._1();
    }

    @Override
    public List<T> toList() {
        return list;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Seq(");
        sb.append(mkString(","));
        sb.append(")");
        return sb.toString();
    }

    @Override
    public IndexedSeq<T> union(Seq<T> that) {
        List<T> list = toList();
        list.addAll(that.toList());
        return IndexedSeq.apply(list);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Seq<T> updated(int index, T elem) {
        if (isEmpty()) {
            return NIL.updated(index, elem);
        }
        Seq<T> prefix = slice(0, index);
        Seq<T> suffix = drop(index + 1);
        return prefix.append(elem).union(suffix);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <U> IndexedSeq<Tuple2<T, U>> zip(final Seq<U> that) {
        int len = this.size();
        if (this.size() > that.size()) {
            len = that.size();
        }
        Seq<Integer> indices = SInt.apply(0).until(len);
        if (indices.isEmpty()) {
            return IndexedSeq.apply();
        } else {
            return (IndexedSeq<Tuple2<T, U>>) indices.map(new F1<Integer, Tuple2<T, U>>() {
                public Tuple2<T, U> apply(Integer i) {
                    return Tuple.apply(toList().get(i), that.toList().get(i));
                }
            });
        }
    }

    @Override
    public IndexedSeq<Tuple2<T, Integer>> zipWithIndex() {
        return zip(indices());
    }

    @Override
    public Seq<T> transpose() {
        Seq<Seq<?>> xss = (Seq<Seq<?>>) this;
        int headSize = xss.head().size();
        List<List<?>> matrix = new ArrayList<List<?>>();
        for (int i = 0; i < headSize; i++) {
            List<Object> row = new ArrayList<Object>();
            for (Seq<?> seq : xss.toList()) {
                row.add(seq.toList().get(i));
            }
            matrix.add(row);
        }
        return (Seq<T>) Seq.apply(matrix).map(new F1<List<?>, Seq<?>>() {
            public Seq<?> apply(List<?> row) throws Exception {
                return Seq.apply(row);
            }
        });
    }

}
