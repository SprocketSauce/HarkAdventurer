import java.util.*;
import java.io.*;

public class ObjectIO
{
	public static void saveGame( LinkedList<Team> teams, String filename )
	{
		FileOutputStream file = null;
		ObjectOutputStream objOut = null;
		
		try 
		{
			file = new FileOutputStream( filename );
			objOut = new ObjectOutputStream( file );
			
			objOut.writeObject( teams );
			objOut.close();
			file.close();
		}
		catch ( IOException e )
		{
			System.out.println( e.getMessage() );			
			try { file.close(); } catch ( IOException e2 ) { }
		}
	} // end method
	
	public static LinkedList<Team> loadGame( String filename )
	{
		Object object;		
		LinkedList<Team> teams = null;
		FileInputStream file = null;
		ObjectInputStream objIn = null;
		
		try
		{
			file = new FileInputStream( filename );
			objIn = new ObjectInputStream( file );
			
			object = objIn.readObject();
			
			if ( object instanceof LinkedList )
			{
				teams = (LinkedList<Team>)object;
			}
			
			objIn.close();
			file.close();
		}
		catch ( IOException e )
		{
			System.out.println( e.getMessage() );			
			try { file.close(); } catch ( IOException e2 ) { }
		}
		catch ( ClassNotFoundException e )
		{
			System.out.println( e.getMessage() );			
			try { file.close(); } catch ( IOException e2 ) { }
		}
		
		return teams;
	} // end method
}
