/*	Author: Marcel Zak
*	version: 0.0
*/
package cs3524.solutions.mud;

import java.rmi.*; 
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;


public class MUDServerImpl implements MUDServerInterface {

	private Integer maxPlayers = 2;	

	private Map<String, MUD> serversMap = new HashMap<String, MUD>(); 
	private	Map<String, String> clientToServerMap = new HashMap<String, String>();
	private	Map<String, Map<String, ClientInterface>> serverToClientsMap = new HashMap<String, HashMap<String, ClientInterface>>();
	private Map<String, List<String>> clientInventoryMap = new HashMap<String, ArrayList<String>>();
	private Map<String, String> clientPositionMap = new HashMap<String, String>(); 

	public MUDServerImpl() throws RemoteException
	{ 
		serversMap.put("Hades", new MUD("servers/hades/hades.edg","servers/hades/hades.msg","servers/hades/hades.thg"));
		Map<String, ClientInterface> clientsMap = new HashMap<String, ClientInterface>();
		serverToClientsMap.put( "Hades", clientsMap);
		serversMap.put("Pathos", new MUD("servers/pathos/pathos.edg","servers/pathos/pathos.msg","servers/pathos/pathos.thg"));
		clientsMap = new HashMap<String, ClientInterface>();
		serverToClientMap.put( "Pathos", clientsMap);
	}

	public List listServers() throws RemoteException {
		List<String> serverList = servers.keySet();
		return serverList;
	}
	
	public String joinServer(String serverName, ClientInterface client) throws RemoteException
	{
		MUD server = serversMap.get(serverName);
		String clientUserName = client.getUserName();
		Map<String, ClientInterface> clientsMap;
		if (server == null)
		{
			serversMap.put(clientUserName+"'s server", MUD("servers/void/void.edg","servers/void/void.msg","servers/void/void.thg"));
			clientToServerMap.put( clientUserName, clientUserName+"'s server" );
			
			clientsMap = new HashMap<String, ClientInterface>( clientUserName, client );
			serverToClientMap.put( clientUserName+"'s server", clientsMap );

			clientPositionMap.put( clientUserName, server.startLocation() );
			
			clientInventoryMap.put( clientUserName, new ArrayList<String>() );
			
			server = servers.get(clientUserName+"'s server");
			server.addThing( server.startLocation(), "User: "+clientUserName );
	
			String message = "\n~~~Welcome to "+clientUserName+"'s Server~~~\n";
			message += "You are currently at "+clientPositionMap.get( clientUserName )+" location\n";
			return message;
	 
		}

		if(serverToClientsMap.get(serverName).size() > maxPlayers)
		{
			return "Sorry maximum players reached. Try later or join another server";
		}
		
		if ( clientToServerMap.get( clientUserName ) != null ) 
		{
			return "Change name please! User already exist!";
		}
		
		clientToServerMap.put( clientUserName, serverName );
		
		clientsMap = serverToClientsMap.get( serverName );
		clientsMap.put( clientUserName, client );
		serverToClientsMap.put( serverName, clientsMap );
		
		clientPositionMap.put( clientUserName, server.startLocation() );

		clientInventoryMap.put( clientUserName, new ArrayList<String>() );
		
		server.addThing( server.startLocation(), "User: "+clientUserName );
		
		String message = ( "\n~~~Welcome to "+serverName+" Server~~~\n" );
		message += "Current number of player on this server is "+serverToClientsMap.get( serverName ).size()+"\n";
		message += "You are currently at "+clientPositionMap.get( clientUserName )+" location\n";
		return message;
		
	}	
	
	public String leaveServer( String clientUserName ) throws RemoteException
	{
		String serverName = clientToServerMap.get( clientUserName );
		MUD server = serversMap.get( serverName );
		Map<String, ClientInterface> clientsMap = serverToClientsMap.get( serverName );
		String position = clientPositionMap.get( clientUserName );
		
		if ( serverName  == null ) 
		{ 
			return "Error the user does not exist at the server\n"; 
		}
		
		server.delThing( position  ,"User: "+clientUserName );
		clientToServerMap.remove( clientUserName );
		clientsMap.remove( clientUserName );
		serverToClientsMap.put( serverName, clientsMap );
		clientInventoryMap.remove( clientUserName );
		clientPositionMap.remove( clientUserName );	
		return "BB see you soon!";
				
	}
	
	public String moveUser(String userName, String direction) throws RemoteException
	{
		
		return "You moved";
		
	}



	public String getThing( String clientUserName, String thing) throws RemoteException
	{
		
		String serverName = clientToServerMap.get( clientUserName );
		MUD server = serversMap.get( serverName );
		Map clientsMap = serverToClientsMap.get( serverName );
		ClientInterface client = clientsMap.get( clientsUserName );
		List<String> inventory = clientInventoryMap.get( clientUserName );
		String things = server.locationInfo( clientPositionMap.get( clientUserName ) );
		String[] thingsArray = things.split("\\s+");
		for ( String t : things )
		{
			if ( thing == t )
			{
				server.delThing( clientPositionMap( clientUserName ), t );
				inventory.put( t );
				clientInventoryMap.put( clientUserName, inventory );
				return "You have: "+inventory.toString();
			}
		}
		return "You have: "+inventory.toString();
					
	}
	
}
