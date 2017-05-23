
package bytecode;

import debugger.DebuggerVM;
import vm.VirtualMachine;


public class DBPopCode extends PopCode{
     
  
    public void execute(VirtualMachine vm){
    super.execute(vm);

    DebuggerVM tmp = (DebuggerVM) vm;
        tmp.popEnivronmentStack(popLevels);
    }

}
