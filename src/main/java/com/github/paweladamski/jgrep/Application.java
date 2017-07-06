package com.github.paweladamski.jgrep;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Application {
    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            String regex = args[0];
            File filePath = new File(args[1]);
            Grep grep = new Grep();
            Writer writer  = new OutputStreamWriter(System.out);
            //grep.main(filePath,regex);
            //grep.grep(filePath,writer,regex);
            grep.grep(new FileReader(filePath),writer,regex);
            long end = System.currentTimeMillis();

            System.out.println();
            System.out.println("Time: "+(end-start));
        } catch (IOException e) {

            e.printStackTrace();
        }


    }
}
