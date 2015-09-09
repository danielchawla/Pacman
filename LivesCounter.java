import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Font;
/**
 * Write a description of class Counter here.
 * 
 * @author (Daniel Chawla) 
 * @version (a version number or a date)
 */
public class LivesCounter extends Actor
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
        setImage(new GreenfootImage(100, 24));//new image 100 pixels long and 24 high
        GreenfootImage image = getImage();
        Font font = image.getFont();//gets font
        image.setFont(font.deriveFont(24.0F));  // use 24 pt. font for image
        image.clear();//clear previous image
        image.drawString("Lives: "+Pacman.pacmanLife/2, 1, 18);//Has the text Lives and gets the value for Pacmans lives and divides by 2
        setImage(image);
    }
}
