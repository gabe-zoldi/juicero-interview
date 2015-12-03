package com.juicero.interview.gzoldi;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by gzold7195817 on 12/2/15.
 */
public class ParserTest {

    @Test
    public void smoke() {
        assertThat( "smoke failed",
                new Parser().flatten("2a3b4cd"), equalTo("aabbbccccd")
        );
    }

    @Test
    public void empty() {
        assertThat( "empty failed",
                new Parser().flatten(""), equalTo("")
        );
    }

    @Test
    public void reverse_smoke() {
        assertThat( "smoke reversed",
                new Parser().flatten("d4c3b2a"), equalTo("dccccbbbaa")
        );
        // TODO: this will fail, insert character order is not maintained
    }

}