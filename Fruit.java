import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fruit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fruit extends Actor
{
    private int Type;
    public Fruit(int Type)
    {
       this.Type = Type;
       if(Type == 11)
       {
           setImage("cherries.png");
       }
       else if(Type == 12)
       {
           setImage("orange.png");
        }
        else if(Type == 13)
        {
            setImage("bananas.png");
        }
        else if(Type == 14)
        {
            setImage("grapes.gif");
        }
        else if(Type == 15)
        {
            setImage("stawberry.gif");
        }
    }
    /**
     * Act - do whatever the Fruit wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
