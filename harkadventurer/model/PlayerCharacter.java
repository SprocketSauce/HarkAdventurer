package harkadventurer.model;

import java.util.*;

/**
 * A character controlled by a player. PCs restore less health to opposing teams when
 * killed.
 *
 * @author Jack McNair 18927430
 * @since 09/10/2017
 */
public class PlayerCharacter extends Character
{
	/**
	 * Notifies the character's observers that they have been killed. Restores 5% of
	 * the health of all enemy characters.
	 */
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
} // end class
