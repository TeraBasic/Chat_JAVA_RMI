

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import javax.swing.JOptionPane;

import calssdep.Profil;


//pas besoin de héritager UnicastRemoteObject
public class RMIClient implements Client {
	  private static Client client;
	  private Profil profil;
	  private Server service;
	  private static String user = "Yi";
	  private static String  code;
	  boolean connected=false;
	  static boolean estClient;
	  private boolean isStandalone = false;
	public static void main(String[] args) throws Exception {
		if (estClient){
			login();			
		}
		else {
			
			inscription();
		}		
	}
	//inscription
	public static void inscription() throws MalformedURLException, RemoteException, NotBoundException {
		//inscription();
		System.out.println("Input le nom : ");
        Scanner sc = new Scanner(System.in); 
		user = sc.nextLine();
		System.out.println("Input le code : ");
        Scanner sc2 = new Scanner(System.in); 
		code = sc2.nextLine();
		client = new RMIClient();
		try {
			UnicastRemoteObject.exportObject(client,0);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Server service = (Server) Naming.lookup("rmi://localhost:1099/HelloServiceImpl");
		service.addClient(client, "je suis nouveau clien !");
	}
	//login
	public static void login() throws RemoteException {
		client = new RMIClient();
		UnicastRemoteObject.exportObject(client,0);
		Server service = null;
			try {
				service = (Server) Naming.lookup("rmi://localhost:1099/HelloServiceImpl");
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		//connect();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		try {
			while ((line = br.readLine())!= null) {			
				//faire appel la méthode distante, soi même comme paramètre
				//service.sendMessageGroup(client, line);
				service.sendMessageSeul(client, "Yi", line);
				//service.hello(client, line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
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
	public void afficherMessage(String msg) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println(msg);
	}
	/**
	 * 	
	 */
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