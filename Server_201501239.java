import java.rmi.registry.Registry; 
import java.rmi.registry.LocateRegistry; 
import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject; 
import java.util.Date;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Server_201501239 implements Interface_201501239 { 
    public  static Account_201501239 a[]={
        new Account_201501239("vinay",9491334650L,"Premium",1000)   
            ,new Account_201501239("Sridhar",9491334651L,"Premium",400)  
            ,new Account_201501239("Sairagh",9491334652L,"Premium",450)
            ,new Account_201501239("Sr",9491334653L,"Basic",500) 
            ,new Account_201501239("vi",9491334654L,"Basic",600)
    };  
    public Server_201501239() {} 
    public static void main(String args[]) { 
        try { 
            // Instantiating the implementation class 
            Server_201501239 obj = new Server_201501239(); 

            // Exporting the object of implementation class  
            // (here we are exporting the remote object to the stub) 
            Interface_201501239 stub = (Interface_201501239) UnicastRemoteObject.exportObject(obj, 0);  

            // Binding the remote object (stub) in the registry 
            Registry registry = LocateRegistry.getRegistry(); 

            registry.bind("Hell", stub);  
            System.err.println("Server ready"); 
        } catch (Exception e) { 
            System.err.println("Server exception: " + e.toString()); 
            e.printStackTrace(); 
        } 
    }
    public int hash_map(long account_number)
    {
        long a = 5L;
        return (int)(account_number%a);
    }
    public int deposit(long account_number,int money){
        int hash_val = hash_map(account_number);
        // a[hash_val].deposit(money);
        Date date = new Date();
        //String str=date.toString();

        //System.out.println(str);

        a[hash_val].update(money,date,"deposit");
        int id=a[hash_val].deposit(money);
        return id;
    }
    public String complete_de(long account_number)
    {
        return a[hash_map(account_number)].complete_details();
    }
    public int withdraw(long account_number,int money)
    {
        int hash_val=hash_map(account_number);
        if(a[hash_val].getBalance()<money){
            return -1;
        }
        else{
            //a[hash_val].update(time,money,time);
            Date date = new Date();
            // String str=date.toString();

            //System.out.println(str);
            a[hash_val].update(money,date,"withdraw");
            int id=a[hash_val].withdraw(money);
            return id;
        }

    }
    public String trans_de(long account_num,String start,String end)
    {
        Date date1,date2;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            date1 = sdf.parse(start);
            date2 = sdf.parse(end);
            String str =a[hash_map(account_num)].trans_details(date1,date2);
            System.out.println(date1);
            System.out.println(date2);
            return str;
        }
        catch(ParseException e){
            //e.ParseException();
            e.printStackTrace();
        }
        String ss="";
        return ss;
    }
    public float balance(long account_number,int flag)
    {
        int hash_val = hash_map(account_number);
        Date date=new Date();
        if(flag==2)
            a[hash_val].update(0,date,"Balance Enquiry");
        return a[hash_val].getBalance(); 
    }
}
class Account_201501239{ 
    Account_201501239(){};
    Account_201501239(String na,long acc,String type,float amou)
    {
        acc_no=acc;
        name=na;
        amount=amou;
        account_type = type;
    }  
    long acc_no;  
    String name;  
    float amount;  
    String account_type;
    //Array of String
    int[] money_trans = new int[200];
    int id=0;
    String[] meth = new String[200];
    Date[] time = new Date[200];
    //Map<String, int> myMap = new HashMap<String, int>();
    // HashMap<String, Integer> myMap = new HashMap<String, Integer>();
    // myMap.put("Jan", 1);
    // myMap.put("Feb", 2);
    // Integer k = new Integer(3);
    // myMap.put("Mar", k);
    // myMap.put("Apr", 4);myMap.put("May", 5);myMap.put("Jun", 6);
    // myMap.put("Jul", 7);myMap.put("Aug", 8);myMap.put("Sep", 9);
    // myMap.put("Oct", 10);myMap.put("Nov", 11);myMap.put("Dec", 12);
    void update(int amount,Date dat,String met)
    {
        //date[id]=dat;
        time[id]=dat;
        meth[id]=met;
        money_trans[id]=amount;
        id++;
    }
    // int compare_date(String a,String b)
    // {
    //    String[] a1 = a.split("\\s");
    //    String[] b1 = b.split("\\s");
    //    int i = myMap.get(a1[1]);
    //    int j = myMap.get(b1[0]);
    //    if(i>j)
    //       return 1;
    //    else if(i<j)
    //       return 0;
    //    else
    //    {
    //       int y=Integer.parseInt(a[2]);
    //       int h=Integer.parseInt(b[1]);
    //       if(y<h)
    //          return 0;
    //       else
    //          return 1;
    //    }
    //    return 1;
    // }
    //Thu Sep 14 19:45:31 IST 2017
    String trans_details(Date date1,Date date2)
    {
        StringBuilder data = new StringBuilder();
        for(int i=0;i<id;i++)
        {
            System.out.println(time[i]+"11212"+date1+"asdfsa"+date2+"asfa");
            System.out.println(time[i].after(date1));
            System.out.println(date2.after(time[i]));
            System.out.println(date2.equals(time[i]));

            if((time[i].after(date1) || time[i].equals(date1)) && (date2.after(time[i]) || date2.equals(time[i])))
            {

                data.append(i);
                data.append("|");
                data.append(time[i].toString());
                data.append("|");
                data.append(money_trans[i]);
                data.append("|");
                data.append(meth[i]);
                data.append("\n");
            }

        }
        return data.toString();
    }
    String complete_details()
    {
        StringBuilder data = new StringBuilder();
        for(int i=0;i<id;i++)
        {
            data.append(i);
            data.append("|");
            data.append(time[i].toString());
            data.append("|");
            data.append(money_trans[i]);
            data.append("|");
            data.append(meth[i]);
            data.append("\n");
        }
        return data.toString();
    }
    //Triplet<String,Integer,Double[]> triplet;
    void insert(int a,String n,float amt){  
        acc_no=a;  
        name=n;  
        amount=amt;  
    }  
    int deposit(float amt){  
        amount=amount+amt;  
        System.out.println(amt+" deposited");  
        return id-1;
    }  
    int withdraw(float amt){  
        if(amount<amt){  
            System.out.println("Insufficient Balance");  
            return -1;
        }else{  
            amount=amount-amt;  
            System.out.println(amt+" withdrawn");  
            return id-1;
        }  
    }  
    void checkBalance(){System.out.println("Balance is: "+amount);}
    float getBalance(){return amount;}  
    void display(){System.out.println(acc_no+" "+name+" "+amount);}  
}  

