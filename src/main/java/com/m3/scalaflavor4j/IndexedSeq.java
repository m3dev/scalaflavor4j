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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * {@link Seq} implementation
 */
public class IndexedSeq<T> extends Seq<T> {

    private static final long serialVersionUID = 1L;

    /**
     * use Nil when this is empty (because the elements in the list are mutable)
     */
    private final Nil<T> NIL = Nil._();

    private final List<T> list;

    public static <T> IndexedSeq<T> apply(T... values) {
        return _(values);
    }

    public static <T> IndexedSeq<T> _(T... values) {
        List<T> list = new ArrayList<T>();
        for (T value : values) {
            list.add(value);
        }
        return _(list);
    }

    public static <T> IndexedSeq<T> apply(Collection<T> list) {
        return _(list);
    }

    public static <T> IndexedSeq<T> _(Collection<T> list) {
        return new IndexedSeq<T>(list);
    }

    private IndexedSeq(Collection<T> list) {
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
        return IndexedSeq._(newList);
    }

    @Override
    public boolean contains(final T elem) {
        return dropNull().foldLeft(false, new FoldLeftF2<Boolean, T>() {
            public Boolean _(Boolean contains, T exist) {
                if (!contains) {
                    return exist.equals(elem);
                }
                return contains;
            }
        });
    }

