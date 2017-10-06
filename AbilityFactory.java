public class AbilityFactory
{
	private static Ability testAbility = null;
	
	public static void setTestAbility( Ability test )
	{
		testAbility = test;
	} // end method

	public static Ability createAbility( String data ) throws AbilityException
	{
		char type, target;
		String name;
		String[] dataParts;
		int base, numDice, typeDice;
		TargetingStrategy targetStrat;
		
		Ability ability = null;
		
		if ( testAbility == null )
		{
			dataParts = data.split(",");
			
			if ( dataParts.length < 6 )
			{
				throw new AbilityException( "Insufficient data" );
			} // end if

			type = dataParts[0].charAt(0);
			name = dataParts[1];
			target  = dataParts[2].charAt(0);
			
			targetStrat = createTargetStrat( type, target );
			
			try
			{
				base = Integer.parseInt( dataParts[3] );
				numDice = Integer.parseInt( dataParts[4] );
				typeDice = Integer.parseInt( dataParts[5] );
			}
			catch ( NumberFormatException e )
			{
				throw new AbilityException( "Invalid integer" );
			} // end try-catch

			ability = new Ability( type, name, targetStrat, base, numDice, typeDice );
		}
		else
		{
			ability = testAbility;
		} // end try-catch

		return ability;
	} // end method
	
	private static TargetingStrategy createTargetStrat( char type, char target ) throws AbilityException
	{
		TargetingStrategy strat;
		
		switch ( type )
		{
			case 'H': case 'h':
			switch ( target )
			{
				case 'S': case 's':
				strat = new TargetSingleAlly();
				break;
				
				case 'M': case 'm':
				strat = new TargetMultiAlly();
				break;
				
				default:
				throw new AbilityException( "Invalid targeting type" );
			} // end switch
			break;
			
			case 'D': case 'd':
			switch ( target )
			{
				case 'S': case 's':
				strat = new TargetSingleEnemy();
				break;
				
				case 'M': case 'm':
				strat = new TargetMultiEnemy();
				break;
				
				default:
				throw new AbilityException( "Invalid targeting type" );
			} // end switch
			break;
			
			default:
			throw new AbilityException( "Invalid ability type" );
		} // end switch
		
		return strat;
	} // end method
} // end class
