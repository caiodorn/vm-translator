package com.caiodorn.nand2tetris.vm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BytecodeParserTest {

    @Test
    public void shouldThrowNullPointerException_whenNullArgument() {
        assertThrows(NullPointerException.class, () -> new BytecodeParser().parse(null));
    }

    @Test
    public void shouldInitializePointers() {
        List<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("@256");
        expectedOutput.add("D=A");
        expectedOutput.add("@SP");
        expectedOutput.add("M=D");
        expectedOutput.add("@2048");
        expectedOutput.add("D=A");
        expectedOutput.add("@LCL");
        expectedOutput.add("M=D");

        assertEquals(expectedOutput, new BytecodeParser().parse(new ArrayList<>()));
    }

    @Test
    public void shouldGenerateExpectedAssemblyCode_whenPushConstant() {
        List<String> vmCommand = new ArrayList<>();
        vmCommand.add("push constant 8");

        List<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("@256");
        expectedOutput.add("D=A");
        expectedOutput.add("@SP");
        expectedOutput.add("M=D");
        expectedOutput.add("@2048");
        expectedOutput.add("D=A");
        expectedOutput.add("@LCL");
        expectedOutput.add("M=D");

        expectedOutput.add("@8");
        expectedOutput.add("D=A");
        expectedOutput.add("@SP");
        expectedOutput.add("AM=M+1");
        expectedOutput.add("M-1=D");

        assertEquals(expectedOutput, new BytecodeParser().parse(vmCommand));
    }

    @Test
    public void shouldGenerateExpectedAssemblyCode_whenPushLocal() {
        List<String> vmCommand = new ArrayList<>();
        vmCommand.add("push local 8");

        List<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("@256");
        expectedOutput.add("D=A");
        expectedOutput.add("@SP");
        expectedOutput.add("M=D");
        expectedOutput.add("@2048");
        expectedOutput.add("D=A");
        expectedOutput.add("@LCL");
        expectedOutput.add("M=D");

        expectedOutput.add("@8"); // assign *lcl[8] to D register
        expectedOutput.add("D=A");
        expectedOutput.add("@LCL");
        expectedOutput.add("A=D+M");
        expectedOutput.add("D=M");

        expectedOutput.add("@LCL"); // decrement lcl
        expectedOutput.add("M=M-1");

        expectedOutput.add("@SP"); // increment sp
        expectedOutput.add("AM=M+1");
        expectedOutput.add("M-1=D");

        assertEquals(expectedOutput, new BytecodeParser().parse(vmCommand));
    }

    @Test
    public void shouldGenerateExpectedAssemblyCode_whenPopLocal() {
        List<String> vmCommand = new ArrayList<>();
        vmCommand.add("pop local 8");

        List<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("@256");
        expectedOutput.add("D=A");
        expectedOutput.add("@SP");
        expectedOutput.add("M=D");
        expectedOutput.add("@2048");
        expectedOutput.add("D=A");
        expectedOutput.add("@LCL");
        expectedOutput.add("M=D");

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

        assertEquals(expectedOutput, new BytecodeParser().parse(vmCommand));
    }

}
