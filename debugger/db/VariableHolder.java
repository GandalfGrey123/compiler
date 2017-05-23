package debugger;

public class VariableHolder {
    
    private int offset;
    private int value;

    public VariableHolder(int offset, int value) {
        this.offset= offset;
        this.value= value;
    }
    
    public int getOffset(){
        return offset;
    }
    public int getValue(){
        return value;
    }
}
