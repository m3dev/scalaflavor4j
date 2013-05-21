package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class PartialFunctionTest {

    @Test
    public void type() throws Exception {
        assertThat(PartialFunction.class, notNullValue());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void instantiation() throws Exception {
        PartialFunction<String> target = new PartialFunction<String>(CaseClause._case(String.class)._arrow(
                new Function1<String, String>() {
                    public String apply(String v1) throws Exception {
                        return v1;
                    }
                }));
        assertThat(target, notNullValue());
    }

    @Test
    public void __A$Object() throws Exception {
        assertTrue(true);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void __A$CaseClauseArray() throws Exception {
        PartialFunction<String> target = new PartialFunction<String>(CaseClause._case(String.class)._arrow(
                new Function1<String, String>() {
                    public String apply(String v1) throws Exception {
                        return v1;
                    }
                }));
        assertThat(target, notNullValue());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void __A$Seq() throws Exception {
        PartialFunction<String> target = PartialFunction.<String> apply(Seq.<CaseClause<?, String>> apply(CaseClause._case(
                String.class)._arrow(new Function1<String, String>() {
            public String apply(String v1) throws Exception {
                return v1;
            }
        })));
        assertThat(target, notNullValue());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void isDefinedAt_A$Object() throws Exception {
        PartialFunction<String> target = PartialFunction.<String> apply(Seq.<CaseClause<?, String>> apply(CaseClause._case(
                String.class)._arrow(new Function1<String, String>() {
            public String apply(String v1) throws Exception {
                return v1;
            }
        })));
        assertThat(target.isDefinedAt("foo"), is(true));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void orElse_A$PartialFunction() throws Exception {

        PartialFunction<String> str = PartialFunction.<String> apply(Seq.<CaseClause<?, String>> apply(CaseClause._case(
                String.class)._arrow(new Function1<String, String>() {
            public String apply(String v1) throws Exception {
                return v1;
            }
        })));
        try {
            str.apply(123);
            fail();
        } catch (MatchError e) {
        }

        PartialF<String> integer = new PartialF<String>(CaseClause._case(Integer.class)._arrow(
                new F1<Integer, String>() {
                    public String apply(Integer v1) throws Exception {
                        return v1.toString();
                    }
                }));
        PartialFunction<String> f = str.orElse(integer);
        assertThat(f.apply(123), is(equalTo("123")));
    }

}
