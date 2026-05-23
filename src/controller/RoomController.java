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
 * Purpose: RoomController handles all user interactions with the room, item clicks, locked item codes, and door clicks.
 *
 * RoomController is-a ActionListener
 */
public class RoomController implements ActionListener
{
	private Room room;
	private RoomView roomView;
	private InventoryController inventoryController;
	private EscapeGameController escapeGameController;
	
	/**
	 * Initalizes the RoomController with the room and roomView and sets up the action listener for the door button.
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
	    for (int i = 0; i < room.getItems().size(); i++)
	    {
	        Item item = room.getItems().get(i);
	        if (item.getName().equals(itemName))
	        {
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

	    if (code != null && item.checkSolved(code))
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
	 * Removes item from the room and adds it to the inventory.
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