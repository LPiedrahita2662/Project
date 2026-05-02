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

import java.util.ArrayList;

/**
 * Purpose: The reponsibility of Room is ...
 *
 * Room is-a ...
 * Room is ...
 */


public class Room
{
	private ArrayList<Item> items;
	private String roomName;
	private Door door;
	
	public Room(String roomName, Door door)
	{
		this.roomName = roomName;
		this.door = door;
	}
	
	public void addItem(Item item)
	{
		items.add(item);
	}
	
	public void removeItem(Item item)
	{
		items.remove(item);
	}

	/**
	 * @return the items
	 */
	public ArrayList<Item> getItems()
	{
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(ArrayList<Item> items)
	{
		this.items = items;
	}

	/**
	 * @return the roomName
	 */
	public String getRoomName()
	{
		return roomName;
	}

	/**
	 * @return the door
	 */
	public Door getDoor()
	{
		return door;
	}
}
