package ru.itis.zheleznov.utils;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class ModeValidator implements IParameterValidator {

    @Override
    public void validate(String name, String value) throws ParameterException {
        if (!value.equals("one-thread") && !value.equals("multi-thread")) {
            throw new ParameterException("Parameter " + name + "should be equals 'one-thread' or 'multi-thread'");
        }
    }
}
