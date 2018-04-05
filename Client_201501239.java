import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry;  
import java.util.Formatter;
import java.lang.*;
import java.io.*;
public class Client_201501239 {  
    private Client_201501239() {}  
    public static void main(String[] args) {  
        try {  
            // Getting the registry 
            Registry registry = LocateRegistry.getRegistry(null); 

            // Looking up the registry for the remote object 
            Interface_201501239 stub = (Interface_201501239) registry.lookup("Hell"); 

            // Calling the remote method using the obtained object 
            //stub.printMsg(); 
            int choice= Integer.parseInt(args[0]);
            if(choice==1)
            {
                // String s=sc.nextLine();
                //  System.out.println(s);
                // System.out.println("Primality of "+args[1] + " is "+ decrypt(stub.primality_test(encrypt(args[1]))));
                //stub.deposit()
                int id =stub.deposit(Long.parseLong(args[1]),Integer.parseInt(args[2]));
                float ans=stub.balance(Long.parseLong(args[1]),1);
                System.out.println("Succesfully depsoited " +args[2] +" Rupees and the updated balance is " +ans);
                System.out.println("Id of the transaction is "+id);

            }
            else if(choice==2)
            {
                //   String s=sc.nextLine();
                int id=stub.withdraw(Long.parseLong(args[1]),Integer.parseInt(args[2]));
                if(id!=-1){
                float ans=stub.balance(Long.parseLong(args[1]),1);
                System.out.println("Succesfully withdrawed " +args[2] +" Rupees and the updated balance is " +ans);
                System.out.println("Id of the transaction is "+id);
                }
                else
                {
                    System.out.println("Amount not sufficient to withdraw the asked withdraw amount"); 
                }
            }
            else if(choice==3)
            {
                // String s=sc.nextLine();
                float ans=stub.balance(Long.parseLong(args[1]),2);
                System.out.println("Balance of the account "+args[1] +" is " +ans);
                //System.out.println("Fibanocci Number of "+args[1]+" is "+decrypt(stub.fibanocci(encrypt(args[1])))+". ");

            }
            else if(choice==4)
            {
                //   String s=sc.nextLine();
                String str = stub.trans_de(Long.parseLong(args[1]),args[2],args[3]);
                print_tabular(str);
              //  System.out.println(stub.trans_de(Long.parseLong(args[1]),args[2],args[3]));
                //  System.out.println("After reversing the cases of "+args[1]+":- Obtained string is "+decrypt(stub.reverseCase(encrypt(args[1]))));            
            }
            else if(choice==5)
            {
                String str = stub.complete_de(Long.parseLong(args[1]));
               // System.out.println(str);
                print_tabular(str);
            }
            else{
                System.exit(0);
            }

            //   stub.deposit(9491334650L,100);
            // System.out.println(stub.balance(9491334650L));
            // System.out.println("Remote method invoked"); 
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString()); 
            e.printStackTrace(); 
        } 
    }
    public static void print_tabular(String str)
    {
        if(str.length()==0)
        {
            System.out.println("No Transactions found");
            System.exit(0);
        }
        String[] str_s = str.split("\n");
       // int le=str_s.length();
        String str3 = "| Id  || TimeStamp of Transaction ||  Amount   || Transaction Type |";
        String str4 = "--------------------------------------------------------------------";
             System.out.println(str3);
             System.out.println(str4);
        for(String string : str_s)
        {
         //    String s=str_s[i];
          //  string = string.replace(" ", "|");
             String[] s1 = string.split("[\\|]");
          //   for (String sw : s1)
            //    System.out.println(sw);
           //  int le1=s1.length();
             System.out.format("|%1$-5d||%2$-26s||  %3$-10d||%4$-19s|\n", Integer.parseInt(s1[0]), s1[1], Integer.parseInt(s1[2]),s1[3]);
        }
    } 
}
