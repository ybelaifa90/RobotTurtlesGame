package Model;

import Core.IPlayer;
import Core.IJewel;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class represents all the game data in our program.
 * @author Yacine Belaifa
 */

public class GameModel  
{
    /*--------------------Instance Variables---------------------------------*/
    private PlayerHandler playerHandler;  
    private JewelHandler jewelHandler;  
    private Board boardClass;// instance of the Board class 
    private static BoardTile[][] gameBoard; //the 2d grid of tiles that is an instance of the Board class
    protected boolean hasWon; 

    /*--------------------Constructor---------------------------------*/
    public GameModel()
    {
        this.boardClass = new Board(); // initialize board 
        GameModel.gameBoard = boardClass.getGrid(); // get the grid from the boardClass
        this.playerHandler = new PlayerHandler();
        this.jewelHandler = new JewelHandler();
        this.hasWon = false;
    } 
    
    /*-----------------------Getter Methods------------------------*/ 

    /**
     * This method retrieves an instance of the BoardTile[][] Grid represnting the game board.
     * @return - BoardTile[][] representing the game board. It is static so that the PlayerHandler and 
     * JewelHandler can access it.
     */
    public static BoardTile[][] getGameBoard()
    {
        return GameModel.gameBoard;
    }

    /**
     * This method takes in some object implementing IPlayer, and runs through a complete turn for this IPlayer.
     * @param p - the IPlayer who is taking their turn
     * @param move - the type of move (turn left, turn right, forward) that the IPlayer will complete.
     * @return - an int stating if the program exited successfully - returns 1 if yes, 0 if no.
     */
    public int takePlayerTurn(IPlayer p, String move) // this method handles taking a turn for the current player
    {
            System.out.println("The user chose: " + move);
            if (move.equals("forward"))                             //If the Player wants to move forward
            {
                if(isValidMove(p.getLocation(),p.getDirection())==true && this.canCaptureJewel(p) == true)  //If the current player is about to collect their jewel
                {
                    int[] position = p.getLocation();               //retrieves the Player's position.
                    if(p.getDeck().getRemainingForwardMoves() >0)
                    {
                        //When the current player is about to win game - collects jewel and clears it from board.
                        BoardTile prevTile = gameBoard[position[0]][position[1]];
                        MoveFunctions.forward(p);                  // moving player forward
                        System.out.println("Cards remaining: " + p.getDeck() + "\n");//move forward - must do this after checking if going forward will capture the jewel
                        hasWon = true;
                        this.clearJewelFromBoard(p,prevTile);       //removes the jewel from the Board.
                        this.playerHandler.writePlayerDataToFile(); //Updating the position of the Player in its text file.
                        this.jewelHandler.writeJewelDataToFile();   //Updating the position of the Jewel in its text file.
                        return 1;
                    }
                    else
                    {
                        System.out.println("ERROR: This player has no more forward cards");
                        if(p.getDeck().isEmpty())
                        {
                            this.clearPlayerFromBoard(p,gameBoard[position[0]][position[1]]);
                            this.playerHandler.writePlayerDataToFile();
                        }
                        else
                        {
                            return 0;   //Code for "Redo this turn"
                        }
                    }
                }
                else if (this.canCaptureJewel(p) == true && isValidMove(p.getLocation(),p.getDirection())==false) //If the move the user wants to make is invalid.
                {
                    System.out.println("ERROR: Invalid Move\n");
                }
                else
                {
                    //Average case for moving forward - going forward is a valid move and the Player still has 
                    System.out.println("Current Location is: " + Arrays.toString(p.getLocation()));
                    if (isValidMove(p.getLocation(),p.getDirection()))
                    {
                        if(p.getDeck().getRemainingForwardMoves() >0)// check whether a Player still has forward cards in their deck
                        {
                            MoveFunctions.forward(p); 
                            this.playerHandler.writePlayerDataToFile();
                            System.out.println("Updated Location is: " + Arrays.toString(p.getLocation()) + "\n");
                            System.out.println("Cards remaining: " + p.getDeck() + "\n");// Display the Player's deck
                            return 1;
                        }
                        else
                        {
                            if(p.getDeck().isEmpty())   //If the player has no remaining cards in their deck
                            {
                                int[] position = p.getLocation();
                                this.clearPlayerFromBoard(p,gameBoard[position[0]][position[1]]);   //Player is removed from the Board
                                this.playerHandler.writePlayerDataToFile();         //Writing the Player's new position to their text file (in PlayerHandler)
                                return 0;
                            }
                            else
                            {
                                return 0;
                            }
                        }
                    }
                    else
                    {
                        System.out.println("ERROR: Invalid Move\n");
                    }
                }
                return 1;
            }
                else if (move.equals("left turn"))
                {
                    if(p.getDeck().getRemainingLeftMoves() >0)
                    {
                        MoveFunctions.turn_left(p); 
                        this.playerHandler.writePlayerDataToFile();
                        System.out.println("Cards remaining: " + p.getDeck());;// Display the Player's deck
                        System.out.println("Updated Location is: " + Arrays.toString(p.getLocation()));
                        return 1;
                    }
                    else
                    {
                        System.out.println("ERROR: This player has no more left turn cards\n");
                        if(p.getDeck().isEmpty())
                        {
                            int[] position = p.getLocation();
                            this.clearPlayerFromBoard(p,gameBoard[position[0]][position[1]]);
                            this.playerHandler.writePlayerDataToFile(); //Writing the Player's new position to their text file (in PlayerHandler)
                            return 1;
                        }
                        else
                        {
                            return 0;
                        }
                    }
                }
                else if (move.equals("right turn"))
                {
                    if(p.getDeck().getRemainingRightMoves() >0) //check if the Player has right turn cards remaining in their Deck.
                    {
                        MoveFunctions.turn_right(p); 
                        this.playerHandler.writePlayerDataToFile(); //Writing the Player's new position to their text file (in PlayerHandler)
                        System.out.println("Cards remaining: " + p.getDeck());
                        System.out.println("Updated Location is: " + Arrays.toString(p.getLocation())); 
                        return 1;
                    }
                    else
                    {
                        System.out.println("ERROR: This player has no more right turn cards\n");
                        if(p.getDeck().isEmpty())
                        {
                            int[] position = p.getLocation();
                            this.clearPlayerFromBoard(p,gameBoard[position[0]][position[1]]);   //Removing the Player from the Board.
                            this.playerHandler.writePlayerDataToFile();
                            return 0;
                        }
                        else
                        {
                            return 0;
                        }
                    }
                }
                
            return 1;       //Default case - move was successful - return 1.
            }
    
