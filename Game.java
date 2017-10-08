import java.util.*;

public class Game
{
	private LinkedList<Ability> abilityList;
	private LinkedList<Team> teamList;
	
	public void startGame()
	{
		loadAbilities();
		
		if ( abilityList != null )
		{
			loadCharacters();
		}
	} // end method
	
	private void loadAbilities()
	{
		String filename;
		boolean retry;
		abilityList = null;
		
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
	
	private void loadCharacters()
	{
		String filename;
		boolean retry;
		teamList = null;
		
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
