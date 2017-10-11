package harkadventurer.model;

import java.util.*;
import harkadventurer.model.Character;
import harkadventurer.model.FriendlyObserver;
import harkadventurer.model.EnemyObserver;

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
