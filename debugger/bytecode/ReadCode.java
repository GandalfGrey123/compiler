package bytecode;
import java.util.Scanner;
import vm.*;

public class ReadCode extends ByteCode {

    private String opCode;

    @Override
    public String getInstruction(){   
    return opCode;
    }
  
    
    @Override
    public String getCode() {
        return opCode;
    }

    @Override
    public void init(String[] args) {
        opCode = args[0];
    }

    //get input from user and put on stack
    @Override
    public void execute(VirtualMachine vm) {
        Scanner in = new Scanner(System.in);
        System.out.print("input a integer: ");
        int num = in.nextInt();
        vm.getNewItem(num);
    }
}
