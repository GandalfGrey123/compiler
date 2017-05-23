package debugger;

/**
 * <pre>
 *
 *     SourceLine class is for creating source code line objects
 *     each source code line will be an object with debugging properties
 *     used by any UI to print source code info
 * 
 * </pre>
**/


public class SourceLine {

    private String lineCode;
    private int lineNumber;
    private boolean isBreakPointSet;

    public SourceLine(String code, int n) {
        lineCode = code;
        lineNumber = n;
        isBreakPointSet = false;
    }

    public void setBreakPoint(boolean newState) {
        isBreakPointSet = newState;
    }
    
    public boolean isBreakPoint(){
        return isBreakPointSet;
    }
    
    public int getLineNumber(){
        return lineNumber;
    }
    
    public String getLineCode() {
        return lineCode;
    }

}
