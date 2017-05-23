package bytecode;

import vm.*;

public class CallCode extends ByteCode {

    private String labelName = null;
    private String branchAddress;
    private String opCode;

    @Override
    public String getInstruction() {
        String instruction;
        instruction = (opCode + " " + labelName);
        return instruction;
    }

    @Override
    public String getLabel() {
        return branchAddress;
    }

    @Override
    public String getCode() {
        return opCode;
    }

    @Override
    public void init(String[] args) {
        opCode = args[0];
        branchAddress = args[1];
        if(labelName == null){
        labelName = args[1];
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        
        //set pc to branchAddress
        //branchAddress - 1 because fetch cycle will update pc to +1 
        //after call code is executed
        vm.saveReturn();
        vm.setPC(Integer.parseInt(branchAddress)-1);
    }
}
