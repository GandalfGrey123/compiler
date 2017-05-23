package bytecode;

import vm.*;

public class LabelCode extends ByteCode {
    private String labelName;
    private String labelLineNumber;
    private String opCode;

    
    @Override
    public void setLabel(String label){
        labelName = label;
    }
    
    //creates a string for dumping
    @Override
    public String getInstruction() {
        String instruction;
        instruction = (opCode + " " + labelName);
        return instruction;
    }

    @Override
    public String getCode() {
        return opCode;
    }

    @Override
    public void init(String[] args) {
        opCode = args[0];
        labelLineNumber = args[1];
    }

    @Override
    public void execute(VirtualMachine vm) {
    }

}
