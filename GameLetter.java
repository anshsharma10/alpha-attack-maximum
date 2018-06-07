/*
   Name: Ansh Sharma
   Teacher: Mr. Rosen
   Date: May 25th, 2018
   Assignment: Alpha Attack - Animation of a small letter falling down, to be used in the game. The letter's character and animations are written based on file input as it must correspond with other parts of the game.

   Variable Dictionary:
   char character: The character to print on the falling letter.
   int speed: The speed of the letter.
   int reds: the number of the red amount in the colour to replace with.
   int greens: the number of the green amount in the colour to replace with.
   int blues: the number of the blues amount in the colour to replace with.
   int id: The unique number identification for each character to fall down.
   String line: The line being read from documents when searching for ids
   String stringId: Used to find the id number when searching through the gameFiles document.
   String originalText: A String used to rewrite the characterFiles.ans when a letter is destroyed
   int charsDestroyed: The number of characters the user destroyed. Used when rewriting the statistics.ans.
   int errors: The number of errors (mistakes) the user made. Used when rewriting the statistics.ans.
*/

import java.awt.*;
import hsa.Console;
import java.lang.*;
import java.io.*;

public class GameLetter extends Thread
{
    private Console c;
    int speed;
    int reds;
    int greens;
    int blues;
    int id;
    String line, originalText = "";     //Define variables to search through data
    int charsDestroyed, errors;         //Define variables to edit the statistics

