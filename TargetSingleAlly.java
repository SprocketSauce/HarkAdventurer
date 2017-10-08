import java.util.*;
import java.io.*;

public class TargetSingleAlly implements TargetingStrategy, Serializable
{
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
	
	public String toString()
	{
		return "Single Ally";
	} // end method
} // end class
