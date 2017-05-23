package bytecode;

import vm.*;

abstract public class ByteCode {

    public ByteCode() {
    }

    public String getLabel() {
        return "";
    }
    public void setLabel(String label){
    }
    
    abstract public String getInstruction();

    abstract public String getCode();

    abstract public void init(String[] args);

    abstract public void execute(VirtualMachine vm);
}
