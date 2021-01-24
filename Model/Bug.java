package Model;

import Core.IPlayer;

/**
 * This class is used to model the Bug object.
 * @author Yacine Belaifa
 */
public class Bug 
{
    /*---------------------------Constructor--------------------*/
    public Bug()    
    {
        
    }
    
    /**
     * Method to reverse the last move made by a particular Player, hence undoing it.
     * @param p - the Player whose move will be undone.
     */
    public void undoMove(IPlayer p)
    {
        //pops the last move off the given player's Stack
        String lastMove = p.getSequenceOfMoves().pop();
        //If the player's last move was turning left.
        if (lastMove.equals("TURN_LEFT"))
        {
            MoveFunctions.turn_right(p);  
            int currentLeftMoves = p.getDeck().getRemainingLeftMoves();
            p.getDeck().setRemainingLeftMoves(currentLeftMoves +1);
        }
        //If the player's last move was turning right.
        else if(lastMove.equals("TURN_RIGHT"))
        {
            MoveFunctions.turn_left(p);  
            int currentRightMoves = p.getDeck().getRemainingRightMoves();
            p.getDeck().setRemainingRightMoves(currentRightMoves +1);
        }
        else    //If the player's last move was going forward.
        {
            int[] currPosition = p.getLocation();
            if (p.getDirection().equals("EAST")) 
            {
                currPosition[0] = currPosition[0] - 1;
            }
            else if(p.getDirection().equals("WEST")) 
            {
                currPosition[0] = currPosition[0] + 1; 
            }
            else if(p.getDirection().equals("NORTH"))
            {
                currPosition[1] = currPosition[1] + 1;
            }
            else
            {
                currPosition[1] = currPosition[1] - 1; 
            }
            int currentForwardMoves = p.getDeck().getRemainingForwardMoves();
            p.getDeck().setRemainingForwardMoves(currentForwardMoves +1);
            p.setLocation(currPosition);
        }
            
    }
}