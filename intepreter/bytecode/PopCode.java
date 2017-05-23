package bytecode;

import vm.*;

public class PopCode extends ByteCode {

    private int popLevels;
    private String opCode;

    @Override
    public String getInstruction() {
        String instruction;
        instruction = (opCode + " " + popLevels);
        return instruction;
    }

    @Override
    public String getCode() {
        return opCode;
    }

    @Override
    public void init(String[] args) {
        opCode = args[0];
        popLevels = Integer.parseInt(args[1]);
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.popLevels(popLevels);
    }

}
