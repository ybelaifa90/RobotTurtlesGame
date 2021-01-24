package Controller;

import Model.*;
import View.*;
import Core.IPlayer;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashSet;

/**
 * This class takes care of communication between the GameModel and the View (both the CommandLineView and the GUIView)
 * @author Yacine Belaifa
 */


public class GameController
{
    /*---------------------------Instance Variables -------------------------------------*/
    private GameModel model; 
    private CommandLineView CLI;
    private GUIView GUI;
    private Scanner in;
    private int numPlayers;
    /*---------------------------------Constructor--------------------------------------*/
    public GameController()
    {
        CLI = new CommandLineView();
        model = new GameModel();
        numPlayers = 0;
    }

    /*---------------------------------Methods-----------------------------------------*/
    /**
     * Method to close the GUI, detect a change in components to displayed, and reopen it shortly after,
     * displaying the updated state of the program.
     */
    public void updateGUIView() 
    {
        GUI.closeFrame();
        GUI.validate();
        GUI.repaint();
        GUI.showExistingBoard();

    }
   
    /**
     * This method declares a Scanner object, reads in a String of user input, and stores the inputted value. Used in \
     * several places, but especially to retrieve the turn a particular Player chose to make.
     * @return String - the String entered by the user.
     */
    public String getUserInput() 
    {
        in = new Scanner(System.in);  
        String input = in.nextLine();
        return input;
    }

    /**
     * This method verifies the turn the user chose, and if it is invalid, the user is prompted to enter a valid option
     * @param move - The move chosen by the current player (move forward, turn left, etc..).
     */
    public String checkTurnFormat(String move)
    {
        //Logical expression to identify which moves are valid 
        //If the expression is true, the user has entered an illegal move.
        boolean wrong_move = !(move.equals("forward")|| move.equals("left turn")||move.equals("right turn"));  
        while(wrong_move) // We loop until a valid move is entered 
        {
            System.out.println("enter valid move");
            move = getUserInput();
            if (move.equals("forward"))
            {
                System.out.println("Entered forward move. This is now valid. ");
                move = "forward";
                wrong_move = false;
            }
            else if(move.equals("left_turn"))
            {
                System.out.println("Entered left turn. This is now valid.");
                move = "left turn";
                wrong_move = false;
            }
            else if(move.equals("right_turn"))
            {
                System.out.println("Entered right turn. This is now valid.");
                move = "right turn";
                wrong_move = false;
            }
        } return move;
    }
   
    public void setUpObjects(int level)
    {
        if(level == 1)
        {
            this.manageTurnFlow();
        }
        else if(level == 2)
        {
            model.setUpIceWalls();
            this.manageTurnFlow();
        }
        else if(level == 3)
        {
            //now has laser cards
            model.setUpIceWalls();
            this.manageTurnFlow();
        }
        else if(level == 4)
        {
            //now has laser cards
            model.setUpIceWalls();
            model.setUpStoneWalls();
            this.manageTurnFlow();
        }
        else if(level == 5)
        {
            //now has laser cards
            model.setUpIceWalls();
            model.setUpStoneWalls();
            model.setUpCrates();
            this.manageTurnFlow();   
        }
        else if(level == 6)
        {
            //now has laser cards
            model.setUpIceWalls();
            model.setUpStoneWalls();
            model.setUpCrates();
            this.manageTurnFlow();
            //play 3 functionality
        }
        else if(level == 7)
        {
            //now has laser cards
            model.setUpIceWalls();
            model.setUpStoneWalls();
            model.setUpCrates();
            this.manageTurnFlow();
            //write program functionality
        }
        else if(level == 8)
        {
            //now has laser cards
            model.setUpIceWalls();
            model.setUpStoneWalls();
            model.setUpCrates();
            this.manageTurnFlow();
            // function frog
        }

    }

    /**
     * This method is essentially the "main loop" of the entire program - it processes all the mechanics needed
     * for the players of the game to alternate taking turns so long as there is at least one player still
     * playing the game.
     */
    public void manageTurnFlow()
    {
        ArrayList<IPlayer> players = model.getPlayerHandler().getPlayers();
        while (players.size() >= 1)
        {
            for (int i =0; i < players.size();i++)          //Iterate through the list of players
            {
                IPlayer p = players.get(i);                 //Retrieve the current player
                if(p.getDeck().isEmpty()==false)            //If the player's deck has cards remaining 
                {
                    CLI.printTurnStatement(p.getColor());
                    String move = this.getUserInput();      //The player selects a move
                    move = this.checkTurnFormat(move);             //Verifying that the move they made is valid.
                    int status = model.takePlayerTurn(p,move);  //Determining if the move the player made is sucessful or not
                    this.updateGUIView();                   //Updating the GUI to reflect the program's change in state.
                    while (status ==0)                       //If the move was unsuccesful, loop until we have a successful move.
                    {
                        CLI.invalidInputMessage();
                        CLI.printTurnStatement(p.getColor());
                        move = this.getUserInput();   
                        this.checkTurnFormat(move);
                        status = model.takePlayerTurn(p,move);
                        this.updateGUIView();
                    }
                    if (model.playerHasWon())               //If the final player has won the game
                    {
                        updateWinMessage(p.getColor());
                        return; // PROBLEM: still calls Bug Card if we remove return
                                    //If we keep return, it stops after 1 player wins

                    }
                    //Retrieves "yes" or "no" - if the current player wants to use the Bug card.
                    String bugStatus = this.bugInformation();   
                    model.bugProcessing(bugStatus, p);
                    this.updateGUIView();
                    if (bugStatus.equals("yes"))
                    {
                        i = i -1;   //do-over of previous loop
                    }
                    else
                    {
                        this.CLI.showEndOfTurnMessage(p.getColor());    //printing out to the user that their turn is over.
                    }
                }
                else
                {
                    System.out.println("Sorry, you have no more cards remaining :(");
                    players.remove(p);  //If a player has no cards remaining, they will be removed from the ArrayList of players.
                }
            }
        }
    }

