/**
* Lead Author(s):
* @author Luke Piedrahita
* 
* References:
* Morelli, R., & Walde, R. (2016).
* Java, Java, Java: Object-Oriented Problem Solving
* https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
*
* Oracle. (2014).
* JButton (Java Platform SE 8).
* Java SE 8 API Documentation.
* https://docs.oracle.com/javase/8/docs/api/javax/swing/JButton.html
* 
* Version: 2026-05-15
*/

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.InventoryController;
import model.Item;

/**
 * Purpose: InventoryView displays the items collected by the player in the inventory. It shows a button for each item in the inventory.
 * 
 * InventoryView is-a JPanel
 */
public class InventoryView extends JPanel
{
    private InventoryController inventoryController;
	private JLabel inventoryLabel;
	private ArrayList<JButton> inventoryButtons;

	/**
	 * Initializes a new InventoryView with a background, a title label, and an empty list of item buttons.
	 */
    public InventoryView()
    {
        setBackground(new Color(45, 45, 55));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        inventoryButtons = new ArrayList<JButton>();
        inventoryLabel = new JLabel("Inventory");
        inventoryLabel.setForeground(Color.WHITE);
        inventoryLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        inventoryLabel.setAlignmentX(CENTER_ALIGNMENT);
        inventoryLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        add(inventoryLabel);
    }
    
    /**
     * Updates the inventory with the current list of items. Removes existing buttons and adds one for each item in the inventory.
     * 
     * @param items the list of items currently in the inventory
     */
    public void updateInventory(ArrayList<Item> items)
    {
        // removes all existing buttons from the inventory view
        for (int i = 0; i < inventoryButtons.size(); i++)
        {
            remove(inventoryButtons.get(i));
        }
        inventoryButtons.clear();

        // adds new buttons for each item in the inventory
        for (int i = 0; i < items.size(); i++)
        {
            Item item = items.get(i);
            JButton itemButton = new JButton();

            // set image if available otherwise show name
            if (item.getImage() != null)
            {
                Image scaledImage = item.getImage().getImage()
                    .getScaledInstance(120, 120, Image.SCALE_SMOOTH);
                itemButton.setIcon(new ImageIcon(scaledImage));
            }
            else
            {
                itemButton.setText(item.getName());
                itemButton.setForeground(Color.WHITE);
            }

            itemButton.setPreferredSize(new Dimension(160, 160));
            itemButton.setMaximumSize(new Dimension(160, 160));
            itemButton.setBackground(new Color(60, 60, 75));
            itemButton.setOpaque(true);
            itemButton.setAlignmentX(CENTER_ALIGNMENT);
            itemButton.setActionCommand(item.getName());

            // add controller as listener if controller is set
            if (inventoryController != null)
            {
                itemButton.addActionListener(inventoryController);
            }

            inventoryButtons.add(itemButton);
            add(itemButton);
        }

        revalidate();
        repaint();
    }
    
    /**
     * Highlights the button of the selected item.
     * 
     * @param itemName the name of the item to be highlighted
     */
    public void highlightSelectedItem(String itemName)
    {
        for (int i = 0; i < inventoryButtons.size(); i++)
        {
            if (inventoryButtons.get(i).getActionCommand().equals(itemName))
            {
                inventoryButtons.get(i).setBackground(
                    new Color(100, 100, 150));
            }
            else
            {
                inventoryButtons.get(i).setBackground(
                    new Color(60, 60, 75));
            }
        }
    }

    /**
     * Sets the InventoryController for this view and adds it as a listener to all existing inventory buttons.
     * 
     * @param inventoryController the InventoryController to be set for this view
     */
    public void setInventoryController(InventoryController inventoryController)
    {
        this.inventoryController = inventoryController;
        
        // add controller as listener to all existing inventory buttons
        for (int i = 0; i < inventoryButtons.size(); i++)
        {
            inventoryButtons.get(i).addActionListener(inventoryController);
        }
        
    }

    /**
     * Returns the list of inventory buttons.
     * 
     * @return the ArrayList of inventory buttons
     */
    public ArrayList<JButton> getInventoryButtons()
    {
        return inventoryButtons;
    }

    /**
     * Returns the inventory title label.
     * 
     * @return the inventory label
     */
    public JLabel getInventoryLabel()
    {
        return inventoryLabel;
    }
}