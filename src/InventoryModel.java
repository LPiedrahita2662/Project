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
 * Purpose: The reponsibility of InventoryModel is ...
 *
 * InventoryModel is-a ...
 * InventoryModel is ...
 */
public class InventoryModel
{
	private ArrayList<Item> items;
	private Item selectedItem;
	
	public InventoryModel()
	{
		this.items = new ArrayList<Item>();
	}
	
	public ArrayList<Item> getItems()
	{
		return items;
	}
	
	public void addItem(Item item)
	{
		items.add(item);
	}
	
	public void removeItem(Item item)
	{
		items.remove(item);
	}
	
	public Item getSelectedItem()
	{
		return selectedItem;
	}
	
	public void setSelectedItem(Item selectedItem)
	{
		this.selectedItem = selectedItem;
	}
	
	public void clearSelectedItem()
	{
		this.selectedItem = null;
	}
	
	public String toString()
	{
		return "items = " + items + ", selectedItem = " + selectedItem;
	}
}