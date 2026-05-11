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

import java.util.ArrayList;

/**
 * Purpose: The reponsibility of EscapeGameModel is ...
 *
 * EscapeGameModel is-a ...
 * EscapeGameModel is ...
 */
public class EscapeGameModel
{
	private ArrayList<Item> inventory;
	private boolean isWon;
	private Room currentRoom;
	
	public EscapeGameModel(Room currentRoom)
	{
		this.inventory = new ArrayList<Item>();
		this.isWon = false;
		this.currentRoom = currentRoom;
	}
	
	public ArrayList<Item> getInventory()
	{
		return inventory;
	}
	
	public boolean isWon()
	{
		return isWon;
	}
	
	public void setWon(boolean isWon)
	{
		this.isWon = isWon;
	}
	
	public Room getCurrentRoom()
	{
		return currentRoom;
	}
	
	public void setCurrentRoom(Room currentRoom)
	{
		this.currentRoom = currentRoom;
	}
	
	public String toString()
	{
		return "inventory = " + inventory + ", isWon = " + isWon + ", currentRoom = " + currentRoom;
	}
}