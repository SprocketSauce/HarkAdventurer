import java.util.*;
import java.io.Serializable;

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

	//	=====CONSTRUCTORS=====
	//	-----Default Constructor-----
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
	public Character( String inName, int inMax, LinkedList<Ability> inAbility ) throws CharacterException
	{
		setName( inName );
		setMaxHealth( inMax );
		setCurHealth( inMax );
		abilities = inAbility;
	} // end constructor

	// =====ACCESSORS=====
	public String getName() { return new String( name ); }
	public int getMaxHealth() { return maxHealth; }
	public int getCurHealth() { return curHealth; }
	public LinkedList<Ability> getAbilities() { return (LinkedList<Ability>)abilities.clone(); }
	
	public LinkedList<FriendlyObserver> getFriendlyObservers() 
	{ 
		return (LinkedList<FriendlyObserver>)fObservers.clone(); 
	} // end accessor
	
	public LinkedList<EnemyObserver> getEnemyObservers() 
	{ 
		return (LinkedList<EnemyObserver>)eObservers.clone(); 
	} // end accessor

	// =====MUTATORS=====
	public void setName ( String inName )
	{
		name = new String( inName );
	} // end mutator

	public void setMaxHealth( int inMax ) throws CharacterException
	{
		if ( inMax < 20 )
		{
			throw new CharacterException( "Max Health must be at least 20" );
		}
		else
		{
			maxHealth = inMax;
		}
	} // end mutator

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

	public void changeHealth( int amount )
	{
		setCurHealth( curHealth + amount );
	} // end mutator

	public void addAbility( Ability inAbility )
	{
		abilities.add( inAbility );
	} // end mutator
	
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
