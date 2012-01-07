package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class MatchErrorTest {

    @Test
    public void type() throws Exception {
        assertThat(MatchError.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        String message = "bar";
        MatchError target = new MatchError(message);
        assertThat(target, notNullValue());
    }

    @Test
    public void getLocalizedMessage_A$() throws Exception {
        String message = "foo";
        MatchError target = new MatchError(message);
        String actual = target.getLocalizedMessage();
        String expected = "foo";
        assertThat(actual, is(equalTo(expected)));
    }

}
