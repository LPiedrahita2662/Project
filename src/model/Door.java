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
	private boolean isLocked;
	private boolean isFinalDoor;
	private String unlockCode;
	
	/**
	 * Initializes a new Door object with the locked status, final door status, and exit code.
	 * 
	 * @param isLocked whether the door is locked or not
	 * @param isFinalDoor whether the door is a final door or not
	 * @param exitCode the code required to unlock the door
	 */
	public Door (boolean isLocked, boolean isFinalDoor, String exitCode)
	{
		this.isLocked = isLocked;
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
	 * Unlocks the door by setting the isLocked variable to false.
	 */
	public void unlockDoor()
	{
		isLocked = false;
	}
	
	/**
	 * Returns whether the door is currently locked or not.
	 * 
	 * @return true if the door is locked, false otherwise
	 */
	public boolean isLocked()
	{
		return isLocked;
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
	
	public String toString()
	{
	    return "isLocked = " + isLocked + ", isFinalDoor = " + isFinalDoor;
	}
}