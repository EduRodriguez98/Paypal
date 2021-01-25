package server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class PayPal extends UnicastRemoteObject implements IPayPal {
	
	private static final long serialVersionUID = 1L;
	public static HashMap<String, String> users = new HashMap<String, String>();
	
	protected PayPal() throws RemoteException {
		users.put("mireya.quintana@opendeusto.es", "1111");
	}

	public boolean validatePayment(String username, String password) throws RemoteException {
		
		if(users.containsKey(username) && users.containsValue(password)) {
			// correct login details
			return true;
		} else {
			// non-existent user details or wrong user login details
			return false;
		}
	}
	
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("usage: java [policy] [codebase] server.Server [host] [port] [server]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {		
			IPayPal paypalServer = new PayPal();
			Naming.rebind(name, paypalServer);
			System.out.println("* Server '" + name + "' active and waiting...");
		} catch (Exception e) {
			System.err.println("- Exception running the server: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
