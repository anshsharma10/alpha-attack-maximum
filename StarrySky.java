/*
   Name: Ansh Sharma
   Teacher: Mr. Rosen
   Date: May 25th, 2018
   Assignment: Alpha Attack - Animation of a sky with stars. All graphics.
*/

import java.awt.*;
import hsa.Console;
import java.lang.*;

public class StarrySky extends Thread
{
    private Console c;

    public void skyRun ()
    {
	for (int i = 0 ; i < 501 ; i++)
	{
	    c.setColor (new Color (90 - i / 6, 105 - i / 5, 182 - i / 6));
	    c.drawLine (0, i, 640, 1 + i);
	}
	c.setColor (Color.lightGray);
	c.fillStar (133, 47, 20, 20);
	c.fillStar (52, 213, 20, 20);
	c.fillStar (41, 428, 20, 20);
	c.fillStar (190, 324, 20, 20);
	c.fillStar (458, 299, 20, 20);
	c.fillStar (561, 447, 20, 20);
	c.fillStar (599, 155, 20, 20);
	c.fillStar (483, 23, 20, 20);
	for (int i = 0 ; i < 2 ; i++)
	{
	    c.setColor (Color.white);
	    c.fillStar (133, 47, 20, 20);
	    try
	    {
		Thread.sleep (328);
	    }
	    catch (Exception e)
	    {
	    }
	    c.setColor (Color.lightGray);
	    c.fillStar (133, 47, 20, 20);
	    c.setColor (Color.white);
	    c.fillStar (52, 213, 20, 20);
	    try
	    {
		Thread.sleep (328);
	    }
	    catch (Exception e)
	    {
	    }
	    c.setColor (Color.lightGray);
	    c.fillStar (52, 213, 20, 20);
	    c.setColor (Color.white);
	    c.fillStar (41, 428, 20, 20);
	    try
	    {
		Thread.sleep (328);
	    }
	    catch (Exception e)
	    {
	    }
	    c.setColor (Color.lightGray);
	    c.fillStar (41, 428, 20, 20);
	    c.setColor (Color.white);
	    c.fillStar (190, 324, 20, 20);
	    try
	    {
		Thread.sleep (328);
	    }
	    catch (Exception e)
	    {
	    }
	    c.setColor (Color.lightGray);
	    c.fillStar (190, 324, 20, 20);
	    c.setColor (Color.white);
	    c.fillStar (458, 299, 20, 20);
	    try
	    {
		Thread.sleep (328);
	    }
	    catch (Exception e)
	    {
	    }
	    c.setColor (Color.lightGray);
	    c.fillStar (458, 299, 20, 20);
	    c.setColor (Color.white);
	    c.fillStar (561, 447, 20, 20);
	    try
	    {
		Thread.sleep (328);
	    }
	    catch (Exception e)
	    {
	    }
	    c.setColor (Color.lightGray);
	    c.fillStar (561, 447, 20, 20);
	    c.setColor (Color.white);
	    c.fillStar (599, 155, 20, 20);
	    try
	    {
		Thread.sleep (328);
	    }
	    catch (Exception e)
	    {
	    }
	    c.setColor (Color.lightGray);
	    c.fillStar (599, 155, 20, 20);
	    c.setColor (Color.white);
	    c.fillStar (483, 23, 20, 20);
	    try
	    {
		Thread.sleep (328);
	    }
	    catch (Exception e)
	    {
	    }
	    c.setColor (Color.lightGray);
	    c.fillStar (483, 23, 20, 20);
	}
	
    }


    public StarrySky (Console con)
    {
	c = con;
    }


    public void run ()
    {
	skyRun ();
    }
}



