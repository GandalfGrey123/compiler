package bytecode;

import vm.*;

public class ReturnCode extends ByteCode {

    private String opCode;

    @Override
    public String getInstruction() {
        return opCode;
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
        int returnAddress = vm.getReturn();
        vm.popFrame();
        vm.setPC(returnAddress);
    }
}
