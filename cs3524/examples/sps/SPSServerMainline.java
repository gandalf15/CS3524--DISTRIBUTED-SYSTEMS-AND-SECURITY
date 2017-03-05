/*******************************************************************
 * cs3515.examples.sps.SPSServerMainline                 *
 *******************************************************************/
/* updated to Java 8: RMISecurityManager is deprecated
 * MK, 2016-01-27
 */

package cs3524.examples.sps;

import java.rmi.Naming;
import java.lang.SecurityManager;
import java.net.InetAddress;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RMISecurityManager;
/**
 * The server mainline that generates and registers an instance of
 * SPSServerImpl.

 * <p>Usage: java SPSServerMainline port

 * <p>where there is an rmiregistry listening at the port specified on
 * the host that this server is running.

 * @author Tim Norman, University of Aberdeen
 * @version 2.0
 */

public class SPSServerMainline
{
    public static void main(String args[])
    {
	if (args.length < 2) {
	    System.err.println( "Usage:\njava SPSServerMainline <registryport> <serverport>" ) ;
	    return;
	}

	try {
	    String hostname = (InetAddress.getLocalHost()).getCanonicalHostName() ;
	    int registryport = Integer.parseInt( args[0] ) ;
	    int serverport = Integer.parseInt( args[1] ) ;
	
	    System.setProperty( "java.security.policy", "sps.policy" ) ;
	    System.setSecurityManager( new RMISecurityManager() ) ;

            SPSServerImpl spsserv = new SPSServerImpl();
	    SPSServerInterface spsstub = (SPSServerInterface)UnicastRemoteObject.exportObject( spsserv, serverport );
            Naming.rebind( "rmi://" + hostname + ":"
			   + registryport + "/SPSService", spsstub );
	}
	catch(java.net.UnknownHostException e) {
	    System.err.println( "Can't determine the local host." );
	    System.err.println( e.getMessage() );
	}
	catch (java.io.IOException e) {
            System.err.println( "Failed to register." );
	    System.err.println( e.getMessage() );
        }
    }
}
