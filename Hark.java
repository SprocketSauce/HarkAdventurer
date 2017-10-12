import java.util.*;
import harkadventurer.control.*;
import harkadventurer.view.UI;

/**
 * Runs the combat tool.
 */
public class Hark
{
	public static void main( String[] args )
	{
		UI ui = new UI();

		CharacterFactory charFact = new CharacterFactory();
		AbilityFactory abilFact = new AbilityFactory();
		TeamManager manager = new TeamManager();
		FileLoader loader = new FileLoader( charFact, abilFact, manager );

		ObjectIO objLoader = new ObjectIO();
		
		GameController game = new GameController( ui, loader, objLoader );
		
		ui.titleCard();
		ui.mainMenu( game );
	} // end main
} // end class
