package cs3524.solutions.mud;

import java.util.List;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends java.rmi.Remote {
	public String getWorld() throws java.rmi.RemoteException;
	public String setWorld(String newWorld) throws java.rmi.RemoteException;

	public List<String> getThings() throws java.rmi.RemoteException;
	public void carry(String thing) throws java.rmi.RemoteException;
	public Boolean drop(String thing) throws java.rmi.RemoteException;

	public void printmess(String mess) throws java.rmi.RemoteException;

	public String getName() throws java.rmi.RemoteException;

	public String getLocation() throws java.rmi.RemoteException;

	public String setLocation(String newlocation) throws java.rmi.RemoteException;
	public void print(String output) throws java.rmi.RemoteException;
}
