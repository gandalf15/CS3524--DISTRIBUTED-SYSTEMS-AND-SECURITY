package cs3524.examples.rmishout;

public class RepeatLinesTask implements TaskInterface
{
   String _msg = null ;
   int    _n   = 0 ;

   public RepeatLinesTask ( String msg, int n )
   {
      this._msg = msg ;
      this._n   = n   ;
   }
   public Object execute()
   {
      String lines = new String ( _msg ) ;
      for ( int i = 1; i < _n; i++ ) lines = lines + â€œ\nâ€ + _msg ;
      return lines ;
   }
}
