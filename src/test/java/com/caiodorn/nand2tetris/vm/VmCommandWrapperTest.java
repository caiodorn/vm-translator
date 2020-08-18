package com.caiodorn.nand2tetris.vm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VmCommandWrapperTest {

    @Test
    public void shouldGenerateExpectedAssemblyCode_whenStackCommand_pushConstant() {
        String vmCommand = ("push constant 8");

        List<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("@8");
        expectedOutput.add("D=A");
        expectedOutput.add("@SP");
        expectedOutput.add("A=M");
        expectedOutput.add("M=D");
        expectedOutput.add("@SP");
        expectedOutput.add("M=M+1");

        assertEquals(expectedOutput, VmCommandWrapper.of(vmCommand).asAssemblyCommands());
    }

    @Test
    public void shouldGenerateExpectedAssemblyCode_whenStackCommand_pushLocal() {
        String vmCommand = ("push local 8");

        List<String> expectedOutput = new ArrayList<>(); //RAM[13..15] -- general purpose
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

        assertEquals(expectedOutput, VmCommandWrapper.of(vmCommand).asAssemblyCommands());
    }

}
