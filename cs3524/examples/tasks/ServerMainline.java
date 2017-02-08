package cs3524.examples.tasks;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.server.UnicastRemoteObject;

public class ServerMainline 
{
	static public void main ( String args[] )
	{
        if (args.length < 2) {
		    System.err.println( "Usage:\njava ServerMainline <registryport> <serviceport>" ) ;
		    return;
		}
		
		try
		{
			String hostname = (InetAddress.getLocalHost()).getCanonicalHostName() ;
			System.out.println ( hostname ) ;
		    int registryport = Integer.parseInt( args[0] ) ;
		    int serviceport = Integer.parseInt( args[1] );

		    System.setProperty( "java.security.policy", "rmishout.policy" ) ;
		    System.setSecurityManager( new RMISecurityManager() ) ;
		    
		    ExecutionService executor = new ExecutionService() ;
		    ExecutionServiceInterface stub = (ExecutionServiceInterface) UnicastRemoteObject.exportObject(executor, serviceport) ;
		    String regURL = "rmi://" + hostname + ":" + registryport + "/ExecutionService";
		    Naming.rebind(regURL, stub) ;
		    
		    System.out.println ( "Server ready." ) ;

		}
		catch (java.net.UnknownHostException e) 
		{
		    System.err.println( "Cannot get local host name." );
		    System.err.println( e.getMessage() );
		}
		catch (java.io.IOException e) 
		{
            System.err.println( "Failed to register." );
	        System.err.println( e.getMessage() );
        }
	}

}

