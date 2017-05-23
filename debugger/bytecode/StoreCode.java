package bytecode;

import vm.*;

public class StoreCode extends ByteCode {

    private int stackOffset;
    private String opCode;
    private String id;

    @Override
    public String getInstruction() {
        String instruction;
         if (id != null) {
            instruction = (opCode + " " + stackOffset + " " + id);
        } else{
            instruction = (opCode + " " + stackOffset);
        }
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
         if(args.length == 3 && id == null) id = args[2];
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.store(stackOffset);
    }
}
