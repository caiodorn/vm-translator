package com.caiodorn.nand2tetris.vm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class VMTranslator {

    private static final String ASM_EXTENSION = ".asm";

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("Path to file or directory must be provided for parsing!");
        }

        try {
            final List<String> assemblyCode = new ArrayList<>();
            final Path path = Path.of(args[0]);
            String outputFileName;

            if (Files.isDirectory(path)) {
                AsmDefaults.initialize(assemblyCode, true);
                processDirectory(path, assemblyCode);
                outputFileName = Path.of(path.toString(), path.getFileName().toString() + ASM_EXTENSION).toString();
            } else {
                AsmDefaults.initialize(assemblyCode, false);
                processFile(path, assemblyCode);
                outputFileName = Path.of(path.getParent().toString(), getFilenameWithoutExtension(path) + ASM_EXTENSION).toString();
            }

            AsmDefaults.finalize(assemblyCode);
            Files.write(Paths.get(outputFileName), assemblyCode);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void processDirectory(Path path, List<String> assemblyCode) throws IOException {
        Files.newDirectoryStream(path)
                .forEach(fullyQualifiedFileName -> {
                    try {
                        if (fullyQualifiedFileName.toString().contains(".vm")) {
                            processFile(fullyQualifiedFileName, assemblyCode);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private static void processFile(Path path, List<String> assemblyCode) throws IOException {
        List<String> rawLines = Files.readAllLines(path);

        if (rawLines.isEmpty()) {
            throw new RuntimeException("File cannot be empty!");
        }

        assemblyCode.addAll(new VMCommandParser(getFilenameWithoutExtension(path)).toAssembly(rawLines));
    }

    private static String getFilenameWithoutExtension(Path path) {
        return path.getFileName().toString().split("\\.")[0];
    }

}
