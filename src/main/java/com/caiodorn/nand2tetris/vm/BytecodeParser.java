package com.caiodorn.nand2tetris.vm;

import java.util.ArrayList;
import java.util.List;


public class BytecodeParser {

    public List<String> parse(List<String> vmCommands) {
        List<String> asmCommands = new ArrayList<>();

        vmCommands.forEach(command -> asmCommands.addAll(VmCommandWrapper.of(command).asAssemblyCode()));

        return asmCommands;
    }

}
