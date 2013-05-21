package com.m3.scalaflavor4j;

import static com.m3.scalaflavor4j.CaseClause.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class PartialFTest {

    CaseClause<CSV, CSV> csvCase = _case(CSV.class)._arrow(new F1<CSV, CSV>() {
        public CSV apply(CSV csv) {
            return csv;
        }
    });

    @Test
    public void type() throws Exception {
        assertThat(PartialF.class, notNullValue());
    }

    class CSV {
        String[] values;

        public CSV(String csv) {
            this.values = csv.split(",");
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void __A$PatternFunctionArray() throws Exception {
        PartialF<CSV> pf = new PartialF<CSV>(csvCase);
        assertThat(pf, notNullValue());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void create_A$Seq() throws Exception {
        PartialF<CSV> pf = new PartialF<CSV>(csvCase);
        assertThat(pf, notNullValue());
        assertThat(pf.apply(new CSV("foo,bar,baz")), is(notNullValue()));
        try {
            pf.apply("");
            fail();
        } catch (MatchError e) {
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void instantiation() throws Exception {
        PartialF<CSV> target = new PartialF<CSV>(csvCase);
        assertThat(target, notNullValue());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void __A$CaseClauseArray() throws Exception {
        PartialF<CSV> actual = new PartialF<CSV>(csvCase);
        assertThat(actual, notNullValue());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void __A$Seq() throws Exception {
        Seq<CaseClause<?, CSV>> seq = Seq.<CaseClause<?, CSV>> apply(csvCase);
        PartialF<CSV> actual = PartialF.<CSV> apply(seq);
        assertThat(actual, notNullValue());
    }

}
