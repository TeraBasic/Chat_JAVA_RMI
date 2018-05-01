import java.rmi.*;

//interface coté client doit aussi héritager Remote
public interface Client extends Remote
{
	
	//difini les méthodes qui permet de l'utiliser remote
	void showDialog(String msg)
		throws java.rmi.RemoteException;
	/**
	 * récupère nom de l'utilisateur
	 */
	public String getName() throws RemoteException;
	/**
	 * sent message
	 */
	public void sendMessage(String msg) throws RemoteException;
}