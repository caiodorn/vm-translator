package com.caiodorn.nand2tetris.vm;

import java.util.List;

public final class VmCommandWrapper {

    private final String command;

    private VmCommandWrapper(String command) {
        this.command = command;
    }

    public List<String> asAssemblyCode() {
        return null;
    }

    public static VmCommandWrapper of(String command) {
        return new VmCommandWrapper(command);
    }

}
