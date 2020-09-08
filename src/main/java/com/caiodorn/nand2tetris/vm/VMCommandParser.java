package com.caiodorn.nand2tetris.vm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class VMCommandParser {

    private static final String PART_DELIMITER = " ";
    private final String FILENAME;

    public VMCommandParser(String filename) {
        this.FILENAME = filename;
    }

    public List<String> toAssembly(List<String> vmCommands) {
        final List<String> asmCommands = new ArrayList<>();
        removeInvalidChars(vmCommands).forEach(
                command -> asmCommands.addAll(convertCommand(command.toLowerCase()))
        );

        return asmCommands;
    }

    private List<String> removeInvalidChars(List<String> rawLines) {
        final List<String> cleanedUpLines = new ArrayList<>();
        String commentDelimiter = "//";

        List<String> nonEmptyLines = rawLines.stream()
                .filter(line -> !line.trim().isEmpty() && !line.trim().startsWith(commentDelimiter))
                .collect(Collectors.toList());

        nonEmptyLines.forEach(line -> cleanedUpLines.add(line.split(commentDelimiter)[0]));

        return cleanedUpLines;
    }

    private List<String> convertCommand(String vmCommand) {
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.addAll(
                ConverterDictionary.get(getCommandType(vmCommand)).apply(vmCommand + PART_DELIMITER + FILENAME)
        );

        return assemblyCommands;
    }

    private VMCommandTypeEnum getCommandType(String vmCommand) {
        return VMCommandTypeEnum.of(removeValueIfAny(vmCommand));
    }

    private String removeValueIfAny(String vmCommand) {
        int valueSeparatorIndex = vmCommand.lastIndexOf(PART_DELIMITER);

        return valueSeparatorIndex == -1 ?
                vmCommand : vmCommand.substring(0, valueSeparatorIndex);
    }

}
