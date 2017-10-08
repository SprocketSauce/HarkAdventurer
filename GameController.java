import java.util.*;

public class GameController
{
	public void startGame()
	{
		LinkedList<Ability> abilityList;
		LinkedList<Team> teamList;
		
		loadAbilities();
		
		if ( abilityList != null )
		{
			loadCharacters();
		} // end if
		
		if ( teamList != null )
		{
			runGame( teamList );
		} // end if
	} // end method
	
	private void runGame( LinkedList<Team> teamList )
	{
		Team team;
		Character chara;
		ListIterator<Team> teamIter;
		ListIterator<Character> charIter;
		
		teamIter = teamList.listIterator(0);
		
		while ( teamIter.hasNext() )
		{
			team = teamIter.next();
			charIter = team.getCharacters().listIterator(0);
			
			while ( charIter.hasNext() )
			{
				chara = charIter.next();
				takeTurn( chara, team, teamList );
			} // end while
		} // end while
	} // end method
	
	private void takeTurn( Character chara, Team curTeam, LinkedList<Team> teamList )
	{
		
	}
	
	private LinkedList<Ability> loadAbilities()
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
	
	private LinkedList<Team> loadCharacters()
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
