package Model;

import Core.ITile;

/**
 * This class implements the Tile interface, and constructs a concrete 
 * BoardTile object that will make up a piece of the game board.
 * @author Yacine Belaifa
 */
public class BoardTile implements ITile
{

    /*-------------------Instance Variables---------------------------------- */
    private int[] location ;    // array of size 2 where location[0] = x_cord, location[1] = y_cord
    private boolean occupied;   // a boolean variable indicating if this BoardTile contains an object or not.
    private String type;        // the type of object that the BoardTile contains. It will hold a value of "None" if isOccupied = false
    
    /**
     * Constructor for the BoardTile class
     * @param location - the 2-element int array that indicates the BoardTile's position on the game board.
     * @param type - the type of object that the BoardTile contains. 
     */
    public BoardTile(int[]location, String type) 
    {
        this.location = location;
        this.type = type;
        this.occupied = false;
    }

    /*---------------------Accessor Methods------------------------ */

    /**
     * This method retrieves the value of the 2-element int array representing an Object's location
     * @return int[]: the 2-element array containing the Object's location
     */
    public int[] getLocation()
    {
        return this.location;

    }

    /**
    * In the case where the current TileObject is occupied, this method identifies the Object that the Tile contains.
    * @return the type of Object on the Tile if it is occupied, "None" otherwise.
    */
    public String getType()
    {
        return this.type ;
    }

    /**
    * This method identifies if the current Tile is empty, or if it is occupied by another Object.
    * @return true if the tile is occupied, false otherwise
    */
    public boolean isOccupied()
    {
        return this.occupied;

    }
    
    /*---------------------Setter Methods----------------------- */
    /**
    * This method identifies if the current Tile is empty, or if it is occupied by another Object.
    * @return true if the tile is occupied, false otherwise
    */
    public void setLocation(int[] loc)
    {
        this.location = loc;
    }

    /**
     * This method updates the type of Object contained by the Tile.
     * @param type - the new Type of object contained by the Tile.
     */
    public void setType(String t)
    {
         this.type = t;
    }

    /**
     * This method updates the Tile object to indicate if the current Tile is occupied by some Object or not.
     * @param status - the boolean value indicating if the Tile is occupied.
     */
    public void setOccupiedStatus(boolean status)
    {
        this.occupied = status;
    }


    

    
}
