package com.caiodorn.nand2tetris.vm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class ConverterDictionary {

    private static final String CMD_SEPARATOR = " ";
    private static final List<String> PUSH;
    private static final List<String> POP;

    static {
        List<String> pushCommands = new ArrayList<>();
        pushCommands.add("@SP");
        pushCommands.add("AM=M+1");
        pushCommands.add("M-1=D");
        PUSH = Collections.unmodifiableList(pushCommands);

        List<String> popCommands = new ArrayList<>();
        popCommands.add("@SP");
        popCommands.add("AM=M-1");
        popCommands.add("D=M");
        POP = Collections.unmodifiableList(popCommands);
    }

    private static final Function<String, List<String>> PUSH_CONSTANT = (s) -> {
        List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add("@" + s.split(CMD_SEPARATOR)[2]);
        assemblyCommands.add("D=A");
        assemblyCommands.addAll(PUSH);

        return assemblyCommands;
    };

    private static final Function<String, List<String>> PUSH_LOCAL = (s) -> {
        List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add("@" + s.split(CMD_SEPARATOR)[2]);
        assemblyCommands.add("D=A");
        assemblyCommands.add("@LCL");
        assemblyCommands.add("A=D+M");
        assemblyCommands.add("D=M");
        assemblyCommands.add("@LCL");
        assemblyCommands.add("M=M-1");
        assemblyCommands.addAll(PUSH);

        return assemblyCommands;
    };

    private static final Function<String, List<String>> POP_LOCAL = (s) -> {
        List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add("@" + s.split(CMD_SEPARATOR)[2]);
        assemblyCommands.add("D=A");
        assemblyCommands.add("@LCL");
        assemblyCommands.add("D=D+M");
        assemblyCommands.add("@R13");
        assemblyCommands.add("M=D");
        assemblyCommands.addAll(POP);
        assemblyCommands.add("@R13");
        assemblyCommands.add("A=M");
        assemblyCommands.add("M=D");

        return assemblyCommands;
    };

    private static final Map<VmCommandTypeEnum, Function<String, List<String>>> CONVERTERS = Map.of(
            VmCommandTypeEnum.PUSH_CONSTANT, PUSH_CONSTANT,
            VmCommandTypeEnum.PUSH_LOCAL, PUSH_LOCAL,
            VmCommandTypeEnum.POP_LOCAL, POP_LOCAL
    );

    private ConverterDictionary() {}

    public static Function<String, List<String>> get(VmCommandTypeEnum commandType) {
        return CONVERTERS.get(commandType);
    }

}
