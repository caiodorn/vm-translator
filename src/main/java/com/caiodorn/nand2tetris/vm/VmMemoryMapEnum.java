package com.caiodorn.nand2tetris.vm;

public enum VmMemoryMapEnum {

    SP(0),
    LCL(1),
    ARG(2),
    THIS(3),
    THAT(4),
    STATIC_BASE_ADDR(16),
    STATIC_FINAL_ADDR(255),
    STACK_BASE_ADDR(256),
    STACK_FINAL_ADDR(2047),
    LCL_BASE_ADDR(2048);

    private final int ramAddr;

    VmMemoryMapEnum(int ramAddr) {
        this.ramAddr = ramAddr;
    }

    public int getRamAddr() {
        return ramAddr;
    }

}
