package harkadventurer.control;

import harkadventurer.model.Ability;
import harkadventurer.excep.AbilityException;

/**
 * A factory for constructing Ability-class objects. Constructs abilities, then constructs and applies
 * their targeting and effect logic.
 *
 * @author Jack McNair 18927430
 * @since 10/10/2017
 */
public class AbilityFactory
{
	private Ability testAbility = null;
	
	/**
	 * Sets a test ability. If a test ability is set, the factory will return the test ability
	 * instead of constructing a new one. Use if you want the factory to return a mock ability.
	 *
	 * @param test The test ability
	 */
	public void setTestAbility( Ability test )
	{
		testAbility = test;
	} // end method
	
	/**
	 * Constructs an ability based on the input chars. The input characters should be the
	 * "Type" and "Target" columns of the input file. Assumes that all damage abilities target
	 * enemies and all healing abilities target allies.
	 *
	 * @param type A char determining the ability's type - 'H' for healing and 'D' for damage
	 * @param target A char determining the ability's targeting logic - 'S' for single target and
	 * 'M' for multi
	 * @return An unnamed ability with 0 base damage that rolls 0d2, with the appropriate targeting
	 * and execution logic
	 * @throws AbilityException If either of the inputs are invalid
	 */
	public Ability createAbility( char type, char target ) throws AbilityException
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
