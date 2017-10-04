import java.util.*;

public class PlayerCharacter extends Character
{
    public void notifyObservers()
    {
        for ( Observer obv : super.getObservers() )
        {
            obv.update( 0.05 );
        } // end for
    } // end method
}