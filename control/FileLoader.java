package harkadventurer.control;

import java.util.*;
import java.io.*;
import harkadventurer.model.Team;
import harkadventurer.model.Ability;
import harkadventurer.model.Character;
import harkadventurer.control.AbilityFactory;
import harkadventurer.control.CharacterFactory;
import harkadventurer.excep.*;

public class FileLoader
{
	public static LinkedList<Ability> readAbilities( String filename ) throws AbilityException
	{
		LinkedList<String> lineList;
		LinkedList<Ability> abilities = new LinkedList<Ability>();
		Ability ability;
		
		try
		{
			lineList = readFile( filename );
		}
		catch ( IOException e )
		{
			throw new AbilityException( "Error: " + e.getMessage() ); 
		} // end try-catch
		

		for ( int i = 1; i < lineList.size(); i++ )
		{
			try
			{
				ability = readAbilityLine( lineList.get(i) );
			}
			catch ( AbilityException e )
			{
				throw new AbilityException( "Error: " + filename + " line " + (i + 1) + ": " + e.getMessage() );
			} // end try-catch
			
			abilities.add( ability );
		} // end for

		return abilities;
	} // end method

	public static LinkedList<Team> readCharacters( String filename, LinkedList<Ability> abilities ) throws CharacterException
	{
        Character chara;
        String line;
        String[] splitLine;
        LinkedList<String> lineList;
		LinkedList<Character> characters = new LinkedList<Character>();
		LinkedList<Team> teams;
		
		try
		{
			lineList = readFile( filename );
		}
		catch ( IOException e )
		{
			throw new CharacterException( "Error: " + e.getMessage() ); 
		} // end try-catch
		
		for ( int i = 1; i < lineList.size(); i++ )
		{
			try
			{
				chara = readCharacterLine( lineList.get(i), abilities );
			}
			catch ( CharacterException e )
			{
				throw new CharacterException( "Error: " + filename + " line " + (i + 1) + ": " + e.getMessage() );
			} // end try-catch
			
			characters.add( chara );
		} // end for
		
        teams = TeamManager.teamSort( characters );
        
        return teams;
	} // end method

	private static LinkedList<String> readFile( String fileName ) throws IOException
	{
		FileInputStream fileStrm = null;
		InputStreamReader rdr;
		BufferedReader bufRdr;
		String line;
		LinkedList<String> lineList = new LinkedList<String>();

		try
		{
			fileStrm = new FileInputStream( fileName );
			rdr = new InputStreamReader( fileStrm );
			bufRdr = new BufferedReader( rdr );
			
			line = new String( bufRdr.readLine() );
			while ( line != null )
			{
				lineList.add( line );
				line = bufRdr.readLine();
			} // end while
		}
		catch ( IOException e )
		{
			if ( fileStrm != null )
			{
				try { fileStrm.close(); } catch ( IOException e2 ) {}
			} // end if
			
			throw new IOException( e.getMessage() );			
		} // end try-catch

		return lineList;
	} // end method
	
	private static Ability readAbilityLine( String data ) throws AbilityException
	{
		char type, target;
		String name;
		String[] dataParts;
		int base, numDice, typeDice;
		Ability ability;
		TargetingStrategy targetStrat;
		
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

		ability = AbilityFactory.createAbility( type, target );

		ability.setName( name );
		ability.setBase( base );
		ability.setNumDice( numDice );
		ability.setTypeDice( typeDice );

		return ability;
	} // end method
	
	private static Character readCharacterLine( String data, LinkedList<Ability> abilities ) throws CharacterException
	{
		int maxHealth;
		String[] dataParts;
		Character chara;
		Ability ability;
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
		
		chara = CharacterFactory.createCharacter( dataParts[0].charAt(0) );
		
		chara.setName( dataParts[1] );
        chara.setMaxHealth( maxHealth );
        chara.setCurHealth( maxHealth );
            
        for ( int i = 3; i < dataParts.length; i++ )
        {
            ability = getAbility( dataParts[i], abilities );
            chara.addAbility( ability );
        } // end for
        
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
