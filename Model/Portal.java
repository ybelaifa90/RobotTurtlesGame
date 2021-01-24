package Model;

/**
 * Class which models a Portal Tile - a tile that transports you to another location if you land on it.
 * @author  Yacine Belaifa
 */
public class Portal
{
    /*-------------------Instance Variables------------------- */
    private int[] location1;
    private int[] location2;

    /**
     * Constructor for the Portal class 
     * @param loc1 - the location of the first Portal tile
     * @param loc2 - the location of the second Portal tile
     */
    public Portal(int[] loc1, int[] loc2)
    {
        //Initializing coordinates for 1st portal tile
        this.location1 = new int[2];
        this.location1[0] = loc1[0];
        this.location1[1] = loc1[1];

        //Initializing coordinates for 2nd portal tile
        this.location2 = new int[2];
        this.location2[0] = loc2[0];
        this.location2[1] = loc2[1];
    }

    /**
     * Given the location from one Tile, return the location of the other Tile that is mapped to it.
     * @param location1
     * @return
     */
    public int[] getPortal1To2(int[] location1) 
    {
        return location2;
    }

    public int[] getPortal2To1(int[] location2) 
    {
        return location1;
    }

    public void setPortal1(int[] loc) 
    {
        this.location1 = loc;
    }

    public void setPortal2(int[] loc) 
    {
        this.location2 = loc;
    }
    
}
