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

package service;

import java.util.ArrayList;

import model.Room;

/**
 * Purpose: RoomRepository loads rooms from a file using the FileService and manages the all the rooms in the game.
 */
public class RoomRepository
{
	private ArrayList<Room> rooms;
	private FileService fileService;
	private int roomIndex;
	
	/**
	 * Initializes a new RoomRepository with the file name to load rooms from.
	 * 
	 * @param fileName the name of the file to load rooms from
	 */
	public RoomRepository(String fileName)
	{
		this.fileService = new FileService(fileName);
		this.rooms = fileService.loadRooms();
		this.roomIndex = 0;
	}
	
	/**
	 * Returns the next room in the list of rooms.
	 * 
	 * @return the next room
	 */
	public Room getNextRoom()
	{
		if (roomIndex < rooms.size() - 1)
		{
			roomIndex++;
			return rooms.get(roomIndex);
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Returns the first room in the list of rooms.
	 * 
	 * @return the first room in the list
	 */
	public Room getFirstRoom()
	{
		return rooms.get(0);
	}
	
	/**
	 * Returns the current room based on the index.
	 * 
	 * @return the current room
	 */
	public Room getCurrentRoom()
	{
		return rooms.get(roomIndex);
	}
	
	/**
	 * Returns whether there is another room next.
	 * 
	 * @return true if there is another room next, false otherwise
	 */
	public boolean hasNextRoom()
	{
		return roomIndex < rooms.size() - 1;
	}
	
	/**
	 * Returns all of the rooms from the respository.
	 * 
	 * @return the ArrayList of rooms in the repository
	 */
	public ArrayList<Room> getRooms()
	{
		return rooms;
	}
	
	public String toString()
    {
        return "RoomRepository - rooms = " + rooms.size() + ", roomIndex = " + roomIndex;
    }
	
}
