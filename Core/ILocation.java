package Core;

/**
 * This is an interface designed to update and retrieve the location 
 * (represented by a 2-element int array) of an Object it implements.
 * @author Yacine Belaifa
 */
public interface ILocation 
{
    //-------------------Accessor methods---------------------
    /**
     * This method retrieves the value of the 2-element int array representing an Object's location
     * @return int[]: the 2-element array containing the Object's location
     */
    public int[] getLocation();

    //-------------------Setter methods---------------------
    /**
     * This method updates the value of the Object's location with that of the 2-element array passed in the parameter
     * @param loc - a 2-element int array containing the new value of the Object's location.
     */
    public void setLocation(int[] loc);
}
