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
        final String fullyQualifiedFileName = args[0];
        final int fileNamePosition = fullyQualifiedFileName.split("/").length - 1;
        final String fileName = fullyQualifiedFileName.split("/")[fileNamePosition];
        final String outputFileName = fileName.split("\\.")[0].concat(".asm");

        try {
            List<String> rawLines = Files.readAllLines(Paths.get(fullyQualifiedFileName));
            Files.write(Paths.get(outputFileName), rawLines);
        } catch (IOException e) {
            log.error("An error occurred!", e);
            throw new RuntimeException(e);
        }
    }

}
