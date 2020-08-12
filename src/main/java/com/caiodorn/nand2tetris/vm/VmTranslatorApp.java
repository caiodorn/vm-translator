package com.caiodorn.nand2tetris.vm;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class VmTranslatorApp {

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("A file must be provided for parsing!");
        }

        //TODO might have to translate multiple files
        final String fullyQualifiedInputFileName = args[0];

        try {
            List<String> rawLines = Files.readAllLines(Paths.get(fullyQualifiedInputFileName));
            List<String> assemblyCode = new BytecodeParser().parse(removeInvalidChars(rawLines));
            Files.write(Paths.get(createOutputFileName(fullyQualifiedInputFileName)), assemblyCode);
        } catch (IOException e) {
            log.error("An error occurred!", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Removes comments and empty lines.
     * @param rawLines
     * @return
     */
    private static List<String> removeInvalidChars(List<String> rawLines) {
        List<String> cleanedUpLines = new ArrayList<>();
        String commentDelimiter = "//";

        List<String> nonEmptyLines = rawLines.stream()
                .filter(line -> !line.trim().isEmpty() && !line.trim().startsWith(commentDelimiter))
                .collect(Collectors.toList());

        nonEmptyLines.forEach(line -> cleanedUpLines.add(line.split(commentDelimiter)[0]));

        return cleanedUpLines;
    }

    private static String createOutputFileName(String fullyQualifiedFileName) {
        int fileNamePosition = fullyQualifiedFileName.split("/").length - 1;
        String fileName = fullyQualifiedFileName.split("/")[fileNamePosition];
        String outputFileName = fileName.split("\\.")[0].concat(".asm");

        return outputFileName;
    }

}
