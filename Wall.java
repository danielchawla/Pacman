import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Jail here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wall extends Actor
{
    private int Type;
    public Wall(int Type)
    {
        this.Type = Type;
        if(Type == 1)
        {
            setImage("block.png");
        }
        else if(Type ==2){
            setImage("block2.png");
        }
    }
    /**
     * Act - do whatever the Jail wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
