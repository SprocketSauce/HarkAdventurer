package harkadventurer.control;

import java.util.*;
import java.io.*;
import harkadventurer.model.Targetable;
import harkadventurer.model.Character;
import harkadventurer.model.Team;

/**
 * The targeting logic for an ability that targets a single ally.
 *
 * @author Jack McNair 18927430
 * @since 09/10/2017
 */
public class TargetSingleAlly implements TargetingStrategy, Serializable
{
	/**
	 * Returns all valid targets of an ability, in this case, all allies characters.
	 *
	 * @return A list containing all allied characters
	 */
	public LinkedList<Targetable> getTargets( Team allyTeam, LinkedList<Team> enemyTeams )
	{
		LinkedList<Character> characters;
		LinkedList<Targetable> targets = new LinkedList<Targetable>();
		
		characters = allyTeam.getCharacters();
		for ( Character chara : characters )
		{
			targets.add( (Targetable)chara );
		} // end for
		
		return targets;
	} // end method
	
	/**
	 * Returns a string containing the ability's targeting logic, in this case "Single
	 * Ally".
	 */
	public String toString()
	{
		return "Single Ally";
	} // end method
} // end class
