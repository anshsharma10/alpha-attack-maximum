// Name: Ansh Sharma
// Teacher: Mr. Rosen
// Date: May 25th, 2018
// Assignment: Create Alpha Attack: A typing game. Designed to count scores and errors as the user types. Customizable difficulty, errortrapping as well as menu selection are included.

/* /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Variable Dictionary

Name               Data Type                Description
menuChoice         static char              The user's selected choice when moving through menu screens. Found from menuChoice, and both are used in conjunction. Errortrapped.
destroyedChars     int                      Number of destroyed character boxes. Used in final score calcuation.
errors             int                      Number of errors the user has made when typing
score              int                      Total user's score. Found from: (# of errors / destroyedChars)*(destroyedChars)*(WPM), converted to int.
time               int                      Elapsed seconds, used to calculate WPM.
debugMode          boolean                  Represents whether or not the user has entered debug mode.

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Method Dictionary

Name               Data Return    Access    Description                                                                 Parameters
letterFall()       void           public    Calls the LetterFalling thread/class to start. A letter that falls down
					    and explodes.
skyRun()           void           public    Calls the StarrySky thread/class to start. Creates a sky with blinking
					    stars.
gameLetter(id)     void           public    Calls the gameLetter thread/class to start. Creates a falling letter        int id
					    to be used in the game.
pauseProgram()     void           public    Pause the program until the user inputs a key.
splashScreen()     void           public    Creates an animated splash screen
mainMenu()         void           public    Draw the main menu, get user choice.
instructions()     void           public    Draw instructions, animated and scrolling
goodbye()          void           public    Exit the program with a graphic display.
highScores()       void           public    Draw the high scores, and tell the user if no high scores exist.
clearScores()      void           public    Clear the high scores after prompt from the user.
askData()          int            private   Display options to the user, and return difficulty level.
game(difficulty)   void           public    Run the game until the user exits or the game ends.                         int difficulty
gameOver(          void           public    Display the game over screen and scores, prompt the user to exit.           int destroyedChars, int errors
destroyedChars,
errors)
moveLetter(letter) void           public    A method calling a class that will animate a letter down the screen.        char letter

*/ ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

import java.awt.*;
import hsa.Console;
import java.io.*;
import hsa.Message;
import java.util.*;

public class AlphaAttackMAXIMUM
{
    static Console c;
    static char menuChoice = 1;
    static int destroyedChars;
    static int errors;
    long time;
    int score;
    boolean debugMode = true;
    /*
	letterFall() : Calls on the class letterFall to create a falling letter and explosion animation. Uses the join method to have the main method wait until execution is done.
	Global variables used: Console c
	Local variables used: None
    */
    public void letterFall ()
    {
	LetterFalling l = new LetterFalling (c);
	l.start ();
	try
	{
	    l.join ();
	}
	catch (Exception e)
	{
	}
    }


    /*
	 gameLetter(int id) : Calls on the class gameLetter to animate a small falling letter for the game.
	 Global variables used: Console c
	 Local variables used: char chara : The character to display on the letter
			       int spd : The speed of the character, dependent on difficulty.
			       int red, green, blue: The relative red green and blue values for the colour of the background to replace in the animation.
    */
    public void gameLetter (int id)
    {
	GameLetter g = new GameLetter (c, id);
	g.start ();
    }


    /*
     letterTracer() : Calls on the class letterTracer() to track the letter the player is currently typing and mark whether it is right or not.
     Global variables used: Console c
     Local variables used: none
    */
    public void letterTracer ()
    {
	LetterTracer l = new LetterTracer (c);
	l.start ();
    }



    /*
	skyRun() : Calls on the class StarrySky to create an animated sky with twinkling stars.
	Global variables used: Console c
	Local variables used: None
    */
    public void skyRun ()
    {
	StarrySky s = new StarrySky (c);
	s.start ();
    }


