package Jewel;

import Core.IJewel;

/**
 * Concrete class to model a green-colored Jewel.
 * @author Yacine Belaifa
 */
public class GreenJewel implements IJewel
{
    private int[] location;
    private final String color;

    public GreenJewel()
    {
        this.location = new int[2];
        this.location[0] = 3;
        this.location[1] = 4;
        color = "green"; 
    }

    /**
     * This method retrieves the value of the 2-element int array representing the GreenJewel's location
     * @return int[]: the 2-element array containing the GreenJewel's location
     */
    @Override
    public int[] getLocation() 
    {
        return location;
    }

    /**
     * This method updates the value of the GreenJewel's location with that of the 2-element array passed in the parameter
     * @param loc - a 2-element int array containing the new value of the GreenJewel's location.
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
