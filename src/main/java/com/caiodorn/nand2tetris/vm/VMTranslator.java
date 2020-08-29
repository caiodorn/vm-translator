package com.caiodorn.nand2tetris.vm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class VMTranslator {

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("A file must be provided for parsing!");
        }

        //TODO might have to translate multiple files
        final String fullyQualifiedInputFileName = args[0];

        try {
            final List<String> rawLines = Files.readAllLines(Paths.get(fullyQualifiedInputFileName));

            if (rawLines.isEmpty()) {
                throw new RuntimeException("File cannot be empty!");
            }

            final String currentFilename = extractFileName(fullyQualifiedInputFileName);
            final String fullyQualifiedOutputFileName = fullyQualifiedInputFileName.split("\\.")[0];

            final List<String> assemblyCode = new ArrayList<>();
            AsmDefaults.initialize(assemblyCode);
            assemblyCode.addAll(new BytecodeParser(currentFilename).parse(rawLines));
            AsmDefaults.finalize(assemblyCode);

            Files.write(Paths.get(fullyQualifiedOutputFileName + ".asm"), assemblyCode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String extractFileName(String fullyQualifiedFileName) {
        int fileNamePosition = fullyQualifiedFileName.split("/").length - 1;
        String fileName = fullyQualifiedFileName.split("/")[fileNamePosition];
        String outputFileName = fileName.split("\\.")[0];

        return outputFileName;
    }

}
