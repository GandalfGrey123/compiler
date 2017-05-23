package interpreter;

import debuggingconsoleui.UserInterfaceDB;
import java.io.*;
import bytecodeloader.*;
import program.*;
import vm.*;
import codetable.*;
import debugger.*;

/**
 * <pre>
 *
 *     Interpreter class runs the interpreter:
 *     1. Perform all initializations
 *     2. Load the bytecodes from file
 *     3. Run the virtual machine
 *
 * </pre>
 */


public class Interpreter {

    ByteCodeLoader bcl;
    private boolean debugOn;
    
    private String fileName;
    private String byteCodeFile;
    private String xSourceFile;
    private String debugByteCode;

    public Interpreter(String codeFile, boolean debugSwitch) {
        
        
        //create source file and byte code file name
        fileName = codeFile;
        xSourceFile = (fileName + ".x");
        byteCodeFile = (fileName + ".x.cod");
        debugByteCode = (fileName + ".x.debug.cod");
        
        
        try {
            
        debugOn = debugSwitch;    
            
            if (codeFile.isEmpty()) {
                throw new IOException();
            } else {
                
               //normal execution
               if(debugOn == false){ 
                CodeTable.init();
                bcl = new ByteCodeLoader(byteCodeFile);
               }
               
               //init commandline and setup debug envorinment
               if(debugOn == true){
                CodeTable.initDebugCodes();
                bcl = new ByteCodeLoader(debugByteCode);
               }
            }
            
        }catch(IOException e){System.out.println("**** " + e);}
    }

	void run() {
		Program program = bcl.loadCodes();
		VirtualMachine vm = new VirtualMachine(program);
		vm.executeProgram();
	}
        
        void runDebugMode() {         
            
                //load byte codes so VM can execute 
                Program program = bcl.loadCodes();
                DebuggerVM dvm = new DebuggerVM(program);
                
                //send debugger the xSourceFile so it can provide interface feedback
                UserInterfaceDB debuggerConsole = new UserInterfaceDB(dvm,xSourceFile);
                debuggerConsole.executeProgram();
        }

	public static void main(String args[]) {
		
                //check if user supplied file to interpret
                if (args.length == 0) {
			System.out.println("***Incorrect usage, try: java interpreter.Interpreter <file>");
			System.exit(1);
		} 
                  
                //CASE 1 , non debug mode
                //program executed in regular mode (java -jar <file>) 
                if(args.length == 1){
                 (new Interpreter(args[0], false)).run();  
                
                //check to see if there is more commands with file
                } else if(args.length == 2){
                    
                 //CASE 2 , debug mode
                 //program executed in debug mode (java -jar -d <file>) 
                 if(args[0].equals("-d")) {
                        (new Interpreter(args[1], true)).runDebugMode();
                 }
                 
                 //incorrect command
                 else { 
                         System.out.println("***Incorrect usage, do you want to "
                         + "run debugger? try: java interpreter.Interpreter <file> -d");
                 }
                 }	
	}
}
