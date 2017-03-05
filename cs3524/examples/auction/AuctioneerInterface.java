/*******************************************************************
 * cs3515.examples.auction.AuctioneerInterface                     *
 *******************************************************************/

package cs3524.examples.auction;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The remote persona of the Auctioneer, which provides the means for
 * voters to register a vote.

 * <p> Any implementation of this interface must implement at least
 * these methods.

 * @see AuctioneerImpl

 * @author Tim Norman, University of Aberdeen
 * @version 1.0
 */

public interface AuctioneerInterface extends Remote
{
    /**
     * Method to enable a bidder to bid for the item being sold.

     * <p>The bidder provides a reference to itself as the first
     * parameter of this method so that it can be told whether or not
     * its vote is successful in the auction.

     * @param buyer a reference to the bidder for call backs.
     * @param price The bid made for the item being auctioned.
     */
    public void bid( BidderInterface buyer,
		     float price )
	throws RemoteException;
}
