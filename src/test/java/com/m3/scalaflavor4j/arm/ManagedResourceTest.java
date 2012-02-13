package com.m3.scalaflavor4j.arm;

import static com.m3.scalaflavor4j.arm.Resource.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import com.m3.scalaflavor4j.F1;
import com.m3.scalaflavor4j.VoidF1;

public class ManagedResourceTest {

    @Test
    public void type() throws Exception {
        assertThat(ManagedResource.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        ByteArrayInputStream closable = new ByteArrayInputStream(new byte[] { 83, 99, 97, 108, 97 });
        ManagedResource<ByteArrayInputStream> managedResource = Resource.managed(closable);
        assertThat(managedResource, notNullValue());
    }

    @Test
    public void map_A$Function1() throws Exception {
        ByteArrayInputStream closable = new ByteArrayInputStream(new byte[] { 83, 99, 97, 108, 97 });
        String result = managed(closable).map(new F1<ByteArrayInputStream, String>() {
            public String _(ByteArrayInputStream is) throws Exception {
                byte[] bytes = new byte[5];
                is.read(bytes);
                return new String(bytes);
            }
        });
        assertThat(result, is(equalTo("Scala")));
    }

    class DummyStream {
        boolean isClosed = false;

        public void close() {
            isClosed = true;
        }
    }

    @Test
    public void map_A$Function1_callingClose() throws Exception {
        DummyStream ds = new DummyStream();
        String result = managed(ds).map(new F1<DummyStream, String>() {
            public String _(DummyStream ds) throws Exception {
                return "ok";
            }
        });
        assertThat(ds.isClosed, is(true));
        assertThat(result, is(equalTo("ok")));
    }

    @Test
    public void foreach_A$VoidFunction1() throws Exception {
        ByteArrayInputStream closable = new ByteArrayInputStream(new byte[] { 83, 99, 97, 108, 97 });
        managed(closable).foreach(new VoidF1<ByteArrayInputStream>() {
            public void _(ByteArrayInputStream is) throws Exception {
                byte[] bytes = new byte[5];
                is.read(bytes);
                for (byte b : bytes) {
                    System.out.println(b);
                }
            }
        });
    }

    @Test
    public void foreach_A$VoidFunction1_callingClose() throws Exception {
        DummyStream ds = new DummyStream();
        managed(ds).foreach(new VoidF1<DummyStream>() {
            public void _(DummyStream ds) throws Exception {
            }
        });
        assertThat(ds.isClosed, is(true));
    }

    @Test
    public void toList_A$() throws Exception {
        Resource resource = new Resource(new ByteArrayInputStream(new byte[] { 83, 99, 97, 108, 97 }));
        ManagedResource<InputStream> mr = new ManagedResource<InputStream>(resource);
        List<InputStream> list = mr.toList();
        assertThat(list.get(0), is(notNullValue()));
    }

    @Test
    public void isEmpty_A$_false() throws Exception {
        Resource resource = new Resource(new ByteArrayInputStream(new byte[] { 83, 99, 97, 108, 97 }));
        ManagedResource<InputStream> mr = new ManagedResource<InputStream>(resource);
        boolean actual = mr.isEmpty();
        assertThat(actual, is(false));
    }

    @Test
    public void isEmpty_A$_true() throws Exception {
        Resource resource = new Resource(null);
        ManagedResource<InputStream> mr = new ManagedResource<InputStream>(resource);
        boolean actual = mr.isEmpty();
        assertThat(actual, is(true));
    }

}
