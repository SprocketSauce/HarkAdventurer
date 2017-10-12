package harkadventurer.control;

import java.util.*;
import harkadventurer.model.Team;
import harkadventurer.model.Character;
import harkadventurer.model.PlayerCharacter;

public class TeamManager
{
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
