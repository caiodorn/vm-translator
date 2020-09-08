package com.caiodorn.nand2tetris.vm;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class ConverterDictionary {

    private static final Map<VMCommandTypeEnum, Function<String, List<String>>> CONVERTERS;

    static {
        Map<VMCommandTypeEnum, Function<String, List<String>>> converters = new HashMap<>();
        converters.put(VMCommandTypeEnum.PUSH_CONSTANT, Converters.PUSH_CONSTANT);
        converters.put(VMCommandTypeEnum.PUSH_LOCAL, Converters.PUSH_LOCAL);
        converters.put(VMCommandTypeEnum.POP_LOCAL, Converters.POP_LOCAL);
        converters.put(VMCommandTypeEnum.PUSH_STATIC, Converters.PUSH_STATIC);
        converters.put(VMCommandTypeEnum.POP_STATIC, Converters.POP_STATIC);
        converters.put(VMCommandTypeEnum.PUSH_THIS, Converters.PUSH_THIS);
        converters.put(VMCommandTypeEnum.POP_THIS, Converters.POP_THIS);
        converters.put(VMCommandTypeEnum.PUSH_THAT, Converters.PUSH_THAT);
        converters.put(VMCommandTypeEnum.POP_THAT, Converters.POP_THAT);
        converters.put(VMCommandTypeEnum.PUSH_TEMP, Converters.PUSH_TEMP);
        converters.put(VMCommandTypeEnum.POP_TEMP, Converters.POP_TEMP);
        converters.put(VMCommandTypeEnum.PUSH_POINTER, Converters.PUSH_POINTER);
        converters.put(VMCommandTypeEnum.POP_POINTER, Converters.POP_POINTER);
        converters.put(VMCommandTypeEnum.PUSH_ARGUMENT, Converters.PUSH_ARGUMENT);
        converters.put(VMCommandTypeEnum.POP_ARGUMENT, Converters.POP_ARGUMENT);
        converters.put(VMCommandTypeEnum.ADD, Converters.ADD);
        converters.put(VMCommandTypeEnum.SUB, Converters.SUB);
        converters.put(VMCommandTypeEnum.NEG, Converters.NEG);
        converters.put(VMCommandTypeEnum.EQ, Converters.EQ);
        converters.put(VMCommandTypeEnum.GT, Converters.GT);
        converters.put(VMCommandTypeEnum.LT, Converters.LT);
        converters.put(VMCommandTypeEnum.AND, Converters.AND);
        converters.put(VMCommandTypeEnum.OR, Converters.OR);
        converters.put(VMCommandTypeEnum.NOT, Converters.NOT);
        converters.put(VMCommandTypeEnum.GOTO, Converters.GOTO);
        converters.put(VMCommandTypeEnum.IF_GOTO, Converters.IF_GOTO);
        CONVERTERS = Collections.unmodifiableMap(converters);
    }

    private ConverterDictionary() {
    }

    public static Function<String, List<String>> get(VMCommandTypeEnum commandType) {
        return CONVERTERS.get(commandType);
    }

}