    @Override
    public int count(final Function1<T, Boolean> predicate) {
        return foldLeft(0, new FoldLeftF2<Integer, T>() {
            public Integer _(Integer count, T element) throws Exception {
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
            public Boolean _(final T thisValue) {
                return that.foldLeft(false, new FoldLeftF2<Boolean, T>() {
                    public Boolean _(Boolean isFound, T thatValue) {
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
        return foldLeft(IndexedSeq.<T> _(), new FoldLeftF2<IndexedSeq<T>, T>() {
            public IndexedSeq<T> _(IndexedSeq<T> distinct, T element) {
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
            public Boolean _(T element) {
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
            return IndexedSeq._();
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
        return foldLeft(IndexedSeq.<T> _(), new FoldLeftF2<IndexedSeq<T>, T>() {
            public IndexedSeq<T> _(IndexedSeq<T> filtered, T element) throws Exception {
                if (isOk.apply(element)) {
                    return filtered.append(element);
                }
                return filtered;
            };
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public IndexedSeq<T> filterNot(final Function1<T, Boolean> isOk) {
        return foldLeft(IndexedSeq.<T> _(), new FoldLeftF2<IndexedSeq<T>, T>() {
            public IndexedSeq<T> _(IndexedSeq<T> filtered, T element) throws Exception {
                if (!isOk.apply(element)) {
                    return filtered.append(element);
                }
                return filtered;
            };
        });
    }

    @Override
    public Option<T> find(final Function1<T, Boolean> predicate) {
        return foldLeft(Option.<T> none(), new FoldLeftF2<Option<T>, T>() {
            public Option<T> _(Option<T> found, T element) throws Exception {
                if (!found.isDefined() && predicate.apply(element)) {
                    return Option._(element);
                }
                return found;
            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public <U> IndexedSeq<U> flatMap(final Function1<T, CollectionLike<U>> f) {
        return foldLeft(IndexedSeq.<U> _(), new FoldLeftF2<IndexedSeq<U>, T>() {
            public IndexedSeq<U> _(final IndexedSeq<U> seq, final T element) throws Exception {
                CollectionLike<U> col = f.apply(element);
                return seq.union(IndexedSeq._(col.toList()));
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
        } catch (Exception e) {
            throw new ScalaFlavor4JException(e);
        }
    }

    @Override
    public <U> U foldRight(U z, Function2<T, U, U> operator) {
        try {
            if (isEmpty()) {
                return NIL.foldRight(z, operator);
            }
            int lastIndex = list.size() - 1;
            for (int i = lastIndex; i >= 0; i--) {
                z = operator.apply(list.get(i), z);
            }
            return z;
        } catch (Exception e) {
            throw new ScalaFlavor4JException(e);
        }
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
        } catch (Exception e) {
            throw new ScalaFlavor4JException(e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <U> SMap<U, Seq<T>> groupBy(final Function1<T, U> getGroupName) {
        if (isEmpty()) {
            return NIL.groupBy(getGroupName);
        }
        return foldLeft(SMap.<U, Seq<T>> _(), new FoldLeftF2<SMap<U, Seq<T>>, T>() {
            public SMap<U, Seq<T>> _(SMap<U, Seq<T>> map, T element) throws Exception {
                U groupName = getGroupName.apply(element);
                Seq<T> groupMembers = map.getOrElse(groupName, Seq.<T> _());
                return map.update(groupName, groupMembers.append(element));
            };
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
        return Option._(head());
    }

    @Override
    public int indexOf(final T elem) {
        if (isEmpty()) {
            return NIL.indexOf(elem);
        }
        final int DEFAULT = -1;
        return SInt._(0).until(size()).foldLeft(DEFAULT, new FoldLeftF2<Integer, Integer>() {
            public Integer _(Integer found, Integer i) {
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
        return SInt._(0).until(size());
    }

    @Override
    public IndexedSeq<T> intersect(final Seq<T> that) {
        return flatMap(new F1<T, CollectionLike<T>>() {
            public CollectionLike<T> _(T thisElement) {
                if (that.contains(thisElement)) {
                    return Option._(thisElement);
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
        return Option._(last());
    }

    @Override
    @SuppressWarnings("unchecked")
    public <U> IndexedSeq<U> map(final Function1<T, U> f) {
        return foldLeft(IndexedSeq.<U> _(), new FoldLeftF2<IndexedSeq<U>, T>() {
            public IndexedSeq<U> _(IndexedSeq<U> mapped, T element) throws Exception {
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
            return dropNull().foldLeft(SNum._(0), new FoldLeftF2<SNum, T>() {
                public SNum _(SNum max, T element) {
                    BigDecimal bd = new BigDecimal(element.toString());
                    if (max.toBigDecimal().compareTo(bd) == 1) {
                        return max;
                    } else {
                        return SNum._(bd);
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
            return dropNull().foldLeft(SNum._(Integer.MAX_VALUE), new FoldLeftF2<SNum, T>() {
                public SNum _(SNum min, T v) {
                    BigDecimal bd = new BigDecimal(v.toString());
                    if (min.toBigDecimal().compareTo(bd) == -1) {
                        return min;
                    } else {
                        return SNum._(bd);
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
        SInt._(0).until(lastIndex).foreach(new VoidF1<Integer>() {
            public void _(Integer i) {
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
        return this.union(SInt._(size()).until(len).map(new F1<Integer, T>() {
            public T _(Integer i) {
                return elem;
            }
        }));
    }

    @Override
    @SuppressWarnings("unchecked")
    public Tuple2<Seq<T>, Seq<T>> partition(final Function1<T, Boolean> predicate) {
        Pair<Seq<T>, Seq<T>> partition = Pair.<Seq<T>, Seq<T>> _(IndexedSeq.<T> _(), IndexedSeq.<T> _());
        return foldLeft(partition, new FoldLeftF2<Pair<Seq<T>, Seq<T>>, T>() {
            public Pair<Seq<T>, Seq<T>> _(Pair<Seq<T>, Seq<T>> partition, T element) throws Exception {
                Seq<T> matched = partition._1();
                Seq<T> unmatched = partition._2();
                if (predicate.apply(element)) {
                    return Pair._(matched.append(element), unmatched);
                }
                return Pair._(matched, unmatched.append(element));
            };
        });
    }

    @Override
    public IndexedSeq<T> patch(int from, Seq<T> patch, int replaced) {
        IndexedSeq<T> prefix = slice(0, from);
        IndexedSeq<T> suffix = drop(from + replaced);
        return prefix.union(patch).union(suffix);
    }

    @Override
    @SuppressWarnings("unchecked")
    public IndexedSeq<T> reverse() {
        return foldRight(IndexedSeq.<T> _(), new FoldRightF2<T, IndexedSeq<T>>() {
            public IndexedSeq<T> _(T element, IndexedSeq<T> reversed) {
                return reversed.append(element);
            };
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
            public Boolean _(Boolean sameElements, Tuple2<T, T> thisAndThat) {
                if (sameElements) {
                    if (thisAndThat._1() == null && thisAndThat._2() == null) {
                        return true;
                    }
                    if (!thisAndThat._1().equals(thisAndThat._2())) {
                        return false;
                    }
                }
                return sameElements;
            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public <U> IndexedSeq<U> scanLeft(U z, final Function2<U, T, U> op) {
        return foldLeft(IndexedSeq._(z), new FoldLeftF2<IndexedSeq<U>, T>() {
            public IndexedSeq<U> _(IndexedSeq<U> scanResult, T element) throws Exception {
                U result = op.apply(scanResult.last(), element);
                return scanResult.append(result);
            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public <U> IndexedSeq<U> scanRight(U z, final Function2<T, U, U> op) {
        return foldRight(IndexedSeq._(z), new FoldRightF2<T, IndexedSeq<U>>() {
            public IndexedSeq<U> _(T element, IndexedSeq<U> scanResult) throws Exception {
                U result = op.apply(element, scanResult.last());
                return scanResult.append(result);
            }
        }).reverse();
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
        if (from >= size() || until > size()) {
            return IndexedSeq._();
        }
        return SInt._(from).until(until).foldLeft(IndexedSeq.<T> _(), new FoldLeftF2<IndexedSeq<T>, Integer>() {
            public IndexedSeq<T> _(IndexedSeq<T> sliced, Integer i) {
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
            return IndexedSeq.<Seq<T>> _();
        }
        final int thisAllSize = size();
        int finalStartIdx = thisAllSize - 1;
        if (blockSize >= thisAllSize) {
            finalStartIdx = 0;
        } else if (step == 1 || thisAllSize % blockSize == step) {
            finalStartIdx = thisAllSize - blockSize;
        }
        Seq<Integer> blockStartIndices = SInt._(0).to(finalStartIdx, step);
        return (IndexedSeq<Seq<T>>) blockStartIndices.map(new F1<Integer, Seq<T>>() {
            public Seq<T> _(Integer startIdx) {
                int lastIdx = startIdx + blockSize - 1;
                Seq<Integer> indicesInBlock = SInt._(startIdx).to(lastIdx).filter(new PredicateF1<Integer>() {
                    public Boolean _(Integer idx) {
                        return idx < thisAllSize;
                    }
                });
                return indicesInBlock.foldLeft(IndexedSeq.<T> _(), new FoldLeftF2<IndexedSeq<T>, Integer>() {
                    public IndexedSeq<T> _(IndexedSeq<T> seq, Integer idx) {
                        return seq.append(list.get(idx));
                    }
                });
            }
        });
    }

    @Override
    public IndexedSeq<T> sortWith(final Function2<T, T, Boolean> lessThan) {
        List<T> copied = foldLeft(new ArrayList<T>(), new FoldLeftF2<List<T>, T>() {
            public List<T> _(List<T> copied, T element) {
                copied.add(element);
                return copied;
            }
        });
        Collections.sort(copied, new Comparator<T>() {
            public int compare(T o1, T o2) {
                try {
                    return lessThan.apply(o1, o2) ? 0 : 1;
                } catch (Exception e) {
                    throw new ScalaFlavor4JException(e);
                }
            };
        });
        return IndexedSeq._(copied);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Tuple2<Seq<T>, Seq<T>> span(final Function1<T, Boolean> predicate) {
        Triple<Boolean, Seq<T>, Seq<T>> spanned = foldLeft(Triple._(false, Seq.<T> _(), Seq.<T> _()),
                new FoldLeftF2<Triple<Boolean, Seq<T>, Seq<T>>, T>() {
                    public Triple<Boolean, Seq<T>, Seq<T>> _(Triple<Boolean, Seq<T>, Seq<T>> spanned, T element)
                            throws Exception {
                        boolean unmatchFound = spanned._1();
                        Seq<T> stillMatched = spanned._2();
                        Seq<T> others = spanned._3();
                        if (unmatchFound) {
                            return Triple._(unmatchFound, stillMatched, others.append(element));
                        } else if (!predicate.apply(element)) {
                            return Triple._(true, stillMatched, others.append(element));
                        }
                        return Triple._(unmatchFound, stillMatched.append(element), others);
                    };
                });
        return Pair._(spanned._2(), spanned._3());

    }

    @Override
    public Tuple2<Seq<T>, Seq<T>> splitAt(int n) {
        return Pair.<Seq<T>, Seq<T>> _(take(n), drop(n));
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
        return SInt._(offset).until(max).foldLeft(true, new FoldLeftF2<Boolean, Integer>() {
            public Boolean _(Boolean stillSame, Integer i) {
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
            return dropNull().foldLeft(SNum._(0), new FoldLeftF2<SNum, T>() {
                public SNum _(SNum sum, T element) {
                    BigDecimal added = sum.toBigDecimal().add(new BigDecimal(element.toString()));
                    return SNum._(added);
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
            return IndexedSeq._();
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
        return IndexedSeq._(list);
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
        Seq<Integer> indices = SInt._(0).until(len);
        if (indices.isEmpty()) {
            return IndexedSeq._();
        } else {
            return (IndexedSeq<Tuple2<T, U>>) indices.map(new F1<Integer, Tuple2<T, U>>() {
                public Pair<T, U> _(Integer i) {
                    return Pair._(toList().get(i), that.toList().get(i));
                }
            });
        }
    }

    @Override
    public IndexedSeq<Tuple2<T, Integer>> zipWithIndex() {
        return zip(indices());
    }

}
