/*
   Name: Ansh Sharma
   Teacher: Mr. Rosen
   Date: May 25th, 2018
   Assignment: Alpha Attack - Animation of a small letter falling down, to be used in the game. All graphics.

   Variable Dictionary:
   char character: The character to print on the falling letter.
   int speed: The speed of the letter.
   int reds: the number of the red amount in the colour to replace with.
   int greens: the number of the green amount in the colour to replace with.
   int blues: the number of the blues amount in the colour to replace with.
   int id: The unique number identification for each character to fall down.
   String line: The line being read from the gameFiles document when searching for ids
   String stringId: Used to find the id number when searching through the gameFiles document.
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

    public void gameLetterFall ()
    {
	BufferedReader input;
	char character = ' ';

	int xPos = (int) (Math.random () * 600);                                //Generate a random position
	for (int i = 1 ; i < 385 ; i++)
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
		    String line = input.readLine ();                                  //Read a line from the characterFiles document
		    character = line.charAt (0);
		    for (int v = 1 ; v < line.length () ; v++)
		    {
			stringId += line.charAt (v);
		    }
		    int readId = Integer.parseInt (stringId);              //Read the character id. If the ID is the same as the class's ID, then break the loop.
		    if (readId == id)
			break;
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



