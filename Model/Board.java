package Model;

/**
 * This class is used to model the game board.
 * @author Yacine Belaifa
 */
public class Board 
{
    /*------------------------Instance Variables----------------------- */
    private BoardTile[][] grid; //A 2D array of BoardTiles to represent the grid itself.

    /*-----------------------Constructor------------------------------- */
    public Board()
    {
        
        grid = new BoardTile[8][8] ;        // Initialize the grid of Tiles
        for(int i = 0;i<grid.length; i++)
        {
            for(int j = 0 ; j< grid[i].length ; j++)
            {
                int[] pos = {i,j};          //Declaring the position that the particular BoardTile occupies.
                grid[i][j] = new BoardTile(pos,"None");   //Constructing a BoardTile at every entry in the 2D array
            }
        }
    }

    /*--------------------------Accessor Methods----------------------------------- */
    /** 
     * Method to retrieve the grid of BoardTile objects
     * @return BoardTile[][] - the grid of BoardTile objects
    */
    public BoardTile[][] getGrid()
    {
        return this.grid;
    }
}
