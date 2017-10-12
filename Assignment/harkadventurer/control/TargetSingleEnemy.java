package harkadventurer.control;

import java.util.*;
import java.io.*;
import harkadventurer.model.Targetable;
import harkadventurer.model.Team;
import harkadventurer.model.Character;

/**
 * The targeting logic for an ability that targets a single enemy.
 *
 * @author Jack McNair 18927430
 * @since 09/10/2017
 */
public class TargetSingleEnemy implements TargetingStrategy, Serializable
{
	/**
	 * Returns all valid targets of an ability, in this case, all enemy characters.
	 *
	 * @return A list containing all enemy characters
	 */
	public LinkedList<Targetable> getTargets( Team allyTeam, LinkedList<Team> enemyTeams )
	{
		LinkedList<Character> characters = new LinkedList<Character>();
		LinkedList<Targetable> targets = new LinkedList<Targetable>();
		
		for ( Team team : enemyTeams )
		{
			characters.addAll( team.getCharacters() );
		}
		
		for ( Character chara : characters )
		{
			targets.add( (Targetable)chara );
		} // end for
		
		return targets;
	} // end method
	
	/**
	 * Returns a string containing the ability's targeting logic, in this case "Single
	 * Enemy"
	 */
	public String toString()
	{
		return "Single Enemy";
	} // end method
} // end class
