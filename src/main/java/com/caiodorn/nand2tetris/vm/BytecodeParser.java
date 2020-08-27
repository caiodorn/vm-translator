package com.caiodorn.nand2tetris.vm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class BytecodeParser {

    public List<String> parse(List<String> vmCommands) {
        List<String> asmCommands = new ArrayList<>();
        initialize(asmCommands);
        removeInvalidChars(vmCommands).forEach(command -> asmCommands.addAll(toAssembly(command.toLowerCase())));
        finalize(asmCommands);

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

    private void initialize(List<String> asmCommands) {
        //init pointers
        asmCommands.add("@256");
        asmCommands.add("D=A");
        asmCommands.add("@SP");
        asmCommands.add("M=D");
        asmCommands.add("@2048");
        asmCommands.add("D=A");
        asmCommands.add("@LCL");
        asmCommands.add("M=D");
        asmCommands.add("@PROGRAM_START");
        asmCommands.add("0;JMP");

        //eq
        asmCommands.add("(BEGIN_EQ)");
        asmCommands.add("@SP");
        asmCommands.add("AM=M-1");
        asmCommands.add("D=M");
        asmCommands.add("A=A-1");
        asmCommands.add("D=M-D");
        asmCommands.add("M=-1");
        asmCommands.add("@END_EQ");
        asmCommands.add("D;JEQ");
        asmCommands.add("@SP");
        asmCommands.add("A=M-1");
        asmCommands.add("M=0");
        asmCommands.add("(END_EQ)");
        asmCommands.add("@R14");            // returns to origin - addr stored @R14
        asmCommands.add("A=M");
        asmCommands.add("0;JMP");

        //gt
        asmCommands.add("(BEGIN_GT)");
        asmCommands.add("@SP");
        asmCommands.add("AM=M-1");
        asmCommands.add("D=M");
        asmCommands.add("A=A-1");
        asmCommands.add("D=M-D");
        asmCommands.add("M=-1");
        asmCommands.add("@END_GT");
        asmCommands.add("D;JGT");
        asmCommands.add("@SP");
        asmCommands.add("A=M-1");
        asmCommands.add("M=0");
        asmCommands.add("(END_GT)");
        asmCommands.add("@R14");            // returns to origin - addr stored @R14
        asmCommands.add("A=M");
        asmCommands.add("0;JMP");

        //lt
        asmCommands.add("(BEGIN_LT)");
        asmCommands.add("@SP");
        asmCommands.add("AM=M-1");
        asmCommands.add("D=M");
        asmCommands.add("A=A-1");
        asmCommands.add("D=M-D");
        asmCommands.add("M=-1");
        asmCommands.add("@END_LT");
        asmCommands.add("D;JLT");
        asmCommands.add("@SP");
        asmCommands.add("A=M-1");
        asmCommands.add("M=0");
        asmCommands.add("(END_LT)");
        asmCommands.add("@R14");            // returns to origin - addr stored @R14
        asmCommands.add("A=M");
        asmCommands.add("0;JMP");

        asmCommands.add("(PROGRAM_START)"); // actual program starts after this line -- must be last!
    }

    private void finalize(List<String> asmCommands) {
        asmCommands.add("(END)");
        asmCommands.add("@END");
        asmCommands.add("0;JMP");
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
