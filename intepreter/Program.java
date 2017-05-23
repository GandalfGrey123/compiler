package program;

import bytecode.*;
import java.util.*;

public class Program {

    private List<ByteCode> program;

    public Program() {
        program = new ArrayList();
    }

    //resolving the branch addresses  
    public void resolveAddresses(HashMap<String, String> labels) {
        
        String [] args = new String[2];
        
        //iterate through program resolving branch addresses
        for (int i = 0; i < (program.size() - 1); i++) {
            //if next instruction is a branching code, convert label to line number              
            if ((program.get(i).getCode()).equals("FALSEBRANCH")
                    | (program.get(i).getCode()).equals("GOTO")
                    | (program.get(i).getCode()).equals("CALL")){
                
               args[0] = program.get(i).getCode();       
               args[1] = labels.get( program.get(i).getLabel() );   
               program.get(i).init(args);
            }

        }
    }

    public void addCode(ByteCode newCode) {
        program.add(newCode);
    }
    
    
    public void printProgram(){
    for(int i =0; i < (program.size());i++){
     System.out.println(program.get(i).getCode());   
    }
    }
    
    public ByteCode getNextCode(int counter){
     return program.get(counter);
    }
    
    public int getSize(){
    return program.size();
    }
    
}
