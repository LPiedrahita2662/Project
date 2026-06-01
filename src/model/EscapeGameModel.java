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

/**
 * Purpose: EscapeGameModel holds the player inventory, the current room, and whether the game is won or not.
 */
public class EscapeGameModel
{
	private InventoryModel inventoryModel;
	private boolean isWon;
	private Room currentRoom;
	
	/**
	 * Initializes a new EscapeGameModel with the current room. The inventory is initialized as an empty ArrayList and isWon is set to false.
	 * 
	 * @param currentRoom the starting room of the game
	 */
	public EscapeGameModel(Room currentRoom)
	{
		this.inventoryModel = new InventoryModel();
		this.isWon = false;
		this.currentRoom = currentRoom;
	}
	
	/**
	 * Returns whether or not the game has been won.
	 * 
	 * @return true if the game is won, false otherwise
	 */
	public boolean isWon()
	{
		return isWon;
	}
	
	/**
	 * Sets whether the game has been won.
	 * 
	 * @param isWon true if the game is won, false otherwise
	 */
	public void setWon(boolean isWon)
	{
		this.isWon = isWon;
	}
	
	/**
	 * Returns the current room.
	 * 
	 * @return currentRoom the current room the player is in
	 */
	public Room getCurrentRoom()
	{
		return currentRoom;
	}
	
	/**
	 * Sets the current room.
	 * 
	 * @param currentRoom the room to set as the current room
	 */
	public void setCurrentRoom(Room currentRoom)
	{
		this.currentRoom = currentRoom;
	}
	
	/**
	 * Returns the inventory model.
	 * 
	 * @return the inventory model
	 */
	public InventoryModel getInventoryModel()
	{
	    return inventoryModel;
	}

	/**
	 * Returns a string representation of the escape game model.
	 *
	 * @return a string with the inventory model, isWon status, and current room
	 */
	@Override
	public String toString()
	{
		return "inventoryModel = " + inventoryModel + ", isWon = " + isWon + ", currentRoom = " + currentRoom;
	}
}