    /*
	pauseProgram() : Will pause the program until the user inputs any key.
	Global variables used: None
	Local variables used: None
    */
    public void pauseProgram ()
    {
	c.getChar ();
    }


    /*
	splashScreen() : Calls on skyRun() and letterFall() to create an animated splash screen. Transitions automatically to the main menu.
	Global variables used: None
	Local variables used: None
    */
    public void splashScreen ()
    {
	skyRun ();
	letterFall ();
    }


    /*
	mainMenu() : Creates an animated opening for the main menu, and allows the user to choose where they want to go by using WASD and enter.
	Global variables used: menuChoice, Console c
	Local variables used: char userInput, The key the user inputs when moving through menu screens. Errortrapped and used in conjunction with menuChoice.
    */
    public void mainMenu ()
    {
	char userInput;                                         //Declare local variable
	for (int i = 0 ; i < 25 ; i += 2)                       //Animate the opening
	{
	    for (int v = 0 ; v < 32 ; v += 2)
	    {
		c.setColor (Color.black);
		c.fillRect (20 * v, 0 + i * 20, 20, 40);
		try
		{
		    Thread.sleep (2);
		}
		catch (Exception e)
		{
		}
	    }
	}
	for (int i = 0 ; i < 25 ; i += 2)
	{
	    for (int v = 0 ; v < 32 ; v += 2)
	    {
		c.setColor (Color.black);
		c.fillRect (20 + 20 * v, 0 + i * 20, 20, 40);
		try
		{
		    Thread.sleep (2);
		}
		catch (Exception e)
		{
		}
	    }
	}
	c.setColor (new Color (92, 84, 173));
	c.fillRect (0, 320, 640, 180);
	for (int i = 0 ; i < 10 ; i++)
	{
	    for (int v = 0 ; v < 320 ; v++)
	    {
		c.setColor (new Color ((153 - v / 4) + i * 10, 48 + i * 10, 140));
		c.drawLine (0, v, 640, v + 1);
	    }
	    try
	    {
		Thread.sleep (150);
	    }
	    catch (Exception e)
	    {
	    }
	}
	try
	{
	    Thread.sleep (300);
	}
	catch (Exception e)
	{
	}
	c.setColor (Color.white);
	c.setFont (new Font ("Arial", Font.BOLD, 80));
	c.drawString ("ALPHA", 20, 80);
	try
	{
	    Thread.sleep (300);
	}
	catch (Exception e)
	{
	}
	c.setColor (Color.white);
	c.setFont (new Font ("Arial", Font.BOLD, 80));
	c.drawString ("ATTACK", 20, 160);
	try
	{
	    Thread.sleep (300);
	}
	catch (Exception e)
	{
	}
	for (int i = 0 ; i < 15 ; i++)
	{

	    c.setColor (new Color (255, 17 * i, 0));
	    c.setFont (new Font ("Arial", Font.BOLD, 120 - i));
	    c.drawString ("MAXIMUM", 20 + i * 14 / 5, 280);
	    try
	    {
		Thread.sleep (50);
	    }
	    catch (Exception e)
	    {
	    }
	}
	c.setFont (new Font ("Lucida Console", 0, 35));         //Display menu options
	c.drawString ("1-START", 25, 380);
	c.drawString ("2-HELP", 25, 450);
	c.drawString ("3-HI SCORES", 340, 380);
	c.drawString ("4-EXIT", 340, 450);
	menuChoice = 1;
	for (int i = 0 ; i < 5 ; i++)                           //Start out the user's highlighted selection at 1, or start
	{
	    c.drawRect (15 + i, 340 + i, 270 - 2 * i, 60 - 2 * i);
	}
	menuChoice = '1';
	while (true)
	{
	    userInput = c.getChar ();                           //Get user input
	    if (userInput == 'w' || userInput == 's')
	    {
		if (userInput == 's' && menuChoice < '4')
		{
		    menuChoice = (char) ((int) menuChoice + 1); //If input is "s", or down, move the menuChoice up by 1 to the next option
		}
		else if (userInput == 'w' && menuChoice > '1')
		{
		    menuChoice = (char) ((int) menuChoice - 1); //If input is "w", or up, move the menuChoice down by 1 to the next option
		}
		for (int i = 0 ; i < 5 ; i++)                   //Regardless of input, up or down, draw over each highlighted selection
		{
		    c.setColor (new Color (92, 84, 173));
		    c.drawRect (15 + i, 340 + i, 270 - 2 * i, 60 - 2 * i);
		    c.drawRect (15 + i, 410 + i, 270 - 2 * i, 60 - 2 * i);
		    c.drawRect (330 + i, 340 + i, 270 - 2 * i, 60 - 2 * i);
		    c.drawRect (330 + i, 410 + i, 270 - 2 * i, 60 - 2 * i);
		}
		c.setColor (new Color (255, 255, 0));
		switch (menuChoice)                             //Use switch to highlight the user's menu input
		{
		    case '1':
			for (int i = 0 ; i < 5 ; i++)
			{
			    c.drawRect (15 + i, 340 + i, 270 - 2 * i, 60 - 2 * i);
			}
			break;
		    case '2':
			for (int i = 0 ; i < 5 ; i++)
			{
			    c.drawRect (15 + i, 410 + i, 270 - 2 * i, 60 - 2 * i);
			}
			break;
		    case '3':
			for (int i = 0 ; i < 5 ; i++)
			{
			    c.drawRect (330 + i, 340 + i, 270 - 2 * i, 60 - 2 * i);
			}
			break;
		    case '4':
			for (int i = 0 ; i < 5 ; i++)
			{
			    c.drawRect (330 + i, 410 + i, 270 - 2 * i, 60 - 2 * i);
			}
			break;
		}
	    }
	    else if ((int) userInput == 10)                     //If the user presses enter, exit the main menu and keep the menuChoice for later use.
	    {
		break;
	    }
	}
    }


