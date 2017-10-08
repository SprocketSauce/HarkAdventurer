import java.util.*;
import java.io.*;

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
				ability = AbilityFactory.createAbility( lineList.get(i) );
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
				chara = CharacterFactory.createCharacter( lineList.get(i), abilities );
			}
			catch ( CharacterException e )
			{
				throw new CharacterException( "Error: " + filename + " line " + (i + 1) + ": " + e.getMessage() );
			} // end try-catch
			
			characters.add( chara );
		} // end for
		
        teams = TeamManager.teamSort( characters );
        
        return teams;
	}

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
}
