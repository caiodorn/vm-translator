package com.caiodorn.nand2tetris.vm;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class VMTranslatorTest {

    @AfterEach
    public void cleanUp() throws IOException {
        Path path = (Path.of("src/test/resources"));

        Files.newDirectoryStream(path).forEach(fullyQualifiedFileName -> {
            try {
                if (fullyQualifiedFileName.toString().contains(".asm")) {
                    Files.delete(Path.of(fullyQualifiedFileName.toString()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    void shouldGenerateOutputFile_whenSingleFile() {
        VMTranslator.main(new String[] {"src/test/resources/StackTest.vm"});

        assertTrue(Files.exists(Paths.get("src/test/resources/StackTest.asm")));
    }

    @Test
    void shouldGenerateOutputFile_whenDirectory() {
        VMTranslator.main(new String[] {"src/test/resources/ProgramFlow/FibonacciSeries"});

        assertTrue(Files.exists(Paths.get("src/test/resources/ProgramFlow/FibonacciSeries/FibonacciSeries.asm")));
    }

}
