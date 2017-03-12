package cs3524.solutions.mud;

import java.rmi.Naming;
import java.lang.SecurityManager;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Scanner;
/**
 * Author: Marcel Zak
 * version 0.0
 */

public class ClientMainline
{
		public static void main(String args[])
		{
		if (args.length < 3) {
			System.err.println( "Usage:\njava ClientMainline <registryhost> <registryport> <callbackport>" ) ;
			return;
		}

		try {
			String hostname = args[0];
			int registryport = Integer.parseInt( args[1] ) ;
			int callbackport = Integer.parseInt( args[2] ) ;

			System.setProperty( "java.security.policy", "mud.policy" ) ;
			System.setSecurityManager( new SecurityManager() ) ;

			String clientUserName = System.console().readLine("Your name: ").trim();


			ClientImpl newClient = new ClientImpl( clientUserName );
			ClientInterface clientStub = (ClientInterface)UnicastRemoteObject.exportObject( newClient, callbackport );

			String regURL = "rmi://" + hostname + ":" + registryport + "/MUDServer";
			MUDServerInterface serverStub = (MUDServerInterface)Naming.lookup( regURL );

			List<String> servers = serverStub.listServers();
			Integer i = 1;
			for( String srv : servers )
			{
				System.out.println("("+i+") "+srv);
				if (servers.size() == i){
					++i;
					System.out.println("("+i+") Create own server");
				}		
				++i;
			}
			
			//pick a server or create your own
			String chosenServerString = null;
			boolean response = false;
			while(chosenServerString == null)
			{
				chosenServerString = System.console().readLine("Connect to server number: ").trim();
				if (Integer.parseInt(chosenServerString) <= servers.size())
				{
					response = serverStub.joinServer(servers.get(Integer.parseInt(chosenServerString)), clientStub);
					if ( response == false ){System.exit(0);}
				}
				else if (Integer.parseInt(chosenServerString) == servers.size()+1)
				{
					response = serverStub.joinServer("new", clientStub);
					if ( response == false ){System.exit(0);}
				}
				else {
					chosenServerString = null;
					System.out.println("Invalid choice! Try again.");
				}
			}
			
			//controll
			System.out.println( "For help just type 'help'" );
			String userInput;
			while(true)
			{
				userInput = System.console().readLine("What do you want to do?\n").trim();
				
				if ( userInput.equals( "help" ) )
				{
					System.out.println( "\nview \nmove \ntake \nshow inventory \nonline users \nmessage \nexit\n" );
				}
				
				if ( userInput.equals( "view" ) )
				{
					System.out.println( "You can look (N)orth, (E)ast, (S)outh, (W)est\n" );
					userInput = System.console().readLine( "Where do you want to look?\n" ).trim();
					if ( userInput.equals("N") || userInput.equals("E") || userInput.equals("S") || userInput.equals("W") )
					{
						serverStub.view( clientUserName, userInput );
						userInput = null;
					}
				
				}
				
				if ( userInput.equals( "move" ) )
				{	
					System.out.println( "You can move (N)orth, (E)ast, (S)outh, (W)est\n" );
					userInput = System.console().readLine( "Where do you want to move?\n" ).trim();
					if ( userInput.equals("N") || userInput.equals("E") || userInput.equals("S") || userInput.equals("W") )
					{
						serverStub.moveUser( clientUserName, userInput );
						userInput = null;
					}
				}
				
				if ( userInput.equals( "take" ) )
				{
					System.out.println( "You can take:\n" );
					serverStub.view( clientUserName, "C" );
					userInput = System.console().readLine( "What would you like to take?\n" ).trim();
					serverStub.getThing( clientUserName, userInput );
					userInput = null;
				}
				
				if ( userInput.equals( "show inventory" ) )
				{
					serverStub.showInventory( clientUserName );
				}
				
				if ( userInput.equals( "online users" ) )
				{
					serverStub.listUsers( clientUserName );
					userInput = null;
				}
				
				if ( userInput.equals( "message" ) )
				{
					System.out.println( "You can message to:\n" );
					serverStub.listUsers( clientUserName );
					String to = System.console().readLine( "Now write the name:\n" ).trim();
					String message = System.console().readLine( "Now write the message:\n" ).trim();
					serverStub.message(clientUserName, to, message );
					userInput = null;
				}
				
				if ( userInput.equals( "exit" ) )
				{
					serverStub.leaveServer( clientUserName );
					System.exit(0);
				}
				
			}

		}
		catch(java.rmi.NotBoundException e) {
			System.err.println( "Can't find the auctioneer in the registry." );
		}
		catch (java.io.IOException e) {
			System.out.println( "Failed to register." );
		}
	}
}
