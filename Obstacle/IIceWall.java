package Obstacle;
import Core.*;

/**
 * Interface to construct IceWall objects
 */
public interface IIceWall extends  ILocation
{
    public boolean isPuddle();
    public void changeWallState();

}
