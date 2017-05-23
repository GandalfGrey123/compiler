package debugger;
import java.util.*;

public class FunctionEnvironmentRecord {

    private String functionId;    
    private int lineStart;
    private int lineEnd;
    private int currentLine;

    Table funcTable;

    public FunctionEnvironmentRecord() {

        //initialize with default values until interpreter reads a FUNCTION byte code
        functionId = null;
        lineStart = 0;
        lineEnd = 0;
        currentLine = 0;

        //create the function's record table
        funcTable = new Table();
    }

    public void beginScope() {
        funcTable.beginScope();
    }

    public void endScope() {
        funcTable.endScope();
    }
    
    
    // pop n items from the function table
    public void popSymbols(int nItems) {
        funcTable.pop(nItems);
    }

    //used for FORMAL and LIT
    public void newVar(String id, Integer lit) {
        funcTable.addVar(id, lit);
    }
    
    public void printVars(){
         /*java.util.Set<String> table = funcTable.keys();
         Iterator nextKey = table.iterator();
         String id;
         int varVal;
         
          while(nextKey.hasNext()){
                id = (String)nextKey.next();
                varVal = (int) funcTable.get(id);
                System.out.print("  "+ id + ": " + varVal);
          }
         */
        
        
    }

    public void initFunction(String id, int start, int end) {
        setFunctionId(id);
        setLineStart(start);
        setLineEnd(end);
    }

    public void setFunctionId(String name) {
        functionId = name;
    }

    public void setLineStart(int n) {
        lineStart = n;
    }

    public void setLineEnd(int n) {
        lineEnd = n;
    }

    public void setCurrentLine(int n) {
        currentLine = n;
    }
    
    public Set<String> getKeySet(){
        return funcTable.keys();
    }
    
    
    int getOffsetValue(String key){
        int x = (int)funcTable.get(key);
        return x;
    }

    public String getFunctionId() {
        return functionId;
    }

    public int getLineStart() {
        return lineStart;
    }

    public int getLineEnd() {
        return lineEnd;
    }

    public int getCurrentLine() {
        return currentLine;
    }

    public void printEv() {        
        java.util.Set<String> table = funcTable.keys();
        //if the envorinment is empty print the empty symbols
        if (functionId == null) {
            System.out.println("(<>,-,-,-,-)");
        } 
        
        //not empty
        else {
                System.out.print("(<");
          //first check if there are variables
            if (!table.isEmpty()) {
   
               Iterator nextKey = table.iterator();
               String id;
               int varVal;
               
               while(nextKey.hasNext()){
                   
                id = (String)nextKey.next();
                varVal = (int) funcTable.get(id);
                System.out.print(id + "/" + varVal);
                if(nextKey.hasNext()) System.out.print(",");
               }
                
            //then print the rest of the function info
                printFunctionInfo();
                
           //no variables so just print function info
            } else {
                printFunctionInfo();
            }   
        }
        
    }
    
    
    //helper function 
    //prints the function info after variables
    public void printFunctionInfo(){
                System.out.print(">,");
                System.out.print(functionId + ",");
                System.out.print(lineStart+",");
                System.out.print(lineEnd+",");
                if(currentLine != 0) System.out.print(currentLine);
                else System.out.print("-");
                System.out.print(")");
                System.out.println();
    }
       
}
