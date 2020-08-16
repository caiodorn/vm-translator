package com.caiodorn.nand2tetris.vm;

public enum VmMemoryMapEnum {

    SP(0),
    LCL(1),
    ARG(2),
    THIS(3),
    THAT(4),
    STACK_BASE_ADDR(256),
    STACK_MAX_ADDR(2047);

    private final int ramAddr;

    VmMemoryMapEnum(int ramAddr) {
        this.ramAddr = ramAddr;
    }

    public int getRamAddr() {
        return ramAddr;
    }

}
