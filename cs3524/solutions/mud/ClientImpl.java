
package cs3524.solutions.mud;

import java.util.List;
import java.util.ArrayList;

public class ClientImpl implements ClientInterface {
	private String clientUserName;
	
	public ClientImpl( String clientUserName ) 
	{
    		clientUserName = clientUserName;
	}

  	public String getUserName() 
	{
    		return clientUserName;
  	}

  	public void sendMessage(String message)
	{
    		System.out.println(message);

  	}
}
