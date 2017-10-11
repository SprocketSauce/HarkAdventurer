package harkadventurer.model;

import harkadventurer.control.AbilityEffect;
import harkadventurer.control.TargetingStrategy;
import harkadventurer.excep.AbilityException;
import java.io.Serializable;

/**
 * Ability-type objects store all information pertaining to an ability, including its
 * name, base damage, dice, targeting and effect.
 *
 * @author Jack McNair 18927430
 * @since 09/10/2017
 */
public class Ability implements Serializable
{
	// ===== CLASSFIELDS =====	
	private AbilityEffect effect;
	private String name;
	private TargetingStrategy target;
	private int base;
	private int numDice;
	private int typeDice;

	/** 
	 * Constructs an ability object with no name, effect and targeting, with a base 
	 * damage of 0 and 0 2-sided dice.
	 */
	public Ability()
	{
		effect = null;
		name = null;
		target = null;
		base = 0;
		numDice = 0;
		typeDice = 2;
	} // end constructor
	
	/**
	 * Constructs an ability object initialized with the given effect, name, target,
	 * base effect and dice.
	 * @param inEffect The ability's effect
	 * @param inName The ability's name
	 * @param inTarget The ability's targeting logic
	 * @param inBase The number to be added to the ability's dice roll
	 * @param inNumDice The number of dice to be rolled
	 * @param inTypeDice The type of dice to be rolled
	 * @throws AbilityException If any of the inputs are invalid
	 */
	public Ability( AbilityEffect inEffect, String inName, TargetingStrategy inTarget, int inBase, int inNumDice, int inTypeDice ) throws AbilityException
	{
		setEffect( inEffect );
		setName( inName );
		setTarget( inTarget );
		setBase( inBase );
		setNumDice( inNumDice );
		setTypeDice( inTypeDice );
	} // end constructor

	// ===== ACCESSORS =====

	/** 
	 * Returns the ability's effect. 
	 * @return The ability's effect 
	 */
	public AbilityEffect getEffect() { return effect; }

	/** 
	 * Returns the ability's name. 
	 * @return The ability's name
	 */
	public String getName() { return new String( name ); }

	/** 
	 * Returns the ability's targeting strategy. 
	 * @return The ability's targeting strategy
	 */
	public TargetingStrategy getTarget() { return target; }

	/** 
	 * Returns the ability's base damage. 
	 * @return The ability's base damage
	 */
	public int getBase() { return base; }

	/** 
	 * Returns the number of dice the ability rolls. 
	 * @return The number of dice 
	 */
	public int getNumDice() { return numDice; }

	/** 
	 * Returns the number of faces on the dice the ability rolls. 
	 * @return The dice type 
	 */
	public int getTypeDice() { return typeDice; }
    
    // ===== MUTATORS =====
	/**
	 * Sets the ability's effect to the input.
	 * @param inEffect The desired effect
	 */
	public void setEffect( AbilityEffect inEffect )
	{
		effect = inEffect;
	} // end method
	
	/**
	 * Sets the ability's name.
	 * @param inName The ability's name
	 */
	public void setName( String inName )
	{
		name = new String( inName );
	} // end method

	/**
	 * Sets the ability's targeting logic.
	 * @param inTarget The desired targeting logic
	 */
	public void setTarget( TargetingStrategy inTarget )
	{
		target = inTarget;
	} // end method

	/**
	 * Sets the ability's base damage.
	 * @param inBase The desired base damage
	 * @throws AbilityException If the input is negative
	 */
	public void setBase( int inBase ) throws AbilityException
	{
		if ( inBase >= 0 )
		{
			base = inBase;
		}
		else
		{
			throw new AbilityException( "Negative base effect" );
		}
	} // end method

	/**
	 * Sets the number of dice to be rolled by the ability.
	 * @param inNumDice The desired number of dice
	 * @throws AbilityException If the input is negative
	 */
	public void setNumDice( int inNumDice ) throws AbilityException
	{
		if ( inNumDice >= 0 )
		{
			numDice = inNumDice;
		}
		else
		{
			throw new AbilityException( "Negative number of dice" );
		} // end if
	} // end method

	/**
	 * Sets the type of dice to be rolled by the ability. A dice can have 2, 3, 4, 6, 8,
	 * 10, 12, 20 or 100 faces.
	 * @param inTypeDice The number of faces on the ability's dice
	 * @throws AbilityException If the input is not a valid type of dice
	 */
	public void setTypeDice( int inTypeDice ) throws AbilityException
	{
		switch ( inTypeDice )
		{
			case 2: case 3: case 4: case 6: case 8: case 10: case 12: case 20: case 100:
				typeDice = inTypeDice;
				break;
			default:
				throw new AbilityException( "Invalid die type" );
		} // end switch
	} // end method	
    
	/**
	 * Returns a String containing the ability's information.
	 * @return A String containing all the ability's classfields.
	 */
    public String toString()
    {
        String outStr;
        
        outStr = name + "\nType: " + effect.toString();
        outStr += "    Target: " + target.toString();        
        outStr += "    Damage: " + numDice + "d" + typeDice + " + " + base;
        
        return outStr;
    } // end method
}
