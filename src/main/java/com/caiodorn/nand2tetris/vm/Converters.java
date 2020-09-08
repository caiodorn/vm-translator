package com.caiodorn.nand2tetris.vm;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public final class Converters {

    private static final String CMD_SEPARATOR = " ";
    private static final int TEMP_BASE_ADDR = 5;

    public  static int returnLabelCount = 0;

    private static final List<String> PUSH = List.of(
            "@SP",
            "AM=M+1",
            "A=A-1",
            "M=D"
    );

    private static final List<String> POP = List.of(
            "@SP",
            "AM=M-1",
            "D=M"
    );

    public static final Function<String, List<String>> PUSH_CONSTANT = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add("@" + s.split(CMD_SEPARATOR)[2]);
        assemblyCommands.add("D=A");
        assemblyCommands.addAll(PUSH);

        return assemblyCommands;
    };

    public static final Function<String, List<String>> PUSH_LOCAL = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add("@" + s.split(CMD_SEPARATOR)[2]);
        assemblyCommands.add("D=A");
        assemblyCommands.add("@LCL");
        assemblyCommands.add("A=D+M");
        assemblyCommands.add("D=M");
        assemblyCommands.addAll(PUSH);

        return assemblyCommands;
    };

    public static final Function<String, List<String>> POP_LOCAL = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
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

    public static final Function<String, List<String>> POP_ARGUMENT = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add("@" + s.split(CMD_SEPARATOR)[2]);
        assemblyCommands.add("D=A");
        assemblyCommands.add("@ARG");
        assemblyCommands.add("D=D+M");
        assemblyCommands.add("@R13");
        assemblyCommands.add("M=D");
        assemblyCommands.addAll(POP);
        assemblyCommands.add("@R13");
        assemblyCommands.add("A=M");
        assemblyCommands.add("M=D");

        return assemblyCommands;
    };

    public static final Function<String, List<String>> PUSH_ARGUMENT = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add("@" + s.split(CMD_SEPARATOR)[2]);
        assemblyCommands.add("D=A");
        assemblyCommands.add("@ARG");
        assemblyCommands.add("A=D+M");
        assemblyCommands.add("D=M");
        assemblyCommands.addAll(PUSH);

        return assemblyCommands;
    };

    public static final Function<String, List<String>> POP_THIS = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add("@" + s.split(CMD_SEPARATOR)[2]);
        assemblyCommands.add("D=A");
        assemblyCommands.add("@THIS");
        assemblyCommands.add("D=D+M");
        assemblyCommands.add("@R13");
        assemblyCommands.add("M=D");
        assemblyCommands.addAll(POP);
        assemblyCommands.add("@R13");
        assemblyCommands.add("A=M");
        assemblyCommands.add("M=D");

        return assemblyCommands;
    };

    public static final Function<String, List<String>> PUSH_THIS = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add("@" + s.split(CMD_SEPARATOR)[2]);
        assemblyCommands.add("D=A");
        assemblyCommands.add("@THIS");
        assemblyCommands.add("A=D+M");
        assemblyCommands.add("D=M");
        assemblyCommands.addAll(PUSH);

        return assemblyCommands;
    };

    public static final Function<String, List<String>> POP_THAT = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add("@" + s.split(CMD_SEPARATOR)[2]);
        assemblyCommands.add("D=A");
        assemblyCommands.add("@THAT");
        assemblyCommands.add("D=D+M");
        assemblyCommands.add("@R13");
        assemblyCommands.add("M=D");
        assemblyCommands.addAll(POP);
        assemblyCommands.add("@R13");
        assemblyCommands.add("A=M");
        assemblyCommands.add("M=D");

        return assemblyCommands;
    };

    public static final Function<String, List<String>> PUSH_THAT = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add("@" + s.split(CMD_SEPARATOR)[2]);
        assemblyCommands.add("D=A");
        assemblyCommands.add("@THAT");
        assemblyCommands.add("A=D+M");
        assemblyCommands.add("D=M");
        assemblyCommands.addAll(PUSH);

        return assemblyCommands;
    };

    public static final Function<String, List<String>> POP_POINTER = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
        final int thisOrThat = Integer.valueOf(s.split(CMD_SEPARATOR)[2]);
        assemblyCommands.addAll(POP);
        assemblyCommands.add(thisOrThat == 0 ? "@THIS" : "@THAT");
        assemblyCommands.add("M=D");

        return assemblyCommands;
    };

    public static final Function<String, List<String>> PUSH_POINTER = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
        final int thisOrThat = Integer.valueOf(s.split(CMD_SEPARATOR)[2]);
        assemblyCommands.add(thisOrThat == 0 ? "@THIS" : "@THAT");
        assemblyCommands.add("D=M");
        assemblyCommands.addAll(PUSH);

        return assemblyCommands;
    };


    public static final Function<String, List<String>> POP_STATIC = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.addAll(POP);
        assemblyCommands.add(String.format("@%s.%s", s.split(CMD_SEPARATOR)[3], s.split(CMD_SEPARATOR)[2]));
        assemblyCommands.add("M=D");

        return assemblyCommands;
    };

    public static final Function<String, List<String>> PUSH_STATIC = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add(String.format("@%s.%s", s.split(CMD_SEPARATOR)[3], s.split(CMD_SEPARATOR)[2]));
        assemblyCommands.add("D=M");
        assemblyCommands.addAll(PUSH);

        return assemblyCommands;
    };

    public static final Function<String, List<String>> POP_TEMP = (s) -> {
        final int addr = TEMP_BASE_ADDR + Integer.valueOf(s.split(CMD_SEPARATOR)[2]);
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.addAll(POP);
        assemblyCommands.add("@" + addr);
        assemblyCommands.add("M=D");

        return assemblyCommands;
    };

    public static final Function<String, List<String>> PUSH_TEMP = (s) -> {
        final int addr = TEMP_BASE_ADDR + Integer.valueOf(s.split(CMD_SEPARATOR)[2]);
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add("@" + addr);
        assemblyCommands.add("D=M");
        assemblyCommands.addAll(PUSH);

        return assemblyCommands;
    };

    public static final Function<String, List<String>> ADD = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.addAll(POP);
        assemblyCommands.add("A=A-1");
        assemblyCommands.add("M=M+D");

        return assemblyCommands;
    };

    public static final Function<String, List<String>> SUB = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.addAll(POP);
        assemblyCommands.add("A=A-1");
        assemblyCommands.add("M=M-D");

        return assemblyCommands;
    };

    public static final Function<String, List<String>> NEG = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add("@SP");
        assemblyCommands.add("A=M-1");
        assemblyCommands.add("M=-M");

        return assemblyCommands;
    };

    public static final Function<String, List<String>> EQ = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add(String.format("@RETURN_TO_%d", ++returnLabelCount));
        assemblyCommands.add("D=A");
        assemblyCommands.add("@R14");
        assemblyCommands.add("M=D");
        assemblyCommands.add("@BEGIN_EQ");
        assemblyCommands.add("0;JMP");
        assemblyCommands.add(String.format("(RETURN_TO_%d)", returnLabelCount));

        return assemblyCommands;
    };

    public static final Function<String, List<String>> GT = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add(String.format("@RETURN_TO_%d", ++returnLabelCount));
        assemblyCommands.add("D=A");
        assemblyCommands.add("@R14");
        assemblyCommands.add("M=D");
        assemblyCommands.add("@BEGIN_GT");
        assemblyCommands.add("0;JMP");
        assemblyCommands.add(String.format("(RETURN_TO_%d)", returnLabelCount));

        return assemblyCommands;
    };

    public static final Function<String, List<String>> LT = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add(String.format("@RETURN_TO_%d", ++returnLabelCount));
        assemblyCommands.add("D=A");
        assemblyCommands.add("@R14");
        assemblyCommands.add("M=D");
        assemblyCommands.add("@BEGIN_LT");
        assemblyCommands.add("0;JMP");
        assemblyCommands.add(String.format("(RETURN_TO_%d)", returnLabelCount));

        return assemblyCommands;
    };

    public static final Function<String, List<String>> AND = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.addAll(POP);
        assemblyCommands.add("A=A-1");
        assemblyCommands.add("M=D&M");

        return assemblyCommands;
    };

    public static final Function<String, List<String>> OR = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.addAll(POP);
        assemblyCommands.add("A=A-1");
        assemblyCommands.add("M=D|M");

        return assemblyCommands;
    };

    public static final Function<String, List<String>> NOT = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add("@SP");
        assemblyCommands.add("A=M-1");
        assemblyCommands.add("M=!M");

        return assemblyCommands;
    };

    public static final Function<String, List<String>> GOTO = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add(String.format("@%s.%s$%s", s.split(CMD_SEPARATOR)[2], s.split(CMD_SEPARATOR)[3], s.split(CMD_SEPARATOR)[1]));
        assemblyCommands.add("0;JMP");

        return assemblyCommands;
    };

    public static final Function<String, List<String>> IF_GOTO = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.addAll(POP);
        assemblyCommands.add(String.format("@%s.%s$%s", s.split(CMD_SEPARATOR)[2], s.split(CMD_SEPARATOR)[3], s.split(CMD_SEPARATOR)[1]));
        assemblyCommands.add("D;JEQ");

        return assemblyCommands;
    };

    public static final Function<String, List<String>> LABEL = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add(String.format("(%s.%s$%s)", s.split(CMD_SEPARATOR)[2], s.split(CMD_SEPARATOR)[3], s.split(CMD_SEPARATOR)[1]));

        return assemblyCommands;
    };

}
