public class TestUI
{
	public static void main( String[] args )
	{
		int selection;

		UI.titleCard();
		selection = UI.mainMenu();
		while ( selection != 2 )
		{
			System.out.println( "Loading!" );
			selection = UI.mainMenu();
		} // end while
	}
}
