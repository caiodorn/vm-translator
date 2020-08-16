package com.caiodorn.nand2tetris.vm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class BytecodeParser {

    public List<String> parse(List<String> vmCommands) {
        List<String> asmCommands = new ArrayList<>();

        removeInvalidChars(vmCommands).forEach(command -> asmCommands.addAll(VmCommandWrapper.of(command).asAssemblyCommands()));

        return asmCommands;
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

}
