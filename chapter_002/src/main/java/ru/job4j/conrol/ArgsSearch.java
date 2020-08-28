package ru.job4j.conrol;

import java.util.HashMap;
import java.util.Map;

import static ru.job4j.conrol.ArgsEnum.SEARCH_BY;
import static ru.job4j.conrol.StrConstant.*;

public class ArgsSearch {

    private final String[] args;
    private final Map<String, String> argsMap;

    public ArgsSearch(String[] args) {
        this.args = args;
        argsMap = new HashMap<>();
        parseArgs();
    }

    public String getDirectory() {
        return argsMap.get(D);
    }

    public String getName() {
        return argsMap.get(N);
    }

    public String getOutput() {
        return argsMap.get(O);
    }

    public String getSearchMethod() {
        return argsMap.get(S);
    }

    private void parseArgs() {
        checkSizeArgs();
        for (ArgsEnum argsEnum : ArgsEnum.values()) {
            if (!argsEnum.equals(SEARCH_BY)) {
                parseArgDNO(argsEnum);
            } else {
                parseArg(argsEnum);
            }
        }
    }

    private void checkSizeArgs() {
        StringBuilder builder = new StringBuilder();
        if (args.length != 7) {
            for (ArgsEnum argsEnum : ArgsEnum.values()) {
                builder.append(argsEnum.getException()).append(System.lineSeparator());
            }
            throwException(builder.toString());
        }
    }

    private void parseArg(ArgsEnum arg) {
        if (args[arg.getKeyIndex()].matches("-m|-f|-r")) {
            argsMap.put(arg.getKey(), args[arg.getValueIndex()]);
        } else {
            throwException(arg.getException());
        }
    }

    private void parseArgDNO(ArgsEnum arg) {
        if (args[arg.getKeyIndex()].equals(arg.getKey())) {
            argsMap.put(arg.getKey(), args[arg.getValueIndex()]);
        } else {
            throwException(arg.getException());
        }
    }

    private void throwException(String exception) {
        throw new IllegalArgumentException(exception + " All parameters are specified with a space."
                + " Example: -d c:/ -n *.txt -m -o log.txt");
    }
}
