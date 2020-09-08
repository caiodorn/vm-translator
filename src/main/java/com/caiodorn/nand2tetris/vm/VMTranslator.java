package com.caiodorn.nand2tetris.vm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class VMTranslator {

    private static final String DIR_DELIMITER = "/";
    private static final String FILE_EXT_DELIMITER = "\\.";
    private static final String ASM_EXTENSION = ".asm";

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("Path to file or directory must be provided for parsing!");
        }

        final String path = args[0];

        try {
            final List<String> assemblyCode = new ArrayList<>();
            String outputFileName;
            AsmDefaults.initialize(assemblyCode);

            if (Files.isDirectory(Path.of(path))) {
                processDirectory(path, assemblyCode);
                outputFileName = path + path.substring(path.lastIndexOf(DIR_DELIMITER)) + ASM_EXTENSION;
            } else {
                processFile(path, assemblyCode);
                outputFileName = path.split(FILE_EXT_DELIMITER)[0] + ASM_EXTENSION;
            }

            AsmDefaults.finalize(assemblyCode);
            Files.write(Paths.get(outputFileName), assemblyCode);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void processDirectory(String path, List<String> assemblyCode) throws IOException {
        Files.newDirectoryStream(Path.of(path)).forEach(fullyQualifiedFileName -> {
            try {
                processFile(fullyQualifiedFileName.toString(), assemblyCode);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void processFile(String fullyQualifiedFileName, List<String> assemblyCode) throws IOException {
        List<String> rawLines = Files.readAllLines(Paths.get(fullyQualifiedFileName));

        if (rawLines.isEmpty()) {
            throw new RuntimeException("File cannot be empty!");
        }

        assemblyCode.addAll(new VMCommandParser(getFileNameWithoutExtension(fullyQualifiedFileName)).toAssembly(rawLines));
    }

    private static String getFileNameWithoutExtension(String fullyQualifiedFileName) {
        return fullyQualifiedFileName
                .substring(fullyQualifiedFileName.lastIndexOf(DIR_DELIMITER) + 1)
                .split(FILE_EXT_DELIMITER)[0];
    }

}
