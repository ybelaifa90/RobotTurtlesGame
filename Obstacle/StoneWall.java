package Obstacle;

import Core.ILocation;
public class StoneWall implements ILocation
{
    private int[] location;

    public StoneWall(int[] loc)
    {
        this.location = new int[2];
        this.location[0] = loc[0];
        this.location[1] = loc[1];
    }

    @Override
    public int[] getLocation() 
    {
        return this.location;
    }

    @Override
    public void setLocation(int[] loc) 
    {
        location[0] = loc[0];
        location[1] = loc[1];
    }
    
}
