package vm;

import program.*;
import bytecode.*;
import java.util.*;

public class VirtualMachine {

    protected RunTimeStack runStack;
    private Stack<Integer> returnAddrs;
    protected int pc;

    private boolean dumpSwitch;
    private Program runProgram;
    

    public VirtualMachine(Program byteCodes){
        runProgram = byteCodes;
        runStack = new RunTimeStack();
        returnAddrs = new Stack();
        pc = 0;
        
        
    }

    public void executeProgram(){

        while (isRunning()){          
           ByteCode nextCode = runProgram.getNextCode(pc);
           nextCode.execute(this);
           dump(nextCode);          
           pc++;
        }
    }
    
    private boolean isRunning(){
     return (pc<runProgram.getSize());
    }
    
    public void store(int offset){
        runStack.store(offset);
    }

    public void load(int offset){
        runStack.load(offset);
    }

    public void loadImediate(int n){
        runStack.loadImediate(n);
    }

    public void popLevels(int level){
        runStack.popLevels(level);
    }

    public void getNewItem(int item){
        runStack.push(item);
    }

    public void write(){
        runStack.write();
    }

    public void binOp(String operator){
        runStack.runStackBop(operator);
    }

    public int popRunStack(){
        return runStack.pop();
    }
    

    public void pushFrame(int offset){
        runStack.newFrameAt(offset);
    }
    
    public void popFrame(){
     runStack.popFrame();
    }

    public void saveReturn(){
        returnAddrs.add(pc);
    }

    public int getReturn(){
        return returnAddrs.pop();
    }
    
    public int whatIsRunStackSize(){
        return runStack.getStackSize();
    }

    public void setPC(int n){
        pc = n;
    }

    public void setDumpSwitch(boolean state){
        dumpSwitch = state;
    }
    
    private void dump(ByteCode code){
    System.out.println(code.getInstruction());
    runStack.dumpStack();
    }
    
   public int currentFrameSize(){
    return runStack.lastStackFrameSize();
   }
    
    
}
