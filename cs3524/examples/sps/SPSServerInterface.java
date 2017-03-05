/*******************************************************************
 * cs3515.examples.sps.SPSServerInterface                *
 *******************************************************************/

package cs3524.examples.sps;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The remote persona of an SPSServer, which provides the means for a
 * client to play the scissors-paper-stone game.

 * <p> Any implementation of this interface must implement at least
 * these methods.

 * @author Tim Norman, University of Aberdeen
 * @version 1.0
 */

public interface SPSServerInterface extends Remote
{
    /**
     * Request the server to make its move in the game and return the
     * resulting SPS object.
     */
    public SPS move( SPS clientChoice )
	throws RemoteException;
}
