/**
* Lead Author(s):
* @author Luke Piedrahita
* 
* References:
* Morelli, R., & Walde, R. (2016).
* Java, Java, Java: Object-Oriented Problem Solving
* https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
*
* Version: 2026-05-31
*/

package model;

import java.util.ArrayList;

/**
 * Purpose: InventoryModel represents the inventory of items. Holds the list of items collected and which item is currently selected.
 */
public class InventoryModel
{
	private ArrayList<Item> items;
	private Item selectedItem;
	
	/**
	 * Initializes a new InventoryModel with an empty list of items.
	 */
	public InventoryModel()
	{
		this.items = new ArrayList<Item>();
		this.selectedItem = null;
	}
	
	/**
	 * Returns the list of items in the inventory.
	 * 
	 * @return the ArrayList of items in the inventory
	 */
	public ArrayList<Item> getItems()
	{
		return items;
	}
	
	/**
	 * Adds an item to the inventory.
	 * 
	 * @param item the item to be added to the inventory
	 */
	public void addItem(Item item)
	{
		items.add(item);
	}
	
	/**
	 * Returns the selected item in the inventory.
	 * 
	 * @return the selected item in the inventory
	 */
	public Item getSelectedItem()
	{
		return selectedItem;
	}
	
	/**
	 * Sets the selected item in the inventory.
	 * 
	 * @param selectedItem the item to be set as the selected item in the inventory
	 */
	public void setSelectedItem(Item selectedItem)
	{
		this.selectedItem = selectedItem;
	}
	
	/**
	 * Returns a string representation of the inventory model.
	 *
	 * @return a string with the list of items and the selected item
	 */
	@Override
	public String toString()
	{
		return "items = " + items + ", selectedItem = " + selectedItem;
	}
}