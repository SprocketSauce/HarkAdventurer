import java.util.*;
import java.io.Serializable;

public class Team implements Targetable, FriendlyObserver, EnemyObserver, Serializable
{
	// ===== CLASSFIELDS =====
	private String name;	
	private LinkedList<Character> characters;
	
	// ===== CONSTRUCTORS =====
	public Team( String inName )
	{
		setName( inName );
		characters = new LinkedList<Character>();
	} // end constructor

	public Team( String inName, LinkedList<Character> inCharacters )
	{
		setName( inName );
		setCharacters( inCharacters );
	} // end constructor
	
	// ===== ACCESSORS =====
	public String getName() { return name; }
	public LinkedList<Character> getCharacters() { return characters; }
	
	public int getNumCharacters() { return characters.size(); }
	
	public Character getCharacter( int index ) { return characters.get( index ); }
	
	// ===== MUTATORS =====
	public void setName( String inName )
	{
		name = new String( inName );
	} // end mutator
	
	public void setCharacters( LinkedList<Character> inCharacters )
	{
		characters = (LinkedList<Character>)inCharacters.clone();
	}
	
	public void addCharacter( Character inChara )
	{
		characters.add( inChara );
	} // end mutator
	
	// ===== METHODS =====
	public void changeHealth( int amount )
	{
		for ( Character chara : characters )
		{
			chara.changeHealth( amount );
		} // end for
	} // end method
	
	public String toString()
	{
		String outStr;
		
		outStr = "Team Name: " + name + "\nCharacters:    ";
		for ( Character chara : characters )
		{
			outStr += chara.getName() + "    ";
		} // end for
		
		return outStr;
	}
	
	public void friendlyDeathUpdate()
	{
		Character chara;
		boolean found = false;
		ListIterator<Character> iter = characters.listIterator(0);
		
		while ( !found )
		{
			chara = iter.next();
			
			if ( chara.getCurHealth() <= 0 )
			{
				iter.remove();
				found = true;
			} // end if
		} // end while
	} // end method
	
	public void enemyDeathUpdate( double healAmount )
	{
		int healBy;
		
		for ( Character chara : characters )
		{
			healBy = (int)( (double)chara.getMaxHealth() * healAmount );
			chara.changeHealth( healBy );
		} // end for
	} // end method
}
