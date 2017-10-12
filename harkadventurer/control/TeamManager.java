package harkadventurer.control;

import java.util.*;
import harkadventurer.model.Team;
import harkadventurer.model.Character;
import harkadventurer.model.PlayerCharacter;

/**
 * A factory for creating Team-type objects and sorting characters into them.
 *
 * @author Jack McNair 18927430
 * @since 10/10/2017
 */
public class TeamManager
{
	/**
	 * Sorts a list of characters into two teams. Non-player characters will be sorted into the
	 * "Monsters" team, player characters will be sorted into the "Adventurers" team. The teams
	 * are registered as the Characters' observers.
	 *
	 * @param characters The list of characters to be sorted
	 * @return A list containing the two teams. The first team contains all player characters, the
	 * second team contains all non-player characters.
	 */
	public LinkedList<Team> teamSort( LinkedList<Character> characters )
	{
		LinkedList<Team> teams = new LinkedList<Team>();
		Team pcTeam = new Team( "Adventurers" );
		Team npcTeam = new Team( "Monsters" );
		
		for ( Character c : characters )
		{
			if ( c instanceof PlayerCharacter )
			{
				pcTeam.addCharacter( c );
				c.registerFriendlyObserver( pcTeam );
				c.registerEnemyObserver( npcTeam );
			}
			else
			{
				npcTeam.addCharacter( c );
				c.registerFriendlyObserver( npcTeam );
				c.registerEnemyObserver( pcTeam );
			} // end if
		} // end for
		
		teams.add( pcTeam );
		teams.add( npcTeam );
		
		return teams;			
	} // end method
} // end class
