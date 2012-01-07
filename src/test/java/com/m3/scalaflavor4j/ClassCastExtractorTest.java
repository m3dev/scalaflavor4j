package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ClassCastExtractorTest {

    @Test
    public void type() throws Exception {
        assertThat(ClassCastExtractor.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        Class<String> clazz = String.class;
        ClassCastExtractor<String> target = new ClassCastExtractor<String>(clazz);
        assertThat(target, notNullValue());
    }

    @Test
    public void extract_A$Object() throws Exception {
        Class<String> clazz = String.class;
        ClassCastExtractor<String> target = new ClassCastExtractor<String>(clazz);
        String actual = target.extract("foo");
        assertThat(actual, is(notNullValue()));
    }

    interface Person {
    }

    class GrandPa implements Person {
    }

    class Pa extends GrandPa {
    }

    class Child extends Pa {
    }

    @Test
    public void extract_A$Object_inheritance() throws Exception {
        ClassCastExtractor<Person> personExt = new ClassCastExtractor<Person>(Person.class);
        assertThat(personExt.extract(new GrandPa()), is(notNullValue()));
        assertThat(personExt.extract(new Pa()), is(notNullValue()));
        assertThat(personExt.extract(new Child()), is(notNullValue()));
        ClassCastExtractor<GrandPa> grandPaExt = new ClassCastExtractor<GrandPa>(GrandPa.class);
        assertThat(grandPaExt.extract(new GrandPa()), is(notNullValue()));
        assertThat(grandPaExt.extract(new Pa()), is(notNullValue()));
        assertThat(grandPaExt.extract(new Child()), is(notNullValue()));
        ClassCastExtractor<Pa> paExt = new ClassCastExtractor<Pa>(Pa.class);
        assertThat(paExt.extract(new GrandPa()), is(nullValue()));
        assertThat(paExt.extract(new Pa()), is(notNullValue()));
        assertThat(paExt.extract(new Child()), is(notNullValue()));
        ClassCastExtractor<Child> childExt = new ClassCastExtractor<Child>(Child.class);
        assertThat(childExt.extract(new GrandPa()), is(nullValue()));
        assertThat(childExt.extract(new Pa()), is(nullValue()));
        assertThat(childExt.extract(new Child()), is(notNullValue()));
    }

}
