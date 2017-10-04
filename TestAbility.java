import java.util.*;
import java.io.*;

public class TestAbility
{
    public static void main( String[] args )
    {
        LinkedList<Ability> abilities = null;
        LinkedList<Character> characters;
        
        try
        {
            abilities = FileLoader.readAbilities( "TestAbilities.txt" );

            for ( Ability a : abilities )
            {
                System.out.println( a.toString() );
                System.out.println( "DAMAGE: " + a.getDamage() );
            }
        }
        catch ( IOException e )
        {
            System.out.println( "Error: " + e.getMessage() );
        }
        catch ( AbilityException e )
        {
            System.out.println( "Error: " + e.getMessage() );
        }
        
        try
        {
            characters = FileLoader.readCharacters( "TestCharacters.txt", abilities );
            
            for ( Character c : characters )
            {
                System.out.println( c.toString() );
            }
        }
        catch ( IOException e )
        {
            System.out.println( "Error: " + e.getMessage() );
        }
        catch ( CharacterException e )
        {
            System.out.println( "Error: " + e.getMessage() );
        }
    }
}