@PROGRAM_START
0;JMP
(BEGIN_EQ)
@SP
AM=M-1
D=M
A=A-1
D=M-D
M=-1
@END_EQ
D;JEQ
@SP
A=M-1
M=0
(END_EQ)
@R14
A=M
0;JMP
(BEGIN_GT)
@SP
AM=M-1
D=M
A=A-1
D=M-D
M=-1
@END_GT
D;JGT
@SP
A=M-1
M=0
(END_GT)
@R14
A=M
0;JMP
(BEGIN_LT)
@SP
AM=M-1
D=M
A=A-1
D=M-D
M=-1
@END_LT
D;JLT
@SP
A=M-1
M=0
(END_LT)
@R14
A=M
0;JMP
(PROGRAM_START)
@256
D=A
@SP
M=D
@17
D=A
@SP
AM=M+1
A=A-1
M=D
@17
D=A
@SP
AM=M+1
A=A-1
M=D
@RETURN_TO_1
D=A
@R14
M=D
@BEGIN_EQ
0;JMP
(RETURN_TO_1)
@17
D=A
@SP
AM=M+1
A=A-1
M=D
@16
D=A
@SP
AM=M+1
A=A-1
M=D
@RETURN_TO_2
D=A
@R14
M=D
@BEGIN_EQ
0;JMP
(RETURN_TO_2)
@16
D=A
@SP
AM=M+1
A=A-1
M=D
@17
D=A
@SP
AM=M+1
A=A-1
M=D
@RETURN_TO_3
D=A
@R14
M=D
@BEGIN_EQ
0;JMP
(RETURN_TO_3)
@892
D=A
@SP
AM=M+1
A=A-1
M=D
@891
D=A
@SP
AM=M+1
A=A-1
M=D
@RETURN_TO_4
D=A
@R14
M=D
@BEGIN_LT
0;JMP
(RETURN_TO_4)
@891
D=A
@SP
AM=M+1
A=A-1
M=D
@892
D=A
@SP
AM=M+1
A=A-1
M=D
@RETURN_TO_5
D=A
@R14
M=D
@BEGIN_LT
0;JMP
(RETURN_TO_5)
@891
D=A
@SP
AM=M+1
A=A-1
M=D
@891
D=A
@SP
AM=M+1
A=A-1
M=D
@RETURN_TO_6
D=A
@R14
M=D
@BEGIN_LT
0;JMP
(RETURN_TO_6)
@32767
D=A
@SP
AM=M+1
A=A-1
M=D
@32766
D=A
@SP
AM=M+1
A=A-1
M=D
@RETURN_TO_7
D=A
@R14
M=D
@BEGIN_GT
0;JMP
(RETURN_TO_7)
@32766
D=A
@SP
AM=M+1
A=A-1
M=D
@32767
D=A
@SP
AM=M+1
A=A-1
M=D
@RETURN_TO_8
D=A
@R14
M=D
@BEGIN_GT
0;JMP
(RETURN_TO_8)
@32766
D=A
@SP
AM=M+1
A=A-1
M=D
@32766
D=A
@SP
AM=M+1
A=A-1
M=D
@RETURN_TO_9
D=A
@R14
M=D
@BEGIN_GT
0;JMP
(RETURN_TO_9)
@57
D=A
@SP
AM=M+1
A=A-1
M=D
@31
D=A
@SP
AM=M+1
A=A-1
M=D
@53
D=A
@SP
AM=M+1
A=A-1
M=D
@SP
AM=M-1
D=M
A=A-1
M=M+D
@112
D=A
@SP
AM=M+1
A=A-1
M=D
@SP
AM=M-1
D=M
A=A-1
M=M-D
@SP
A=M-1
M=-M
@SP
AM=M-1
D=M
A=A-1
M=D&M
@82
D=A
@SP
AM=M+1
A=A-1
M=D
@SP
AM=M-1
D=M
A=A-1
M=D|M
@SP
A=M-1
M=!M
(END)
@END
0;JMP
