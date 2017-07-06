package com.github.paweladamski.jgrep;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Line {

    private String line;
    private Matcher matcher;

    public Line(String line,Matcher matcher) {
        this.line = line;
        this.matcher = matcher;
    }

    public String getLine() {
        return line;
    }

    public Matcher getMatcher() {
        return matcher;
    }
}
