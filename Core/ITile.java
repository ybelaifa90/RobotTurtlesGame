
package Core;

/**
 * This interface contains all the methods required by any type of Tile object. It extends the location interface,
 * and any classes implementing IJewel will implement the methods in ILocation.
 * @author Yacine Belaifa
 */
public interface ITile extends ILocation
{
    /*--------------------Accessor Methods----------------------- */
    /**
    * This method identifies if the current Tile is empty, or if it is occupied by another Object.
    * @return true if the tile is occupied, false otherwise
    */
    public boolean isOccupied();

    /**
    * In the case where the current TileObject is occupied, this method identifies the Object that the Tile contains.
    * @return the type of Object on the Tile if it is occupied, "None" otherwise.
    */
    public String getType();

    /*--------------------Setter Methods------------------------ */
    /**
     * This method updates the type of Object contained by the Tile.
     * @param type - the new Type of object contained by the Tile.
     */
    public void setType(String type);

    /**
     * This method updates the Tile object to indicate if the current Tile is occupied by some Object or not.
     * @param status - the boolean value indicating if the Tile is occupied.
     */
    public void setOccupiedStatus(boolean status);

}
