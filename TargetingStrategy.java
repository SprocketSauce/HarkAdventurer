import java.util.*;
import java.io.Serializable;

public interface TargetingStrategy
{
	LinkedList<Targetable> getTargets( Team allyTeam, LinkedList<Team> enemyTeams );
	String toString();
} // end interface
