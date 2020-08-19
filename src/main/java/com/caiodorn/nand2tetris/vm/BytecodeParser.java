package com.caiodorn.nand2tetris.vm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class BytecodeParser {

    public List<String> parse(List<String> vmCommands) {
        List<String> asmCommands = new ArrayList<>();
        inializePointers(asmCommands);
        removeInvalidChars(vmCommands).forEach(command -> asmCommands.addAll(toAssembly(command)));

        return asmCommands;
    }

    /**
     * Removes comments and empty lines.
     * @param rawLines
     * @return
     */
    private List<String> removeInvalidChars(List<String> rawLines) {
        List<String> cleanedUpLines = new ArrayList<>();
        String commentDelimiter = "//";

        List<String> nonEmptyLines = rawLines.stream()
                .filter(line -> !line.trim().isEmpty() && !line.trim().startsWith(commentDelimiter))
                .collect(Collectors.toList());

        nonEmptyLines.forEach(line -> cleanedUpLines.add(line.split(commentDelimiter)[0]));

        return cleanedUpLines;
    }

    private void inializePointers(List<String> asmCommands) {
        asmCommands.add("@256");
        asmCommands.add("D=A");
        asmCommands.add("@SP");
        asmCommands.add("M=D");
        asmCommands.add("@2048");
        asmCommands.add("D=A");
        asmCommands.add("@LCL");
        asmCommands.add("M=D");
    }

    private List<String> toAssembly(String vmCommand) {
        List<String> assemblyCommands = new ArrayList<>();
        assemblyCommands.addAll(ConverterDictionary.get(getCommandType(vmCommand)).apply(vmCommand));

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
