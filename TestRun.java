import java.util.*;

public class TestRun
{
	public static void main( String[] args )
	{
		GameController game = new GameController();
		
		UI.titleCard();
		UI.mainMenu( game );
	} // end main
} // end class
