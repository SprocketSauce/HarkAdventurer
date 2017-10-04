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

			ability = new Ability( type, name, target, base, numDice, typeDice );
		}
		else
		{
			ability = testAbility;
		} // end try-catch

		return ability;
	} // end method
} // end class
