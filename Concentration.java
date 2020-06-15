/*
Name: Nicole Han
Program Name: Concentration
Date: January 10, 2019
Teacher: Ms. Krasteva

Assignment: 
This program is a two-player card game, Concentration.
It begins with an animated splashscreen, and then proceeds
to the main menu where users can choose to view instructions,
select a level and play, view highscores, or exit. In the
game, users repeatedly select two cards to "flip", upon
which the selected cards will turn over and reveal the
design behind them. The goal for each user is to match
up as many pairs as possible.

Instance Variable Dictionary:
Name         |Type             |Purpose
-------------|-----------------|----------------
c            |Console          |to display output and graphics
menuChoice   |String           |to store the user's choice in main menu
levelChoice  |int              |to store the user's selection for which level they want to play / view highscores for
p1Score      |int              |used to count player 1's score
p2Score      |int              |used to count player 2's score
p1           |String           |used to store player 1's username
p2           |String           |used to store player 2's username
cardType     |2D int array     |used to store which card is at a certain position in the grid
exitToMenu   |boolean          |used to store if the user wishes to exit to the main menu
SCORE_FILES  |1D String array  |used to store the names of the files which score highscores
 */

import hsa.*; //gives access to the hsa class
import java.awt.*; //gives access to the awt class
import javax.swing.*; //gives access to the swing class
import java.io.*; //gives access to the io class

public class Concentration { //creates a new class called Concentration
    Console c; //instance variable of Console
    String menuChoice; //instance variable to store user's choice in main menu
    int levelChoice; //instance variable to store user's chose in level selection
    int p1Score; //instance variable to count player 1's score
    int p2Score; //instance variable to count player 2's score
    String p1; //instance variable to store player 1's username
    String p2; //instance variable to store player 2's username
    int[][] cardType; //instance variable to store the current card grid
    boolean exitToMenu; //instance variable to store if the user wishes to exit to main menu or not
    final String[] SCORE_FILES = {"highscores/easyScores.concentration", "highscores/mediumScores.concentration", "highscores/hardScores.concentration"}; //final variable to store the names of the highscore files

    /*
    Concentration class constructor
    
    Purpose:
    Concentration class constructor creates the Console to
    display graphics on, and initializes font data variables.
    
    Local Variable Dictionary:
    Name      |Type        |Purpose
    --------- |------------|------------
    e         |Exception   |used for catching Exceptions
    
    Segment Block:
    try-catch blocks are used when importing fonts 
    to catch any errors when processing fonts.
     */
    public Concentration() {
	//initializes instance variables
	p1Score = 0;
	p2Score = 0;
	p1 = "";
	p2 = "";
	exitToMenu = false;
	c = new Console(35,122,"Concentration"); //creates a new Console object with title
	try { //try catch block catches errors
	    //imports fonts
	    //citation: http://www.java2s.com/Tutorials/Java/Graphics_How_to/Font/Load_font_from_ttf_file.htm
	    Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/baby-boss.regular.otf")); //imports font from data folder
	    Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/rakesly el.ttf")); //imports font from data folder
	    Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/Hotplate(sRB).TTF")); //imports font from data folder
	} catch (Exception e) {
	}
    }


    /*
    title method
    
    Purpose:
    Used to draw the program title.
    
    Local Variable Dictionary:
    Name          |Type    |Purpose
    --------------|--------|------------
    blue          |Color   |stores blue colour data
    lightOrange   |Color   |stores light orange colour data
    orange        |Color   |stores orange colour data
     */
    private void title() {
	//local variables
	Color blue = new Color(206, 242, 245); //blue colour
	Color lightOrange = new Color(255, 219, 201); //light orange colour
	Color orange = new Color(242, 133, 82); //orange colour

	//draws background
	c.setColor(blue);
	c.fillRect(0, 0, 975, 700); //draws rectangle size of the console
	//draws title
	c.setFont(new Font("Hotplate (sRB)", Font.PLAIN, 40));
	c.setColor(lightOrange);
	c.drawString("CONCENTRATION", 320, 50);
	c.setColor(orange);
	c.drawString("CONCENTRATION", 322, 52);
    }

    /*
    pauseProgram method
    
    Purpose:
    Waits until user presses a character
    to continue the program.
     */
    private void pauseProgram() {
	c.setColor(Color.BLACK);
	c.setFont(new Font("RakeslyEl-Regular", Font.PLAIN, 15));
	c.drawString("< P R E S S   A N Y   K E Y   T O   C O N T I N U E >", 360, 585); //displays message to user to press a key
	c.getChar(); //waits until user presses a key to continue
    }

    /*
    splashscreen method
    
    Purpose:
    Creates new Splashscreen object to display
    the splashscreen for the program.
     */
    public void splashScreen() {
	Splashscreen b = new Splashscreen(c); //creates new Splashscreen object and passes c in
	b.run(); //calls method to display the splashscreen
    }

    /*
    mainMenu method
    
    Purpose:
    To display the options from main menu,
    to view instructions, select a level and
    play the game, view highscores for a 
    selected gameboard, or exit. Players
    choose their selection by pressing
    keys from A - D. Main menu is then
    followed by their selection.
    
    Local Variable Dictionary:
    Name     |Type   |Purpose
    ---------|-------|-----------
    yellow   |Color  |stores yellow colour data
    
    Loops:
    A while loop runs until the user
    presses one of the four selection keys.
    
    Conditional Statements:
    An if statement checks if the character
    inputted is equal to one of the four
    options by using equalsIgnoreCase().
     */
    public void mainMenu() {
	//local variable
	Color yellow = new Color(255, 242, 217); //yellow colour

	title(); //draws the program title and clears the previous screen
	c.setColor(yellow); //sets colour to be yellow
	//draws four buttons on the screen
	c.fillRoundRect(340, 135, 300, 50, 10, 10);
	c.fillRoundRect(340, 205, 300, 50, 10, 10);
	c.fillRoundRect(340, 275, 300, 50, 10, 10);
	c.fillRoundRect(340, 345, 300, 50, 10, 10);
	//draws title and menu options
	c.setColor(Color.BLACK); //sets colour to be black
	c.setFont(new Font("Baby Boss", Font.PLAIN, 25));
	c.drawString("MAIN MENU", 435, 120); //draws title
	c.setFont(new Font("RakeslyEl-Regular", Font.PLAIN, 20));
	c.drawString("a )   i n s t r u c t i o n s", 410, 163);
	c.drawString("b )   l e v e l   s e l e c t", 410, 233);
	c.drawString("c )   h i g h   s c o r e s", 410, 303);
	c.drawString("d )   q u i t   g a m e", 410, 373);
	//loop that runs until one of the menu options is pressed
	while (true) {
	    menuChoice = "" + c.getChar(); //gets character the user presses
	    if (menuChoice.equalsIgnoreCase("A") || menuChoice.equalsIgnoreCase("B") || menuChoice.equalsIgnoreCase("C") || menuChoice.equalsIgnoreCase("D")) { //checks if it is an option
		break; //exits loop if user selected an option
	    }
	}
    }

    /*
    instructions method
    
    Purpose:
    Displays the instructions for the game.
    Displays the 32 different cards in the
    game if the users chooses to view them.
    
    Local Variable Dictionary:
    Name     |Type          |Purpose
    ---------|--------------|-----------
    input    |String        |stores the key the user entered
    cur      |int           |stores the current card being displayed
    blue     |Color         |stores blue colour data
    names    |String array  |stores the names for the animals on the cards

    Loops:
    Two different while loops, each running
    until user presses one of the current options.
    
    Conditional Statements:
    - If-else statements inside the while loops check
      if the user has pressed one of the options.
    - An if statement checks if the user has
      pressed 'A', meaning that they want to view
      the cards, and displays accordingly.
    - An if-else statement checks the current card
      number to display coordinates correctly.
     */
    public void instructions() {
	//local variables
	String input = ""; //variable to temporarily store the user's input
	int cur = 0; //stores the current card being displayed
	Color blue = new Color(206, 242, 245); //blue colour
	String[] names = {"C A T", "B I R D", "F I S H", "B U T T E R F L Y", "B E E", "D U C K", "G I R A F F E", "T U R T L E", "S N A K E", "F R O G", "P I G", "W A L R U S", "D O G", "S N A I L", "O P O S S U M", "S Q U I R R E L", "L I O N", "S P I D E R", "K O A L A", "P E N G U I N", "S E A L", "R A B B I T", "O W L", "P A N D A", "E L E P H A N T", "R A C C O O N", "L A D Y B U G", "T O U C A N", "J E L L Y F I S H", "D O L P H I N", "M O N K E Y", "R H I N O"};

	levelChoice = 2; //sets current size to draw the displayed cards
	title(); //draws the program title and clears the previous screen
	c.setColor(Color.BLACK); //sets colour to be black
	//draws title and instructions
	c.setFont(new Font("Baby Boss", Font.PLAIN, 20));
	c.drawString("INSTRUCTIONS", 420, 120);
	c.setFont(new Font("RakeslyEl-Regular", Font.PLAIN, 17));
	c.drawString("C o n c e n t r a t i o n   i s   a   t w o   p l a y e r - g a m e .   G i v e n   a", 295, 160);
	c.drawString("g r i d   o f   c a r d s ,   p l a y e r s   w i l l   t a k e   t u r n s   c h o o s i n g", 285, 185);
	c.drawString("t w o   c a r d s   a t   a   t i m e .   O n   t h e   b a c k   o f   e a c h   c a r d   i s", 275, 210);
	c.drawString("a   d e s i g n ,   a n d   e a c h   d e s i g n   w i l l   b e   h i d d e n   t w i c e   i n", 280, 235);
	c.drawString("t h e   g r i d .   O n c e   y o u   f l i p   t w o   o f   t h e   s a m e   c a r d ,   t h e y", 270, 260);
	c.drawString("w i l l   r e m a i n   f a c e   u p .   Y o u r   g o a l   i s   t o   m a t c h   u p   t h e", 280, 285);
	c.drawString("s a m e   c a r d s   b y   t r y i n g   t o   r e m e m b e r   t h e i r   p o s i t i o n   a s", 260, 310);
	c.drawString("y o u   p l a y .   S c o r e   i s   k e p t   t r a c k   b y   h o w   m a n y   m a t c h e s", 265, 335);
	c.drawString("e a c h   p l a y e r   h a s   c o r r e c t l y   m a d e .   C h o o s e   w i s e l y !", 285, 360);
	c.drawString("P r e s s   ' A '   t o   v i e w   t h e   c a r d s .   P r e s s   ' B '   t o   e x i t   t o   m a i n   m e n u .", 235, 410);
	//loop that runs until one of the menu options is pressed
	while (true) {
	    input = "" + c.getChar(); //gets the character pressed
	    if (input.equalsIgnoreCase("A") || input.equalsIgnoreCase("B")) { //checks if it is an option
		break; //exits loop if user selected an option
	    }
	}
	if (input.equalsIgnoreCase("A")) { //user wishes to view the cards
	    //clears previous text to display new text
	    c.setColor(blue);
	    c.fillRect(0, 60, 975, 640);
	    //display information and options for viewing different cards
	    c.setFont(new Font("RakeslyEl-Regular", Font.PLAIN, 40));
	    c.setColor(Color.BLACK);
	    c.drawString("<", 975 / 2 - (getSize(levelChoice, 78) - 6) / 2 - 10, 350);
	    c.drawString(">", 975 / 2 + (getSize(levelChoice, 78) - 6) / 2 - 10, 350);
	    c.setFont(new Font("RakeslyEl-Regular", Font.PLAIN, 13));
	    c.drawString("P r e s s   ' A '", 975 / 2 - (getSize(levelChoice, 78) - 6) / 2 - 30, 360);
	    c.drawString("P r e s s   ' D '", 975 / 2 + (getSize(levelChoice, 78) - 6) / 2 - 20, 360);
	    c.drawString("E X I T", 475, 340);
	    c.drawString("P r e s s   ' E '", 465, 360);
	    c.setFont(new Font("RakeslyEl-Regular", Font.PLAIN, 20));
	    while (true) {
		//covers previous card number
		c.setColor(blue);
		c.fillRect(0, 390, 975, 210);
		c.setColor(Color.BLACK);
		c.drawString("/   3 2", 485, 420);
		//displays new card number
		if (cur > 9) {
		    c.drawString((cur + 1) / 10 + " " + (cur + 1) % 10, 453, 420);
		} else {
		    c.drawString("" + (cur + 1), 463, 420);
		}
		c.drawString(names[cur], (int) (975 / 2 - (names[cur].length() * 20) / 6.3), 450); //displays the animal name on the current card
		drawCard(cur + 1, 975 / 2 - (getSize(levelChoice, 78) - 6) / 2, 100, 3); //draws the card
		input = "" + c.getChar(); //stores the character entered by the user
		//if-statement to check input
		if (input.equalsIgnoreCase("A")) { //user wishes to view previous card
		    cur--;
		    cur += 32;
		    cur %= 32;
		} else if (input.equalsIgnoreCase("D")) { //user wishes to view next card
		    cur++;
		    cur %= 32;
		} else if (input.equalsIgnoreCase("E")) { //user wishes to exit
		    break; //exit loop
		}
	    }
	}
    }

    /*
    levelSelect method
    
    Purpose:
    Allows user to select which level they
    want to play or view highscores for.
    
    Local Variable Dictionary:
    Name     |Type    |Purpose
    ---------|--------|-----------
    yellow   |Color   |stores yellow colour data
    input    |String  |temporarily stores the user's input
    
    Loops:
    A while loop runs until the user
    presses one of the four selection keys.
    
    Conditional Statements:
    An if statement checks if the character
    inputted is equal to one of the four
    options and assigning the size value
    to the levelChoice variable.
     */
    public void levelSelect() {
	//local variables
	Color yellow = new Color(255, 242, 217); //yellow colour
	String input = ""; //variable to temporarily store the user's input

	title(); //draws the program title and clears the previous screen
	c.setColor(yellow); //sets colour to be yellow
	//draws four buttons on the screen
	c.fillRoundRect(340, 135, 300, 50, 10, 10);
	c.fillRoundRect(340, 205, 300, 50, 10, 10);
	c.fillRoundRect(340, 275, 300, 50, 10, 10);
	c.fillRoundRect(340, 345, 300, 50, 10, 10);
	c.setColor(Color.BLACK); //sets colour to be black
	//draws title depending on current state and options
	c.setFont(new Font("Baby Boss", Font.PLAIN, 25));
	if (menuChoice.equalsIgnoreCase("B")) { //method is before the game screen
	    c.drawString("LEVEL SELECTION", 420, 120);
	} else { //method is before the score board display
	    c.drawString("SCORE BOARD SELECTION", 390, 120);
	}
	c.setFont(new Font("RakeslyEl-Regular", Font.PLAIN, 20));
	c.drawString("a )   e a s y   ( 4 x 4 )", 405, 163);
	c.drawString("b )   m e d i u m   ( 6 x 6 )", 405, 233);
	c.drawString("c )   h a r d   ( 8 x 8 )", 405, 303);
	c.drawString("d )   e x i t   t o   m e n u", 400, 373);
	//loop that runs until user enters one of the four options
	while (true) {
	    input = "" + c.getChar();
	    if (input.equalsIgnoreCase("A")) { //user selected option A, grid size is 4
		levelChoice = 4; //sets the value for levelChoice
		break; //exits the loop
	    } else if (input.equalsIgnoreCase("B")) { //user selected option B, grid size is 6
		levelChoice = 6; //sets the value for levelChoice
		break; //exits the loop
	    } else if (input.equalsIgnoreCase("C")) { //user selected option C, grid size is 8
		levelChoice = 8; //sets the value for levelChoice
		break; //exits the loop
	    } else if (input.equalsIgnoreCase("D")) { //user selected to exit to menu
		exitToMenu = true; //signals to return to main menu
		break; //exits the loop
	    }
	}
    }

