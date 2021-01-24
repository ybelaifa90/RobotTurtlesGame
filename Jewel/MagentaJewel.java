package Jewel;

import Core.IJewel;

/**
 * Concrete class to model a magenta-colored Jewel.
 * @author Yacine Belaifa
 */
public class MagentaJewel implements IJewel
{

    private int [] location;
    private final String color;

    public MagentaJewel()
    {
        this.location = new int[2];
        this.location[0] = 3;
        this.location[1] = 3;
        this.color = "magenta";
    }

    /**
     * This method retrieves the value of the 2-element int array representing the MagentaJewel's location
     * @return int[]: the 2-element array containing the MagentaJewel's location
     */
    @Override
    public int[] getLocation() 
    {
        return location;
    }

    @Override
    public void setLocation(int[] loc) 
    {
        location = loc;    
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
