package Model;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashSet;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import Core.IPlayer;
import Player.PlayerFactory;

/**
 * This is a class used by the GameModel to deal with Player objects. 
 * @author Yacine Belaifa
 */
public class PlayerHandler 
{
    /*-----------Instance Variables---------------- */
    private ArrayList<IPlayer> players;  
    private BoardTile[][] gameBoard;

     /*-----------Constructor---------------- */
    public PlayerHandler()
    {
        players = new ArrayList<IPlayer>();
        gameBoard = GameModel.getGameBoard();
    }
    
    /**
     * This method takes in the set of Player colors entered by the User and creates 
     * different Types of objects implementing IPlayer based on these colors.
     * @param colors - set of all colors entered by the user.
     */
    public void setUpPlayersList(HashSet<String> colors) // sets up players for the game 
    {
        Iterator<String> i = colors.iterator();
        PlayerFactory pFactory = new PlayerFactory();
        while(i.hasNext())
        {
            String color = i.next();
            IPlayer newPlayer = pFactory.createPlayer(color);   //Retrieving a player from the PlayerFactory
            int[] position = newPlayer.getLocation();
            gameBoard[position[0]][position[1]].setType("Player");
            System.out.println(players.add(newPlayer)); 
        }
        GameModel.setGameBoard(gameBoard);
        this.writePlayerDataToFile();
    }

    /**
     * This method retrieves the list of objects implemented by IPlayer.
     * @return ArrayList<IPlayer> - the list of objects implemented by IPlayer
     */
    public ArrayList<IPlayer> getPlayers()
    {
        return players;
    }
    /**
     * This function creates a file, playerData.txt, which records the specific Player information that 
     * is needed for the View. This ensures the Model and the View do not communicate 
     * directly and only do so using the contents of this file.
    */
    public void writePlayerDataToFile()
    {
        try 
        {
            File playerFile = new File("playerData.txt");
            FileWriter writer = new FileWriter(playerFile);
            for (int i =0; i < players.size(); i++)
            {
                IPlayer p = players.get(i);
                int[] position = p.getLocation();
                writer.write(position[0] + " " + position[1] + " " + p.getColor() + " " + p.getDirection() + "\n");
            }
            
            writer.close();
            playerFile.deleteOnExit();
        } 
        catch (IOException e) 
        {
            System.err.println("ERROR WITH WRITING PLAYER DATA TO THE FILE");
        }
    }
    public void setPlayersList(ArrayList<IPlayer> pList)
    {
        this.players = pList;
    }
}
