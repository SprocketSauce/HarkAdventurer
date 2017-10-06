import java.util.*;

public class UI
{
    public static void titleCard()
    {
        System.out.println( "=================================================\nHARK, ADVENTURERS!\nBy Jack McNair 18927430\n=================================================\n" );
    } // end method
    
    public static void mainMenu()
    {
        int selection;
        Game game;

		selection = inputInt( 1, 2, "1.    Start Game\n2.    Exit\n\nSelection: " );
		
		if ( selection == 1 )
		{
			game = new Game();
			game.startGame();
		} // end if
		else
		{
			System.out.println( "Have a nice day." );
		}
    } // end method
    
    public static String inputAbilities()
    {
    	return inputString( "Enter abilities file: " );    	  	
    } // end method
    
    public static String inputCharacters()
    {
    	return inputString( "Enter characters file: ");
    }

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
}
