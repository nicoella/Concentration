/*
Name: Nicole Han
Program Name: Conceentration
Date: January 10, 2019
Teacher: Ms. Krasteva

The splashscreen class displays the splashscreen
for the program in it's own thread.

Instance Variable Dictionary:
Name         |Type             |Purpose
-------------|-----------------|----------------
c            |Console          |to display output and graphics
*/

import hsa.*; //gives access to the hsa class
import java.awt.*; //gives access to the awt class

public class Splashscreen extends Thread { //creates a new class called Splashscreen
    Console c; //instance variable of Console
    
    /*
    Splashscreen class constructor
    
    Purpose:
    Initializes variables, imports fonts.
    
    Local Variable Dictionary:
    Name     |Type        |Purpose
    ---------|------------|------------
    e        |Exception   |used for catching Exceptions
    
    Parameters:
    Console con - used to pass in a Console from the main
		  class to draw graphics on.
    
    Segment Block:
    A try-catch statement catches errors when importing fonts.
    */
    public Splashscreen(Console con) {
	c = con; //passes in from another class
	try { //try catch block catches errors
	    //imports fonts
	    //citation: http://www.java2s.com/Tutorials/Java/Graphics_How_to/Font/Load_font_from_ttf_file.htm
	    Font.createFont(Font.TRUETYPE_FONT,getClass().getResourceAsStream("/fonts/Hotplate(sRB).TTF")); //imports font from data folder
	} catch (Exception e) {
	}
    }
    /*
    run method
    
    Purpose:
    Used to call the method to draw the animation.
    */
    public void run() {
	draw(); //calls animation method
    }

