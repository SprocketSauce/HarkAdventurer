package harkadventurer.excep;

/**
 * Thrown when a character is given an invalid input, or when a character file is incorrectly 
 * formatted.
 *
 * @author Jack McNair 18927430
 * @since 09/10/2017
 */
public class CharacterException extends Exception
{
	/**
	 * Constructs a CharacterException with the specified message.
	 *
	 * @param message The detail message
	 */
	public CharacterException( String message )
	{
		super( message );
	}
}
