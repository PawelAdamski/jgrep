package com.github.paweladamski.jgrep;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.function.Function;

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


    @Test
    public void shouldNotFailForEmptyLines() throws IOException {
        grep.grep(lines("","b",""),output,"b");
        assertThat(output.toString(),equalTo(lines("b")));
    }


    @Test
    public void shouldApplyUppercase() throws IOException {
        grep.grep(lines("","b",""),output,"b",new UpperCase());
        assertThat(output.toString(),equalTo(lines("B")));
    }



    @Test
    public void shouldApplyReplaceAndUpperCase() throws IOException {
        grep.grep(lines("abc","b",""),output,"(.)(.)(.)",new Replace("$2$1"),new UpperCase());
        assertThat(output.toString(),equalTo(lines("BA")));
    }

    private String lines(String... lines) {
        String lineSeparator = System.getProperty( "line.separator" );
        return String.join(lineSeparator,lines);
    }

}


class UpperCase implements Function<Line,String >{

    @Override
    public String apply(Line s) {
        return s.getLine().toUpperCase();
    }
}
