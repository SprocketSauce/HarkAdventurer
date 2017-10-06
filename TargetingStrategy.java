import java.util.*;

public interface TargetingStrategy
{
	LinkedList<Targetable> getTargets( Team allyTeam, LinkedList<Team> enemyTeams );
	String toString();
} // end interface
