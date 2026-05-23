/**
* Lead Author(s):
* @author Luke Piedrahita
* 
* References:
* Morelli, R., & Walde, R. (2016).
* Java, Java, Java: Object-Oriented Problem Solving
* https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
*
* Oracle. (2012).
* Performing Custom Painting: A Closer Look at the Paint Mechanism. The Java Tutorials.
* https://docs.oracle.com/javase/tutorial/uiswing/painting/closer.html
* 
* Oracle. (2014).
* JButton (Java Platform SE 8).
* Java SE 8 API Documentation.
* https://docs.oracle.com/javase/8/docs/api/javax/swing/JButton.html
* 
* Version: 2026-05-11
*/
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.RoomController;
import model.Room;
import model.Item;

/**
 * Purpose: RoomView displays the current room background and all of the items in the room at their x and y positions.
 *
 * RoomView is-a JPanel
 */
public class RoomView extends JPanel
{
	private RoomController roomController;
	private ArrayList<JButton> itemButtons;
	private JButton doorButton;
	private JLabel messageLabel;
	private JLabel roomNameLabel;

	
	/**
	 * Initializes a new RoomView with a door button that leads the exit and a list of item buttons.
	 */
	public RoomView()
	{
		setLayout(null);
		itemButtons = new ArrayList<JButton>();
		doorButton = new JButton("EXIT");
		doorButton.setBounds(400, 160, 150, 300);
		doorButton.setBackground(Color.BLACK);
		doorButton.setForeground(Color.WHITE);
		doorButton.setOpaque(true);
		doorButton.setBorderPainted(false);
		doorButton.setActionCommand("DOOR");
		add(doorButton);
		
		messageLabel = new JLabel("Click on an item to examine it.");
		messageLabel.setForeground(Color.WHITE);
		messageLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
		messageLabel.setHorizontalAlignment(JLabel.CENTER);
		messageLabel.setBounds(0, 520, 900, 30);
		add(messageLabel);
		
		roomNameLabel = new JLabel("Room #1");
		roomNameLabel.setForeground(Color.BLACK);
		roomNameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
		roomNameLabel.setBounds(15, 0, 300, 40);
		add(roomNameLabel);
	}
	
	/**
	 * Uses the built in paintComponent method to draw the background of the room. 
	 * The top 75% of the panel is gray to represent the walls and the bottom 25% is brown to represent the floor.
	 * 
	 * @param g the Graphics object used to draw the background
	 */
	@Override
	protected void paintComponent(Graphics backgroundGraphics)
	{
	    super.paintComponent(backgroundGraphics);
	    
	    backgroundGraphics.setColor(new Color(169, 169, 169));
	    backgroundGraphics.fillRect(0, 0, getWidth(), (int)(getHeight() * 0.75));

	    backgroundGraphics.setColor(new Color(105, 55, 0));
	    backgroundGraphics.fillRect(0, (int)(getHeight() * 0.75), getWidth(), (int)(getHeight() * 0.25));
	}
	
	/**
	 * Renders of all the items in the room as buttons at their postions from the CSV.
	 * 
	 * @param room the room whose items are being rendered
	 */
	public void renderItems(Room room)
	{
		// removes all item buttons from the view when changing rooms
		for (JButton button : itemButtons)
		{
			remove(button);
		}
		itemButtons.clear();
		
		for (int i = 0; i < room.getItems().size(); i++)
		{
			 Item item = room.getItems().get(i);
			    
			    JButton itemButton = new JButton();
			    
			    // set image if available otherwise show name as text
			    if (item.getImage() != null)
			    {
			    	Image scaledImage = item.getImage().getImage() .getScaledInstance(item.getSize(), item.getSize(),Image.SCALE_SMOOTH);
			    	itemButton.setBounds(item.getXPosition(), item.getYPosition(), item.getSize(), item.getSize());
			    	itemButton.setIcon(new ImageIcon(scaledImage));
			    }
			    else
			    {
			        itemButton.setText(item.getName());
			    }
			    
			    // position button at item x and y from file
			    itemButton.setBounds(item.getXPosition(),item.getYPosition(), 80, 80);
			    itemButton.setBorderPainted(false);
			    itemButton.setContentAreaFilled(false);
			    itemButton.setFocusPainted(false);
			    
			    // action command used by controller to identify which item was clicked
			    itemButton.setActionCommand(item.getName());
			    
			    itemButtons.add(itemButton);
			    add(itemButton);
		}
		
		revalidate();
		repaint();
	}
	
	/**
	 * Removes an item from the view when it is picked up by the player.
	 * 
	 * @param itemName the name of the item to be removed
	 */
	public void removeItemFromView(String itemName)
	{
	    JButton toRemove = null;
	    for (int i = 0; i < itemButtons.size(); i++)
	    {
	        if (itemButtons.get(i).getActionCommand().equals(itemName))
	        {
	            toRemove = itemButtons.get(i);
	            break;
	        }
	    }
	    if (toRemove != null)
	    {
	        itemButtons.remove(toRemove);
	        remove(toRemove);
	        revalidate();
	        repaint();
	    }
	}
	
	/**
	 * Returns the door button for this room.
	 * 
	 * @return the door JButton for this room
	 */
	public JButton getDoorButton()
	{
	    return doorButton;
	}

	/**
	 * Returns the list of item buttons in the room.
	 * 
	 * @return the ArrayList of JButtons representing the items in the room
	 */
	public ArrayList<JButton> getItemButtons()
	{
	    return itemButtons;
	}
	
	/**
	 * Sets the message in the message bar at the bottom of the view.
	 * 
	 * @param message the message to be displayed in the message bar
	 */
	public void setMessage(String message)
	{
	    messageLabel.setText(message);
	}
	
	/**
	 * Sets the RoomController for this view.
	 * 
	 * @param roomController the RoomController to be set for this view
	 */
	public void setRoomController(RoomController roomController)
	{
	    this.roomController = roomController;
	}
	
	/**
	 * Sets the name of the room in the top left corner of the view.
	 * 
	 * @param name the name of the room to be displayed
	 */
	public void setRoomName(String name)
	{
	    roomNameLabel.setText(name);
	}
}