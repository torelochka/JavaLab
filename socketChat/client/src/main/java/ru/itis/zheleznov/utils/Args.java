package ru.itis.zheleznov.utils;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.util.List;

@Parameters(separators = "=")
public class Args {

    @Parameter(names = {"--server-ip"}, required = true)
    public String host;

    @Parameter(names = {"--server-port"})
    public Integer port;
}
