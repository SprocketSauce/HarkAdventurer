package harkadventurer.control;

import java.util.*;
import harkadventurer.model.Targetable;
import harkadventurer.model.Team;

public interface TargetingStrategy
{
	LinkedList<Targetable> getTargets( Team allyTeam, LinkedList<Team> enemyTeams );
	String toString();
} // end interface
