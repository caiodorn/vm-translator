package com.caiodorn.nand2tetris.vm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VmMapEnumTest {

    @Test
    public void shouldReturnExpectedValues() {
        assertEquals(0, VmMapEnum.SP.getRamAddr());
        assertEquals(1, VmMapEnum.LCL.getRamAddr());
        assertEquals(2, VmMapEnum.ARG.getRamAddr());
        assertEquals(3, VmMapEnum.THIS.getRamAddr());
        assertEquals(4, VmMapEnum.THAT.getRamAddr());
        assertEquals(256, VmMapEnum.STACK_BASE_ADDR.getRamAddr());
        assertEquals(2047, VmMapEnum.STACK_MAX_ADDR.getRamAddr());
    }

}
