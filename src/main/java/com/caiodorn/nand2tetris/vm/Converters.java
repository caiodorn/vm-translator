package com.caiodorn.nand2tetris.vm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class Converters {

    private static final String CMD_SEPARATOR = " ";

    private static final Function<String, List<String>> PUSH_CONSTANT_CONVERTER = (s) -> {
        List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add("@" + s.split(CMD_SEPARATOR)[2]);
        assemblyCommands.add("D=A");
        assemblyCommands.add("@SP");
        assemblyCommands.add("A=M");
        assemblyCommands.add("M=D");
        assemblyCommands.add("@SP");
        assemblyCommands.add("M=M+1");

        return assemblyCommands;
    };

    private static final Function<String, List<String>> PUSH_LOCAL_CONVERTER = (s) -> {
        List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add("@" + s.split(CMD_SEPARATOR)[2]);
        assemblyCommands.add("D=A");
        assemblyCommands.add("@LCL");
        assemblyCommands.add("A=D+M");
        assemblyCommands.add("D=M");
        assemblyCommands.add("@LCL");
        assemblyCommands.add("M=M-1");
        assemblyCommands.add("@SP");
        assemblyCommands.add("M=M+1");
        assemblyCommands.add("A=M-1");
        assemblyCommands.add("M=D");

        return assemblyCommands;
    };

    private static final Map<VmCommandTypeEnum, Function<String, List<String>>> CONVERTERS = Map.of(
            VmCommandTypeEnum.PUSH_CONSTANT, PUSH_CONSTANT_CONVERTER,
            VmCommandTypeEnum.PUSH_LOCAL, PUSH_LOCAL_CONVERTER
    );

    private Converters() {}

    public static Function<String, List<String>> getConverter(String vmCommandType) {
        return CONVERTERS.get(VmCommandTypeEnum.fromType(vmCommandType));
    }

}
