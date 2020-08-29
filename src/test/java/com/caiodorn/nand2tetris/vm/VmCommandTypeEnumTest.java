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
        assertEquals(VmCommandTypeEnum.POP_THIS, VmCommandTypeEnum.of(VmCommandTypeEnum.POP_THIS.getType()));
        assertEquals(VmCommandTypeEnum.PUSH_THIS, VmCommandTypeEnum.of(VmCommandTypeEnum.PUSH_THIS.getType()));
        assertEquals(VmCommandTypeEnum.POP_THAT, VmCommandTypeEnum.of(VmCommandTypeEnum.POP_THAT.getType()));
        assertEquals(VmCommandTypeEnum.PUSH_THAT, VmCommandTypeEnum.of(VmCommandTypeEnum.PUSH_THAT.getType()));
        assertEquals(VmCommandTypeEnum.POP_ARGUMENT, VmCommandTypeEnum.of(VmCommandTypeEnum.POP_ARGUMENT.getType()));
        assertEquals(VmCommandTypeEnum.PUSH_ARGUMENT, VmCommandTypeEnum.of(VmCommandTypeEnum.PUSH_ARGUMENT.getType()));
        assertEquals(VmCommandTypeEnum.POP_POINTER, VmCommandTypeEnum.of(VmCommandTypeEnum.POP_POINTER.getType()));
        assertEquals(VmCommandTypeEnum.PUSH_POINTER, VmCommandTypeEnum.of(VmCommandTypeEnum.PUSH_POINTER.getType()));
        assertEquals(VmCommandTypeEnum.POP_STATIC, VmCommandTypeEnum.of(VmCommandTypeEnum.POP_STATIC.getType()));
        assertEquals(VmCommandTypeEnum.PUSH_STATIC, VmCommandTypeEnum.of(VmCommandTypeEnum.PUSH_STATIC.getType()));
        assertEquals(VmCommandTypeEnum.POP_TEMP, VmCommandTypeEnum.of(VmCommandTypeEnum.POP_TEMP.getType()));
        assertEquals(VmCommandTypeEnum.PUSH_TEMP, VmCommandTypeEnum.of(VmCommandTypeEnum.PUSH_TEMP.getType()));
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
