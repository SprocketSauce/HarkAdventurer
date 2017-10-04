import java.util.*;

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

	public static Character createCharacter( String data, LinkedList<Ability> abilities ) throws CharacterException
	{
        int maxHealth;
		String[] dataParts;
        Ability ability;
		
		Character chara = null;
        
        if ( testCharacter == null )
        {
            dataParts = data.split(",");
            
            if ( dataParts.length < 3 )
            {
                throw new CharacterException( "Insufficient data" );
            } // end if
            
            try
            {
                maxHealth = Integer.parseInt( dataParts[2] );
            }
            catch ( NumberFormatException e )
			{
				throw new CharacterException( "Health is not an integer" );
			} // end try-catch
            
            chara = createCharacter( dataParts[0].charAt(0) );
            
            chara.setName( dataParts[1] );
            chara.setMaxHealth( maxHealth );
            chara.setCurHealth( maxHealth );
            
            for ( int i = 3; i < dataParts.length; i++ )
            {
                ability = getAbility( dataParts[i], abilities );
                chara.addAbility( ability );
            } // end for
        }
        else
        {
            chara = testCharacter;
        } // end if
        
        return chara;
	} // end method
    
    
    private static Ability getAbility( String name, LinkedList<Ability> abilities ) throws CharacterException
    {
        Ability ability = null;
        
        for ( Ability a : abilities )
        {
            if ( name.equals( a.getName() ) )
            {
                ability = a;
            } // end if
        } // end for
        
        if ( ability == null )
        {
            throw new CharacterException( "Ability " + name + " does not exist" );
        } // end if
        
        return ability;
    } // end method
} // end class
