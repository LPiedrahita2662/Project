/**
* Lead Author(s):
* @author Luke Piedrahita
*
* References:
* Morelli, R., & Walde, R. (2016).
* Java, Java, Java: Object-Oriented Problem Solving
* https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
*
* Version: 2026-04-29
*/

package model;

/**
 * Purpose: Represents a door in the game. Door can be locked or unlocked with an exit code. 
 * A door can also be a final door, which means it leads to the end of the game.
 */
public class Door
{
	private boolean isFinalDoor;
	private String unlockCode;
	
	/**
	 * Initializes a new Door object with the locked status, final door status, and exit code.
	 * 
	 * @param isFinalDoor whether the door is a final door or not
	 * @param exitCode the code required to unlock the door
	 */
	public Door (boolean isFinalDoor, String exitCode)
	{
		this.isFinalDoor = isFinalDoor;
		this.unlockCode = exitCode;
	}
	
	/**
	 * Checks if the provided exit code matches the door's unlock code.
	 * 
	 * @param exitCode the exit code entered by the player
	 * @return true if the exit code matches the door's unlock code, false otherwise
	 */
	public boolean checkExitCode(String exitCode)
	{
		return this.unlockCode.equals(exitCode);
	}
	
	/**
	 * Returns whether the door is a final door or not.
	 * 
	 * @return true if the door is a final door, false otherwise
	 */
	public boolean isFinalDoor()
	{
		return isFinalDoor;
	}
	
	/**
	 * Returns a string representation of the door.
	 *
	 * @return a string with the isFinalDoor status
	 */
	@Override
	public String toString()
	{
	    return "isFinalDoor = " + isFinalDoor;
	}
}