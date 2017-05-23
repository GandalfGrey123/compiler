package bytecode;
import vm.*;
import debugger.DebuggerVM;

public class DBReturnCode extends ReturnCode{
      
    
    public void execute(VirtualMachine vm){
    super.execute(vm);
    DebuggerVM dvm = (DebuggerVM)vm;
    
    dvm.endScope();
    dvm.popRecord();
    }
}
