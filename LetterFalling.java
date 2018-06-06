/*
   Name: Ansh Sharma
   Teacher: Mr. Rosen
   Date: May 25th, 2018
   Assignment: Alpha Attack - Animation of a letter falling down. All graphics.
*/

import java.awt.*;
import hsa.Console;
import java.lang.*;     // to access Thread class

public class LetterFalling extends Thread
{
    private Console c;

    public void letterFall ()
    {
	for (int i = 1 ; i < 350 ; i++)
	{
	    c.setColor (new Color (249, 225, 154));
	    c.fillRoundRect (275, -115 + i, 120, 120, 10, 10);
	    c.setColor (new Color (239, 203, 96));
	    c.fillRoundRect (280, -110 + i, 110, 110, 10, 10);
	    c.setColor (new Color (132, 99, 37));
	    c.setFont (new Font ("Serif", Font.BOLD, 130));
	    c.drawString ("A", 290, -10 + i);
	    try
	    {
		Thread.sleep (15);
	    }
	    catch (Exception e)
	    {
	    }

	    if (i % 20.0 == 0 && i % 40.0 != 0)
	    {
		c.setColor (Color.white);
		c.fillRect (275, 480, 120, 10);
	    }
	    if (i % 40.0 == 0 && i < 320)
	    {
		for (int v = 480 ; v < 491 ; v++)
		{
		    c.setColor (new Color (90 - v / 6, 105 - v / 5, 182 - v / 6));
		    c.drawLine (275, v, 395, v);
		}
	    }
	    if (i > 115)
	    {
		c.setColor (new Color (90 - ((i - 115) / 6), (105 - (i - 115) / 5), (182 - (i - 115) / 6)));
		c.fillRect (275, -115 + i, 120, 120);
	    }
	    if (i == 320)
	    {
		c.setColor (Color.white);
		c.setFont (new Font ("Sans Serif", Font.BOLD, 50));
		c.drawString ("A", 315, 478);
	    }
	}
	for (int i = 0 ; i < 79 ; i += 2)
	{
	    c.setColor (new Color (255, 225 - i, 0));
	    c.drawOval (389 + i / 2, 278 + i / 2, 79 - i, 79 - i);
	    try
	    {
		Thread.sleep (4);
	    }
	    catch (Exception e)
	    {
	    }
	}
	for (int i = 0 ; i < 50 ; i += 2)
	{
	    c.setColor (new Color (255, 225 - i, 0));
	    c.drawOval (259 + i / 2, 300 + i / 2, 50 - i, 50 - i);
	    try
	    {
		Thread.sleep (4);
	    }
	    catch (Exception e)
	    {
	    }
	}
	for (int i = 0 ; i < 120 ; i += 2)
	{
	    c.setColor (new Color (255, 225 - i, 0));
	    c.drawOval (280 + i / 2, 250 + i / 2, 120 - i, 120 - i);
	    try
	    {
		Thread.sleep (4);
	    }
	    catch (Exception e)
	    {
	    }
	}
	for (int i = 0 ; i < 200 ; i += 2)
	{
	    c.setColor (new Color (255, 225 - i, 0));
	    c.drawOval (180 + i / 2, 150 + i / 2, 200 - i, 200 - i);
	    try
	    {
		Thread.sleep (2);
	    }
	    catch (Exception e)
	    {
	    }
	}
	for (int i = 0 ; i < 180 ; i += 2)
	{
	    c.setColor (new Color (255, 225 - i, 0));
	    c.drawOval (300 + i / 2, 100 + i / 2, 180 - i, 180 - i);
	    try
	    {
		Thread.sleep (2);
	    }
	    catch (Exception e)
	    {
	    }
	}
	for (int i = 0 ; i < 600 ; i += 2)
	{
	    c.setColor (new Color (255, 225 - i / 3, 0));
	    c.drawOval (0 + i / 2, -50 + i / 2, 600 - i, 600 - i);
	    try
	    {
		Thread.sleep (2);
	    }
	    catch (Exception e)
	    {
	    }
	}

    }


    public LetterFalling (Console con)
    {
	c = con;
    }


    public void run ()
    {
	letterFall ();
    }
}



