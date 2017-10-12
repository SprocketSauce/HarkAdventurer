package harkadventurer.excep;

/**
 * Thrown when an ability is given an invalid input, or when an Ability file is incorrectly formatted.
 *
 * @author Jack McNair 18927430
 * @since 10/10/2017
 */
public class AbilityException extends Exception
{
	/**
	 * Constructs an AbilityException with the specified message.
	 *
	 * @param message The detail message
	 */
	public AbilityException( String message )
	{
		super( message );
	}
}
