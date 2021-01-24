package Obstacle;

import Core.ILocation;
import Core.IPlayer;

public interface ICrates extends ILocation
{
    
    public void pushCrate(IPlayer player);
    public boolean canPushCrate(IPlayer player);
    
    
}
