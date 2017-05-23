
package codetable;

public class CodeTable {
    
    
   private static java.util.HashMap<String,String> opCodes = new java.util.HashMap<String,String>(); 
    
    public static String get(String code){
    return opCodes.get(code);
    }
    
    public static void init(){    
        opCodes.put("HALT","HaltCode");
        opCodes.put("FALSEBRANCH","FalseBranchCode" );
        opCodes.put("GOTO","GoToCode");
        opCodes.put("STORE","StoreCode" );
        opCodes.put("LOAD","LoadCode" );
        opCodes.put("LIT","LitCode" );
        opCodes.put("ARGS","ArgsCode");
        opCodes.put("CALL","CallCode" );
        opCodes.put("RETURN","ReturnCode" );
        opCodes.put("BOP","BopCode" );
        opCodes.put("POP", "PopCode");
        opCodes.put("READ","ReadCode" );
        opCodes.put("DUMP", "DumpCode");
        opCodes.put("WRITE","WriteCode" );
        opCodes.put("LABEL","LabelCode" );
    }
}
