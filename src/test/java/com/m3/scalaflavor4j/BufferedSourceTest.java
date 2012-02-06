package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class BufferedSourceTest {

    public String INPUT_TXT_CONTENT = "Scala is a general purpose programming language designed to express common programming patterns in a concise, elegant, and type-safe way. \n"
            + "It smoothly integrates features of object-oriented and functional languages, enabling Java and other programmers to be more productive. \n"
            + "Code sizes are typically reduced by a factor of two to three when compared to an equivalent Java application. ";

    @Test
    public void type() throws Exception {
        assertThat(BufferedSource.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        BufferedSource target = new BufferedSource(new File("src/test/resources/input.txt"), null);
        assertThat(target, notNullValue());
    }

    @Test
    public void toByteSeq_A$() throws Exception {
        File file = new File("src/test/resources/input.txt");
        String enc = null;
        BufferedSource target = new BufferedSource(file, enc);
        Seq<Byte> actual = target.toByteSeq();
        assertThat(actual.size(), is(equalTo(387)));
    }

    @Test
    public void toCharSeq_A$() throws Exception {
        File file = new File("src/test/resources/input.txt");
        String enc = null;
        BufferedSource target = new BufferedSource(file, enc);
        Seq<Character> actual = target.toCharSeq();
        assertThat(actual.size(), is(equalTo(387)));
    }

    @Test
    public void getLines_A$() throws Exception {
        File file = new File("src/test/resources/input.txt");
        String enc = null;
        BufferedSource target = new BufferedSource(file, enc);
        Seq<String> actual = target.getLines();
        assertThat(actual.size(), is(equalTo(3)));
    }

}
