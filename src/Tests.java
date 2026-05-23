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

import javax.swing.JButton;

import controller.InventoryController;
import controller.RoomController;
import model.Door;
import model.InventoryModel;
import model.Item;
import model.LockedItem;
import model.Room;
import service.FileService;
import service.RoomRepository;
import view.EscapeGameView;

/**
 * Purpose: The reponsibility of Main is ...
 *
 * Main is-a ...
 * Main is ...
 */
public class Tests
{
    public static void main(String[] args)
    {
        // CREATES A DOOR
    	Door door1 = new Door(false, "123");
        System.out.println(door1);
        System.out.println(door1.checkExitCode("123"));
        System.out.println(door1.checkExitCode("853490213"));

        // CREATES A ROOM
        Room room1 = new Room("Study", door1);
        System.out.println(room1);
        
        // CREATES A VASE ITEM AND TESTS CHANGING THE ISCLICKED
        Item vase = new Item("Vase", "The first digit is 3", null, 800, 400, 80);
		System.out.println(vase);
		System.out.println(vase.isClicked());
		vase.clickItem();
		System.out.println(vase.isClicked());
		
		// ADD VASE ITEM TO A NEW ROOM 
		Room room2 = new Room("Living Room", door1);
		room1.addItem(vase);
		System.out.println(room1);
		
		// CREATES NEW LOCKED ITEM (SAFE) AND CHECKS METHODS
		Item safe = new LockedItem("Safe", "The code is 618.", null, 500, 300, "618", 80);
		System.out.println(safe);
		System.out.println(((LockedItem)safe).isSolved());
		System.out.println(((LockedItem)safe).checkSolved("123"));
		System.out.println(((LockedItem)safe).checkSolved("618"));
		
		// TESTS FILE SERVICE
		FileService fileService = new FileService("GameData.csv");
        ArrayList<Room> rooms = fileService.loadRooms();
        System.out.println("Rooms loaded: " + rooms.size());
		
        // TESTS THE VIEW
        RoomRepository viewRepository = new RoomRepository("GameData.csv");
        Room firstRoom = viewRepository.getFirstRoom();
        EscapeGameView view = new EscapeGameView();
        view.getRoomView().renderItems(firstRoom);
        
        // TESTS ROOMCONTROLLER
        RoomRepository roomRepo = new RoomRepository("GameData.csv");
        Room roomControllerRoom = roomRepo.getFirstRoom();
        EscapeGameView roomControllerView = new EscapeGameView();
        RoomController roomController = new RoomController(roomControllerRoom, roomControllerView.getRoomView());
        roomControllerView.getRoomView().renderItems(roomControllerRoom);

        // TESTS INVENTORYCONTROLLER
        InventoryModel inventoryModel = new InventoryModel();
        InventoryController inventoryController = new InventoryController(inventoryModel, roomControllerView.getInventoryView());
        roomController.setInventoryController(inventoryController);
    }
}