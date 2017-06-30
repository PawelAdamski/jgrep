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
import java.util.Scanner;
import java.util.regex.Pattern;

public class Grep {



    public void grep(String input, Writer output, String regex) throws IOException {
        grep(new StringReader(input),output,regex);
    }


    public void grep(Reader input, Writer output, String regex) throws IOException {
        Pattern pattern = Pattern.compile(regex);
        BufferedWriter writer = new BufferedWriter(output);

        BufferedReader reader = new BufferedReader(input);
        Scanner scanner = new Scanner(reader);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (pattern.matcher(line).find()){
                writer.write(line);
                if (scanner.hasNextLine()) {
                    writer.newLine();
                }
            }
        }
        writer.flush();
        scanner.close();
    }
}
