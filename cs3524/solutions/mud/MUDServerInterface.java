/*	Author: Marcel Zak
*	version: 0.0
*/

package cs3524.solutions.mud;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MUDServerInterface extends Remote
{
    
	public List<String> listServers() throws RemoteException;
	public boolean joinServer( String serverName, ClientInterface client ) throws RemoteException;
	public boolean leaveServer( String clientUserName ) throws RemoteException;
	public boolean moveUser( String clientUserName, String position ) throws RemoteException;
	public boolean getThing( String clientUserName, String thing ) throws RemoteException;
}
