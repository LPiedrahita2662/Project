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

import javax.swing.ImageIcon;

/**
 * Purpose: Represents an item in the game. An item has a name, hint text, size, image, position, and can be opened or clicked.
 */
public class Item
{
	private String name;
	private String hintText;
	private boolean isClicked;
	private ImageIcon image;
	private int xPosition;
	private int yPosition;
	private int size;
	
	/**
	 * Initializes a new Item object with the name, hint text, image, and xy positions.
	 * 
	 * @param name the name of the item
	 * @param hintText the hint text associated with the item
	 * @param image the image representing the item
	 * @param xPosition the x position of the item in the room
	 * @param yPosition the y position of the item in the room
	 */
	public Item(String name, String hintText, ImageIcon image, int xPosition, int yPosition, int size)
	{
		this.name = name;
		this.hintText = hintText;
		this.image = image;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		isClicked = false;
		this.size = size;
	}
	
	/**
	 * Returns the name of the item.
	 * 
	 * @return the name of the item
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Returns the hint text for the item.
	 * 
	 * @return the hint text for the item
	 */
	public String getHintText()
	{
		return hintText;
	}
	
	/**
	 * Returns the image representing the item.
	 * 
	 * @return the ImageIcon for the item
	 */
	public ImageIcon getImage()
	{
		return image;
	}
	
	/**
	 * Returns the size of the item.
	 * 
	 * @return the size of the item
	 */
	public int getSize()
	{
	    return size;
	}
	
	/**
	 * Returns the x position of the item in the room.
	 * 
	 * @return the x position of the item
	 */
	public int getXPosition()
	{
		return xPosition;
	}
	
	/**
	 * Returns the y position of the item in the room.
	 * 
	 * @return the y position of the item
	 */
	public int getYPosition()
	{
		return yPosition;
	}
	
	/**
	 * Returns whether the item has been clicked or not.
	 * 
	 * @return true if the item has been clicked, false otherwise
	 */
	public boolean isClicked()
	{
		return isClicked;
	}
	
	/**
	 * Marks item as clicked by setting the isClicked variable to true.
	 */
	public void clickItem()
	{
		isClicked = true;
	}
	
	public String toString()
	{
		return "name = " + name + ", hintText = " + hintText + ", isClicked = " + isClicked + ", x = " + xPosition + ", y = " + yPosition;
	}
}