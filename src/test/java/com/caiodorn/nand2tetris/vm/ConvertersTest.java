package com.caiodorn.nand2tetris.vm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConvertersTest {

    @Test
    public void shouldReturnConverter_whenPushConstantType() {
        assertNotNull(Converters.getConverter(VmCommandTypeEnum.PUSH_CONSTANT.getType()));
    }

    @Test
    public void shouldReturnConverter_whenPushLocalType() {
        assertNotNull(Converters.getConverter(VmCommandTypeEnum.PUSH_LOCAL.getType()));
    }

}
