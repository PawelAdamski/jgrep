package com.github.paweladamski.jgrep;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class GrepTest {

    Grep grep = new Grep();
    Writer output ;


    @Before
    public void setUp() {
        output = new StringWriter();
    }

    @Test
    public void shouldReturnEmptyStringWhenInputIsEmpty() throws IOException {
        grep.grep("",output,"foo");
        assertThat(output.toString(),equalTo(""));
    }


    @Test
    public void shouldReturnLinesMatchingRegex() throws IOException {
        grep.grep(lines("a","b","c"),output,"a|c");
        assertThat(output.toString(),equalTo(lines("a","c")));
    }

    private String lines(String... lines) {
        String lineSeparator = System.getProperty( "line.separator" );
        return String.join(lineSeparator,lines);
    }

}
