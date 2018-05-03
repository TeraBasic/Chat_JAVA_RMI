
import java.rmi.*;
public interface Server extends Remote {
	// 定义允许远程调用的方法，第一个形参类型是Client
	void hello(Client client, String saying) throws Exception;
	
	public void addClient(Client c,String msg) throws RemoteException;
	
	public void removeClient(Client c,String msg) throws RemoteException;
	
	public void sendMessageGroup(Client c,String msg) throws RemoteException;
	
	public void sendMessageSeul(Client c, String client, String msg) throws RemoteException;

}