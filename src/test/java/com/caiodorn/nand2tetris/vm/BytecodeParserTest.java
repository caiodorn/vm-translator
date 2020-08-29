package com.caiodorn.nand2tetris.vm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BytecodeParserTest {

    private final String filename = "filename";

    @Test
    void shouldThrowNullPointerException_whenNullArgument() {
        assertThrows(NullPointerException.class, () -> new BytecodeParser(filename).parse(null));
    }

}