    /*
    userInput method
    
    Purpose:
    Allows users to input the player usernames.
    
    Local Variable Dictionary:
    Name     |Type        |Purpose
    ---------|------------|-----------
    pos      |int         |stores current position user is typing in
    player   |int         |loop counter variable
    user     |String      |stores currently entered player username
    input    |char        |stores current key pressed by the user
    e        |Exception   |used for catching Exceptions

    
    Loops:
    - A for loop runs twice to allow username
      input for both player 1 and player 2.
    - A while loop runs until user enters a
      valid username.
    
    Conditional Statements:
    - An if-statement checks if current username
      typing position is in range.
    - An if-else statment checks the ASCII of the
      currently entered character and processes
      it accordingly.
    - If statements are used to check if the
      currently entered username is valid (in the
      correct length range, not equal to other
      username, and only contains alphanumeric
      characters).
    - An if statement checks if user is able to
      press the backspace key.
    
    Segment Block:
    A try-catch catches any IllegalArgumentExceptions
    when the user presses enter for their username.
     */
    public void userInput() {
	//local variables
	int pos = 0; //stores the current position the user is typing in
	int player; //loop counter variable
	String user = ""; //stores the currently entered username
	char input; //stores key pressed by the user

	title(); //draws the program title and clears the previous screen
	//displays the title
	c.setColor(Color.BLACK);
	c.setFont(new Font("Baby Boss", Font.PLAIN, 25));
	c.drawString("USER INPUT", 430, 120);
	//displays the prompts
	c.setFont(new Font("RakeslyEl-Regular", Font.PLAIN, 17));
	c.drawString("E n t e r   ' e x i t '   t o   r e t u r n   t o   m a i n   m e n u.", 310, 160);
	c.drawString("P l e a s e   e n t e r   p l a y e r   1 ' s   u s e r n a m e .", 320, 210);
	c.drawString("P l e a s e   e n t e r   p l a y e r   2 ' s   u s e r n a m e .", 320, 330);
	//draws the boxes for the user to type in
	c.setColor(Color.WHITE);
	c.fillRoundRect(260, 230, 450, 40, 10, 10);
	c.fillRoundRect(260, 350, 450, 40, 10, 10);
	//receives, stores, and processes input
	for (player = 0; player < 2; player++) { //runs twice, one for each player
	    pos = 0; //stores the current position the user is typing in
	    user = ""; //stores the currently entered username
	    while (true) { //loop which runs until the user is finished entering their username
		c.setColor(Color.BLACK);
		if (pos <= 29) { //only displays the line for typing if user is within the range
		    c.drawLine(280 + pos * 14, 260 + player * 120, 290 + pos * 14, 260 + player * 120);
		}
		input = c.getChar(); //receives and stores the entered character
		if (input == 10) { //user has pressed the ENTER key, with ASCII 10
		    if (user.equalsIgnoreCase("exit")) { //user has entered "exit", meaning they want to return to main menu
			exitToMenu = true; //signals to return to main menu
			break; //exits loop
		    }
		    try { //try-catch block to catch errors
			if (user.length() == 0 || user.length() > 16) { //username length is too short or too long
			    throw new IllegalArgumentException("The length of your username must be between 1 and 16 characters, inclusive."); //throws error that username is not in range
			}
			if (player == 1 && p1.equals(user)) { //username is equal to player 1's username
			    throw new IllegalArgumentException("Usernames cannot be the same."); //throws error that usernames cannot be the same
			}
			for (int i = 0; i < user.length(); i++) { //checks the characters the username contains, only accepting alphanumeric characters
			    if ((user.charAt(i) >= 'a' && user.charAt(i) <= 'z') || (user.charAt(i) >= 'A' && user.charAt(i) <= 'Z') || (user.charAt(i) >= '0' && user.charAt(i) <= '9')) { //current character is alphanumeric
				continue;
			    }
			    throw new IllegalArgumentException("Usernames must only contain alphabetical characters and numbers."); //throws error that username contains non-alphanumeric characters
			}
			//covers over currently drawn line
			c.setColor(Color.WHITE); //sets colour to be white
			c.drawLine(280 + pos * 14, 260 + player * 120, 290 + pos * 14, 260 + player * 120);
			break;
		    } catch (IllegalArgumentException e) { //catches errors and displays them using JOptionPane
			JOptionPane.showMessageDialog(null, e.getMessage() + " Please try again.", "[ ERROR ]", JOptionPane.ERROR_MESSAGE); //displays the error
			pos = 0; //re-sets current position
			user = ""; //re-sets currently entered username
			//re-draws box
			c.setColor(Color.WHITE); //sets colour to be white
			c.fillRoundRect(260, 230 + player * 120, 450, 40, 10, 10);
		    }
		} else { //user has pressed a key other than the ENTER key
		    if (input == 8 && pos > 0) { //user has pressed the BACKSPACE key and current box is not empty, with ASCII 08
			//covers over previously entered character
			c.setColor(Color.WHITE);
			c.drawLine(280 + pos * 14, 260 + player * 120, 290 + pos * 14, 260 + player * 120);
			pos--; //shifts position back by one
			c.fillRect(275 + pos * 14, 230 + player * 120, 20, 40);
			user = user.substring(0, user.length() - 1); //shortens string by one
		    } else if (input != 8 && pos <= 29) { //user has not pressed BACKSPACE and string has not exceeded the box's length
			user += input; //adds key pressed by the user
			c.drawString("" + input, 282 + pos * 14, 255 + player * 120); //displays what has been typed so far
			//covers over current line
			c.setColor(Color.WHITE);
			c.drawLine(280 + pos * 14, 260 + player * 120, 290 + pos * 14, 260 + player * 120);
			pos++; //shifts position up by one
		    }
		}
	    }
	    if (player == 0) { //currently entering for the first player
		p1 = user; //stores entered username in the first player's variable
	    } else { //currently entering for the second player
		p2 = user; //stores entered username in the second player's variable
	    }
	    if (exitToMenu) { //user has entered "exit", exits the loop to return to main menu
		break;
	    }
	}
    }

    /*
    getSize method
    
    Purpose: 
    Converts the size for graphics on a currarent
    level game based on the ratio between it and
    the main game board (size 4x4).
    
    Parameters / Local Variable Dictionary:
    Name         |Type    |Purpose
    -------------|--------|-----------
    levelChoice  |int     |passed into the method, stores the current choice for the level
    origSize     |int     |passed into the method, stores the current size to be converted
    
    Return:
    Returns an integer, the value after the origSize
    variable has been converted.
     */
    private int getSize(int levelChoice, int origSize) {
	return (int) (1.4 * (4.0 / levelChoice) * origSize); //returns the current coordinate, ratioed based on the levelSize variable, used 1.4 to convert from mac window size to windows window size
    }

    /*
    randCard method
    
    Purpose:
    Used to generate a randomly positioned card grid.
    
    Local Variable Dictionary:
    Name      |Type            |Purpose
    ----------|----------------|-----------
    card      |int             |used to store currently randomly generated card
    taken     |boolean array   |used to store if a card has been generated or not
    count     |int             |loop counter variable 
    num       |int             |loop counter variable
    x         |int             |used to store randomly generated column for the card
    y         |int             |used to store randomly generated row for the card
    
    Loops:
    - A loop runs to generate enough cards for the
      current level size.
    - A while loop runs until a new random card
      has been determined to generate position for.
    - A for loop runs twice, to generate two
      positions for each card.
    - A while loop runs until a valid position for
      current card is created.
      
    Conditional Statements:
    - An if statement checks if currently randomly
      generated card has already been generated.
    - An if statement checks if currently generated
      position for the card has already been taken.
     */
    private void randCard() {
	//local variables
	int card = 0; //used to store the currently generated card
	boolean[] taken = new boolean[33]; //variable to store if a current card had been generated or not
	int count; //loop counter variable
	int num; //loop counter variable
	int x; //used to store randomly generated card position
	int y; //used to store randomly generated card position

	//for loop which runs to generate enough cards for current level
	for (count = 1; count <= (levelChoice * levelChoice) / 2; count++) { //generates enough cards for the current game level
	    while (true) { //loop that runs until a card that has not been previously generated is generated
		card = (int) (Math.random() * 32) + 1; //generates a random card number
		if (!taken[card]) { //if card is not currently taken, take it
		    taken[card] = true; //sets the value in the boolean to be true
		    break; //exits the loop
		}
	    }
	    for (num = 0; num < 2; num++) { //loop runs twice to generate two positions for each card
		while (true) { //runs until a valid random position is generated
		    //generates two coordinates in the grid to place the card
		    x = (int) (Math.random() * (levelChoice));
		    y = (int) (Math.random() * (levelChoice));
		    //checks if current position in the grid is empty
		    if (cardType[x][y] == 0) {
			cardType[x][y] = card; //sets current position to be the card
			break; //exits the loop
		    }
		}
	    }
	}
    }