    /*
	instructions() : Displays the instructions in an animated format, then uses pauseProgram to go back to the main menu.
	Global variables used: none
	Local variables used: none
    */
    public void instructions ()
    {
	c.setColor (Color.black);
	c.fillRect (0, 0, 640, 500);
	for (int i = 0 ; i < 500 ; i++)
	{
	    c.setColor (Color.lightGray);
	    c.setFont (new Font ("Arial", Font.BOLD, 120 - i));
	    c.drawString ("Instructions", 20 + i * 14 / 5, 280);
	}
    }


    /*
	goodbye() : Exits the program after using pauseProgram, with a goodbye message.
	Global variables used: none
	Local variables used: none
    */
    public void goodbye ()
    {
    }


    /*
	highScores() : Displays the top 10 high scores using file input stored in a seperate document.
	Global variables used: none
	Local variables used: none
    */
    public void highScores ()
    {
    }


    /*
	clearScores() : Asks the user if they are sure they want to clear scores, then clears the scores.
	Global variables used: none
	Local variables used: none
    */
    public void clearScores ()
    {
    }


    /*
	askData() : Displays difficulty options and asks the user what their difficulty level is. The user can select various levels using a highlighting box over the options.
	Global variables used: menuChoice, Console c
	Local variables used: char userInput, The key the user inputs when moving through menu screens. Errortrapped and used in conjunction with menuChoice.
    */
    private int askData ()
    {
	char userInput;                                         //Declare local variable userInput
	c.setColor (new Color (60, 39, 42));            //Create graphics for buttons
	c.fillRect (0, 0, 640, 125);
	c.setColor (new Color (123, 49, 61));
	c.fillRect (0, 125, 640, 500);
	c.setColor (new Color (231, 199, 240));
	c.fillRect (43, 160, 206, 77);
	c.setColor (new Color (242, 223, 255));
	c.fillRect (45, 163, 202, 71);
	c.setColor (new Color (204, 127, 255));
	c.fillRect (43, 266, 206, 77);
	c.setColor (new Color (212, 147, 255));
	c.fillRect (45, 269, 202, 71);
	c.setColor (new Color (125, 23, 193));
	c.fillRect (43, 375, 206, 77);
	c.setColor (new Color (171, 45, 255));
	c.fillRect (45, 378, 202, 71);
	c.setColor (new Color (218, 69, 69));
	c.fillRect (372, 160, 206, 77);
	c.setColor (new Color (255, 45, 45));
	c.fillRect (374, 163, 202, 71);
	c.setFont (new Font ("Lucida Console", 0, 60));
	c.setColor (new Color (212, 147, 255));
	c.drawString ("EASY", 70, 218);
	c.setColor (new Color (171, 45, 255));
	c.drawString ("MED", 85, 325);
	c.setColor (new Color (255, 45, 45));
	c.drawString ("HARD", 65, 430);
	c.setColor (new Color (156, 53, 68));
	c.setFont (new Font ("Lucida Console", 0, 40));
	c.drawString ("TIME", 425, 190);
	c.drawString ("ATTACK", 402, 230);
	if (debugMode)                                          //Only display these options if debug mode is on
	{
	    c.setColor (new Color (117, 17, 32));
	    c.fillRect (372, 266, 206, 77);
	    c.setColor (new Color (156, 53, 68));
	    c.fillRect (374, 269, 202, 71);
	    c.setColor (Color.black);
	    c.fillRect (372, 375, 206, 77);
	    c.setColor (new Color (54, 54, 54));
	    c.fillRect (374, 378, 202, 71);
	    c.setColor (new Color (54, 54, 54));
	    c.setFont (new Font ("Lucida Console", 0, 60));
	    c.drawString ("MAX", 420, 325);
	    c.setColor (Color.white);
	    c.drawString ("DEBUG", 384, 430);
	}
	for (int i = 0 ; i < 5 ; i++)                           //Start out the user's highlighted selection at 1, or start
	{
	    c.setColor (new Color (255, 255, 0));
	    c.drawRect (38 + i, 155 + i, 215 - 2 * i, 86 - 2 * i);
	}
	menuChoice = '1';
	while (true)
	{
	    userInput = c.getChar ();                           //Get user input
	    if (userInput == 'w' || userInput == 's')
	    {
		if (userInput == 's' && menuChoice < '4' && !(debugMode))
		{
		    menuChoice = (char) ((int) menuChoice + 1); //If input is "s", or down, move the menuChoice up by 1 to the next option. Limit choices if debugmode is off.
		}
		else if (userInput == 's' && menuChoice < '6' && debugMode)
		{
		    menuChoice = (char) ((int) menuChoice + 1); //If input is "s", or down, move the menuChoice up by 1 to the next option. Allow the full range of choices if debugMode is on.
		}
		else if (userInput == 'w' && menuChoice > '1')
		{
		    menuChoice = (char) ((int) menuChoice - 1); //If input is "w", or up, move the menuChoice down by 1 to the next option
		}

		for (int i = 0 ; i < 5 ; i++)                   //Regardless of input, up or down, draw over each highlighted selection
		{
		    c.setColor (new Color (123, 49, 61));
		    c.drawRect (38 + i, 155 + i, 215 - 2 * i, 86 - 2 * i);
		    c.drawRect (38 + i, 261 + i, 215 - 2 * i, 86 - 2 * i);
		    c.drawRect (38 + i, 370 + i, 215 - 2 * i, 86 - 2 * i);
		    c.drawRect (367 + i, 155 + i, 215 - 2 * i, 86 - 2 * i);
		    c.drawRect (367 + i, 261 + i, 215 - 2 * i, 86 - 2 * i);
		    c.drawRect (367 + i, 370 + i, 215 - 2 * i, 86 - 2 * i);
		}
		c.setColor (new Color (255, 255, 0));
		switch (menuChoice)                             //Use switch to highlight the user's menu input
		{
		    case '1':
			for (int i = 0 ; i < 5 ; i++)
			{
			    c.drawRect (38 + i, 155 + i, 215 - 2 * i, 86 - 2 * i);
			}
			break;
		    case '2':
			for (int i = 0 ; i < 5 ; i++)
			{
			    c.drawRect (38 + i, 261 + i, 215 - 2 * i, 86 - 2 * i);
			}
			break;
		    case '3':
			for (int i = 0 ; i < 5 ; i++)
			{
			    c.drawRect (38 + i, 370 + i, 215 - 2 * i, 86 - 2 * i);
			}
			break;
		    case '4':
			for (int i = 0 ; i < 5 ; i++)
			{
			    c.drawRect (367 + i, 155 + i, 215 - 2 * i, 86 - 2 * i);
			}
			break;
		    case '5':
			for (int i = 0 ; i < 5 ; i++)
			{
			    c.drawRect (367 + i, 261 + i, 215 - 2 * i, 86 - 2 * i);
			}
			break;
		    case '6':
			for (int i = 0 ; i < 5 ; i++)
			{
			    c.drawRect (367 + i, 370 + i, 215 - 2 * i, 86 - 2 * i);
			}
			break;
		}
	    }
	    else if ((int) userInput == 10)                     //If the user presses enter, exit the main menu and keep the menuChoice for later use.
	    {
		//Return menuChoice as user's difficulty
		//Difficulty of 1 means easy
		//Difficulty of 2 means medium
		//Difficulty of 3 means hard
		//Difficulty of 4 means Time attack
		//Difficulty of 5 means Maximum difficulty
		//Difficulty of 6 means Debug mode
		return Integer.parseInt ("" + menuChoice);
	    }
	}
    }


