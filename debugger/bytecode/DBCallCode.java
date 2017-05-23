package bytecode;

import debugger.DebuggerVM;
import vm.VirtualMachine;


public class DBCallCode extends CallCode{
     
  @Override
  public void execute(VirtualMachine vm){
    super.execute(vm);
    DebuggerVM dvm = (DebuggerVM) vm;
    dvm.pushRecord(); 
  }
    
}
