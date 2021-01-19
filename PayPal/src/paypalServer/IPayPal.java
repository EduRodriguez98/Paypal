package paypalServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPayPal extends Remote{

	public boolean makePaymentPaypal(String username, String password, int reservationNum, int amount) throws RemoteException;
}
 