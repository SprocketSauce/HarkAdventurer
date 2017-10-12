package harkadventurer.control;

import java.util.*;
import harkadventurer.model.Character;
import harkadventurer.model.PlayerCharacter;
import harkadventurer.model.NonPlayerCharacter;
import harkadventurer.excep.CharacterException;

/**
 * A factory for constructing Character-type objects.
 *
 * @author Jack McNair 18927430
 * @since 10/10/2017
 */
public class CharacterFactory
{
	private Character testCharacter = null;
	
	/**
	 * Sets a test character. If a test character is set, the factory will return the test character
	 * instead of constructing a new one. Use if you want the factory to return a mock character.
	 */
	public void setTestCharacter( Character test )
	{
		testCharacter = test;
	} // end method

	/**
	 * Constructs a character based on the input char. The input should be the "Type" column of the
	 * input file.
	 *
	 * @param type A char determining the character's type - 'P' for player and 'N' for non-player
	 * @return An unnamed character with 1 health and no abilities of the desired type
	 * @throws CharacterException If the input character is not an 'N' or 'P'
	 */
	public Character createCharacter( char type ) throws CharacterException
	{
		Character chara;

		if ( testCharacter == null )
		{
			if ( type == 'P' || type == 'p' )
			{
				chara = new PlayerCharacter();
			}
			else if ( type == 'N' || type == 'n' )
			{
				chara = new NonPlayerCharacter();
			}
			else
			{
				throw new CharacterException( "Invalid character type");
			}
		}
		else
		{
            		chara = testCharacter;
		}
		
		return chara;
	} // end method
} // end class
