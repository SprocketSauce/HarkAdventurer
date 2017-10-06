import java.util.*;

public class Team implements Targetable, Observer
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
}
