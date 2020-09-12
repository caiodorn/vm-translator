package com.caiodorn.nand2tetris.vm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class AsmDefaultsTest {

    @Test
    void shouldAddDefaultInitDefaultCommands_whenInitialize_givenFile() {
        List<String> expected = getInitializeDefaultCommands(false);
        List<String> actual = new ArrayList<>();

        AsmDefaults.initialize(actual, false);

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void shouldAddDefaultInitDefaultCommands_whenInitialize_givenDirectory() {
        List<String> expected = getInitializeDefaultCommands(true);
        List<String> actual = new ArrayList<>();

        AsmDefaults.initialize(actual, true);

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void shouldAddDefaultInitDefaultCommands_whenFinalize() {
        List<String> expected = getFinalizeDefaultCommands();
        List<String> actual = new ArrayList<>();

        AsmDefaults.finalize(actual);

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    private List<String> getInitializeDefaultCommands(boolean isDirectory) {
        List<String> asmCommands = new ArrayList<>();
        //init pointers
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
        asmCommands.add("@256");            // init SP
        asmCommands.add("D=A");
        asmCommands.add("@SP");
        asmCommands.add("M=D");
        
        if (isDirectory) {
            asmCommands.add(String.format("@RETURN_TO_%d",  Converters.getReturnLabelCount() + 1));
            asmCommands.add("D=A");
            asmCommands.add("@SP");
            asmCommands.add("AM=M+1");
            asmCommands.add("A=A-1");
            asmCommands.add("M=D");

            asmCommands.add("@LCL");
            asmCommands.add("D=M");
            asmCommands.add("@SP");
            asmCommands.add("AM=M+1");
            asmCommands.add("A=A-1");
            asmCommands.add("M=D");

            asmCommands.add("@ARG");
            asmCommands.add("D=M");
            asmCommands.add("@SP");
            asmCommands.add("AM=M+1");
            asmCommands.add("A=A-1");
            asmCommands.add("M=D");

            asmCommands.add("@THIS");
            asmCommands.add("D=M");
            asmCommands.add("@SP");
            asmCommands.add("AM=M+1");
            asmCommands.add("A=A-1");
            asmCommands.add("M=D");

            asmCommands.add("@THAT");
            asmCommands.add("D=M");
            asmCommands.add("@SP");
            asmCommands.add("AM=M+1");
            asmCommands.add("A=A-1");
            asmCommands.add("M=D");

            asmCommands.add(String.format("@%s", "call Sys.init 0".split(" ")[2]));
            asmCommands.add("D=A");
            asmCommands.add("@5");
            asmCommands.add("D=D+A");
            asmCommands.add("@SP");
            asmCommands.add("D=M-D");
            asmCommands.add("@ARG");
            asmCommands.add("M=D");

            asmCommands.add("@SP");
            asmCommands.add("D=M");
            asmCommands.add("@LCL");
            asmCommands.add("M=D");

            asmCommands.add(String.format("@%s", "call Sys.init 0".split(" ")[1]));
            asmCommands.add("0;JMP");

            asmCommands.add(String.format("(RETURN_TO_%d)", Converters.getReturnLabelCount() + 1));
        }

        return asmCommands;
    }

    private List<String> getFinalizeDefaultCommands() {
        List<String> asmCommands = new ArrayList<>();
        asmCommands.add("(END)");
        asmCommands.add("@END");
        asmCommands.add("0;JMP");

        return asmCommands;
    }

}
