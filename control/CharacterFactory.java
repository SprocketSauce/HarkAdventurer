package harkadventurer.control;

import java.util.*;
import harkadventurer.model.Character;
import harkadventurer.model.PlayerCharacter;
import harkadventurer.model.NonPlayerCharacter;
import harkadventurer.excep.CharacterException;

public class CharacterFactory
{
	private static Character testCharacter = null;
    
    public static void setTestCharacter( Character test )
    {
        testCharacter = test;
    } // end method
    
    public static Character createCharacter( char type ) throws CharacterException
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
