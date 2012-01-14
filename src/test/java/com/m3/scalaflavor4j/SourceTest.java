package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import org.junit.Test;

public class SourceTest {

    public String INPUT_TXT_CONTENT = "Scala is a general purpose programming language designed to express common programming patterns in a concise, elegant, and type-safe way. \n"
            + "It smoothly integrates features of object-oriented and functional languages, enabling Java and other programmers to be more productive. \n"
            + "Code sizes are typically reduced by a factor of two to three when compared to an equivalent Java application. ";

    @Test
    public void type() throws Exception {
        assertThat(Source.class, notNullValue());
    }

    @Test
    public void fromFile_A$String$String() throws Exception {
        String name = "src/test/resources/input.txt";
        String enc = null;
        BufferedSource bs = Source.fromFile(name, enc);
        assertThat(bs.getLines().mkString("\n"), is(equalTo(INPUT_TXT_CONTENT)));
    }

    @Test
    public void fromFile_A$File$String() throws Exception {
        File file = new File("src/test/resources/input.txt");
        String enc = "UTF-8";
        BufferedSource bs = Source.fromFile(file, enc);
        assertThat(bs.getLines().mkString("\n"), is(equalTo(INPUT_TXT_CONTENT)));
    }

    @Test
    public void fromInputStream_A$InputStream() throws Exception {
        File file = new File("src/test/resources/input.txt");
        InputStream is = new FileInputStream(file);
        BufferedSource bs = Source.fromInputStream(is);
        assertThat(bs.getLines().mkString("\n"), is(equalTo(INPUT_TXT_CONTENT)));
    }

    @Test
    public void fromInputStream_A$InputStream$String() throws Exception {
        File file = new File("src/test/resources/input.txt");
        InputStream is = new FileInputStream(file);
        String enc = "UTF-8";
        BufferedSource bs = Source.fromInputStream(is, enc);
        assertThat(bs.getLines().mkString("\n"), is(equalTo(INPUT_TXT_CONTENT)));
    }

    @Test
    public void fromURL_A$URL$String() throws Exception {
        URL url = new URL("http://seratch.github.com/");
        String enc = null;
        BufferedSource bs = Source.fromURL(url, enc);
        assertThat(bs.getLines().mkString("\n"), notNullValue());
    }

}
