package com.github.paweladamski.jgrep;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class GrepTest {

    Grep grep = new Grep();
    Writer output;
    List<Function<Line, String>> lineProcessors;


    @Before
    public void setUp() {
        output = new StringWriter();
        lineProcessors = new ArrayList<>();
    }

    @Test
    public void shouldReturnEmptyStringWhenInputIsEmpty() throws IOException {
        grep.grep("", output, "foo", lineProcessors);
        assertThat(output.toString(), equalTo(""));
    }


    @Test
    public void shouldReturnLinesMatchingRegex() throws IOException {
        grep.grep(lines("a", "b", "c"), output, "a|c", lineProcessors);
        assertThat(output.toString(), equalTo(lines("a", "c")));
    }


    @Test
    public void shouldNotFailForEmptyLines() throws IOException {
        grep.grep(lines("", "b", ""), output, "b", lineProcessors);
        assertThat(output.toString(), equalTo(lines("b")));
    }


    @Test
    public void shouldApplyUppercase() throws IOException {
        lineProcessors.add(new UpperCase());
        grep.grep(lines("", "b", ""), output, "b", lineProcessors);
        assertThat(output.toString(), equalTo(lines("B")));
    }


    @Test
    public void shouldApplyReplaceAndUpperCase() throws IOException {
        lineProcessors.add(new Replace("$2$1"));
        lineProcessors.add(new UpperCase());
        grep.grep(lines("abc", "b", ""), output, "(.)(.)(.)", lineProcessors);
        assertThat(output.toString(), equalTo(lines("BA")));
    }


    private String lines(String... lines) {
        String lineSeparator = System.getProperty("line.separator");
        return String.join(lineSeparator, lines) + lineSeparator;
    }

}


class UpperCase implements Function<Line, String> {

    @Override
    public String apply(Line s) {
        return s.getLine().toUpperCase();
    }
}
