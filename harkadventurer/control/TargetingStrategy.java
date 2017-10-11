package harkadventurer.control;

import java.util.*;
import harkadventurer.model.Targetable;
import harkadventurer.model.Team;

/**
 * Classes that define an ability's target selection should implement this interface.
 *
 * @author Jack McNair 18927430
 * @since 09/10/2017
 */
public interface TargetingStrategy
{
	/**
	 * When provided with a list of allies and enemies, will return a list of valid 
	 * targets for an ability.
	 *
	 * @param allyTeam The allied team
	 * @param enemyTeams A list of enemy teams
	 * @return A list of valid targets
	 */
	LinkedList<Targetable> getTargets( Team allyTeam, LinkedList<Team> enemyTeams );
	
	/**
	 * Returns a string describing the ability's targeting logic. For example, if
	 * the ability targets a single ally, this will return "Single Ally".
	 *
	 * @return A string describing the ability's targeting logic
	 */
	String toString();
} // end interface
