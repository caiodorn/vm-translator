@7     // push constant 7
D=A
@SP
A=M
M=D
@SP    // increment stack pointer
M=M+1
@8     // push constant 8
D=A
@SP
A=M
M=D
@SP
M=M+1  // increment stack pointer
@SP    // add operation starts here
A=M-1  // go *sp-1, since pointer always point to next position
D=M    // buffer value stored at *sp-1
@SP    // decrement stack pointer
AM=M-1 // go *sp-1, since pointer always point to next position
D=D+M  // D = 8 + 7






