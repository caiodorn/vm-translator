package com.caiodorn.nand2tetris.vm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VMCommandTypeEnumTest {

    @Test
    void shouldThrowException_whenFromType_givenTypeDoesntExist() {
        assertThrows(IllegalArgumentException.class, () -> VMCommandTypeEnum.of("invalid"));
    }

    @Test
    void shouldReturnExpectedEnum_whenFromType_givenTypeExists() {
        assertEquals(VMCommandTypeEnum.PUSH_CONSTANT, VMCommandTypeEnum.of(VMCommandTypeEnum.PUSH_CONSTANT.getType()));
        assertEquals(VMCommandTypeEnum.POP_LOCAL, VMCommandTypeEnum.of(VMCommandTypeEnum.POP_LOCAL.getType()));
        assertEquals(VMCommandTypeEnum.PUSH_LOCAL, VMCommandTypeEnum.of(VMCommandTypeEnum.PUSH_LOCAL.getType()));
        assertEquals(VMCommandTypeEnum.POP_THIS, VMCommandTypeEnum.of(VMCommandTypeEnum.POP_THIS.getType()));
        assertEquals(VMCommandTypeEnum.PUSH_THIS, VMCommandTypeEnum.of(VMCommandTypeEnum.PUSH_THIS.getType()));
        assertEquals(VMCommandTypeEnum.POP_THAT, VMCommandTypeEnum.of(VMCommandTypeEnum.POP_THAT.getType()));
        assertEquals(VMCommandTypeEnum.PUSH_THAT, VMCommandTypeEnum.of(VMCommandTypeEnum.PUSH_THAT.getType()));
        assertEquals(VMCommandTypeEnum.POP_ARGUMENT, VMCommandTypeEnum.of(VMCommandTypeEnum.POP_ARGUMENT.getType()));
        assertEquals(VMCommandTypeEnum.PUSH_ARGUMENT, VMCommandTypeEnum.of(VMCommandTypeEnum.PUSH_ARGUMENT.getType()));
        assertEquals(VMCommandTypeEnum.POP_POINTER, VMCommandTypeEnum.of(VMCommandTypeEnum.POP_POINTER.getType()));
        assertEquals(VMCommandTypeEnum.PUSH_POINTER, VMCommandTypeEnum.of(VMCommandTypeEnum.PUSH_POINTER.getType()));
        assertEquals(VMCommandTypeEnum.POP_STATIC, VMCommandTypeEnum.of(VMCommandTypeEnum.POP_STATIC.getType()));
        assertEquals(VMCommandTypeEnum.PUSH_STATIC, VMCommandTypeEnum.of(VMCommandTypeEnum.PUSH_STATIC.getType()));
        assertEquals(VMCommandTypeEnum.POP_TEMP, VMCommandTypeEnum.of(VMCommandTypeEnum.POP_TEMP.getType()));
        assertEquals(VMCommandTypeEnum.PUSH_TEMP, VMCommandTypeEnum.of(VMCommandTypeEnum.PUSH_TEMP.getType()));
        assertEquals(VMCommandTypeEnum.ADD, VMCommandTypeEnum.of(VMCommandTypeEnum.ADD.getType()));
        assertEquals(VMCommandTypeEnum.SUB, VMCommandTypeEnum.of(VMCommandTypeEnum.SUB.getType()));
        assertEquals(VMCommandTypeEnum.NEG, VMCommandTypeEnum.of(VMCommandTypeEnum.NEG.getType()));
        assertEquals(VMCommandTypeEnum.EQ, VMCommandTypeEnum.of(VMCommandTypeEnum.EQ.getType()));
        assertEquals(VMCommandTypeEnum.GT, VMCommandTypeEnum.of(VMCommandTypeEnum.GT.getType()));
        assertEquals(VMCommandTypeEnum.LT, VMCommandTypeEnum.of(VMCommandTypeEnum.LT.getType()));
        assertEquals(VMCommandTypeEnum.AND, VMCommandTypeEnum.of(VMCommandTypeEnum.AND.getType()));
        assertEquals(VMCommandTypeEnum.OR, VMCommandTypeEnum.of(VMCommandTypeEnum.OR.getType()));
        assertEquals(VMCommandTypeEnum.NOT, VMCommandTypeEnum.of(VMCommandTypeEnum.NOT.getType()));
        assertEquals(VMCommandTypeEnum.GOTO, VMCommandTypeEnum.of(VMCommandTypeEnum.GOTO.getType()));
        assertEquals(VMCommandTypeEnum.IF_GOTO, VMCommandTypeEnum.of(VMCommandTypeEnum.IF_GOTO.getType()));
        assertEquals(VMCommandTypeEnum.LABEL, VMCommandTypeEnum.of(VMCommandTypeEnum.LABEL.getType()));
        assertEquals(VMCommandTypeEnum.FUNCTION, VMCommandTypeEnum.of(VMCommandTypeEnum.FUNCTION.getType()));
        assertEquals(VMCommandTypeEnum.RETURN, VMCommandTypeEnum.of(VMCommandTypeEnum.RETURN.getType()));
        assertEquals(VMCommandTypeEnum.CALL, VMCommandTypeEnum.of(VMCommandTypeEnum.CALL.getType()));
    }

}
