import java.util.*;
import java.io.*;

public class FileLoader
{
	public static LinkedList<Ability> readAbilities( String fileName ) throws IOException, AbilityException
	{
		LinkedList<String> lineList = readFile( fileName );
		LinkedList<Ability> abilities = new LinkedList<Ability>();
		Ability ability;

		for ( int i = 1; i < lineList.size(); i++ )
		{
			ability = AbilityFactory.createAbility( lineList.get(i) );
			abilities.add( ability );
		} // end for

		return abilities;
	} // end method

	public static LinkedList<Character> readCharacters( String fileName, LinkedList<Ability> abilities ) throws IOException, CharacterException
	{
        LinkedList<String> lineList = readFile( fileName );
        LinkedList<Character> characters = new LinkedList<Character>();
        Character character;
        
        for ( int i = 1; i < lineList.size(); i++ )
        {
            character = CharacterFactory.createCharacter( lineList.get(i), abilities );
            characters.add( character );
        }
        
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
