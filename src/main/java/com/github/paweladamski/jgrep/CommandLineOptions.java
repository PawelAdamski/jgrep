package com.github.paweladamski.jgrep;

import com.beust.jcommander.Parameter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CommandLineOptions {

    @Parameter(description = "Files")
    public List<File> files = new ArrayList<>();

    @Parameter(names={ "-d", "--replace" }, description = "String used to replace matching.")
    public String replacement;

    public String regex;

}
