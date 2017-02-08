package cs3524.examples.tasks;

import java.rmi.Remote;
import java.rmi.RemoteException;

public class ExecutionService implements ExecutionServiceInterface
{
   public Object doTask ( TaskInterface task ) throws RemoteException
   {
       return task.execute() ;
   }
}
