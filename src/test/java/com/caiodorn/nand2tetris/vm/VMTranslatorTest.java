package com.caiodorn.nand2tetris.vm;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class VMTranslatorTest {

    private static String currentPath;

    private static final String PROJECT7_MEMORY_ACCESS = "src/test/resources/project7/MemoryAccess";
    private static final String PROJECT7_STACK_ARITHMETIC = "src/test/resources/project7/StackArithmetic";
    private static final String PROJECT7_EXPECTATIONS_MEMORY_ACCESS = "src/test/resources/project7/expectations/MemoryAccess";
    private static final String PROJECT7_EXPECTATIONS_STACK_ARITHMETIC = "src/test/resources/project7/expectations/StackArithmetic";

    private static final String PROJECT8_PROGRAM_FLOW = "src/test/resources/project8/ProgramFlow";
    private static final String PROJECT8_FUNCTION_CALLS = "src/test/resources/project8/FunctionCalls";
    private static final String PROJECT8_EXPECTATIONS_PROGRAM_FLOW = "src/test/resources/project8/expectations/ProgramFlow";
    private static final String PROJECT8_EXPECTATIONS_FUNCTION_CALLS = "src/test/resources/project8/expectations/FunctionCalls";

    @AfterEach
    public void cleanUp() throws IOException {
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
        currentPath = PROJECT7_MEMORY_ACCESS + "/BasicTest";

        VMTranslator.main(new String[] {currentPath + "/BasicTest.vm"});

        compareFiles(Path.of(PROJECT7_EXPECTATIONS_MEMORY_ACCESS + "/BasicTest.asm"), Path.of(currentPath + "/BasicTest.asm"));
    }

    @Test
    void shouldGenerateOutputFile_whenSingleFile_PointerTest() {
        currentPath = PROJECT7_MEMORY_ACCESS + "/PointerTest";

        VMTranslator.main(new String[] {currentPath + "/PointerTest.vm"});

        compareFiles(Path.of(PROJECT7_EXPECTATIONS_MEMORY_ACCESS + "/PointerTest.asm"), Path.of(currentPath + "/PointerTest.asm"));
    }

    @Test
    void shouldGenerateOutputFile_whenSingleFile_StaticTest() {
        currentPath = PROJECT7_MEMORY_ACCESS + "/StaticTest";

        VMTranslator.main(new String[] {currentPath + "/StaticTest.vm"});

        compareFiles(Path.of(PROJECT7_EXPECTATIONS_MEMORY_ACCESS + "/StaticTest.asm"), Path.of(currentPath + "/StaticTest.asm"));
    }

    @Test
    void shouldGenerateOutputFile_whenSingleFile_SimpleAdd() {
        currentPath = PROJECT7_STACK_ARITHMETIC + "/SimpleAdd";

        VMTranslator.main(new String[] {currentPath + "/SimpleAdd.vm"});

        compareFiles(Path.of(PROJECT7_EXPECTATIONS_STACK_ARITHMETIC + "/SimpleAdd.asm"), Path.of(currentPath + "/SimpleAdd.asm"));
    }

    //TODO this test randomly fails (seems to be related with Gradle, not sure why it fails)
    @Test
    void shouldGenerateOutputFile_whenSingleFile_StackTest() {
        currentPath = PROJECT7_STACK_ARITHMETIC + "/StackTest";

        VMTranslator.main(new String[] {currentPath + "/StackTest.vm"});

        compareFiles(Path.of(PROJECT7_EXPECTATIONS_STACK_ARITHMETIC + "/StackTest.asm"), Path.of(currentPath + "/StackTest.asm"));
    }

    @Test
    void shouldGenerateOutputFile_whenSingleFile_BasicLoop() {
        currentPath = PROJECT8_PROGRAM_FLOW + "/BasicLoop";

        VMTranslator.main(new String[] {currentPath + "/BasicLoop.vm"});

        compareFiles(Path.of(PROJECT8_EXPECTATIONS_PROGRAM_FLOW + "/BasicLoop.asm"), Path.of(currentPath + "/BasicLoop.asm"));
    }

    @Test
    void shouldGenerateOutputFile_whenSingleFile_FibonacciSeries() {
        currentPath = PROJECT8_PROGRAM_FLOW + "/FibonacciSeries";

        VMTranslator.main(new String[] {currentPath + "/FibonacciSeries.vm"});

        compareFiles(Path.of(PROJECT8_EXPECTATIONS_PROGRAM_FLOW + "/FibonacciSeries.asm"), Path.of(currentPath + "/FibonacciSeries.asm"));
    }

    @Test
    void shouldGenerateOutputFile_whenSingleFile_SimpleFunction() {
        currentPath = PROJECT8_FUNCTION_CALLS + "/SimpleFunction";

        VMTranslator.main(new String[] {currentPath + "/SimpleFunction.vm"});

        compareFiles(Path.of(PROJECT8_EXPECTATIONS_FUNCTION_CALLS + "/SimpleFunction.asm"), Path.of(currentPath + "/SimpleFunction.asm"));
    }

//    @Test
//    void shouldGenerateOutputFile_whenDirectory_NestedCall() {
//        currentPath = PROJECT8_FUNCTION_CALLS + "/NestedCall";
//
//        VMTranslator.main(new String[] {currentPath});
//
//        compareFiles(Path.of(PROJECT8_EXPECTATIONS_FUNCTION_CALLS + "/NestedCall.asm"), Path.of(currentPath + "/NestedCall.asm"));
//    }
//
//    @Test
//    void shouldGenerateOutputFile_whenDirectory_FibonacciElement() {
//        currentPath = PROJECT8_FUNCTION_CALLS + "/FibonacciElement";
//
//        VMTranslator.main(new String[] {currentPath});
//
//        compareFiles(Path.of(PROJECT8_EXPECTATIONS_FUNCTION_CALLS + "/FibonacciElement.asm"), Path.of(currentPath + "/FibonacciElement.asm"));
//    }

    private void compareFiles(Path expected, Path actual) {
        try {
            assertArrayEquals(Files.readAllLines(actual).toArray(), Files.readAllLines(expected).toArray());
        } catch (IOException e) {
            fail(e);
        }
    }

}
