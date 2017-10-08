public class AbilityFactory
{
	private static Ability testAbility = null;
	
	public static void setTestAbility( Ability test )
	{
		testAbility = test;
	} // end method
	
	public static Ability createAbility( char type, char target ) throws AbilityException
	{
		Ability ability = null;
		TargetingStrategy strat;
		
		if ( testAbility == null )
		{
			ability = new Ability();
		
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
		
			ability.setTarget( strat );
		}
		else
		{
			ability = testAbility;
		} // end if
		
		return ability;
	} // end method
} // end class
