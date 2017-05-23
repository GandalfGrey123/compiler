package bytecode;

import vm.VirtualMachine;
import debugger.DebuggerVM;

public class FunctionCode extends ByteCode{
     
    private String opCode;
    private String functionId;
    private int startLine;
    private int endLine;
    
    @Override
     public String getInstruction(){
         return opCode + " " + functionId + " " + startLine+ " " + endLine;
    }

     @Override
     public String getCode(){
         return opCode;
    }
     
     @Override
     public void init(String[] args){
       opCode = args[0];
       functionId = args[1];
       startLine = Integer.parseInt(args[2]);
       endLine = Integer.parseInt(args[3]);
     }
     
     public void execute(VirtualMachine vm){
         DebuggerVM dvm = (DebuggerVM)vm;
         dvm.initFuncData(functionId, startLine, endLine);
         
     }
}
