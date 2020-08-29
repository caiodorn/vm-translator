package com.caiodorn.nand2tetris.vm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class VMTranslator {

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("A file must be provided for parsing!");
        }

        //TODO might have to translate multiple files
        final String fullyQualifiedInputFileName = args[0];

        try {
            List<String> rawLines = Files.readAllLines(Paths.get(fullyQualifiedInputFileName));

            if (rawLines.isEmpty()) {
                throw new RuntimeException("File cannot be empty!");
            }

            String currentFilename = createOutputFileName(fullyQualifiedInputFileName);
            List<String> assemblyCode = new BytecodeParser(currentFilename).parse(rawLines);
            Files.write(Paths.get(currentFilename), assemblyCode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String createOutputFileName(String fullyQualifiedFileName) {
        int fileNamePosition = fullyQualifiedFileName.split("/").length - 1;
        String fileName = fullyQualifiedFileName.split("/")[fileNamePosition];
        String outputFileName = fileName.split("\\.")[0].concat(".asm");

        return outputFileName;
    }

}
