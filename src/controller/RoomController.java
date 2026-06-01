/**
* Lead Author(s):
* @author Luke Piedrahita
* 
* References:
* Morelli, R., & Walde, R. (2016).
* Java, Java, Java: Object-Oriented Problem Solving
* https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
*
* Version: 2026-05-18
*/

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Item;
import model.LockedItem;
import model.Room;
import view.RoomView;

/**
 * Purpose: RoomController handles all user interactions with the room including item clicks, locked item code entry, door clicks, and game reset.
 *
 * RoomController is-a ActionListener
 */
public class RoomController implements ActionListener
{
	private Room room;
	private RoomView roomView;
	private InventoryController inventoryController;
	private EscapeGameController escapeGameController;
	private int roomItemsCollected = 0;
	
	/**
	 * Initializes the RoomController with the room and roomView and sets up the action listener for the door button.
	 * 
	 * @param room the current room
	 * @param roomView the room view associated with the current room
	 */
	public RoomController(Room room, RoomView roomView)
	{
		this.room = room;
		this.roomView = roomView;
		this.roomView.getDoorButton().addActionListener(this);
		this.roomView.setRoomController(this);
	}
	
	/**
	 * Handles the action events for item and door buttons.
	 * 
	 * @param e the ActionEvent from a button click
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
	    String command = e.getActionCommand();
	    
	    if (command.equals("DOOR"))
	    {
	        if (escapeGameController != null)
	        {
	            escapeGameController.handleDoorClick();
	        }
	    }
	    else if (command.equals("RESET"))
	    {
	        if (escapeGameController != null)
	        {
	            escapeGameController.handleReset();
	        }
	    }
	    else
	    {
	        handleItemClick(command);
	    }
	}
	
	/**
	 * Finds the clicked item by name, and shows its hints or prompts.
	 * 
	 * @param itemName the name of the item that was clicked
	 */
	public void handleItemClick(String itemName)
	{
		// searches the room for the item that matches the clicked button name
	    for (int i = 0; i < room.getItems().size(); i++)
	    {
	        Item item = room.getItems().get(i);
	        if (item.getName().equals(itemName))
	        {
	        	// marks the item as clicked and handles it based on whether it is locked or not
	            item.clickItem();

	            if (item instanceof LockedItem)
	            {
	                handleLockedItemClick((LockedItem) item);
	            }
	            else
	            {
	                JOptionPane.showMessageDialog(roomView, item.getHintText(), item.getName(), JOptionPane.INFORMATION_MESSAGE);
	                moveItemIntoInventory(item);
	            }
	            break;
	        }
	    }
	}

	/**
	 * Prompts the user to enter a code for the locked item and shows the hint.
	 * 
	 * @param item the locked item that was clicked
	 */
	public void handleLockedItemClick(LockedItem item)
	{
	    String code = JOptionPane.showInputDialog(roomView, "Enter the code to unlock " + item.getName() + ":", item.getName(), JOptionPane.QUESTION_MESSAGE);

	    // null means the player clicked cancel so exit without showing an error
	    if (code == null)
	    {
	        return;
	    }
	    
	    // if code is correct show hint and move to inventory, otherwise show error
	    if (item.checkSolved(code))
	    {
	        JOptionPane.showMessageDialog(roomView, item.getHintText(), item.getName(), JOptionPane.INFORMATION_MESSAGE);
	        moveItemIntoInventory(item);
	    }
	    else
	    {
	        JOptionPane.showMessageDialog(roomView, "Incorrect code. Try again.", item.getName(), JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	/**
	 * Removes the item from the room and room view and adds it to the inventory. Also updates the clue counter in the view.
	 *
	 * @param item the item to move into the inventory
	 */
	public void moveItemIntoInventory(Item item)
	{
	    room.removeItem(item);
	    roomView.removeItemFromView(item.getName());
	    
	    if (inventoryController != null)
	    {
	        inventoryController.addItemToInventory(item);
	        roomItemsCollected++;
	        roomView.setClueCount(roomItemsCollected);
	    }
	}

	/**
	 * Sets the current room when the user goes to a new room.
	 * 
	 * @param room the new room to set as the current room
	 */
	public void setRoom(Room room)
	{
	    this.room = room;
	    roomItemsCollected = 0;
	}
	
	/**
	 * Sets the inventory controller to move items into the inventory when they are clicked.
	 * 
	 * @param inventoryController the inventory controller to set
	 */
	public void setInventoryController(InventoryController inventoryController)
	{
	    this.inventoryController = inventoryController;
	}
	
	/**
	 * Sets the escape game controller to handle door clicks and moving to the next room.
	 * 
	 * @param escapeGameController the escape game controller to set
	 */
	public void setEscapeGameController(EscapeGameController escapeGameController)
	{
		this.escapeGameController = escapeGameController;
	}
}