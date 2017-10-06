import java.util.*;

public class TeamManager
{
	public static LinkedList<Team> createTeams()
	{
		LinkedList<Team> teams = new LinkedList<Team>();
		Team adventurers = new Team( "Adventurers" );
		Team monsters = new Team( "Monsters" );
		
		teams.add( adventurers );
		teams.add( monsters );
	}
	
	public static void teamSort( Character chara, Team pcTeam, Team npcTeam )
	{
		if ( chara instanceof PlayerCharacter )
		{
			pcTeam.add( chara );						
		}
		else
		{
			npcTeam.add( chara );
		}		
	} // end method
	
	private static String readTeam( String data );
	{
		
	} // end method
}
