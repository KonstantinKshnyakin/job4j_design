package ru.job4j.conrol;

import static ru.job4j.conrol.StrConstant.*;

public enum ArgsEnum {

    DIRECTORY(0, 1, D, "The first parameter must be \"-d\" - directory."),
    NAME(2, 3, N, "The second parameter must be \"-n\": file name - \"file.txt\", "
            + "masks - \"*.txt\" or patterns - \"f*.txt\""),
    SEARCH_BY(4, 4, S, "The third parameter is to choose how to search:"
            + " \"-m\" - by mask, \"-f\" - by name match or \"-r\" - by regular expression."),
    OUTPUT(5, 6, O, "The fourth parameter is the file where the result will be written.");

    private final String exception;
    private final int keyIndex;
    private final int valueIndex;
    private final String key;

    ArgsEnum(int keyIndex, int valueIndex, String key, String exception) {
        this.keyIndex = keyIndex;
        this.valueIndex = valueIndex;
        this.key = key;
        this.exception = exception;
    }

    public int getKeyIndex() {
        return keyIndex;
    }

    public int getValueIndex() {
        return valueIndex;
    }

    public String getKey() {
        return key;
    }

    public String getException() {
        return exception;
    }
}
