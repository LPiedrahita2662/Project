/**
* Lead Author(s):
* @author lukepiedrahita; student ID
* @author Full name; student ID
* <<Add additional lead authors here>>
*
* Other Contributors:
* Full name; student ID or contact information if not in class
* <<Add additional contributors (mentors, tutors, friends) here, with contact information>>
*
* References:
* Morelli, R., & Walde, R. (2016).
* Java, Java, Java: Object-Oriented Problem Solving
* https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
*
* <<Add more references here>>
*
* Version: 2026-04-29
*/

/**
 * Purpose: The reponsibility of Door is ...
 *
 * Door is-a ...
 * Door is ...
 */

public class Door
{
	private boolean isLocked;
	private boolean isFinalDoor;
	private String exitCode;
	
	public Door (boolean isLocked, boolean isFinalDoor, String exitCode)
	{
		this.isLocked = isLocked;
		this.isFinalDoor = isFinalDoor;
		this.exitCode = exitCode;
	}
	
	public boolean checkExitCode(String exitCode)
	{
		return (this.exitCode == exitCode);
	}
	
	public void unlockDoor()
	{
		isLocked = false;
	}
	
	public boolean isLocked()
	{
		return isLocked;
	}
	
	public boolean isFinalDoor()
	{
		return isFinalDoor;
	}
}