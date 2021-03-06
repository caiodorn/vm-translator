package com.caiodorn.nand2tetris.vm;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public final class Converters {

    private static final String CMD_SEPARATOR = " ";
    private static final int TEMP_BASE_ADDR = 5;
    private static int returnLabelCount = 0;

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
        final int thisOrThat = Integer.parseInt(s.split(CMD_SEPARATOR)[2]);
        final List<String> assemblyCommands = new ArrayList<>(POP);
        assemblyCommands.add(thisOrThat == 0 ? "@THIS" : "@THAT");
        assemblyCommands.add("M=D");

        return assemblyCommands;
    };

    public static final Function<String, List<String>> PUSH_POINTER = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
        final int thisOrThat = Integer.parseInt(s.split(CMD_SEPARATOR)[2]);
        assemblyCommands.add(thisOrThat == 0 ? "@THIS" : "@THAT");
        assemblyCommands.add("D=M");
        assemblyCommands.addAll(PUSH);

        return assemblyCommands;
    };


    public static final Function<String, List<String>> POP_STATIC = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>(POP);
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
        final int addr = TEMP_BASE_ADDR + Integer.parseInt(s.split(CMD_SEPARATOR)[2]);
        final List<String> assemblyCommands = new ArrayList<>(POP);
        assemblyCommands.add("@" + addr);
        assemblyCommands.add("M=D");

        return assemblyCommands;
    };

    public static final Function<String, List<String>> PUSH_TEMP = (s) -> {
        final int addr = TEMP_BASE_ADDR + Integer.parseInt(s.split(CMD_SEPARATOR)[2]);
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add("@" + addr);
        assemblyCommands.add("D=M");
        assemblyCommands.addAll(PUSH);

        return assemblyCommands;
    };

    public static final Function<String, List<String>> ADD = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>(POP);
        assemblyCommands.add("A=A-1");
        assemblyCommands.add("M=M+D");

        return assemblyCommands;
    };

    public static final Function<String, List<String>> SUB = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>(POP);
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
        final List<String> assemblyCommands = new ArrayList<>(POP);
        assemblyCommands.add("A=A-1");
        assemblyCommands.add("M=D&M");

        return assemblyCommands;
    };

    public static final Function<String, List<String>> OR = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>(POP);
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
        assemblyCommands.add(String.format("@%s$%s", s.split(CMD_SEPARATOR)[3], s.split(CMD_SEPARATOR)[1]));
        assemblyCommands.add("0;JMP");

        return assemblyCommands;
    };

    public static final Function<String, List<String>> IF_GOTO = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>(POP);
        assemblyCommands.add(String.format("@%s$%s", s.split(CMD_SEPARATOR)[3], s.split(CMD_SEPARATOR)[1]));
        assemblyCommands.add("D;JNE");

        return assemblyCommands;
    };

    public static final Function<String, List<String>> LABEL = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add(String.format("(%s$%s)", s.split(CMD_SEPARATOR)[3], s.split(CMD_SEPARATOR)[1]));

        return assemblyCommands;
    };

    public static final Function<String, List<String>> FUNCTION = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add(String.format("(%s)", s.split(CMD_SEPARATOR)[1]));

        int argCount = Integer.parseInt(s.split(CMD_SEPARATOR)[2]);

        for (int i = 0; i < argCount; i++) {
            assemblyCommands.add("@0");
            assemblyCommands.add("D=A");
            assemblyCommands.addAll(PUSH);
        }

        return assemblyCommands;
    };

    public static final Function<String, List<String>> CALL = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add(String.format("@RETURN_TO_%d",  ++returnLabelCount));
        assemblyCommands.add("D=A");
        assemblyCommands.add("@SP");
        assemblyCommands.add("AM=M+1");
        assemblyCommands.add("A=A-1");
        assemblyCommands.add("M=D");

        assemblyCommands.add("@LCL");
        assemblyCommands.add("D=M");
        assemblyCommands.add("@SP");
        assemblyCommands.add("AM=M+1");
        assemblyCommands.add("A=A-1");
        assemblyCommands.add("M=D");

        assemblyCommands.add("@ARG");
        assemblyCommands.add("D=M");
        assemblyCommands.add("@SP");
        assemblyCommands.add("AM=M+1");
        assemblyCommands.add("A=A-1");
        assemblyCommands.add("M=D");

        assemblyCommands.add("@THIS");
        assemblyCommands.add("D=M");
        assemblyCommands.add("@SP");
        assemblyCommands.add("AM=M+1");
        assemblyCommands.add("A=A-1");
        assemblyCommands.add("M=D");

        assemblyCommands.add("@THAT");
        assemblyCommands.add("D=M");
        assemblyCommands.add("@SP");
        assemblyCommands.add("AM=M+1");
        assemblyCommands.add("A=A-1");
        assemblyCommands.add("M=D");

        assemblyCommands.add(String.format("@%s", s.split(CMD_SEPARATOR)[2]));
        assemblyCommands.add("D=A");
        assemblyCommands.add("@5");
        assemblyCommands.add("D=D+A");
        assemblyCommands.add("@SP");
        assemblyCommands.add("D=M-D");
        assemblyCommands.add("@ARG");
        assemblyCommands.add("M=D");

        assemblyCommands.add("@SP");
        assemblyCommands.add("D=M");
        assemblyCommands.add("@LCL");
        assemblyCommands.add("M=D");

        assemblyCommands.add(String.format("@%s", s.split(CMD_SEPARATOR)[1]));
        assemblyCommands.add("0;JMP");

        assemblyCommands.add(String.format("(RETURN_TO_%d)", returnLabelCount));

        return assemblyCommands;
    };

    public static final Function<String, List<String>> RETURN = (s) -> {
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.add("@LCL");  // RET = *(LCL-5)
        assemblyCommands.add("D=M");
        assemblyCommands.add("@5");
        assemblyCommands.add("A=D-A");
        assemblyCommands.add("D=M");
        assemblyCommands.add("@R13");  // save RET in temp location
        assemblyCommands.add("M=D");

        assemblyCommands.add("@SP");   // pop
        assemblyCommands.add("AM=M-1");
        assemblyCommands.add("D=M");

        assemblyCommands.add("@ARG");  // *ARG = pop()
        assemblyCommands.add("A=M");
        assemblyCommands.add("M=D");

        assemblyCommands.add("@ARG");  // SP = ARG+1
        assemblyCommands.add("D=M+1");
        assemblyCommands.add("@SP");
        assemblyCommands.add("M=D");

        assemblyCommands.add("@LCL");  // THAT = *(LCL-1)
        assemblyCommands.add("D=M");
        assemblyCommands.add("@1");
        assemblyCommands.add("A=D-A");
        assemblyCommands.add("D=M");
        assemblyCommands.add("@THAT");
        assemblyCommands.add("M=D");

        assemblyCommands.add("@LCL");  // THIS = *(LCL-2)
        assemblyCommands.add("D=M");
        assemblyCommands.add("@2");
        assemblyCommands.add("A=D-A");
        assemblyCommands.add("D=M");
        assemblyCommands.add("@THIS");
        assemblyCommands.add("M=D");

        assemblyCommands.add("@LCL");  // ARG = *(LCL-3)
        assemblyCommands.add("D=M");
        assemblyCommands.add("@3");
        assemblyCommands.add("A=D-A");
        assemblyCommands.add("D=M");
        assemblyCommands.add("@ARG");
        assemblyCommands.add("M=D");

        assemblyCommands.add("@LCL");  // LCL = *(LCL-4)
        assemblyCommands.add("D=M");
        assemblyCommands.add("@4");
        assemblyCommands.add("A=D-A");
        assemblyCommands.add("D=M");
        assemblyCommands.add("@LCL");
        assemblyCommands.add("M=D");

        assemblyCommands.add("@R13");  // goto RET
        assemblyCommands.add("A=M");
        assemblyCommands.add("0;JMP");

        return assemblyCommands;
    };

    public static int getReturnLabelCount() {
        return returnLabelCount;
    }

    public static void resetReturnLabelCount() {
        returnLabelCount = 0;
    }

}
