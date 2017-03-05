/*******************************************************************
 * cs3515.examples.auction.BidderInterface                         *
 *******************************************************************/

package cs3524.examples.auction;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The remote persona of the Auctioneer, which provides the means for
 * voters to register a vote.

 * <p> Any implementation of this interface must implement at least
 * these methods.

 * @author Tim Norman, University of Aberdeen
 * @version 1.0
 */

public interface BidderInterface extends Remote
{
    /**
     * Method to enable the auctineer to inform the bidder that they
     * have won the auction.

     * @param item the item that has been bought.
     * @param price The price paid for the item.
     */
    public void won( String item,
		     float price )
	throws RemoteException;

    /**
     * Method to enable the auctineer to inform the bidder that they
     * have lost the auction.

     * @param item the item that was being auctioned.
     * @param msg A message from the auction to the bidder.
     */
    public void lost( String item,
		      String msg )
	throws RemoteException;
}
