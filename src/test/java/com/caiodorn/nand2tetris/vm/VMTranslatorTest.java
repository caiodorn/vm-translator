package com.caiodorn.nand2tetris.vm;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class VMTranslatorTest {

    private static String currentPath;

    @AfterAll
    public static void cleanUp() throws IOException {
        Path path = (Path.of(currentPath));

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
    void shouldGenerateOutputFile_whenSingleFile_BasicTest() {
        currentPath = "src/test/resources/project7/vm";

        VMTranslator.main(new String[] {currentPath + "/BasicTest.vm"});

        compareFiles(Path.of("src/test/resources/project7/asm/BasicTest.asm"), Path.of(currentPath + "/BasicTest.asm"));
    }

    @Test
    void shouldGenerateOutputFile_whenSingleFile_PointerTest() {
        currentPath = "src/test/resources/project7/vm";

        VMTranslator.main(new String[] {currentPath + "/PointerTest.vm"});

        compareFiles(Path.of("src/test/resources/project7/asm/PointerTest.asm"), Path.of(currentPath + "/PointerTest.asm"));
    }

    @Test
    void shouldGenerateOutputFile_whenSingleFile_StaticTest() {
        currentPath = "src/test/resources/project7/vm";

        VMTranslator.main(new String[] {currentPath + "/StaticTest.vm"});

        compareFiles(Path.of("src/test/resources/project7/asm/StaticTest.asm"), Path.of(currentPath + "/StaticTest.asm"));
    }

    @Test
    void shouldGenerateOutputFile_whenSingleFile_SimpleAdd() {
        currentPath = "src/test/resources/project7/vm";

        VMTranslator.main(new String[] {currentPath + "/SimpleAdd.vm"});

        compareFiles(Path.of("src/test/resources/project7/asm/SimpleAdd.asm"), Path.of(currentPath + "/SimpleAdd.asm"));
    }

    @Test
    void shouldGenerateOutputFile_whenSingleFile_StackTest() {
        currentPath = "src/test/resources/project7/vm";

        VMTranslator.main(new String[] {currentPath + "/StackTest.vm"});

        compareFiles(Path.of("src/test/resources/project7/asm/StackTest.asm"), Path.of(currentPath + "/StackTest.asm"));
    }

    @Test
    void shouldGenerateOutputFile_whenDirectory() {
        currentPath = "src/test/resources/project7/vm";

        VMTranslator.main(new String[] {currentPath});

        assertTrue(Files.exists(Paths.get(currentPath + "/vm.asm")));
    }

    private void compareFiles(Path expected, Path actual) {
        try {
            assertArrayEquals(Files.readAllLines(actual).toArray(), Files.readAllLines(expected).toArray());
        } catch (IOException e) {
            fail(e);
        }
    }

}
