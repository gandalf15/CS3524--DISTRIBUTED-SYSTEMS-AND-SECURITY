
package cs3524.examples.tasks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

public class Client 
{
	public static void main ( String args[] ) throws RemoteException
	{
		if (args.length < 2) {
		    System.err.println( "Usage:\njava Client <host> <registryport>" ) ;
		    return;
		}
		
		String hostname = args[0];
		int    port     = Integer.parseInt( args[1] );

		// Specify the security policy and set the security manager.
		System.setProperty( "java.security.policy", "rmishout.policy" ) ;
		System.setSecurityManager( new RMISecurityManager() ) ;

		try
		{
			String regURL = "rmi://" + hostname + ":" + port + "/ExecutionService" ;
			ExecutionServiceInterface remoteExecutor = (ExecutionServiceInterface) Naming.lookup ( regURL ) ;
			
			BufferedReader in = new BufferedReader ( new InputStreamReader( System.in ));
	        System.out.print ( "Message> " ) ;
	        
	        String message = in.readLine() ;
	        
	        //create task
	        ShoutTask task = new ShoutTask ( message ) ;
	        // send task to server
	        String result = (String) remoteExecutor.doTask ( task ) ;
	        // print result of task execution
	        System.out.println ( "Result : " + result ) ;
	        
		}
		catch ( java.io.IOException e) {
            System.err.println( "I/O error." );
    	    System.err.println( e.getMessage() );
            }
    	catch (java.rmi.NotBoundException e) {
                System.err.println( "Server not bound." );
    	    System.err.println( e.getMessage() );
        }
	}

}
