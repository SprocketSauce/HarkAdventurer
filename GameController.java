import java.util.*;

public class GameController
{
	// ===== CLASSFIELDS =====
	private LinkedList<Team> teams;
	
	public GameController()
	{
		teams = null;
	}
	
	public void startGame()
	{
		LinkedList<Ability> abilityList = null;
		teams = null;
		
		abilityList = loadAbilities();
		
		if ( abilityList != null )
		{
			loadCharacters( abilityList );
		} // end if
		
		if ( teams != null )
		{
			UI.roundMenu( this );
		} // end if
	} // end method
	
	public void saveGame()
	{
		String filename;
		
		filename = UI.inputSaveAs();
		ObjectIO.saveGame( teams, filename );
	} // end method
	
	public void loadGame()
	{
		String filename;
		
		filename = UI.inputLoadGame();
		teams = ObjectIO.loadGame( filename );
		UI.roundMenu( this );
	} // end method
	
	public void playRound()
	{
		Team curTeam;
		Character chara;
		Ability ability;
		LinkedList<Team> enemyTeams;
		ListIterator<Character> iter;
		
		for ( int i = 0; i < teams.size(); i++ )
		{
			curTeam = teams.get(i);
			enemyTeams = (LinkedList<Team>)teams.clone();
			enemyTeams.remove(i);
			
			iter = curTeam.getCharacters().listIterator();
			
			while ( iter.hasNext() && teams.size() != 1 )
			{
				chara = iter.next();
				ability = UI.turnMenu( chara );
				
				if ( ability != null )
				{
					useAbility( ability, curTeam, enemyTeams );
				}
				
				checkEmptyTeams();
			} // end method
		} // end method
		
		if ( teams.size() == 1 )
		{
			UI.endGame( teams.get(0) );
		}
		else
		{
			UI.roundMenu( this );
		} // end if
	} // end method
	
	private void useAbility( Ability ability, Team curTeam, LinkedList<Team> enemyTeams )
	{
		Targetable target;
		LinkedList<Targetable> validTargets;
		TargetingStrategy targeting = ability.getTarget();
		AbilityEffect effect = ability.getEffect();
		
		validTargets = targeting.getTargets( curTeam, enemyTeams );
		target = UI.targetMenu( validTargets );
		
		effect.resolve( target, rollDice( ability ) );
	} // end method
	
	private int rollDice( Ability ability )
	{
		int result = ability.getBase();

		for ( int i = 0; i < ability.getNumDice(); i++ )
		{
			result = result + (int)( Math.random() * ability.getTypeDice() + 1.0 );
		} // end for

		return result;
	} // end method
	
	private void checkEmptyTeams()
	{
		Team team;
		ListIterator<Team> iter = teams.listIterator(0);
				
		while ( iter.hasNext() )
		{
			team = iter.next();
			
			if ( team.getNumCharacters() == 0 )
			{
				iter.remove();
			} // end if
		} // end while
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
		
		return abilityList;
	} // end method
	
	private void loadCharacters( LinkedList<Ability> abilityList )
	{
		String filename;
		boolean retry;
		
		do
		{
			retry = false;
			filename = UI.inputCharacters();
			
			try
			{
				teams = FileLoader.readCharacters( filename, abilityList );
			}
			catch ( CharacterException e )
			{
				retry = UI.retry( e.getMessage() );
			} // end try-catch
		} while ( retry );
	} // end method
} // end class
