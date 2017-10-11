package harkadventurer.model;

import java.util.*;
import java.io.Serializable;
import harkadventurer.model.Targetable;
import harkadventurer.model.Ability;
import harkadventurer.model.FriendlyObserver;
import harkadventurer.model.EnemyObserver;
import harkadventurer.excep.CharacterException;

/**
 * Character-type objects store a character's name, maximum health, current health and
 * list of abilities.
 *
 * @author Jack McNair 18927430
 * @since 09/10/2017
 */
abstract public class Character implements Targetable, Serializable
{
	// ===== CLASSFIELDS =====
	private String name;
	private int maxHealth;
	private int curHealth;
	private LinkedList<Ability> abilities;
	
	// ----- Observers -----
	private LinkedList<FriendlyObserver> fObservers;
	private LinkedList<EnemyObserver> eObservers;

	// =====CONSTRUCTORS=====
	// -----Default Constructor-----
	/**
	 * Constructs a character object with 1 hit point and no abilities or observers.
	 */
	public Character()
	{
		name = null;
		maxHealth = 1;
		curHealth = 1;
		abilities = new LinkedList<Ability>();
		fObservers = new LinkedList<FriendlyObserver>();
		eObservers = new LinkedList<EnemyObserver>();
	} // end constructor

	// -----Alternate Constructor-----
	/**
	 * Constructs a character object with the input name, hit points and abilities.
	 * The character will have full health and no observers.
	 *
	 * @param inName The character's name
	 * @param inMax The character's maximum hit points
	 * @param inAbility A list of the character's abilities
	 * @throws CharacterException If any of the inputs are invalid
	 */
	public Character( String inName, int inMax, LinkedList<Ability> inAbility ) throws CharacterException
	{
		setName( inName );
		setMaxHealth( inMax );
		setCurHealth( inMax );
		abilities = inAbility;
		fObservers = new LinkedList<FriendlyObserver>();
		eObservers = new LinkedList<EnemyObserver>();
	} // end constructor

	// =====ACCESSORS=====
	/**
	 * Returns the character's name.
	 * @return The character's name
	 */
	public String getName() { return new String( name ); }
	
	/**
	 * Returns the character's maximum health.
	 * @return The character's maximum health */
	public int getMaxHealth() { return maxHealth; }

	/**
	 * Returns the character's current health.
	 * @return The character's current health
	 */
	public int getCurHealth() { return curHealth; }

	/**
	 * Returns a list of the character's abilities.
	 * @return A list of the character's abilities
	 */
	public LinkedList<Ability> getAbilities() { return (LinkedList<Ability>)abilities.clone(); }
	
	/**
	 * Returns a list of the character's friendly observers.
	 * @return a list of the character's friendly observers
	 */
	public LinkedList<FriendlyObserver> getFriendlyObservers() 
	{ 
		return (LinkedList<FriendlyObserver>)fObservers.clone(); 
	} // end accessor
	
	/**
	 * Returns a list of the character's enemy observers.
	 * @return a list of the character's enemy observers
	 */
	public LinkedList<EnemyObserver> getEnemyObservers() 
	{ 
		return (LinkedList<EnemyObserver>)eObservers.clone(); 
	} // end accessor

	// =====MUTATORS=====
	/**
	 * Sets the character's name.
	 * 
	 * @param inName The desired name
	 */
	public void setName ( String inName )
	{
		name = new String( inName );
	} // end mutator

	/**
	 * Sets the character's maximum health.
	 * 
	 * @param inMax The desired max healh
	 * @throws CharacterException If the input is less than 20
	 */
	public void setMaxHealth( int inMax ) throws CharacterException
	{
		if ( inMax < 20 )
		{
			throw new CharacterException( "Max Health must be at least 20" );
		}UI
		else
		{
			maxHealth = inMax;
		}
	} // end mutator

	/**
	 * Sets the character's current health. If the input is higher than the
	 * character's maximum health, current health will be set to maximum health.
	 * If the input is 0 or less, the character's observers will be notified that
	 * they have died.
	 *
	 * @param inCur The desired curren health
	 */
	public void setCurHealth( int inCur )
	{
		if ( inCur > maxHealth )
		{
			curHealth = maxHealth;
		}
		else if ( inCur <= 0 )
		{
			curHealth = 0;
			notifyObservers();
		}
		else
		{
			curHealth = inCur;
		}		
	} // end mutator

	/**
	 * Changes the character's current health by the input amount. The character will
	 * be healed if positive, damaged if negative.
	 * 
	 * @param inAmount The amount by which to change the charactet's health
	 */
	public void changeHealth( int amount )
	{
		setCurHealth( curHealth + amount );
	} // end mutator

	/**
	 * Adds an ability to the character's list of abilities.
	 * 
	 * @param inAbility The ability to be added
	 */
	public void addAbility( Ability inAbility )
	{
		abilities.add( inAbility );
	} // end mutator
	
	/**
	 * Two characters are equal
	 */
	@Override
	public boolean equals( Object inObj )
	{
		Character inChara;
		boolean result = false;
		
		if ( inObj instanceof Character )
		{
			inChara = (Character)inObj;
			result = ( name == inChara.getName() && maxHealth == inChara.getMaxHealth() && curHealth == inChara.getCurHealth() && abilities.equals( inChara.getAbilities() ) );
		}
		
		return result;
	} // end method
    
    public String toString()
    {
        String outStr;
        
        outStr = "Name: " + name + "    HP: " + curHealth + "/" + maxHealth + "\nAbilities:    ";
        for( Ability a : abilities )
        {
            outStr += a.getName() + "    ";
        } // end for
        
        return outStr;
    } // end method

    public void registerFriendlyObserver( FriendlyObserver inObv )
    {
        fObservers.add( inObv );
    } // end mutator
    
    public void registerEnemyObserver( EnemyObserver inObv )
    {
        eObservers.add( inObv );
    } // end mutator
    
    abstract public void notifyObservers();
} // end class
