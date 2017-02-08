package cs3524.examples.tasks;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ExecutionServiceInterface extends Remote
{
    
    public Object doTask( TaskInterface mytask )
	throws RemoteException;
}
