package com.caiodorn.nand2tetris.vm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;


public class VMCommandParser {

    private static final String PART_DELIMITER = " ";
    private final String FILENAME;
    private final Stack<String> currentFunction;

    public VMCommandParser(String filename) {
        this.FILENAME = filename;
        this.currentFunction = new Stack<>();
        this.currentFunction.push("main");
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
        updateFunctionStack(vmCommand);

        return new ArrayList<>(
                ConverterDictionary.get(getCommandType(vmCommand)).apply(String.format("%s %s %s", vmCommand, FILENAME, currentFunction.peek()))
        );
    }

    private void updateFunctionStack(String vmCommand) {
        if (VMCommandTypeEnum.FUNCTION.equals(getCommandType(vmCommand))) {
            currentFunction.push(vmCommand.split(PART_DELIMITER)[1]);
        } else if (VMCommandTypeEnum.RETURN.equals(getCommandType(vmCommand))) {
            currentFunction.pop();
        }
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
