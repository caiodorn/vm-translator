package com.caiodorn.nand2tetris.vm;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class VMTranslatorTest {

    @Test
    void shouldGenerateOutputFile() {
        VMTranslator.main(new String[] {"src/test/resources/StackTest.vm"});

        assertTrue(Files.exists(Paths.get("src/test/resources/StackTest.asm")));
    }

}
