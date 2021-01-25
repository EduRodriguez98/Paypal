package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPayPal extends Remote {
	public boolean validatePayment(String email, String password) throws RemoteException;
}
