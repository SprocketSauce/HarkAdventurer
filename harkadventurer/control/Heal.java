package harkadventurer.control;

import java.io.Serializable;
import harkadventurer.control.AbilityEffect;
import harkadventurer.model.Targetable;

/**
 * Contains the execution logic for abilities that heal characters.
 *
 * @author Jack McNair 18927430
 * @since 09/10/2017
 */
public class Heal implements AbilityEffect, Serializable
{
	/**
	 * Heals the specified target by the specified amount.
	 *
	 * @param amount The amount of healing to apply
	 */
	public void resolve( Targetable target, int amount )
	{
		target.changeHealth( amount );		
	} // end method
	
	/**
	 * Returns a string describing the ability's effect, in this case, "Heal".
	 */
	public String toString()
	{
		return "Heal";
	} // end method
} // end class
