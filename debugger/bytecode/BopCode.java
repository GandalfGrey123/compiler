package bytecode;

import vm.*;

public class BopCode extends ByteCode {

    private String opCode;
    private String binOp;

    @Override
    public String getInstruction() {
        String instruction;
        instruction = (opCode + " " + binOp);
        return instruction;
    }

    @Override
    public String getCode() {
        return opCode;
    }

    @Override
    public void init(String[] args) {
        opCode = args[0];
        binOp = args[1];
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.binOp(this.binOp);
    }
}
