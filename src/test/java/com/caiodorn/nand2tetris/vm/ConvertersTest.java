package com.caiodorn.nand2tetris.vm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class ConvertersTest {

    @Test
    void shouldReturnExpectedAssemblyCode_whenPushConstant() {
        List<String> expected = new ArrayList<>();
        expected.add("@10");
        expected.add("D=A");
        expected.add("@SP");
        expected.add("AM=M+1");
        expected.add("A=A-1");
        expected.add("M=D");

        assertIterableEquals(expected, Converters.PUSH_CONSTANT.apply("push constant 10"));
    }

    @Test
    void shouldReturnExpectedAssemblyCode_whenPushLocal() {
        List<String> expected = new ArrayList<>();
        expected.add("@10");
        expected.add("D=A");
        expected.add("@LCL");
        expected.add("A=D+M");
        expected.add("D=M");
        expected.add("@SP");
        expected.add("AM=M+1");
        expected.add("A=A-1");
        expected.add("M=D");

        assertIterableEquals(expected, Converters.PUSH_LOCAL.apply("push local 10"));
    }

    @Test
    void shouldReturnExpectedAssemblyCode_whenPopLocal() {
        List<String> expected = new ArrayList<>();
        expected.add("@10");
        expected.add("D=A");
        expected.add("@LCL");
        expected.add("D=D+M");
        expected.add("@R13");
        expected.add("M=D");
        expected.add("@SP");
        expected.add("AM=M-1");
        expected.add("D=M");
        expected.add("@R13");
        expected.add("A=M");
        expected.add("M=D");

        assertIterableEquals(expected, Converters.POP_LOCAL.apply("pop local 10"));
    }

    @Test
    void shouldReturnExpectedAssemblyCode_whenPopArgument() {
        List<String> expected = new ArrayList<>();
        expected.add("@10");
        expected.add("D=A");
        expected.add("@ARG");
        expected.add("D=D+M");
        expected.add("@R13");
        expected.add("M=D");
        expected.add("@SP");
        expected.add("AM=M-1");
        expected.add("D=M");
        expected.add("@R13");
        expected.add("A=M");
        expected.add("M=D");

        assertIterableEquals(expected, Converters.POP_ARGUMENT.apply("pop argument 10"));
    }

    @Test
    void shouldReturnExpectedAssemblyCode_whenPushArgument() {
        List<String> expected = new ArrayList<>();
        expected.add("@10");
        expected.add("D=A");
        expected.add("@ARG");
        expected.add("A=D+M");
        expected.add("D=M");
        expected.add("@SP");
        expected.add("AM=M+1");
        expected.add("A=A-1");
        expected.add("M=D");

        assertIterableEquals(expected, Converters.PUSH_ARGUMENT.apply("push argument 10"));
    }

    @Test
    void shouldReturnExpectedAssemblyCode_whenPopThis() {
        List<String> expected = new ArrayList<>();
        expected.add("@10");
        expected.add("D=A");
        expected.add("@THIS");
        expected.add("D=D+M");
        expected.add("@R13");
        expected.add("M=D");
        expected.add("@SP");
        expected.add("AM=M-1");
        expected.add("D=M");
        expected.add("@R13");
        expected.add("A=M");
        expected.add("M=D");

        assertIterableEquals(expected, Converters.POP_THIS.apply("pop this 10"));
    }

    @Test
    void shouldReturnExpectedAssemblyCode_whenPushThis() {
        List<String> expected = new ArrayList<>();
        expected.add("@10");
        expected.add("D=A");
        expected.add("@THIS");
        expected.add("A=D+M");
        expected.add("D=M");
        expected.add("@SP");
        expected.add("AM=M+1");
        expected.add("A=A-1");
        expected.add("M=D");

        assertIterableEquals(expected, Converters.PUSH_THIS.apply("push this 10"));
    }

    @Test
    void shouldReturnExpectedAssemblyCode_whenPopThat() {
        List<String> expected = new ArrayList<>();
        expected.add("@10");
        expected.add("D=A");
        expected.add("@THAT");
        expected.add("D=D+M");
        expected.add("@R13");
        expected.add("M=D");
        expected.add("@SP");
        expected.add("AM=M-1");
        expected.add("D=M");
        expected.add("@R13");
        expected.add("A=M");
        expected.add("M=D");

        assertIterableEquals(expected, Converters.POP_THAT.apply("pop that 10"));
    }

    @Test
    void shouldReturnExpectedAssemblyCode_whenPushThat() {
        List<String> expected = new ArrayList<>();
        expected.add("@10");
        expected.add("D=A");
        expected.add("@THAT");
        expected.add("A=D+M");
        expected.add("D=M");
        expected.add("@SP");
        expected.add("AM=M+1");
        expected.add("A=A-1");
        expected.add("M=D");

        assertIterableEquals(expected, Converters.PUSH_THAT.apply("push that 10"));
    }

    @Test
    void shouldReturnExpectedAssemblyCode_whenPopPointer0() {
        List<String> expected = new ArrayList<>();
        expected.add("@SP");
        expected.add("AM=M-1");
        expected.add("D=M");
        expected.add("@THIS");
        expected.add("M=D");

        assertIterableEquals(expected, Converters.POP_POINTER.apply("pop pointer 0"));
    }

    @Test
    void shouldReturnExpectedAssemblyCode_whenPopPointer1() {
        List<String> expected = new ArrayList<>();
        expected.add("@SP");
        expected.add("AM=M-1");
        expected.add("D=M");
        expected.add("@THAT");
        expected.add("M=D");

        assertIterableEquals(expected, Converters.POP_POINTER.apply("pop pointer 1"));
    }

    @Test
    void shouldReturnExpectedAssemblyCode_whenPushPointer0() {
        List<String> expected = new ArrayList<>();
        expected.add("@THIS");
        expected.add("D=M");
        expected.add("@SP");
        expected.add("AM=M+1");
        expected.add("A=A-1");
        expected.add("M=D");

        assertIterableEquals(expected, Converters.PUSH_POINTER.apply("push pointer 0"));
    }

    @Test
    void shouldReturnExpectedAssemblyCode_whenPushPointer1() {
        List<String> expected = new ArrayList<>();
        expected.add("@THAT");
        expected.add("D=M");
        expected.add("@SP");
        expected.add("AM=M+1");
        expected.add("A=A-1");
        expected.add("M=D");

        assertIterableEquals(expected, Converters.PUSH_POINTER.apply("push pointer 1"));
    }

    @Test
    void shouldReturnExpectedAssemblyCode_whenPopStatic() {
        List<String> expected = new ArrayList<>();
        expected.add("@SP");
        expected.add("AM=M-1");
        expected.add("D=M");
        expected.add("@filename.10");
        expected.add("M=D");

        assertIterableEquals(expected, Converters.POP_STATIC.apply("pop static 10 filename"));
    }

    @Test
    void shouldReturnExpectedAssemblyCode_whenPushStatic() {
        List<String> expected = new ArrayList<>();
        expected.add("@filename.10");
        expected.add("D=M");
        expected.add("@SP");
        expected.add("AM=M+1");
        expected.add("A=A-1");
        expected.add("M=D");

        assertIterableEquals(expected, Converters.PUSH_STATIC.apply("push static 10 filename"));
    }

    @Test
    void shouldReturnExpectedAssemblyCode_whenPopTemp() {
        List<String> expected = new ArrayList<>();
        expected.add("@SP");
        expected.add("AM=M-1");
        expected.add("D=M");
        expected.add("@15");
        expected.add("M=D");

        assertIterableEquals(expected, Converters.POP_TEMP.apply("pop temp 10"));
    }

    @Test
    void shouldReturnExpectedAssemblyCode_whenPushTemp() {
        List<String> expected = new ArrayList<>();
        expected.add("@15");
        expected.add("D=M");
        expected.add("@SP");
        expected.add("AM=M+1");
        expected.add("A=A-1");
        expected.add("M=D");

        assertIterableEquals(expected, Converters.PUSH_TEMP.apply("push temp 10"));
    }

    @Test
    void shouldReturnExpectedAssemblyCode_whenAdd() {
        List<String> expected = new ArrayList<>();
        expected.add("@SP");
        expected.add("AM=M-1");
        expected.add("D=M");
        expected.add("A=A-1");
        expected.add("M=M+D");

        assertIterableEquals(expected, Converters.ADD.apply("add"));
    }

    @Test
    void shouldReturnExpectedAssemblyCode_whenSub() {
        List<String> expected = new ArrayList<>();
        expected.add("@SP");
        expected.add("AM=M-1");
        expected.add("D=M");
        expected.add("A=A-1");
        expected.add("M=M-D");

        assertIterableEquals(expected, Converters.SUB.apply("sub"));
    }

    @Test
    void shouldReturnExpectedAssemblyCode_whenNeg() {
        List<String> expected = new ArrayList<>();
        expected.add("@SP");
        expected.add("A=M-1");
        expected.add("M=-M");

        assertIterableEquals(expected, Converters.NEG.apply("neg"));
    }

    @Test
    void shouldReturnExpectedAssemblyCode_whenEq() {
        List<String> expected = new ArrayList<>();
        expected.add(String.format("@RETURN_TO_%d", Converters.getCount() + 1));
        expected.add("D=A");
        expected.add("@R14");
        expected.add("M=D");
        expected.add("@BEGIN_EQ");
        expected.add("0;JMP");
        expected.add(String.format("(RETURN_TO_%d)", Converters.getCount() + 1));

        assertIterableEquals(expected, Converters.EQ.apply("eq"));
    }

    @Test
    void shouldReturnExpectedAssemblyCode_whenGt() {
        List<String> expected = new ArrayList<>();
        expected.add(String.format("@RETURN_TO_%d", Converters.getCount() + 1));
        expected.add("D=A");
        expected.add("@R14");
        expected.add("M=D");
        expected.add("@BEGIN_GT");
        expected.add("0;JMP");
        expected.add(String.format("(RETURN_TO_%d)", Converters.getCount() + 1));

        assertIterableEquals(expected, Converters.GT.apply("gt"));
    }

    @Test
    void shouldReturnExpectedAssemblyCode_whenLt() {
        List<String> expected = new ArrayList<>();
        expected.add(String.format("@RETURN_TO_%d",  Converters.getCount() + 1));
        expected.add("D=A");
        expected.add("@R14");
        expected.add("M=D");
        expected.add("@BEGIN_LT");
        expected.add("0;JMP");
        expected.add(String.format("(RETURN_TO_%d)",  Converters.getCount() + 1));

        assertIterableEquals(expected, Converters.LT.apply("lt"));
    }

    @Test
    void shouldReturnExpectedAssemblyCode_whenAnd() {
        List<String> expected = new ArrayList<>();
        expected.add("@SP");
        expected.add("AM=M-1");
        expected.add("D=M");
        expected.add("A=A-1");
        expected.add("M=D&M");

        assertIterableEquals(expected, Converters.AND.apply("and"));
    }

    @Test
    void shouldReturnExpectedAssemblyCode_whenOr() {
        List<String> expected = new ArrayList<>();
        expected.add("@SP");
        expected.add("AM=M-1");
        expected.add("D=M");
        expected.add("A=A-1");
        expected.add("M=D|M");

        assertIterableEquals(expected, Converters.OR.apply("or"));
    }

    @Test
    void shouldReturnExpectedAssemblyCode_whenNot() {
        List<String> expected = new ArrayList<>();
        expected.add("@SP");
        expected.add("A=M-1");
        expected.add("M=!M");

        assertIterableEquals(expected, Converters.NOT.apply("not"));
    }

}
