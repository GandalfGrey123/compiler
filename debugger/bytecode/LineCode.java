
package bytecode;

import debugger.DebuggerVM;
import vm.VirtualMachine;

public class LineCode extends ByteCode{
     private String opCode;
     private int lineNumber;
     
     @Override
     public String getInstruction(){
         return opCode + lineNumber;
    }

     @Override
     public String getCode(){
         return opCode;
    }

     @Override
     public void init(String[] args){
         opCode = args[0];
         lineNumber = Integer.parseInt(args[1]);
     }
     
     public int getLineNum(){
         return lineNumber;
     }
     
     public void execute(VirtualMachine vm){
         DebuggerVM dvm = (DebuggerVM) vm;
         if(lineNumber != -1){
             dvm.setLine(lineNumber);
         }
     }
     
}
