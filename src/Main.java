/**
* Lead Author(s):
* @author Luke Piedrahita
*
* References:
* Morelli, R., & Walde, R. (2016).
* Java, Java, Java: Object-Oriented Problem Solving
* https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
*
* Version: 2026-05-22
*/

import controller.EscapeGameController;
import model.EscapeGameModel;
import service.RoomRepository;
import view.EscapeGameView;

/**
 * Purpose: Creates the model, view, and controller for the escape game and starts the game.
 */
public class Main
{
    public static void main(String[] args)
    {
        // load the rooms from CSV file
        RoomRepository roomRepository = new RoomRepository("GameData.csv");

        // creates the game model with the first room
        EscapeGameModel gameModel = new EscapeGameModel(roomRepository.getFirstRoom());

        // creates the game view
        EscapeGameView gameView = new EscapeGameView();

        // creates the controller and starts the game
        EscapeGameController controller = new EscapeGameController(gameModel, gameView, roomRepository);
    }
}