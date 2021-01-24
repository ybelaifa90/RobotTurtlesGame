package Jewel;

import Core.IJewel;

/**
 * This is a factory class that constructs a Jewel object. 
 * @author Yacine Belaifa
 */

public class JewelFactory 
{
    /**
     * This method constructs a Jewel object based on the color passed in as a parameter.
     * @param jewelColor - the color of the jewel that is passed in. 
     * @return IJewel - a Jewel object that is newly created.
     */
    public IJewel createJewel(String jewelColor) 
    {
        if(jewelColor.equals("magenta"))
        {
            return new MagentaJewel();
        }
        else if (jewelColor.equals("green"))
        {
            return new GreenJewel();
        }
        else if (jewelColor.equals("blue"))
        {
            return new BlueJewel();
        }
        else if (jewelColor.equals("red"))
        {
            return new RedJewel();
        }
        return null;
        
    }




}
