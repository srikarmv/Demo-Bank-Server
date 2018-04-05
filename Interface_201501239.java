import java.rmi.Remote; 
import java.rmi.RemoteException;  

// Creating Remote interface for our application 
public interface Interface_201501239 extends Remote {  
    // public void df() throws RemoteException;
    public int hash_map(long account_number) throws RemoteException;
    public int deposit(long account_number,int money) throws RemoteException;
    public int withdraw(long account_number,int money) throws RemoteException;
    public float balance(long account_number,int flag) throws RemoteException;
    public String trans_de(long account_num,String start,String end) throws RemoteException;
    public String complete_de(long account_number) throws RemoteException;
} 
