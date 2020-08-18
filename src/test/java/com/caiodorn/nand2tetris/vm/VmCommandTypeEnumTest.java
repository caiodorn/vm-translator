package com.caiodorn.nand2tetris.vm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VmCommandTypeEnumTest {

    @Test
    public void shouldThrowException_whenFromType_givenTypeDoesntExist() {
        assertThrows(IllegalArgumentException.class, () -> VmCommandTypeEnum.of("invalid"));
    }

    @Test
    public void shouldReturnExpectedEnum_whenFromType_givenTypeExists() {
        assertEquals(VmCommandTypeEnum.PUSH_CONSTANT, VmCommandTypeEnum.of(VmCommandTypeEnum.PUSH_CONSTANT.getType()));
    }

}
