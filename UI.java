import java.util.*;

public class UI
{
    public static void titleCard()
    {
        System.out.println( "=================================================\nHARK, ADVENTURERS!\nBy Jack McNair 18927430\n=================================================\n" );
    } // end method
    
    public static int mainMenu()
    {
        int selection;

		selection = inputInt( 1, 2, "1.    Load files\n2.    Exit\n\nSelection: " );
		return selection;	
    } // end method

	public static int inputInt( int min, int max, String prompt )
	{
		Scanner sc = new Scanner( System.in );
		int input;

		System.out.print( prompt );
		input = sc.nextInt();
		
		while ( input < min || input > max )
		{
			System.out.println( "Invalid input" );
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
			input = sc.nextLine();
		} // end while

		return input;
	}
}
