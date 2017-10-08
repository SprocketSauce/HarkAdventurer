import java.util.*;
import java.io.*;

public class TargetMultiAlly implements TargetingStrategy, Serializable
{
	public LinkedList<Targetable> getTargets( Team allyTeam, LinkedList<Team> enemyTeams )
	{
		LinkedList<Targetable> targets = new LinkedList<Targetable>();
		
		targets.add( allyTeam );
		
		return targets;		
	} // end method
	
	public String toString()
	{
		return "Allied Team";
	} // end method
} // end class
