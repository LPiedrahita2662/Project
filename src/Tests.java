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

import java.util.ArrayList;
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
 * Purpose: Tests all major classes and methods in the escape room game including the model, view, controller, and repository.
 */
public class Tests
{
    public static void main(String[] args)
    {
        // CREATES A DOOR AND TESTS EXIT CODE
        Door door1 = new Door(false, "123");
        System.out.println(door1);
        System.out.println("checkExitCode correct: " + door1.checkExitCode("123"));
        System.out.println("checkExitCode incorrect: " + door1.checkExitCode("999"));

        // CREATES A FINAL DOOR AND TESTS IS FINAL DOOR
        Door door2 = new Door(true, "456");
        System.out.println("isFinalDoor: " + door2.isFinalDoor());

        // CREATES A ROOM AND TESTS GET ROOM NAME
        Room room1 = new Room("Study", door1);
        System.out.println(room1);
        System.out.println("getRoomName: " + room1.getRoomName());

        // CREATES A VASE ITEM AND TESTS ISCLICKED
        Item vase = new Item("Vase", "The first digit is 3", null, 800, 400, 80);
        System.out.println(vase);
        System.out.println("isClicked before: " + vase.isClicked());
        vase.clickItem();
        System.out.println("isClicked after: " + vase.isClicked());

        // TESTS ITEM GETTERS
        System.out.println("getName: " + vase.getName());
        System.out.println("getHintText: " + vase.getHintText());
        System.out.println("getXPosition: " + vase.getXPosition());
        System.out.println("getYPosition: " + vase.getYPosition());
        System.out.println("getSize: " + vase.getSize());

        // ADDS VASE TO ROOM AND TESTS ADD AND REMOVE ITEM
        room1.addItem(vase);
        System.out.println("room after addItem: " + room1);
        room1.removeItem(vase);
        System.out.println("room after removeItem: " + room1);

        // CREATES A LOCKED ITEM AND TESTS CHECKSOLVED AND ISSOLVED
        Item safe = new LockedItem("Safe", "The code is 618.", null, 500, 300, "618", 80);
        System.out.println(safe);
        System.out.println("isSolved before: " + ((LockedItem)safe).isSolved());
        System.out.println("checkSolved incorrect: " + ((LockedItem)safe).checkSolved("123"));
        System.out.println("checkSolved correct: " + ((LockedItem)safe).checkSolved("618"));
        System.out.println("isSolved after: " + ((LockedItem)safe).isSolved());

        // TESTS INVENTORY MODEL ADD AND GET ITEMS
        InventoryModel inventoryModel = new InventoryModel();
        inventoryModel.addItem(vase);
        System.out.println("inventory size after add: " + inventoryModel.getItems().size());
        inventoryModel.setSelectedItem(vase);
        System.out.println("getSelectedItem: " + inventoryModel.getSelectedItem().getName());

        // TESTS FILE SERVICE LOADS CORRECT NUMBER OF ROOMS
        FileService fileService = new FileService("GameData.csv");
        ArrayList<Room> rooms = fileService.loadRooms();
        System.out.println("rooms loaded: " + rooms.size());

        // TESTS ROOM REPOSITORY GET FIRST ROOM AND HAS NEXT ROOM
        RoomRepository roomRepo = new RoomRepository("GameData.csv");
        System.out.println("getFirstRoom: " + roomRepo.getFirstRoom().getRoomName());
        System.out.println("hasNextRoom: " + roomRepo.hasNextRoom());
        System.out.println("getNextRoom: " + roomRepo.getNextRoom().getRoomName());
        System.out.println("hasNextRoom after advance: " + roomRepo.hasNextRoom());

        // TESTS THE VIEW RENDERS ITEMS
        RoomRepository viewRepo = new RoomRepository("GameData.csv");
        Room firstRoom = viewRepo.getFirstRoom();
        EscapeGameView view = new EscapeGameView();
        view.getRoomView().renderItems(firstRoom);
        System.out.println("items rendered: " + view.getRoomView().getItemButtons().size());

        // TESTS ROOMCONTROLLER WIRING
        RoomRepository roomControllerRepo = new RoomRepository("GameData.csv");
        Room roomControllerRoom = roomControllerRepo.getFirstRoom();
        EscapeGameView roomControllerView = new EscapeGameView();
        RoomController roomController = new RoomController(roomControllerRoom, roomControllerView.getRoomView());
        roomControllerView.getRoomView().renderItems(roomControllerRoom);

        // TESTS INVENTORYCONTROLLER WIRING
        InventoryModel inventoryModel2 = new InventoryModel();
        InventoryController inventoryController = new InventoryController(inventoryModel2, roomControllerView.getInventoryView());
        roomController.setInventoryController(inventoryController);
        System.out.println("inventoryController set successfully");

        // TESTS CLUE COUNT UPDATE
        roomControllerView.getRoomView().setClueCount(2);
        System.out.println("setClueCount ran successfully");

        // TESTS SET ROOM NAME
        roomControllerView.getRoomView().setRoomName("Library");
        System.out.println("setRoomName: " + roomControllerRoom.getRoomName());
    }
}