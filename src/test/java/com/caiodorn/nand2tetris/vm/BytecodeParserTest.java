package com.caiodorn.nand2tetris.vm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BytecodeParserTest {

    @Test
    public void shouldThrowNullPointerException_whenNullArgument() {
        assertThrows(NullPointerException.class, () -> new BytecodeParser().parse(null));
    }

    @Test
    public void shouldGenerateExpectedAssemblyCode_whenPushConstant() {
        List<String> vmCommand = Arrays.asList("push constant 8");
        List<String> expectedOutput = initialize();

        expectedOutput.add("@8");
        expectedOutput.add("D=A");
        expectedOutput.add("@SP");
        expectedOutput.add("AM=M+1");
        expectedOutput.add("A=A-1");
        expectedOutput.add("M=D");

        finalize(expectedOutput);

        assertEquals(expectedOutput, new BytecodeParser().parse(vmCommand));
    }

    @Test
    public void shouldGenerateExpectedAssemblyCode_whenPushLocal() {
        List<String> vmCommand = Arrays.asList("push local 8");
        List<String> expectedOutput = initialize();

        expectedOutput.add("@8"); // assign *lcl[8] to D register
        expectedOutput.add("D=A");
        expectedOutput.add("@LCL");
        expectedOutput.add("A=D+M");
        expectedOutput.add("D=M");

        expectedOutput.add("@LCL"); // decrement lcl
        expectedOutput.add("M=M-1");

        expectedOutput.add("@SP"); // increment sp
        expectedOutput.add("AM=M+1");
        expectedOutput.add("A=A-1");
        expectedOutput.add("M=D");

        finalize(expectedOutput);

        assertEquals(expectedOutput, new BytecodeParser().parse(vmCommand));
    }

    @Test
    public void shouldGenerateExpectedAssemblyCode_whenPopLocal() {
        List<String> vmCommand = Arrays.asList("pop local 8");
        List<String> expectedOutput = initialize();

        expectedOutput.add("@8");
        expectedOutput.add("D=A");
        expectedOutput.add("@LCL");
        expectedOutput.add("D=D+M"); // calculate LCL+8
        expectedOutput.add("@R13");
        expectedOutput.add("M=D"); //buffer target address

        expectedOutput.add("@SP"); // pop value from stack and decrement sp
        expectedOutput.add("AM=M-1");
        expectedOutput.add("D=M");

        expectedOutput.add("@R13");
        expectedOutput.add("A=M");
        expectedOutput.add("M=D"); // stores in RAM[LCL+8]

        finalize(expectedOutput);

        assertEquals(expectedOutput, new BytecodeParser().parse(vmCommand));
    }

    @Test
    public void shouldGenerateExpectedAssemblyCode_whenAdd() {
        List<String> vmCommand = Arrays.asList("add");

        List<String> expectedOutput = initialize();

        expectedOutput.add("@SP");
        expectedOutput.add("AM=M-1");
        expectedOutput.add("D=M");

        expectedOutput.add("A=M-1");
        expectedOutput.add("M=D+M");

        finalize(expectedOutput);

        assertEquals(expectedOutput, new BytecodeParser().parse(vmCommand));
    }

    @Test
    public void shouldGenerateExpectedAssemblyCode_whenSub() {
        List<String> vmCommand = Arrays.asList("sub");
        List<String> expectedOutput = initialize();

        expectedOutput.add("@SP");
        expectedOutput.add("AM=M-1");
        expectedOutput.add("D=M");

        expectedOutput.add("A=M-1");
        expectedOutput.add("M=D-M");

        finalize(expectedOutput);

        assertEquals(expectedOutput, new BytecodeParser().parse(vmCommand));
    }

    @Test
    public void shouldGenerateExpectedAssemblyCode_whenNeg() {
        List<String> vmCommand = Arrays.asList("neg");
        List<String> expectedOutput = initialize();

        expectedOutput.add("@SP");
        expectedOutput.add("A=M-1");
        expectedOutput.add("M=-M");

        finalize(expectedOutput);

        assertEquals(expectedOutput, new BytecodeParser().parse(vmCommand));
    }

    @Test
    public void shouldGenerateExpectedAssemblyCode_whenEq() {
        List<String> vmCommand = Arrays.asList("eq");
        List<String> expectedOutput = initialize();

        expectedOutput.add(String.format("@RETURN_TO_%d", ConverterDictionary.counter + 1));
        expectedOutput.add("D=A");
        expectedOutput.add("@R14");
        expectedOutput.add("M=D");
        expectedOutput.add("@BEGIN_EQ");
        expectedOutput.add("0;JMP");
        expectedOutput.add(String.format("(RETURN_TO_%d)", ConverterDictionary.counter + 1));

        finalize(expectedOutput);

        assertEquals(expectedOutput, new BytecodeParser().parse(vmCommand));
    }

    @Test
    public void shouldGenerateExpectedAssemblyCode_whenLt() {
        List<String> vmCommand = Arrays.asList("lt");
        List<String> expectedOutput = initialize();

        expectedOutput.add(String.format("@RETURN_TO_%d", ConverterDictionary.counter + 1));
        expectedOutput.add("D=A");
        expectedOutput.add("@R14");
        expectedOutput.add("M=D");
        expectedOutput.add("@BEGIN_LT");
        expectedOutput.add("0;JMP");
        expectedOutput.add(String.format("(RETURN_TO_%d)", ConverterDictionary.counter + 1));

        finalize(expectedOutput);

        assertEquals(expectedOutput, new BytecodeParser().parse(vmCommand));
    }

    @Test
    public void shouldGenerateExpectedAssemblyCode_whenGt() {
        List<String> vmCommand = Arrays.asList("gt");
        List<String> expectedOutput = initialize();

        expectedOutput.add(String.format("@RETURN_TO_%d", ConverterDictionary.counter + 1));
        expectedOutput.add("D=A");
        expectedOutput.add("@R14");
        expectedOutput.add("M=D");
        expectedOutput.add("@BEGIN_GT");
        expectedOutput.add("0;JMP");
        expectedOutput.add(String.format("(RETURN_TO_%d)", ConverterDictionary.counter + 1));

        finalize(expectedOutput);

        assertEquals(expectedOutput, new BytecodeParser().parse(vmCommand));
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
