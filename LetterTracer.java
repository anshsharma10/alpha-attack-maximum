/*
   Name: Ansh Sharma
   Teacher: Mr. Rosen
   Date: May 25th, 2018
   Assignment: Alpha Attack - Tracks the letter that is currently being typed and marks if it is right or not using characterFiles.java.

   Variable Dictionary:
   char charInput - The typed character by the player
   BufferedReader input - The reader used to scan characterFiles.ans to find the character the game requires to be typed
   String ori
*/

import java.awt.*;
import hsa.Console;
import java.lang.*;
import java.io.*;

public class LetterTracer extends Thread
{
    private Console c;
    int charsDestroyed;
    int errors;

    public void letterTracerTrack ()
    {
	char charInput;
	BufferedReader input;
	String originalText = "";
	String line = "";
	PrintWriter output;
	while (true)
	{
	    charInput = c.getChar ();
	    try
	    {
		input = new BufferedReader (new FileReader ("characterFiles.ans"));           //Create a BufferedReader to search through the read file
		line = input.readLine ();                                //Read the first string in the file
		if (line.charAt (0) == charInput)       //An if statement for if the user typed the correct character. Updates statistics and characters that are left.
		{
		    while (true) //If the user typed the right character, then recreate the whole characterFiles.ans, but remove the character ID they just typed.
			try
			{
			    line = input.readLine ();
			    if (line == (null))
				break;
			    originalText += line;
			    originalText += "/";        //Add a backslash so we can seperate each line
			}
		    catch (Exception e)
		    {
		    }
		    input.close ();     //Close and save input
		    output = new PrintWriter (new FileWriter ("characterFiles.ans"));       //Create a PrintWriter to erase the written character from characterFiles.ans if the character is correct
		    for (int i = 0 ; i < originalText.length () ; i++)  //Iterate through each character in originalText
		    {
			switch (originalText.charAt (i))
			{
			    case '/':                                       //If the loop finds a backslash, print a new line
				output.println ();
				break;
			    default:                                        //Otherwise, print the character that is in the text.
				output.print (originalText.charAt (i));

			}
		    }
		    output.close ();
		    originalText = "";   //Reset the originalText for further use
		    input = new BufferedReader (new FileReader ("stats.ans"));           //Create a BufferedReader to search through the stats file for Statistics
		    charsDestroyed = Integer.parseInt (input.readLine ()) + 1;          //The first number is the number of characters destroyed. Adds 1 to that since the user typed the right character.
		    errors = Integer.parseInt (input.readLine ());                      //The second number is the number of errors the user has made. Since the user typed right, that doesn't affect anything, so we copy and paste it.
		    input.close ();
		    output = new PrintWriter (new FileWriter ("stats.ans"));            //Create a PrintWriter to rewrite the stats but with an additional point in chars destroyed..
		    output.println (charsDestroyed);    //print stats
		    output.println (errors);
		    output.close ();

		}
		else            //If the user typed the wrong character, then add an error to the error count.
		{
		    originalText = "";   //Reset the originalText for further use
		    input = new BufferedReader (new FileReader ("stats.ans"));           //Create a BufferedReader to search through the stats file for Statistics
		    charsDestroyed = Integer.parseInt (input.readLine ());          //The first number is the number of characters destroyed. Since the user typed the wrong character, that doesn't affect anything, so we copy and paste it.
		    errors = Integer.parseInt (input.readLine ()) + 1;              //The second number is the number of errors the user has made. Adds 1 to that since the user typed the wrong character.
		    input.close ();
		    output = new PrintWriter (new FileWriter ("stats.ans"));            //Create a PrintWriter to rewrite the stats but with an additional point in errors.
		    output.println (charsDestroyed);    //print stats
		    output.println (errors);
		    output.close ();
		}

	    }
	    catch (Exception e)
	    {
	    }
	}

    }


    public LetterTracer (Console con)
    {
	c = con;

    }


    public void run ()
    {
	letterTracerTrack ();
    }
}



