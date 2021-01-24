package Jewel;

import Core.IJewel;

/**
 * Concrete class to model a blue-colored Jewel.
 * @author Yacine Belaifa
 */

public class BlueJewel implements IJewel
{
    /*----------Instance Variables----- */
    private int [] location;
    private final String color;

    public BlueJewel()
    {
        this.location = new int[2];
        this.location[0] = 4;
        this.location[1] = 4;
        color = "blue"; 
    }

    /**
     * This method retrieves the value of the 2-element int array representing the GreenJewel's location
     * @return int[]: the 2-element array containing the GreenJewel's location
     */
    @Override
    public int[] getLocation() 
    {
        return this.location;
    }

    /**
     * This method updates the value of the BlueJewel's location with that of the 2-element array passed in the parameter
     * @param loc - a 2-element int array containing the new value of the BlueJewel's location.
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
        return color;
    }

   

  
}
