import java.util.*;

public class TestTeams
{
	public static void main( String[] args )
	{
		LinkedList<Ability> abilities;
		LinkedList<Team> teams;
		Ability ability;
		
		try
		{
			abilities = FileLoader.readAbilities( "TestAbilities.txt" );
			teams = FileLoader.readCharacters( "TestCharacters.txt", abilities );
			
			System.out.println( "INITIAL STATE\n" );
			printTeams( teams );
			
			teams.get(0).changeHealth( -20 );
			System.out.println( "\nDAMAGE ADVENTURERS\n" );
			printTeams( teams );
			
			teams.get(1).getCharacters().get(0).changeHealth( -100 );
			System.out.println( "\nKILL MONSTER\n" );
			printTeams( teams );
			
			ObjectIO.saveGame( teams, "TestSave" );
			teams = null;
			teams = ObjectIO.loadGame( "TestSave" );
			
			System.out.println( "\nSAVE/LOAD\n" );
			printTeams( teams );
			
			System.out.println( "\nFIRE BOLT ON THRAWN\n" );
			ability = teams.get(0).getCharacters().get(0).getAbilities().get(1);
			System.out.println( ability.toString() + "\n" );
			ability.getEffect().resolve( teams.get(1).getCharacters().get(0), ability.getDamage() );
			printTeams( teams );
		}
		catch ( CharacterException e )
		{
			System.out.println( e.getMessage() );
		}
		catch ( AbilityException e )
		{
			System.out.println( e.getMessage() );
		}
	}
	
	public static void printTeams( LinkedList<Team> teams )
	{
		LinkedList<Character> characters;
		
		for ( Team team : teams )
		{
			System.out.println( team.toString() );
			characters = team.getCharacters();
			
			for ( Character chara : characters )
			{
				System.out.println( chara.toString() );
			}
			
			System.out.println();
		}
	}
}