    /*
	game (): Runs the actual game using seperate classes for animations and calculations in this class.
	Global variables used: none
	Local variables used: parameter int difficulty, the level of difficulty for the user.
			      BufferedReader input, the input used to read several files for various data.
			      String originalText, the text to write, consolidated into a single string.
			      String line, the line that input is currently reading through. Updated each time input reads through a line.
			      PrintWriter output, the output used to write encoded text for various purposes.
			      int reds, greens blues: ints to be used for colour selection.
    */
    public void game (int difficulty)
    {
	BufferedReader input;
	String originalText = "";
	String line = "";
	int reds = 255, greens = 255, blues = 255;
	PrintWriter output;
	try                                                                     //Use a try statement to create text to iterate through
	{
	    output = new PrintWriter (new FileWriter ("gameFiles.ans"));  //Write the game files (e.g text speed, background colour) based on the difficulty.
	    output.println ("% Ansh Sharma - gameFiles.ans");
	    output.println (22 - 7 * difficulty);
	    switch (difficulty)         //Change background colour depending on difficulty.
	    {
		case 1:
		    reds = 255;
		    greens = 255;
		    blues = 0;
		    break;
		case 2:
		    reds = 0;
		    greens = 255;
		    blues = 255;
		    break;
		case 3:
		    reds = 255;
		    greens = 0;
		    blues = 255;
		    break;
		case 4:
		    reds = 255;
		    greens = 193;
		    blues = 0;
		    break;
		case 5:
		    reds = 0;
		    greens = 255;
		    blues = 193;
		    break;
		case 6:
		    reds = 193;
		    greens = 0;
		    blues = 255;
		    break;
	    }
	    output.println (reds);
	    output.println (greens);
	    output.println (blues);
	    output.println (0);
	    output.println (0);
	    output.close ();
	    input = new BufferedReader (new FileReader ("gameText.ans"));       //Read a line from gameText to copy into encoded characters in characterFiles. This encoded text will be used in teh game.
	    while (true)
	    {
		try
		{
		    line = input.readLine ();
		    if (line == (null))
			break;
		    originalText += line;                                  //Create one string with all the characters, add this line to that string
		}
		catch (Exception e)
		{
		}
	    }
	    input.close ();
	    output = new PrintWriter (new FileWriter ("characterFiles.ans"));
	    for (int i = 0 ; i < originalText.length () ; i++)              //Create a for loop to iterate through all of the text from gameText and print it to characterFiles.ans
	    {
		switch (originalText.charAt (i))
		{
		    case ' ':                                       //If the loop finds a space, output an underscore
			output.print ('_');
			output.println (i);
			break;
		    default:                                        //Otherwise, print the character that is in the text.
			output.print (originalText.charAt (i));
			output.println (i);

		}
	    }
	    originalText = "";
	    output.close ();
	    output = new PrintWriter (new FileWriter ("stats.ans"));
	    output.println ("0");   //Reset the stats to 0
	    output.println ("0");
	    output.close ();
	}
	catch (Exception e)
	{
	}

	for (int i = 0 ; i < 385 ; i++)                                 //Draw the background
	{
	    if (reds == 0)
		c.setColor (new Color (0, greens - i / 2, blues - i / 2));
	    else if (greens == 0)
		c.setColor (new Color (reds - i / 2, 0, blues - i / 2));
	    else if (blues == 0)
		c.setColor (new Color (reds - i / 2, greens - i / 2, 0));
	    c.drawLine (0, i, 640, i);
	}
	c.setColor (new Color (42, 119, 119));                      //Draw the bottom panel where text is displayed.
	c.fillRect (0, 386, 500, 114);
	c.setColor (new Color (64, 199, 199));
	c.fillRect (5, 391, 490, 104);
	c.setColor (new Color (140, 55, 36));
	c.fillRect (500, 386, 140, 114);
	c.setColor (new Color (237, 56, 16));
	c.fillRect (505, 391, 130, 104);
	c.setFont (new Font ("Lucida Console", 0, 20));
	c.setColor (Color.lightGray);
	c.drawString ("WPM", 510, 440);
	c.setFont (new Font ("Lucida Console", 0, 10));
	c.drawString ("Characters", 510, 460);
	c.drawString ("Errors", 510, 480);

	int count = 0;          //Define a counter to track each letter drawn in the game
	int tick = 0;          //Define a counter to track each millisecond that passes in the game, not completely accurate,
	//but that is fine as this is only for basic purposes. A more accurate tick will be used to calculate WPM
	letterTracer ();        //Call letterTracer to begin tracking the letters typed.
	time = (new Date ()).getTime ();        //Establish the number of milliseconds passed at the start of the game.
	while (true)
	{
	    try
	    {
		Thread.sleep (5);
	    }
	    catch (Exception e)
	    {
	    }
	    if (tick % (300 / difficulty) == 0 && difficulty < 4)   //If the game is on one of the basic easy/medium/hard modes, make the game continue like normal.
	    {
		gameLetter (count);
		count++;
	    }
	    if (tick % 200 == 0)            //Refresh background
	    {
		for (int i = 0 ; i < 385 ; i++)                                 //Draw the background
		{
		    if (reds == 0)
			c.setColor (new Color (0, greens - i / 2, blues - i / 2));
		    else if (greens == 0)
			c.setColor (new Color (reds - i / 2, 0, blues - i / 2));
		    else if (blues == 0)
			c.setColor (new Color (reds - i / 2, greens - i / 2, 0));
		    c.drawLine (0, i, 640, i);
		}
	    }
	    if (tick % 10 == 0)
	    {
		c.setColor (new Color (64, 199, 199));  //Update the bottom panel and the next 12 characters to be displayed.
		c.fillRect (5, 391, 490, 104);
		c.setFont (new Font ("Lucida Console", 0, 40));
		try
		{
		    input = new BufferedReader (new FileReader ("characterFiles.ans"));
		    for (int i = 0 ; i < 12 ; i++)
		    {
			line = input.readLine ();
			c.setFont (new Font ("Lucida Console", 0, 40));
			c.setColor (new Color (53, 78, 117));
			c.drawString ("" + line.charAt (0), 8 + 41 * i, 490);
		    }
		    input.close ();
		    c.setColor (new Color (237, 56, 16));
		    c.fillRect (569, 391, 65, 104);
		    input = new BufferedReader (new FileReader ("stats.ans"));
		    c.setFont (new Font ("Lucida Console", 0, 15));
		    c.setColor (Color.lightGray);
		    destroyedChars = Integer.parseInt (input.readLine ());
		    c.drawString ("" + destroyedChars, 573, 460);               //Calculate and print destroyed characters by the user
		    c.setFont (new Font ("Lucida Console", 0, 15));
		    c.setColor (Color.lightGray);
		    errors = Integer.parseInt (input.readLine ());
		    c.drawString ("" + errors, 573, 480);                   //Calculate and print errors
		    c.setFont (new Font ("Lucida Console", 0, 30));
		    c.setColor (Color.lightGray);
		    c.drawString ("" + Math.round(destroyedChars / 5.0 / (new Date ().getTime () - time) * 60000), 573, 430);       //Calculate and print wpm
		    input.close ();
		}
		catch (Exception e)
		{
		}
	    }
	    if (tick % 5 == 0)
	    {
		try
		{
		    input = new BufferedReader (new FileReader ("gameFiles.ans"));    //Check if there is a 1 at the 6th line of gameFiles.ans, meaning a GameLetter class has requested to create a new letter
		    for (int v = 0 ; v < 5 ; v++)
		    {
			input.readLine ();
		    }
		    if (input.readLine ().equals ("1"))
		    {
			gameLetter (count);
			count++;

			input.close ();
			input = new BufferedReader (new FileReader ("gameFiles.ans"));    //Encode and recreate gameFiles.ans, but add a 0 to the 6th line since we're resetting it to normal.
			for (int v = 0 ; v < 5 ; v++)
			{
			    originalText += input.readLine ();
			    originalText += "/";
			}
			originalText += "0/";
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
		    }
		}
		catch (Exception e)
		{
		}
	    }
	    tick++;
	}
    }



