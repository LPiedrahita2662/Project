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

import javax.swing.ImageIcon;

/**
 * Purpose: The reponsibility of Item is ...
 *
 * Item is-a ...
 * Item is ...
 */
public class Item
{
	private String name;
	private String hintText;
	private boolean isOpened;
	private boolean isClicked;
	private ImageIcon image;
	private int xPosition;
	private int yPosition;
	
	public Item(String name, String hintText, ImageIcon image, int xPosition, int yPosition)
	{
		this.name = name;
		this.hintText = hintText;
		this.image = image;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		isOpened = false;
		isClicked = false;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getHintText()
	{
		return hintText;
	}
	
	public ImageIcon getImage()
	{
		return image;
	}
	
	public int getXPosition()
	{
		return xPosition;
	}
	
	public int getYPosition()
	{
		return yPosition;
	}
	
	public boolean isOpened()
	{
		return isOpened;
	}
	
	public boolean isClicked()
	{
		return isClicked;
	}
	
	public void openItem()
	{
		isOpened = true;
	}
	
	public void clickItem()
	{
		isClicked = true;
	}
	
	public String toString()
	{
		return "name = " + name + ", hintText = " + hintText + ", isClicked = " + isClicked + ", isOpened = " + isOpened + ", x = " + xPosition + ", y = " + yPosition;
	}
}