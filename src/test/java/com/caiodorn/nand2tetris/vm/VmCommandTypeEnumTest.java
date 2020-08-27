package com.caiodorn.nand2tetris.vm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VmCommandTypeEnumTest {

    @Test
    void shouldThrowException_whenFromType_givenTypeDoesntExist() {
        assertThrows(IllegalArgumentException.class, () -> VmCommandTypeEnum.of("invalid"));
    }

    @Test
    void shouldReturnExpectedEnum_whenFromType_givenTypeExists() {
        assertEquals(VmCommandTypeEnum.PUSH_CONSTANT, VmCommandTypeEnum.of(VmCommandTypeEnum.PUSH_CONSTANT.getType()));
        assertEquals(VmCommandTypeEnum.POP_LOCAL, VmCommandTypeEnum.of(VmCommandTypeEnum.POP_LOCAL.getType()));
        assertEquals(VmCommandTypeEnum.PUSH_LOCAL, VmCommandTypeEnum.of(VmCommandTypeEnum.PUSH_LOCAL.getType()));
        assertEquals(VmCommandTypeEnum.ADD, VmCommandTypeEnum.of(VmCommandTypeEnum.ADD.getType()));
        assertEquals(VmCommandTypeEnum.SUB, VmCommandTypeEnum.of(VmCommandTypeEnum.SUB.getType()));
        assertEquals(VmCommandTypeEnum.NEG, VmCommandTypeEnum.of(VmCommandTypeEnum.NEG.getType()));
        assertEquals(VmCommandTypeEnum.EQ, VmCommandTypeEnum.of(VmCommandTypeEnum.EQ.getType()));
        assertEquals(VmCommandTypeEnum.GT, VmCommandTypeEnum.of(VmCommandTypeEnum.GT.getType()));
        assertEquals(VmCommandTypeEnum.LT, VmCommandTypeEnum.of(VmCommandTypeEnum.LT.getType()));
        assertEquals(VmCommandTypeEnum.AND, VmCommandTypeEnum.of(VmCommandTypeEnum.AND.getType()));
        assertEquals(VmCommandTypeEnum.OR, VmCommandTypeEnum.of(VmCommandTypeEnum.OR.getType()));
        assertEquals(VmCommandTypeEnum.NOT, VmCommandTypeEnum.of(VmCommandTypeEnum.NOT.getType()));
    }

}
