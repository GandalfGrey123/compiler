package bytecode;

import vm.*;

public class LitCode extends ByteCode {

    private int literalValue;
    private String id = null;
    private String opCode;

    @Override
    public String getInstruction() {
        String instruction;
        if (id != null) {
            instruction = (opCode + " " + literalValue + " " + id);
        } else {
            instruction = (opCode + " " + literalValue);
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
        literalValue = Integer.parseInt(args[1]);
        if(args.length == 3 && id == null) id = args[2];
        
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.loadImediate(literalValue);
    }
}
