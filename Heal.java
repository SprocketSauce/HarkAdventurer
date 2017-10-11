package harkadventurer.control;

import java.io.Serializable;
import harkadventurer.control.AbilityEffect;
import harkadventurer.model.Targetable;

public class Heal implements AbilityEffect, Serializable
{
	public void resolve( Targetable target, int amount )
	{
		target.changeHealth( amount );		
	} // end method
	
	public String toString()
	{
		return "Heal";
	} // end method
} // end class
