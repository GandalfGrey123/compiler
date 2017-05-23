package bytecode;

import vm.*;
import java.util.*;

public class FalseBranchCode extends ByteCode {
    
    private String labelName = null;
    private String branch;
    private String opCode;

    @Override
    public String getInstruction() {
        String instruction;
        instruction = (opCode + " " + labelName);
        return instruction;
    }

    
    @Override
    public String getLabel() {
        return branch;
    }

    @Override
    public String getCode() {
        return opCode;
    }

    public int getBranchLine() {
        return Integer.parseInt(branch);
    }

    @Override
    public void init(String[] args) {
        opCode = args[0];
        branch = args[1];
        if(labelName == null){
        labelName = args[1];
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        int temp = vm.popRunStack();
        if (temp == 0) vm.setPC(this.getBranchLine());
    }
}
