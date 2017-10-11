package harkadventurer.view;

import java.util.*;
import harkadventurer.control.*;
import harkadventurer.model.*;

public class UI
{
    public static void titleCard()
    {
        System.out.println( "=================================================\nHARK, ADVENTURERS!\nBy Jack McNair 18927430\n=================================================\n" );
    } // end method
    
    public static void mainMenu( GameController game )
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
    
    public static String inputAbilities()
    {
    	return inputString( "Enter abilities file: " );    	  	
    } // end method
    
    public static String inputCharacters()
    {
    	return inputString( "Enter characters file: " );
    } // end method
    
    public static String inputSaveAs()
    {
    	return inputString( "Save as: " );
    } // end method
    
    public static String inputLoadGame()
    {
    	return inputString( "Load game: " );
    } // end method

	public static int inputInt( int min, int max, String prompt )
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

	public static String inputString( String prompt )
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
	
	public static boolean retry( String prompt )
	{
		int selection;
		
		System.out.println( prompt );
		selection = inputInt( 1, 2, "\n1)    Try again\n2)    Cancel\n\nSelection: " );
		
		return ( selection == 1 );
	}
	
	// WORK IN PROGRESS
	public static void roundMenu( GameController game )
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
					System.out.println( "File loaded" );
					break;
				case 4:
					break;
			} // end switch
		} while ( selection == 2 || selection == 3 );		
	} // end method
	
	public static Ability turnMenu( harkadventurer.model.Character chara )
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
	
	public static Ability abilityMenu( LinkedList<Ability> abilities )
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
	
	public static Targetable targetMenu( LinkedList<Targetable> targets )
	{
		int selection;
		String prompt;
		Targetable target;
		
		prompt = "Seletct Target:\n\n";
		for ( int i = 0; i < targets.size(); i++ )
		{
			prompt = prompt + i + ")    " + targets.get(i).toString() + "\n\n";
		} // end for
		prompt = prompt + "Selection: ";
		
		selection = inputInt( 0, targets.size() - 1, prompt );
		
		target = targets.get( selection );
		
		return target;
	} // end method
	
	public static void endGame( Team victors )
	{
		System.out.println( "Congratulations, " + victors.getName() + "!\nYou have vanquished your enemies!" );
	}
}
