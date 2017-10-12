package harkadventurer.control;

import java.util.*;
import harkadventurer.view.UI;
import harkadventurer.control.*;
import harkadventurer.model.*;
import harkadventurer.model.Character;
import harkadventurer.excep.*;

/**
 * A GameController object manages the game. The GameController initialises the gamestate, and controls
 * the flow of events throughout rounds of combat.
 *
 * @author Jack McNair 18927430
 * @since 10/10/2017
 */
public class GameController
{
	// ===== CLASSFIELDS =====
	
	private LinkedList<Team> teams;
	private UI ui;
	private FileLoader loader;
	private ObjectIO objLoader;
	
	/**
	 * Constructs a GameController that uses the specified user interface, file loader and object 
	 * loader.
	 *
	 * @param inUI The desired user interface
	 * @param inLoader The FileLoader that will load Character and Ability files.
	 * @param inObjLoader The ObjectIO object that will be used to save and load games in progress
	 */
	public GameController( UI inUI, FileLoader inLoader, ObjectIO inObjLoader )
	{
		teams = null;
		ui = inUI;
		loader = inLoader;
		objLoader = inObjLoader;
	} // end method
	
	/**
	 * Begins a game using an Abilities and Character csv files. Uses the csv files to construct
	 * a list of teams, then, if the teams were loaded correctly, starts the game.
	 */
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
		}
	} // end method
	
	/**
	 * Saves the game's list of teams as an object file. Prompts the user to input a file name.
	 */
	public void saveGame()
	{
		String filename;
		
		filename = ui.inputSaveAs();
		objLoader.saveGame( teams, filename );
	} // end method
	
	/**
	 * Loads a list of teams from an object file, then begins a round.
	 */
	public void loadGame()
	{
		String filename;
		LinkedList<Team> inTeams = null;
		
		filename = ui.inputLoadGame();
		inTeams = objLoader.loadGame( filename );

		if ( inTeams != null )
		{
			teams = inTeams;
		} // end if

		ui.roundMenu( this );
	} // end method
	
	/**
	 * Runs a round of combat. Iterates through each team, taking a turn for each character. At the
	 * end of each turn, empty teams are removed from the team list. If there is only one team
	 * remaining at the end of the round, the game ends.
	 */
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
	
	// ===== PRIVATE METHODS =====
	/*
	 * useAbility
	 * Resolves an ability. Generates a list of valid targets from an ability's targeting logic,
	 * prompts the user to select a target, then resolves the ability against that target.
	 *
	 * Parameters:
	 * ability - The ability to be used
	 * curTeam - The team of the character using the ability
	 * enemyTeams - A list of enemy teams
	 */
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
	
	/*
	 * rollDice
	 * Rolls the dice for a specified ability.
	 *
	 * Parameters:
	 * ability - The ability being rolled
	 *
	 * Returns:
	 * The result of the dice roll
	 */
	private int rollDice( Ability ability )
	{
		int result = ability.getBase();

		for ( int i = 0; i < ability.getNumDice(); i++ )
		{
			result = result + (int)( Math.random() * ability.getTypeDice() + 1.0 );
		} // end for

		return result;
	} // end method
	
	/*
	 * checkEmptyTeams
	 * Removes empty teams from the team list.
	 */
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
	
	/*
	 * loadAbilities
	 * Uses the FileLoader to load a list of abilities from a csv file. Prompt the user to input a
	 * filename. If the load fails, the user had the option to input a different filename or cancel.
	 *
	 * Returns:
	 * The list of all abilities
	 */
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
	

	/*
	 * loadCharacters
	 * Uses the FileLoader to load a list of characters from a csv file and sort them into teams. 
	 * Prompt the user to input a filename. If the load fails, the user had the option to input a 
	 * different filename or cancel.
	 *
	 * Parameters:
	 * abilityList - The list of all abilities 
	 */
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
