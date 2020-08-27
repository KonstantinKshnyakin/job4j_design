package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgZip {

    private final String[] args;
    private final Map<String, String> argsMap;
    public static final String D = "d";
    public static final String E = "e";
    public static final String O = "o";

    public ArgZip(String[] args) {
        this.args = args;
        argsMap = new HashMap<>();
        parse();
        valid();
    }

    public boolean valid() {
        if (argsMap.size() != 3) {
            throw new IllegalArgumentException("There must be three parameters:"
                    + " directory - \"-d\", exclude files - \"-e\", output folder - \"-o\".");
        }
        if (!argsMap.containsKey(D)) {
            throw new IllegalArgumentException("There must be parameter directory - \"-d\".");
        }
        if (!argsMap.containsKey(E)) {
            throw new IllegalArgumentException("There must be parameter exclude files- \"-e\".");
        }
        if (!argsMap.containsKey(O)) {
            throw new IllegalArgumentException("There must be parameter output folder - \"-o\"");
        }
        return true;
    }

    public String directory() {
        return argsMap.get(D);
    }

    public String exclude() {
        return argsMap.get(E);
    }

    public String output() {
        return argsMap.get(O);
    }

    private void parse() {
        for (String arg : args) {
            String[] splitArg = arg.replace("-", "").split("=");
            argsMap.put(splitArg[0], splitArg[1]);
        }
    }
}
