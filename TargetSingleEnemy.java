import java.util.*;
import java.io.*;

public class TargetSingleEnemy implements TargetingStrategy, Serializable
{
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
	
	public String toString()
	{
		return "Single Enemy";
	} // end method
} // end class
