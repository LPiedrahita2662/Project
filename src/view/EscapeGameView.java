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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Purpose: EscapeGameView is the main view of the game, holds the room view, inventory view, and the label and message bar.
 * 
 * EscapeGameView is-a JFrame
 */
public class EscapeGameView extends JFrame
{
	private RoomView roomView;
	private InventoryView inventoryView;
	
	/**
	 * Initializes a new EscapeGameView with the layout, panel, message bar, room view, and inventory view.
	 */
	public EscapeGameView()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Escape Room Game");
		setMinimumSize(new Dimension(1100, 650));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(169, 169, 169));
		
		// adds the top panel to the main panel 
		mainPanel.add(topPanel, BorderLayout.NORTH);
		
		// creates the room view and inventory view
		roomView = new RoomView();
		inventoryView = new InventoryView();
		
		// sets the size of the room view and inventory view
		roomView.setSize(new Dimension(850, 600));
		inventoryView.setPreferredSize(new Dimension(160, 600));
		
		mainPanel.add(roomView, BorderLayout.CENTER);
		add(inventoryView, BorderLayout.WEST);
		add(mainPanel, BorderLayout.CENTER);
		
		pack();
		setVisible(true);
	}
	
	/**
	 * Returns the RoomView panel.
	 * 
	 * @return the RoomView panel
	 */
	public RoomView getRoomView()
	{
	    return roomView;
	}

	/**
	 * Returns the InventoryView panel.
	 * 
	 * @return the InventoryView panel
	 */
	public InventoryView getInventoryView()
	{
	    return inventoryView;
	}
}