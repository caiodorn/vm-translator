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

        assertEquals(expectedOutput, VmCommandWrapper.of(vmCommand).asAssemblyCode());
    }

}
