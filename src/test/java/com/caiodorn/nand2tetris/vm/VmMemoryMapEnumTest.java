package com.caiodorn.nand2tetris.vm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VmMemoryMapEnumTest {

    @Test
    public void shouldReturnExpectedValues() {
        assertEquals(0, VmMemoryMapEnum.SP.getRamAddr());
        assertEquals(1, VmMemoryMapEnum.LCL.getRamAddr());
        assertEquals(2, VmMemoryMapEnum.ARG.getRamAddr());
        assertEquals(3, VmMemoryMapEnum.THIS.getRamAddr());
        assertEquals(4, VmMemoryMapEnum.THAT.getRamAddr());
        assertEquals(256, VmMemoryMapEnum.STACK_BASE_ADDR.getRamAddr());
        assertEquals(2047, VmMemoryMapEnum.STACK_MAX_ADDR.getRamAddr());
    }

}
