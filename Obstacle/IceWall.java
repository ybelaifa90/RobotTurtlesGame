package Obstacle;

public class IceWall implements IIceWall
{
    private int[] location;
    private boolean melted;

    public IceWall()
    {
        this.location = new int[2];
        this.melted = false;
    }

    @Override
    public int[] getLocation() 
    {
        return this.location;
    }

    @Override
    public void setLocation(int[] loc) 
    {
        this.location = loc;    
    }

    @Override
    public boolean isPuddle() 
    {
        return this.melted;
    }

    @Override
    public void changeWallState()
    {
        this.melted = true;
    }
}
