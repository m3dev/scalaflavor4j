package com.m3.scalaflavor4j;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ProcessTest {

    @Test
    public void type() throws Exception {
        assertThat(Process.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        Process target = Process.apply("ls");
        assertThat(target, notNullValue());
    }

    @Test
    public void run_A$() throws Exception {
        int actual = Process.apply("ls").run();
        int expected = 0;
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void runAndGetStdout_A$() throws Exception {
        Seq<String> stdout = Process.apply("ls").runAndGetStdout();
        assertThat(stdout.size(), is(greaterThan(0)));
    }

    @Test
    public void apply_A$String() throws Exception {
        Process process = Process.apply("ls");
        assertThat(process, is(notNullValue()));
    }

    @Test
    public void __A$String() throws Exception {
        Process process = com.m3.scalaflavor4j.Process.apply("ls");
        assertThat(process, is(notNullValue()));
    }

}
