package bytecode;

import vm.*;

public class LoadCode extends ByteCode {

    private String id;
    private int stackOffset;
    private String opCode;

    @Override
    public String getInstruction() {
        String instruction;

        instruction = (opCode + " " + stackOffset + " " + id);

        return instruction;
    }

    @Override
    public String getCode() {
        return opCode;
    }

    @Override
    public void init(String[] args) {
        opCode = args[0];
        stackOffset = Integer.parseInt(args[1]);
        id = args[2];
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.load(stackOffset);
    }
}
