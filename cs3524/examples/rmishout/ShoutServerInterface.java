/*******************************************************************
 * cs3515.examples.rmishout.ShoutServerInterface                   *
 *******************************************************************/

package cs3524.examples.rmishout;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The remote persona of a ShoutServer.
 * <p> Any implementation of this interface must implement at least
 * these methods.
 * @author Tim Norman, University of Aberdeen
 * @version 2.0
 */

public interface ShoutServerInterface extends Remote
{
    /**
     * Invoke the shout service.
     */
    public String shout( String s )
	throws RemoteException;
}