    /*
    draw method
    
    Purpose:
    Displays the splashscreen animation.
    
    Local Variable Dictionary:
    Name          |Type        |Purpose
    --------------|------------|------------
    mediumBlue    |Color       |stores medium blue colour data
    orange        |Color       |stores orange colour data
    mediumOrange  |Color       |stores medium orange colour data
    darkBlue      |Color       |stores dark blue colour data
    lightOrange   |Color       |stores light orange colour data
    darkOrange    |Color       |stores dark orange colour data
    gray          |Color       |stores gray colour data
    yellow        |Color       |stores yellow colour data
    darkYellow    |Color       |stores dark yellow colour data
    lightGray     |Color       |stores light gray colour data
    red           |Color       |stores red colour data
    purple        |Color       |stores purple colour data
    black         |Color       |stores black colour data
    name          |String      |stores the title of the game
    i             |int         |used in loop counting
    j             |int         |used in loop counting
    e             |Exception   |used to catch Exceptions
    
    Loops:
    - Multiple for loops used to animate objects
      "fading in" from the background.
    - A for loop used to animate the two cards
      fipping over and displaying a smiley face.
    - A for loop used to draw the title one
      letter at a time.
      
    Conditional Statements:
    - An if statement used to set the colour of the
      card, depending on if it is flipped or not.
    - An if statement used to only display the smiley
      face if the card is half or more flipped.
    
    Segment Block:
    Try-catch statements are used when animating.
    */
    public void draw() {
	//local variables
	Color mediumBlue = new Color(100, 146, 176); //medium blue colour
	Color orange = new Color(242, 133, 82); //orange colour
	Color mediumOrange = new Color(255, 219, 201); //medium orange colour
	Color darkBlue = new Color(167, 199, 219, 30); //dark blue colour
	Color lightOrange = new Color(247, 213, 161); //light orange colour
	Color darkOrange = new Color(224, 189, 135); //dark orange colour
	Color gray = new Color(54, 53, 52); //gray colour
	Color yellow = new Color(242, 235, 203, 15); //yellow colour
	Color darkYellow = new Color(250, 236, 170, 30); //dark yellow colour
	Color lightGray = new Color(199, 198, 197, 30); //light gray colour
	Color red = new Color(252, 146, 146); //red colour
	Color purple = new Color(189, 190, 217); //purple colour
	Color black = new Color(10, 10, 10, 20); //black colour
	int i; //loop counter variable
	int j; //loop counter variable
	String name = "CONCENTRATION"; //stores game title

	//draws the background
	c.setColor(mediumBlue);
	c.fillRect(0, 0, 975, 700);
	//draws the neck
	c.setColor(darkOrange);
	c.fillRect(478,370,20,40);
	//draws the shirt
	c.setColor(red);
	c.fillArc(438,390,100,150,0,180);
	//draws the face
	c.setColor(lightOrange);
	c.fillOval(413,230,150,150);
	//draws the eyes, fading in
	c.setColor(gray);
	for(i=1; i<=10; i++) { //10 loop iterations to fade the eyes in
	    for(j=-1; j<=1; j+=2) { //draws two eyes
		c.fillRoundRect(487-j*60,310-i,2,i*2,5,5);
	    }
	    //slows the animation
	    try {
		Thread.sleep(50);
	    } catch (Exception e) {
	    }
	}
	//draws two cards, fading in
	c.setColor(darkBlue);
	for(i=1; i<=25; i++) { //25 loop iterations to fade the cards in
	    c.fillRect(250,170,109,109);
	    c.fillRect(617,170,109,109);
	    //slows the animation
	    try {
		Thread.sleep(10);
	    } catch (Exception e) {
	    }
	}
	
	//pause before drawing the lightbulb
	try {
	    Thread.sleep(500);
	} catch (Exception e) {
	}
	
	//draws the lightbulb, fading in
	for(i=1; i<=25; i++) { //25 loop iterations to fade the lightbulb in
	    c.setColor(yellow);
	    c.fillOval(448,95,80,80);
	    c.fillRect(473,165,30,10);
	    c.setColor(darkYellow);
	    c.fillOval(473,120,30,30);
	    c.fillRect(483,140,10,30);
	    c.drawLine(435,135,410,135);
	    c.drawLine(541,135,566,135);
	    c.drawLine(488+(int)(Math.cos(15)*53),135+(int)(Math.sin(15)*53),488+(int)(Math.cos(15)*78),135+(int)(Math.sin(15)*78));
	    c.drawLine(488-(int)(Math.cos(15)*53),135+(int)(Math.sin(15)*53),488-(int)(Math.cos(15)*78),135+(int)(Math.sin(15)*78));
	    c.drawLine(488+(int)(Math.cos(15)*53),135-(int)(Math.sin(15)*53),488+(int)(Math.cos(15)*78),135-(int)(Math.sin(15)*78));
	    c.drawLine(488-(int)(Math.cos(15)*53),135-(int)(Math.sin(15)*53),488-(int)(Math.cos(15)*78),135-(int)(Math.sin(15)*78));
	    c.drawLine(488,82,488,58);
	    c.setColor(lightGray);
	    c.fillRect(471,175,34,25);
	    //slows the animation
	    try {
		Thread.sleep(10);
	    } catch (Exception e) {
	    }
	}
	//draws the flipped cards
	darkBlue = new Color(167, 199, 219);
	for(i=109; i>=-109; i--) {
	    c.setColor(mediumBlue);
	    c.fillRect(304-Math.abs(i+1)/2,170,Math.abs(i+1),109);
	    c.fillRect(671-Math.abs(i+1)/2,170,Math.abs(i+1),109);
	    if(i<=0) { //card is on flipped side
		c.setColor(purple);
	    } else { //card has been flipped
		c.setColor(darkBlue);
	    }
	    c.fillRect(304-Math.abs(i)/2,170,Math.abs(i),109);
	    c.fillRect(671-Math.abs(i)/2,170,Math.abs(i),109);
	    if(i<=0) { //draws smiley-face if card is more than half-flipped
		c.setColor(gray);
		c.fillOval(304-Math.abs(i)/2+(int)(Math.abs(i)/109.0*(285-250)),205,(int)(Math.abs(i)/109.0*5),5);
		c.fillOval(304-Math.abs(i)/2+(int)(Math.abs(i)/109.0*(319-250))-(int)(Math.abs(i)/109.0*5),205,(int)(Math.abs(i)/109.0*5),5);
		c.drawArc(304-Math.abs(i)/2+(int)(Math.abs(i)/109.0*(304-(int)(Math.abs(i)/109.0*56)/2-250)),210,(int)(Math.abs(i)/109.0*56),35,180,180);
		c.fillOval(671-Math.abs(i)/2+(int)(Math.abs(i)/109.0*(285-250)),205,(int)(Math.abs(i)/109.0*5),5);
		c.fillOval(671-Math.abs(i)/2+(int)(Math.abs(i)/109.0*(319-250))-(int)(Math.abs(i)/109.0*5),205,(int)(Math.abs(i)/109.0*5),5);
		c.drawArc(671-Math.abs(i)/2+(int)(Math.abs(i)/109.0*(304-(int)(Math.abs(i)/109.0*56)/2-250)),210,(int)(Math.abs(i)/109.0*56),35,180,180);
	    }
	    //slows the animation
	    try {
		Thread.sleep(7);
	    } catch (Exception e) {
	    }
	}
	//draws the mouth
	c.setColor(gray);
	for(i=4; i<=60; i++) { //slowly increases the angle for the mouth
	    c.drawArc(448,280,80,70,270-i,i*2);
	    //slows the animation
	    try {
		Thread.sleep(10);
	    } catch (Exception e) {
	    }
	}
	//draws the title
	c.setFont(new Font("Hotplate (sRB)",Font.PLAIN,50));
	for(i=1; i<=name.length(); i++) { //display the title character-by-character
	    c.setColor(mediumOrange);
	    c.drawString(name.substring(0,i),282,530);
	    c.setColor(orange);
	    c.drawString(name.substring(0,i),285,533);
	    //slows the animation
	    try {
		Thread.sleep(100);
	    } catch (Exception e) {
	    }
	}
	//delay before moving on
	try {
	    Thread.sleep(1000);
	} catch (Exception e) {
	}
    }
}
