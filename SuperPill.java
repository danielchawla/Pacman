import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SuperPill here.
 * 
 * @author (Daniel Chawla) 
 * @version (a version number or a date)
 */
public class SuperPill extends Dots
{
    private GreenfootImage image1;
    private GreenfootImage image2;
    private int i=0;//countdown timer
    private int a=0;//countdown timer
    public SuperPill()
    {
        image1 = new GreenfootImage("pill2.png");
        image2 = new GreenfootImage("pill2.png");
        setImage(image1);
    }
    /**
     * Act - do whatever the SuperPill wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (a == 0)
        {
            i = 35;//counter is 35
            setImage(image2);
            getImage().scale(12,12);//makes the dot larger
        }
        else if(i == 0)
        {
            a = 35;//counter equals 35
            setImage(image1);
            getImage().scale(7,7);//makes the dot smaller
        }
        i--;
        a--;
            
    }    
}
