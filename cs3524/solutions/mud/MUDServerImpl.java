/*	Author: Marcel Zak
*	version: 0.0
*/
package cs3524.solutions.mud;

import java.rmi.*; 
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;


public class MUDServerImpl implements MUDServerInterface {

	private Integer maxPlayers = 2;	

	private Map<String, MUD> serversMap = new HashMap<String, MUD>(); 
	private	Map<String, String> clientToServerMap = new HashMap<String, String>();
	private	Map<String, HashMap<String, ClientInterface>> serverToClientsMap = new HashMap<String, HashMap<String, ClientInterface>>();
	private Map<String, ArrayList<String>> clientInventoryMap = new HashMap<String, ArrayList<String>>();
	private Map<String, String> clientPositionMap = new HashMap<String, String>(); 

	public MUDServerImpl() throws RemoteException
	{ 
		serversMap.put("Hades", new MUD("servers/hades/hades.edg","servers/hades/hades.msg","servers/hades/hades.thg"));
		HashMap<String, ClientInterface> clientsMap = new HashMap<String, ClientInterface>();
		serverToClientsMap.put( "Hades", clientsMap);
		serversMap.put("Pathos", new MUD("servers/pathos/pathos.edg","servers/pathos/pathos.msg","servers/pathos/pathos.thg"));
		clientsMap = new HashMap<String, ClientInterface>();
		serverToClientsMap.put( "Pathos", clientsMap);
	}

	public List<String> listServers() throws RemoteException {
		Set<String> serversSet = serversMap.keySet();
		return new ArrayList<String>(serversSet);
	}
	
	public String joinServer(String serverName, ClientInterface client) throws RemoteException
	{
		MUD server = serversMap.get(serverName);
		String clientUserName = client.getUserName();
		HashMap<String, ClientInterface> clientsMap;
		if (server == null)
		{
			serversMap.put(clientUserName+"'s server", new MUD("servers/void/void.edg","servers/void/void.msg","servers/void/void.thg"));
			clientToServerMap.put( clientUserName, clientUserName+"'s server" );
			
			clientsMap = new HashMap<String, ClientInterface>();
			clientsMap.put( clientUserName, client ); 
			serverToClientsMap.put( clientUserName+"'s server", clientsMap );

			clientPositionMap.put( clientUserName, server.startLocation() );
			
			clientInventoryMap.put( clientUserName, new ArrayList<String>() );
			
			server = serversMap.get(clientUserName+"'s server");
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
		HashMap<String, ClientInterface> clientsMap = serverToClientsMap.get( serverName );
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
	
	public String moveUser(String userName, String position) throws RemoteException
	{
		
		return "You moved";
		
	}



	public String getThing( String clientUserName, String thing) throws RemoteException
	{
		
		String serverName = clientToServerMap.get( clientUserName );
		MUD server = serversMap.get( serverName );
		HashMap<String, ClientInterface> clientsMap = serverToClientsMap.get( serverName );
		ClientInterface client = clientsMap.get( clientUserName );
		ArrayList<String> inventory = clientInventoryMap.get( clientUserName );
		String things = server.locationInfo( clientPositionMap.get( clientUserName ) );
		String[] thingsArray = things.split("\\s+");
		for ( String t : thingsArray )
		{
			if ( thing == t )
			{
				server.delThing( clientPositionMap.get( clientUserName ), t );
				inventory.add( t );
				clientInventoryMap.put( clientUserName, inventory );
				return "You have: "+inventory.toString();
			}
		}
		return "You have: "+inventory.toString();
					
	}
	
}
