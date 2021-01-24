package Player;

import Core.IPlayer;
import java.util.Stack;
import Model.Bug;
import Deck.*;


public class MagentaPlayer implements IPlayer 
{

    private int[] location; /*
                             * an int[] array of size 2 defines the player's location. Element 0 is their
                             * horizontal position, element 1 is their vertical position.
                             */
    private Deck playerDeck;
    private Bug bugCard;
    private String turtDirection;
    private Stack<String> moves; // stack of player moves , needed for bug functionality
    private final String color;

    public MagentaPlayer()// constructor 
    {

        location = new int[2];
        
        location[0] = 7;
        location[1] = 0;
        turtDirection = "EAST";
        playerDeck = new Deck(8,8,18);
        bugCard = new Bug();
        moves = new Stack<String>();
        color = "magenta";

    }
    @Override
    public int[] getLocation() 
    {
        return this.location;
    }

    @Override
    public void setLocation(int[] position) 
    {
        this.location[0] = position[0];
        this.location[1] = position[1];
    }

    @Override
    public Bug getBugCard()
    {
        return this.bugCard;
    }

    @Override
    public Deck getDeck() 
    {
        return this.playerDeck;
    }

    @Override
    public Stack<String> getSequenceOfMoves() 
    {
       return moves;
    }

    @Override
    public String getDirection() 
    {
        return this.turtDirection;
    }

    @Override
    public void setDirection(String dir) 
    {
        this.turtDirection = dir;
    }

    @Override
    public String getColor() 
    {
        return color;
    }
}
