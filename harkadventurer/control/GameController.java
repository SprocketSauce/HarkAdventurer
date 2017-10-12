package harkadventurer.control;

import java.util.*;
import harkadventurer.view.UI;
import harkadventurer.control.*;
import harkadventurer.model.*;
import harkadventurer.model.Character;
import harkadventurer.excep.*;

public class GameController
{
	// ===== CLASSFIELDS =====
	
	private LinkedList<Team> teams;
	private UI ui;
	private FileLoader loader;
	private ObjectIO objLoader;
	
	public GameController( UI inUI, FileLoader inLoader, ObjectIO inObjLoader )
	{
		teams = null;
		ui = inUI;
		loader = inLoader;
		objLoader = inObjLoader;
	} // end method
	
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
			ui.roundMenu( this );
		} // end if
	} // end method
	
	public void saveGame()
	{
		String filename;
		
		filename = ui.inputSaveAs();
		objLoader.saveGame( teams, filename );
	} // end method
	
	public void loadGame()
	{
		String filename;
		
		filename = ui.inputLoadGame();
		teams = objLoader.loadGame( filename );
		ui.roundMenu( this );
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
				ability = ui.turnMenu( chara );
				
				if ( ability != null )
				{
					useAbility( ability, curTeam, enemyTeams );
				}
				
				checkEmptyTeams();
			} // end method
		} // end method
		
		if ( teams.size() == 1 )
		{
			ui.endGame( teams.get(0) );
		}
		else
		{
			ui.roundMenu( this );
		} // end if
	} // end method
	
	private void useAbility( Ability ability, Team curTeam, LinkedList<Team> enemyTeams )
	{
		Targetable target;
		LinkedList<Targetable> validTargets;
		TargetingStrategy targeting = ability.getTarget();
		AbilityEffect effect = ability.getEffect();
		
		validTargets = targeting.getTargets( curTeam, enemyTeams );
		target = ui.targetMenu( validTargets );
		
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
			filename = ui.inputAbilities();
		
			try
			{
				abilityList = loader.readAbilities( filename );
			}
			catch ( AbilityException e )
			{
				retry = ui.retry( e.getMessage() );
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
			filename = ui.inputCharacters();
			
			try
			{
				teams = loader.readCharacters( filename, abilityList );
			}
			catch ( CharacterException e )
			{
				retry = ui.retry( e.getMessage() );
			} // end try-catch
		} while ( retry );
	} // end method
} // end class
