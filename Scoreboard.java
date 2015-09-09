import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)
import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;

/**
 * The ScoreBoard is used to display results on the screen. It can display some
 * text and several numbers.
 * 
 * @author M Kolling
 * @version 1.0
 */
public class Scoreboard extends Actor
{
    public static final float FONT_SIZE = 36.0f;
    public static final int WIDTH = 300;
    public static final int HEIGHT = 240;
    
    /**
     * Create a score board for the final result.
     */
    public Scoreboard(int score, int dot, int Ghost)
    {
        makeImage(" Score: ", score, "Ghost: ", Ghost,  "Dots:   ", dot);
    }

    /**
     * Make the score board image.
     */
    private void makeImage(String title, int score, String ghosts, int Ghost, String dots, int dot)
    {
        GreenfootImage image = new GreenfootImage(WIDTH, HEIGHT);//sets image = new GreenfootImage(WIDTH, HEIGHT)

        image.setColor(new Color(0, 0, 0, 160));//set color to black
        image.fillRect(0, 0, WIDTH, HEIGHT);//fill rectangle
        image.setColor(new Color(0, 255, 64, 100));//sets color to green
        image.fillRect(5, 5, WIDTH-15, HEIGHT-15);//fills the smaller rectangle
        Font font = image.getFont();
        font = font.deriveFont(FONT_SIZE);//sets font size to 36F
        image.setFont(font);
        image.setColor(Color.WHITE);//sets color to white
        image.drawString(title + score, 40, 70);//draws the title and total score
        image.drawString(ghosts + Ghost, 40, 135);//draws the Ghost title and the number of ghosts
        image.drawString(dots + dot, 40, 180);//draws the Dot title of the number of dots
        setImage(image);
    }
}