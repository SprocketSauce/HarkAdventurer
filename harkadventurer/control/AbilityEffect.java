package harkadventurer.control;

import harkadventurer.model.Targetable;

/**
 * Classes that define an ability's effect should implement this interface.
 *
 * @author Jack McNair 18927430
 * @since 09/10/2017
 */
public interface AbilityEffect
{
	/**
	 * Applies the ability's effect to the target.
	 *
	 * @param target The ability's target
	 * @param amount The magnitude of the effect
	 */
	void resolve( Targetable target, int amount );

	/**
	 * Returns a string describing the ability's effect. For example, a healing effect
	 * returns "Heal".
	 *
	 * @return A string describing the ability's effect
	 */
	String toString();
} // end interface
