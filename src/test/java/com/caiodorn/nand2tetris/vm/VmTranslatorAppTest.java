package com.caiodorn.nand2tetris.vm;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class VmTranslatorAppTest {

    @Test
    public void shouldGenerateOutputFile() {
        VmTranslatorApp.main(new String[] {"src/test/resources/BasicTest.vm"});

        assertTrue(Files.exists(Paths.get("BasicTest.asm")));
    }

}
