package Player;

import Core.IPlayer;


public class PlayerFactory 
{

/**
 * This is a factory class that constructs a Player object. 
 * @author Yacine Belaifa
 */
    
    /**
     * This method constructs a Jewel object based on the color passed in as a parameter.
     * @param jewelColor - the color of the jewel that is passed in. 
     * @return IJewel - a Jewel object that is newly created.
     */
    public IPlayer createPlayer(String p) 
    {
        if(p.equals("magenta"))
        {
            return new MagentaPlayer();
        }
        else if (p.equals("green"))
        {
            return new GreenPlayer();
        }
        else if (p.equals("blue"))
        {
            return new BluePlayer();
        }
        else if (p.equals("red"))
        {
            return new RedPlayer();
        }
        return null;
        
    }




    
}
