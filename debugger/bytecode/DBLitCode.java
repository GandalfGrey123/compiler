package bytecode;

import vm.*;
import debugger.DebuggerVM;

public class DBLitCode extends LitCode {

    public void execute(VirtualMachine vm) {
        super.execute(vm);

        DebuggerVM tmp = (DebuggerVM) vm;

        if (id != null) {
            int offsetval = tmp.currentFrameSize();
            tmp.addFuncVars(id, offsetval);
        }

    }
}
