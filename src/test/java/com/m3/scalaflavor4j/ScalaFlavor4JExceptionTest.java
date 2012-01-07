package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ScalaFlavor4JExceptionTest {

    @Test
    public void type() throws Exception {
        assertThat(ScalaFlavor4JException.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        Throwable e = new RuntimeException();
        ScalaFlavor4JException target = new ScalaFlavor4JException(e);
        assertThat(target, notNullValue());
    }

}
