package com.caiodorn.nand2tetris.vm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class ConverterDictionary {

    private static final String CMD_SEPARATOR = " ";
    public static int counter = 0; // TODO think of a better way of managing this

    private static final List<String> PUSH;
    static {
        List<String> pushCommands = new ArrayList<>();
        pushCommands.add("@SP"); 
        pushCommands.add("AM=M+1");
        pushCommands.add("A=A-1");
        pushCommands.add("M=D");
        PUSH = Collections.unmodifiableList(pushCommands);
    }

    private static final List<String> POP;
    static {
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

    private static final Function<String, List<String>> ADD = (s) -> {
        List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.addAll(POP);
        assemblyCommands.add("A=M-1");
        assemblyCommands.add("M=D+M");

        return assemblyCommands;
    };

    private static final Function<String, List<String>> SUB = (s) -> {
        List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.addAll(POP);
        assemblyCommands.add("A=M-1");
        assemblyCommands.add("M=D-M");

        return assemblyCommands;
    };

    private static final Function<String, List<String>> NEG = (s) -> {
        List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add("@SP");
        assemblyCommands.add("A=M-1");
        assemblyCommands.add("M=-M");

        return assemblyCommands;
    };

    private static final Function<String, List<String>> EQ = (s) -> {
        List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add(String.format("@RETURN_TO_%d", ++counter));
        assemblyCommands.add("D=A");
        assemblyCommands.add("@R14");
        assemblyCommands.add("M=D");
        assemblyCommands.add("@BEGIN_EQ");
        assemblyCommands.add("0;JMP");
        assemblyCommands.add(String.format("(RETURN_TO_%d)", counter));

        return assemblyCommands;
    };

    private static final Function<String, List<String>> GT = (s) -> {
        List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add(String.format("@RETURN_TO_%d", ++counter));
        assemblyCommands.add("D=A");
        assemblyCommands.add("@R14");
        assemblyCommands.add("M=D");
        assemblyCommands.add("@BEGIN_GT");
        assemblyCommands.add("0;JMP");
        assemblyCommands.add(String.format("(RETURN_TO_%d)", counter));

        return assemblyCommands;
    };

    private static final Function<String, List<String>> LT = (s) -> {
        List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add(String.format("@RETURN_TO_%d", ++counter));
        assemblyCommands.add("D=A");
        assemblyCommands.add("@R14");
        assemblyCommands.add("M=D");
        assemblyCommands.add("@BEGIN_LT");
        assemblyCommands.add("0;JMP");
        assemblyCommands.add(String.format("(RETURN_TO_%d)", counter));

        return assemblyCommands;
    };

    private static final Function<String, List<String>> AND = (s) -> {
        List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.addAll(POP);
        assemblyCommands.add("@SP");
        assemblyCommands.add("A=M-1");
        assemblyCommands.add("M=D&M");

        return assemblyCommands;
    };

    private static final Function<String, List<String>> OR = (s) -> {
        List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.addAll(POP);
        assemblyCommands.add("@SP");
        assemblyCommands.add("A=M-1");
        assemblyCommands.add("M=D|M");

        return assemblyCommands;
    };

    private static final Function<String, List<String>> NOT = (s) -> {
        List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add("@SP");
        assemblyCommands.add("A=M-1");
        assemblyCommands.add("M=!M");

        return assemblyCommands;
    };

    private static final Map<VmCommandTypeEnum, Function<String, List<String>>> CONVERTERS;

    static {
        Map<VmCommandTypeEnum, Function<String, List<String>>> converters = new HashMap<>();
        converters.put(VmCommandTypeEnum.PUSH_CONSTANT, PUSH_CONSTANT);
        converters.put(VmCommandTypeEnum.PUSH_LOCAL, PUSH_LOCAL);
        converters.put(VmCommandTypeEnum.POP_LOCAL, POP_LOCAL);
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
