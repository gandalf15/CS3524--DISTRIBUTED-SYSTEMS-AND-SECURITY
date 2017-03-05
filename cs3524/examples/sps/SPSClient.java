/*******************************************************************
 * cs3515.examples.sps.SPSClient                         *
 *******************************************************************/
/* updated to Java 8: RMISecurityManager is deprecated
 * MK, 2016-01-27
 */

package cs3524.examples.sps;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.lang.SecurityManager;
import java.rmi.RemoteException;

/**
 * A client that enables the user to play a number of instances of the
 * scissors-paper-stone game.

 * <p>Usage: java SPSClient host port

 * <p>where host:port represents the connection endpoint at which the
 * rmiregistry on the server's host is listening.

 * @author Tim Norman, University of Aberdeen
 * @version 2.0
 */

public class SPSClient
{
  public static void main(String args[])
      throws RemoteException
    {
	if (args.length < 2) {
	    System.err.println( "Usage:\njava SPSClient <registryhost> <registryport>" );
	    return;
	}
	String hostname = args[0];
	int port = Integer.parseInt( args[1] );
	
	// Specify the security policy and set the security manager.
	System.setProperty( "java.security.policy", "sps.policy" ) ;
	System.setSecurityManager( new SecurityManager() ) ;

	try {
	    // Obtain the server handle from the RMI registry
	    // listening at hostname:port.
	    String regURL = "rmi://"+hostname+":"+port+"/SPSService";
	    SPSServerInterface sps = (SPSServerInterface)Naming.lookup( regURL );
	    BufferedReader in = new BufferedReader( new InputStreamReader( System.in ));
	    SPS result = null;
	    char sel;
	    // Prompt the user for a move and iterate until the user
	    // no longer wishes to play.
	    do {
		System.out.println( "Please enter your move (c=scissors, p=paper, s=stone)\nor any other character to exit." );
		try {
		    sel = in.readLine().charAt(0);
		    if (sel == 'c' || sel == 'p' || sel == 's')
			result = sps.move( new SPS( sel ) );
		    else
			result = null;
		}
		catch (StringIndexOutOfBoundsException e) {
		    // This exception will be thrown if the user
		    // simply presses return.
		    result = null;
		}
		// Report the outcome of the game to the user.
		if (result != null) {
		    System.out.println( result.toString() );
		    if (result.clientWon())
			System.out.println( "Congratulations, you won!" );
		    if (result.serverWon())
			System.out.println( "Commiserations, you lost!" );
		    if (result.draw())
			System.out.println( "A draw.  Try again." );
		}
	    } while ( result != null );
	} 
	catch (Exception e) {
	    e.printStackTrace( System.err );
	} // do stuff with the exceptions
    }
}
