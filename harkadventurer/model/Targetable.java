package harkadventurer.model;

/**
 * A class that implements the Targetable interface can be targeted by abilities.
 *
 * @author Jack McNair 18927430
 * @since 09/10/2017
 */
public interface Targetable
{
	/**
	 * Applies a specified amount of damage or healing to the target. A positive
	 * amount heals, a negative amount damages.
	 *
	 * @param amount The amount of damage or healing to apply
	 */
	void changeHealth( int amount );

	/**
	 * Returns a string containing the target's information.
	 * @return A string containing the target's information
	 */
	String toString();
} // end interface
