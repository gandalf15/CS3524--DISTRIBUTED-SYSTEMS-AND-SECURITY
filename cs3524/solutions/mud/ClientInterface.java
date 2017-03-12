package cs3524.solutions.mud;

import java.util.List;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote 
{
	public String getUserName() throws RemoteException;
	public void message( String message ) throws RemoteException;
	
}
