package View;
// Controller.*;


import java.util.ArrayList;


public class CommandLineView 
{
    //private GameController controller;  
    
    public CommandLineView()// constructor 
    {
        //this.controller = gController;
       
    }
    
    public void showColorMessage(ArrayList<String> colors)
    {
        System.out.println("Select a color that has not yet been chosen: :" + colors.toString());
    }
    public void showEndOfTurnMessage(String color)
    {
        System.out.println("You have reached the end of your turn:" + color + " turtle\n");
    }
    public void gameRules()
    {
        System.out.println("Welcome to RobotTurtles, the game designed for kids to learn programming but also to have fun!");
        System.out.println("Players each control a colored circle (representing a Turtle) and take turns either turning or moving the Turtle0\n.");
        System.out.println("Players try to move towards capturing one of the jewels on the board.");
        System.out.println("Once they capture their jewel, they are done playing! ");
        System.out.println("The game ends when all players capture their jewels! \n");
        System.out.println("The youngest player starts playing first, and then play continues clockwise.");
        System.out.println("This game has up to 4 players, and each player starts by picking a color. \n");
    }
    public void printTurnStatement(String color)
    {
        System.out.println(">>>Ready for your turn, " + color + " turtle?\n");
        System.out.println("Please enter the move you would like to make: (forward, left turn, right turn) ");
    }
    
    public void invalidInputMessage()
    {
        System.out.println("The input you entered is invalid. Please make a new selection.\n");
    }
     public void getBugMessage()
    {
        System.out.println("Would you like to use the Bug card to undo your move: (yes, no)?");
    }
    public void showCurrentPlayerHasWon(String color)
    {
        System.out.println("The " + color + " turtle has captured their jewel!");
        System.out.println("The game is finished for the current player !!, YOU WON !\n");
    }
    public void showEndOfGameMessage()
    {
       System.out.println("RobotTurtles is finished! All players have collected their jewels!\n");
       System.out.println("Thanks for playing the basic game of Robot Turtles! Stay tuned for the release of updated versions\n"); 
    }
    public void showLevels()
    {
        System.out.println("Please enter the level you would like to play: ");
        System.out.println("Level 1: Basic game");
        System.out.println("Level 2: Now featuring IceWalls");
        System.out.println("Level 3: Now featuring IceWalls and Laser Cards");
        System.out.println("Level 4: Now featuring IceWalls, Laser Cards and Stone Walls");
        System.out.println("Level 5: Now featuring IceWalls, Laser Cards, Stone Walls, Crates");
        System.out.println("Level 6: Now featuring IceWalls, Laser Cards, Stone Walls, Crates and Play 3 move functionality!");
        System.out.println("Level 7: Now featuring IceWalls, Laser Cards, Stone Walls, Crates, and the ability to write your own program!");
        System.out.println("Level 8: Now featuring IceWalls, Laser Cards, Stone Walls, Crates, and the ability to use the function frog!");
    }
}