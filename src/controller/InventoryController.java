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

import model.InventoryModel;
import model.Item;
import view.InventoryView;

/**
 * Purpose: InventoryController manages all user interactions with the game's inventory, selecting items and displaying hint text.
 *
 * InventoryController is-a ActionListener
 */
public class InventoryController implements ActionListener
{
	private InventoryModel inventoryModel;
	private InventoryView inventoryView;
	
	/**
	 * Initializes a new InventoryController with the inventory model and view, and sets itself as the controller for the view.
	 * 
	 * @param inventoryModel the inventory model to manage the inventory data
	 * @param inventoryView the inventory view
	 */
	public InventoryController(InventoryModel inventoryModel, InventoryView inventoryView)
	{
		this.inventoryModel = inventoryModel;
		this.inventoryView = inventoryView;
		this.inventoryView.setInventoryController(this);
	}
	
	/**
	 * Handles action events from inventory item buttons.
	 * 
	 * @param e the action event from clicking an inventory item button
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		handleItemClick(command);
	}
	
	/**
	 * Handles the behavior when an inventory item is clicked, it updates the selected item in the model and shows the hint text in a dialog.
	 * 
	 * @param itemName the name of the item that was clicked
	 */
	public void handleItemClick(String itemName)
	{
		for (int i = 0; i < inventoryModel.getItems().size(); i++)
		{
			Item item = inventoryModel.getItems().get(i);
			if (item.getName().equals(itemName))
			{
				inventoryModel.setSelectedItem(item);
				
				inventoryView.highlightSelectedItem(itemName);
				
				JOptionPane.showMessageDialog(inventoryView, item.getHintText(), item.getName(), JOptionPane.INFORMATION_MESSAGE);
				break;
			}
		}
	}
	
	/**
	 * Adds an item to the inventory and updates the view to show the new item.
	 *  
	 * @param item the item to add to the inventory
	 */
	public void addItemToInventory(Item item)
	{
		inventoryModel.addItem(item);
		inventoryView.updateInventory(inventoryModel.getItems());
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
}