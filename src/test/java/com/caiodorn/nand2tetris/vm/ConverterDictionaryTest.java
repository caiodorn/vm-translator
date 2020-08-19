package com.caiodorn.nand2tetris.vm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConverterDictionaryTest {

    @Test
    public void shouldReturnConverter_whenPushConstantType() {
        assertNotNull(ConverterDictionary.get(VmCommandTypeEnum.PUSH_CONSTANT));
    }

    @Test
    public void shouldReturnConverter_whenPushLocalType() {
        assertNotNull(ConverterDictionary.get(VmCommandTypeEnum.PUSH_LOCAL));
    }

}
