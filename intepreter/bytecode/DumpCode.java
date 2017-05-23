package bytecode;

import vm.*;

public class DumpCode extends ByteCode {

    private String opCode;
    private String dumpState;

    @Override
    public String getInstruction() {
        return opCode + " " +dumpState;
    }

    @Override
    public String getCode() {
        return opCode;
    }

    @Override
    public void init(String[] args) {
        opCode = args[0];
        dumpState = args[1];
    }

    @Override
    public void execute(VirtualMachine vm) {
        switch (dumpState) {
            case "ON":
                vm.setDumpSwitch(true);
                break;
            case "OFF":
                vm.setDumpSwitch(false);
                break;
        }
    }
}
