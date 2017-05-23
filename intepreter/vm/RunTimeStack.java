package vm;

import java.util.*;

public class RunTimeStack {

    private Vector<Integer> runStack;
    private Stack<Integer> framePointer;
    private int top;

    public RunTimeStack() {
        framePointer = new Stack();
        runStack = new Vector();

        framePointer.push(0);
        top = -1;
    }
    
    public int getStackSize(){
    return runStack.size();
    }

    public void write() {
        System.out.println("Writing to console: " + runStack.lastElement());
    }

    public void store(int offset) {
        int temp = this.pop();
        int currentFrame = framePointer.lastElement();
        runStack.insertElementAt(temp, (currentFrame + offset));
    }

    public void load(int offset) {
        if (!framePointer.isEmpty()) {
            int currentFrame = framePointer.lastElement();
            offset = offset + currentFrame;
            int temp = runStack.get(offset);
            runStack.add(temp);
        } else {
            runStack.add(offset, runStack.get(offset));
        }
    }

    public int push(int item) {
        runStack.add(item);
        return item;
    }

    public void loadImediate(int n) {
        runStack.add(n);
    }

    public Integer push(Integer item) {
        runStack.add(item);
        return item;
    }

    public int pop() {
        int temp = runStack.lastElement();
        runStack.remove(runStack.size()-1);
        return temp;
    }

    public void popLevels(int levels) {
        for (int i = 0; i < levels; i++) {
         pop();
        }
    }

    public int peek() {
        return runStack.lastElement();
    }
    

    public void newFrameAt(int offset) {
        framePointer.push(runStack.size() - offset);
    }

    public void popFrame() {
        //frameStart is the index in runStack
        int frameStart = framePointer.lastElement();
        int levelsToPop = runStack.size()-frameStart;
        int returnValue = runStack.get(runStack.size()-1);
        this.popLevels(levelsToPop);
        framePointer.remove(framePointer.size()-1);
        push(returnValue);
    }
    
    
    public void runStackBop(String operator){
        
        int tempTop = this.pop();
        int bottom = this.pop();

        if (operator.equals("+")) {
            this.push(bottom + tempTop);
        } else if (operator.equals("-")) {
            this.push(bottom - tempTop);
        } else if (operator.equals("/")) {
            this.push(bottom / tempTop);
        } else if (operator.equals("*")) {
            this.push(bottom * tempTop);
        } else if (operator.equals("==")) {
            if (bottom == tempTop) {
                this.push(1);
            } else {
                this.push(0);
            }
        }else if(operator.equals("!=")){
        if (bottom != tempTop) {
                this.push(1);
            } else {
                this.push(0);
            }
        } else if (operator.equals("<=")) {
            if (bottom <= tempTop) {
                this.push(1);
            } else {
                this.push(0);
            }
        } else if (operator.equals(">")) {
            if (bottom > tempTop) {
                this.push(1);
            } else {
                this.push(0);
            }
        } else if (operator.equals(">=")) {
            if (bottom >= tempTop) {
                this.push(1);
            } else {
                this.push(0);
            }
        } else if (operator.equals("<")) {
            if (bottom < tempTop) {
                this.push(1);
            } else {
                this.push(0);
            }

        } else if (operator.equals("|")) {
            if ((bottom == 0) && (tempTop == 0)) {
                this.push(0);
            } else {
                this.push(1);
            }

        } else if (operator.equals("&")) {
            if ((bottom == 0) || (tempTop == 0)){
             this.push(0);
            }else if(bottom == 1 && tempTop == 1){
             this.push(1);
            }
        }
        
    }
    

    public void dumpStack() {
        
        if(!runStack.isEmpty()){
        //if only frame is at 0
        if (framePointer.size() == 1) {
            System.out.print("[");
            for (int i = 0; i < runStack.size(); i++) {
                System.out.print(runStack.get(i));
                if (i != (runStack.size() - 1)) {
                    System.out.print(",");
                }
            }
            System.out.println("]");
        } 
        
        //else has more than one frame
        else {
            int nextFrame = 1;        
            int frameEnd = framePointer.get(nextFrame);
            boolean hasMoreFrames = true;
            int h;
            
            
            System.out.print("[");
            //loop through stack , frameEnd is where the next frame ends
            for(int currentItem = 0; currentItem < frameEnd; currentItem++){
                
                //if currentItem is one before the next frameEnd..
                if((currentItem == (frameEnd-1)) && (hasMoreFrames == true))
                {
                    //..print the item and then print frame delimiter
                    
                    if (nextFrame == framePointer.lastElement()) {
                        //get the next frame end
                        frameEnd = runStack.size();
                        hasMoreFrames = false;
                       
                    } else {
                        frameEnd = framePointer.get(nextFrame);
                    }
                    
                    System.out.print(runStack.get(currentItem));
                    if(lastStackFrameSize() != 0){
                    System.out.print("] [");
                    }
               } 
                
                else {
                    System.out.print(runStack.get(currentItem));
                    if (currentItem != (runStack.size()- 1)) System.out.print(",");
                }
                nextFrame++;
            }
            System.out.println("]");
        }
        
    }
    }
    
    
    private int lastStackFrameSize(){
     int temp = framePointer.lastElement();
     temp = runStack.size()- temp;
     return temp;
    }
    
}
