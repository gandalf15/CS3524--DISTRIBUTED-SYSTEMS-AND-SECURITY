/*******************************************************************
 * cs3515.examples.auction.BidderImpl                              *
 *******************************************************************/

package cs3524.examples.auction;

import java.rmi.RemoteException;
import java.util.Vector;
import java.util.Enumeration;


/**
 * An implementation of the AuctioneerInterface remote interface.

 * @see BidderInterface

 * @author Tim Norman, University of Aberdeen
 * @version 1.0
 */

public class BidderImpl
    implements BidderInterface
{
    /**
     * The constructor.  Nothing to be done here.
     */
    public BidderImpl()
	throws RemoteException
    {
    }

    /**
     * Method to enable the auctineer to inform the bidder that they
     * have won the auction.

     * @param item the item that has been bought.
     * @param price The price paid for the item.
     */
    public void won( String item,
		     float price )
	throws RemoteException
    {
	System.out.println( "Congratulations!  You have bought " + item + "." );
	System.out.println( "You paid " + price + "." );
    }

    /**
     * Method to enable the auctineer to inform the bidder that they
     * have lost the auction.

     * @param item the item that was being auctioned.
     * @param msg A message from the auctioneer.
     */
    public void lost( String item,
		      String msg )
	throws RemoteException
    {
	System.out.println( "Sorry, your bid for " + item +
			    " was not successful.\n" + msg );
    }
}

