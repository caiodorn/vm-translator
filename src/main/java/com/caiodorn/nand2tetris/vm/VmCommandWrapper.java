package com.caiodorn.nand2tetris.vm;

import java.util.ArrayList;
import java.util.List;

import static com.caiodorn.nand2tetris.vm.Converters.getConverter;

public final class VmCommandWrapper {

    private final String vmCommand;
    private final List<String> assemblyCommands = new ArrayList<>();

    private VmCommandWrapper(String vmCommand) {
        this.vmCommand = vmCommand;
    }

    public List<String> asAssemblyCommands() {
        if (assemblyCommands.isEmpty()) {
            String valueSeparator = " ";
            assemblyCommands.addAll(getConverter(removeValueIfAny(vmCommand.lastIndexOf(valueSeparator))).apply(vmCommand));
        }

        return new ArrayList<>(assemblyCommands);
    }

    private String removeValueIfAny(int valueSeparatorIndex) {
        return valueSeparatorIndex == -1 ?
                vmCommand : vmCommand.substring(0, valueSeparatorIndex);
    }

    public static VmCommandWrapper of(String command) {
        return new VmCommandWrapper(command);
    }

}
