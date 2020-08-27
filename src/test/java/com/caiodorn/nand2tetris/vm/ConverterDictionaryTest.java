package com.caiodorn.nand2tetris.vm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConverterDictionaryTest {

    @Test
    void shouldReturnConverter_whenPushConstantType() {
        assertNotNull(ConverterDictionary.get(VmCommandTypeEnum.PUSH_CONSTANT));
    }

    @Test
    void shouldReturnConverter_whenPushLocalType() {
        assertNotNull(ConverterDictionary.get(VmCommandTypeEnum.PUSH_LOCAL));
    }

    @Test
    void shouldReturnConverter_whenPopLocalType() {
        assertNotNull(ConverterDictionary.get(VmCommandTypeEnum.POP_LOCAL));
    }

    @Test
    void shouldReturnConverter_whenAddType() {
        assertNotNull(ConverterDictionary.get(VmCommandTypeEnum.ADD));
    }

    @Test
    void shouldReturnConverter_whenSubType() {
        assertNotNull(ConverterDictionary.get(VmCommandTypeEnum.SUB));
    }

    @Test
    void shouldReturnConverter_whenNegType() {
        assertNotNull(ConverterDictionary.get(VmCommandTypeEnum.NEG));
    }

    @Test
    void shouldReturnConverter_whenEqType() {
        assertNotNull(ConverterDictionary.get(VmCommandTypeEnum.EQ));
    }

    @Test
    void shouldReturnConverter_whenGtType() {
        assertNotNull(ConverterDictionary.get(VmCommandTypeEnum.GT));
    }

    @Test
    void shouldReturnConverter_whenLtType() {
        assertNotNull(ConverterDictionary.get(VmCommandTypeEnum.LT));
    }

    @Test
    void shouldReturnConverter_whenAndType() {
        assertNotNull(ConverterDictionary.get(VmCommandTypeEnum.AND));
    }

    @Test
    void shouldReturnConverter_whenOrType() {
        assertNotNull(ConverterDictionary.get(VmCommandTypeEnum.OR));
    }

    @Test
    void shouldReturnConverter_whenNotType() {
        assertNotNull(ConverterDictionary.get(VmCommandTypeEnum.NOT));
    }

}
