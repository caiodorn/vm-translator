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
@3030
D=A
@SP
AM=M+1
A=A-1
M=D
@SP
AM=M-1
D=M
@THIS
M=D
@3040
D=A
@SP
AM=M+1
A=A-1
M=D
@SP
AM=M-1
D=M
@THAT
M=D
@32
D=A
@SP
AM=M+1
A=A-1
M=D
@2
D=A
@THIS
D=D+M
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
@46
D=A
@SP
AM=M+1
A=A-1
M=D
@6
D=A
@THAT
D=D+M
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
@THIS
D=M
@SP
AM=M+1
A=A-1
M=D
@THAT
D=M
@SP
AM=M+1
A=A-1
M=D
@SP
AM=M-1
D=M
A=A-1
M=M+D
@2
D=A
@THIS
A=D+M
D=M
@SP
AM=M+1
A=A-1
M=D
@SP
AM=M-1
D=M
A=A-1
M=M-D
@6
D=A
@THAT
A=D+M
D=M
@SP
AM=M+1
A=A-1
M=D
@SP
AM=M-1
D=M
A=A-1
M=M+D
(END)
@END
0;JMP
