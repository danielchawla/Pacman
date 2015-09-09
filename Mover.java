import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class MovingThing here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mover extends Actor
{
    protected static final int NORTH = 0;
    protected static final int EAST = 1;
    protected static final int SOUTH = 2;
    protected static final int WEST = 3;

    
    /**
     * Act - do whatever the MovingThing wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Mover()
    {
        getImage().scale(21,21);
    }
    public void act() 
    {
        
    }
    
    public void move()
    {
        switch(getRotation()) {
            case SOUTH :
                setLocation(getX(), getY() + 1);
                break;
            case EAST :
                setLocation(getX() + 1, getY());
                break;
            case NORTH :
                setLocation(getX(), getY() - 1);
                break;
            case WEST :
                setLocation(getX() - 1, getY());
                break;
        }
    }
    
    /**
     * Test to see if the object can move. Borrowed from wombats2 scenario.
     */
    public boolean canMove(int Direction)
    {
        World myWorld = getWorld();
        int x = getX();
        int y = getY();
        switch(Direction) {
            case SOUTH :
                y= y+11;
                break;
            case EAST :
                x= x+11;
                break; 
            case NORTH :
                y= y-11;
                break;
            case WEST :
                x= x-11;
                break;
        }
        // test for outside border
        if (x >= myWorld.getWidth() || y >= myWorld.getHeight()) {
            return false;
        }
        else if (x < 0 || y < 0) {
            return false;
        }
        List Wall2 = myWorld.getObjectsAt(x, y, Wall.class);
        if(Wall2.isEmpty()) {
           return true;
        }
       else {
           return false;
         }
    }
    
    /**
     * Check if can eat
     */
    public boolean canEat(Class Clss)
    {
        Actor food = getOneObjectAtOffset(0, 0, Clss);
        return food != null;
    }
    
    /**
     * Main eat method
     */
    public void eat(Class Clss)
    {
        Actor food = getOneObjectAtOffset(0, 0, Clss);
        if(food != null)
        {
            getWorld().removeObject(food);
        }
    }
    /**
     * Checks to see if Object CanTurn
     */        
     public boolean canTurn()
     {
          if((getX()-10)%21 == 0 && ((getY()-10)%21) == 0)
          {
              return true;
          }
          else
          {
              return false;
          }
     }
     public void teleport()
     {
         if(getY() == 262 && getX() == 10)
         {
             setLocation(639, 262);
         }
         if(getY() == 262 && getX() == 640)
         {
             setLocation(11, 262);
         }
     }
}
