package harkadventurer.model;

/**
 * A class can implement the EnemyObserver interface when it wants to be informed of the
 * death of enemy characters.
 * 
 * @author Jack McNair 18927430
 * @since 09/10/2017
 */
public interface EnemyObserver
{
	/**
	 * This method is called when an enemy character is killed.
	 *
	 * @param healAmount The amount that the character heals enemies upon their
	 * death
	 */
	void enemyDeathUpdate( double healAmount );
} // end interface
