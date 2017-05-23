package vm;

import program.*;
import bytecode.*;
import java.util.*;

public class VirtualMachine {

    private RunTimeStack runStack;
    private Stack<Integer> returnAddrs;
    private int pc;

    private boolean dumpSwitch;
    private Program runProgram;
    

    public VirtualMachine(Program byteCodes) {
        runProgram = byteCodes;
        runStack = new RunTimeStack();
        returnAddrs = new Stack();
        pc = 0;
    }

    public void executeProgram() {

        while (isRunning()) {
           
           if(dumpSwitch == true)
               if(!(runProgram.getNextCode(pc).getInstruction().equals("DUMP OFF"))){
                   dump();
               } else{
                      runProgram.getNextCode(pc).execute(this);
               }
           else{
            runProgram.getNextCode(pc).execute(this);
           }
           pc++;
        }
    }
    
    private boolean isRunning(){
    
     return (pc<runProgram.getSize());
    }
    
    public void store(int offset) {
        runStack.store(offset);
    }

    public void load(int offset) {
        runStack.load(offset);
    }

    public void loadImediate(int n) {
        runStack.loadImediate(n);
    }

    public void popLevels(int level) {
        runStack.popLevels(level);
    }

    public void getNewItem(int item) {
        runStack.push(item);
    }

    public void write() {
        runStack.write();
    }

    public void binOp(String operator) {
        runStack.runStackBop(operator);
    }

    public int popRunStack() {
        return runStack.pop();
    }

    public void newFrame(int offset) {
        runStack.newFrameAt(offset);
    }

    public void saveReturn() {
        returnAddrs.add(pc + 1);
    }

    public int getReturn() {
        return returnAddrs.pop();
    }

    public void setPC(int n) {
        pc = n;
    }

    public void setDumpSwitch(boolean state) {
        dumpSwitch = state;
    }
    
    private void dump(){
    System.out.println(runProgram.getNextCode(pc).getInstruction());
    runProgram.getNextCode(pc).execute(this);
    runStack.dumpStack();
    }
    
    public void popFrame(){
     runStack.popFrame();
    }
}