    /*
    gameOver () : Displays the game over screen and user scores, and saves a high score if possible. Asks the user for their name.
    Global variables used: none
    Local variables used: none
    */
    public void gameOver (int destroyedChars, int errors)
    {
    }


    /*
	moveLetter() : A class to be used to animate a small letter down the screen for gameplay.
	Global variables used: none
	Local variables used: none
    */
    public void moveLetter (char letter)
    {
    }


    /*
	main method : The method used for control flow.
	Global variables used: Console c
	Local variables used: AlAt, the class object of AlphaAttackMAXIMUM, this class.
    */
    public static void main (String[] args)
    {
	c = new Console ("Ansh Sharma - Alpha Attack MAXIMUM");
	AlphaAttackMAXIMUM AlAt = new AlphaAttackMAXIMUM ();
	//AlAt.splashScreen ();
	while (menuChoice != 4)
	{
	    AlAt.mainMenu ();
	    switch (menuChoice)
	    {
		case '1':
		    AlAt.game (AlAt.askData ());
		    AlAt.gameOver (destroyedChars, errors);
		    break;
		case '2':
		    AlAt.instructions ();
		    break;
		case '3':
		    while (menuChoice != 'e')
		    {
			AlAt.highScores ();
			switch (menuChoice)
			{
			    case 'c':
				AlAt.clearScores ();
				break;
			    case 'e':
				break;
			    default:
				new Message ("Wrong input! Please enter e or c.");
				break;
			}
		    }
		    break;
		case '4':
		    AlAt.goodbye ();
		    break;
	    }
	}
    }
}


