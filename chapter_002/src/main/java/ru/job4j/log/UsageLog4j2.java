package ru.job4j.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j2 {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 127;
        short sh = 32000;
        int i = 21312324;
        long l = 482384231L;
        float f = 2154.2F;
        double d = 456765.45;
        boolean bool = true;
        char ch = 's';
        LOG.debug("byte = {}, short = {}, int = {}, long = {}", b, sh, i, l);
        LOG.debug("float = {}, double = {}, boolean = {}, char = {}", f, d, bool, ch);
    }
}