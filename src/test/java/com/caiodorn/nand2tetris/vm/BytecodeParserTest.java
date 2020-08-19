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
        List<String> output = new ArrayList<>();
        output.add("@256");
        output.add("D=A");
        output.add("@SP");
        output.add("M=D");
        output.add("@2048");
        output.add("D=A");
        output.add("@LCL");
        output.add("M=D");

        assertEquals(output, new BytecodeParser().parse(new ArrayList<>()));
    }

}
