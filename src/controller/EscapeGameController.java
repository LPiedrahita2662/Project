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

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.EscapeGameModel;
import model.Room;
import service.RoomRepository;
import view.EscapeGameView;

/**
 * Purpose: EscapeGameController connects the model, view, and repository together and runs the game.
 */
public class EscapeGameController
{
	private Room currentRoom;
	private RoomRepository roomRepository;
	private EscapeGameModel model;
	private EscapeGameView view;
	private RoomController roomController;
	private InventoryController inventoryController;
	
	/**
	 * Initializes a new EscapeGameController with the model, view, and room repository and starts the game by calling run().
	 * 
	 * @param model the escape game model that holds the current room and inventory
	 * @param view the escape game view that displays the current room and inventory
	 * @param roomRepository the room repository that holds all of the rooms in the game
	 */
	public EscapeGameController(EscapeGameModel model, EscapeGameView view, RoomRepository roomRepository)
	{
		this.model = model;
		this.view = view;
		this.roomRepository = roomRepository;
		this.currentRoom = roomRepository.getFirstRoom();
		run();
	}
	
	/**
	 * Sets up the inventory controller, room controller, and action listeners for the door and item buttons.
	 */
	public void run()
	{
	    inventoryController = new InventoryController(model.getInventoryModel(), view.getInventoryView());

	    roomController = new RoomController(currentRoom, view.getRoomView());
	    roomController.setInventoryController(inventoryController);
	    roomController.setEscapeGameController(this);
	    
	    view.getRoomView().renderItems(currentRoom);
	    view.getRoomView().setRoomName(currentRoom.getRoomName());
	    
	    for (JButton button : view.getRoomView().getItemButtons())
	    {
	        button.addActionListener(roomController);
	    }
	}
	
	/**
	 * Handles the door click event by moving to the next room, or prompting the user for the final escape code.
	 */
	public void handleDoorClick()
	{
		if (roomRepository.hasNextRoom())
		{
			currentRoom = roomRepository.getNextRoom();
			model.setCurrentRoom(currentRoom);
			roomController.setRoom(currentRoom);
			
			view.getRoomView().renderItems(currentRoom);
			view.getRoomView().setRoomName(currentRoom.getRoomName());
			
			for (JButton button : view.getRoomView().getItemButtons())
			{
				button.addActionListener(roomController);
			}
		}
		else
		{
			handleFinalDoor();
		}
	}
	
	/**
	 * Prompts the player to enter the final code to escape, checks if the code is correct, and updates the model and view.
	 */
	public void handleFinalDoor()
	{
		String code = JOptionPane.showInputDialog(view, "Enter the final code to escape:", "Final Door", JOptionPane.QUESTION_MESSAGE);
		
		if (code != null && currentRoom.getDoor().checkExitCode(code))
		{
			model.setWon(true);
			JOptionPane.showMessageDialog(view, "Congratulations! You've escaped!", "You Win!", JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(view, "Incorrect code. Try again.", "Wrong Code", JOptionPane.ERROR_MESSAGE);
		}
	}
}