    public void gameLetterFall ()
    {
	BufferedReader input;
	PrintWriter output;
	char character = ' ';

	int xPos = (int) (Math.random () * 600);                                //Generate a random x position to fall down at

	for (int i = 1 ; i < 385 ; i++) //This for loop animates the letter falling down
	{
	    try
	    {
		input = new BufferedReader (new FileReader ("gameFiles.ans"));
		input.readLine ();
		speed = Integer.parseInt (input.readLine ());                        //Read the speed from the gameFiles document
		reds = Integer.parseInt (input.readLine ());                         //Read the colours to replace with from the gameFiles document
		greens = Integer.parseInt (input.readLine ());
		blues = Integer.parseInt (input.readLine ());
		input = new BufferedReader (new FileReader ("characterFiles.ans")); //Start reading from characterFiles.java, to read available characters
		while (true)
		{
		    String stringId = "";
		    try         //Try to search for the character id in characterFiles.java
		    {
			String line = input.readLine ();                                  //Read a line from the characterFiles document
			character = line.charAt (0);
			for (int v = 1 ; v < line.length () ; v++)              //Find the id of the character
			{
			    stringId += line.charAt (v);
			}
			int readId = Integer.parseInt (stringId);              //Read the character id. If the ID is the same as the class's ID, then break the loop.
			if (readId == id)
			    break;
		    }
		    catch (Exception e)
		    {
			break;          //Exit the while loop if unable to find any id
		    }

		}
		if (input.readLine () == null)
		{
		    for (int v = 0 ; v < 6 ; v++)   //If characterFiles.java has been entirely searched, then that means this character has already been destroyed, a destroying animation will therefore play.
		    {
			c.setColor (new Color (249 - 15 * v, 225 - 15 * v, 154 - 15 * v));                       //Draw the destroyed character
			c.fillRoundRect (xPos, -40 + i, 40, 40, 5, 5);
			c.setColor (new Color (239 - 15 * v, 203 - 15 * v, 96 - 15 * v));
			c.fillRoundRect (xPos + 2, -37 + i, 35, 35, 5, 5);
			c.setColor (new Color (132 - 15 * v, 99 - 15 * v, 37));
			try
			{
			    Thread.sleep (40);
			}
			catch (Exception d)
			{
			}
		    }
		    for (int v = 0 ; v < 40 ; v++)
		    {
			if (i > 40)                                                             //Replace the letter depending on the reds, greens, and blues.
			{
			    if (reds == 0)
				c.setColor (new Color (0, (greens + 20) - (i + v) / 2, (blues + 20) - (i + v) / 2));
			    else if (greens == 0)
				c.setColor (new Color ((reds + 20) - (i + v) / 2, 0, (blues + 20) - (i + v) / 2));
			    else if (blues == 0)
				c.setColor (new Color ((reds + 20) - (i + v) / 2, (greens + 20) - (i + v) / 2, 0));
			    c.drawLine (xPos, -40 + i + v, xPos + 40, -40 + i + v);
			}
		    }
		    break;  //Break the for loop and end the program
		}

		c.setColor (new Color (249, 225, 154));                             //Make the letter fall down using animations.
		c.fillRoundRect (xPos, -40 + i, 40, 40, 5, 5);
		c.setColor (new Color (239, 203, 96));
		c.fillRoundRect (xPos + 2, -37 + i, 35, 35, 5, 5);
		c.setColor (new Color (132, 99, 37));
		c.setFont (new Font ("Serif", Font.BOLD, 40));
		c.drawString ("" + character, xPos + 10, -8 + i);
		try
		{
		    Thread.sleep (speed);
		}
		catch (Exception e)
		{
		}
		if (i > 40)                                                             //Replace the colour depending on the reds, greens, and blues.
		{
		    if (reds == 0)
			c.setColor (new Color (0, (greens + 20) - i / 2, (blues + 20) - i / 2));
		    else if (greens == 0)
			c.setColor (new Color ((reds + 20) - i / 2, 0, (blues + 20) - i / 2));
		    else if (blues == 0)
			c.setColor (new Color ((reds + 20) - i / 2, (greens + 20) - i / 2, 0));
		    c.drawLine (xPos, -40 + i, xPos + 40, -40 + i);
		}
		if (i == 384)        //Destroy the character if it reaches the bottom, remove it from the character list, and give the user a score for errors made
		{
		    input = new BufferedReader (new FileReader ("characterFiles.ans"));
		    input.readLine ();
		    while (true) //Read through characterFiles.ans for the rest of the characters
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
		    output = new PrintWriter (new FileWriter ("characterFiles.ans"));       //Create a PrintWriter to erase the deleted character from characterFiles.ans since it has reached the end
		    for (int v = 0 ; v < originalText.length () ; v++)  //Iterate through each character in originalText
		    {
			switch (originalText.charAt (v))
			{
			    case '/':                                       //If the loop finds a backslash, print a new line
				output.println ();
				break;
			    default:                                        //Otherwise, print the character that is in the text.
				output.print (originalText.charAt (v));

			}
		    }
		    output.close ();
		    originalText = "";   //Reset the originalText for further use
		    input = new BufferedReader (new FileReader ("stats.ans"));           //Create a BufferedReader to search through the stats file for Statistics
		    charsDestroyed = Integer.parseInt (input.readLine ());          //The first number is the number of characters the user destroyed. No effect since the char was destroyed on its own.
		    errors = Integer.parseInt (input.readLine ()) + 1;              //The second number is the number of errors the user has made. Adds 1 to that since the user missed this character.
		    input.close ();
		    output = new PrintWriter (new FileWriter ("stats.ans"));            //Create a PrintWriter to rewrite the stats but with an additional point in errors.
		    output.println (charsDestroyed);    //print stats
		    output.println (errors);
		    output.close ();

		    for (int v = 0 ; v < 6 ; v++)
		    {
			c.setColor (new Color (249 - 15 * v, 225 - 15 * v, 154 - 15 * v));                       //Draw the destroyed character
			c.fillRoundRect (xPos, -40 + i, 40, 40, 5, 5);
			c.setColor (new Color (239 - 15 * v, 203 - 15 * v, 96 - 15 * v));
			c.fillRoundRect (xPos + 2, -37 + i, 35, 35, 5, 5);
			c.setColor (new Color (132 - 15 * v, 99 - 15 * v, 37));
			try
			{
			    Thread.sleep (40);
			}
			catch (Exception d)
			{
			}
		    }
		    for (int v = 0 ; v < 40 ; v++)
		    {
			if (i > 40)                                                             //Replace the letter depending on the reds, greens, and blues.
			{
			    if (reds == 0)
				c.setColor (new Color (0, (greens + 20) - (i + v) / 2, (blues + 20) - (i + v) / 2));
			    else if (greens == 0)
				c.setColor (new Color ((reds + 20) - (i + v) / 2, 0, (blues + 20) - (i + v) / 2));
			    else if (blues == 0)
				c.setColor (new Color ((reds + 20) - (i + v) / 2, (greens + 20) - (i + v) / 2, 0));
			    c.drawLine (xPos, -40 + i + v, xPos + 40, -40 + i + v);
			}
		    }

		    input = new BufferedReader (new FileReader ("gameFiles.ans"));    //Encode and recreate gameFiles.ans, but add a 1 at the 6th line to designate that a new letter must be printed.
		    for (int v = 0 ; v < 5 ; v++)
		    {
			originalText += input.readLine ();
			originalText += "/";
		    }
		    originalText += "1";
		    input.close ();
		    output = new PrintWriter (new FileWriter ("gameFiles.ans"));
		    for (int v = 0 ; v < originalText.length () ; v++)  //Iterate through each character in originalText
		    {
			switch (originalText.charAt (v))
			{
			    case '/':                                       //If the loop finds a backslash, print a new line
				output.println ();
				break;
			    default:                                        //Otherwise, print the character that is in the text.
				output.print (originalText.charAt (v));

			}
		    }
		    output.close ();

		    for (int v = 0 ; v < 5 ; v++)           //Display a graphical red box to represent the error
		    {
			c.setColor (Color.red);
			c.drawRect (0 + v, 386 + v, 500 - 2 * v, 114 - 2 * v);
		    }
		    try
		    {
			Thread.sleep (100);
		    }
		    catch (Exception e)
		    {
		    }
		    for (int v = 0 ; v < 5 ; v++)
		    {
			c.setColor (new Color (42, 119, 119));
			c.drawRect (0 + v, 386 + v, 500 - 2 * v, 114 - 2 * v);
		    }
		}
	    }
	    catch (Exception e)
	    {
	    }

	}
    }


    public GameLetter (Console con, int identification)
    {
	c = con;
	id = identification;

    }


    public void run ()
    {
	gameLetterFall ();
    }
}



