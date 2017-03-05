/*******************************************************************
 * cs3515.examples.sps.SPSServerImpl                     *
 *******************************************************************/

package cs3524.examples.sps;

import java.rmi.RemoteException;
import java.util.Random;

/**
 * Provides a random choice between Scissors, Paper and Stone,
 * returning the resulting SPS object to the client.

 * @see SPSServerInterface

 * @author Tim Norman, University of Aberdeen
 * @version 1.0
 */

public class SPSServerImpl
    implements SPSServerInterface
{
    private Random _gen;

    /**
     * Initialises a Pseudo-random number generator with the server
     * creation time.
     */
    public SPSServerImpl()
	throws RemoteException
    {
	_gen = new Random( System.currentTimeMillis() );
    }

    /**
     * Makes the server's move in the game.
     */
    public SPS move( SPS clientChoice )
	throws RemoteException
    {
	return clientChoice.serverMove( myChoice() );
    }

    /**
     * Implements the server's move using the pseudo-random number
     * generator.
     */
    private char myChoice()
    {
	int sel = _gen.nextInt( 3 );
	switch (sel) {
	case 0:
	    return 'c';
	case 1:
	    return 'p';
	default:
	    return 's';
	}
    }
}