
package cs3524.solutions.mud;

import java.util.List;
import java.util.ArrayList;

public class Client implements ClientInterface {
	private String name;
	private String location;
	private String world;
	private List<String> things = new ArrayList<String>();
	
	public Client(String pname, String startingLocation) {
    		name = pname;
    		location = startingLocation;
  	}// defines player by name and assigned starting location of the world


  	public String getName() {
    		return name;
  	}// getname function, used in messaging

  	public void printmess(String mess){
    		System.out.println(mess);

  	}//print message function, used for both global and private messaging

	public String getLocation() {
    		return location;
  	}// to get location of a player in the world


  	public String setLocation(String newLocation) {
    		return location = newLocation;
  	}// to move player in the world

	public void print(String output){
    
	System.out.print(output);
    
	} // to print players status after each move, the same as printmess, but its more readible this way

	public String getWorld() {
		return world;
	}//to find out in which world a player is 

	public String setWorld(String newWorld) {
    		return world = newWorld;
	}//to spawn player in world

	public List<String> getThings() {
		return things;
	}//to get things at given location

	public void carry(String thing) {
		things.add(thing);
	}// to pick up things, and add them to a bag

	public Boolean drop(String thing) {
    		if (things.contains(thing)) {
			things.remove(thing);
      			return true;
		} else { return false; }
	}// to drop things
}
