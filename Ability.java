package harkadventurer.model;

import harkadventurer.control.AbilityEffect;
import harkadventurer.control.TargetingStrategy;
import harkadventurer.excep.AbilityException;
import java.io.Serializable;

public class Ability implements Serializable
{
	// ===== CLASSFIELDS =====	
	private AbilityEffect effect;
	private String name;
	private TargetingStrategy target;
	private int base;
	private int numDice;
	private int typeDice;

	public Ability()
	{
		effect = null;
		name = null;
		target = null;
		base = 0;
		numDice = 0;
		typeDice = 2;
	} // end constructor
	
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
    public AbilityEffect getEffect() { return effect; }
    public String getName() { return new String( name ); }
    public TargetingStrategy getTarget() { return target; }
    public int getBase() { return base; }
    public int getNumDice() { return numDice; }
    public int getTypeDice() { return typeDice; }
    
    // ===== MUTATORS =====
	public void setEffect( AbilityEffect inEffect )
	{
		effect = inEffect;
	} // end method

	public void setName( String inName )
	{
		name = new String( inName );
	} // end method

	public void setTarget( TargetingStrategy inTarget )
	{
		target = inTarget;
	} // end method

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
    
    public String toString()
    {
        String outStr;
        
        outStr = name + "\nType: " + effect.toString();
        outStr += "    Target: " + target.toString();        
        outStr += "    Damage: " + numDice + "d" + typeDice + " + " + base;
        
        return outStr;
    } // end method
}
