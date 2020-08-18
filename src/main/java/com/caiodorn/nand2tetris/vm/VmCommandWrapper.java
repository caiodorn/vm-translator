package com.caiodorn.nand2tetris.vm;

import java.util.ArrayList;
import java.util.List;

public final class VmCommandWrapper {

    private final String vmCommand;
    private final List<String> assemblyCommands = new ArrayList<>();

    private VmCommandWrapper(String vmCommand) {
        this.vmCommand = vmCommand;
    }

    public static VmCommandWrapper wrap(String command) {
        return new VmCommandWrapper(command);
    }

    public List<String> asAssemblyCommands() {
        if (assemblyCommands.isEmpty()) {
            assemblyCommands.addAll(VmCommandConverters.getForType(getCommandType())
                    .apply(vmCommand)
            );
        }

        return new ArrayList<>(assemblyCommands);
    }

    private VmCommandTypeEnum getCommandType() {
        return VmCommandTypeEnum.of(removeValueIfAny(vmCommand.lastIndexOf(" ")));
    }

    private String removeValueIfAny(int valueSeparatorIndex) {
        return valueSeparatorIndex == -1 ?
                vmCommand : vmCommand.substring(0, valueSeparatorIndex);
    }

}
