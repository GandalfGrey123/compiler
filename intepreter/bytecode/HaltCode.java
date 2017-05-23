package bytecode;

import vm.*;

public class HaltCode extends ByteCode {

    private String opCode;

    @Override
    public String getInstruction() {
        String instruction;
        instruction = (opCode);
        return instruction;
    }

    @Override
    public String getCode() {
        return opCode;
    }

    @Override
    public void init(String[] args) {
        opCode = args[0];
    }

    @Override
    public void execute(VirtualMachine vm) {
        System.exit(0);
    }
}
