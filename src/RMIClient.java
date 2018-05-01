

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JOptionPane;


//pas besoin de héritager UnicastRemoteObject
public class RMIClient implements Client {
	private static Client client;
	  private Server service;
	  private String user = "Yi";
	  boolean connected=false;
	  private boolean isStandalone = false;
	public static void main(String[] args) throws Exception {
		client = new RMIClient();
		UnicastRemoteObject.exportObject(client,0);
		Server service = (Server) Naming.lookup("rmi://localhost:1099/HelloServiceImpl");
		//connect();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		while ((line = br.readLine()) != null) {			
			//faire appel la méthode distante, soi même comme paramètre
			service.sendMessage(client, line);
			//service.hello(client, line);
		}
	}
	// la méthode permet de être appelé distante 
	public void showDialog(String msg) throws java.rmi.RemoteException {		
		JOptionPane.showMessageDialog(null,msg);
		System.out.println(this.client+"dit"+msg);
	}
	@Override
	public String getName() throws RemoteException {
		// TODO Auto-generated method stub		
		    return user;		  		
	}
	@Override
	public void sendMessage(String msg) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println(msg);
	}
	
	private void connect()
	  {
	    try
	    {
	      service = (Server)Naming.lookup("rmi://localhost:1099/HelloServiceImpl");
	      connected=true;
	      service.addClient(this,user+"连入");
	    }
	    catch(Exception e)
	    {
	      
	      connected=false;	      
	      service=null;
	      System.out.println(e);
	    }
	  }
}