    /**
     * This method allows the user to enter if they would like to use the Bug, and 
     * stores the resulting user input ("yes" or "no"), along with verifying that a valid
     * String was entered.
     * @return String - the String entered by the user indicating whether they would like to use the Bug or not.
     */
    public String bugInformation()  
    {
        this.CLI.getBugMessage();
        String bugStatus = getUserInput();
        //Logical expression to determine if the user entered a valid option for using the Bug. If they did, this returns true.
        boolean validBug = bugStatus.equals("yes") || bugStatus.equals("no");   
        //Continuously prompting the user to enter either "yes" or "no".
        while(validBug == false) 
        {
            CLI.invalidInputMessage();
            bugStatus = getUserInput();
            validBug = bugStatus.equals("yes") || bugStatus.equals("no");

        }
        return bugStatus;
    }

    /**
     * This method calls methods in the CommandLineView showing that a given Player has won the game,
     * but this method is not called if we are at the end of the game.
     * @param color - the String identifying the Color of the player we want to show a message for.
     */    
    public void updateWinMessage(String color)
    {
        this.CLI.showCurrentPlayerHasWon(color);
        if(model.getPlayerHandler().getPlayers().size() == 1)
        {
            this.CLI.showEndOfGameMessage();
        }
    }

    /**
     * This method takes care of setting up the game: performing duties such as showing the rules, entering
     * a number of players, and allowing the Players to choose their Colors and the colors of their Jewels. 
     * It also calls on the Model and View classes to set up the Jewel logic and to show the Jewels.
     */
    public void launchGame()
    {

        this.CLI = new CommandLineView(); // initialize the CommandLineView 
        System.out.println("Welcome to RobotTurtles");
        System.out.println("Would you like to review game rules ? yes/no");
        Scanner in = new Scanner(System.in);
        String r = in.nextLine();
        if(r.equals("yes"))
        {
            CLI.gameRules();   
        }
           
        System.out.println("Enter number of players:");
        in = new Scanner(System.in);    
        this.numPlayers = in.nextInt();
        System.out.println("Players: " + numPlayers);
        while(numPlayers< 1 ||  numPlayers > 4 )    //Prompting for a valid number of players (between 1 and 4)
        {
            System.out.println("ERROR: Entered invalid number of players. Please re-enter the number of players (between 1 and 4).");
            this.numPlayers = in.nextInt();
        }
        
        HashSet<String> colors = this.colorFormat(); 
        model.getPlayerHandler().setUpPlayersList(colors);
        System.out.println("Setting up jewels");
        HashSet<String> jewels = this.colorFormat(); 
        model.getJewelHandler().setUpJewelsList(jewels);
        
        this.CLI.showLevels();
        int level = in.nextInt();   //user chooses level
        this.setUpGUI();
        this.setUpObjects(level);
    }

    /**
     * Method to construct an instance of the GUIView class, and render the board visible.
     */
    public void setUpGUI()
    {
        this.GUI = new GUIView(this);
        this.GUI.showBoard();
    }
    
    /**
     * This method determines which colors are being used by the Players or Jewels that are going to be displayed.
     * @return - a HashSet of all the colors actively being used by the Players or Jewels (depending on where
     *  the method is being called). These colors are represented by Strings.
     */
    public HashSet<String> colorFormat()
    {
        ArrayList<String> colorsList = new ArrayList<String>();
        //initially adding in all the possible colors to an ArrayList
        colorsList.add("green");
        colorsList.add("red");
        colorsList.add("magenta");
        colorsList.add("blue");
        HashSet<String> chosenColors = new HashSet<String>();//generating a set to store the colors that will be used
        for (int i =0; i <numPlayers ;i++ )
        {
            CLI.showColorMessage(colorsList);
            String color = getUserInput();                  //User inputs a color
            if (colorsList.contains(color)== false)
            {
                while (colorsList.contains(color)==false)   //prompting f the user enters an invalid color
                {
                    CLI.invalidInputMessage();
                    CLI.showColorMessage(colorsList);
                    color = getUserInput();
                }
            }
            colorsList.remove(color);   //removing the chosen color from the ArrayList
            chosenColors.add(color);    //adding the color to the HashSet
        }
        return chosenColors;
    }


}
