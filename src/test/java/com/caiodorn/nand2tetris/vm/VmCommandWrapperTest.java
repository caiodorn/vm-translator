package com.caiodorn.nand2tetris.vm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VmCommandWrapperTest {

    @Test
    public void shouldGenerateExpectedAssemblyCode_whenPushConstant() {
        String vmCommand = ("push constant 8");

        List<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("@8");
        expectedOutput.add("D=A");
        expectedOutput.add("@SP");
        expectedOutput.add("A=M");
        expectedOutput.add("M=D");
        expectedOutput.add("@SP");
        expectedOutput.add("M=M+1");

        assertEquals(expectedOutput, VmCommandWrapper.wrap(vmCommand).asAssemblyCommands());
    }

    @Test
    public void shouldGenerateExpectedAssemblyCode_whenPushLocal() {
        String vmCommand = ("push local 8");

        List<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("@8"); // assign *lcl[8] to D register
        expectedOutput.add("D=A");
        expectedOutput.add("@LCL");
        expectedOutput.add("A=D+M");
        expectedOutput.add("D=M");

        expectedOutput.add("@LCL"); // decrement lcl
        expectedOutput.add("M=M-1");

        expectedOutput.add("@SP"); // increment sp
        expectedOutput.add("M=M+1");
        expectedOutput.add("A=M-1");
        expectedOutput.add("M=D"); // set D to *sp-1

        assertEquals(expectedOutput, VmCommandWrapper.wrap(vmCommand).asAssemblyCommands());
    }

    @Test
    public void shouldGenerateExpectedAssemblyCode_whenPopLocal() {
        String vmCommand = ("pop local 8");

        List<String> expectedOutput = new ArrayList<>();
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

        assertEquals(expectedOutput, VmCommandWrapper.wrap(vmCommand).asAssemblyCommands());
    }

}
