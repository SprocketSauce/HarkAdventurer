import java.util.*;

public class NonPlayerCharacter extends Character
{
    public void notifyObservers()
    {
        for ( FriendlyObserver obv : super.getFriendlyObservers() )
        {
        	obv.friendlyDeathUpdate();
        }
        
        for ( EnemyObserver obv : super.getEnemyObservers() )
        {
            obv.enemyDeathUpdate( 0.1 );
        } // end for        
    } // end method
}
