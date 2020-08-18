package com.caiodorn.nand2tetris.vm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class VmCommandConvertersTest {

    @Test
    public void shouldReturnConverter_whenPushConstantType() {
        assertNotNull(VmCommandConverters.getForType(VmCommandTypeEnum.PUSH_CONSTANT));
    }

    @Test
    public void shouldReturnConverter_whenPushLocalType() {
        assertNotNull(VmCommandConverters.getForType(VmCommandTypeEnum.PUSH_LOCAL));
    }

}
