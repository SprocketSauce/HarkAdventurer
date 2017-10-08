import java.util.*;

public class TestTeams
{
	public static void main( String[] args )
	{
		LinkedList<Ability> abilities;
		LinkedList<Team> teams;
		
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
