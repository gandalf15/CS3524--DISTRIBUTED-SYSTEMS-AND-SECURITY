package cs3524.examples.tasks;

public class ShoutTask implements TaskInterface
{
   String message = null ;
   public ShoutTask ( String message )
   {
      this.message = message ;
   }
   public Object execute()
   {
      return message.toUpperCase() ;
   }
}
