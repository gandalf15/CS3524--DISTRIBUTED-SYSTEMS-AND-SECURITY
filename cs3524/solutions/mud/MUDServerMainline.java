/*	Author: Marcel Zak
*	version: 0.0
*/
package cs3524.solutions.mud;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.server.UnicastRemoteObject;

public class MUDServerMainline {
    
    static BufferedReader in = new BufferedReader( new InputStreamReader( System.in ));

    
    public static void main(String args[]){
        
        if(args.length < 2){
            System.err.println("Usage:\n java MUDServerMainline <registryport> <serverport>");
            return;
        } 
        
        try {
	    String hostname = (InetAddress.getLocalHost()).getCanonicalHostName();
	    int registryPort = Integer.parseInt(args[0]);
            int serverPort = Integer.parseInt(args[1]);   
                        
            // Setup Security 
            System.setProperty("java.security.policy", "mud.policy");
            System.setSecurityManager( new RMISecurityManager() );

            // Generate the remote objects
            MUDServiceImpl mudserver = new MUDServerImpl();
            MUDService mudstub = (MUDServerInterface)UnicastRemoteObject.exportObject(mudserver, serverPort);
            
            String regURL = "rmi://" + hostname + ":" + registryPort + "/MUDServer";
            System.out.println("Registering " + regURL);
            Naming.rebind(regURL, mudstub);
            
           
            
            while(true){
                
                String input = in.readLine();
                
                if(input.contains("create")){
                    String[] arguments = input.split(" ");
                    
                    if(mudserver.Servers.size() < 4){
                        System.out.println("Creaing Server  " + arguments[1]);

                        MUD newmud = new MUD(arguments[2],arguments[3],arguments[4]);
                        mudserver.Servers.put(arguments[1], newmud);
                    } else {
                        System.out.println("Maximum MUD servers reached (4)");
                    }
                }
                
                
            }
            
            
        } 
        catch (java.net.UnknownHostException e) {
            System.err.println("Gannot get local host name.");
        }
        catch (java.io.IOException e){
            System.err.println("Failed to regitser.");
        }
        
    }
    
}
