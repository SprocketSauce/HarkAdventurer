import java.util.*;

public class Game
{
	private LinkedList<Ability> abilityList;
	private LinkedList<Character> characterList;
	
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
		characterList = null;
		
		do
		{
			retry = false;
			filename = UI.inputCharacters();
			
			try
			{
				characterList = FileLoader.r
			}
		} while ( retry );
	}
} // end class