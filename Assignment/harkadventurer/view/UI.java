package harkadventurer.view;

import java.util.*;
import harkadventurer.control.*;
import harkadventurer.model.*;

/**
 * The user interface manages all menus and user input.
 *
 * @author Jack McNair 18927430
 * @since 10/10/2017
 */
public class UI
{
	/**
	 * Displays the title card.
	 */
	public void titleCard()
	{
		System.out.println( "=================================================\nHARK, ADVENTURERS!\nBy Jack McNair 18927430\n=================================================\n" );
	} // end method

	/**
	 * Displays the main menu. Gives the user the option to start a new game, load a game in 
	 * progress or exit.
	 *
	 * @param game The game controller to be used
	 */
	public void mainMenu( GameController game )
	{
		int selection;

		selection = inputInt( 1, 3, "1.    New Game\n2.    Load Game\n3.    Exit\n\nSelection: " );
		
		if ( selection == 1 )
		{
			game.startGame();
		}
		else if ( selection == 2 )
		{
			game.loadGame();
		}
		else
		{
			System.out.println( "Have a nice day." );
		} // end if
	} // end method
	
	/**
	 * Prompts the user to input the filename of an abilities file.
	 *
	 * @return The input filename
	 */
	public String inputAbilities()
	{
		return inputString( "Enter abilities file: " );    	  	
	} // end method

	/**
	 * Prompts the user to input the filename of an characters file.
	 *
	 * @return The input filename
	 */
	public String inputCharacters()
	{
		return inputString( "Enter characters file: " );
	} // end method

	/**
	 * Prompts the user to input a filename to Save As.
	 *
	 * @return The input filename
	 */
	public String inputSaveAs()
	{
		return inputString( "Save as: " );
	} // end method

	/**
	 * Prompts the user to input the name of a game to load.
	 *
	 * @return The input filename
	 */
	public String inputLoadGame()
	{
		return inputString( "Load game: " );
	} // end method

	/**
	 * Prompts the user to input an integer. Loops if the integer is not within a specified range.
	 *
	 * @param min The lower limit
	 * @param max The upper limit
	 * @param prompt The prompt to be displayed to the user
	 * @return The input integer
	 */
	public int inputInt( int min, int max, String prompt )
	{
		Scanner sc = new Scanner( System.in );
		int input;

		System.out.print( prompt );
		input = sc.nextInt();
		
		while ( input < min || input > max )
		{
			System.out.println( "Invalid input\n" );
			System.out.print( prompt );
			input = sc.nextInt();
		} // end while

		return input;
	} // end method

	/**
	 * Prompts the user to input a string. Loops if they do not input anything.
	 *
	 * @param prompt The prompt to be displayed to the user
	 * @return The input string
	 */
	public String inputString( String prompt )
	{
		Scanner sc = new Scanner( System.in );
		String input;

		System.out.print( prompt);
		input = sc.nextLine();

		while ( input.equals( "" ) )
		{
			System.out.println( "No input detected" );
			System.out.print( prompt );
			input = sc.nextLine();
		} // end while

		return input;
	}
	
	/**
	 * Asks the user if they want to try something again. Returns true if they do, false if they
	 * don't.
	 *
	 * @param prompt The prompt to be displayed to the user
	 * @return Whether or not the user wishes to try again
	 */
	public boolean retry( String prompt )
	{
		int selection;
		
		System.out.println( prompt );
		selection = inputInt( 1, 2, "\n1)    Try again\n2)    Cancel\n\nSelection: " );
		
		return ( selection == 1 );
	}
	
	/**
	 * Displays the functionality available at the start of the round. The user is given the option
	 * to start the round, save the game, load a game or exit.
	 *
	 * @param game The game controller to be used
	 */
	public void roundMenu( GameController game )
	{
		int selection;
		
		System.out.println( "=================================================\n NEW ROUND!\n=================================================\n" );
		
		do
		{
			selection = inputInt( 1, 4, "1)    Next Round\n2)    Save Game\n3)    Load Game\n4)    Exit\n\nSelection: ");
			
			switch ( selection )
			{
				case 1:
					game.playRound();
					break;
				case 2:
					game.saveGame();
					System.out.println( "File saved" );
					break;
				case 3:
					game.loadGame();
					break;
				case 4:
					break;
			} // end switch
		} while ( selection == 2 );		
	} // end method
	
	/**
	 * Gives a player the option to use an ability or pass. If they use an ability, the ability menu
	 * is called.
	 *
	 * @param chara The character taking their turn
	 * @return The ability the player chooses. Returns null if they pass
	 */
	public Ability turnMenu( harkadventurer.model.Character chara )
	{
		int selection = 0;
		Ability ability = null;
		
		while ( ability == null && selection != 2 )
		{
			System.out.println( "\n" + chara.getName() + "'s turn\n" );
			selection = inputInt( 1, 2, "1)    Use Ability\n2)    Pass\n\nSelection: " );
			
			if ( selection == 1 )
			{
				ability = abilityMenu( chara.getAbilities() );
			} // end if			
		} // end while	
		
		return ability;	
	} // end method
	
	/**
	 * Prompts the player to select an ability or cancel. If they choose an ability, the targeting
	 * menu is called.
	 *
	 * @param abilities The list of abilities available to the character
	 * @return The ability chosen by the player. Returns null if they cancel
	 */
	public Ability abilityMenu( LinkedList<Ability> abilities )
	{
		int selection;
		String prompt;
		Ability ability = null;
		
		prompt = "Select Ability:\n\n";		
		for ( int i = 0; i < abilities.size(); i++ )
		{
			prompt = prompt + i + ")    " + abilities.get(i).toString() + "\n\n";
		} // end for		
		prompt = prompt + abilities.size() + ")    Cancel\n\nSelection: ";
		
		selection = inputInt( 0, abilities.size(), prompt );
		
		if ( selection != abilities.size() )
		{
			ability = abilities.get( selection );
		} // end if
		
		return ability;
	} // end method
	
	/**
	 * Prompts the player to choose a target for an ability.
	 *
	 * @param targets The list of valid targets
	 * @return The target the player selects
	 */
	public Targetable targetMenu( LinkedList<Targetable> targets )
	{
		int selection;
		String prompt;
		Targetable target;
		
		prompt = "Select Target:\n\n";
		for ( int i = 0; i < targets.size(); i++ )
		{
			prompt = prompt + i + ")    " + targets.get(i).toString() + "\n\n";
		} // end for
		prompt = prompt + "Selection: ";
		
		selection = inputInt( 0, targets.size() - 1, prompt );
		
		target = targets.get( selection );
		
		return target;
	} // end method

	/**
	 * Informs the user that a file has been successfully loaded.
	 */
	public void fileLoaded()
	{
		System.out.println( "File successfully loaded" );
	}
	
	/**
	 * Congratulates the victorious team on their glorious triumph.
	 *
	 * @param victors The victorious team
	 */
	public void endGame( Team victors )
	{
		System.out.println( "Congratulations, " + victors.getName() + "!\nYou have vanquished your enemies!" );
	}
}
