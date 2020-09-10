package com.caiodorn.nand2tetris.vm;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class ConverterDictionary {

    private static final Map<VMCommandTypeEnum, Function<String, List<String>>> CONVERTERS = Map.ofEntries(
            Map.entry(VMCommandTypeEnum.PUSH_CONSTANT, Converters.PUSH_CONSTANT),
            Map.entry(VMCommandTypeEnum.PUSH_LOCAL, Converters.PUSH_LOCAL),
            Map.entry(VMCommandTypeEnum.POP_LOCAL, Converters.POP_LOCAL),
            Map.entry(VMCommandTypeEnum.PUSH_STATIC, Converters.PUSH_STATIC),
            Map.entry(VMCommandTypeEnum.POP_STATIC, Converters.POP_STATIC),
            Map.entry(VMCommandTypeEnum.PUSH_THIS, Converters.PUSH_THIS),
            Map.entry(VMCommandTypeEnum.POP_THIS, Converters.POP_THIS),
            Map.entry(VMCommandTypeEnum.PUSH_THAT, Converters.PUSH_THAT),
            Map.entry(VMCommandTypeEnum.POP_THAT, Converters.POP_THAT),
            Map.entry(VMCommandTypeEnum.PUSH_TEMP, Converters.PUSH_TEMP),
            Map.entry(VMCommandTypeEnum.POP_TEMP, Converters.POP_TEMP),
            Map.entry(VMCommandTypeEnum.PUSH_POINTER, Converters.PUSH_POINTER),
            Map.entry(VMCommandTypeEnum.POP_POINTER, Converters.POP_POINTER),
            Map.entry(VMCommandTypeEnum.PUSH_ARGUMENT, Converters.PUSH_ARGUMENT),
            Map.entry(VMCommandTypeEnum.POP_ARGUMENT, Converters.POP_ARGUMENT),
            Map.entry(VMCommandTypeEnum.ADD, Converters.ADD),
            Map.entry(VMCommandTypeEnum.SUB, Converters.SUB),
            Map.entry(VMCommandTypeEnum.NEG, Converters.NEG),
            Map.entry(VMCommandTypeEnum.EQ, Converters.EQ),
            Map.entry(VMCommandTypeEnum.GT, Converters.GT),
            Map.entry(VMCommandTypeEnum.LT, Converters.LT),
            Map.entry(VMCommandTypeEnum.AND, Converters.AND),
            Map.entry(VMCommandTypeEnum.OR, Converters.OR),
            Map.entry(VMCommandTypeEnum.NOT, Converters.NOT),
            Map.entry(VMCommandTypeEnum.GOTO, Converters.GOTO),
            Map.entry(VMCommandTypeEnum.IF_GOTO, Converters.IF_GOTO),
            Map.entry(VMCommandTypeEnum.LABEL, Converters.LABEL),
            Map.entry(VMCommandTypeEnum.FUNCTION, Converters.FUNCTION),
            Map.entry(VMCommandTypeEnum.RETURN, Converters.RETURN),
            Map.entry(VMCommandTypeEnum.CALL, Converters.CALL)
    );

    private ConverterDictionary() {}

    public static Function<String, List<String>> get(VMCommandTypeEnum commandType) {
        return CONVERTERS.get(commandType);
    }

}
