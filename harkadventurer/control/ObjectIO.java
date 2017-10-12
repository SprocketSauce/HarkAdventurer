package harkadventurer.control;

import java.util.*;
import java.io.*;
import harkadventurer.model.Team;

/**
 * Saves and loads lists of teams. Used to save a game in progress.
 *
 * @author Jack McNair 18927430
 * @since 10/10/2017
 */
public class ObjectIO
{
	/**
	 * Saves a list of teams to an object file.
	 *
	 * @param teams The list of teams to be saved
	 * @param filename The name of the object file to be saved
	 */
	public void saveGame( LinkedList<Team> teams, String filename )
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
	
	/**
	 * Loads an object file containing a list of teams.
	 *
	 * @param filename The file to be loaded
	 * @return The list of teams described by the object file
	 */
	public LinkedList<Team> loadGame( String filename )
	{
		Object object;
		boolean isValid = true;
		LinkedList<List> list = null;		
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
				list = (LinkedList)object;
				for ( Object o : list )
				{
					if ( !( o instanceof Team ) )
					{
						isValid = false;
					} // end if
				} // end for
			}
			else
			{
				isValid = false;
			} // end if

			if ( isValid )
			{
				teams = (LinkedList<Team>)object;
			} // end if
			
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
		} // end try-catch
		
		return teams;
	} // end method
}
