package com.caiodorn.nand2tetris.vm;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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
            Files.write(Paths.get(createOutputFileName(fullyQualifiedInputFileName)), rawLines);
        } catch (IOException e) {
            log.error("An error occurred!", e);
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
