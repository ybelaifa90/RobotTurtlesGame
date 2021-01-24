package Core;

import java.util.Stack;
import Model.Bug;
import Deck.*;

/**
 * This interface contains all the methods required by any type of Player object. It extends the location interface,
 * and any classes implementing IPlayer will implement the methods in ILocation.
 * @author Yacine Belaifa
 */
public interface IPlayer extends ILocation
{

    /*Accessor methods */

    /**
     * Method to retrieve the Bug object associated with the given Player object
     * @return Bug - the Bug associated with this Player
     */
    public Bug getBugCard();

    /**
     * Method to retrieve the Deck object associated with the given Player object
     * @return Deck - the Deck of cards associated with this Player
     */
    public Deck getDeck();

    /**
     * Method to retrieve the sequence of moves that the given Player has made as of a certain point of the game.
     * @return Stack<String> - the Stack of Strings representing the moves this Player has made.
     */
    public Stack<String> getSequenceOfMoves();

    /**
     * Method to retrieve the String representing the Player's current direction
     * @return String - the Player's current direction
     */
    public String getDirection();

    /**
     * Method to update the Player's direction with the direction specified in the parameter
     * @param dir - the Player's new direction
     */
    public void setDirection(String dir);

    /**
     * Method to retrieve the color of the given Player object
     * @return Deck - the String representing the color of this player
     */
    public String getColor();


}
