package harkadventurer.control;

import java.util.*;
import java.io.*;
import harkadventurer.model.Team;
import harkadventurer.model.Ability;
import harkadventurer.model.Character;
import harkadventurer.control.AbilityFactory;
import harkadventurer.control.CharacterFactory;
import harkadventurer.excep.*;

/**
 * FileLoader objects read Character and Ability txt files and convert them into characters and
 * abilities. The characters are sorted into teams. The exact nature of the characters, abilities and
 * teams created depends on the factories input at construction.
 *
 * @author Jack McNair 18927430
 * @since 10/10/2017
 */
public class FileLoader
{
	private CharacterFactory charFact;
	private AbilityFactory abilFact;
	private TeamManager manager;

	/**
	 * Constructs a new FileLoader that uses the specified factories.
	 *
	 * @param inCharFact The desired Character Factory
	 * @param inAbilFact The desired Ability Factory
	 * @param inManager The desired Team Manager
	 */
	public FileLoader( CharacterFactory inCharFact, AbilityFactory inAbilFact, TeamManager inManager )
	{
		charFact = inCharFact;
		abilFact = inAbilFact;
		manager  = inManager;
	}

	/**
	 * Reads an abilities file. The abilities are constructed by the factory, placed into a linked 
	 * list and returned.
	 *
	 * @param filename The name of the file to be read
	 * @return A list of abilities
	 * @throws AbilityException If the file cannot be loaded or has invalid formatting
	 */
	public LinkedList<Ability> readAbilities( String filename ) throws AbilityException
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

	/**
	 * Reads a characters file. The characters are constructed by the factory and sorted into teams
	 * by the team manager. The teams are then returned. Abilities must be constructed first.
	 *
	 * @param filename The name of the file to be read
	 * @param abilities The list of valid abilities
	 * @return A list of teams containing the loaded characters
	 * @throws CharacterException If the file fails to load, has invalid formatting or any of the
	 * characters have abilities that don't exist
	 */
	public LinkedList<Team> readCharacters( String filename, LinkedList<Ability> abilities ) throws CharacterException
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
		
        teams = manager.teamSort( characters );
        
        return teams;
	} // end method

	// ===== PRIVATE METHODS =====
	/*
	 * readFile
	 * Reads an input file, converting each line into an entry in a list of strings.
	 *
	 * Parameters:
	 * filename - The name of the file to be read.
	 *
	 * Returns:
	 * A list of strings, each string being a line of the file.
	 *
	 * Throws:
	 * IOException - In case of an error in file reading.
	 */
	private LinkedList<String> readFile( String fileName ) throws IOException
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
	
	/*
	 * readAbilityLine
	 * Creates an ability from a line of data.
	 *
	 * Parameters:
	 * data - A single line from an Abilities input file.
	 *
	 * Returns:
	 * The ability generated.
	 *
	 * Throws:
	 * AbilityException - If the data format is invalid
	 */
	private Ability readAbilityLine( String data ) throws AbilityException
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

		ability = abilFact.createAbility( type, target );

		ability.setName( name );
		ability.setBase( base );
		ability.setNumDice( numDice );
		ability.setTypeDice( typeDice );

		return ability;
	} // end method
	
	/*
	 * readCharacterLine
	 * Creates a character from a line of data.
	 *
	 * Parameters:
	 * data - A single line from a Characters input file.
	 *
	 * Returns:
	 * The character generated.
	 *
	 * Throws:
	 * CharacterException - If the data format is invalid
	 */
	private Character readCharacterLine( String data, LinkedList<Ability> abilities ) throws CharacterException
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
		
		chara = charFact.createCharacter( dataParts[0].charAt(0) );
		
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
	
	/*
	 * getAbility
	 * Given a string and a list of abilities, retrieves an ability from the list with a name that
	 * matches the string.
	 *
	 * Parameters:
	 * name - The name of the ability to be retrieved
	 * abilities - The list of valid abilities
	 *
	 * Returns:
	 * An ability from the list with the specified name
	 *
	 * Throws:
	 * CharacterException - If there is no ability with the specified name
	 */
	private Ability getAbility( String name, LinkedList<Ability> abilities ) throws CharacterException
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
