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
* Version: 2026-05-31
*/
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
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
 * Purpose: RoomView displays the current room background, furniture, items, door, reset button, and message bar.
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
	private String currentRoomName;
	private JButton resetButton;
	private JLabel clueLabel;

	
	/**
	 * Initializes a new RoomView with a door button, reset button, clue label, message label, room name label, and an empty list of item buttons.
	 */
	public RoomView()
	{
	    setLayout(null);
	    itemButtons = new ArrayList<JButton>();

	    // Reset button in the top right corner of the view
	    resetButton = new JButton("Reset Game")
	    {
	        @Override
	        protected void paintComponent(Graphics g)
	        {
	            // draw blue rounded background with black outline and centered text
	            g.setColor(new Color(70, 130, 180));
	            g.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
	            g.setColor(Color.BLACK);
	            g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 8, 8);
	            g.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 8, 8);
	            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
	            FontMetrics fm = g.getFontMetrics();
	            int textX = (getWidth() - fm.stringWidth("Reset Game")) / 2;
	            int textY = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
	            g.drawString("Reset Game", textX, textY);
	        }
	    };
	    resetButton.setBounds(790, 8, 130, 24);
	    resetButton.setFocusPainted(false);
	    resetButton.setBorderPainted(false);
	    resetButton.setContentAreaFilled(false);
	    resetButton.setOpaque(false);
	    resetButton.setActionCommand("RESET");
	    add(resetButton);

	    doorButton = new JButton("EXIT")
	    {
	        @Override
	        protected void paintComponent(Graphics g)
	        {
	            // draw black door with white border, gray doorknob, and room label
	            g.setColor(Color.BLACK);
	            g.fillRect(0, 0, getWidth(), getHeight());
	            g.setColor(Color.WHITE);
	            g.drawRect(2, 2, getWidth() - 4, getHeight() - 4);
	            g.setColor(Color.GRAY);
	            g.fillOval(12, getHeight() / 2, 18, 18);
	            g.setColor(Color.WHITE);
	            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
	            if ("Study".equals(currentRoomName))
	            {
	                g.drawString("FINAL EXIT", getWidth() / 2 - 42, 25);
	            }
	            else
	            {
	                g.drawString("NEXT ROOM", getWidth() / 2 - 48, 25);
	            }
	        }
	    };
	    doorButton.setBounds(400, 170, 150, 300);
	    doorButton.setBorderPainted(false);
	    doorButton.setContentAreaFilled(false);
	    doorButton.setFocusPainted(false);
	    doorButton.setActionCommand("DOOR");
	    add(doorButton);

	    // Message label at the bottom of the view
	    messageLabel = new JLabel("Click on an item to examine it.");
	    messageLabel.setForeground(Color.WHITE);
	    messageLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
	    messageLabel.setHorizontalAlignment(JLabel.CENTER);
	    messageLabel.setBounds(30, 540, 900, 30);
	    add(messageLabel);

	    JLabel hintLabel = new JLabel("Make sure to examine all 3 items before moving to the next room.");
	    hintLabel.setForeground(Color.WHITE);
	    hintLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
	    hintLabel.setHorizontalAlignment(JLabel.CENTER);
	    hintLabel.setBounds(30, 575, 900, 25);
	    add(hintLabel);

	    // Clue counter label
	    clueLabel = new JLabel("Clues found: 0/3");
	    clueLabel.setForeground(Color.WHITE);
	    clueLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
	    clueLabel.setHorizontalAlignment(JLabel.CENTER);
	    clueLabel.setBounds(790, 36, 130, 20);
	    add(clueLabel);

	    // Room name label
	    roomNameLabel = new JLabel("Room #1");
	    roomNameLabel.setForeground(Color.WHITE);
	    roomNameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
	    roomNameLabel.setBounds(15, 0, 300, 40);
	    add(roomNameLabel);
	}
	
	/**
	 * Uses the built in paintComponent method to draw the room background, furniture, and other elements.
	 * The top 75% is gray for the wall and bottom 25% is brown for the floor.
	 * The Library room draws a bookshelf and table. The Study room draws a desk.
	 *
	 * @param backgroundGraphics the Graphics object used to draw the background
	 */
	@Override
	protected void paintComponent(Graphics backgroundGraphics)
	{
	    super.paintComponent(backgroundGraphics);
	    
	    backgroundGraphics.setColor(new Color(169, 169, 169));
	    backgroundGraphics.fillRect(0, 0, getWidth(), (int)(getHeight() * 0.75));

	    backgroundGraphics.setColor(new Color(105, 55, 0));
	    backgroundGraphics.fillRect(0, (int)(getHeight() * 0.75), getWidth(), (int)(getHeight() * 0.25));
	    
	    if ("Library".equals(currentRoomName))
	    {
	        // Code for Bookshelf
	        backgroundGraphics.setColor(new Color(101, 67, 33));
	        backgroundGraphics.fillRect(220, 291, 130, 180);
	        backgroundGraphics.setColor(new Color(70, 42, 14));
	        backgroundGraphics.fillRect(220, 291, 130, 10);
	        backgroundGraphics.fillRect(220, 351, 130, 8);
	        backgroundGraphics.fillRect(220, 411, 130, 8);
	        backgroundGraphics.fillRect(220, 461, 130, 10);
	        backgroundGraphics.fillRect(220, 291, 8, 180);
	        backgroundGraphics.fillRect(342, 291, 8, 180);
	        backgroundGraphics.setColor(new Color(180, 40, 40));
	        backgroundGraphics.fillRect(230, 306, 18, 44);
	        backgroundGraphics.setColor(new Color(40, 80, 160));
	        backgroundGraphics.fillRect(250, 309, 15, 41);
	        backgroundGraphics.setColor(new Color(34, 120, 60));
	        backgroundGraphics.fillRect(267, 303, 20, 47);
	        backgroundGraphics.setColor(new Color(180, 140, 30));
	        backgroundGraphics.fillRect(289, 311, 14, 39);
	        backgroundGraphics.setColor(new Color(120, 40, 120));
	        backgroundGraphics.fillRect(305, 305, 17, 45);
	        backgroundGraphics.setColor(new Color(160, 60, 30));
	        backgroundGraphics.fillRect(230, 363, 22, 47);
	        backgroundGraphics.setColor(new Color(40, 130, 130));
	        backgroundGraphics.fillRect(254, 366, 16, 44);
	        backgroundGraphics.setColor(new Color(180, 40, 40));
	        backgroundGraphics.fillRect(272, 361, 19, 49);
	        backgroundGraphics.setColor(new Color(50, 50, 140));
	        backgroundGraphics.fillRect(293, 365, 14, 45);
	        backgroundGraphics.setColor(new Color(34, 120, 60));
	        backgroundGraphics.fillRect(309, 362, 20, 48);

	        // Code for Table
	        backgroundGraphics.setColor(new Color(101, 67, 33));
	        backgroundGraphics.fillRect(650, 385, 200, 14);
	        backgroundGraphics.setColor(new Color(70, 42, 14));
	        backgroundGraphics.fillRect(660, 399, 180, 9);
	        backgroundGraphics.setColor(new Color(101, 67, 33));
	        backgroundGraphics.fillRect(660, 408, 13, 80);
	        backgroundGraphics.fillRect(827, 408, 13, 80);
	        backgroundGraphics.setColor(new Color(70, 42, 14));
	        backgroundGraphics.fillRect(663, 408, 4, 80);
	        backgroundGraphics.fillRect(830, 408, 4, 80);
	        backgroundGraphics.setColor(new Color(101, 67, 33));
	        backgroundGraphics.fillRect(660, 465, 180, 9);
	    }

	    if ("Study".equals(currentRoomName))
	    {
	        // Code for Desk
	        backgroundGraphics.setColor(new Color(101, 67, 33));
	        backgroundGraphics.fillRect(605, 380, 280, 14);
	        backgroundGraphics.setColor(new Color(70, 42, 14));
	        backgroundGraphics.fillRect(615, 394, 260, 9);
	        backgroundGraphics.setColor(new Color(101, 67, 33));
	        backgroundGraphics.fillRect(615, 403, 13, 80);
	        backgroundGraphics.fillRect(862, 403, 13, 80);
	        backgroundGraphics.setColor(new Color(70, 42, 14));
	        backgroundGraphics.fillRect(618, 403, 4, 80);
	        backgroundGraphics.fillRect(865, 403, 4, 80);
	        backgroundGraphics.setColor(new Color(101, 67, 33));
	        backgroundGraphics.fillRect(615, 460, 260, 9);
	        backgroundGraphics.setColor(new Color(80, 50, 20));
	        backgroundGraphics.fillRect(705, 394, 80, 9);
	        backgroundGraphics.setColor(new Color(180, 140, 60));
	        backgroundGraphics.fillRect(738, 397, 14, 4);
	    }
	}
	
	/**
	 * Renders all items in the room as buttons at their positions loaded from the CSV file.
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
	        	Image scaledImage = item.getImage().getImage().getScaledInstance(-1, item.getSize(), Image.SCALE_SMOOTH);
	        	ImageIcon icon = new ImageIcon(scaledImage);
	        	itemButton.setIcon(icon);
	        	itemButton.setBounds(item.getXPosition(), item.getYPosition(), icon.getIconWidth(), item.getSize());
	        }
	        else
	        {
	            itemButton.setText(item.getName());
	            itemButton.setBounds(item.getXPosition(), item.getYPosition(), 100, 30);
	        }

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
		// searches for the button matching the item name and removes it from the view
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
	    resetButton.addActionListener(roomController);
	}
	
	/**
	 * Sets the name of the room in the top left corner of the view.
	 * 
	 * @param name the name of the room to be displayed
	 */
	public void setRoomName(String name)
	{
	    roomNameLabel.setText(name);
	    this.currentRoomName = name;
	    repaint();
	}
	
	/**
	 * Sets the clue counter label to show how many items have been collected.
	 *
	 * @param count the number of items currently in the inventory
	 */
	public void setClueCount(int count)
	{
	    clueLabel.setText("Clues found: " + count + "/3");
	}
}