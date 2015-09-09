import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Font;
/**
 * Write a description of class Counter here.
 * 
 * @author (Daniel) 
 * @version (a version number or a date)
 */
public class ScoreCounter extends Actor
{
    /**
     * Act - do whatever the Counter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        updateImage();
    }   
    /**
     * Make the image
     */
    private void updateImage()
    {
        setImage(new GreenfootImage(175, 24));//new Image 175 pixels long, 24 high
        GreenfootImage image = getImage();
        Font font = image.getFont();//gets font
        image.setFont(font.deriveFont(24.0F));  // use 24 pt. font for image
        image.clear();//clear previous image
        image.drawString("Score: "+Pacman.Counter, 1, 18);//has the text "Score" and gets the Score from the Pacman Counter
        setImage(image);
    }
}
