package Deck;
import Obstacle.IceWall;

public class LaserCard 
{
    private static IceWall wallToMelt; 
    
    public LaserCard(int[] wallLocation)
    {
        wallToMelt.setLocation(wallLocation);
    }

    public static void meltWall()
    {
        wallToMelt.changeWallState();
    }
}
