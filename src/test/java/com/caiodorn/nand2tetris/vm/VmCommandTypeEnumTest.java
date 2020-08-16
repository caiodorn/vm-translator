package com.caiodorn.nand2tetris.vm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VmCommandTypeEnumTest {

    @Test
    public void shouldThrowException_whenTypeDoesntExist() {
        assertThrows(IllegalArgumentException.class, () -> VmCommandTypeEnum.fromType("invalid"));
    }

    @Test
    public void shouldReturnExpectedEnum_whenTypeExists() {
        assertEquals(VmCommandTypeEnum.PUSH_CONSTANT, VmCommandTypeEnum.fromType(VmCommandTypeEnum.PUSH_CONSTANT.getType()));
    }

}
