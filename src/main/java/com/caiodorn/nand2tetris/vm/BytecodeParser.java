package com.caiodorn.nand2tetris.vm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class BytecodeParser {

    private final String FILENAME;

    public BytecodeParser(String filename) {
        this.FILENAME = filename;
    }

    public List<String> parse(List<String> vmCommands) {
        final List<String> asmCommands = new ArrayList<>();
        removeInvalidChars(vmCommands).forEach(command -> asmCommands.addAll(toAssembly(command.toLowerCase())));

        return asmCommands;
    }

    /**
     * Removes comments and empty lines.
     * @param rawLines
     * @return
     */
    private List<String> removeInvalidChars(List<String> rawLines) {
        final List<String> cleanedUpLines = new ArrayList<>();
        final String commentDelimiter = "//";

        final List<String> nonEmptyLines = rawLines.stream()
                .filter(line -> !line.trim().isEmpty() && !line.trim().startsWith(commentDelimiter))
                .collect(Collectors.toList());

        nonEmptyLines.forEach(line -> cleanedUpLines.add(line.split(commentDelimiter)[0]));

        return cleanedUpLines;
    }

    private List<String> toAssembly(String vmCommand) {
        final List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.addAll(ConverterDictionary.get(getCommandType(vmCommand)).apply(vmCommand + " " + FILENAME));

        return assemblyCommands;
    }

    private VmCommandTypeEnum getCommandType(String vmCommand) {
        return VmCommandTypeEnum.of(removeValueIfAny(vmCommand));
    }

    private String removeValueIfAny(String vmCommand) {
        int valueSeparatorIndex = vmCommand.lastIndexOf(" ");

        return valueSeparatorIndex == -1 ?
                vmCommand : vmCommand.substring(0, valueSeparatorIndex);
    }

}
