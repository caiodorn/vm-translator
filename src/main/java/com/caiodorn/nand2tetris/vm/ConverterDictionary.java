package com.caiodorn.nand2tetris.vm;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class ConverterDictionary {

    private static final Map<VmCommandTypeEnum, Function<String, List<String>>> CONVERTERS;

    static {
        Map<VmCommandTypeEnum, Function<String, List<String>>> converters = new HashMap<>();
        converters.put(VmCommandTypeEnum.PUSH_CONSTANT, Converters.PUSH_CONSTANT);
        converters.put(VmCommandTypeEnum.PUSH_LOCAL, Converters.PUSH_LOCAL);
        converters.put(VmCommandTypeEnum.POP_LOCAL, Converters.POP_LOCAL);
        converters.put(VmCommandTypeEnum.PUSH_STATIC, Converters.PUSH_STATIC);
        converters.put(VmCommandTypeEnum.POP_STATIC, Converters.POP_STATIC);
        converters.put(VmCommandTypeEnum.PUSH_THIS, Converters.PUSH_THIS);
        converters.put(VmCommandTypeEnum.POP_THIS, Converters.POP_THIS);
        converters.put(VmCommandTypeEnum.PUSH_THAT, Converters.PUSH_THAT);
        converters.put(VmCommandTypeEnum.POP_THAT, Converters.POP_THAT);
        converters.put(VmCommandTypeEnum.PUSH_TEMP, Converters.PUSH_TEMP);
        converters.put(VmCommandTypeEnum.POP_TEMP, Converters.POP_TEMP);
        converters.put(VmCommandTypeEnum.PUSH_POINTER, Converters.PUSH_POINTER);
        converters.put(VmCommandTypeEnum.POP_POINTER, Converters.POP_POINTER);
        converters.put(VmCommandTypeEnum.PUSH_ARGUMENT, Converters.PUSH_ARGUMENT);
        converters.put(VmCommandTypeEnum.POP_ARGUMENT, Converters.POP_ARGUMENT);
        converters.put(VmCommandTypeEnum.ADD, Converters.ADD);
        converters.put(VmCommandTypeEnum.SUB, Converters.SUB);
        converters.put(VmCommandTypeEnum.NEG, Converters.NEG);
        converters.put(VmCommandTypeEnum.EQ, Converters.EQ);
        converters.put(VmCommandTypeEnum.GT, Converters.GT);
        converters.put(VmCommandTypeEnum.LT, Converters.LT);
        converters.put(VmCommandTypeEnum.AND, Converters.AND);
        converters.put(VmCommandTypeEnum.OR, Converters.OR);
        converters.put(VmCommandTypeEnum.NOT, Converters.NOT);
        CONVERTERS = Collections.unmodifiableMap(converters);
    }

    private ConverterDictionary() {
    }

    public static Function<String, List<String>> get(VmCommandTypeEnum commandType) {
        return CONVERTERS.get(commandType);
    }

}
