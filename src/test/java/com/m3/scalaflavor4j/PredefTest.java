package com.m3.scalaflavor4j;

import static com.m3.scalaflavor4j.Predef.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.MissingFormatArgumentException;

import org.junit.Ignore;
import org.junit.Test;

public class PredefTest {

    @Test
    public void type() throws Exception {
        assertThat(Predef.class, notNullValue());
    }

    @Test
    public void print_A$Object_null() throws Exception {
        print(null);
    }

    @Test
    public void print_A$Object() throws Exception {
        print("xxx");
    }

    @Test
    public void println_A$Object_null() throws Exception {
        println(null);
    }

    @Test
    public void println_A$Object() throws Exception {
        println("xxx");
    }

    @Test
    public void println_A$() throws Exception {
        println();
    }

    @Test
    public void printf_A$String$ObjectArray() throws Exception {
        printf("%s programmer", "Scala");
    }

    @Test(expected = MissingFormatArgumentException.class)
    public void printf_A$String$ObjectArray_Invalid() throws Exception {
        printf("%s%s programmer", "Scala");
    }

    @Ignore
    @Test
    public void readLine_A$() throws Exception {
        println("Please input something.");
        println(readLine());
    }

    @Ignore
    @Test
    public void readBoolean_A$() throws Exception {
        println("Please input a boolean value.");
        println(readBoolean());
    }

    @Ignore
    @Test
    public void readByte_A$() throws Exception {
        println("Please input a byte value.");
        println(readByte());
    }

    @Ignore
    @Test
    public void readChar_A$() throws Exception {
        println("Please input a char value.");
        println(readChar());
    }

    @Ignore
    @Test
    public void readDouble_A$() throws Exception {
        println("Please input a double value.");
        println(readDouble());
    }

    @Ignore
    @Test
    public void readFloat_A$() throws Exception {
        println("Please input a float value.");
        println(readFloat());
    }

    @Ignore
    @Test
    public void readInt_A$() throws Exception {
        println("Please input an int value.");
        println(readInt());
    }

    @Ignore
    @Test
    public void readLong_A$() throws Exception {
        println("Please input a long value.");
        println(readLong());
    }

    @Ignore
    @Test
    public void readShort_A$() throws Exception {
        println("Please input a short value.");
        println(readShort());
    }

}
