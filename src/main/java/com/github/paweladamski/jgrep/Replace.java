package com.github.paweladamski.jgrep;

import java.util.function.Function;

public class Replace implements Function<Line,String> {

    private String replacement;

    public Replace(String replacement) {
        this.replacement = replacement;
    }


    @Override
    public String apply(Line line) {
        return line.getMatcher().replaceAll(replacement);
    }
}
