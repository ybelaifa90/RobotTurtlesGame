package Deck;

import java.util.ArrayList;

import Core.IPlayer;
import Model.MoveFunctions;

public class FunctionFrogCard
{
    //Can choose laser cards, forward, left, right
    //Can ask the user what they want - 
    //1. Main program list - includes frogs
    //2. List of moves for Function Frog to do - does not contain frogs

    private ArrayList<String> mainProgram;  // String of moves and function frogs
    private ArrayList<String> function;     // String of moves for the function frog to execure

    public FunctionFrogCard()
    {
        this.mainProgram = new ArrayList<String>();
        this.function = new ArrayList<String>();
    }

    public ArrayList<String> getMainProgram()
    {
        return this.mainProgram;
    }

    public ArrayList<String> getFunction()
    {
        return this.function;
    }

    public void setMainProgram(ArrayList<String> program)
    {
        this.mainProgram = program;
    }

    public void setFunction(ArrayList<String> frogFunction)
    {
        this.function = frogFunction;
    }

    public void doFrogFunction(IPlayer currPlayer)
    {
        for (int i = 0; i < mainProgram.size(); i++)
        {
            if (mainProgram.get(i).equals("frog"))
            {
                for (int j = 0; j < function.size(); j++)   //go into function and loop through every move inside the function
                {
                    if(function.get(j).equals("forward"))
                    {
                        MoveFunctions.forward(currPlayer);
                    }
                    else if (function.get(j).equals("left turn"))
                    {
                        MoveFunctions.turn_left(currPlayer);
                    }
                    else if (function.get(j).equals("right turn"))
                    {
                        MoveFunctions.turn_right(currPlayer);
                    }
                    else //laser card
                    {
                        LaserCard.meltWall();
                    }
                }
            }
            else
            {
                //Make this concise - helper function?
                if(function.get(i).equals("forward"))
                {
                    MoveFunctions.forward(currPlayer);
                }
                else if (function.get(i).equals("left turn"))
                {
                    MoveFunctions.turn_left(currPlayer);
                }
                else if (function.get(i).equals("right turn"))
                {
                    MoveFunctions.turn_right(currPlayer);
                }
                else //laser card
                {
                    LaserCard.meltWall();
                }
            }
        }
        
    }

    public boolean functionIsValid()
    {
        if (function.contains("frog"))
        {
            return false;
        }
        return true;
    }

    public boolean programIsValid()
    {
        if (mainProgram.contains("frog"))
        {
            return true;
        }
        return false;
    }
    //Enter your main program: frog turn_right frog forward
    //Enter your main program:

    //In main class, before we run through a program/function, we want to make sure the function and program are valid
    //For program to be valid, must contain 1+ frog 
    //For function to be valid, cannot contain any frogs 
}
