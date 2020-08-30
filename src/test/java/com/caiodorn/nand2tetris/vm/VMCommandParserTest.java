package com.caiodorn.nand2tetris.vm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class VMCommandParserTest {

    private final String filename = "filename";

    @Test
    void shouldThrowNullPointerException_whenNullArgument() {
        assertThrows(NullPointerException.class, () -> new VMCommandParser(filename).toAssembly(null));
    }

}