    /**
     * This method uses the Player's position and direction to determine if the Player's upcoming move is allowed
     * (ie: if they are about to walk into a wall or an obstacle)
     * @param currposition - the Player's current position
     * @param dir - the Player's current direction
     * @return - a boolean indicating if the move the Player wants to do is a legal operation - true if it is, false otherwise.
     */
    public boolean isValidMove(int[] currposition, String dir)
    {
        int x_cord = currposition[0];
        int y_cord = currposition[1];
        if (x_cord - 1 < 0 && dir.equals("WEST") || x_cord +1 >= gameBoard.length && dir.equals("EAST"))
        {
            return false;
        }
        else if(y_cord+1 >=gameBoard.length && dir.equals("SOUTH") || y_cord - 1 <0&&dir.equals("NORTH") )
        {
            return false;
        }
        return true ;
    }
    
    /**
     * This method takes care of the GameModel logic needed for processing a bug - identifying the
     * previous move, calling on the Bug to undo it, and resetting the Tile's status based on this
     * new information.
     * @param bugStatus - a String - "yes" or "no" - if the Player wanted to use the Bug.
     * @param p - the Player who has just completed their turn.
    */
    public void bugProcessing(String bugStatus, IPlayer p)
    {
        if(bugStatus.equals("yes") && p.getSequenceOfMoves().size() > 0)
        {
            String prevMove= p.getSequenceOfMoves().peek();
            p.getBugCard().undoMove(p);//We are undoing the most recently played move
            //This fragment just clears up the board if we specifically change the status
            int[] prevLocation = p.getLocation();
            int[] currLocation = prevLocation;
            if(prevMove.equals("FORWARD"))
            {
                currLocation = p.getLocation(); 
            }
            
            BoardTile prevTile = gameBoard[prevLocation[0]][prevLocation[1]];
            BoardTile currTile = gameBoard[currLocation[0]][currLocation[1]];
            this.playerHandler.writePlayerDataToFile();
            prevTile.setOccupiedStatus(false);
            currTile.setOccupiedStatus(true);
        }
        else if(bugStatus.equals("yes") && p.getSequenceOfMoves().size() == 0)
        {
            System.out.println("\n ERROR: We are trying to Bug when we have not written any code yet \n");
        }
    }
    

    public boolean canCaptureJewel(IPlayer p)
    {
        int[] pos = p.getLocation();
        BoardTile nextTileLocation = null;  //Tile that player is about to move to 
        //set location of next tile
        if (p.getDirection().equals("EAST") && pos[0] <gameBoard.length -1 ) // east is right 
        { 
            nextTileLocation = gameBoard[pos[0]+1][pos[1]];
        }
        else if(p.getDirection().equals("WEST")&& pos[0] >0) // west is left 
        {
            nextTileLocation = gameBoard[pos[0]-1][pos[1]];
        }             
        else if(p.getDirection().equals("NORTH")&& pos[1] >0)
        {
            nextTileLocation = gameBoard[pos[0]][pos[1]-1];
        }
        else if(p.getDirection().equals("SOUTH")&& pos[1]<gameBoard.length-1 )           
        {
           nextTileLocation = gameBoard[pos[0]][pos[1]+1]; 
        }
        else
        {
            nextTileLocation = gameBoard[pos[0]][pos[1]]; 
        }
        //verify that the Type of tile is a Jewel
        if (nextTileLocation.getType().equals("Jewel"))
        {
            return true;
        }
        return false;
    }
   
