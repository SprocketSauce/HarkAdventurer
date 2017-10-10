import java.io.Serializable;

public class Damage implements AbilityEffect, Serializable
{
	public void resolve( Targetable target, int amount )
	{
		target.changeHealth( -amount );		
	} // end method
	
	public String toString()
	{
		return "Damage";
	} // end method
} // end class
