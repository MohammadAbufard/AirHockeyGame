/* ***************************** Refrences *******************************
1. The Audio Player Code Was Taken From:

    http://www.javaworld.com/article/2077521/learn-java/java-tip-24--how-to-play-audio-in-applications.html

2. The Use of the Mouse and the KeyBoard At the Same Time Was Helped By:

    Prathmesh Shetty & Nim Wijetunga

3. The Ablility to use the Mouse was Taught From:

    https://docs.oracle.com/javase/tutorial/uiswing/events/mouselistener.html

    http://math.hws.edu/eck/cs124/javanotes2/c6/s2.html

4.  Ibtesams Helped Us Create the Buttons for Our Main Menu. He Also Gave Us Some Useful Advices that helped Us Develop Our Main Menu
    */


// The "HockeyDashV3" class.
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Game4 extends Applet implements Runnable, MouseMotionListener, KeyListener
{

    //********************************** Variables Used To Make the Game ***********************************************************

    // Place instance variables here
    // Initialization of variables
    int appletsize_x = 422;
    int appletsize_y = 680;


    // The Ball
    int x_pos = appletsize_x / 2;           // x - Position of ball
    int y_pos = 300;                        // y - Position of ball
    int radius = 17;                        // Radius of ball
    int x_speed = 3;
    int y_speed = 3;


    // The Moving Stick
    int x_pos2 = appletsize_x / 2;          // x - Position of the stick
    int y_pos2 = 620;                       // y - Position of the stick
    int radius2 = 0;                        // Radius of the stick
    int x_speed2 = 0;                       // The speed of the stick


    // The net
    int x_pos3 = appletsize_x / 2;          // x - Position of the net
    int y_pos3 = 40;                        // y - Position of the net
    int radius3 = 0;                        // Radius of the net
    int x_speed3 = 2;                       // The speed of the net


    // The Last Obstacle
    int x_pos4 = appletsize_x;              // x - Position of the last obstacle
    int y_pos4 = 227;                       // y - Position of the last obstacle
    int radius4 = 0;                        // Radius of the last obstacle
    int x_speed4 = 3;                       // The speed of the last obstacle


    // The First Obstacle
    int x_pos5 = appletsize_x;              // x - Position of the first obstacle
    int y_pos5 = 450;                       // y - Position of the first obstacle
    int radius5 = 0;                        // Radius of the first obstacle
    int x_speed5 = 3;                       // The speed of the first obstacle


    // The Last Obstacle
    int x_pos6 = appletsize_x;              // x - Position of the second obstacle
    int y_pos6 = 350;                       // y - Position of the second obstacle
    int radius6 = 0;                        // Radius of the second obstacle
    int x_speed6 = 3;                       // The speed of the second obstacle


    // The First Fake Ball
    int x_pos7 = 30;                         // x - Position of the first fake ball
    int y_pos7 = appletsize_y / 2;           // y - Position of the first fake ball
    int radius7 = 17;                        // Radius of the first fake ball
    int x_speed7 = 4;
    int y_speed7 = 4;


    // The Last Fake Ball
    int x_pos8 = 207;                        // x - Position of the second fake ball
    int y_pos8 = 310;                        // y - Position of the second fake ball
    int radius8 = 17;                        // Radius of the second fake ball
    int x_speed8 = 4;
    int y_speed8 = 4;


    int counter = 0;                        // A counter to keep track of the score
    int livesCount = 3;                     // A counter to keep track of the number of lives left



    //********************************** Variables Used To Make the Main Menu ***********************************************************

    // The coordinates of the mouse clicking on the screen
    int mouseClick_x;
    int mouseClick_y;

    // The coordinates of the mouse position on the screen
    int mousePos_x;
    int mousePos_y;


    // The position of the "play" button in the main screen
    int playButton_x = 45;
    int playButton_y = 555;
    // The play button's click coordinates (this allows the mouse to click on the button)
    int playButtonSize_x = 158;
    int playButtonSize_y = 56;


    // The Position of the "help" button (in the main screen)
    int helpButton_x = 235;
    int helpButton_y = 557;


    // The position of the returnToMainButton button
    int returningToMenu_x = 125;
    int returningToMenu_y = 630;


    // The pick of a team before clicking
    int selectBox_x = 102;
    int selectBox_y = 102;
    // The clicking position of the first team (the canadian team/flag)
    int usersTeamChoice_x = 190;
    int usersTeamChoice_y = 100;
    // The clicking position of the second team (the american team/flag)
    int usersTeamChoice2_x = 180;
    int usersTeamChoice2_y = 300;
    // The clicking position of the third team (the swedish team/flag)
    int usersTeamChoice3_x = 180;
    int usersTeamChoice3_y = 440;


    // The pick the user before clicking
    int userPick = 0;
    // The user's selection
    int userSelection = 0;


    // The used booleans in the main menu
    boolean help = false;               // Indicates if the user chose the help option in the main menu
    boolean atMainMenu = true;          // Indicates if the user is at the main menu
    boolean menuStarted = true;         // Indicates if the main menu have started (it have been executed)
    boolean start = false;              // Indicates if the user pressed the play button in the main menu
    boolean returnToMain = true;        // Indicates if the user chose to return to the main menu
    boolean startGame = false;           // Indicates if the game started



    //********************************** Imports All The Needed/used Images in the game  ***********************************************************


    // Declare two instance variables at the head of the program
    private Image dbImage;
    private Graphics dbg;

    Image canadianFlag = Toolkit.getDefaultToolkit ().getImage ("cjersey.png");                         // The Canadian Flag Image
    Image americanFlag = Toolkit.getDefaultToolkit ().getImage ("Ajersey.png");                         // The American Flag Image
    Image swedensFlag = Toolkit.getDefaultToolkit ().getImage ("Sjersey.png");                          // The Swedish Flag Image
    Image returnToMainButton = Toolkit.getDefaultToolkit ().getImage ("returnButton.png");              // The Return Button Image
    Image returnToMainDarkButton = Toolkit.getDefaultToolkit ().getImage ("returnButtonDark.png");      // The Dark Return Button Image
    Image helpButton = Toolkit.getDefaultToolkit ().getImage ("helpButton.png");                        // The Help Button Image
    Image helpDarkButton = Toolkit.getDefaultToolkit ().getImage ("helpButtonDark.png");                // The Dark Help Button Image
    Image usersTeamChoice = Toolkit.getDefaultToolkit ().getImage ("CanadianJersey.png");               // The Canadian Jersey Image
    Image usersTeamChoice2 = Toolkit.getDefaultToolkit ().getImage ("AmericanJersey.png");              // The American Jersey Image
    Image usersTeamChoice3 = Toolkit.getDefaultToolkit ().getImage ("SwedishJersey.png");               // The Swedish Jersey Image
    Image playButton = Toolkit.getDefaultToolkit ().getImage ("PlayButton.png");                        //The Play Button Image
    Image playButtonDark = Toolkit.getDefaultToolkit ().getImage ("PlayButtonDark.png");                // The Dark Play Button Image
    Image field = Toolkit.getDefaultToolkit ().getImage ("Field.jpg");                                  // The Hockey Field (The game's background)Image
    Image hearts = Toolkit.getDefaultToolkit ().getImage ("Heart.jpg");                                 // The User's Lives Image
    Image arrows = Toolkit.getDefaultToolkit ().getImage ("arrowkeysfade.png");                         // The Arrow Image (Used in The Help Screen)
    Image scorescreen = Toolkit.getDefaultToolkit ().getImage ("ScoreScreen1.jpg");                     // The Background of the Score Screen (When the Game Finishes)
    Image mainMenuScreen = Toolkit.getDefaultToolkit ().getImage ("mainscreen2.jpg");                   // The Background of the Main Menu



    public void init ()
    {
	addKeyListener (this);

	// Sets the size of the menu applet
	resize (422, 700);

	addMouseMotionListener (this);

	addMouseListener (new MouseAdapter ()

	{
	    public void mouseClicked (MouseEvent d)
	    {
		// set's and gives the mouseClick variables their values
		mouseClick_x = d.getX ();
		mouseClick_y = d.getY ();


		// If the user is at the main menu
		if (atMainMenu == true)
		{

		    // Takes the user to the help screen when he/she clicks on the help button in the manin menu
		    if (mouseClick_y >= helpButton_y && mouseClick_y <= helpButton_y + playButtonSize_y && mouseClick_x >= helpButton_x && mouseClick_x <= helpButton_x + playButtonSize_x)
		    {
			// The user is at the help screen
			help = true;

			// The user is not in the main menu
			atMainMenu = false;
			repaint ();
		    }

		    // Takes the user to the next screen if he/she chose the start button
		    if (mouseClick_y >= playButton_y && mouseClick_y <= playButton_y + playButtonSize_y && mouseClick_x >= playButton_x && mouseClick_x <= playButton_x + playButtonSize_x)
		    {

			// The user start the game (takes him/her self to the choosing team menu)
			start = true;

			// The user is no longer in the main menu
			atMainMenu = false;
			repaint ();
		    }
		}

		// If the user is at the help screen
		if (help == true)
		{

		    // If the user clicks on the return button he/she will be taken to the main menu
		    if (mouseClick_y >= returningToMenu_y && mouseClick_y <= returningToMenu_y + playButtonSize_y && mouseClick_x >= returningToMenu_x && mouseClick_x <= returningToMenu_x + playButtonSize_x)
		    {

			// The user is taken to the main menu
			atMainMenu = true;
			repaint ();
		    }
		}

		// If the user clicked on the play button in the main menu (the choosing team menu)
		if (start == true)
		{

		    // If the user clicks on the return button he/she will be taken back to the main menu
		    if (mouseClick_y >= returningToMenu_y && mouseClick_y <= returningToMenu_y + playButtonSize_y && mouseClick_x >= returningToMenu_x && mouseClick_x <= returningToMenu_x + playButtonSize_x)
		    {

			// Sets the user's choice to zero (because he/she returned to the main menu)
			userPick = 0;

			// The game is back to the main menu (the user needs to click on the play button once again for the start value to be true again)
			start = false;

			// The user is back to the main menu
			atMainMenu = true;
			repaint ();
		    }


		    // If the user chooses the first team
		    if (userPick != 1 && mouseClick_x >= usersTeamChoice_x && mouseClick_x <= usersTeamChoice_x + selectBox_x && mouseClick_y >= usersTeamChoice_y && mouseClick_y <= usersTeamChoice_y + selectBox_y)
		    {
			startGame = true;
			// Saves the user's choice
			userPick = 1;
			repaint ();
		    }

		    // If the user chooses the second team
		    else if (userPick != 2 && mouseClick_x >= usersTeamChoice2_x && mouseClick_x <= usersTeamChoice2_x + selectBox_x && mouseClick_y >= usersTeamChoice2_y && mouseClick_y <= usersTeamChoice2_y + selectBox_y)
		    {
			startGame = true;

			// Saves the user's choice
			userPick = 2;
			repaint ();
		    }

		    // If the user chooses the second team
		    else if (userPick != 3 && mouseClick_x >= usersTeamChoice3_x && mouseClick_x <= usersTeamChoice3_x + selectBox_x && mouseClick_y >= usersTeamChoice3_y && mouseClick_y <= usersTeamChoice3_y + selectBox_y)
		    {
			startGame = true;

			// Saves the user's choice
			userPick = 3;
			repaint ();
		    }


		}

	    }
	}
	);

    }


    public void mouseMoved (MouseEvent f)
    {

	// set's and gives the mousePos variables their values
	mousePos_x = f.getX ();
	mousePos_y = f.getY ();

	// If the user is at the main menu
	if (atMainMenu == true)
	{
	    // If the mouse position is equal to the position of the play button it becomes darker
	    if (mousePos_y >= playButton_y && mousePos_y <= playButton_y + playButtonSize_y && mousePos_x >= playButton_x && mousePos_x <= playButton_x + playButtonSize_x)
	    {

		// The menu did not start yet
		menuStarted = false;
		repaint ();
	    }
	    else
	    {

		// The menu started
		menuStarted = true;
		repaint ();
	    }


	    // If the mouse position is equal to the position of the help button it becomes darker
	    if (mousePos_x >= helpButton_x && mousePos_x <= helpButton_x + playButtonSize_x && mousePos_y >= helpButton_y && mousePos_y <= helpButton_y + playButtonSize_y)
	    {

		// The user is not at the help screen
		help = false;
		repaint ();
	    }
	    else
	    {

		// The user is at the help screen
		help = true;
		repaint ();
	    }


	}


	// If the user is at the choose a team screen
	if (start == true)
	{

	    // If the mouse position is equal to the position of the return button it becomes darker
	    if (mousePos_y >= returningToMenu_y && mousePos_y <= returningToMenu_y + playButtonSize_y && mousePos_x >= returningToMenu_x && mousePos_x <= returningToMenu_x + playButtonSize_x)
	    {

		// The user have not returned to the main menu
		returnToMain = false;
		repaint ();
	    }
	    else
	    {
		// The user is at the main menu
		returnToMain = true;
		repaint ();
	    }


	    // If the mouse position is equal to the position of the first team (after the user have pressed the play button) it will show the user the team's jersey
	    if (mousePos_x >= usersTeamChoice_x && mousePos_x <= usersTeamChoice_x + selectBox_x && mousePos_y >= usersTeamChoice_y && mousePos_y <= usersTeamChoice_y + selectBox_y)
	    {
		// The user's chose is stored in the userSelection variable
		userSelection = 1;
		repaint ();
	    }

	    // If the mouse position is equal to the position of the second team (after the user have pressed the play button) it will show the user the team's jersey
	    else if (mousePos_x >= usersTeamChoice2_x && mousePos_x <= usersTeamChoice2_x + selectBox_x && mousePos_y >= usersTeamChoice2_y && mousePos_y <= usersTeamChoice2_y + selectBox_y)
	    {

		// The user's chose is stored in the userSelection variable
		userSelection = 2;
		repaint ();
	    }

	    // If the mouse position is equal to the position of the third team (after the user have pressed the play button) it will show the user the team's jersey
	    else if (mousePos_x >= usersTeamChoice3_x && mousePos_x <= usersTeamChoice3_x + selectBox_x && mousePos_y >= usersTeamChoice3_y && mousePos_y <= usersTeamChoice3_y + selectBox_y)
	    {
		// The user's chose is stored in the userSelection variable
		userSelection = 3;
		repaint ();
	    }


	}

    }


    public void mouseDragged (MouseEvent ex)
    {

    }


    public void mouseExit (MouseEvent ex)
    {

    }


    public void mouseUp (MouseEvent ex)
    {

    }


    public void mouseDown (MouseEvent ex)
    {

    }


    public void start ()
    {

	// define a new thread
	Thread th = new Thread (this);
	// start this thread
	th.start ();

    }


    public void stop ()
    {
	//nothing right now
    }


    public void destroy ()
    {
	//nothing right now
    }


    public void keyReleased (KeyEvent e)
    {

    }


    public void keyTyped (KeyEvent e)
    {

    }


    // method to handle key - down events
    public void keyPressed (KeyEvent e)
    {
	int key = e.getKeyCode ();

	// user presses left cursor key
	if (key == KeyEvent.VK_RIGHT)
	{

	    // Moves the stick to the right
	    x_pos2 = x_pos2 + 15;

	}

	// user presses right cursor key
	else if (key == KeyEvent.VK_LEFT)
	{

	    // Moves the stick to the left
	    x_pos2 = x_pos2 - 15;

	}

	else
	{
	    /* Additionally the method prints out the ASCII - value if an other key is pressed. This is not necessary but a possibility for you to test which value a key has.*/
	    System.out.println ("Character: " + (char) key + " Integer Value: " + key);
	}

    }


    public void run ()
    {

	// lower ThreadPriority
	Thread.currentThread ().setPriority (Thread.MIN_PRIORITY);


	// Plays the game's background music
	AudioClip backgroundMusic = getAudioClip (getCodeBase (), "Backyard_Hockey_Music_Air_Hockey_1.wav ");
	backgroundMusic.loop ();
	// Taken From: Refrence #1



	// run a long while (true) this means in our case "always"
	while (true && livesCount != 0)
	{

	    //******************************************** Ball Movement **************************************

	    // Stops The Game From Starting before the player chooses a team
	    while (startGame == false)
	    {
		livesCount = 3;
	    }


	    // Ball is bounced if its x - position reaches the right border of the applet
	    if (x_pos > appletsize_x - (radius))
	    {


		// Audio is played when the ball hit the right border of the applet
		AudioClip right = getAudioClip (getCodeBase (), "Puck Hitting Border.wav ");
		right.play ();
		// Taken From: Refrence #1

		// Change direction of ball movement
		x_speed = -3;


		// The x - speed of the ball increases as the user's score increases
		if (counter >= 2)
		{
		    x_speed = -4;
		}

		if (counter >= 3)
		{
		    x_speed = -5;
		}

		if (counter >= 7)
		{
		    x_speed = -6;
		}

		if (counter >= 12)
		{
		    x_speed = -7;
		}
	    }


	    // Ball is bounced if its x - position reaches the left border of the applet
	    else if (x_pos < radius)
	    {

		// Audio is played when the ball hit the eft border of the applet
		AudioClip left = getAudioClip (getCodeBase (), "Puck Hitting Border.wav ");
		left.play ();
		// Taken From: Refrence #1

		// Change direction of ball movement
		x_speed = +3;


		// The x - speed of the ball increases as the user's score increases
		if (counter >= 2)
		{
		    x_speed = +4;
		}

		if (counter >= 3)
		{
		    x_speed = +5;
		}

		if (counter >= 7)
		{
		    x_speed = +6;
		}

		if (counter >= 12)
		{
		    x_speed = +7;
		}
	    }

	    //if the ball is g
	    else if (y_pos > appletsize_y)
	    {

		// Audio is played when the ball hit the bottom border of the applet
		AudioClip hit = getAudioClip (getCodeBase (), "Puck Hitting Border.wav ");
		hit.play ();
		// Taken From: Refrence #1


		// Change direction of ball movement
		y_speed = -3;

		// The user loses a life
		livesCount--;


		// The y - speed of the ball increases as the user's score increases
		if (counter >= 2)
		{
		    y_speed = -4;
		}

		if (counter >= 3)
		{
		    y_speed = -5;
		}

		if (counter >= 7)
		{
		    y_speed = -6;
		}

		if (counter >= 12)
		{
		    y_speed = -7;
		}

		// If the user have 0 lives left the game will end
		if (livesCount == 0)
		{
		    return;
		}
	    }


	    // Ball is bounced if its y - position reaches the top border of the applet
	    else if (y_pos < 38)
	    {

		// Audio is played when the ball hit the top border of the applet
		AudioClip hit2 = getAudioClip (getCodeBase (), "Puck Hitting Border.wav ");
		hit2.play ();
		// Taken From: Refrence #1


		// Change direction of ball movement
		y_speed = +3;


		// The y - speed of the ball increases as the user's score increases
		if (counter >= 2)
		{
		    y_speed = +4;
		}

		if (counter >= 3)
		{
		    y_speed = +5;
		}

		if (counter >= 7)
		{
		    y_speed = +6;
		}

		if (counter >= 12)
		{
		    y_speed = +7;
		}
	    }

	    x_pos = x_pos + x_speed; //update the current location of the x on the ball
	    y_pos = y_pos + y_speed; //update the current location of the y on the ball


	    //*************************************** The Stick Movement **********************************************

	    // Ball is bounced if its positions touches the moving stick
	    if (y_pos > (y_pos2 - radius) && x_pos > x_pos2 - radius && (x_pos < x_pos2 + 100))
	    {

		// Change direction of ball movement
		y_speed = -3;


		// The y - speed of the ball increases as the user's score increases
		if (counter >= 2)
		{
		    y_speed = -4;
		}

		if (counter >= 3)
		{
		    y_speed = -5;
		}

		if (counter >= 7)
		{
		    y_speed = -6;
		}

		if (counter >= 12)
		{
		    y_speed = -7;
		}

	    }


	    // The stick stops if its x - position reaches the right border of the applet
	    if (x_pos2 > 327)
	    {

		// Stop the stick from escaping
		x_pos2 = 327;

	    }


	    // The stick stops if its x - position reaches the left border of the applet
	    else if (x_pos2 < 0)
	    {

		// Change direction of ball movement
		x_pos2 = 0;
	    }




	    //*************************************** The Nets Movement **********************************************


	    // Ball is bounced if its positions are equal to the net and one is added to the score
	    else if (y_pos < (y_pos3 - radius) + 30 && x_pos < x_pos3 + 100 && (x_pos > x_pos3 - 15 && y_pos > y_pos3))
	    {

		// Audio is played when the ball touches the net
		AudioClip goal = getAudioClip (getCodeBase (), "GoalSound.wav ");
		goal.play ();
		// Taken From: Refrence #1


		// Change direction of ball movement
		y_speed = +3;


		// The y - speed of the ball increases as the user's score increases
		if (counter >= 2)
		{
		    y_speed = +4;
		}

		if (counter >= 3)
		{
		    y_speed = +5;
		}

		if (counter >= 7)
		{
		    y_speed = +6;
		}

		if (counter >= 12)
		{
		    y_speed = +7;
		}
		// Increase the user's score
		counter++;

	    }


	    // The net is bounced if its x - position reaches the right border of the applet
	    if (x_pos3 > 355)
	    {

		// Change direction of ball movement
		x_speed3 = -3;

		// The x - speed of the net increases as the user's score increases
		if (counter >= 3)
		{
		    x_speed3 = -3;
		}

		if (counter >= 7)
		{
		    x_speed3 = -4;
		}

		if (counter >= 12)
		{
		    x_speed3 = -5;
		}
	    }
	    // The net is bounced if its x - position reaches the left border of the applet
	    else if (x_pos3 < radius)
	    {

		// Change direction of ball movement
		x_speed3 = +3;


		// The x - speed of the net increases as the user's score increases
		if (counter >= 3)
		{
		    x_speed3 = +3;
		}

		if (counter >= 7)
		{
		    x_speed3 = +4;
		}

		if (counter >= 12)
		{
		    x_speed3 = +5;
		}
	    }

	    x_pos3 += x_speed3; //update the current location of the x on the net




	    //*************************************** The First Obtacles  **********************************************


	    // If the user's score is equal or greater than 3 the ball is bounced if its positions are equal to the first obstacle
	    if (counter >= 3 && (y_pos < (y_pos5 - radius) + 22 && x_pos < x_pos5 - 320 && (x_pos > x_pos5 - 415 && y_pos > y_pos5)))

		{

		    // Audio is played when the ball is bounced off the first obstacle
		    AudioClip firstObstacle = getAudioClip (getCodeBase (), "Obstaclesound.wav");
		    firstObstacle.play ();
		    // Taken From: Refrence #1


		    // Change direction of ball movement
		    y_speed = +3;


		    // The y - speed of the ball (after it is bouced of the first obstacle) increases as the user's score increases
		    if (counter >= 2)
		    {
			y_speed = +4;
		    }

		    if (counter >= 3)
		    {
			y_speed = +5;
		    }

		    if (counter >= 7)
		    {
			y_speed = +6;
		    }

		    if (counter >= 12)
		    {
			y_speed = +7;
		    }
		}



	    //*************************************** The Second Obtacles  **********************************************

	    // If the user's score is equal or greater than 7 the ball is bounced if its positions are equal to the second obstacle
	    if (counter >= 7 && (y_pos < (y_pos6 - radius) + 25 && x_pos < x_pos6 + 320 && (x_pos > x_pos6 - 110 && y_pos > y_pos6)))
	    {

		// Audio is played when the ball is bounced off the second obstacle
		AudioClip secondObstacle = getAudioClip (getCodeBase (), "Obstaclesound.wav");
		secondObstacle.play ();
		// Taken From: Refrence #1


		// Change direction of ball movement
		y_speed = +3;


		// The y - speed of the ball (after it is bouced of the second obstacle) increases as the user's score increases
		if (counter >= 2)
		{
		    y_speed = +4;
		}

		if (counter >= 3)
		{
		    y_speed = +5;
		}

		if (counter >= 7)
		{
		    y_speed = +6;
		}

		if (counter >= 12)
		{
		    y_speed = +7;
		}
	    }


	    //*************************************** The Third Obtacles  **********************************************
	    // If the user's score is equal or greater than 12 the ball is bounced if its positions are equal to the third obstacle
	    if (counter >= 12 && (y_pos < (y_pos4 - radius) + 23 && x_pos < x_pos4 + 100 && (x_pos > x_pos4 - 15 && y_pos > y_pos4)))

		{

		    // Audio is played when the ball is bounced off the third obstacle
		    AudioClip thirdObstacle = getAudioClip (getCodeBase (), "Obstaclesound.wav");
		    thirdObstacle.play ();
		    // Taken From: Refrence #1


		    // Change direction of ball movement
		    y_speed = +3;


		    // The y - speed of the ball (after it is bouced of the third obstacle) increases as the user's score increases
		    if (counter >= 2)
		    {
			y_speed = +4;
		    }

		    if (counter >= 3)
		    {
			y_speed = +5;
		    }

		    if (counter >= 7)
		    {
			y_speed = +6;
		    }

		    if (counter >= 12)
		    {
			y_speed = +7;
		    }
		}

	    // The last obstacle is bounced if its x - position reaches the right border of the applet
	    if (x_pos4 > 355)
	    {

		// Change direction of ball movement
		x_speed4 = -3;


		// The x - speed of the last obstacle increases as the user's score increases
		if (counter >= 3)
		{
		    x_speed4 = -4;
		}

		if (counter >= 7)
		{
		    x_speed4 = -5;
		}

		if (counter >= 12)
		{
		    x_speed4 = -6;
		}
	    }


	    // The last ostacle is bounced if its x - position reaches the left border of the applet
	    else if (x_pos4 < radius)
	    {

		// Change direction of ball movement
		x_speed4 = +3;


		// The x - speed of the last obstacle increases as the user's score increases
		if (counter >= 3)
		{
		    x_speed4 = +4;
		}

		if (counter >= 7)
		{
		    x_speed4 = +5;
		}

		if (counter >= 12)
		{
		    x_speed4 = +6;
		}
	    }

	    x_pos4 += x_speed4; //update the current location of the x on the last obstacle




	    //*************************************** The First Fake Ball  **********************************************

	    // The fake balls will be added to the game when the player's score is greater than 20 (the first fake ball will be added) and greater than 26 ((the first fake ball will be added).
	    // Their job is to disract the player's attention away from the real ball, causing him/her to loose lives.



	    // The first fake ball is bounced if its x - position reaches the right border of the applet
	    if (x_pos7 > appletsize_x - (radius7 + 14))
	    {

		// Change direction of the first fake ball movement
		x_speed7 = -4;

		// The x - speed of the first fake ball increases as the user's score increases
		if (counter >= 3)
		{
		    x_speed7 = -5;
		}

		if (counter >= 7)
		{
		    x_speed7 = -6;
		}

		if (counter >= 12)
		{
		    x_speed7 = -7;
		}
	    }


	    // The first fake ball is bounced if its x - position reaches the left border of the applet
	    else if (x_pos7 < radius7 - 29)
	    {

		// Change direction of the first fake ball movement
		x_speed7 = +4;


		// The x - speed of the first fake ball increases as the user's score increases
		if (counter >= 3)
		{
		    x_speed7 = +5;
		}

		if (counter >= 7)
		{
		    x_speed7 = +6;
		}

		if (counter >= 12)
		{
		    x_speed7 = +7;
		}
	    }

	    else if (x_pos7 < radius7)
	    {

		// Change direction of the first fake ball movement
		x_speed7 = +4;

		// The x - speed of the first fake ball increases as the user's score increases
		if (counter >= 3)
		{
		    x_speed7 = +5;
		}

		if (counter >= 7)
		{
		    x_speed7 = +6;
		}

		if (counter >= 12)
		{
		    x_speed7 = +7;
		}
	    }

	    //if the first fake ball is g
	    else if (y_pos7 > appletsize_y)
	    {

		// Change direction the first fake ball movement
		y_speed7 = -4;

		// The y - speed of the first fake ball increases as the user's score increases
		if (counter >= 3)
		{
		    y_speed7 = -5;
		}

		if (counter >= 7)
		{
		    y_speed7 = -6;
		}

		if (counter >= 12)
		{
		    y_speed7 = -7;
		}
	    }

	    // The first fake ball is bounced if its y - position reaches the top border of the applet
	    else if (y_pos7 < 38)
	    {

		// Change direction the first fake ball movement
		y_speed7 = +5;


		// The y - speed of the first fake ball increases as the user's score increases
		if (counter >= 3)
		{
		    y_speed7 = +5;
		}

		if (counter >= 7)
		{
		    y_speed7 = +6;
		}

		if (counter >= 12)
		{
		    y_speed7 = +7;
		}
	    }

	    x_pos7 = x_pos7 + x_speed7; //update the current location of the x on the first fake ball
	    y_pos7 = y_pos7 + y_speed7; //update the current location of the y on the first fake ball




	    //*************************************** The Second Fake Ball  **********************************************

	    // The second fake ball is bounced if its x - position reaches the right border of the applet
	    if (x_pos8 > appletsize_x - (radius8 + 14))
	    {

		// Change direction of the second fake ball movement
		x_speed8 = -4;

		// The x - speed of the second fake ball increases as the user's score increases
		if (counter >= 3)
		{
		    x_speed8 = -5;
		}

		if (counter >= 7)
		{
		    x_speed8 = -6;
		}

		if (counter >= 12)
		{
		    x_speed8 = -7;
		}
	    }


	    // The second fake ball is bounced if its x - position reaches the left border of the applet
	    else if (x_pos8 < radius8 - 29)
	    {

		// Change direction of the second fake ball movement
		x_speed8 = +4;


		// The x - speed of the second fake ball increases as the user's score increases
		if (counter >= 3)
		{
		    x_speed8 = +5;
		}

		if (counter >= 7)
		{
		    x_speed8 = +6;
		}

		if (counter >= 12)
		{
		    x_speed8 = +7;
		}
	    }

	    else if (x_pos8 < radius8)
	    {

		// Change direction of the second fake ball movement
		x_speed8 = +4;

		// The x - speed of the second fake ball increases as the user's score increases
		if (counter >= 3)
		{
		    x_speed8 = +5;
		}

		if (counter >= 7)
		{
		    x_speed8 = +6;
		}

		if (counter >= 12)
		{
		    x_speed8 = +7;
		}
	    }

	    //if the second fake ball is g
	    else if (y_pos8 > appletsize_y)
	    {

		// Change direction the second fake ball movement
		y_speed8 = -4;

		// The y - speed of the second fake ball increases as the user's score increases
		if (counter >= 3)
		{
		    y_speed8 = -5;
		}

		if (counter >= 7)
		{
		    y_speed8 = -6;
		}

		if (counter >= 12)
		{
		    y_speed8 = -7;
		}
	    }

	    // The second fake ball is bounced if its y - position reaches the top border of the applet
	    else if (y_pos8 < 38)
	    {

		// Change direction the second fake ball movement
		y_speed8 = +5;


		// The y - speed of the second fake ball increases as the user's score increases
		if (counter >= 3)
		{
		    y_speed8 = +5;
		}

		if (counter >= 7)
		{
		    y_speed8 = +6;
		}

		if (counter >= 12)
		{
		    y_speed8 = +7;
		}
	    }

	    x_pos8 = x_pos8 + x_speed8; //update the current location of the x on the second fake ball
	    y_pos8 = y_pos8 + y_speed8; //update the current location of the y on the second fake ball

	    // repaint the applet
	    repaint ();

	    try
	    {
		// Stop thread for 20 milliseconds
		Thread.sleep (20);
	    }
	    catch (InterruptedException ex)
	    {
		// do nothing
	    }

	    // set ThreadPriority to maximum value
	    Thread.currentThread ().setPriority (Thread.MAX_PRIORITY);
	}
    }


    /** Update - Method, implements double buffering */
    public void update (Graphics g)
    {

	// initialize buffer
	if (dbImage == null)
	{
	    dbImage = createImage (this.getSize ().width, this.getSize ().height);
	    dbg = dbImage.getGraphics ();
	}


	// clear screen in background
	dbg.setColor (getBackground ());
	dbg.fillRect (0, 0, this.getSize ().width, this.getSize ().height);

	// draw elements in background
	dbg.setColor (getForeground ());
	paint (dbg);

	// draw image on the screen
	g.drawImage (dbImage, 0, 0, this);

    }


    public void paint (Graphics g)
    {

	// If the user is at the main menu
	if (atMainMenu == true)
	{

	    // The main menu's background is painted
	    g.drawImage (mainMenuScreen, -32, 5, this);

	    // Draws the play button on the main menu
	    if (menuStarted == true)
		g.drawImage (playButton, playButton_x, playButton_y, this);
	    else if (menuStarted == false)
		g.drawImage (playButtonDark, playButton_x, playButton_y, this);


	    // Draws the help button on the main menu
	    if (help == true)
		g.drawImage (helpButton, helpButton_x, helpButton_y, this);
	    else if (help == false)
		g.drawImage (helpDarkButton, helpButton_x, helpButton_y, this);

	}


	// If the user clicked on the help button
	if (atMainMenu == false && start == false && help == true)
	{

	    // Sets the background color
	    setBackground (Color.gray);

	    // Font set to AgencyFb, words are size 55
	    Font font = new Font ("Agency FB", 0, 55);
	    g.setFont (font);

	    // Prints the heading of the help screen
	    g.drawString (" How To Play", 100, 50);

	    // Font set to Calibri, words are size 20
	    Font font2 = new Font ("Calibri", 0, 20);
	    g.setFont (font2);

	    // Prints out the rules of the game on the help screen
	    g.drawString ("Controls: Move the paddle with left, right arrow", 0, 90);
	    g.drawString ("keys.", 4, 120);
	    g.drawString ("Objective: Hit oppposing paddle with the puck", 0, 160);
	    g.drawString ("for points.", 4, 200);
	    g.drawString ("Obstacles in (Black) will be added in as score", 0, 240);
	    g.drawString ("increases.", 4, 280);
	    g.drawString ("Three lives given, a life will be lost if ball goes", 0, 320);
	    g.drawString ("behind your paddle.", 4, 360);
	    g.drawString ("When the game ends, move the cursor across ", 4, 400);
	    g.drawString ("the screen to view score.", 4, 430);

	    // Draws the Image of the Arrows on the Screen to Help the User Understand How the Game Works
	    g.drawImage (arrows, 80, 450, this);



	    // Draws the return button to allow the user to return to the main menu
	    if (returnToMain == true)
		g.drawImage (returnToMainButton, returningToMenu_x, returningToMenu_y, this);
	    else if (returnToMain == false)
		g.drawImage (returnToMainDarkButton, returningToMenu_x, returningToMenu_y, this);

	}


	// If the user clicked on the play button
	if (atMainMenu == false && start == true)
	{

	    // Sets the bacground color
	    setBackground (Color.gray);

	    // Paints the heading on top of the new screen
	    g.setColor (Color.BLACK);
	    g.setFont (new Font ("Ariel", 30, 45));
	    g.drawString ("Choose a Team", 45, 50);

	    // Draws the flags of the countries
	    g.drawImage (canadianFlag, -60, -40, this);
	    g.drawImage (americanFlag, -60, 130, this);
	    g.drawImage (swedensFlag, -60, 300, this);


	    // If the user clicked on the return button
	    if (returnToMain == true)
		g.drawImage (returnToMainButton, returningToMenu_x, returningToMenu_y, this);
	    else if (returnToMain == false)
		g.drawImage (returnToMainDarkButton, returningToMenu_x, returningToMenu_y, this);


	    // If the position of the mouse is equal to the position of the first team's flag the program will show the team's jersey
	    if (userSelection == 1)
		g.drawImage (usersTeamChoice, -60, -40, this);

	    // If the position of the mouse is equal to the position of the second team's flag the program will show the team's jersey
	    else if (userSelection == 2)
		g.drawImage (usersTeamChoice2, -60, 130, this);

	    // If the position of the mouse is equal to the position of the third team's flag the program will show the team's jersey
	    else if (userSelection == 3)
		g.drawImage (usersTeamChoice3, -60, 300, this);


	    // Selects the first team's jersey if the user clicked on it
	    if (userPick == 1)
	    {
		g.drawImage (usersTeamChoice, -50, -40, this);

		// Starts the game
		startGame = true;
	    }

	    // Selects the second team's jersey if the user clicked on it
	    else if (userPick == 2)
	    {
		g.drawImage (usersTeamChoice2, -60, 130, this);

		// Starts the game
		startGame = true;
	    }

	    // Selects the third team's jersey if the user clicked on it
	    else if (userPick == 3)
	    {
		g.drawImage (usersTeamChoice3, -60, 300, this);

		// Starts the game
		startGame = true;
	    }


	    // If the game started
	    if (startGame == true)
	    {

		// Prevents the use of the mouse (prevents changing the team after the game started)
		usersTeamChoice_y = 10000;
		usersTeamChoice2_y = 10000;
		usersTeamChoice3_y = 1000;


		// Removes the Return to Menu Button From the Screen
		returningToMenu_y = 730;

		//Draws the Bckground of the Game
		g.drawImage (field, 0, 25, null);
		g.setColor (Color.WHITE);
		g.fillRect (0, 0, 422, 30);


		//************** Paints the User's Score and Draws the user's lives ***************

		// sets the color of the obstacles, the score and the lives
		g.setColor (Color.black);

		Font font = new Font ("Arial", 15, 15);
		g.setFont (font);
		// paint the user's score
		g.drawString ("Score: " + counter, 1, 19);

		// paint the number of lives left
		g.drawString ("Lives: ", 290, 19);


		// Draws 3 hearts on th top right of the screen when the user have 3 lives left
		if (livesCount == 3)
		{
		    g.drawImage (hearts, 325, 5, null);
		    g.drawImage (hearts, 350, 5, null);
		    g.drawImage (hearts, 375, 5, null);
		}


		// Draws 2 hearts on th top right of the screen when the user have 2 lives left
		if (livesCount == 2)
		{
		    g.drawImage (hearts, 350, 5, null);
		    g.drawImage (hearts, 375, 5, null);
		}


		// Draws 1 hearts on th top right of the screen when the user have 1 life left
		if (livesCount == 1)
		{
		    g.drawImage (hearts, 375, 5, null);
		}




		//************************ Pints the ball, the moving stick, and the net *******************

		// If the user chose the first team the baddle will be red
		if (userPick == 1)
		{
		    // sets the color of the moving stick
		    g.setColor (Color.red);

		    // paint the moving stick
		    g.fillRect (x_pos2, y_pos2, 100, 10);

		}

		// If the user chose the first team the baddle will be blue
		else if (userPick == 2)
		{
		    // sets the color of the moving stick
		    g.setColor (Color.BLUE);

		    // paint the moving stick
		    g.fillRect (x_pos2, y_pos2, 100, 10);
		}

		// If the user chose the first team the baddle will be yellow
		else if (userPick == 3)
		{
		    // sets the color of the moving stick
		    g.setColor (Color.YELLOW);

		    // paint the moving stick
		    g.fillRect (x_pos2, y_pos2, 100, 10);

		}


		// sets the color of the ball
		g.setColor (Color.red);

		// paint the red ball
		g.fillOval (x_pos - radius, y_pos - radius, 2 * radius, 2 * radius);

		// sets the color of the net
		g.setColor (Color.green);

		// paint the net
		g.fillRect (x_pos3 - radius, y_pos3 - radius, 100, 10);



		//*********************** Draws the Obstacles *************************************

		// sets the color of the net
		g.setColor (Color.black);

		// paint the first obstacle when the user's score is 3 or more
		if (counter >= 3)
		{
		    g.fillRect (x_pos5 - 420, y_pos5 - radius, 100, 10);
		}


		// paint the second obstacle when the user's score is 2 or more
		if (counter >= 7)
		{
		    g.fillRect (x_pos6 - 100, y_pos6 - radius, 100, 10);
		}


		// paint the last obstacle when the user's score is 1 or more
		if (counter >= 12)
		{
		    g.fillRect (x_pos4 - radius, y_pos4 - radius, 100, 10);

		}


		//************** Paints the Fake balls when the user's score is greater than 20 ***************
		g.setColor (Color.red);

		// If the user's score is 20 or above a fake ball is added
		if (counter >= 20)
		{
		    g.fillOval (x_pos7 - radius7, y_pos7 - radius7, 2 * radius7, 2 * radius7);
		}

		// If the user's score is 26 or above another fake ball is added
		if (counter >= 26)
		{
		    g.fillOval (x_pos8 - radius8, y_pos8 - radius8, 2 * radius8, 2 * radius8);
		}
	    }


	    // The Score Screen (When the User Loses All Their Lives)
	    if (startGame == true && livesCount == 0)
	    {
		g.drawImage (scorescreen, 0, 0, null);

		// Paints the heading on top of the screen
		g.setColor (Color.WHITE);
		g.setFont (new Font ("Ariel", 30, 45));
		g.drawString ("Game Over", 100, 80);

		// Prints out the User's Score
		g.setFont (new Font ("Ariel", 20, 30));
		g.drawString ("Score: " + counter, 20, 160);

		// Thanks the User's for Playing the Game
		g.setFont (new Font ("Ariel", 20, 30));
		g.drawString ("Thank you very much for  ", 20, 240);
		g.drawString ("trying our game, hope you", 20, 280);
		g.drawString ("enjoyed.", 20, 320);

		// Prints Out the Programer's names
		g.setFont (new Font ("Ariel", 20, 20));
		g.drawString ("BY: Mohammad Abu Fardeh & Abdulahad ", 20, 450);
		g.drawString ("BY: Mohammad Abu Fardeh & Abdulahad ", 20, 450);
		g.drawString ("Hussain", 20, 480);

	    }

	}


    } // paint method
} // HockeyDashV3 class


