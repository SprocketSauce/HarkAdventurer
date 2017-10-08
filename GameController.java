import java.util.*;

public class GameController
{
	public void startGame()
	{
		LinkedList<Ability> abilityList = null;
		LinkedList<Team> teamList = null;
		
		loadAbilities();
		
		if ( abilityList != null )
		{
			loadCharacters( abilityList );
		} // end if
		
		if ( teamList != null )
		{
			UI.roundMenu( teamList );
		} // end if
	} // end method
	
	private LinkedList<Ability> loadAbilities()
	{
		String filename;
		boolean retry;
		LinkedList<Ability> abilityList = null;
		
		do
		{
			retry = false;
			filename = UI.inputAbilities();
		
			try
			{
				abilityList = FileLoader.readAbilities( filename );
			}
			catch ( AbilityException e )
			{
				retry = UI.retry( e.getMessage() );
			} // end try-catch
		} while ( retry );
	} // end method
	
	private LinkedList<Team> loadCharacters( LinkedList<Ability> abilityList )
	{
		String filename;
		boolean retry;
		LinkedList<Team> teamList = null;
		
		do
		{
			retry = false;
			filename = UI.inputCharacters();
			
			try
			{
				teamList = FileLoader.readCharacters( filename, abilityList );
			}
			catch ( CharacterException e )
			{
				retry = UI.retry( e.getMessage() );
			} // end try-catch
		} while ( retry );
	} // end method
} // end class
