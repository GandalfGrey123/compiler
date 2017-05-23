package bytecodeloader;

import program.*;
import codetable.CodeTable;
import bytecode.*;

import java.io.BufferedReader;
import java.io.FileReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ByteCodeLoader {
    
    private String nextLine;
    private String[] nextCode;
    private String codeClass;
    private int lineNumber;
    
    //label storage, reference to resolve branch address
    private HashMap<String,String> labels = new HashMap<String,String>(); 

    BufferedReader sourceReader;
    private Program byteCodeProgram;

    public ByteCodeLoader(String sourceFile) throws IOException {   
        sourceReader = new BufferedReader(new FileReader(sourceFile));
        byteCodeProgram = new Program();
        nextLine = "";
        lineNumber = 0;
    }

    public Program loadCodes() {
        try {
            while ((nextLine = sourceReader.readLine()) != null) {
             initByteCodeObject(nextLine);
             lineNumber++;
            }
            
            //send program object the labels so it can resolve branches
            byteCodeProgram.resolveAddresses(labels);
            
        } catch (IOException ex) {
            System.out.println("error reading bytes code" + ex);
        }
        //return program 
        return byteCodeProgram;
    }
    
    
    
    
    //creates the ByteCode objects given the next line in the source file.
    //initialize arguments
    private void initByteCodeObject(String instruction){
         try { 
          
             //seperate whitespace, grab opcode and args
            nextCode = instruction.split("\\s+");
            codeClass = CodeTable.get(nextCode[0]);
                    
            ByteCode byteCode = (ByteCode)(Class.forName("bytecode." + codeClass).newInstance());
           
           if(nextCode[0].equals("LABEL")){
          
             //associate label with line number, to resolve branch code labels
               labels.put(nextCode[1],Integer.toString(lineNumber));  
               
             //save label name in bytecode object   
              byteCode.setLabel(nextCode[1]);
              
             //label equals line number, init label object's program position
             nextCode[1] = Integer.toString(lineNumber);
             byteCode.init(nextCode);
            } 
           
            else { 
             byteCode.init(nextCode); 
            }
           
           //load into program object
            byteCodeProgram.addCode(byteCode);
            
        } catch (InstantiationException ex) {
            Logger.getLogger(ByteCodeLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ByteCodeLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ByteCodeLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
