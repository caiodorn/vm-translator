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

    @Test
    public void shouldReturnConverter_whenPopLocalType() {
        assertNotNull(ConverterDictionary.get(VmCommandTypeEnum.POP_LOCAL));
    }

    @Test
    public void shouldReturnConverter_whenAddType() {
        assertNotNull(ConverterDictionary.get(VmCommandTypeEnum.ADD));
    }

    @Test
    public void shouldReturnConverter_whenSubType() {
        assertNotNull(ConverterDictionary.get(VmCommandTypeEnum.SUB));
    }

    @Test
    public void shouldReturnConverter_whenNegType() {
        assertNotNull(ConverterDictionary.get(VmCommandTypeEnum.NEG));
    }

    @Test
    public void shouldReturnConverter_whenEqType() {
        assertNotNull(ConverterDictionary.get(VmCommandTypeEnum.EQ));
    }

    @Test
    public void shouldReturnConverter_whenGtType() {
        assertNotNull(ConverterDictionary.get(VmCommandTypeEnum.GT));
    }

    @Test
    public void shouldReturnConverter_whenLtType() {
        assertNotNull(ConverterDictionary.get(VmCommandTypeEnum.LT));
    }

    @Test
    public void shouldReturnConverter_whenAndType() {
        assertNotNull(ConverterDictionary.get(VmCommandTypeEnum.AND));
    }

    @Test
    public void shouldReturnConverter_whenOrType() {
        assertNotNull(ConverterDictionary.get(VmCommandTypeEnum.OR));
    }

    @Test
    public void shouldReturnConverter_whenNotType() {
        assertNotNull(ConverterDictionary.get(VmCommandTypeEnum.NOT));
    }

}
