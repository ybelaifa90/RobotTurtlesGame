package Jewel;

import Core.IJewel;

/**
 * Concrete class to model a red-colored Jewel.
 * @author Yacine Belaifa
 */
public class RedJewel implements IJewel
{
    /*-----------Instance Variables------------*/
    private int [] location;
    private final String color;

    /*------------Constructor------------------*/
    public RedJewel()
    {
        this.location = new int[2];
        this.location[0] = 4;
        this.location[1] = 3;
        color = "red";
    }

    /**
     * This method retrieves the value of the 2-element int array representing the RedJewel's location
     * @return int[]: the 2-element array containing the Object's location
     */
    @Override
    public int[] getLocation() 
    {
        return location;
    }

    /**
     * This method updates the value of the RedJewel's location with that of the 2-element array passed in the parameter
     * @param loc - a 2-element int array containing the new value of the RedJewel's location.
     */
    @Override
    public void setLocation(int[] loc) 
    {
        this.location = loc;
        
    }

    /**
     * Method to retrieve the color of the given Jewel object
     * @return String - the String representing the color of this jewel
     */
    public String getColor()
    {
        return this.color;
    }

    
}
