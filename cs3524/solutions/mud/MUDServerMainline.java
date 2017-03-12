/*	Author: Marcel Zak
*	version: 0.0
*/

package cs3524.solutions.mud;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.server.UnicastRemoteObject;

public class MUDServerMainline {
	
	static BufferedReader in = new BufferedReader( new InputStreamReader( System.in ));

	
	public static void main(String args[]){
	
	if(args.length < 2){
		System.err.println("Usage:\n java MUDServerMainline <registryport> <serverport>");
		return;
	} 
	
	try {
		String hostname = (InetAddress.getLocalHost()).getCanonicalHostName();
		int registryPort = Integer.parseInt(args[0]);
		int serverPort = Integer.parseInt(args[1]);   
			
		// Setup Security 
		System.setProperty("java.security.policy", "mud.policy");
		System.setSecurityManager( new RMISecurityManager() );

		// Generate the remote objects
		MUDServerImpl mudserver = new MUDServerImpl();
		MUDServerInterface mudstub = (MUDServerInterface)UnicastRemoteObject.exportObject(mudserver, serverPort);
		
		String regURL = "rmi://" + hostname + ":" + registryPort + "/MUDServer";
		System.out.println("Registering " + regURL);
		Naming.rebind(regURL, mudstub);
	  
	} 
	catch (java.net.UnknownHostException e) {
		System.err.println("Java can't determine the local host!");
	}
	catch (java.io.IOException e){
		System.err.println("Failed to regitser.");
	}
	
	}
	
}
