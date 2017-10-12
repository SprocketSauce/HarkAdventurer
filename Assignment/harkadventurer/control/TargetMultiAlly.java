package harkadventurer.control;

import java.util.*;
import java.io.*;
import harkadventurer.model.Targetable;
import harkadventurer.model.Team;

/**
 * The targeting logic for an ability that targets multiple allies.
 *
 * @author Jack McNair 18927430
 * @since 09/10/2017
 */
public class TargetMultiAlly implements TargetingStrategy, Serializable
{
	/**
	 * Returns all valid targets of an ability, in this case, the allied team.
	 *
	 * @param allyTeam The allied team
	 * @param enemyTeams A list of enemy teams
	 * @return A list containing only the allied team
	 */
	public LinkedList<Targetable> getTargets( Team allyTeam, LinkedList<Team> enemyTeams )
	{
		LinkedList<Targetable> targets = new LinkedList<Targetable>();
		
		targets.add( allyTeam );
		
		return targets;		
	} // end method
	
	/**
	 * Returns a string describing the ability's targeting logic, in this case "Allied
	 * Team".
	 *
	 * @return A string describing the ability's targeting logic
	 */
	public String toString()
	{
		return "Allied Team";
	} // end method
} // end class
