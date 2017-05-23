package bytecode;

import vm.*;

public class ArgsCode extends ByteCode {

    private int argCount;
    private String opCode;

    @Override
    public String getInstruction() {
        String instruction;
        instruction = (opCode + " " + argCount);
        return instruction;
    }

    @Override
    public String getCode() {
        return opCode;
    }

    @Override
    public void init(String[] args) {
        opCode = args[0];
        argCount = Integer.parseInt(args[1]);
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.pushFrame(argCount);
    }
}
