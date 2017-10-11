package harkadventurer.control;

import harkadventurer.model.Ability;
import harkadventurer.excep.AbilityException;

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
		AbilityEffect effect;
		
		if ( testAbility == null )
		{
			ability = new Ability();
		
			switch ( type )
			{
				case 'H': case 'h':				
				effect = new Heal();
				
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
				effect = new Damage();
				
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
			
			ability.setEffect( effect );
			ability.setTarget( strat );
		}
		else
		{
			ability = testAbility;
		} // end if
		
		return ability;
	} // end method
} // end class
