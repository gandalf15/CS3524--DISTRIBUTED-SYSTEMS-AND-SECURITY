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
			for(String srv : servers){
				System.out.println("("+i+") "+srv);
				if (servers.size() == i){
					++i;
					System.out.println("("+i+") Create own server");
				}		
				++i;
			}

			String chosenServerString = null;
			while(chosenServerString == null){
				chosenServerString = System.console().readLine("Connect to server number: ").trim();
				if (Integer.parseInt(chosenServerString) <= servers.size()+1){
					serverStub.joinServer(servers.get(Integer.parseInt(chosenServerString)), clientStub);
				} else {
					chosenServerString = null;
					System.out.println("Invalid choice! Try again.");
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
