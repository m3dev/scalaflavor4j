package com.m3.scalaflavor4j.arm;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.Test;

public class ResourceTest {

    @Test
    public void type() throws Exception {
        assertThat(Resource.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        ByteArrayInputStream closable = new ByteArrayInputStream(new byte[] { 83, 99, 97, 108, 97 });
        Resource target = new Resource(closable);
        assertThat(target, notNullValue());
    }

    @Test
    public void getResource_A$() throws Exception {
        ByteArrayInputStream closable = new ByteArrayInputStream(new byte[] { 83, 99, 97, 108, 97 });
        Resource target = new Resource(closable);
        ByteArrayInputStream actual = (ByteArrayInputStream) target.getResource();
        assertThat(actual, notNullValue());
    }

    @Test
    public void close_A$() throws Exception {
        ByteArrayInputStream closable = new ByteArrayInputStream(new byte[] { 83, 99, 97, 108, 97 });
        Resource target = new Resource(closable);
        target.close();
    }

    @Test
    public void ensureClosable_A$Object() throws Exception {
        ByteArrayInputStream closable = new ByteArrayInputStream(new byte[] { 83, 99, 97, 108, 97 });
        Resource.ensureClosable(closable);
    }

    @Test
    public void managed_A$Object() throws Exception {
        ByteArrayInputStream closable = new ByteArrayInputStream(new byte[] { 83, 99, 97, 108, 97 });
        ManagedResource<ByteArrayInputStream> actual = Resource.managed(closable);
        assertThat(actual, notNullValue());
    }

}
