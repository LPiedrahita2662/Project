/**
* Lead Author(s):
* @author Luke Piedrahita
*
* References:
* Morelli, R., & Walde, R. (2016).
* Java, Java, Java: Object-Oriented Problem Solving
* https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
*
* Version: 2026-05-04
*/

package model;

import javax.swing.ImageIcon;

/**
 * Purpose: Represents a locked item in the game. A locked item can be solved and has an unlock code.
 * 
 * LockedItem is-a Item
 */
public class LockedItem extends Item
{
	private String unlockCode;
	private boolean isSolved;
	
	/**
	 * Initializes a new LockedItem with the name, hint text, image, xy positions, image size, and unlock code.
	 * 
	 * @param name the name of the locked item
	 * @param hintText the hint text associated with the locked item
	 * @param image the image representing the locked item
	 * @param xPosition the x position of the locked item in the room
	 * @param yPosition the y position of the locked item in the room
	 * @param unlockCode the code required to solve the locked item
	 * @param size the size of the locked item
	 */
	public LockedItem(String name, String hintText, ImageIcon image, int xPosition, int yPosition, String unlockCode, int size)
	{
	    super(name, hintText, image, xPosition, yPosition, size);
	    this.unlockCode = unlockCode;
	    isSolved = false;
	}
	
	/**
	 * Checks if the given code matches the item's unlock code. If it matches then item isSolved.
	 * 
	 * @param code the code entered by the player to solve the locked item
	 * @return true if the code matches the unlock code and the item is solved, false otherwise
	 */
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
	
	/**
	 * Returns whether the locked item has been solved or not.
	 * 
	 * @return true if the locked item is solved, false otherwise
	 */
	public boolean isSolved()
	{
		return isSolved;
	}
	
	/**
	 * Returns a string representation of the locked item.
	 *
	 * @return a string with the name and isSolved status
	 */
	@Override
	public String toString()
	{
	    return "name = " + getName() + ", isSolved = " + isSolved;
	}
}