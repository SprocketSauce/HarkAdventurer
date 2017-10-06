import java.util.*;
import java.io.*;

public class FileLoader
{
	public static LinkedList<Ability> readAbilities( String filename ) throws AbilityException
	{
		LinkedList<String> lineList;
		
		try
		{
			lineList = readFile( filename );
		}
		catch ( IOException e )
		{
			throw new AbilityException( "Error: " + e.getMessage() ); 
		} // end try-catch
		
		LinkedList<Ability> abilities = new LinkedList<Ability>();
		Ability ability;

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

	public static LinkedList<Character> readCharacters( String fileName, LinkedList<Ability> abilities ) throws IOException, CharacterException
	{
        Character character;
        LinkedList<Team> teams;
        LinkedList<Character> characters = new LinkedList<Character>();              
        LinkedList<String> lineList = readFile( fileName );
                
        for ( int i = 1; i < lineList.size(); i++ )
        {
            character = CharacterFactory.createCharacter( lineList.get(i), abilities );
            characters.add( character );
        }
        
        teams = CharacterFactory.teamSort( characters );
        
        return characters;
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
