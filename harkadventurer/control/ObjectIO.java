package harkadventurer.control;

import java.util.*;
import java.io.*;
import harkadventurer.model.Team;

public class ObjectIO
{
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
