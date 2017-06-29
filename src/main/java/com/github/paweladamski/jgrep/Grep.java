package com.github.paweladamski.jgrep;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;

public class Grep {



    public void grep(String input, Writer output, String regex) throws IOException {
        grep(new StringReader(input),output,regex);
    }


    public void grep(Reader input, Writer output, String regex) throws IOException {
        output.write("");
    }
}
