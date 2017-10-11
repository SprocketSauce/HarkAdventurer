package harkadventurer.control;

import harkadventurer.model.Targetable;

public interface AbilityEffect
{
	void resolve( Targetable target, int amount );
	String toString();
} // end interface
