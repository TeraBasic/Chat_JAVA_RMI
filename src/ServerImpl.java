
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.*;
 
public class ServerImpl extends UnicastRemoteObject implements Server {
	
	// pour stocker tous les clients
	static List<Client> users = new ArrayList<Client>();
	 
	public ServerImpl() throws RemoteException {
	}
	 
	// 该方法中有一个形参是Client类型
	// 通过这种方式让服务器获得远程客户端对象的引用
	public void hello(Client cm, String saying) throws Exception {
		if (!users.contains(cm)) {
			users.add(cm);
		}
		try {
			java.util.Date now = new java.util.Date();
			String clientt = cm.toString();
			String msg = clientt + now + saying;
			// 依次调用连接到该服务器的每个客户端的showDialog方法
			// faire appele la méthodes distantes des clients sur le serveur
			for (Client c : users) {
			// faire appele la méthodes distantes
			c.showDialog(msg);
			}
		} catch (RemoteException ex) {
			users.remove(cm);
			ex.printStackTrace();
		}
	}
	 
	public static void main(String args[]) throws Exception {		
		// register la port de RMI
		LocateRegistry.createRegistry(1099);
		Server remote = new ServerImpl();
		//将远程服务对象绑定到指定JNDI。
		Naming.rebind("rmi://localhost:1099/HelloServiceImpl", remote);
	}

	@Override
	public void addClient(Client c, String msg) throws RemoteException {
		// TODO Auto-generated method stub
		if(!users.contains(c)){
			users.add(c);
			for(int i=0;i<users.size();i++) {	      
	        //sendMessage((Client)users.get(i),msg);
				System.out.println(users.get(i).getName());
	      }
	      //users.add(c);
	    }
	}

	@Override
	public void removeClient(Client c, String msg) throws RemoteException {
		// TODO Auto-generated method stub
		 if(users.contains(c))
		    {
		      for(int i=0;i<users.size();i++)
		      {
		        //sendMessage((IChatClient)clients.elementAt(i),msg);
		        ( (Client) users.get(i)).afficherMessage(msg);
		      }
		      users.remove(c);
		    }
	}

	@Override
	public void sendMessageGroup(Client cm, String msg) throws RemoteException {
		// TODO Auto-generated method stub
		if(!users.contains(cm)) {
			users.add(cm); 
			} 			
		//envoyes les message vers tous les utilisateur
		try {
			for (Client c : users) {
		
			// 回调远程客户端方法
			String user=cm.getName(); 
	      if(user==null || user=="") {
	    	  user = "anonymous";
	      }	        
	      c.afficherMessage(user + " : " + msg);
	      //c.showDialog(msg);
			}
		} catch (RemoteException ex) {
			users.remove(cm);
			ex.printStackTrace();
		}
		/*
		    for(int i=0;i<users.size();i++)
		    {
		      String user=c.getName(); 
		      if(user==null || user=="")
		        user = "anonymous";
		      ( (Client) users.get(i)).sendMessage(user + " : " + msg);
		    }*/
  }

	@Override
	public void sendMessageSeul(Client cm, String Nomclient, String msg) throws RemoteException {
		// TODO Auto-generated method stub
		if(!users.contains(cm)) {
			users.add(cm); 
			} 			
		//envoyes les message vers un utilisateur
		try {
			for (Client c : users) {
		
			  // 回调远程客户端方法
			  String user=cm.getName(); 
		      if(user==null || user=="") {
		    	  user = "anonymous";
		      }	 
		      if (c.getName().equals(Nomclient)) {
		    	  c.afficherMessage(user + " : " + msg);
		      }		      
		      //c.showDialog(msg);
			}
		} catch (RemoteException ex) {
			users.remove(cm);
			ex.printStackTrace();
		}
	}
	
}