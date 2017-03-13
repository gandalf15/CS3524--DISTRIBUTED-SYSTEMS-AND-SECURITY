
package cs3524.solutions.mud;

import java.util.List;
import java.util.ArrayList;

public class ClientImpl implements ClientInterface {
	private String clientUserName;
	
	public ClientImpl( String userName ) 
	{
    		clientUserName = userName;
	}

  	public String getUserName() 
	{
		String name = clientUserName;
	    	return name;
  	}

  	public void sendMessage(String message)
	{
    		System.out.println(message);

  	}
}
