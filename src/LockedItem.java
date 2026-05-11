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
* Version: 2026-05-04
*/

import javax.swing.ImageIcon;

/**
 * Purpose: The reponsibility of Lockeditem is ...
 *
 * Lockeditem is-a ...
 * Lockeditem is ...
 */
public class LockedItem extends Item
{
	private String unlockCode;
	private boolean isSolved;
	
	public LockedItem(String name, String hintText, ImageIcon image, int xPosition, int yPosition, String unlockCode)
	{
		super(name, hintText, image, xPosition, yPosition);
		this.unlockCode = unlockCode;
		isSolved = false;
	}
	
	public String getUnlockCode()
	{
		return unlockCode;
	}
	
	public boolean checkSolved(String code)
	{
		if (code.equals(unlockCode))
		{
			isSolved = true;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean isSolved()
	{
		return isSolved;
	}
	
	public String toString()
	{
	    return "name = " + getName() + ", isSolved = " + isSolved;
	}
}