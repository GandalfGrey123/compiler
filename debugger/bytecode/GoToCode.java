package bytecode;

import vm.*;

public class GoToCode extends ByteCode {

    private String labelName = null;
    private String branchLineNumber;
    private String opCode;

    @Override
    public String getInstruction() {
        String instruction;

        instruction = (opCode + " " + labelName);
        return instruction;
    }

    @Override
    public String getLabel() {
        return branchLineNumber;
    }

    @Override
    public String getCode() {
        return opCode;
    }

    @Override
    public void init(String[] args) {
        opCode = args[0];
        branchLineNumber = args[1];
        if(labelName == null){
        labelName = args[1];
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.setPC(Integer.parseInt(branchLineNumber));
    }
}
