

package debugger;
import program.*;
import vm.*;
import bytecode.*;

import java.util.Stack;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class DebuggerVM extends VirtualMachine{
    
    private final Program runProgram;
    private final ArrayList<SourceLine> sourceCode;
    
    private boolean isRunning;
    private boolean doStepOut;
    
    private String nextLine;
    private BufferedReader sourceReader;
    private Stack <FunctionEnvironmentRecord> environmentStack; 
    private Stack <Record> executionStack;
   
     
    public DebuggerVM(Program program){
        super(program);
        runProgram = program;
        sourceCode = new ArrayList();
        
        isRunning = false;
        environmentStack = new Stack();
        executionStack = new Stack();
        environmentStack.push((new FunctionEnvironmentRecord()));
        beginScope();
    }
    
    @Override
    public void executeProgram(){
    int environmentSize = environmentStack.size();    
        
        while(isRunning){
            
         ByteCode nextCode = runProgram.getNextCode(pc);
           nextCode.execute(this);
           
           //System.out.println("EXECUTING BYTECODE: " + nextCode.getInstruction() + " PC: " + pc);
           pc++;
           
           if((nextCode instanceof LineCode))checkBreakPoint(nextCode);
           if(doStepOut) stepOutAt(environmentSize-1);
           
        }
    
    }
    
    public void doStepOut() {
        doStepOut = true;
    }

    private void stepOutAt(int end) {
        if ((environmentStack.size()) == (end)) {
            isRunning = false;
            doStepOut = false;
        }
    }
    
    private void checkBreakPoint(ByteCode code){
             int lineNumber = ((LineCode)code).getLineNum();
                if(lineNumber >= 1){
                    SourceLine line =  sourceCode.get(lineNumber-1);
                    if(line.isBreakPoint()) isRunning = false; 
                }
    }
    
    
   
    //loadSourceCode() for sending debugger object a source file
    //SouceLine objects' lineNumber data member begins at 1.
    public void loadSourceCode(String sourceFile){
        int lineNum = 1;
        try{
         sourceReader = new BufferedReader(new FileReader(sourceFile));
            while((nextLine = sourceReader.readLine()) != null){
                if(!(nextLine.equals(""))){   //filter out white space
                    SourceLine newLine = new SourceLine(nextLine, lineNum);
                     sourceCode.add(newLine);  //add to the source code container  
                    lineNum++;
                }
            }
        } catch(IOException e){
            System.out.println("*** problem opening file");
        }
    }
    
    
    public void exePushRecord(){
        int size = this.whatIsRunStackSize();
        executionStack.push((new Record(pc, size)));
    }
    
    public void exePopRecord(){
        executionStack.pop();
    }
    
    public void pushRecord(){
        environmentStack.push((new FunctionEnvironmentRecord()));
        beginScope();
    }
    
    public void popRecord(){
        environmentStack.pop();
    }
    
    public void beginScope(){
        FunctionEnvironmentRecord temp = environmentStack.peek();
        temp.beginScope();
    }
    
    public void endScope(){
     FunctionEnvironmentRecord temp = environmentStack.peek();
        temp.endScope();
    }
    
     //initFuncData init the FunctionEnvironment object's id, start, and end info
    public void initFuncData(String id, int start, int end){
        FunctionEnvironmentRecord topRecord = environmentStack.peek();
        topRecord.initFunction(id, start, end);
    }
    
    public void addFuncVars(String id, int value){
        FunctionEnvironmentRecord topRecord = environmentStack.peek();
        topRecord.newVar(id, value);
    }
    
    
    public void popEnivronmentStack(int levels){
        FunctionEnvironmentRecord topRecord = environmentStack.peek();
        topRecord.popSymbols(levels);
    }
    
    
    // turn off and on break points
    public void setBreakPointAt(int lineNumber){
        sourceCode.get(lineNumber).setBreakPoint(true);
    }
    
    public void clearAllBreakPoints(){
     for(int i =0; i < sourceCode.size(); i++){
      sourceCode.get(i).setBreakPoint(false);
     }
    
    }
    
    public void removeBreakPointAt(int lineNumber){
        sourceCode.get(lineNumber).setBreakPoint(false);
    }
    
    public void continueProgram(){
        isRunning = true;
    }
    
    
    public void setLine(int line){
        FunctionEnvironmentRecord temp = environmentStack.peek();
        temp.setCurrentLine(line);
    }
    
    public int getCurrentLine(){
    FunctionEnvironmentRecord record = environmentStack.peek();
    return record.getCurrentLine();
    }
    
    public int getSourceFileSize(){
        return sourceCode.size();
    }
    
    public void printSourceCode(int startLine, int endLine, int currentLine){
        System.out.println();
        for(int i = startLine; i < endLine; i++){
            if(sourceCode.get(i).isBreakPoint()) System.out.print("**");
             
            if(i == currentLine-1){
             System.out.println(sourceCode.get(i).getLineNumber()+ ". " + sourceCode.get(i).getLineCode() + "  <---------");      
            } else{
             System.out.println(sourceCode.get(i).getLineNumber()+ ". " + sourceCode.get(i).getLineCode());
            }
         }
    }
    
    
    public String displayCurrentFunctionID(){
        FunctionEnvironmentRecord record = environmentStack.peek();
        return record.getFunctionId();
    }   
    
    
    public void displayCurrentFunction(){
        
        int currentLine = environmentStack.peek().getCurrentLine(); 
        int funcStart = environmentStack.peek().getLineStart();
        int funcEnd = environmentStack.peek().getLineEnd();
        printSourceCode(funcStart-1,funcEnd,currentLine);
    }
    
    public void displayCurrentFunctionVars(){
        
        FunctionEnvironmentRecord record = environmentStack.peek();
        String key; 
        int value;
       
        
        Set<String> tableSet= record.getKeySet();
        Iterator iterator= tableSet.iterator();
        
        while(iterator.hasNext()){
            key= (String)(iterator.next());  
            value = record.getOffsetValue(key);
            value = runStack.getAtValue(value);
            System.out.print(key + ": "+ value + " ");            
        }
        
        System.out.println();
    } 
}
