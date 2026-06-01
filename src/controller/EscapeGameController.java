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
	 * Sets up the inventory controller, room controller, action listeners for the door and item buttons, and shows the intro dialog.
	 */
	public void run()
	{
		// creates the inventory and room controllers and wires them together
	    inventoryController = new InventoryController(model.getInventoryModel(), view.getInventoryView());
	    roomController = new RoomController(currentRoom, view.getRoomView());
	    roomController.setInventoryController(inventoryController);
	    roomController.setEscapeGameController(this);
	    
	    // renders the first room and sets the room name label
	    view.getRoomView().renderItems(currentRoom);
	    view.getRoomView().setRoomName(currentRoom.getRoomName());
	    
	    // adds the room controller as a listener to all item buttons in the room
	    for (JButton button : view.getRoomView().getItemButtons())
	    {
	        button.addActionListener(roomController);
	    }
	    
	    // shows the intro dialog explaining the game to the player
	    JOptionPane.showMessageDialog(view,
	        "You've been locked in a mysterious library.\n" +
	        "Search the room for clues and crack the codes to escape.\n\n" +
	        "- Examine all items in each room before moving on.\n" +
	        "- Use the hints you find to solve the locked items.\n" +
	        "- Find the final escape code to win.\n\n" +
	        "Good luck.",
	        "Welcome to the Escape Room",
	        JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Handles the door click event. If items have not all been collected shows a warning. If there is a next room moves to it. If it is the final room prompts for the escape code.
	 */
	public void handleDoorClick()
	{
	    if (roomRepository.hasNextRoom())
	    {
	    	// blocks the player from leaving if they haven't collected all 3 items
	        int itemsCollected = model.getInventoryModel().getItems().size();
	        if (itemsCollected < 3)
	        {
	            JOptionPane.showMessageDialog(view, "You have not yet examined all items in the current room.", "Not so fast!", JOptionPane.WARNING_MESSAGE);
	            return;
	        }

	        // resets the clue counter for the new room
	        view.getRoomView().setClueCount(0);
	        
	        // advances to the next room and updates the model, controller, and view
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
	    
	    // null means the player clicked cancel so exit without showing an error
	    if (code == null)
	    {
	        return;
	    }
	    
	    if (currentRoom.getDoor().checkExitCode(code))
	    {
	    	// marks the game as won in the model
	        model.setWon(true);
	        
	        // gives the player the option to play again or exit
	        Object[] options = {"Play Again", "Exit Game"};
	        int choice = JOptionPane.showOptionDialog(view,
	            "Congratulations! You've escaped!",
	            "You Win!",
	            JOptionPane.YES_NO_OPTION,
	            JOptionPane.INFORMATION_MESSAGE,
	            null,
	            options,
	            options[0]);
	        
	        if (choice == 0)
	        {
	            view.dispose();
	            RoomRepository newRepo = new RoomRepository("GameData.csv");
	            EscapeGameModel newModel = new EscapeGameModel(newRepo.getFirstRoom());
	            EscapeGameView newView = new EscapeGameView();
	            new EscapeGameController(newModel, newView, newRepo);
	        }
	        else
	        {
	            System.exit(0);
	        }
	    }
	    else
	    {
	        JOptionPane.showMessageDialog(view, "Incorrect code. Try again.", "Wrong Code", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	/**
	 * Prompts the player to confirm a game reset and restarts the game if confirmed.
	 */
	public void handleReset()
	{
	    int choice = JOptionPane.showConfirmDialog(view, 
	    	"Are you sure you want to reset the game?",
	        "Reset Game",
	        JOptionPane.YES_NO_OPTION);
	    
	    //  ends the current game and creates a new instance from the beginning
	    if (choice == JOptionPane.YES_OPTION)
	    {
	        view.dispose();
	        RoomRepository newRepo = new RoomRepository("GameData.csv");
	        EscapeGameModel newModel = new EscapeGameModel(newRepo.getFirstRoom());
	        EscapeGameView newView = new EscapeGameView();
	        new EscapeGameController(newModel, newView, newRepo);
	    }
	}
}