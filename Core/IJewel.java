package Core;

/**
 * This is an interface for Jewel objects. It extends the location interface,
 * and any classes implementing IJewel will implement the methods in ILocation.
 * @author Yacine Belaifa. 
 */
public interface  IJewel extends ILocation
{
    /**
     * Method to retrieve the color of the given Jewel object
     * @return String - the String representing the color of this jewel
     */
    public String getColor();

}
