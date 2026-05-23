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
* Scanner (Java Platform SE 8).
* Java SE 8 API Documentation.
* https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html
* 
* Version: 2026-05-04
*/

package service;
import model.Room;
import model.Item;
import model.LockedItem;
import model.Door;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;

import java.io.File;

/**
 * Purpose: FileService reads the data CSV file and builds Room, Item, LockedItem, and Door objects based on the data.
 */
public class FileService
{
	private String fileName;
	private ArrayList<Room> roomsFromFile;
	
	/**
	 * Initalizes a new FileService with the file name to load rooms from.
	 * 
	 * @param fileName the CSV file name
	 */
	public FileService(String fileName)
	{
		this.fileName = fileName;
		this.roomsFromFile = new ArrayList<Room>();
	}
	
	/**
	 * Reads the CSV file and creates Room objects for each room with items, lockedItems, and doors from the file data.
	 * 
	 * @return the ArrayList of rooms loaded from the file
	 */
	public ArrayList<Room> loadRooms()
	{
		// try-with-resources automatically closes scanner when done
		try (Scanner scanner = new Scanner(new File(fileName));)
		{
			Room currentRoom = null;
			
			// reads the file line by line
			while (scanner.hasNextLine())
			{
				// creates a new scanner for the line to parse the comma-separated data
				String line = scanner.nextLine();
				
				// skip empty lines that separate rooms in the file
				if (line.isEmpty()) continue;
				
				Scanner lineScanner = new Scanner(line);
				lineScanner.useDelimiter(",");
				
				// first part of the line indicates what the type of data is, room, item, locked item, or door
				String typeOfData = lineScanner.next();
				
				if (typeOfData.equals("ROOM"))
				{
					String roomName = lineScanner.next();
					currentRoom = new Room(roomName, null);
					roomsFromFile.add(currentRoom);
				}
				else if (typeOfData.equals("ITEM"))
				{
					String itemName = lineScanner.next();
					String hintText = lineScanner.next();
					String imageFileName = lineScanner.next();
					int xPosition = lineScanner.nextInt();
					int yPosition = lineScanner.nextInt();
					int size = lineScanner.nextInt();
					
					// loads image and adds item to the current room
					ImageIcon itemImage = loadImage(imageFileName);
					Item item = new Item(itemName, hintText, itemImage, xPosition, yPosition, size);
					currentRoom.addItem(item);
				}
				else if (typeOfData.equals("LOCKEDITEM"))
				{
					String itemName = lineScanner.next();
					String hintText = lineScanner.next();
					String imageFileName = lineScanner.next();
					int xPosition = lineScanner.nextInt();
					int yPosition = lineScanner.nextInt();
					String unlockCode = lineScanner.next();
					int size = lineScanner.nextInt();
					
					ImageIcon itemImage = loadImage(imageFileName);
					LockedItem item = new LockedItem(itemName, hintText, itemImage, xPosition, yPosition, unlockCode, size);
					currentRoom.addItem(item);
				}
				else if (typeOfData.equals("DOOR"))
				{
					boolean isFinalDoor = lineScanner.nextBoolean();
					String unlockCode = lineScanner.next();
					
					Door door = new Door(isFinalDoor, unlockCode);
					currentRoom.setDoor(door);
				}
				
				lineScanner.close();
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Error: File not found: " + fileName);
		}
		
		// returns the list of rooms loaded from the file
		return roomsFromFile;
	}
	
	/**
	 * Loads an image from the file.
	 * 
	 * @param imageFileName the name of the image file
	 * @return the ImageIcon loaded from the file
	 */
	public ImageIcon loadImage(String imageFileName)
	{
	    try
	    {
	        return new ImageIcon(imageFileName);
	    }
	    catch (Exception e)
	    {
	        System.out.println("Error loading image: " + imageFileName);
	        return null;
	    }
	}
}