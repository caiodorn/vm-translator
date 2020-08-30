package com.caiodorn.nand2tetris.vm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConverterDictionaryTest {

    @Test
    void shouldReturnConverter_whenPushConstantType() {
        assertNotNull(ConverterDictionary.get(VMCommandTypeEnum.PUSH_CONSTANT));
    }

    @Test
    void shouldReturnConverter_whenPushLocalType() {
        assertNotNull(ConverterDictionary.get(VMCommandTypeEnum.PUSH_LOCAL));
    }

    @Test
    void shouldReturnConverter_whenPopLocalType() {
        assertNotNull(ConverterDictionary.get(VMCommandTypeEnum.POP_LOCAL));
    }

    @Test
    void shouldReturnConverter_whenAddType() {
        assertNotNull(ConverterDictionary.get(VMCommandTypeEnum.ADD));
    }

    @Test
    void shouldReturnConverter_whenSubType() {
        assertNotNull(ConverterDictionary.get(VMCommandTypeEnum.SUB));
    }

    @Test
    void shouldReturnConverter_whenNegType() {
        assertNotNull(ConverterDictionary.get(VMCommandTypeEnum.NEG));
    }

    @Test
    void shouldReturnConverter_whenEqType() {
        assertNotNull(ConverterDictionary.get(VMCommandTypeEnum.EQ));
    }

    @Test
    void shouldReturnConverter_whenGtType() {
        assertNotNull(ConverterDictionary.get(VMCommandTypeEnum.GT));
    }

    @Test
    void shouldReturnConverter_whenLtType() {
        assertNotNull(ConverterDictionary.get(VMCommandTypeEnum.LT));
    }

    @Test
    void shouldReturnConverter_whenAndType() {
        assertNotNull(ConverterDictionary.get(VMCommandTypeEnum.AND));
    }

    @Test
    void shouldReturnConverter_whenOrType() {
        assertNotNull(ConverterDictionary.get(VMCommandTypeEnum.OR));
    }

    @Test
    void shouldReturnConverter_whenNotType() {
        assertNotNull(ConverterDictionary.get(VMCommandTypeEnum.NOT));
    }

}
