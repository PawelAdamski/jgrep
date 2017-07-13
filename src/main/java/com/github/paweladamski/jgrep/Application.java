package com.github.paweladamski.jgrep;

import com.beust.jcommander.JCommander;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Application {
    public static void main(String[] args) {
        try {
            CommandLineOptions cmdLine = parseCommandLine(args);
            Grep grep = new Grep();
            for (Reader reader : readers(cmdLine)) {
                grep.grep(reader, output(), cmdLine.regex, lineProcessors(cmdLine));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Reader> readers(CommandLineOptions cmdLine) throws IOException {
        List<Reader> readers = new ArrayList<>();
        if (cmdLine.files.isEmpty()) {
            readers.add(new InputStreamReader(System.in));
        } else {
            for (File file : cmdLine.files) {
                readers.add(new FileReader(file));
            }
        }
        return readers;
    }

    private static List<Function<Line, String>> lineProcessors(CommandLineOptions cmdLine) {
        List<Function<Line, String>> lineProcessors = new ArrayList<>();
        if (cmdLine.replacement != null) {
            lineProcessors.add(new Replace(cmdLine.replacement));
        }
        return lineProcessors;
    }

    private static Writer output() {
        return new OutputStreamWriter(System.out);
    }

    private static CommandLineOptions parseCommandLine(String[] args) {
        CommandLineOptions cmdLineOptions = new CommandLineOptions();
        JCommander jCommander = JCommander.newBuilder()
                .addObject(cmdLineOptions)
                .build();
        if (args.length == 0) {
            jCommander.usage();
            System.exit(0);
        }

        String[] nargs = Arrays.copyOfRange(args, 1, args.length);

        cmdLineOptions.regex = args[0];


        jCommander.parse(nargs);
        if (cmdLineOptions.help) {
            jCommander.usage();
            System.exit(0);
        }
        return cmdLineOptions;
    }
}

