package com.caiodorn.nand2tetris.vm;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum VMCommandTypeEnum {

    PUSH_CONSTANT("push constant"),
    PUSH_LOCAL("push local"),
    POP_LOCAL("pop local"),
    PUSH_TEMP("push temp"),
    POP_TEMP("pop temp"),
    PUSH_THIS("push this"),
    POP_THIS("pop this"),
    PUSH_THAT("push that"),
    POP_THAT("pop that"),
    PUSH_POINTER("push pointer"),
    POP_POINTER("pop pointer"),
    PUSH_STATIC("push static"),
    POP_STATIC("pop static"),
    PUSH_ARGUMENT("push argument"),
    POP_ARGUMENT("pop argument"),
    ADD("add"),
    SUB("sub"),
    NEG("neg"),
    EQ("eq"),
    GT("gt"),
    LT("lt"),
    AND("and"),
    OR("or"),
    NOT("not"),
    GOTO("goto"),
    IF_GOTO("if-goto"),
    LABEL("label"),
    FUNCTION("function"),
    RETURN("return"),
    CALL("call");

    private final String type;

    VMCommandTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static VMCommandTypeEnum of(String type) {
        try {
            return Stream.of(VMCommandTypeEnum.values())
                    .filter((t -> type.equals(t.type)))
                    .collect(Collectors.toList())
                    .get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(String.format("No suitable enum for provided type: %s.", type));
        }
    }

}
