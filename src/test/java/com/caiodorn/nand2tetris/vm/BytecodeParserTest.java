package com.caiodorn.nand2tetris.vm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BytecodeParserTest {

    private final String filename = "filename";

    @Test
    void shouldThrowNullPointerException_whenNullArgument() {
        assertThrows(NullPointerException.class, () -> new BytecodeParser(filename).parse(null));
    }

    private List<String> initialize() {
        List<String> boilerPlateCommands = new ArrayList<>();
        boilerPlateCommands.add("@256");
        boilerPlateCommands.add("D=A");
        boilerPlateCommands.add("@SP");
        boilerPlateCommands.add("M=D");
        boilerPlateCommands.add("@2048");
        boilerPlateCommands.add("D=A");
        boilerPlateCommands.add("@LCL");
        boilerPlateCommands.add("M=D");
        boilerPlateCommands.add("@PROGRAM_START");
        boilerPlateCommands.add("0;JMP");

        //eq
        boilerPlateCommands.add("(BEGIN_EQ)");
        boilerPlateCommands.add("@SP");
        boilerPlateCommands.add("AM=M-1");
        boilerPlateCommands.add("D=M");
        boilerPlateCommands.add("A=A-1");
        boilerPlateCommands.add("D=D-M");
        boilerPlateCommands.add("M=0");
        boilerPlateCommands.add("@END_EQ");
        boilerPlateCommands.add("D;JEQ");
        boilerPlateCommands.add("@SP");
        boilerPlateCommands.add("A=M-1");
        boilerPlateCommands.add("A=A-1");
        boilerPlateCommands.add("M=-1");
        boilerPlateCommands.add("(END_EQ)");
        boilerPlateCommands.add("@R14");            // returns to origin - addr stored @R14
        boilerPlateCommands.add("A=M");
        boilerPlateCommands.add("0;JMP");

        //gt
        boilerPlateCommands.add("(BEGIN_GT)");
        boilerPlateCommands.add("@SP");
        boilerPlateCommands.add("AM=M-1");
        boilerPlateCommands.add("D=M");
        boilerPlateCommands.add("A=A-1");
        boilerPlateCommands.add("D=D-M");
        boilerPlateCommands.add("M=0");
        boilerPlateCommands.add("@END_GT");
        boilerPlateCommands.add("D;JGT");
        boilerPlateCommands.add("@SP");
        boilerPlateCommands.add("A=M-1");
        boilerPlateCommands.add("A=A-1");
        boilerPlateCommands.add("M=-1");
        boilerPlateCommands.add("(END_GT)");
        boilerPlateCommands.add("@R14");            // returns to origin - addr stored @R14
        boilerPlateCommands.add("A=M");
        boilerPlateCommands.add("0;JMP");

        //lt
        boilerPlateCommands.add("(BEGIN_LT)");
        boilerPlateCommands.add("@SP");
        boilerPlateCommands.add("AM=M-1");
        boilerPlateCommands.add("D=M");
        boilerPlateCommands.add("A=A-1");
        boilerPlateCommands.add("D=D-M");
        boilerPlateCommands.add("M=0");
        boilerPlateCommands.add("@END_LT");
        boilerPlateCommands.add("D;JLT");
        boilerPlateCommands.add("@SP");
        boilerPlateCommands.add("A=M-1");
        boilerPlateCommands.add("A=A-1");
        boilerPlateCommands.add("M=-1");
        boilerPlateCommands.add("(END_LT)");
        boilerPlateCommands.add("@R14");            // returns to origin - addr stored @R14
        boilerPlateCommands.add("A=M");
        boilerPlateCommands.add("0;JMP");

        boilerPlateCommands.add("(PROGRAM_START)"); // actual program starts after this line -- must be last!

        return boilerPlateCommands;
    }

    private void finalize(List<String> asmCommands) {
        asmCommands.add("(END)");
        asmCommands.add("@END");
        asmCommands.add("0;JMP");
    }

}
