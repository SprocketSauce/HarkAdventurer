public class Ability
{
	private char type;
	private String name;
	private char target;
	private int base;
	private int numDice;
	private int typeDice;

	
	public Ability( char inType, String inName, char inTarget, int inBase, int inNumDice,
		int inTypeDice ) throws AbilityException
	{
		setType( inType );
		setName( inName );
		setTarget( inTarget );
		setBase( inBase );
		setNumDice( inNumDice );
		setTypeDice( inTypeDice );
	} // end method

    // =====ACCESSORS=====
    public char getType() { return type; }
    public String getName() { return new String( name ); }
    public char getTarget() { return target; }
    public int getBase() { return base; }
    public int getNumDice() { return numDice; }
    public int getTypeDice() { return typeDice; }
    
    // =====MUTATORS=====
	public void setType( char inType ) throws AbilityException
	{
		if ( inType == 'H' || inType == 'D' )
		{
			type = inType;
		}
		else
		{
			throw new AbilityException( "Invalid ability type" );
		} // end if
	} // end method

	public void setName( String inName )
	{
		name = new String( inName );
	} // end method

	public void setTarget( char inTarget ) throws AbilityException
	{
		if ( inTarget == 'S' || inTarget == 'M' )
		{
			target = inTarget;
		}
		else
		{
			throw new AbilityException( "Invalid target type" );
		} // end if
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

	public int getDamage()
	{
		int damage = base;

		for ( int i = 0; i < numDice; i++ )
		{
			damage = damage + (int)( Math.random() * typeDice + 1.0 );
		}

		return damage;
	} // end method
    
    public String toString()
    {
        String outStr;
        
        outStr = name + "\nType: ";
        if ( type == 'H' )
        {
            outStr += "Heal";
        }
        else if ( type == 'D' )
        {
            outStr += "Damage";
        } // end if
        
        outStr += "    Target: ";
        if ( target == 'S')
        {
            outStr += "Single";            
        }
        else if ( target == 'M' )
        {
            outStr += "Multi";
        } // end if
        
        outStr += "    Damage: " + numDice + "d" + typeDice + " + " + base;
        
        return outStr;
    } // end method
}
