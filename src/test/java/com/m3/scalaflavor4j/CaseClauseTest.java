package com.m3.scalaflavor4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.m3.scalaflavor4j.CaseClause.CaseClauseBuilder;

public class CaseClauseTest {

    @Test
    public void type() throws Exception {
        assertThat(CaseClause.class, notNullValue());
    }

    @Test
    public void _case_A$Class() throws Exception {
        Class<String> clazz = String.class;
        CaseClauseBuilder<String> actual = CaseClause._case(clazz);
        assertThat(actual, notNullValue());
    }

    @Test
    public void _case_A$Extractor() throws Exception {
        Extractor<String> extractor_ = new Extractor<String>() {
            public String extract(Object v) throws Exception {
                if (v == null) {
                    return null;
                }
                return v.toString();
            }
        };
        CaseClauseBuilder<String> actual = CaseClause._case(extractor_);
        assertThat(actual, notNullValue());
    }

    @Test
    public void __A$Object_null() throws Exception {
        CaseClause<String, String> caseClause = CaseClause._case(String.class)._arrow(new F1<String, String>() {
            public String _(String v1) throws Exception {
                return v1;
            }
        });
        Object v = null;
        Option<String> actual = caseClause._(v);
        assertThat(actual.isDefined(), is(false));
    }

    @Test
    public void __A$Object_Integer() throws Exception {
        CaseClause<String, String> caseClause = CaseClause._case(String.class)._arrow(new F1<String, String>() {
            public String _(String v1) throws Exception {
                return v1;
            }
        });
        Object v = 123;
        Option<String> actual = caseClause._(v);
        assertThat(actual.isDefined(), is(false));
    }

    @Test
    public void __A$Object_String() throws Exception {
        CaseClause<String, String> caseClause = CaseClause._case(String.class)._arrow(new F1<String, String>() {
            public String _(String v1) throws Exception {
                return v1;
            }
        });
        Object v = "foo";
        Option<String> actual = caseClause._(v);
        assertThat(actual.isDefined(), is(true));
    }

    @Test
    public void isDefinedAt_A$Object_null() throws Exception {
        CaseClause<String, String> caseClause = CaseClause._case(String.class)._arrow(new F1<String, String>() {
            public String _(String v1) throws Exception {
                return v1;
            }
        });
        Object v = null;
        boolean actual = caseClause.isDefinedAt(v);
        boolean expected = false;
        assertThat(actual, is(equalTo(expected)));
    }

}
