package Obstacle;

import Core.IPlayer;
import Model.GameModel;
import Model.BoardTile;

public class Crate implements ICrates
{
    private int[] location;
    private BoardTile[][] gameBoard;

    public Crate(int[] loc)
    {
        this.location = new int[2];
        this.location[0] = loc[0];
        this.location[1] = loc[1];
        gameBoard = GameModel.getGameBoard();
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
    /**
     * Method which either moves the crate if there is a Player right next to it, 
     * or does not if the wall is in front of an obstacle.
     * @param player - the player who is actively moving the crate
     */
    public void pushCrate(IPlayer player) 
    {
        if (this.canPushCrate(player))
        {
            int[] currentPosition = player.getLocation();
            int[] newCrateLocation = new int[2];
            if (player.getDirection().equals("EAST") && currentPosition[0] < gameBoard.length-1) 
            { 
                newCrateLocation[0] = currentPosition[0] + 1;
                newCrateLocation[1] = currentPosition[1];
            }
            else if(player.getDirection().equals("WEST") && currentPosition[0] > 0)
            {
                newCrateLocation[0] = currentPosition[0] - 1;
                newCrateLocation[1] = currentPosition[1];
            }             
            else if(player.getDirection().equals("NORTH") && currentPosition[1] > 0)
            {
                newCrateLocation[0] = currentPosition[0];
                newCrateLocation[1] = currentPosition[1] - 1;
            }
            else if(player.getDirection().equals("SOUTH") && currentPosition[1] < gameBoard.length-1 )           
            {
                newCrateLocation[0] = currentPosition[0];
                newCrateLocation[1] = currentPosition[1] + 1;
            }
            this.setLocation(newCrateLocation);
            GameModel.setGameBoard(gameBoard);
        }
    }

    public boolean canPushCrate(IPlayer player)
    {
        int[] pos = player.getLocation();
        BoardTile nextTileLocation;
        if (player.getDirection().equals("EAST") && pos[0] <gameBoard.length -1) 
        { 
            int newXCoordinate = pos[0]+1;
            nextTileLocation = gameBoard[newXCoordinate][pos[1]];
            if(nextTileLocation.getType().equals("Crate")) //if this Tile contains a Crate (has type Crate)
            {
                if(newXCoordinate < gameBoard.length - 1)   //if we have not yet fallen off the board
                {
                    nextTileLocation = gameBoard[newXCoordinate+1][pos[1]];
                    if(!nextTileLocation.isOccupied()) //If the Tile in front of the crate is free
                    {
                        return true;
                    }
                }
            }
            return false;
            
        }
        else if(player.getDirection().equals("WEST")&& pos[0] >0)
        {
            int newXCoordinate = pos[0]-1;
            nextTileLocation = gameBoard[newXCoordinate][pos[1]];
            if(nextTileLocation.getType().equals("Crate")) //if this Tile contains a Crate (has type Crate)
            {
                if(newXCoordinate > 0)   //if we have not yet fallen off the board
                {
                    nextTileLocation = gameBoard[newXCoordinate-1][pos[1]];
                    if(!nextTileLocation.isOccupied()) //If the Tile in front of the crate is free
                    {
                        return true;
                    }
                }
            }
            return false;
        }             
        else if(player.getDirection().equals("NORTH")&& pos[1] >0)
        {
            int newYCoordinate = pos[1]-1;
            nextTileLocation = gameBoard[pos[0]][newYCoordinate];
            if(nextTileLocation.getType().equals("Crate")) //if this Tile contains a Crate (has type Crate)
            {
                if(newYCoordinate > 0)   //if we have not yet fallen off the board
                {
                    nextTileLocation = gameBoard[pos[0]][newYCoordinate - 1];
                    if(!nextTileLocation.isOccupied()) //If the Tile in front of the crate is free
                    {
                        return true;
                    }
                }
            }
            return false;
        }
        else if(player.getDirection().equals("SOUTH")&& pos[1]<gameBoard.length-1 )           
        {
            int newYCoordinate = pos[1]+1;
            nextTileLocation = gameBoard[pos[0]][newYCoordinate];
            if(nextTileLocation.getType().equals("Crate")) //if this Tile contains a Crate (has type Crate)
            {
                if(newYCoordinate <gameBoard.length-1 )   //if we have not yet fallen off the board
                {
                    nextTileLocation = gameBoard[pos[0]][newYCoordinate + 1];
                    if(!nextTileLocation.isOccupied()) //If the Tile in front of the crate is free
                    {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;   //if we have not encountered one of these cases, we return false.
    }
}