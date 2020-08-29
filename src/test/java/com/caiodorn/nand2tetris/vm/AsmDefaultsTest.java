package com.caiodorn.nand2tetris.vm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class AsmDefaultsTest {

    @Test
    void shouldAddDefaultInitTemplateCommands_whenInitialize() {
        List<String> expected = getInitializeTemplateCommands();
        List<String> actual = new ArrayList<>();

        AsmDefaults.initialize(actual);

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void shouldAddDefaultInitTemplateCommands_whenFinalize() {
        List<String> expected = getFinalizeTemplateCommands();
        List<String> actual = new ArrayList<>();

        AsmDefaults.finalize(actual);

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    private List<String> getInitializeTemplateCommands() {
        List<String> asmCommands = new ArrayList<>();
        //init pointers
        asmCommands.add("@256");
        asmCommands.add("D=A");
        asmCommands.add("@SP");
        asmCommands.add("M=D");
        asmCommands.add("@2048");
        asmCommands.add("D=A");
        asmCommands.add("@LCL");
        asmCommands.add("M=D");
        asmCommands.add("@5632");
        asmCommands.add("D=A");
        asmCommands.add("@ARG");
        asmCommands.add("M=D");
        asmCommands.add("@9216");
        asmCommands.add("D=A");
        asmCommands.add("@THIS");
        asmCommands.add("M=D");
        asmCommands.add("@12800");
        asmCommands.add("D=A");
        asmCommands.add("@THAT");
        asmCommands.add("M=D");
        asmCommands.add("@PROGRAM_START");
        asmCommands.add("0;JMP");

        //eq
        asmCommands.add("(BEGIN_EQ)");
        asmCommands.add("@SP");
        asmCommands.add("AM=M-1");
        asmCommands.add("D=M");
        asmCommands.add("A=A-1");
        asmCommands.add("D=M-D");
        asmCommands.add("M=-1");
        asmCommands.add("@END_EQ");
        asmCommands.add("D;JEQ");
        asmCommands.add("@SP");
        asmCommands.add("A=M-1");
        asmCommands.add("M=0");
        asmCommands.add("(END_EQ)");
        asmCommands.add("@R14");            // returns to origin - addr stored @R14
        asmCommands.add("A=M");
        asmCommands.add("0;JMP");

        //gt
        asmCommands.add("(BEGIN_GT)");
        asmCommands.add("@SP");
        asmCommands.add("AM=M-1");
        asmCommands.add("D=M");
        asmCommands.add("A=A-1");
        asmCommands.add("D=M-D");
        asmCommands.add("M=-1");
        asmCommands.add("@END_GT");
        asmCommands.add("D;JGT");
        asmCommands.add("@SP");
        asmCommands.add("A=M-1");
        asmCommands.add("M=0");
        asmCommands.add("(END_GT)");
        asmCommands.add("@R14");            // returns to origin - addr stored @R14
        asmCommands.add("A=M");
        asmCommands.add("0;JMP");

        //lt
        asmCommands.add("(BEGIN_LT)");
        asmCommands.add("@SP");
        asmCommands.add("AM=M-1");
        asmCommands.add("D=M");
        asmCommands.add("A=A-1");
        asmCommands.add("D=M-D");
        asmCommands.add("M=-1");
        asmCommands.add("@END_LT");
        asmCommands.add("D;JLT");
        asmCommands.add("@SP");
        asmCommands.add("A=M-1");
        asmCommands.add("M=0");
        asmCommands.add("(END_LT)");
        asmCommands.add("@R14");            // returns to origin - addr stored @R14
        asmCommands.add("A=M");
        asmCommands.add("0;JMP");

        asmCommands.add("(PROGRAM_START)"); // actual program starts after this line -- must be last!

        return asmCommands;
    }

    private List<String> getFinalizeTemplateCommands() {
        List<String> asmCommands = new ArrayList<>();
        asmCommands.add("(END)");
        asmCommands.add("@END");
        asmCommands.add("0;JMP");

        return asmCommands;
    }

}