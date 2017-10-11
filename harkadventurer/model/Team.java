package harkadventurer.model;

import java.util.*;
import java.io.Serializable;

/**
 * Team-type objects have a name and store a list of characters.
 *
 * @author Jack McNair 18927430
 * @since 09/10/2017
 */
public class Team implements Targetable, FriendlyObserver, EnemyObserver, Serializable
{
	// ===== CLASSFIELDS =====
	private String name;	
	private LinkedList<Character> characters;
	
	// ===== CONSTRUCTORS =====
	/**
	 * Constructs a team with the specified name and no characters.
	 *
	 * @param inName The team's name
	 */
	public Team( String inName )
	{
		setName( inName );
		characters = new LinkedList<Character>();
	} // end constructor

	/**
	 * Constructs a team with the specified name, containing the characters in the
	 * provided list.
	 *
	 * @param inName The team's name
	 * @param inCharacters The characters in the team
	 */
	public Team( String inName, LinkedList<Character> inCharacters )
	{
		setName( inName );
		setCharacters( inCharacters );
	} // end constructor
	
	// ===== ACCESSORS =====
	/**
	 * Returns the team's name.
	 * @return The team's name
	 */
	public String getName() { return name; }

	/**
	 * Returns a list of the team's characters.
	 * @return A list of the team's characters
	 */
	public LinkedList<Character> getCharacters() { return characters; }
	
	/**
	 * Returns the number of characters in the team.
	 * @return The number of characters in the team.
	 */
	public int getNumCharacters() { return characters.size(); }

	// ===== MUTATORS =====
	/**
	 * Sets the team's name.
	 * 
	 * @param inName The desired name
	 */
	public void setName( String inName )
	{
		name = new String( inName );
	} // end mutator
	
	/**
	 * Sets the team's character list to be equal to the input list.
	 *
	 * @param inCharacters A list of the desired characters
	 */
	public void setCharacters( LinkedList<Character> inCharacters )
	{
		characters = (LinkedList<Character>)inCharacters.clone();
	}
	
	/**
	 * Adds a character to the team.
	 *
	 * @param inChara The character to be added
	 */
	public void addCharacter( Character inChara )
	{
		characters.add( inChara );
	} // end mutator
	
	// ===== METHODS =====
	/**
	 * Changes the health of all characters in the team by the specified amount.
	 * A positive amount heals, a negative amount damages.
	 *
	 * @param amount The amount of damage or healing
	 */
	public void changeHealth( int amount )
	{
		for ( Character chara : characters )
		{
			chara.changeHealth( amount );
		} // end for
	} // end method
	
	/**
	 * Returns a string containing the team's information. This includes the name of
	 * the team and the name of each character in the team.
	 *
	 * @return A string containing the team's information
	 */
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
	
	/**
	 * Called when a member of the team dies, removes the dead character from the
	 * team's character list.
	 */
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
	
	/**
	 * Called when a member of the enemy team dies, heals all characters in the team.
	 * Characters are healed for a certain percentage of their maximum hit points,
	 * determined by the character that died.
	 *
	 * @param healAmount The percentage of the team's max health to be healed
	 */
	public void enemyDeathUpdate( double healAmount )
	{
		int healBy;
		
		for ( Character chara : characters )
		{
			healBy = (int)( (double)chara.getMaxHealth() * healAmount );
			chara.changeHealth( healBy );
		} // end for
	} // end method
} // end class
