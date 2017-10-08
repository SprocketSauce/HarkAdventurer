import java.util.*;

public class PlayerCharacter extends Character
{
    public void notifyObservers()
    {
        for ( FriendlyObserver obv : super.getFriendlyObservers() )
        {
        	obv.friendlyDeathUpdate();
        }
        
        for ( EnemyObserver obv : super.getEnemyObservers() )
        {
            obv.enemyDeathUpdate( 0.05 );
        } // end for        
    } // end method
}
