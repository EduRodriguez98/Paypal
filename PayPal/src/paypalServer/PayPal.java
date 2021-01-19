package paypalServer;
	
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
 
import data.PaymentDTO; 

public class PayPal extends UnicastRemoteObject implements IPayPal {

	private static final long serialVersionUID = 1L;
	public static HashMap<String, String> users = new HashMap<String, String>();
	public static HashMap<String, Integer> reservations = new HashMap<String, Integer>();
	public static ArrayList<PaymentDTO> paymentsDTO = new ArrayList<PaymentDTO>();

	protected PayPal() throws RemoteException {
		users.put("mireya.quintana@opendeusto.es", "1111");
		reservations.put("mireya.quintana@opendeusto.es", 1);
		PaymentDTO payment1 = new PaymentDTO("mireya.quintana@opendeusto.es","1111",1,300);
		paymentsDTO.add(payment1);
	}


	public boolean makePaymentPaypal(String username, String password, int reservationNum, int amount) throws RemoteException {
		boolean payed = false;

		try {
			if (reservations.containsKey(username) && reservations.containsValue(reservationNum)) {
				reservations.put(username, reservationNum);
			}
			System.out.println("Payment completed: " + amount + "€");
			payed = true;

		} catch (Exception ex) {
			System.err.println(" $ Error while processing payment: " + ex);
		}
				
		return payed;
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