    /*
    drawCard method
    
    Purpose:
    Used to draw the graphics for a
    currently randomly generated card.
    
    Local Variable Dictionary:
    Name                          |Type     |Purpose
    ------------------------------|---------|------------------------
    darkOrangeRed                 |Color    |used to store dark orange red colour data
    orangeRed                     |Color    |used to store orange red colour data
    lightOrangeRed                |Color    |used to store light orange red colour data
    brightRed                     |Color    |used to store bright red colour data
    brightYellow                  |Color    |used to store bright yellow colour data
    lightOrange                   |Color    |used to store light orange colour data
    brightOrange                  |Color    |used to store bright orange colour data
    orange                        |Color    |used to store orange colour data
    orangeYellow                  |Color    |used to store orange yellow colour data
    paleBlue                      |Color    |used to store pale blue colour data
    darkOrangeYellow              |Color    |used to store dark orange yellow colour data
    lightOrangeYellow             |Color    |used to store light orange yellow colour data
    strongOrange                  |Color    |used to store strong orange colour data
    brownYellow                   |Color    |used to store brown yellow colour data
    lightBrownYellow              |Color    |used to store light brown yellow colour data
    paleBrownYellow               |Color    |used to store pale brown yellow colour data
    paleBrown                     |Color    |used to store pale brown colour data
    darkPaleBrown                 |Color    |used to store dark pale brown colour data
    lightBrightGreen              |Color    |used to store light bright green colour data
    brightGreen                   |Color    |used to store bright green colour data
    darkBrightGreen               |Color    |used to store dark bright green colour data
    strongRed                     |Color    |used to store strong red colour data
    yellowGreen                   |Color    |used to store yellow green colour data
    darkPaleGreen                 |Color    |used to store dark pale green colour data
    paleGreen                     |Color    |used to store pale green colour data
    lightPaleGreen                |Color    |used to store light pale green colour data
    darkPink                      |Color    |used to store dark pink colour data
    strongDarkPink                |Color    |used to store strong dark pink colour data
    pink                          |Color    |used to store pink colour data
    lightPink                     |Color    |used to store light pink colour data
    redBrown                      |Color    |used to store red brown colour data
    darkRedBrown                  |Color    |used to store dark red brown colour data
    darkYellowBrown               |Color    |used to store dark yellow brown colour data
    strongDarkYellowBrown         |Color    |used to store strong dark yellow brown colour data
    yellowBrown                   |Color    |used to store yellow brown colour data
    lightYellowBrown              |Color    |used to store light yellow brown colour data
    palePink                      |Color    |used to store pale pink colour data
    lightBrownGray                |Color    |used to store light brown gray colour data
    darkBrownGray                 |Color    |used to store dark brown gray colour data
    paleGray                      |Color    |used to store pale gray colour data
    paleLightBrownOrange          |Color    |used to store pale light brown orange colour data
    brownOrange                   |Color    |used to store brown orange colour data
    lightBrownOrange              |Color    |used to store light brown orange colour data
    lightBrown                    |Color    |used to store light brown colour data
    brown                         |Color    |used to store brown colour data
    lightBlack                    |Color    |used to store light black colour data
    lightGray                     |Color    |used to store light gray colour data
    whiteTransparent              |Color    |used to store white transparent colour data
    gray                          |Color    |used to store gray colour data
    white                         |Color    |used to store white colour data
    paleOrange                    |Color    |used to store pale orange colour data
    paleWhite                     |Color    |used to store pale white colour data
    grayYellow                    |Color    |used to store gray yellow colour data
    whiteYellow                   |Color    |used to store white yellow colour data
    strongBrown                   |Color    |used to store strong brown colour data
    lightDarkBrown                |Color    |used to store light dark brown colour data
    lightStrongBrown              |Color    |used to store light strong brown colour data
    strongOrangeYellow            |Color    |used to store strong orange yellow colour data
    paleDarkBrown                 |Color    |used to store pale dark brown colour data
    darkDarkBrown                 |Color    |used to store dark dark brown colour data
    blackBrown                    |Color    |used to store black brown colour data
    orangeWhite                   |Color    |used to store orange white colour data
    darkGrayOrange                |Color    |used to store dark gray orange colour data
    grayOrange                    |Color    |used to store gray orange colour data
    lightGrayOrange               |Color    |used to store light gray orange colour data
    paleGrayOrange                |Color    |used to store pale gray orange colour data
    brownOrangeRed                |Color    |used to store brown orange red colour data
    paleOrangeGray                |Color    |used to store pale orange gray colour data
    lightOrangeGray               |Color    |used to store light orange gray colour data
    brightOrangeGray              |Color    |used to store bright orange gray colour data
    darkOrangeGray                |Color    |used to store dark orange gray colour data
    pinkRed                       |Color    |used to store pink red colour data
    grayRed                       |Color    |used to store gray red colour data
    brightBrownOrange             |Color    |used to store bright brown orange colour data
    darkPurple                    |Color    |used to store dark purple colour data
    purple                        |Color    |used to store purple colour data
    lightPurple                   |Color    |used to store light purple colour data
    purplePink                    |Color    |used to store purple pink colour data
    paleYellowOrange              |Color    |used to store pale yellow orange colour data
    paleBlack                     |Color    |used to store pale black colour data
    brownGray                     |Color    |used to store brown gray colour data
    strongBrownGray               |Color    |used to store strong brown gray colour data
    brightBrownGray               |Color    |used to store bright brown gray colour data
    paleBrownGray                 |Color    |used to store pale brown gray colour data
    playerRed                     |Color    |used to store player red colour data
    playerYellow                  |Color    |used to store player yellow colour data
    playerPurple                  |Color    |used to store player purple colour data
    blackTransparent              |Color    |used to store black transparent colour data
    i                             |int      |used in counting in loops
    j                             |int      |used in counting in loops
    side                          |int array|used to store size of current card
    xpos                          |int array|used in drawing polygons
    ypos                          |int array|used in drawing polygons
    
    Loops:
    - A loop used to draw the bee's body.
    - A loop used to draw the snake body.
    - A loop used to draw the rings on the snail's shell.
    - A loop used to draw the tentacles of the jellyfish.
    - A loop used to draw the body and fins on the dolphin.
     
    Conditional Statements:
    - An if-else statement used to determine which segment
      of code to run based on which card to draw.
    - An if-else statement used to determine which colour
      to use when drawing the bee's body.
    - An if-statment to aid in drawing the rings of
      the snake's body.
    - An if-statement to aid in drawing the eyes
      on the snail.
    - An if-statement to determine which colour
      border to draw around the card.
     */
    private void drawCard(int card, int x, int y, int player) {
	//local variables
	int i = 0; //loop variable
	int j = 0; //loop variable
	int side = getSize(levelChoice, 78) - 6; //calculates the side of the current card
	int[] xpos; //polygon coordinate variables
	int[] ypos; //polygon coordinate variables
	Color darkOrangeRed = new Color(214, 141, 81); //stores dark orange red colour data
	Color orangeRed = new Color(224, 175, 110); //stores orange red colour data
	Color lightOrangeRed = new Color(245, 191, 120); //stores light orange red colour data
	Color brightRed = new Color(245, 51, 51); //stores bright red colour data
	Color brightYellow = new Color(252, 237, 174); //stores bright yellow colour data
	Color lightOrange = new Color(252, 154, 109); //stores light orange colour data
	Color brightOrange = new Color(242, 121, 65); //stores bright orange colour data
	Color orange = new Color(255, 164, 115); //stores orange colour data
	Color orangeYellow = new Color(255, 226, 112); //stores orange yellow colour data
	Color paleBlue = new Color(214, 255, 253); //stores pale blue colour data
	Color darkOrangeYellow = new Color(245, 201, 64); //stores dark orange yellow colour data
	Color lightOrangeYellow = new Color(255, 211, 74); //stores light orange yellow colour data
	Color strongOrange = new Color(255, 131, 64); //stores strong orange colour data
	Color brownYellow = new Color(217, 196, 139); //stores brown yellow colour data
	Color lightBrownYellow = new Color(245, 228, 181); //stores light brown yellow colour data
	Color paleBrownYellow = new Color(222, 202, 146); //stores pale brown yellow colour data
	Color paleBrown = new Color(171, 137, 87); //stores pale brown colour data
	Color darkPaleBrown = new Color(130, 108, 75); //stores dark pale brown colour data
	Color lightBrightGreen = new Color(105, 145, 113); //stores light bright green colour data
	Color brightGreen = new Color(87, 128, 94); //stores bright green colour data
	Color darkBrightGreen = new Color(60, 94, 66); //stores dark bright green colour data
	Color strongRed = new Color(232, 70, 70); //stores strong red colour data
	Color yellowGreen = new Color(93, 130, 78); //stores yellow green colour data
	Color darkPaleGreen = new Color(169, 209, 146); //stores dark pale green colour data
	Color paleGreen = new Color(168, 214, 141); //stores pale green colour data
	Color lightPaleGreen = new Color(205, 245, 181, 100); //stores light pale green colour data
	Color darkPink = new Color(232, 165, 191); //stores dark pink colour data
	Color strongDarkPink = new Color(122, 82, 98); //stores strong dark pink colour data
	Color pink = new Color(245, 179, 205); //stores pink colour data
	Color lightPink = new Color(250, 200, 220); //stores light pink colour data
	Color redBrown = new Color(117, 87, 80); //stores red brown colour data
	Color darkRedBrown = new Color(59, 39, 35); //stores dark red brown colour data
	Color darkYellowBrown = new Color(179, 140, 82); //stores dark yellow brown colour data
	Color strongDarkYellowBrown = new Color(110, 86, 50); //stores strong dark yellow brown colour data
	Color yellowBrown = new Color(201, 160, 99); //stores yellow brown colour data
	Color lightYellowBrown = new Color(240, 198, 134); //stores light yellow brown colour data
	Color palePink = new Color(250, 199, 187); //stores pale pink colour data
	Color lightBrownGray = new Color(191, 178, 164); //stores light brown gray colour data
	Color darkBrownGray = new Color(89, 79, 69); //stores dark brown gray colour data
	Color paleGray = new Color(184, 178, 173); //stores pale gray colour data
	Color paleLightBrownOrange = new Color(219, 191, 156); //stores pale light brown orange colour data
	Color brownOrange = new Color(161, 135, 103); //stores brown orange colour data
	Color lightBrownOrange = new Color(176, 149, 116); //stores light brown orange colour data
	Color lightBrown = new Color(232, 180, 135); //stores light brown colour data
	Color brown = new Color(184, 123, 70); //stores brown colour data
	Color lightBlack = new Color(51, 51, 51); //stores light black colour data
	Color lightGray = new Color(184, 177, 173); //stores light gray colour data
	Color whiteTransparent = new Color(255, 255, 255, 100); //stores white transparent colour data
	Color gray = new Color(163, 154, 149); //stores gray colour data
	Color white = new Color(255, 255, 255, 200); //stores white colour data
	Color paleOrange = new Color(214, 169, 126); //stores pale orange colour data
	Color paleWhite = new Color(240, 237, 230); //stores pale white colour data
	Color grayYellow = new Color(219, 217, 208); //stores gray yellow colour data
	Color whiteYellow = new Color(245, 243, 233); //stores white yellow colour data
	Color strongBrown = new Color(138, 124, 101); //stores strong brown colour data
	Color lightDarkBrown = new Color(189, 180, 166); //stores light dark brown colour data
	Color lightStrongBrown = new Color(138, 124, 101); //stores light strong brown colour data
	Color strongOrangeYellow = new Color(209, 155, 79); //stores strong orange yellow colour data
	Color paleDarkBrown = new Color(138, 124, 101); //stores pale dark brown colour data
	Color darkDarkBrown = new Color(117, 105, 84); //stores dark dark brown colour data
	Color blackBrown = new Color(64, 59, 50); //stores black brown colour data
	Color orangeWhite = new Color(242, 239, 235); //stores orange white colour data
	Color darkGrayOrange = new Color(122, 119, 115); //stores dark gray orange colour data
	Color grayOrange = new Color(148, 143, 136); //stores gray orange colour data
	Color lightGrayOrange = new Color(158, 154, 149); //stores light gray orange colour data
	Color paleGrayOrange = new Color(173, 169, 163); //stores pale gray orange colour data
	Color brownOrangeRed = new Color(191, 159, 136); //stores brown orange red colour data
	Color paleOrangeGray = new Color(156, 150, 148); //stores pale orange gray colour data
	Color lightOrangeGray = new Color(189, 182, 179); //stores light orange gray colour data
	Color brightOrangeGray = new Color(237, 232, 230); //stores bright orange gray colour data
	Color darkOrangeGray = new Color(61, 59, 58); //stores dark orange gray colour data
	Color pinkRed = new Color(237, 81, 81); //stores pink red colour data
	Color grayRed = new Color(194, 194, 194); //stores gray red colour data
	Color brightBrownOrange = new Color(240, 121, 53); //stores bright brown orange colour data
	Color darkPurple = new Color(99, 81, 107); //stores dark purple colour data
	Color purple = new Color(168, 145, 179); //stores purple colour data
	Color lightPurple = new Color(201, 178, 212); //stores light purple colour data
	Color purplePink = new Color(212, 186, 194); //stores purple pink colour data
	Color paleYellowOrange = new Color(224, 203, 182); //stores pale yellow orange colour data
	Color paleBlack = new Color(60, 60, 60); //stores pale black colour data
	Color blackTransparent = new Color(0, 0, 0, 100); //stores black transparent colour data
	Color brownGray = new Color(120, 115, 111); //stores brown gray colour data
	Color strongBrownGray = new Color(99, 95, 91); //stores strong brown gray colour data
	Color brightBrownGray = new Color(140, 135, 132); //stores bright brown gray colour data
	Color paleBrownGray = new Color(158, 151, 147); //stores pale brown gray colour data
	Color playerYellow = new Color(242, 203, 124);  //stores player yellow colour data
	Color playerRed = new Color(252, 184, 184); //stores player red colour data
	Color playerPurple = new Color(217, 200, 230); //stores player purple colour data

	//draws the background for the card
	c.setColor(new Color(89, 184, 189));
	c.fillRect(x, y, side, side);
	//if statement to draw current card
	if (card == 1) { //draws a cat
	    c.setColor(orangeRed);
	    c.fillArc(x + getSize(levelChoice, 23), y + getSize(levelChoice, 30), side - 2 * getSize(levelChoice, 23), 2 * (side - getSize(levelChoice, 30)), 0, 180); //draws the body
	    c.setColor(lightOrangeRed); //light orange-red
	    c.fillOval(x + getSize(levelChoice, 20), y + getSize(levelChoice, 15), side - 2 * getSize(levelChoice, 20), getSize(levelChoice, 25)); //draws the head
	    //draws the ears
	    xpos = new int[]{x + getSize(levelChoice, 20), x + getSize(levelChoice, 35), x + getSize(levelChoice, 23)};
	    ypos = new int[]{y + getSize(levelChoice, 6), y + getSize(levelChoice, 16), y + getSize(levelChoice, 20)};
	    c.fillPolygon(xpos, ypos, 3);
	    xpos = new int[]{x + side - getSize(levelChoice, 20), x + side - getSize(levelChoice, 35), x + side - getSize(levelChoice, 23)};
	    ypos = new int[]{y + getSize(levelChoice, 6), y + getSize(levelChoice, 16), y + getSize(levelChoice, 20)};
	    c.fillPolygon(xpos, ypos, 3);
	    c.setColor(Color.BLACK);
	    //draws the eyes
	    c.fillOval(x + getSize(levelChoice, 28), y + getSize(levelChoice, 25), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    c.fillOval(x + side - getSize(levelChoice, 28) - getSize(levelChoice, 3), y + getSize(levelChoice, 25), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    c.setColor(darkOrangeRed);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 6) / 2, y + getSize(levelChoice, 30), getSize(levelChoice, 6), getSize(levelChoice, 3)); //draws the nose
	} else if (card == 2) { //draws a bird
	    c.setColor(brightRed);
	    c.fillOval(x + getSize(levelChoice, 20), y + getSize(levelChoice, 20), getSize(levelChoice, 17), getSize(levelChoice, 20)); //draws the head
	    c.fillRoundRect(x + getSize(levelChoice, 25), y + getSize(levelChoice, 30), getSize(levelChoice, 17), getSize(levelChoice, 23), getSize(levelChoice, 25), getSize(levelChoice, 25)); //draws the body
	    //draws the wing
	    xpos = new int[]{x + getSize(levelChoice, 35), x + getSize(levelChoice, 55), x + getSize(levelChoice, 38)};
	    ypos = new int[]{y + getSize(levelChoice, 28), y + getSize(levelChoice, 42), y + getSize(levelChoice, 53)};
	    c.fillPolygon(xpos, ypos, 3);
	    //draws the feathers on the head
	    xpos = new int[]{x + getSize(levelChoice, 25), x + getSize(levelChoice, 40), x + getSize(levelChoice, 35)};
	    ypos = new int[]{y + getSize(levelChoice, 20), y + getSize(levelChoice, 15), y + getSize(levelChoice, 25)};
	    c.fillPolygon(xpos, ypos, 3);
	    //draws the eye-BLACK
	    c.setColor(Color.black);
	    c.fillArc(x + getSize(levelChoice, 20), y + getSize(levelChoice, 27), getSize(levelChoice, 16), getSize(levelChoice, 10), 70, 200);
	    //draws the beak
	    c.setColor(brightYellow);
	    xpos = new int[]{x + getSize(levelChoice, 20), x + getSize(levelChoice, 25), x + getSize(levelChoice, 25), x + getSize(levelChoice, 13)};
	    ypos = new int[]{y + getSize(levelChoice, 28), y + getSize(levelChoice, 30), y + getSize(levelChoice, 34), y + getSize(levelChoice, 32)};
	    c.fillPolygon(xpos, ypos, 4);
	    //draws the leg
	    c.fillRect(x + getSize(levelChoice, 36), y + getSize(levelChoice, 53), getSize(levelChoice, 2), getSize(levelChoice, 7));
	    c.fillRect(x + getSize(levelChoice, 33), y + getSize(levelChoice, 58), getSize(levelChoice, 3), getSize(levelChoice, 2));
	    //draws the eye
	    c.setColor(Color.WHITE);
	    c.drawOval(x + getSize(levelChoice, 25), y + getSize(levelChoice, 28), getSize(levelChoice, 3), getSize(levelChoice, 3));
	} else if (card == 3) { //draws a fish
	    //draws fins
	    c.setColor(lightOrange);
	    xpos = new int[]{x + getSize(levelChoice, 20), x + getSize(levelChoice, 40), x + getSize(levelChoice, 35)};
	    ypos = new int[]{y + getSize(levelChoice, 28), y + getSize(levelChoice, 28), y + getSize(levelChoice, 17)};
	    c.fillPolygon(xpos, ypos, 3);
	    c.setColor(brightOrange);
	    //draws the body
	    c.fillOval(x + getSize(levelChoice, 10), y + getSize(levelChoice, 25), side - 2 * getSize(levelChoice, 10) - getSize(levelChoice, 15), side - 2 * getSize(levelChoice, 25));
	    //draws the tail
	    xpos = new int[]{x + getSize(levelChoice, 42), x + getSize(levelChoice, 62), x + getSize(levelChoice, 55), x + getSize(levelChoice, 62), x + getSize(levelChoice, 42)};
	    ypos = new int[]{y + getSize(levelChoice, 32), y + getSize(levelChoice, 22), y + side / 2, y + getSize(levelChoice, 50), y + getSize(levelChoice, 40)};
	    c.fillPolygon(xpos, ypos, 5);
	    c.setColor(Color.WHITE);
	    c.fillOval(x + getSize(levelChoice, 15), y + getSize(levelChoice, 34), getSize(levelChoice, 5), getSize(levelChoice, 5));
	    c.setColor(Color.BLACK);
	    c.fillOval(x + getSize(levelChoice, 16), y + getSize(levelChoice, 35), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    //draws fins
	    c.setColor(lightOrange);
	    c.fillOval(x + getSize(levelChoice, 28), y + getSize(levelChoice, 40), getSize(levelChoice, 8), getSize(levelChoice, 13));
	} else if (card == 4) { //draws a butterfly
	    c.setColor(orange);
	    //draws wings
	    c.fillOval(x + side / 2, y + getSize(levelChoice, 15), getSize(levelChoice, 25), getSize(levelChoice, 20));
	    c.fillOval(x + side / 2 - getSize(levelChoice, 25), y + getSize(levelChoice, 15), getSize(levelChoice, 25), getSize(levelChoice, 20));
	    c.fillOval(x + side / 2, y + getSize(levelChoice, 35), getSize(levelChoice, 20), getSize(levelChoice, 15));
	    c.fillOval(x + side / 2 - getSize(levelChoice, 20), y + getSize(levelChoice, 35), getSize(levelChoice, 20), getSize(levelChoice, 15));
	    c.fillOval(x + side / 2 - getSize(levelChoice, 10), y + getSize(levelChoice, 25), getSize(levelChoice, 20), getSize(levelChoice, 20));
	    //draws the body
	    c.setColor(Color.BLACK);
	    c.fillRoundRect(x + side / 2 - getSize(levelChoice, 1), y + getSize(levelChoice, 15), getSize(levelChoice, 2), getSize(levelChoice, 35), getSize(levelChoice, 5), getSize(levelChoice, 5));
	    //draws antennae
	    c.drawLine(x + side / 2, y + getSize(levelChoice, 15), x + side / 2 + getSize(levelChoice, 5), y + getSize(levelChoice, 10));
	    c.drawLine(x + side / 2, y + getSize(levelChoice, 15), x + side / 2 - getSize(levelChoice, 5), y + getSize(levelChoice, 10));
	} else if (card == 5) { //draws a bee
	    //loop to draw the body
	    for (int circle = 5; circle > 0; circle--) {
		if (circle % 2 == 1) { //alternate colours
		    c.setColor(orangeYellow);
		} else {
		    c.setColor(Color.BLACK);
		}
		c.fillOval(x + (getSize(levelChoice, 10) + circle * getSize(levelChoice, 5)), y + side / 2 - getSize(levelChoice, 10), getSize(levelChoice, 20), getSize(levelChoice, 20));
	    }
	    //draws the wings
	    c.setColor(paleBlue);
	    c.fillOval(x + side / 2, y + side / 2 - getSize(levelChoice, 10) - getSize(levelChoice, 12), getSize(levelChoice, 10), getSize(levelChoice, 12));
	    c.fillOval(x + side / 2 - getSize(levelChoice, 7), y + side / 2 - getSize(levelChoice, 10) - getSize(levelChoice, 12), getSize(levelChoice, 10), getSize(levelChoice, 12));
	    //draws the eyes
	    c.setColor(Color.BLACK);
	    c.fillOval(x + getSize(levelChoice, 17), y + getSize(levelChoice, 35), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    c.fillOval(x + getSize(levelChoice, 27), y + getSize(levelChoice, 35), getSize(levelChoice, 3), getSize(levelChoice, 3));
	} else if (card == 6) { //draws a duck
	    //draws the body
	    c.setColor(darkOrangeYellow);
	    c.fillOval(x + getSize(levelChoice, 15), y + getSize(levelChoice, 30), getSize(levelChoice, 40), getSize(levelChoice, 25));
	    c.fillOval(x + getSize(levelChoice, 35), y + getSize(levelChoice, 32), getSize(levelChoice, 23), getSize(levelChoice, 13));
	    //draws the face
	    c.setColor(lightOrangeYellow);
	    c.fillOval(x + getSize(levelChoice, 15), y + getSize(levelChoice, 15), getSize(levelChoice, 25), getSize(levelChoice, 25));
	    //draws the beak
	    c.setColor(strongOrange);
	    c.fillOval(x + getSize(levelChoice, 27) - getSize(levelChoice, 7), y + getSize(levelChoice, 28), getSize(levelChoice, 12), getSize(levelChoice, 5));
	    //draws the feet
	    c.fillOval(x + getSize(levelChoice, 20), y + getSize(levelChoice, 52), getSize(levelChoice, 10), getSize(levelChoice, 5));
	    c.fillOval(x + getSize(levelChoice, 33), y + getSize(levelChoice, 52), getSize(levelChoice, 10), getSize(levelChoice, 5));
	    //draws the eyes
	    c.setColor(Color.BLACK);
	    c.fillOval(x + getSize(levelChoice, 20), y + getSize(levelChoice, 22), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    c.fillOval(x + getSize(levelChoice, 30), y + getSize(levelChoice, 22), getSize(levelChoice, 3), getSize(levelChoice, 3));
	} else if (card == 7) { //draws a giraffe
	    //draws the body
	    c.setColor(brownYellow);
	    xpos = new int[]{x, x, x + getSize(levelChoice, 20), x + getSize(levelChoice, 40), x + getSize(levelChoice, 33)};
	    ypos = new int[]{y + side - getSize(levelChoice, 20), y + side, y + side, y + getSize(levelChoice, 30), y + getSize(levelChoice, 25)};
	    c.fillPolygon(xpos, ypos, 5);
	    //draws the head
	    c.setColor(lightBrownYellow);
	    xpos = new int[]{x + getSize(levelChoice, 30), x + getSize(levelChoice, 32), x + getSize(levelChoice, 48), x + getSize(levelChoice, 50), x + getSize(levelChoice, 44), x + getSize(levelChoice, 36)};
	    ypos = new int[]{y + getSize(levelChoice, 22), y + getSize(levelChoice, 17), y + getSize(levelChoice, 17), y + getSize(levelChoice, 22), y + getSize(levelChoice, 45), y + getSize(levelChoice, 45)};
	    c.fillPolygon(xpos, ypos, 6);
	    xpos = new int[]{x + getSize(levelChoice, 32), x + getSize(levelChoice, 38), x + getSize(levelChoice, 34), x + getSize(levelChoice, 32)};
	    ypos = new int[]{y + getSize(levelChoice, 17), y + getSize(levelChoice, 17), y + getSize(levelChoice, 10), y + getSize(levelChoice, 10)};
	    c.fillPolygon(xpos, ypos, 4);
	    xpos = new int[]{x + getSize(levelChoice, 48), x + getSize(levelChoice, 42), x + getSize(levelChoice, 46), x + getSize(levelChoice, 48)};
	    ypos = new int[]{y + getSize(levelChoice, 17), y + getSize(levelChoice, 17), y + getSize(levelChoice, 10), y + getSize(levelChoice, 10)};
	    c.fillPolygon(xpos, ypos, 4);
	    //draws the nose
	    c.setColor(paleBrownYellow);
	    c.fillOval(x + getSize(levelChoice, 35), y + getSize(levelChoice, 37), getSize(levelChoice, 10), getSize(levelChoice, 10));
	    //draws the horns
	    c.fillOval(x + getSize(levelChoice, 31), y + getSize(levelChoice, 8), getSize(levelChoice, 4), getSize(levelChoice, 4));
	    c.fillOval(x + getSize(levelChoice, 45), y + getSize(levelChoice, 8), getSize(levelChoice, 4), getSize(levelChoice, 4));
	    //draws the eyes
	    c.setColor(Color.BLACK);
	    c.fillOval(x + getSize(levelChoice, 30), y + getSize(levelChoice, 25), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    c.fillOval(x + getSize(levelChoice, 50) - getSize(levelChoice, 3), y + getSize(levelChoice, 25), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    //draws the nose holes
	    c.setColor(paleBrown);
	    c.fillOval(x + getSize(levelChoice, 38), y + getSize(levelChoice, 38), getSize(levelChoice, 2), getSize(levelChoice, 3));
	    c.fillOval(x + getSize(levelChoice, 44) - getSize(levelChoice, 2), y + getSize(levelChoice, 38), getSize(levelChoice, 2), getSize(levelChoice, 3));
	    //draws the spots
	    c.setColor(darkPaleBrown);
	    c.fillOval(x + getSize(levelChoice, 2), y + side - getSize(levelChoice, 8), getSize(levelChoice, 5), getSize(levelChoice, 5));
	    c.fillOval(x + getSize(levelChoice, 10), y + side - getSize(levelChoice, 12), getSize(levelChoice, 5), getSize(levelChoice, 8));
	    c.fillOval(x + getSize(levelChoice, 18), y + side - getSize(levelChoice, 20), getSize(levelChoice, 6), getSize(levelChoice, 8));
	    c.fillOval(x + getSize(levelChoice, 10), y + side - getSize(levelChoice, 26), getSize(levelChoice, 5), getSize(levelChoice, 5));
	    c.fillOval(x + getSize(levelChoice, 3), y + side - getSize(levelChoice, 20), getSize(levelChoice, 5), getSize(levelChoice, 7));
	    c.fillOval(x + getSize(levelChoice, 16), y + side - getSize(levelChoice, 32), getSize(levelChoice, 7), getSize(levelChoice, 5));
	    c.fillOval(x + getSize(levelChoice, 24), y + side - getSize(levelChoice, 25), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    c.fillOval(x + getSize(levelChoice, 24), y + side - getSize(levelChoice, 38), getSize(levelChoice, 3), getSize(levelChoice, 5));
	    c.fillOval(x + getSize(levelChoice, 28), y + side - getSize(levelChoice, 31), getSize(levelChoice, 3), getSize(levelChoice, 3));
	} else if (card == 8) { //draws a turtle
	    //draws the legs
	    c.setColor(lightBrightGreen);
	    c.fillArc(x + side / 2 + getSize(levelChoice, 8), y + getSize(levelChoice, 25), getSize(levelChoice, 23), getSize(levelChoice, 15), 0, 180);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 8) - getSize(levelChoice, 23), y + getSize(levelChoice, 25), getSize(levelChoice, 23), getSize(levelChoice, 15), 0, 180);
	    c.fillArc(x + side / 2 + getSize(levelChoice, 5), y + getSize(levelChoice, 45), getSize(levelChoice, 23), getSize(levelChoice, 15), 0, 180);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 5) - getSize(levelChoice, 23), y + getSize(levelChoice, 45), getSize(levelChoice, 23), getSize(levelChoice, 15), 0, 180);
	    //draws the head
	    c.fillOval(x + side / 2 - getSize(levelChoice, 7), y + getSize(levelChoice, 10), getSize(levelChoice, 15), getSize(levelChoice, 15));
	    //draws the tail
	    xpos = new int[]{x + side / 2 - getSize(levelChoice, 6), x + side / 2 + getSize(levelChoice, 6), x + side / 2};
	    ypos = new int[]{y + getSize(levelChoice, 50), y + getSize(levelChoice, 50), y + getSize(levelChoice, 60)};
	    c.fillPolygon(xpos, ypos, 3);
	    //draws the shell
	    c.setColor(darkBrightGreen);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 17), y + getSize(levelChoice, 20), getSize(levelChoice, 34), getSize(levelChoice, 34));
	    c.setColor(brightGreen);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 13), y + getSize(levelChoice, 24), getSize(levelChoice, 26), getSize(levelChoice, 26));
	    //draws the eyes
	    c.setColor(Color.BLACK);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 5), y + getSize(levelChoice, 13), getSize(levelChoice, 2), getSize(levelChoice, 2));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 5) - getSize(levelChoice, 2), y + getSize(levelChoice, 13), getSize(levelChoice, 2), getSize(levelChoice, 2));
	} else if (card == 9) { //draws a snake
	    //draws the tongue
	    c.setColor(strongRed);
	    c.fillRect(x + getSize(levelChoice, 50) - getSize(levelChoice, 1), y + getSize(levelChoice, 15), getSize(levelChoice, 2), getSize(levelChoice, 5));
	    c.drawLine(x + getSize(levelChoice, 50), y + getSize(levelChoice, 15), x + getSize(levelChoice, 48), y + getSize(levelChoice, 10));
	    c.drawLine(x + getSize(levelChoice, 51), y + getSize(levelChoice, 15), x + getSize(levelChoice, 53), y + getSize(levelChoice, 10));
	    c.drawLine(x + getSize(levelChoice, 51), y + getSize(levelChoice, 15), x + getSize(levelChoice, 48), y + getSize(levelChoice, 10));
	    c.drawLine(x + getSize(levelChoice, 50), y + getSize(levelChoice, 15), x + getSize(levelChoice, 53), y + getSize(levelChoice, 10));
	    //draws the body
	    c.setColor(yellowGreen);
	    for (i = 60; i >= 40; i--) {
		for (j = 0; j < 6; j++) {
		    c.drawArc(x + getSize(levelChoice, 25) - getSize(levelChoice, i / 6) - getSize(levelChoice * 3, j) - getSize(levelChoice * 2, 6), y + getSize(levelChoice, 35) - getSize(levelChoice, i / 4) - getSize(levelChoice * 3, j), getSize(levelChoice, i / 3) + getSize(levelChoice * 3, j * 2), getSize(levelChoice, i / 2) + getSize(levelChoice * 3, j * 2), 0, 180 + 5 * (10 - (Math.abs(i / 2 - 30) - getSize(levelChoice * 3, j))));
		    if (j < 5) {
			c.drawArc(x + getSize(levelChoice, 40) - getSize(levelChoice, i / 6) - getSize(levelChoice * 3, j), y + getSize(levelChoice, 35) - getSize(levelChoice, i / 4) - getSize(levelChoice * 3, j), getSize(levelChoice, i / 3) + getSize(levelChoice * 3, j * 2), getSize(levelChoice, i / 2) + getSize(levelChoice * 3, j * 2), 180, 230);
		    }
		}
	    }
	    c.fillOval(x + getSize(levelChoice, 45), y + getSize(levelChoice, 20), getSize(levelChoice, 10), getSize(levelChoice, 15));
	    //draws the eyes
	    c.setColor(Color.BLACK);
	    c.fillOval(x + getSize(levelChoice, 45), y + getSize(levelChoice, 23), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    c.fillOval(x + getSize(levelChoice, 52), y + getSize(levelChoice, 23), getSize(levelChoice, 3), getSize(levelChoice, 3));
	} else if (card == 10) { //draws a frog
	    //draws the legs
	    c.setColor(darkPaleGreen);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 18), y + getSize(levelChoice, 42), getSize(levelChoice, 20), getSize(levelChoice, 10));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 18) - getSize(levelChoice, 20), y + getSize(levelChoice, 42), getSize(levelChoice, 20), getSize(levelChoice, 10));
	    c.fillOval(x + side / 2 - getSize(levelChoice, 18), y + getSize(levelChoice, 52), getSize(levelChoice, 15), getSize(levelChoice, 5));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 18) - getSize(levelChoice, 15), y + getSize(levelChoice, 52), getSize(levelChoice, 15), getSize(levelChoice, 5));
	    //draws the body
	    c.setColor(paleGreen);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 13), y + getSize(levelChoice, 25), getSize(levelChoice, 26), getSize(levelChoice, 30));
	    c.setColor(lightPaleGreen);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 10), y + getSize(levelChoice, 30), getSize(levelChoice, 20), getSize(levelChoice, 20));
	    //draws the head
	    c.setColor(paleGreen);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 15), y + getSize(levelChoice, 15), getSize(levelChoice, 30), getSize(levelChoice, 25));
	    //draws the eyes
	    c.fillOval(x + side / 2 - getSize(levelChoice, 18), y + getSize(levelChoice, 12), getSize(levelChoice, 15), getSize(levelChoice, 15));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 18) - getSize(levelChoice, 15), y + getSize(levelChoice, 12), getSize(levelChoice, 15), getSize(levelChoice, 15));
	    c.setColor(Color.WHITE);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 15), y + getSize(levelChoice, 15), getSize(levelChoice, 9), getSize(levelChoice, 9));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 15) - getSize(levelChoice, 9), y + getSize(levelChoice, 15), getSize(levelChoice, 9), getSize(levelChoice, 9));
	    c.setColor(Color.BLACK);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 10), y + getSize(levelChoice, 18), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 10) - getSize(levelChoice, 3), y + getSize(levelChoice, 18), getSize(levelChoice, 3), getSize(levelChoice, 3));
	} else if (card == 11) { //draws a pig
	    //draws the body
	    c.setColor(darkPink);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 20), y + side - getSize(levelChoice, 40), getSize(levelChoice, 40), getSize(levelChoice, 80), 0, 180);
	    //draws the head
	    c.setColor(pink);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 20), y + getSize(levelChoice, 15), getSize(levelChoice, 40), getSize(levelChoice, 40));
	    //draws the ears
	    xpos = new int[]{x + side / 2 - getSize(levelChoice, 17), x + side / 2 - getSize(levelChoice, 3), x + side / 2 - getSize(levelChoice, 13)};
	    ypos = new int[]{y + getSize(levelChoice, 25), y + getSize(levelChoice, 17), y + getSize(levelChoice, 5)};
	    c.fillPolygon(xpos, ypos, 3);
	    xpos = new int[]{x + side / 2 + getSize(levelChoice, 17), x + side / 2 + getSize(levelChoice, 3), x + side / 2 + getSize(levelChoice, 13)};
	    ypos = new int[]{y + getSize(levelChoice, 25), y + getSize(levelChoice, 17), y + getSize(levelChoice, 5)};
	    c.fillPolygon(xpos, ypos, 3);
	    //draws the nose
	    c.setColor(lightPink);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 8), y + getSize(levelChoice, 35), getSize(levelChoice, 16), getSize(levelChoice, 10));
	    c.setColor(strongDarkPink);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 5), y + getSize(levelChoice, 40), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 2), y + getSize(levelChoice, 40), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    //draws the eyes
	    c.setColor(Color.BLACK);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 12), y + getSize(levelChoice, 28), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 12) - getSize(levelChoice, 3), y + getSize(levelChoice, 28), getSize(levelChoice, 3), getSize(levelChoice, 3));
	} else if (card == 12) { //draws a walrus
	    //draws the body
	    c.setColor(redBrown);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 25), y + side - getSize(levelChoice, 60), getSize(levelChoice, 50), getSize(levelChoice, 120), 0, 180);
	    //draws the head
	    c.setColor(darkRedBrown);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 10), y + getSize(levelChoice, 25), getSize(levelChoice, 20), getSize(levelChoice, 15));
	    //draws the tusks
	    c.setColor(Color.WHITE);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 8), y + getSize(levelChoice, 10), getSize(levelChoice, 4), getSize(levelChoice, 50), 180, 180);
	    c.fillArc(x + side / 2 + getSize(levelChoice, 8) - getSize(levelChoice, 4), y + getSize(levelChoice, 10), getSize(levelChoice, 4), getSize(levelChoice, 50), 180, 180);
	    //draws the eyes
	    c.setColor(Color.BLACK);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 8), y + getSize(levelChoice, 20), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 8) - getSize(levelChoice, 3), y + getSize(levelChoice, 20), getSize(levelChoice, 3), getSize(levelChoice, 3));
	} else if (card == 13) { //draws a dog
	    //draws the body
	    c.setColor(darkYellowBrown);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 18), y + side - getSize(levelChoice, 30), getSize(levelChoice, 36), getSize(levelChoice, 60), 0, 180);
	    //draws the ears
	    c.setColor(lightYellowBrown);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 22), y + getSize(levelChoice, 25), getSize(levelChoice, 15), getSize(levelChoice, 25));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 22) - getSize(levelChoice, 15), y + getSize(levelChoice, 25), getSize(levelChoice, 15), getSize(levelChoice, 25));
	    //draws the face
	    c.setColor(yellowBrown);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 18), y + getSize(levelChoice, 20), getSize(levelChoice, 36), getSize(levelChoice, 36));
	    //draws the nose
	    c.setColor(palePink);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 3), y + getSize(levelChoice, 43), getSize(levelChoice, 6), getSize(levelChoice, 12), 180, 180);
	    //draws the nose
	    c.setColor(darkYellowBrown);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 8), y + getSize(levelChoice, 40), getSize(levelChoice, 16), getSize(levelChoice, 10));
	    c.setColor(strongDarkYellowBrown);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 2), y + getSize(levelChoice, 43), getSize(levelChoice, 4), getSize(levelChoice, 2));
	    //draws the eyes
	    c.setColor(Color.BLACK);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 10), y + getSize(levelChoice, 35), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 7), y + getSize(levelChoice, 35), getSize(levelChoice, 3), getSize(levelChoice, 3));
	} else if (card == 14) { //draws a snail
	    //draws the body
	    c.setColor(lightBrownGray);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 25), y + getSize(levelChoice, 40), getSize(levelChoice, 50), getSize(levelChoice, 30), 0, 180);
	    //draws the eye
	    if (levelChoice < 4) {
		c.drawLine(x + 151, y + getSize(levelChoice, 45), x + 168, y + getSize(levelChoice, 35));
		c.drawLine(x + 154, y + getSize(levelChoice, 45), x + 171, y + getSize(levelChoice, 35));
		c.drawLine(x + 152, y + getSize(levelChoice, 45), x + 169, y + getSize(levelChoice, 35));
		c.drawLine(x + 153, y + getSize(levelChoice, 45), x + 170, y + getSize(levelChoice, 35));
	    } else {
		c.drawLine(x + getSize(levelChoice, 55), y + getSize(levelChoice, 45), x + getSize(levelChoice, 60), y + getSize(levelChoice, 35));
		c.drawLine(x + getSize(levelChoice, 54), y + getSize(levelChoice, 45), x + getSize(levelChoice, 59), y + getSize(levelChoice, 35));
	    }
	    c.setColor(Color.WHITE);
	    c.fillOval(x + getSize(levelChoice, 58), y + getSize(levelChoice, 34), getSize(levelChoice, 4), getSize(levelChoice, 4));
	    c.setColor(Color.BLACK);
	    c.fillOval(x + getSize(levelChoice, 59), y + getSize(levelChoice, 35), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    //draws the shell
	    c.setColor(darkBrownGray);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 20), y + getSize(levelChoice, 20), getSize(levelChoice, 30), getSize(levelChoice, 30));
	    c.setColor(lightBrownGray);
	    for (i = 25; i > 0; i -= 8) {
		c.drawOval(x + side / 2 - getSize(levelChoice, 5) - getSize(levelChoice, i / 2), y + getSize(levelChoice, 35) - getSize(levelChoice, i / 2), getSize(levelChoice, i), getSize(levelChoice, i));
	    }
	} else if (card == 15) { //draws an opossum
	    //draws the body
	    c.setColor(paleGray);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 20), y + getSize(levelChoice, 30), getSize(levelChoice, 40), 2 * (side - getSize(levelChoice, 30)), 0, 180);
	    //draws the face
	    c.setColor(Color.WHITE);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 15), y + getSize(levelChoice, 20), getSize(levelChoice, 30), getSize(levelChoice, 30));
	    //draws the nose
	    c.setColor(palePink);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 3), y + getSize(levelChoice, 40), getSize(levelChoice, 6), getSize(levelChoice, 4));
	    //draws the eyes
	    c.setColor(Color.BLACK);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 7), y + getSize(levelChoice, 33), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 4), y + getSize(levelChoice, 33), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    //draws the ears
	    c.fillOval(x + side / 2 - getSize(levelChoice, 15), y + getSize(levelChoice, 20), getSize(levelChoice, 7), getSize(levelChoice, 8));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 8), y + getSize(levelChoice, 20), getSize(levelChoice, 7), getSize(levelChoice, 8));
	    //draws the hands
	    c.fillOval(x + side / 2 - getSize(levelChoice, 13), y + getSize(levelChoice, 55), getSize(levelChoice, 4), getSize(levelChoice, 8));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 9), y + getSize(levelChoice, 55), getSize(levelChoice, 4), getSize(levelChoice, 8));
	    c.setColor(palePink);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 14), y + getSize(levelChoice, 62), getSize(levelChoice, 2), getSize(levelChoice, 2));
	    c.fillOval(x + side / 2 - getSize(levelChoice, 12), y + getSize(levelChoice, 63), getSize(levelChoice, 2), getSize(levelChoice, 2));
	    c.fillOval(x + side / 2 - getSize(levelChoice, 10), y + getSize(levelChoice, 62), getSize(levelChoice, 2), getSize(levelChoice, 2));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 13), y + getSize(levelChoice, 62), getSize(levelChoice, 2), getSize(levelChoice, 2));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 11), y + getSize(levelChoice, 63), getSize(levelChoice, 2), getSize(levelChoice, 2));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 9), y + getSize(levelChoice, 62), getSize(levelChoice, 2), getSize(levelChoice, 2));
	} else if (card == 16) { //draws a squirrel
	    //draws the tail
	    c.setColor(paleLightBrownOrange);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 20), y + getSize(levelChoice, 35), getSize(levelChoice, 20), getSize(levelChoice, 25));
	    c.fillOval(x + side / 2 - getSize(levelChoice, 25), y + getSize(levelChoice, 35), getSize(levelChoice, 15), getSize(levelChoice, 10));
	    //draws the body
	    c.setColor(brownOrange);
	    c.fillRoundRect(x + side / 2 - getSize(levelChoice, 12), y + getSize(levelChoice, 25), getSize(levelChoice, 24), getSize(levelChoice, 35), getSize(levelChoice, 30), getSize(levelChoice, 30));
	    //draws the head
	    c.setColor(lightBrownOrange);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 5), y + getSize(levelChoice, 15), getSize(levelChoice, 25), getSize(levelChoice, 20));
	    //draws the ear
	    c.fillOval(x + side / 2 - getSize(levelChoice, 2), y + getSize(levelChoice, 10), getSize(levelChoice, 7), getSize(levelChoice, 15));
	    //draws the arms
	    c.fillOval(x + side / 2 + getSize(levelChoice, 4), y + getSize(levelChoice, 40), getSize(levelChoice, 10), getSize(levelChoice, 5));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 3), y + getSize(levelChoice, 55), getSize(levelChoice, 10), getSize(levelChoice, 5));
	    //draws the nose
	    c.fillOval(x + side / 2 + getSize(levelChoice, 11), y + getSize(levelChoice, 20), getSize(levelChoice, 12), getSize(levelChoice, 12));
	    //draws the eye
	    c.setColor(Color.WHITE);
	    c.fillOval(x + side / 2 + getSize(levelChoice, 10), y + getSize(levelChoice, 22), getSize(levelChoice, 5), getSize(levelChoice, 5));
	    c.setColor(Color.BLACK);
	    c.fillOval(x + side / 2 + getSize(levelChoice, 12), y + getSize(levelChoice, 23), getSize(levelChoice, 3), getSize(levelChoice, 3));
	} else if (card == 17) { //draws a lion
	    //draws the body
	    c.setColor(lightBrown);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 15), y + side - getSize(levelChoice, 30), getSize(levelChoice, 30), getSize(levelChoice, 60), 0, 180);
	    //draws the mane
	    c.setColor(brown);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 20), y + getSize(levelChoice, 15), getSize(levelChoice, 40), getSize(levelChoice, 45));
	    //draws the face
	    c.setColor(lightBrown);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 10), y + getSize(levelChoice, 25), getSize(levelChoice, 20), getSize(levelChoice, 20));
	    c.fillRoundRect(x + side / 2 - getSize(levelChoice, 6), y + getSize(levelChoice, 40), getSize(levelChoice, 12), getSize(levelChoice, 10), getSize(levelChoice, 10), getSize(levelChoice, 10));
	    //draws the ears
	    c.fillOval(x + side / 2 - getSize(levelChoice, 18), y + getSize(levelChoice, 20), getSize(levelChoice, 8), getSize(levelChoice, 8));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 10), y + getSize(levelChoice, 20), getSize(levelChoice, 8), getSize(levelChoice, 8));
	    //draws the eyes
	    c.setColor(Color.WHITE);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 9), y + getSize(levelChoice, 31), getSize(levelChoice, 5), getSize(levelChoice, 5));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 4), y + getSize(levelChoice, 31), getSize(levelChoice, 5), getSize(levelChoice, 5));
	    c.setColor(Color.BLACK);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 8), y + getSize(levelChoice, 32), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 5), y + getSize(levelChoice, 32), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    //draws the nose
	    c.setColor(new Color(161, 115, 98));
	    xpos = new int[]{x + side / 2 - getSize(levelChoice, 4), x + side / 2 + getSize(levelChoice, 4), x + side / 2};
	    ypos = new int[]{y + getSize(levelChoice, 45), y + getSize(levelChoice, 45), y + getSize(levelChoice, 48)};
	    c.fillPolygon(xpos, ypos, 3);
	} else if (card == 18) { //draws a spider
	    //draws the body
	    c.setColor(lightBlack);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 10), y + side / 2 - getSize(levelChoice, 15), getSize(levelChoice, 20), getSize(levelChoice, 20));
	    c.fillOval(x + side / 2 - getSize(levelChoice, 8), y + side / 2 - getSize(levelChoice, 5), getSize(levelChoice, 16), getSize(levelChoice, 16));
	    //draws the legs
	    c.drawLine(x + side / 2, y + side / 2, x + side / 2 - getSize(levelChoice, 20), y + side / 2 + getSize(levelChoice, 10));
	    c.drawLine(x + side / 2 - getSize(levelChoice, 27), y + side / 2 + getSize(levelChoice, 20), x + side / 2 - getSize(levelChoice, 20), y + side / 2 + getSize(levelChoice, 10));
	    c.drawLine(x + side / 2, y + side / 2 - getSize(levelChoice, 5), x + side / 2 - getSize(levelChoice, 19), y + side / 2 - getSize(levelChoice, 3));
	    c.drawLine(x + side / 2 - getSize(levelChoice, 27), y + side / 2 + getSize(levelChoice, 5), x + side / 2 - getSize(levelChoice, 19), y + side / 2 - getSize(levelChoice, 3));
	    c.drawLine(x + side / 2, y + side / 2 - getSize(levelChoice, 10), x + side / 2 - getSize(levelChoice, 17), y + side / 2 - getSize(levelChoice, 10));
	    c.drawLine(x + side / 2 - getSize(levelChoice, 27), y + side / 2 - getSize(levelChoice, 5), x + side / 2 - getSize(levelChoice, 17), y + side / 2 - getSize(levelChoice, 10));
	    c.drawLine(x + side / 2, y + side / 2 - getSize(levelChoice, 12), x + side / 2 - getSize(levelChoice, 15), y + side / 2 - getSize(levelChoice, 18));
	    c.drawLine(x + side / 2 - getSize(levelChoice, 27), y + side / 2 - getSize(levelChoice, 13), x + side / 2 - getSize(levelChoice, 15), y + side / 2 - getSize(levelChoice, 18));
	    c.drawLine(x + side / 2, y + side / 2, x + side / 2 + getSize(levelChoice, 20), y + side / 2 + getSize(levelChoice, 10));
	    c.drawLine(x + side / 2 + getSize(levelChoice, 27), y + side / 2 + getSize(levelChoice, 20), x + side / 2 + getSize(levelChoice, 20), y + side / 2 + getSize(levelChoice, 10));
	    c.drawLine(x + side / 2, y + side / 2 - getSize(levelChoice, 5), x + side / 2 + getSize(levelChoice, 19), y + side / 2 - getSize(levelChoice, 3));
	    c.drawLine(x + side / 2 + getSize(levelChoice, 27), y + side / 2 + getSize(levelChoice, 5), x + side / 2 + getSize(levelChoice, 19), y + side / 2 - getSize(levelChoice, 3));
	    c.drawLine(x + side / 2, y + side / 2 - getSize(levelChoice, 10), x + side / 2 + getSize(levelChoice, 17), y + side / 2 - getSize(levelChoice, 10));
	    c.drawLine(x + side / 2 + getSize(levelChoice, 27), y + side / 2 - getSize(levelChoice, 5), x + side / 2 + getSize(levelChoice, 17), y + side / 2 - getSize(levelChoice, 10));
	    c.drawLine(x + side / 2, y + side / 2 - getSize(levelChoice, 12), x + side / 2 + getSize(levelChoice, 15), y + side / 2 - getSize(levelChoice, 18));
	    c.drawLine(x + side / 2 + getSize(levelChoice, 27), y + side / 2 - getSize(levelChoice, 13), x + side / 2 + getSize(levelChoice, 15), y + side / 2 - getSize(levelChoice, 18));
	    //draws the eyes
	    c.setColor(Color.WHITE);
	    c.drawOval(x + side / 2 - getSize(levelChoice, 6), y + side / 2 + getSize(levelChoice, 5), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    c.drawOval(x + side / 2 + getSize(levelChoice, 3), y + side / 2 + getSize(levelChoice, 5), getSize(levelChoice, 3), getSize(levelChoice, 3));
	} else if (card == 19) { //draws a koala
	    //draws the body
	    c.setColor(lightGray);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 18), y + getSize(levelChoice, 30), getSize(levelChoice, 36), 2 * (side - getSize(levelChoice, 30)), 0, 180);
	    c.setColor(whiteTransparent);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 13), y + getSize(levelChoice, 40), getSize(levelChoice, 26), 2 * (side - getSize(levelChoice, 40)), 0, 180);
	    //draws the ears
	    c.setColor(gray);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 18), y + getSize(levelChoice, 15), getSize(levelChoice, 36), getSize(levelChoice, 36));
	    c.fillOval(x + side / 2 - getSize(levelChoice, 24), y + getSize(levelChoice, 15), getSize(levelChoice, 20), getSize(levelChoice, 18));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 4), y + getSize(levelChoice, 15), getSize(levelChoice, 20), getSize(levelChoice, 18));
	    c.setColor(whiteTransparent);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 20), y + getSize(levelChoice, 23), getSize(levelChoice, 8), getSize(levelChoice, 10));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 12), y + getSize(levelChoice, 23), getSize(levelChoice, 8), getSize(levelChoice, 10));
	    //draws the nose
	    c.setColor(Color.BLACK);
	    c.fillRoundRect(x + side / 2 - getSize(levelChoice, 5), y + getSize(levelChoice, 30), getSize(levelChoice, 10), getSize(levelChoice, 15), getSize(levelChoice, 15), getSize(levelChoice, 15));
	    c.setColor(palePink);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 4), y + getSize(levelChoice, 40), getSize(levelChoice, 2), getSize(levelChoice, 2));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 2), y + getSize(levelChoice, 40), getSize(levelChoice, 2), getSize(levelChoice, 2));
	    //draws the eyes
	    c.setColor(Color.WHITE);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 11), y + getSize(levelChoice, 26), getSize(levelChoice, 5), getSize(levelChoice, 5));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 6), y + getSize(levelChoice, 26), getSize(levelChoice, 5), getSize(levelChoice, 5));
	    c.setColor(Color.BLACK);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 10), y + getSize(levelChoice, 27), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 7), y + getSize(levelChoice, 27), getSize(levelChoice, 3), getSize(levelChoice, 3));
	} else if (card == 20) { //draws a penguin
	    //draws the body
	    c.setColor(lightBlack);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 14), y + getSize(levelChoice, 25), getSize(levelChoice, 28), getSize(levelChoice, 40));
	    //draws the wings
	    c.fillOval(x + side / 2 - getSize(levelChoice, 25), y + getSize(levelChoice, 35), getSize(levelChoice, 20), getSize(levelChoice, 5));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 5), y + getSize(levelChoice, 35), getSize(levelChoice, 20), getSize(levelChoice, 5));
	    //draws the body spot
	    c.setColor(white);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 10), y + getSize(levelChoice, 30), getSize(levelChoice, 20), getSize(levelChoice, 25));
	    //draws the head
	    c.setColor(lightBlack);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 10), y + getSize(levelChoice, 15), getSize(levelChoice, 20), getSize(levelChoice, 20));
	    //draws the eyes
	    c.setColor(Color.WHITE);
	    c.drawOval(x + side / 2 - getSize(levelChoice, 7), y + getSize(levelChoice, 22), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    c.drawOval(x + side / 2 + getSize(levelChoice, 4), y + getSize(levelChoice, 22), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    //draws the mouth
	    c.setColor(paleOrange);
	    xpos = new int[]{x + side / 2 - getSize(levelChoice, 3), x + side / 2 + getSize(levelChoice, 3), x + side / 2};
	    ypos = new int[]{y + getSize(levelChoice, 28), y + getSize(levelChoice, 28), y + getSize(levelChoice, 31)};
	    c.fillPolygon(xpos, ypos, 3);
	    //draws the feet
	    c.fillOval(x + side / 2 - getSize(levelChoice, 10), y + getSize(levelChoice, 63), getSize(levelChoice, 7), getSize(levelChoice, 3));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 5), y + getSize(levelChoice, 63), getSize(levelChoice, 7), getSize(levelChoice, 3));
	} else if (card == 21) { //draws a seal
	    //draws the body
	    c.setColor(paleWhite);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 20), y + side - getSize(levelChoice, 25), getSize(levelChoice, 40), getSize(levelChoice, 20));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 3), y + side - getSize(levelChoice, 38), getSize(levelChoice, 20), getSize(levelChoice, 30));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 5), y + side - getSize(levelChoice, 40), getSize(levelChoice, 20), getSize(levelChoice, 15));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 15), y + side - getSize(levelChoice, 35), getSize(levelChoice, 15), getSize(levelChoice, 10));
	    //draws the tail
	    c.fillOval(x + side / 2 - getSize(levelChoice, 20), y + side - getSize(levelChoice, 35), getSize(levelChoice, 7), getSize(levelChoice, 20));
	    c.fillOval(x + side / 2 - getSize(levelChoice, 30), y + side - getSize(levelChoice, 25), getSize(levelChoice, 20), getSize(levelChoice, 7));
	    //draws the hand
	    c.fillOval(x + side / 2 + getSize(levelChoice, 6), y + side - getSize(levelChoice, 13), getSize(levelChoice, 20), getSize(levelChoice, 7));
	    //draws the nose
	    c.setColor(Color.BLACK);
	    c.fillOval(x + side / 2 + getSize(levelChoice, 27), y + side - getSize(levelChoice, 33), getSize(levelChoice, 4), getSize(levelChoice, 4));
	    //draws the eye
	    c.fillOval(x + side / 2 + getSize(levelChoice, 16), y + side - getSize(levelChoice, 34), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    //draws the whiskers
	    c.drawLine(x + side / 2 + getSize(levelChoice, 24), y + side - getSize(levelChoice, 31), x + side / 2 + getSize(levelChoice, 19), y + side - getSize(levelChoice, 27));
	    c.drawLine(x + side / 2 + getSize(levelChoice, 26), y + side - getSize(levelChoice, 29), x + side / 2 + getSize(levelChoice, 21), y + side - getSize(levelChoice, 24));
	    c.drawLine(x + side / 2 + getSize(levelChoice, 27), y + side - getSize(levelChoice, 27), x + side / 2 + getSize(levelChoice, 25), y + side - getSize(levelChoice, 22));
	} else if (card == 22) { //draws a rabbit
	    //draws the body
	    c.setColor(grayYellow);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 15), y + side - getSize(levelChoice, 30), getSize(levelChoice, 30), getSize(levelChoice, 60), 0, 180);
	    //draws the head
	    c.setColor(whiteYellow);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 15), y + getSize(levelChoice, 20), getSize(levelChoice, 30), getSize(levelChoice, 30));
	    //draws the arms
	    c.fillOval(x + side / 2 - getSize(levelChoice, 12), y + getSize(levelChoice, 55), getSize(levelChoice, 5), getSize(levelChoice, 12));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 7), y + getSize(levelChoice, 55), getSize(levelChoice, 5), getSize(levelChoice, 12));
	    //draws the ears
	    c.fillOval(x + side / 2 - getSize(levelChoice, 12), y + getSize(levelChoice, 8), getSize(levelChoice, 7), getSize(levelChoice, 22));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 5), y + getSize(levelChoice, 8), getSize(levelChoice, 7), getSize(levelChoice, 22));
	    c.setColor(palePink);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 10), y + getSize(levelChoice, 10), getSize(levelChoice, 3), getSize(levelChoice, 15));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 7), y + getSize(levelChoice, 10), getSize(levelChoice, 3), getSize(levelChoice, 15));
	    //draws the eyes
	    c.setColor(blackTransparent);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 12), y + getSize(levelChoice, 29), getSize(levelChoice, 10), getSize(levelChoice, 10));
	    c.setColor(Color.BLACK);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 8), y + getSize(levelChoice, 33), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 5), y + getSize(levelChoice, 33), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    //draws the nose
	    c.setColor(Color.WHITE);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 7), y + getSize(levelChoice, 37), getSize(levelChoice, 15), getSize(levelChoice, 10));
	    c.setColor(palePink);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 2), y + getSize(levelChoice, 40), getSize(levelChoice, 4), getSize(levelChoice, 3));
	} else if (card == 23) { //draws an owl
	    //draws the wings
	    c.setColor(strongBrown);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 15), y + getSize(levelChoice, 15), getSize(levelChoice, 30), getSize(levelChoice, 45));
	    //draws the legs
	    c.setColor(lightDarkBrown);
	    c.fillRect(x + side / 2 - getSize(levelChoice, 4), y + getSize(levelChoice, 53), getSize(levelChoice, 4), getSize(levelChoice, 10));
	    c.fillRect(x + side / 2 + getSize(levelChoice, 4), y + getSize(levelChoice, 53), getSize(levelChoice, 4), getSize(levelChoice, 10));
	    c.fillArc(x + side / 2 - getSize(levelChoice, 6), y + getSize(levelChoice, 60), getSize(levelChoice, 6), getSize(levelChoice, 10), 0, 180);
	    c.fillArc(x + side / 2 + getSize(levelChoice, 2), y + getSize(levelChoice, 60), getSize(levelChoice, 6), getSize(levelChoice, 10), 0, 180);
	    //draws the chest
	    c.setColor(lightStrongBrown);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 12), y + getSize(levelChoice, 20), getSize(levelChoice, 24), getSize(levelChoice, 38));
	    c.setColor(Color.WHITE);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 12), y + getSize(levelChoice, 20), getSize(levelChoice, 24), getSize(levelChoice, 24));
	    //draws the wings
	    c.setColor(strongBrown);
	    c.fillOval(x + side / 2 + getSize(levelChoice, 5), y + getSize(levelChoice, 20), getSize(levelChoice, 10), getSize(levelChoice, 45));
	    //draws the face
	    c.setColor(strongOrangeYellow);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 15), y + getSize(levelChoice, 12), getSize(levelChoice, 26), getSize(levelChoice, 26));
	    c.setColor(paleDarkBrown);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 15), y + getSize(levelChoice, 12), getSize(levelChoice, 26), getSize(levelChoice, 26), 45, 90);
	    c.setColor(darkDarkBrown);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 20), y + getSize(levelChoice, 12), getSize(levelChoice, 13), getSize(levelChoice, 5));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 3), y + getSize(levelChoice, 12), getSize(levelChoice, 13), getSize(levelChoice, 5));
	    //draws the eyes
	    c.setColor(Color.BLACK);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 10), y + getSize(levelChoice, 23), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 3), y + getSize(levelChoice, 23), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    //draws the beak
	    c.setColor(blackBrown);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 4), y + getSize(levelChoice, 25), getSize(levelChoice, 4), getSize(levelChoice, 8));
	} else if (card == 24) { //draws a panda
	    //draws the arms
	    c.setColor(lightBlack);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 23), y + side - getSize(levelChoice, 36), getSize(levelChoice, 30), getSize(levelChoice, 70), 0, 180);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 7), y + side - getSize(levelChoice, 36), getSize(levelChoice, 30), getSize(levelChoice, 70), 0, 180);
	    //draws the chest
	    c.setColor(orangeWhite);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 15), y + side - getSize(levelChoice, 35), getSize(levelChoice, 30), getSize(levelChoice, 70), 0, 180);
	    c.setColor(lightBlack);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 15), y + side - getSize(levelChoice, 40), getSize(levelChoice, 30), getSize(levelChoice, 20));
	    c.setColor(orangeWhite);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 15), y + side - getSize(levelChoice, 55), getSize(levelChoice, 30), getSize(levelChoice, 30));
	    //draws the ears
	    c.setColor(lightBlack);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 15), y + side - getSize(levelChoice, 58), getSize(levelChoice, 10), getSize(levelChoice, 10));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 5), y + side - getSize(levelChoice, 58), getSize(levelChoice, 10), getSize(levelChoice, 10));
	    //draws the eyes
	    c.fillOval(x + side / 2 - getSize(levelChoice, 12), y + side - getSize(levelChoice, 45), getSize(levelChoice, 8), getSize(levelChoice, 10));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 4), y + side - getSize(levelChoice, 45), getSize(levelChoice, 8), getSize(levelChoice, 10));
	    //draws the nose
	    c.fillOval(x + side / 2 - getSize(levelChoice, 3), y + side - getSize(levelChoice, 35), getSize(levelChoice, 6), getSize(levelChoice, 4));
	    //draws the eyes
	    c.setColor(orangeWhite);
	    c.drawOval(x + side / 2 - getSize(levelChoice, 10), y + side - getSize(levelChoice, 42), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    c.drawOval(x + side / 2 + getSize(levelChoice, 7), y + side - getSize(levelChoice, 42), getSize(levelChoice, 3), getSize(levelChoice, 3));
	} else if (card == 25) { //draws an elephant
	    //draws the chest
	    c.setColor(grayOrange);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 20), y + side - getSize(levelChoice, 36), getSize(levelChoice, 40), getSize(levelChoice, 70), 0, 180);
	    //draws the ears
	    c.setColor(grayOrange);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 30), y + getSize(levelChoice, 15), getSize(levelChoice, 25), getSize(levelChoice, 30));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 5), y + getSize(levelChoice, 15), getSize(levelChoice, 25), getSize(levelChoice, 30));
	    c.fillRect(x + side / 2 - getSize(levelChoice, 17), y + getSize(levelChoice, 15), getSize(levelChoice, 34), getSize(levelChoice, 15));
	    //draws the face
	    c.setColor(lightGrayOrange);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 13), y + getSize(levelChoice, 18), getSize(levelChoice, 26), getSize(levelChoice, 28));
	    xpos = new int[]{x + side / 2 - getSize(levelChoice, 6), x + side / 2 + getSize(levelChoice, 6), x + side / 2 + getSize(levelChoice, 4), x + side / 2 - getSize(levelChoice, 4)};
	    ypos = new int[]{y + getSize(levelChoice, 35), y + getSize(levelChoice, 35), y + getSize(levelChoice, 61), y + getSize(levelChoice, 61)};
	    c.fillPolygon(xpos, ypos, 4);
	    c.setColor(paleGrayOrange);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 6), y + getSize(levelChoice, 58), getSize(levelChoice, 6), getSize(levelChoice, 3));
	    c.fillOval(x + side / 2, y + getSize(levelChoice, 58), getSize(levelChoice, 6), getSize(levelChoice, 3));
	    //draws the eyes
	    c.setColor(Color.BLACK);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 8), y + getSize(levelChoice, 30), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 5), y + getSize(levelChoice, 30), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    //draws the tusks
	    c.setColor(Color.WHITE);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 10), y + getSize(levelChoice, 25), getSize(levelChoice, 4), getSize(levelChoice, 30), 180, 180);
	    c.fillArc(x + side / 2 + getSize(levelChoice, 6), y + getSize(levelChoice, 25), getSize(levelChoice, 4), getSize(levelChoice, 30), 180, 180);
	} else if (card == 26) { //draws a raccoon
	    //draws the tail
	    c.setColor(brownOrangeRed);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 20), y + side - getSize(levelChoice, 32), getSize(levelChoice, 12), getSize(levelChoice, 16), 0, 180);
	    c.setColor(lightBlack);
	    c.fillRect(x + side / 2 - getSize(levelChoice, 20), y + side - getSize(levelChoice, 24), getSize(levelChoice, 12), getSize(levelChoice, 8));
	    c.setColor(brownOrangeRed);
	    c.fillRect(x + side / 2 - getSize(levelChoice, 20), y + side - getSize(levelChoice, 16), getSize(levelChoice, 12), getSize(levelChoice, 8));
	    c.setColor(lightBlack);
	    c.fillRect(x + side / 2 - getSize(levelChoice, 20), y + side - getSize(levelChoice, 8), getSize(levelChoice, 12), getSize(levelChoice, 6));
	    //draws the body
	    c.setColor(paleOrangeGray);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 10), y + side - getSize(levelChoice, 32), getSize(levelChoice, 30), getSize(levelChoice, 60), 0, 180);
	    //draws the face
	    c.setColor(lightOrangeGray);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 10), y + getSize(levelChoice, 27), getSize(levelChoice, 30), getSize(levelChoice, 28));
	    c.setColor(brightOrangeGray);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 8), y + getSize(levelChoice, 36), getSize(levelChoice, 12), getSize(levelChoice, 10));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 7), y + getSize(levelChoice, 36), getSize(levelChoice, 12), getSize(levelChoice, 10));
	    c.setColor(darkOrangeGray);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 9), y + getSize(levelChoice, 38), getSize(levelChoice, 14), getSize(levelChoice, 14));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 6), y + getSize(levelChoice, 38), getSize(levelChoice, 14), getSize(levelChoice, 14));
	    c.setColor(brightOrangeGray);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 3), y + getSize(levelChoice, 45), getSize(levelChoice, 16), getSize(levelChoice, 10));
	    c.setColor(lightBlack);
	    c.fillOval(x + side / 2 + getSize(levelChoice, 4), y + getSize(levelChoice, 38), getSize(levelChoice, 4), getSize(levelChoice, 10));
	    c.setColor(Color.BLACK);
	    c.fillOval(x + side / 2 + getSize(levelChoice, 4), y + getSize(levelChoice, 48), getSize(levelChoice, 4), getSize(levelChoice, 3));
	    //draws the eyes
	    c.setColor(Color.WHITE);
	    c.drawOval(x + side / 2 - getSize(levelChoice, 3), y + getSize(levelChoice, 42), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    c.drawOval(x + side / 2 + getSize(levelChoice, 11), y + getSize(levelChoice, 42), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    //draws the arms
	    c.setColor(new Color(105, 98, 96));
	    c.fillOval(x + side / 2 - getSize(levelChoice, 8), y + side - getSize(levelChoice, 18), getSize(levelChoice, 6), getSize(levelChoice, 13));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 11), y + side - getSize(levelChoice, 18), getSize(levelChoice, 6), getSize(levelChoice, 13));
	    //draws the ears
	    c.setColor(darkOrangeGray);
	    xpos = new int[]{x + side / 2 - getSize(levelChoice, 9), x + side / 2 - getSize(levelChoice, 1), x + side / 2 - getSize(levelChoice, 9)};
	    ypos = new int[]{y + side - getSize(levelChoice, 38), y + side - getSize(levelChoice, 43), y + side - getSize(levelChoice, 48)};
	    c.fillPolygon(xpos, ypos, 3);
	    xpos = new int[]{x + side / 2 + getSize(levelChoice, 19), x + side / 2 + getSize(levelChoice, 11), x + side / 2 + getSize(levelChoice, 19)};
	    ypos = new int[]{y + side - getSize(levelChoice, 38), y + side - getSize(levelChoice, 43), y + side - getSize(levelChoice, 48)};
	    c.fillPolygon(xpos, ypos, 3);
	} else if (card == 27) { //draws a ladybug
	    //draws the legs
	    c.setColor(Color.BLACK);
	    c.drawLine(x + side / 2, y + side / 2, x + side / 2 + getSize(levelChoice, 18), y + side / 2 + getSize(levelChoice, 12));
	    c.drawLine(x + side / 2, y + side / 2, x + side / 2 + getSize(levelChoice, 18), y + side / 2 - getSize(levelChoice, 12));
	    c.drawLine(x + side / 2, y + side / 2, x + side / 2 - getSize(levelChoice, 18), y + side / 2 + getSize(levelChoice, 12));
	    c.drawLine(x + side / 2, y + side / 2, x + side / 2 - getSize(levelChoice, 18), y + side / 2 - getSize(levelChoice, 12));
	    c.drawLine(x + side / 2, y + side / 2, x + side / 2 + getSize(levelChoice, 12), y + side / 2 - getSize(levelChoice, 21));
	    c.drawLine(x + side / 2, y + side / 2, x + side / 2 - getSize(levelChoice, 12), y + side / 2 - getSize(levelChoice, 21));
	    c.drawLine(x + side / 2, y + side / 2 - getSize(levelChoice, 4), x + side / 2 + getSize(levelChoice, 20), y + side / 2 - getSize(levelChoice, 4));
	    c.drawLine(x + side / 2, y + side / 2 - getSize(levelChoice, 4), x + side / 2 - getSize(levelChoice, 20), y + side / 2 - getSize(levelChoice, 4));
	    //draws the body
	    c.setColor(pinkRed);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 15), y + side / 2 - getSize(levelChoice, 20), getSize(levelChoice, 30), getSize(levelChoice, 40));
	    //draws the face
	    c.setColor(lightBlack);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 14), y + side / 2 - getSize(levelChoice, 20), getSize(levelChoice, 28), getSize(levelChoice, 25), 0, 180);
	    c.drawLine(x + side / 2, y + side / 2 - getSize(levelChoice, 20), x + side / 2, y + side / 2 + getSize(levelChoice, 20));
	    //draws the spots
	    c.fillOval(x + side / 2 - getSize(levelChoice, 4), y + side / 2 - getSize(levelChoice, 6), getSize(levelChoice, 8), getSize(levelChoice, 6));
	    c.fillOval(x + side / 2 - getSize(levelChoice, 12), y + side / 2 - getSize(levelChoice, 4), getSize(levelChoice, 5), getSize(levelChoice, 6));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 6), y + side / 2 - getSize(levelChoice, 4), getSize(levelChoice, 5), getSize(levelChoice, 6));
	    c.fillOval(x + side / 2 - getSize(levelChoice, 9), y + side / 2 + getSize(levelChoice, 10), getSize(levelChoice, 5), getSize(levelChoice, 5));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 4), y + side / 2 + getSize(levelChoice, 10), getSize(levelChoice, 5), getSize(levelChoice, 5));
	    c.fillOval(x + side / 2 - getSize(levelChoice, 6), y + side / 2 + getSize(levelChoice, 4), getSize(levelChoice, 4), getSize(levelChoice, 4));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 3), y + side / 2 + getSize(levelChoice, 4), getSize(levelChoice, 4), getSize(levelChoice, 4));
	    //draws the eyes
	    c.setColor(Color.WHITE);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 12), y + side / 2 - getSize(levelChoice, 13), getSize(levelChoice, 5), getSize(levelChoice, 3));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 7), y + side / 2 - getSize(levelChoice, 13), getSize(levelChoice, 5), getSize(levelChoice, 3));
	    c.fillOval(x + side / 2 - getSize(levelChoice, 5), y + side / 2 - getSize(levelChoice, 18), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 2), y + side / 2 - getSize(levelChoice, 18), getSize(levelChoice, 3), getSize(levelChoice, 3));
	} else if (card == 28) { //draws a toucan
	    //draws the leg
	    c.setColor(grayRed);
	    c.fillRect(x + side / 2 - getSize(levelChoice, 12), y + side - getSize(levelChoice, 15), getSize(levelChoice, 4), getSize(levelChoice, 15));
	    //draws the beak
	    c.setColor(brightBrownOrange);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 13), y + side - getSize(levelChoice, 43), getSize(levelChoice, 35), getSize(levelChoice, 20), 0, 180);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 13), y + side - getSize(levelChoice, 38), getSize(levelChoice, 35), getSize(levelChoice, 8), 180, 180);
	    c.setColor(Color.BLACK);
	    c.drawLine(x + side / 2 - getSize(levelChoice, 13), y + side - getSize(levelChoice, 34), x + side / 2 + getSize(levelChoice, 22), y + side - getSize(levelChoice, 34));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 13), y + side - getSize(levelChoice, 38), getSize(levelChoice, 8), getSize(levelChoice, 4));
	    //draws the body
	    c.setColor(lightBlack);
	    c.fillRect(x + side / 2 - getSize(levelChoice, 13), y + side - getSize(levelChoice, 15), getSize(levelChoice, 6), getSize(levelChoice, 13));
	    c.fillArc(x + side / 2 - getSize(levelChoice, 25), y + side - getSize(levelChoice, 21), getSize(levelChoice, 8), getSize(levelChoice, 40), 0, 180);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 24), y + side - getSize(levelChoice, 25), getSize(levelChoice, 20), getSize(levelChoice, 20));
	    c.fillOval(x + side / 2 - getSize(levelChoice, 15), y + side - getSize(levelChoice, 35), getSize(levelChoice, 15), getSize(levelChoice, 25));
	    c.fillOval(x + side / 2 - getSize(levelChoice, 14), y + side - getSize(levelChoice, 40), getSize(levelChoice, 18), getSize(levelChoice, 18));
	    c.setColor(Color.WHITE);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 6), y + side - getSize(levelChoice, 38), getSize(levelChoice, 10), getSize(levelChoice, 13));
	    //draws the eye
	    brightBrownOrange = new Color(240, 121, 53, 100);
	    c.setColor(brightBrownOrange);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 4), y + side - getSize(levelChoice, 38), getSize(levelChoice, 8), getSize(levelChoice, 8));
	    c.setColor(Color.BLACK);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 2), y + side - getSize(levelChoice, 36), getSize(levelChoice, 3), getSize(levelChoice, 3));
	} else if (card == 29) { //draws a jellyfish
	    //draws the tentacles
	    c.setColor(darkPurple);
	    for (i = 0; i < 4; i++) {
		c.drawArc(x + side / 2 - getSize(levelChoice, 16) + i * getSize(levelChoice, 8), y + getSize(levelChoice, 27), getSize(levelChoice, 8), getSize(levelChoice, 8), 90, 180);
		c.drawArc(x + side / 2 - getSize(levelChoice, 16) + i * getSize(levelChoice, 8), y + getSize(levelChoice, 35), getSize(levelChoice, 8), getSize(levelChoice, 8), 270, 180);
		c.drawArc(x + side / 2 - getSize(levelChoice, 16) + i * getSize(levelChoice, 8), y + getSize(levelChoice, 43), getSize(levelChoice, 8), getSize(levelChoice, 8), 90, 180);
		c.drawArc(x + side / 2 - getSize(levelChoice, 16) + i * getSize(levelChoice, 8), y + getSize(levelChoice, 51), getSize(levelChoice, 8), getSize(levelChoice, 8), 270, 180);
	    }
	    //draws the body
	    c.setColor(purple);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 20), y + getSize(levelChoice, 15), getSize(levelChoice, 40), getSize(levelChoice, 30), 0, 180);
	    c.setColor(lightPurple);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 10), y + getSize(levelChoice, 20), getSize(levelChoice, 20), getSize(levelChoice, 15), 0, 180);
	} else if (card == 30) { //draws a dolphin
	    //draws the body
	    c.setColor(purplePink);
	    for (i = 50; i >= 18; i--) {
		for (j = 0; j <= 12; j++) {
		    c.drawArc(x + side / 2 - getSize(levelChoice, 25), y + side / 2 - getSize(levelChoice, i / 2) + getSize(levelChoice, 15 + j / 6), getSize(levelChoice, 50 + j), getSize(levelChoice, i), 70, 110);
		}
	    }
	    c.fillOval(x + side / 2 - getSize(levelChoice, 10), y + side / 2 - getSize(levelChoice, 26) + getSize(levelChoice, 15), getSize(levelChoice, 35), getSize(levelChoice, 20));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 15), y + side / 2 - getSize(levelChoice, 12) + getSize(levelChoice, 15), getSize(levelChoice, 15), getSize(levelChoice, 5));
	    //draws the tail
	    xpos = new int[]{x + side / 2 - getSize(levelChoice, 25), x + side / 2 - getSize(levelChoice, 33), x + side / 2 - getSize(levelChoice, 25), x + side / 2 - getSize(levelChoice, 17)};
	    ypos = new int[]{y + side / 2 - getSize(levelChoice, 5) + getSize(levelChoice, 15), y + side / 2 + getSize(levelChoice, 10) - getSize(levelChoice, 5) + getSize(levelChoice, 15), y + side / 2 + getSize(levelChoice, 8) - getSize(levelChoice, 5) + getSize(levelChoice, 15), y + side / 2 + getSize(levelChoice, 10) - getSize(levelChoice, 5) + getSize(levelChoice, 15)};
	    c.fillPolygon(xpos, ypos, 4);
	    //draws the fins
	    for (i = 30; i >= 10; i--) {
		for (j = 0; j <= 4; j++) {
		    c.drawArc(x + side / 2 - getSize(levelChoice, i / 2) - getSize(levelChoice, 13 - j), y + side / 2 - getSize(levelChoice, 32) + getSize(levelChoice, 15) + getSize(levelChoice, 3), getSize(levelChoice, i), getSize(levelChoice, 30), 0, 90);
		    c.drawArc(x + side / 2 - getSize(levelChoice, i / 2) - getSize(levelChoice, 10 - j), y + side / 2 + getSize(levelChoice, 15) - getSize(levelChoice, 33) + getSize(levelChoice, 3), getSize(levelChoice, i), getSize(levelChoice, 30), 0, -90);
		}
	    }
	    //draws the eye
	    c.setColor(Color.BLACK);
	    c.fillOval(x + side / 2 + getSize(levelChoice, 15), y + getSize(levelChoice, 15) + side / 2 - getSize(levelChoice, 14), getSize(levelChoice, 3), getSize(levelChoice, 3));
	} else if (card == 31) { //draws a monkey
	    //draws the arms
	    c.setColor(Color.BLACK);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 20), y + side - getSize(levelChoice, 36), getSize(levelChoice, 40), getSize(levelChoice, 70), 0, 180);
	    //draws the chest
	    c.setColor(lightBlack);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 15), y + side - getSize(levelChoice, 36), getSize(levelChoice, 30), getSize(levelChoice, 70), 0, 180);
	    //draws the ears
	    c.setColor(paleYellowOrange);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 21), y + getSize(levelChoice, 23), getSize(levelChoice, 12), getSize(levelChoice, 17));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 9), y + getSize(levelChoice, 23), getSize(levelChoice, 12), getSize(levelChoice, 17));
	    //draws the face
	    c.setColor(paleBlack);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 15), y + getSize(levelChoice, 15), getSize(levelChoice, 30), getSize(levelChoice, 30));
	    c.setColor(paleYellowOrange);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 10), y + getSize(levelChoice, 20), getSize(levelChoice, 12), getSize(levelChoice, 12));
	    c.fillOval(x + side / 2 - getSize(levelChoice, 2), y + getSize(levelChoice, 20), getSize(levelChoice, 12), getSize(levelChoice, 12));
	    c.fillOval(x + side / 2 - getSize(levelChoice, 7), y + getSize(levelChoice, 26), getSize(levelChoice, 14), getSize(levelChoice, 14));
	    //draws the eyes
	    c.setColor(Color.BLACK);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 6), y + getSize(levelChoice, 25), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 3), y + getSize(levelChoice, 25), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    //draws the nose
	    c.fillOval(x + side / 2 - getSize(levelChoice, 2), y + getSize(levelChoice, 32), getSize(levelChoice, 2), getSize(levelChoice, 3));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 2), y + getSize(levelChoice, 32), getSize(levelChoice, 2), getSize(levelChoice, 3));
	} else if (card == 32) { //draws a rhino
	    //draws the chest
	    c.setColor(brownGray);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 20), y + side - getSize(levelChoice, 40), getSize(levelChoice, 40), getSize(levelChoice, 80), 0, 180);
	    //draws the legs
	    c.setColor(strongBrownGray);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 17), y + side - getSize(levelChoice, 12), getSize(levelChoice, 12), getSize(levelChoice, 22), 0, 180);
	    c.fillArc(x + side / 2 + getSize(levelChoice, 5), y + side - getSize(levelChoice, 12), getSize(levelChoice, 12), getSize(levelChoice, 22), 0, 180);
	    //draws the head
	    c.setColor(brightBrownGray);
	    c.fillRoundRect(x + side / 2 - getSize(levelChoice, 15), y + getSize(levelChoice, 15), getSize(levelChoice, 30), getSize(levelChoice, 40), getSize(levelChoice, 25), getSize(levelChoice, 25));
	    c.fillRoundRect(x + side / 2 - getSize(levelChoice, 12), y + getSize(levelChoice, 40), getSize(levelChoice, 24), getSize(levelChoice, 20), getSize(levelChoice, 20), getSize(levelChoice, 20));
	    //draws the ears
	    c.setColor(paleBrownGray);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 19), y + getSize(levelChoice, 11), getSize(levelChoice, 12), getSize(levelChoice, 14));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 7), y + getSize(levelChoice, 11), getSize(levelChoice, 12), getSize(levelChoice, 14));
	    //draws the tusk
	    c.setColor(Color.WHITE);
	    c.fillArc(x + side / 2 - getSize(levelChoice, 24), y, getSize(levelChoice, 48), getSize(levelChoice, 48), 260, 20);
	    //draws the eyes
	    c.setColor(Color.BLACK);
	    c.fillOval(x + side / 2 - getSize(levelChoice, 9), y + getSize(levelChoice, 35), getSize(levelChoice, 3), getSize(levelChoice, 3));
	    c.fillOval(x + side / 2 + getSize(levelChoice, 6), y + getSize(levelChoice, 35), getSize(levelChoice, 3), getSize(levelChoice, 3));
	}

	//draws a border around the card based on the current player
	if (player == 1) {
	    c.setColor(playerYellow);
	} else if (player == 2) {
	    c.setColor(playerRed);
	} else {
	    c.setColor(playerPurple);
	}
	for (i = 0; i <= getSize(levelChoice, 2); i++) {
	    c.drawRect(x + i, y + i, side - 2 * i - 1, side - 2 * i - 1);
	}
    }

    /*
    game method
    
    Purpose:
    Runs the game logistics, displaying cards and accepting input.
    
    Local Variable Dictionary:
    Name        |Type        |Purpose
    ------------|------------|------------
    player      |int         |used to store which player's turn it is
    numCard     |int         |used to store the number of cards in the current game
    x1          |int         |used to store the column for the first card entered
    y1          |int         |used to store the row for the first card entered
    x2          |int         |used to store the column for the second card entered
    y2          |int         |used to store the row for the second card entered
    pos         |int         |used to store the position of the current typing cursor
    input       |String      |used to temporarily store the typed username
    add         |int         |used to store how much to add to the user's score
    row         |int         |used as a counter variable in a loop, to count the row being drawn
    col         |int         |used as a counter variable in a loop, to count the column being drawn
    e           |Exception   |used for catching Exceptions
    
    Loops:
    - A for loop runs to draw a grid with
      labelled rows and columns and "covered" cards.
    - A while loop runs until there are no cards
      left to flip.
    - A while loop runs until the user enters
      a valid two cards to flip.
    
    Conditional Statements:
    - An if statement checks which row of the grid is being drawn, 
      to ensure that the column markings are only drawn once.
    - An if statement checks if the user has currently typed
      less than four characters when entering the card and only 
      displays the line for typing if so.
    - An if-else statement checks the key pressed by the user.
      If the key is ENTER, the program will then check if entered
      card is valid. Otherwise, it will process accordingly.
    - An if statement checks if string is too short or not,
      throwing an error if so.
    - An if statement checks if the current character is
      uppercase or lowercase, converting all to the same
      case to accept both when entering cards.
    - An if statement checks if entered card values are valid,
      i.e. in the correct range of values, throwing an error if not.
    - An if statement checks if the two values to flip are the same,
      throwing an error if so.
    - An if statement checks if the two entered usernames are
      the same, throwing an error if so.
    - An if statement checks if the user is able to currently
      press backspace, deleting previous character if so.
    - An if statement checks if user is able to currently add
      a character, adding the character if so.
    - An if statement checks if the user has entered to exit,
      leading them back to main menu if so.
    - An if-statement checks if two flipped cards are the same.
      If so, they are not covered and score is updated. If not,
      they are covered and score remains the same.
    - An if statement checks which player is currently going,
      and updates their score.
    
    Segment Block:
    A try-catch block catches IllegalArgumentException
    errors thrown when processing the entered cards.
    Possible errors thrown are: cards are not valid, cards are
    the same, and one or more of the cards have been flipped.
     */
    public void game() {
	//local variables
	int player = 1; //stores the current player
	int numCard = levelChoice * levelChoice; //stores the number of cards in the game
	int x1 = -1; //stores column of first card entered
	int y1 = -1; //stores row of first card entered
	int x2 = -1; //stores column of first card entered
	int y2 = -1; //stores row of first card entered
	int pos = 0; //stores position of the users typing
	String input = ""; //stores the currently entered username
	int add = 0; //stores how much to enter to the user's score
	int row; //loop counter variable
	int col; //loop counter variable

	title(); //draws the program title and clears the previous screen
	//draws the player information
	c.setColor(Color.BLACK);
	c.setFont(new Font("RakeslyEl-Regular", Font.PLAIN, 15));
	c.drawString("P L A Y E R   # 1   ( y e l l o w )", 10, 100);
	c.drawString("P L A Y E R   # 2   ( r e d )", 830, 100);
	c.drawString("Score: 0", 10, 120);
	c.drawString("Score: 0", 910, 120);
	//generates the random grid
	cardType = new int[levelChoice][levelChoice];
	randCard();
	//draws the grid with flipped over cards
	c.setFont(new Font("RakeslyEl-Regular", Font.PLAIN, 25));
	//loop runs through the rows and columns
	for (row = 0; row < levelChoice; row++) {
	    //displays the row number
	    c.setColor(Color.BLACK);
	    c.drawString("" + (row + 1), 251, 140 + (row + 1) * getSize(levelChoice, 78) - getSize(levelChoice, 30));
	    for (col = 0; col < levelChoice; col++) {
		if (row == 0) { //displays the column number
		    c.setColor(Color.BLACK);
		    c.drawString("" + (char) (col + 'A'), 270 + col * getSize(levelChoice, 78) + getSize(levelChoice, 30), 135);
		}
		//draws the card
		c.setColor(new Color(190, 212, 247));
		c.fillRect(270 + col * getSize(levelChoice, 78) + 3, 140 + row * getSize(levelChoice, 78) + 3, getSize(levelChoice, 78) - 6, getSize(levelChoice, 78) - 6);
	    }
	}
	//runs the game
	while (numCard > 0) { //loop runs until there are no more cards left to flip
	    c.setColor(new Color(206, 242, 245)); //light blue
	    c.fillRect(0, 600, 650, 70);
	    c.setColor(Color.BLACK);
	    //displays the game options
	    c.setFont(new Font("RakeslyEl-Regular", Font.PLAIN, 17));
	    c.drawString("E n t e r   ' e x i t '   t o   r e t u r n   t o   m a i n   m e n u .   A n y   p r o g r e s s   w i l l   b e   l o s t .", 10, 625);
	    c.drawString("P l a y e r   # " + player + " ,   w h i c h   c a r d s   w o u l d   y o u   l i k e   t o   f l i p   ( e x .   ' A 4 B 1 ' ) ?", 10, 655);
	    //variables to store the card entered by the user
	    x1 = -1;
	    y1 = -1;
	    x2 = -1;
	    y2 = -1;
	    pos = 0;
	    input = "";
	    while (true) { //loop that runs allowing the user to type in the card they want to enter
		c.setColor(Color.BLACK);
		if (pos < 4) { //only allows user to type if there is less than four characters
		    c.drawLine(510 + pos * 14, 660, 520 + pos * 14, 660); //draws the line indicating where to type
		}
		char in = c.getChar(); //stores the entered character
		if (in == 10) { //if user pressed ENTER, with ASCII value 10
		    if (input.equalsIgnoreCase("exit")) { //user entered "exit", signalling that they want to return to main menu
			exitToMenu = true; //signals that the user wants to return to main menu
			break; //exits the loop
		    }
		    try { //try-catch block catches errors
			if (pos < 4) { //if entered string is too short, cards are not valid
			    throw new IllegalArgumentException("Those are not valid cards."); //throws an exception that cards are not valid
			}
			//converts characters entered by the user into numeric values
			x1 = input.charAt(0) - 'a';
			if (input.charAt(0) < 'a') {
			    x1 = input.charAt(0) - 'A';
			}
			y1 = input.charAt(1) - '1';
			x2 = input.charAt(2) - 'a';
			if (input.charAt(2) < 'a') {
			    x2 = input.charAt(2) - 'A';
			}
			y2 = input.charAt(3) - '1';
			//checks if entered numeric values are valid
			if (x1 >= 0 && x1 < levelChoice && y1 >= 0 && y1 < levelChoice && x2 >= 0 && x2 < levelChoice && y2 >= 0 && y2 < levelChoice) {
			    if (x1 == x2 && y1 == y2) { //checks if the two cards are the same
				throw new IllegalArgumentException("They can't be the same card."); //throws and exception that cards cannot be the same
			    }
			    if (cardType[x1][y1] == 0 || cardType[x2][y2] == 0) { //checks if the cards ahve already been flipped
				throw new IllegalArgumentException("One or more of those cards have been flipped."); //throws an exception that one or more cards have been flipped
			    }
			    break; //exits the loop, entered cards were valid
			} else { //cards were not in range
			    throw new IllegalArgumentException("Those are not valid cards."); //throws an exception that cards are not valid
			}
		    } catch (IllegalArgumentException e) { //catches errors
			JOptionPane.showMessageDialog(null, e.getMessage() + " Please try again.", "[ ERROR ]", JOptionPane.ERROR_MESSAGE); //displays the error
			pos = 0; //sets the typing position back to 0
			input = ""; //re-sets the entered username
			c.setColor(new Color(206, 242, 245)); //light blue
			c.fillRoundRect(510, 630, 450, 40, 10, 10); //covers previous input
		    }
		} else { //entered character is not ENTER
		    if (in == 8 && pos > 0) { //if user pressed BACKSPACE, with ASCII value 09
			//erases previous entered character
			c.setColor(new Color(206, 242, 245)); //light blue
			c.drawLine(510 + pos * 14, 660, 520 + pos * 14, 660);
			pos--; //shifts position one down
			c.fillRect(505 + pos * 14, 630, 20, 40);
			input = input.substring(0, input.length() - 1); //shortens entered string by one
		    } else if (in != 8 && pos <= 3) {
			input += in; //adds entered character to username
			c.drawString("" + in, 512 + pos * 14, 655);
			c.setColor(new Color(206, 242, 245)); //light blue
			c.drawLine(510 + pos * 14, 660, 520 + pos * 14, 660);
			pos++; //shifts position up by one
		    }
		}
	    }
	    if (exitToMenu) { //user signalled to exit to the main menu
		break; //exits the loop
	    }
	    //draws the two entered cards
	    drawCard(cardType[x1][y1], 270 + x1 * getSize(levelChoice, 78) + 3, 140 + y1 * getSize(levelChoice, 78) + 3, player);
	    drawCard(cardType[x2][y2], 270 + x2 * getSize(levelChoice, 78) + 3, 140 + y2 * getSize(levelChoice, 78) + 3, player);
	    //pauses the screen for 2 seconds to show the cards
	    try {
		Thread.sleep(2000);
	    } catch (Exception e) {
	    }
	    add = 0; //stores how much to add to the user's score
	    if (cardType[x1][y1] != cardType[x2][y2]) { //two cards are not the same design
		//cover back over the cards
		c.setColor(new Color(190, 212, 247));
		c.fillRect(270 + x1 * getSize(levelChoice, 78) + 3, 140 + y1 * getSize(levelChoice, 78) + 3, getSize(levelChoice, 78) - 6, getSize(levelChoice, 78) - 6);
		c.fillRect(270 + x2 * getSize(levelChoice, 78) + 3, 140 + y2 * getSize(levelChoice, 78) + 3, getSize(levelChoice, 78) - 6, getSize(levelChoice, 78) - 6);
	    } else { //cards are equal
		numCard -= 2; //reduce the number of cards by 2
		add = 1; //adds 1 to user score
		//re-sets the cards
		cardType[x1][y1] = 0;
		cardType[x2][y2] = 0;
	    }
	    //draws the player's new score
	    c.setFont(new Font("RakeslyEl-Regular", Font.PLAIN, 15));
	    if (player == 1) { //updates score for player 1
		p1Score += add; //adds to player's score
		//covers older score
		c.setColor(new Color(206, 242, 245)); //light blue
		c.fillRect(40, 107, 30, 13);
		//displays new score
		c.setColor(Color.BLACK);
		c.drawString("" + p1Score, 45, 120);
		player = 2; //switches current player
	    } else if (player == 2) { //updates score for player 2
		p2Score += add; //adds to player's score
		//covers older score
		c.setColor(new Color(206, 242, 245)); //light blue
		c.fillRect(940, 107, 30, 13);
		//displays new score
		c.setColor(Color.BLACK);
		c.drawString("" + p2Score, 945, 120);
		player = 1; //switches current player
	    }
	}
    }

    /*
    gameEnd method
    
    Purpose:
    Displays which player won the game,
    player scores, and updates highscores.

    Conditional Statements:
    An if-statement checks which player won by
    comparing their scores.
     */
    public void gameEnd() {
	scoreSave(); //calls the method to update the current highscores
	title(); //draws the program title and clears the previous screen
	//displays the end message
	c.setColor(Color.BLACK);
	c.setFont(new Font("Baby Boss", Font.PLAIN, 25));
	c.drawString("GAME COMPLETE", 420, 120);
	c.setFont(new Font("RakeslyEl-Regular", Font.PLAIN, 17));
	//if-statement displays the winner
	if (p1Score > p2Score) { //player 1 won
	    c.drawString("T h e   w i n n e r   i s :   P l a y e r   1 !", 390, 160);
	} else if (p2Score > p1Score) { //player 2 won
	    c.drawString("T h e   w i n n e r   i s :   P l a y e r   2 !", 390, 160);
	} else { //players tied
	    c.drawString("I t   w a s   a   t i e.", 440, 160);
	}
	//displays the player scores
	c.drawString("P l a y e r   1   s c o r e d   " + p1Score + "   m a t c h e s .", 380, 210);
	c.drawString("P l a y e r   2   s c o r e d   " + p2Score + "   m a t c h e s .", 380, 235);
	//re-sets player scores
	p1Score = 0;
	p2Score = 0;
	pauseProgram(); //pauses program on current screen until user chooses to continue
    }

    /*
    scoreSave method
    
    Purpose:
    Used to update highscores after a game
    has been played.
    
    Local Variable Dictionary:
    Name        |Type            |Purpose
    ------------|----------------|------------
    input       |String          |used to store the user's input
    player      |String          |used to store the username of the current player
    numLines    |int             |used to store the number of lines in the file
    curScores   |String array    |used to store the current highscores
    temp        |String array    |used to store the updated highscores
    tokens      |String tokens   |used to store the read and split line from the file
    i           |int             |used as a counter variable in a loop, to count the lines in the file
    r           |int             |used as a counter variable in a loop, to count the current player
    score       |int             |used to store the score of the current player
    add         |boolean         |used to store if the current player has been added or not
    e           |Exception       |used for catching Exceptions
    in          |BufferedReader  |used for reading from a file
    out         |PrintWriter     |used for writing from a file
    
    Loops:
    - A for loop used to read all the lines in the file.
    - A for loop used to process for both players.
    - A for loop used to process all the lines in the file.
    - A for loop used to print all lines into the file.
    
    Conditional Statements:
    - An if statement used to check if the first line of the
      file is null, meaning the file is empty.
    - An if-else statement used set the variables for the
      current values being compared.
    - An if-else statement used to determine if the current
      player's score should be inserted at a position.
    - An if statement used to check if the player was not
      added and if it can be.
    
    Segment Block:
    A try-catch block used to catch errors when
    reading and writing to the file.
     */
    private void scoreSave() {
	//local variables
	String input = ""; //used to store the user's input
	String player = ""; //used to store the username of the current player
	int numLines = 0; //used to store the number of lines in the file
	String[] curScores; //used to store the contents of the current file
	String[] temp; //used to store the contents of the new file
	String[] tokens;
	int i; //loop counter variable
	int r; //loop counter variable
	int score = 0; //used to store the score of the current player
	boolean add; //used to store if the user's score has been added
	BufferedReader in; //used to read from highscore file
	PrintWriter out; //used to write from highscore file

	try { //try-catch block catches errors
	    in = new BufferedReader(new FileReader(SCORE_FILES[(levelChoice - 4) / 2])); //reads file depending on current level
	    input = in.readLine(); //reads first line of input, storing the number of players in the file
	    if (input != null) { //first line of input was not empty
		numLines = Integer.parseInt(input); //if current file is empty
	    }
	    curScores = new String[numLines]; //initializes array to store the current number of lines
	    //stores the current lines in the file 
	    for (i = 0; i < numLines; i++) {
		curScores[i] = in.readLine();
	    }
	    //loop runs twice to insert player #1 and player #2
	    for (r = 0; r < 2; r++) {
		//stores the score of the current player being compared
		if (r == 0) { //current player is player 1
		    score = p1Score;
		    player = p1;
		} else { //current player is player 2
		    score = p2Score;
		    player = p2;
		}
		temp = new String[Math.min(10, numLines + 1)]; //initializes array to store the new number of lines
		add = false; //variable to store if the username was added
		for (i = 0; i < numLines; i++) { //loop to run the number of lines in the file
		    tokens = curScores[i].split(" "); //used to split the two values, the username and the score
		    //compares the score to the current value, to see if player should be inputted here
		    if ((score == Integer.parseInt(tokens[1]) && player.toLowerCase().compareTo(tokens[0].toLowerCase()) <= 0) || (score > Integer.parseInt(tokens[1]))) {
			temp[i] = player + " " + score; //adds the player into this position
			add = true; //player was inserted into array
			for (int j = i; j < temp.length - 1; j++) { //inserts rest of the values into the array
			    temp[j + 1] = curScores[j];
			}
			break; //exits the loop
		    } else {
			temp[i] = curScores[i];
		    }
		}
		//if the line can be entered and has not been already
		if (numLines + 1 <= 10 && !add) {
		    temp[temp.length - 1] = player + " " + score;
		}
		//sets current array to new array
		curScores = temp;
		numLines = curScores.length;
	    }
	    //prints current array into the file
	    out = new PrintWriter(new FileWriter(SCORE_FILES[(levelChoice - 4) / 2])); //prints data depending on current level
	    out.println(Math.min(10, numLines)); //prints number of lines
	    for (i = 0; i < Math.min(10, numLines); i++) { //prints the data for the users
		out.println(curScores[i]);
	    }
	    out.close(); //closes the PrintWriter
	} catch (IOException e) { //catches errors
	    c.println("There was an error printing to the file.");
	}
    }

    /*
    highScores method
    
    Purpose:
    Used to display the highscores for the board
    selected by the user in the levelSelect method.

    Local Variable Dictionary:
    Name        |Type           |Purpose
    ------------|---------------|------------
    numLines    |int            |used to store the number of lines in the current file
    input       |String         |used to temporarily store the user's input
    name        |String         |used to store the name of the current user being displayed
    tokens      |String array   |used to store the read and split line from the file
    board       |String         |used to store the name of the current board
    e           |Exception      |used for catching Exceptions 
    in          |BufferedReader |used for reading from a file
    out         |PrintWriter    |used to write to a file
       
    Loops:
    - A for loop reads the lines in the file and displays them.
    - A for loop adds a space after each character in the username,
      to ensure proper formatting when displaying.
    - A while loop runs until valid input (y or n) is inputted.
    
    Conditional Statements:
    - An if statement used to check if the first line of the
      file is null, meaning the file is empty.
    - An if statement checks the current levelChoice variable,
      to determine the title of the current board.
    - An if statement checks if the number of lines in the file
      is equal to 0, displaying that there are no lines if so.
    - An if-else statement checks if the inputted character
      is equal to 'y' or 'n', clearing or not clearing the file
      accordingly.
      
    Segment Block:
    A try-catch block catches errors when reading or
    writing to the file.
     */
    public void highScores() {
	//local variables
	int numLines = 0; //used to store number of lines in the arrat
	String input = ""; //used to temporarily store input
	String name = ""; //used to story player name
	String[] tokens; //used to split input
	String board; //used to store the name of the game board
	BufferedReader in; //used to read from a file
	PrintWriter out; //used to write to a file

	title(); //draws the program title and clears the previous screen
	try { //try-catch block catches errors
	    in = new BufferedReader(new FileReader(SCORE_FILES[(levelChoice - 4) / 2])); //reads file depending on current level
	    input = in.readLine(); //reads first line of input, storing the number of players in the file
	    if (input != null) { //first line of input was not empty
		numLines = Integer.parseInt(input); //if current file is empty
	    }
	    //displays title
	    c.setColor(Color.BLACK);
	    c.setFont(new Font("Baby Boss", Font.PLAIN, 25));
	    c.drawString("HIGHSCORES", 440, 120);
	    //displays the gameboard
	    c.setFont(new Font("RakeslyEl-Regular", Font.PLAIN, 17));
	    //sets name of current board based on levelChoice variable
	    board = "e a s y   d i f f i c u l t y";
	    if (levelChoice == 6) {
		board = "m e d i u m   d i f f i c u l t y";
	    } else if (levelChoice == 8) {
		board = "h a r d   d i f f i c u l t y";
	    }
	    //if statement to check if the file is empty
	    if (numLines == 0) { //file is currently empty, displays that there are no highscores
		c.drawString("T h e r e   a r e   n o   h i g h s c o r e s   f o r   t h e   " + board + "   g a m e   b o a r d .", 230, 160);
	    } else { //file is not currently empty, displays the highscores
		//displays the titles
		c.drawString("S c o r e b o a r d   f o r   t h e   " + board + "   g a m e   b o a r d .", 290, 160);
		c.drawString("U s e r n a m e", 260, 210);
		c.drawString("S c o r e", 660, 210);
		c.drawLine(260, 215, 340, 215);
		c.drawLine(660, 215, 705, 215);
		//loop that reads the file information and displays it
		for (int i = 1; i <= numLines; i++) { //runs for the number of lines in the file
		    input = in.readLine(); //reads a line in the file
		    tokens = input.split(" "); //splits the line based on the username and the player's score
		    name = ""; //used to store the username of the player
		    for (int j = 0; j < tokens[0].length(); j++) { //spaces the username so that it can be displayed
			name += tokens[0].charAt(j) + " ";
		    }
		    //displays the username and the score
		    c.drawString(name, 260, 220 + i * 20);
		    c.drawString(tokens[1], 660, 220 + i * 20);
		}
		//gives user's option to clear the file
		c.drawString("W o u l d   y o u   l i k e   t o   c l e a r   t h e   f i l e   ( y / n ) ?", 310, 555);
		while (true) { //loop that runs until user has entered valid choice
		    input = "" + c.getChar(); //stores the key pressed by the user
		    if (input.equalsIgnoreCase("y")) { //user wishes to clear the file
			try { //try-catch block to catch errors
			    out = new PrintWriter(new FileWriter(SCORE_FILES[(levelChoice - 4) / 2])); //prints to file depending on current level
			    out.println("0"); //prints that there are 0 lines in the file
			    out.close(); //closes the PrintWriter
			} catch (IOException e) { //catches errors
			}
			//covers displayed highscore board
			c.setColor(new Color(206, 242, 245)); //light blue
			c.fillRect(0, 125, 975, 500);
			//displays that there are no highscores
			c.setColor(Color.BLACK);
			c.drawString("T h e r e   a r e   n o   h i g h s c o r e s   f o r   t h e   " + board + "   g a m e   b o a r d .", 230, 160);
			c.drawString("S c o r e   b o a r d   c l e a r e d .", 400, 555);
			break; //exits the loop
		    } else if (input.equalsIgnoreCase("n")) { //user does not wish to clear the highscores
			//covers the message
			c.setColor(new Color(206, 242, 245)); //light blue
			c.fillRect(310, 435, 500, 25);
			break; //exits the loop
		    }
		}
	    }
	} catch (IOException e) { //catches errors
	}
	pauseProgram(); //waits until user presses a key to continue
    }

    /*
    goodbye method
    
    Purpose:
    Displays the program goodbye message;
     */
    public void goodbye() {
	title(); //draws the program title and clears the previous screen
	//displays the title
	c.setColor(Color.BLACK);
	c.setFont(new Font("Baby Boss", Font.PLAIN, 25));
	c.drawString("GOODBYE", 455, 120);
	//displays the goodbye message
	c.setFont(new Font("RakeslyEl-Regular", Font.PLAIN, 17));
	c.drawString("T h a n k   y o u   f o r   p l a y i n g   C o n c e n t r a t i o n .", 335, 160);
	c.drawString("T h i s   p r o g r a m   w a s   m a d e   b y   N i c o l e   H a n .", 335, 185);
	pauseProgram(); //waits until the user presses a key to continue
	c.close(); //closes the console
    }

    /*
    program main menu
    
    Purpose:
    To run the game.
    
    Local Variable Dictionary:
    Name   |Type           |Purpose
    -------|---------------|----------
    a      |Concentration  |to run the game
    
    Loops:
    A while loop runs main menu and user's choice
    until the user chooses to exit.
    
    Conditional Statement:
    If-statements check which key the user has
    pressed to determine which menu option to display.
    */
    public static void main(String[] args) { //main menu of the java application
	Concentration a; //creates a new Concentration object
	a = new Concentration(); //initializes the new Concentration object
	a.splashScreen(); //displays program splashscreen
	//loop that runs until user chooses to exit
	while (true) {
	    a.mainMenu(); //displays program main menu
	    if (a.menuChoice.equalsIgnoreCase("A")) { //user wishes to see instructions
		a.instructions(); //displays program instructions
	    } else if (a.menuChoice.equalsIgnoreCase("B")) { //user wishes to play the game
		a.levelSelect(); //displays program level selection
		if (!a.exitToMenu) { //if user has not chosen to exit
		    a.userInput(); //displays program method to ask for usernames, if user did not choose to exit the current screens
		}
		if (!a.exitToMenu) { //if user has not chosen to exit
		    a.game(); //displays program game method, if user did not choose to exit the current screens
		}
		if (!a.exitToMenu) { //if user has not chosen to exit
		    a.gameEnd(); //displays end of game method, if user did not choose to exit the current screens
		}
	    } else if (a.menuChoice.equalsIgnoreCase("C")) { //user wishes to view the highscores
		a.levelSelect(); //displays program level selection
		if (!a.exitToMenu) { //if user has not chosen to exit
		    a.highScores(); //displays highscores method, if user did not choose to exit the current screens
		}
	    } else if (a.menuChoice.equalsIgnoreCase("D")) { //user wishes to exit the game
		break; //exits the loop
	    }
	    a.exitToMenu = false; //re-sets variable which stores if user wishes to exit current screens
	}
	a.goodbye(); //displays program goodbye method
    }
}
