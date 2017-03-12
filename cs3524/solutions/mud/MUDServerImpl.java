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
	private	Map<String, List<StringClientInterface>> serverToClientsMap = HashMap<String, ArrayList<ClientInterface>>();

	private Integer getClientIndex( userName )
	{
		String serverName = clientToServerMap.get( userName );
		List<ClientInterface> clientsList = serverToClientsMap.get( serverName );
		if ( serverName == null || clientToServer.get( userName ) != serverName) 
		{ 
			return null
		}
		
		int i = 0;
		for ( client : clientsList)
		{
			if ( client.getUserName() == userName)
			{
				return i;
			}
			++i;
		}
		return null;
	}
				



if (serverName != null)
		{
			List clientsList = serverToClientsMap.get( serverName );
			ClientInterface client = 
		}
		return null;
	}

	public MUDServerImpl() throws RemoteException
	{ 
		serversMap.put("Hades", new MUD("servers/hades/hades.edg","servers/hades/hades.msg","servers/hades/hades.thg"));
		List<ClientInterface> clientsList = new ArrayList<ClientInterface>();
		serverToClientsMap.put( "Hades", clientsList);
		List<ClientInterface> clientsList2 = new ArrayList<ClientInterface>();
		serversMap.put("Pathos", new MUD("servers/pathos/pathos.edg","servers/pathos/pathos.msg","servers/pathos/pathos.thg"));
		serverToClientMap.put( "Pathos", clientsList2);
	}

	public List listServers() throws RemoteException {
		List<String> serverList = servers.keySet();
		return serverList;
	}
	
	public String joinServer(String serverName, ClientInterface client) throws RemoteException
	{
		MUD server = serversMap.get(serverName);
		String clientUserName = client.getUserName();
		List<ClientInterface> clientsList;
		if (server == null)
		{
			serversMap.put(clientUserName+"'s server", MUD("servers/void/void.edg","servers/void/void.msg","servers/void/void.thg"));
			clientToServerMap.put( clientUserName, clientUserName+"'s server" );
			clientsList = new ArrayList<ClientInterface>(client);
			serverToClientMap.put( clientUserName+"'s server", clientsList );
			
			server = servers.get(clientUserName+"'s server");
			client.setLocation( server.startLocation() );
			server.addThing( server.startLocation() );
	
			String message = ( "\n~~~Welcome to "+clientUserName+"'s Server~~~\n" );
			return message;
	 
		}

		if(serverToClientsMap.get(serverName).size()>maxPlayers)
		{
			return "Sorry maximum players reached. Try later or join another server"
		}
		
		if ( clientToServerMap.get( clientUserName ) != null ) 
		{
			return "Change name please! User already logged in!"
		}
		
		client.setLocation( server.startLocation() );
		server.addThing( server.startLocation(), "User: "+clientUserName );
		clientsList = serverToClientsMap.get( serverName ); 
		clientsList.put( client );
		serverToClientsMap.put( serverName, clientsList );
		clientToServerMap.put( clientUserName, serverName  );
		
		String message = ( "\n~~~Welcome to "+serverName+" Server~~~\n" );
		message += "Current number of player on this server is "+currentNumberOfPlayers.get(serverName);
		return message;
		
	}	
	
	public Boolean leaveServer( String userName, String serverName ) throws RemoteException
	{
		
		MUD server = serversMap.get( serverName );
		List<ClientInterface> clientsList = serverToClientsMap.get( serverName );
		if (clientToServerMap.get( userName ) == null || clientToServer.get( userName ) != serverName) 
		{ 
			return false 
		}
		
		int i = 0;
		for ( client : clientsList)
		{
			if ( client.getUserName() == userName)
			{
				server.delThing( client.getLocation()  ,"User: "+userName );
				clientList.remove(i);
				serverToClientsMap.put( serverName, clientList );
				clientToServerMap.remove( userName );
				return true;
			}
			++i;
		}
		
		return false;
		
	}
	
	public String moveUser(String userName, String direction) throws RemoteException
	{
		
		
	}



	public String getThingsAtLocation( String userName ) throws RemoteException
	{
		
		String serverName = clientToServerMap.get( userName );
		MUD server = servers.get( serverName );
		List clientsList = serverToClientsMap.get( serverName );
		ClientInterface client = clientsList.get(getClientIndex( userName ));
		String things = server.locationInfo( client.getLocation() );
		return things;
					
	}

	
	public boolean takeItem(String item, String location) throws RemoteException {
		Vertex currentVertex = m.getVertex(location);
		List<String> things = currentVertex._things;		
		if(things.contains(item)){
			m.delThing(location, item);
			
			return true;
		}
		
		return false;
	}
	
	public void changeMUD(String name) throws RemoteException {
		//System.out.println("Server is changing to " + name);
		m = servers.get(name);
		
	}
	
	public String getServers() throws RemoteException{
	
		StringBuilder sb = new StringBuilder();
		Iterator it = servers.keySet().iterator();
		
		while(it.hasNext()){
			sb.append(it.next().toString());
			sb.append(", ");
		}
		
		sb.setLength(sb.length() - 2);
		
		return "Currently running servers: " + sb.toString();
	}

	
	
}
