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
	public Boolean joinServer( String serverName, ClientInterface client ) throws RemoteException;
	public Boolean leaveServer( String userName, String serverName ) throws RemoteException;
	public List<String> getAllServerUsers() throws RemoteException;
	public String getThingsAtLocation( String userName ) throws RemoteException;
	public Boolean moveUser( String userName, String direction ) throws RemoteException;
	public String takeItem( String 
	public Boolean messageTo(String fromUser, String toUser, String messageString) throws RemoteException;
}
