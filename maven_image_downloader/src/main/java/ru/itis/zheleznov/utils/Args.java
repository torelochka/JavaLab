package ru.itis.zheleznov.utils;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.util.List;

@Parameters(separators = "=")
public class Args {

    @Parameter(names = {"--mode"}, validateWith = ModeValidator.class, required = true)
    public String mode;

    @Parameter(names = {"--files"}, listConverter = UrlListConverter.class, required = true)
    public List<String> files;

    @Parameter(names = {"--count"})
    public Integer count = 1;

    @Parameter(names = {"--folder"}, required = true)
    public String folder;
}
