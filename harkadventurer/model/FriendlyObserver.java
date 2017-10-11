package harkadventurer.model;

/**
 * A class can implement the FriendlyObserver interface when it wants to be informed of
 * the death of friendly characters.
 *
 * @author Jack McNair 18927430
 * @since 09/10/2017
 */
public interface FriendlyObserver
{
	/**
	 * This method is called when a friendly character is killed.
	 */
	void friendlyDeathUpdate();
} // end interface
