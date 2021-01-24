package Model;

import java.util.ArrayList;
import Core.IPlayer;
import Deck.Deck;
import Deck.LaserCard;
public class MoveFunctions
{
  /*
Yacine Belaifa

MoveFunctions.java
*/

  public MoveFunctions()// constructor 
  {

  }
  /* left turn */
  public static void turn_left(IPlayer tm)
  {
    if (tm.getDirection().equals("EAST")) 
    {
        tm.setDirection("NORTH");
    }
    else if(tm.getDirection().equals("WEST")) 
    {
        tm.setDirection("SOUTH");
    }
    else if(tm.getDirection().equals("NORTH"))
    {
          tm.setDirection("WEST");
    }
    else
    {
        tm.setDirection("EAST");
    }
        
      Deck playerDeck = tm.getDeck();
      playerDeck.setRemainingLeftMoves(playerDeck.getRemainingLeftMoves() -1);//Reduce number of left turn cards in players deck by 1
      tm.getSequenceOfMoves().push("TURN_LEFT");  //Adding this move to the Stack of existing moves
  }
     
  /* right turn */
    public static void turn_right(IPlayer tm)
    {
        if (tm.getDirection().equals("EAST")) 
        {
            tm.setDirection("SOUTH");
        }
        else if(tm.getDirection().equals("WEST")) 
        {
            tm.setDirection("NORTH");
        }
        else if(tm.getDirection().equals("NORTH"))
        {
            tm.setDirection("EAST");
        }
        else
        {
            tm.setDirection("WEST");
        }
        Deck playerDeck = tm.getDeck();
        playerDeck.setRemainingRightMoves(playerDeck.getRemainingRightMoves() -1); //Reduce number of left turn cards in players deck by 1
        tm.getSequenceOfMoves().push("TURN_RIGHT"); //Adding this move to the Stack of existing moves
    }
    
    /* forward move */
  
    public static void forward(IPlayer tm)
    {
      int[] l = tm.getLocation();
      if (tm.getDirection().equals("EAST"))
      {
        l[0] = l[0] + 1;
            
      }
      else if(tm.getDirection().equals("WEST"))
      {
        l[0] = l[0] - 1; 
            
      }
      else if(tm.getDirection().equals("NORTH"))
      {
        l[1] = l[1] - 1; 
            
      }
      else
      {
        l[1] = l[1] + 1;
           
      }
      tm.setLocation(l);
      Deck playerDeck = tm.getDeck();
      playerDeck.setRemainingForwardMoves(playerDeck.getRemainingForwardMoves() -1);//Reduce number of left turn cards in players deck by 1
      tm.getSequenceOfMoves().push("GO_FORWARD"); //Adding this move to the Stack of existing moves
     
    }

    public void play3(ArrayList<String> moves, IPlayer currPlayer)
    {
      for (int j = 0; j < 3; j++)   
      {
          if(moves.get(j).equals("forward"))
          {
              MoveFunctions.forward(currPlayer);
          }
          else if (moves.get(j).equals("left turn"))
          {
              MoveFunctions.turn_left(currPlayer);
          }
          else if (moves.get(j).equals("right turn"))
          {
              MoveFunctions.turn_right(currPlayer);
          }
          else //laser card
          {
              LaserCard.meltWall();
          }
      } 
    }
    public void writeProgram(ArrayList<String> moves, IPlayer currPlayer)
    {
      for (int j = 0; j < moves.size(); j++)   
      {
          if(moves.get(j).equals("forward"))
          {
              MoveFunctions.forward(currPlayer);
          }
          else if (moves.get(j).equals("left turn"))
          {
              MoveFunctions.turn_left(currPlayer);
          }
          else if (moves.get(j).equals("right turn"))
          {
              MoveFunctions.turn_right(currPlayer);
          }
          else //laser card
          {
              LaserCard.meltWall();
          }
      } 
    }
}