    /**
     * This method removes a jewel from this specified tile on the board 
     * @param p - The Player who has just captured the IJewel
     * @param prevTile - the BoardTile that we want to remove the IJewel from
     */ 
    public void clearJewelFromBoard(IPlayer p, BoardTile prevTile)
    {
        int[] position = p.getLocation();
        ArrayList<IJewel> jewels = this.jewelHandler.getJewels();
        for (int i =0; i < jewels.size();i++ )
        {
            IJewel j = jewels.get(i);
            int[] jPosition = j.getLocation();
            if (jPosition[0] == position[0] && jPosition[1] == position[1])
            {
               jewels.remove(j);
            }
        }
        BoardTile nextTile = gameBoard[position[0]][position[1]];
        prevTile.setOccupiedStatus(false);
        nextTile.setOccupiedStatus(true);
        this.playerHandler.getPlayers().remove(p);
    }

    /**
     * This method removes a Player from this specified tile on the board 
     * (called when the Player moves forward, for their Position to change from one BoardTile to the next )
     * @param p - The Player we want to remove
     * @param prevTile - the BoardTile that we want the Player to disappear from.
     */
    public void clearPlayerFromBoard(IPlayer p, BoardTile prevTile) 
    {
        int[] position = p.getLocation();
        BoardTile nextTile = gameBoard[position[0]][position[1]];
        prevTile.setOccupiedStatus(false);
        nextTile.setOccupiedStatus(true);
        this.playerHandler.getPlayers().remove(p);
    
    }

    /**
     * Method to retrieve the instance of PlayerHandler
     * @return - an instance of PlayerHandler
     */
    public PlayerHandler getPlayerHandler()
    {
        return this.playerHandler;
    }

    /**
     * Method to retrieve the instance of JewelHandler
     * @return - an instance of JewelHandler
     */
    public JewelHandler getJewelHandler()
    {
        return this.jewelHandler;
    }

    /**
     * Method to retrieve the instance of the current Player
     * @return - a boolean indicating the Player has won the game.
     */
    public boolean playerHasWon()
    {
        return this.hasWon;
    }

    /**
     * Method to update the static instance of the GameBoard.
     * @param board - the current instance of the 2D BoardTile array representing the current state of the board. 
     */
    public static void setGameBoard(BoardTile[][] board)
    {
        GameModel.gameBoard = board;
    }

    public void setUpIceWalls()
    {
        try 
        {
            FileReader reader = new FileReader("ObstacleData\\iceWallData.txt");
            BufferedReader bReader = new BufferedReader(reader);
            String line;
 
            while ((line = bReader.readLine()) != null) 
            {
                String[] pieces = line.split(" ");
                int xPosition = Integer.parseInt(pieces[0]);
                int yPosition = Integer.parseInt(pieces[1]);
                gameBoard[xPosition][yPosition].setType("IceWall");
                gameBoard[xPosition][yPosition].setOccupiedStatus(true);
            } 
        }   
        catch (IOException e) 
        {
            System.err.println("ERROR READING iceWallData.txt");
        } 
    }
    public void setUpStoneWalls()
    {
        try 
        {
            FileReader reader = new FileReader("ObstacleData\\stoneWallData.txt");
            BufferedReader bReader = new BufferedReader(reader);
            String line;
 
            while ((line = bReader.readLine()) != null) 
            {
                String[] pieces = line.split(" ");
                int xPosition = Integer.parseInt(pieces[0]);
                int yPosition = Integer.parseInt(pieces[1]);
                gameBoard[xPosition][yPosition].setType("StoneWall");
                gameBoard[xPosition][yPosition].setOccupiedStatus(true);
            } 
        }   
        catch (IOException e) 
        {
            System.err.println("ERROR READING stoneWallData.txt");
        } 
    }
    public void setUpCrates()
    {
        try 
        {
            FileReader reader = new FileReader("ObstacleData\\crateData.txt");
            BufferedReader bReader = new BufferedReader(reader);
            String line;
 
            while ((line = bReader.readLine()) != null) 
            {
                String[] pieces = line.split(" ");
                int xPosition = Integer.parseInt(pieces[0]);
                int yPosition = Integer.parseInt(pieces[1]);
                gameBoard[xPosition][yPosition].setType("Crate");
                gameBoard[xPosition][yPosition].setOccupiedStatus(true);
            } 
        }   
        catch (IOException e) 
        {
            System.err.println("ERROR READING crateData.txt");
        } 
    }
}

