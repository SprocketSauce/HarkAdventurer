import java.util.*;

public class NonPlayerCharacter extends Character
{
    public void notifyObservers()
    {
        for ( Observer obv : super.getObservers() )
        {
            obv.update( 0.1 );
        } // end for
    } // end method
}