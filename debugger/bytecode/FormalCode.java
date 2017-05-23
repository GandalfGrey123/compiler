
package bytecode;

import vm.VirtualMachine;
import debugger.DebuggerVM;

public class FormalCode extends ByteCode{
     
    private int literalValue;
    private String id = null;
    private String opCode;

    @Override
    public String getInstruction() {
        return opCode + " " + id + " " + literalValue;
    }

    @Override
    public String getCode() {
        return opCode;
    }

    @Override
    public void init(String[] args) {
        opCode = args[0];
        id = args[1];
        literalValue = Integer.parseInt(args[2]);
    }

    @Override
     public void execute(VirtualMachine vm){
         DebuggerVM dvm = (DebuggerVM) vm;
         dvm.addFuncVars(id, literalValue);
         
     }
}