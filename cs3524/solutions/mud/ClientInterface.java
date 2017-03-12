package cs3524.solutions.mud;

import java.util.List;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote 
{
	public Boolean setServer( String serverName ) throws RemoteException;
	public String getUserName() throws RemoteException;
	public Boolean setLocation( String location ) throws RemoteException;
	public String getLocation() throws RemoteException;
	public Boolean setInventory( List inventory ) throws RemoteException;
	public List getInventory() throws RemoteException;
	public Boolean message( String message ) throws RemoteException;

	
}
