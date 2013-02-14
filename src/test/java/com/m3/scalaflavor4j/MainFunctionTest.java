package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class MainFunctionTest {

    final VoidF1<Object> print = new VoidF1<Object>() {
        public void apply(Object v1) {
            System.out.println(v1);
        }
    };

    @Test
    public void type() throws Exception {
        assertThat(MainFunction.class, notNullValue());
    }

    @Test
    public void instantiation() throws Exception {
        MainFunction main = new MainFunction() {
            public void apply(String[] args) {
            }
        };
        assertThat(main, notNullValue());
    }

    @Test
    public void apply() throws Exception {
        MainFunction main = new MainFunction() {
            public void apply(String[] args) throws Exception {
                print.apply(args.length);
                Seq.apply(args).foreach(new VoidF1<String>() {
                    public void apply(String arg) throws Exception {
                        print.apply(arg);
                    }
                });
            }
        };
        main.apply(new String[] { "a", "b" });
    }
}
