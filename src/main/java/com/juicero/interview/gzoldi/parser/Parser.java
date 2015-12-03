package com.juicero.interview.gzoldi.parser;

import org.testng.annotations.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by gzold7195817 on 12/2/15.
 */
public class Parser {

    private boolean isNumeric(String str) {
        try {
            int i = Integer.parseInt(str);
        }
        catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

    private Map<String, String> recount(String pattern) {
        // new bucket with deterministic counts
        Map<String, String> deterministic = new HashMap<String, String>();

        // parse to list
        LinkedList list = new LinkedList( Arrays.asList( pattern.split("") ) );

        // go thru list to extract counts and character
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            String val = null;
            String count = (String) iterator.next();

            if (isNumeric(count)) {
                // fast-forward to next element
                val = iterator.next().toString();
            }
            else {
                val = count;
                count = "1";
            }
            deterministic.put(val, count);
        }

        return deterministic;
    }

    private String flatten(Map<String, String> recounted) {
        String flat = "";

        Iterator it = recounted.entrySet().iterator();
        while (it.hasNext()) {
            // get key, value pair
            Map.Entry pair = (Map.Entry) it.next();
            String val = (String) pair.getKey();
            int count = Integer.valueOf( (String) pair.getValue() );

            // append character count times
            for (int i = 0; i < count; i++)
                flat +=val;
        }

        return flat;
    }

    public String flatten(String pattern) {
        return flatten(recount(pattern));
    }

    public static void main(String[] args) {
        //String raw = "2a3b4cd";
        String raw = "d4c3b2a";

        Map<String, String> recounted = new Parser().recount(raw);
        System.out.println("recount:  " + raw + "  ====>  " + recounted);

        String flattened = new Parser().flatten(recounted);
        System.out.println("flatten:  " + recounted + "  ====>  " + flattened);
    }


    /*
     * test cases
     */
    @Test
    public void smoke() {
        assertThat("smoke failed",
                new Parser().flatten("2a3b4cd"), equalTo("aabbbccccd"));
    }

    @Test
    public void empty() {
        assertThat("empty failed",
                new Parser().flatten(""), equalTo(""));
    }

    @Test
    public void reverse_smoke() {
        assertThat("reverse failed",
                new Parser().flatten("d4c3b2a"), equalTo("dccccbbbaa"));
        // TODO: this will fail, insert character order is not maintained
    }

}