package com.github.paweladamski.jgrep;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.nio.CharBuffer;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {

    public void grep(String input, Writer output, String regex, List<Function<Line, String>> lineProcessors) throws IOException {
        grep(new StringReader(input), output, regex, lineProcessors);
    }

    public void grep(Reader input, Writer output, String regex, List<Function<Line, String>> lineProcessors) throws IOException {
        Pattern pattern = Pattern.compile(regex);
        BufferedWriter writer = new BufferedWriter(output);
        BufferedReader reader = new BufferedReader(input);
        boolean somethingFound = false;
        for (; ; ) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                if (somethingFound) {
                    writer.newLine();
                }
                somethingFound = true;
                for (Function<Line, String> lp : lineProcessors) {
                    line = lp.apply(new Line(line,matcher));
                }
                writer.write(line.toString());
            }
        }
        writer.flush();
        reader.close();
    }
}
