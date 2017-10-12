package harkadventurer.control;

import java.io.Serializable;
import harkadventurer.control.AbilityEffect;
import harkadventurer.model.Targetable;

/**
 * Contains the execution logic for abilities that damage characters.
 *
 * @author Jack McNair 18927430
 * @since 09/10/2017
 */
public class Damage implements AbilityEffect, Serializable
{
	/**
	 * Applies the specified amount of damage to the specified target.
	 *
	 * @param amount The amount of damage
	 */
	public void resolve( Targetable target, int amount )
	{
		target.changeHealth( -amount );		
	} // end method
	
	/**
	 * Returns a string describing the ability's effect, in this case, "Damage".
	 */
	public String toString()
	{
		return "Damage";
	} // end method
} // end class
