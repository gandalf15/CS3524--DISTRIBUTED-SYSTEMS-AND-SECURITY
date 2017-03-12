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
	public String joinServer( String serverName, ClientInterface client ) throws RemoteException;
	public String leaveServer( String userName, String serverName ) throws RemoteException;
	public Boolean moveUser( String userName, String direction ) throws RemoteException;
	public String getThing( String userName ) throws RemoteException;
}
