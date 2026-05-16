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

import java.util.ArrayList;

/**
 * Purpose: Represents a room in the game. Each room has a name, a door, and a list of items that can be interacted with.
 */
public class Room
{
	private ArrayList<Item> items;
	private String roomName;
	private Door door;
	
	/**
	 * Initializes a new Room object with the room name and door. The items list is initialized as an empty ArrayList.
	 * 
	 * @param roomName the name of the room
	 * @param door the door associated with the room
	 */
	public Room(String roomName, Door door)
	{
		this.roomName = roomName;
		this.door = door;
		this.items = new ArrayList<Item>();
	}
	
	/**
	 * Adds an item to the room.
	 * 
	 * @param item the item to be added to the room
	 */
	public void addItem(Item item)
	{
		items.add(item);
	}
	
	/**
	 * Removes an item from the room.
	 * 
	 * @param item the item to be removed from the room
	 */
	public void removeItem(Item item)
	{
		items.remove(item);
	}

	/**
	 * Returns the list of items in the room.
	 * 
	 * @return the list of items in the room
	 */
	public ArrayList<Item> getItems()
	{
		return items;
	}

	/**
	 * Returns the name of the room.
	 * 
	 * @return the roomName
	 */
	public String getRoomName()
	{
		return roomName;
	}

	/**
	 * Returns the door of the room.
	 * 
	 * @return the door associated with the room
	 */
	public Door getDoor()
	{
		return door;
	}
	
	/**
	 * Sets the door of the room.
	 * 
	 * @param door the door to set to this room
	 */
	public void setDoor(Door door)
	{
		this.door = door;
	}
	
	public String toString()
	{
	    return "roomName = " + roomName + ", door = " + door + ", items = " + items;
	}
}