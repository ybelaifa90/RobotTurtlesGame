package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import Core.IJewel;
import Jewel.JewelFactory;

/**
 * This is a class used by the GameModel to deal with Jewel objects. 
 * @author Yacine Belaifa
 */
public class JewelHandler 
{
    /*-----------Instance Variables---------------- */
    private ArrayList<IJewel> jewels;  
    private BoardTile[][] gameBoard;

    /*-----------Constructor---------------- */
    public JewelHandler()
    {
        jewels = new ArrayList<IJewel>();
        gameBoard = GameModel.getGameBoard();
    }

    /**
     * This method takes in the set of Jewel colors entered by the User and creates 
     * different Types of IJewel objects based on these colors.
     * @param colors - set of all colors entered by the user.
     */
    public void setUpJewelsList(HashSet<String> colors)
    {
        Iterator<String> i = colors.iterator();
        JewelFactory jFactory = new JewelFactory();
        while(i.hasNext())
        {
            String color = i.next();
            IJewel newJewel = jFactory.createJewel(color);    //Retrieving a jewel from the JewelFactory
            int[] position = newJewel.getLocation();
            gameBoard[position[0]][position[1]].setType("Jewel");
            jewels.add(newJewel);   //Updating the list of Jewels
        }
        GameModel.setGameBoard(gameBoard);
        this.writeJewelDataToFile();
    }
    /**
     * This method retrieves the list of objects implemented by IJewel.
     * @return ArrayList<IJewel> - the list of objects implemented by IJewel
     */
    public ArrayList<IJewel> getJewels()
    {
        return jewels;
    }

    /** 
     * This function creates a file, jewelData.txt, which records the specific Jewel 
     * information that is needed for the View.
     * This ensures the Model and the View do not communicate directly and only do so 
     * using the contents of this file.
    */
    public void writeJewelDataToFile()
    {
        try 
        {
            File jewelFile = new File("jewelData.txt");
            FileWriter writer = new FileWriter(jewelFile);
            for (int i =0; i < jewels.size(); i++)
            {
                IJewel j = jewels.get(i);
                int[] position = j.getLocation();
                writer.write(position[0] + " " + position[1] + " " + j.getColor() + "\n"); //getJewel?
            }
            writer.close();
            jewelFile.deleteOnExit();
        } 
        catch (IOException e) 
        {
            System.err.println("ERROR WITH WRITING JEWEL DATA TO THE FILE");
        }
    }
    
    public void setJewelsList(ArrayList<IJewel> jList)
    {
        this.jewels = jList;
    }

}
