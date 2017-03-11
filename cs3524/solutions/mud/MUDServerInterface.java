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
	public Boolean joinServer(String serverName) throws RemoteException;
	public Boolean leaveServer(ClientInterface client) throws RemoteException;
	public String createServer() throws RemoteException;
   	public List<String> listUsers() throws RemoteException;
	public Boolean movePlayer(ClientInterface client, String direction) throws RemoteException;
	public Boolean messageTo(String fromUser, String toUser, String messageString) throws RemoteException;
}
