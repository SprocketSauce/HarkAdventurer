package harkadventurer.control;

import java.util.*;
import java.io.*;
import harkadventurer.model.Targetable;
import harkadventurer.model.Team;

/**
 * The targeting logic for an ability that targets multiple enemies.
 *
 * @author Jack McNair 18927430
 * @since 09/10/2017
 */
public class TargetMultiEnemy implements TargetingStrategy, Serializable
{
	/**
	 * Returns all valid targets of an ability, in this case, all enemy teams.
	 *
	 * @return A list containing all enemy teams
	 */
	public LinkedList<Targetable> getTargets( Team allyTeam, LinkedList<Team> enemyTeams )
	{
		LinkedList<Targetable> targets = new LinkedList<Targetable>();
		
		for ( Team team : enemyTeams )
		{
			targets.add( (Targetable)team );
		} // end for
		
		return targets;
	} // end method
	
	/**
	 * Returns a string describing the ability's targeting logic, in this case "Enemy
	 * Team".
	 */
	public String toString()
	{
		return "Enemy Team";
	} // end method
} // end class
