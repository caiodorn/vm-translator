package com.caiodorn.nand2tetris.vm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class VMCommandParser {

    private static final String PART_SEPARATOR = " ";
    private final String FILENAME;
    private final Stack<String> currentFunction;
    private final Predicate<String> requiresSpecialHandling = vmCommand ->
            vmCommand.startsWith(VMCommandTypeEnum.FUNCTION.getType())
            || vmCommand.startsWith(VMCommandTypeEnum.CALL.getType());

    public VMCommandParser(String filename) {
        this.FILENAME = filename;
        this.currentFunction = new Stack<>();
        this.currentFunction.push("Root");
    }

    public List<String> toAssembly(List<String> vmCommands) {
        final List<String> asmCommands = new ArrayList<>();
        removeInvalidChars(vmCommands).forEach(
                command -> asmCommands.addAll(convertCommand(command))
        );

        return asmCommands;
    }

    private List<String> removeInvalidChars(List<String> rawLines) {
        final List<String> cleanedUpLines = new ArrayList<>();
        String commentSeparator = "//";

        List<String> nonEmptyLines = rawLines.stream()
                .filter(line -> !line.trim().isEmpty() && !line.trim().startsWith(commentSeparator))
                .collect(Collectors.toList());

        nonEmptyLines.forEach(line -> cleanedUpLines.add(line.split(commentSeparator)[0].trim()));

        return cleanedUpLines;
    }

    private List<String> convertCommand(String vmCommand) {
        updateFunctionStack(vmCommand);

        return ConverterDictionary
                .get(getCommandType(vmCommand))
                .apply(String.format("%s %s %s", vmCommand, FILENAME, currentFunction.peek()));
    }

    private void updateFunctionStack(String vmCommand) {
        if (vmCommand.startsWith(VMCommandTypeEnum.FUNCTION.getType())) {
            currentFunction.push(vmCommand.split(PART_SEPARATOR)[1]);
        }
    }

    private VMCommandTypeEnum getCommandType(String vmCommand) {
        VMCommandTypeEnum type;

        if (requiresSpecialHandling.test(vmCommand)) {
            type = VMCommandTypeEnum.of(vmCommand.split(PART_SEPARATOR)[0]);
        } else {
            type = VMCommandTypeEnum.of(removeValueIfAny(vmCommand));
        }

        return type;
    }

    private String removeValueIfAny(String vmCommand) {
        int valueSeparatorIndex = vmCommand.lastIndexOf(PART_SEPARATOR);

        return valueSeparatorIndex == -1 ?
                vmCommand : vmCommand.substring(0, valueSeparatorIndex);
    }

}
