package com.caiodorn.nand2tetris.vm;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.caiodorn.nand2tetris.vm.Converters.ADD;
import static com.caiodorn.nand2tetris.vm.Converters.AND;
import static com.caiodorn.nand2tetris.vm.Converters.EQ;
import static com.caiodorn.nand2tetris.vm.Converters.GT;
import static com.caiodorn.nand2tetris.vm.Converters.LT;
import static com.caiodorn.nand2tetris.vm.Converters.NEG;
import static com.caiodorn.nand2tetris.vm.Converters.NOT;
import static com.caiodorn.nand2tetris.vm.Converters.OR;
import static com.caiodorn.nand2tetris.vm.Converters.POP_ARGUMENT;
import static com.caiodorn.nand2tetris.vm.Converters.POP_LOCAL;
import static com.caiodorn.nand2tetris.vm.Converters.POP_POINTER;
import static com.caiodorn.nand2tetris.vm.Converters.POP_STATIC;
import static com.caiodorn.nand2tetris.vm.Converters.POP_TEMP;
import static com.caiodorn.nand2tetris.vm.Converters.POP_THAT;
import static com.caiodorn.nand2tetris.vm.Converters.POP_THIS;
import static com.caiodorn.nand2tetris.vm.Converters.PUSH_ARGUMENT;
import static com.caiodorn.nand2tetris.vm.Converters.PUSH_CONSTANT;
import static com.caiodorn.nand2tetris.vm.Converters.PUSH_LOCAL;
import static com.caiodorn.nand2tetris.vm.Converters.PUSH_POINTER;
import static com.caiodorn.nand2tetris.vm.Converters.PUSH_STATIC;
import static com.caiodorn.nand2tetris.vm.Converters.PUSH_TEMP;
import static com.caiodorn.nand2tetris.vm.Converters.PUSH_THAT;
import static com.caiodorn.nand2tetris.vm.Converters.PUSH_THIS;
import static com.caiodorn.nand2tetris.vm.Converters.SUB;

public final class ConverterDictionary {

    private static final Map<VmCommandTypeEnum, Function<String, List<String>>> CONVERTERS;

    static {
        Map<VmCommandTypeEnum, Function<String, List<String>>> converters = new HashMap<>();
        converters.put(VmCommandTypeEnum.PUSH_CONSTANT, PUSH_CONSTANT);
        converters.put(VmCommandTypeEnum.PUSH_LOCAL, PUSH_LOCAL);
        converters.put(VmCommandTypeEnum.POP_LOCAL, POP_LOCAL);
        converters.put(VmCommandTypeEnum.PUSH_STATIC, PUSH_STATIC);
        converters.put(VmCommandTypeEnum.POP_STATIC, POP_STATIC);
        converters.put(VmCommandTypeEnum.PUSH_THIS, PUSH_THIS);
        converters.put(VmCommandTypeEnum.POP_THIS, POP_THIS);
        converters.put(VmCommandTypeEnum.PUSH_THAT, PUSH_THAT);
        converters.put(VmCommandTypeEnum.POP_THAT, POP_THAT);
        converters.put(VmCommandTypeEnum.PUSH_TEMP, PUSH_TEMP);
        converters.put(VmCommandTypeEnum.POP_TEMP, POP_TEMP);
        converters.put(VmCommandTypeEnum.PUSH_POINTER, PUSH_POINTER);
        converters.put(VmCommandTypeEnum.POP_POINTER, POP_POINTER);
        converters.put(VmCommandTypeEnum.PUSH_ARGUMENT, PUSH_ARGUMENT);
        converters.put(VmCommandTypeEnum.POP_ARGUMENT, POP_ARGUMENT);
        converters.put(VmCommandTypeEnum.ADD, ADD);
        converters.put(VmCommandTypeEnum.SUB, SUB);
        converters.put(VmCommandTypeEnum.NEG, NEG);
        converters.put(VmCommandTypeEnum.EQ, EQ);
        converters.put(VmCommandTypeEnum.GT, GT);
        converters.put(VmCommandTypeEnum.LT, LT);
        converters.put(VmCommandTypeEnum.AND, AND);
        converters.put(VmCommandTypeEnum.OR, OR);
        converters.put(VmCommandTypeEnum.NOT, NOT);
        CONVERTERS = Collections.unmodifiableMap(converters);
    }

    private ConverterDictionary() {
    }

    public static Function<String, List<String>> get(VmCommandTypeEnum commandType) {
        return CONVERTERS.get(commandType);
    }

}
