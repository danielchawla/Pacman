import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Font;
import java.awt.Color;

/**
 * Write a description of class Counter here.
 * 
 * @author (Daniel Chawla (with help from Nolan Adams)) 
 * @version (a version number or a date)
 */
public class KillCounter extends Actor
{
    private int lifeLength;//countdown timer
    private int moveWait;//used to delay the upward movement of the kill timer
    private int pointsAcquired;//gets the point acquired for the certain ghost
    
    public KillCounter(int Score)
    {
        lifeLength = 200;//how long that counter is displayed for
        pointsAcquired = Score;//gets the score from Pacman
        GreenfootImage image = new GreenfootImage(21,21);//scales the image
        setImage(image);
        updateImage();
        
    }
    /**
     * Act - do whatever the Counter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        updateImage();
        if(moveWait == 0)
        {
            setLocation(getX(), getY()-1);//has the timer move upwards
            moveWait = 6;
        }
        if(lifeLength > 0)
        {
            lifeLength--;//decrements life
        }
        if(lifeLength <= 0)//removes object when lifeLength is 0
        {
            getWorld().removeObject(this);
        }
        moveWait--;
    }   
    /**
     * Make the image
     */
    private void updateImage()
    {
        setImage(new GreenfootImage(40, 24));//sets image to be a new Greenfoot Image
        GreenfootImage image = getImage();// set image to variable getImage
        Font font = image.getFont();
        image.setFont(font.deriveFont(14.0F));
        image.setColor(new Color(255,255,255));//sets color to blue
        image.drawString("  "+pointsAcquired,1,18);//draw text + value
        setImage(image);
    }
}