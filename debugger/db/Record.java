package debugger;

import java.util.Vector;

public class Record {
    
    private int pc;
    private int size;
    private Integer runstackSize;
    private Vector<VariableHolder> podStack;
    
    public Record(int pc, int size){
        this.pc = pc;
        this.size = size;
    }
    
    
     public void PushHolder(VariableHolder var){
     podStack.add(var);
     }
    
    
    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public int getRunstackSize() {
        return this.size;
    }

    public void setRunstackSize(int size) {
        this.size = size;
    }

}
