import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Pacman here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pacman extends Mover
{
    public static int invincibleCounter = 0;
    public static int dotCounter = 0;//counts the dots
    public static int ghostCounter = 0;//counts the number of ghost eaten
    public static int Counter = 0;
    public static int pacmanLife = 6;
    private GreenfootImage image1;
    private GreenfootImage image2;
    private GreenfootImage PacmanLeft;//image used for animation when pacman is facing left
    private GreenfootImage PacmanLeft2;//image used for animation when pacman is facing left
    private int directionGoing = 1;//variable used to keep track of the directions pacman is facing
    private int a = 0;
    private int b = 0;
    public static int x_pos;
    public static int y_pos;
    private int moveWait = 0;//variable used as a countdown to delay pacman's movements
    public static int ghostEaten;//keeps track of the number of ghost eaten while Pacman is invincible, resets afterward
    public static int fruitEaten;

    
    public Pacman()
    {
        image2= new GreenfootImage("pacman_close.png");//images used for animations
        image1= new GreenfootImage("pacman4.png");//images used for animations
        setImage(image2);
        PacmanLeft = new GreenfootImage("pacman_close.png");//images used for animations
        PacmanLeft.mirrorHorizontally();//flips picture
        PacmanLeft2 = new GreenfootImage("pacman4.png");//images used for animations
        PacmanLeft2.mirrorHorizontally();//flips picture
    }
    
    /**
     * Act - do whatever the Pacman wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public void act() 
    {
        x_pos = getX();//variables that keep track of pacman's position
        y_pos = getY();//variables that keep track of pacman's position
        
        if(moveWait == 0) {
            if(canMove(getRotation()) == true)//if it can move...
            {
                move();
                animation();
                moveWait = 2;//pacman moves every 2 acts
            }
            else
            {
                if(directionGoing ==1)//changes images depending on what way he's facing
                {
                    setImage(image2);
                }
                else
                {
                    setImage(PacmanLeft);
                }
            }
        }
        eat();
        turn();
        teleport();
        invincibleCounter--;
        faceDirection();
        if(moveWait > 0)
        {
            moveWait--;
        }
    }
    /**
     * Eating Method
     */
    private void eat()
    {
        if(canEat(Pill.class))
        {
            eat(Pill.class);
            dotCounter++;
            incrementScore(5);
        }
        if(canEat(Fruit.class))
        {
            eat(Fruit.class);
            incrementScore(400*((int)Math.pow(2,fruitEaten)));
            getWorld().addObject(new KillCounter(400*((int)Math.pow(2,fruitEaten))), getX(), getY()-15);
            fruitEaten++;
        }
            
        if(canEat(SuperPill.class))
        {
            eat(SuperPill.class);
            invincibleCounter = 2000;//Pacman turns Invincible!!!
            incrementScore(5);
        }
        if(canEat(Ghost.class) && getGhost().ghostDead == 0)//if can eat and the ghost isn't dead
        {
            if(invincibleCounter > 0 && getGhost().eatable == true)//checks the invincible counter and sees if ghost is eatable
            {
                getGhost().ghostDead = 1;//ghost is dead
                ghostCounter++;
                incrementScore(200*((int)Math.pow(2,ghostEaten)));//adds to score exponentially dpeding on how ghost is eaten during Pacman's invincibility. Score starts at 200 increases exponentially.
                getWorld().addObject(new KillCounter(200*((int)Math.pow(2,ghostEaten))), getX(), getY()-15);//adds a KillCounter with the number of points from that certain ghost. counter is added 15 pixels above him
            }
            if(invincibleCounter > 0)
            {
                ghostEaten++;
            }
        }
        if(invincibleCounter<=0)
        {
            ghostEaten = 0;//resets the counter
        }
        
    }
    /**
     * Keeps the main score
     */
   public void incrementScore(int ScoreGame)
   {
       Counter = Counter + ScoreGame;//adds the ingame score to the previous value
    }
    
    /**
     * Gets a specific ghost that pacman has intersected
     */
    private Ghost getGhost() 
    {
          return (Ghost)getOneObjectAtOffset(0,0, Ghost.class);
    }
    
    /**
     * Turn method (Control pacman by using the different arrow keys)
     */
    private void turn()//pretty self explanatory
    {
        if(canTurn() == true)
        {
            if(Greenfoot.isKeyDown("up") && canMove(NORTH))
            {
                setRotation(NORTH);        
            }
            else if(Greenfoot.isKeyDown("right")&& canMove(EAST))
            {
                setRotation(EAST);
            }
            else if(Greenfoot.isKeyDown("down")&& canMove(SOUTH))
            {
                setRotation(SOUTH);
            }
            else if(Greenfoot.isKeyDown("left")&& canMove(WEST))
            {
                setRotation(WEST);
            }
        }
    }
        
    
    /**
     * Animation. This is done by switching two of four possible images.
     * There are four different pictures, two of pacman facing one way,
     * two the other way. This method gets the variable direction going
     * and sets the images accordingly, there are 2 counters to delay the
     * switching of images.
     */
    public void animation()
    {
        if(a == 0)
        {
            b = 11;//sets counter to a new value
            if(directionGoing == 1)
            {
                setImage(image2);
            }
            else
            {
                setImage(PacmanLeft);
            }
        }
        else if(b == 0)
        {
            a = 11;//sets counter to a new value
            if(directionGoing == 1)
            {
                setImage(image1);
            }
            else
            {
                setImage(PacmanLeft2);
            }
        }
        b--;//decrements counter
        a--;//decrements counter
    }
    /**
     * Method so that when Pacman is going a certain direction,
     * a variable is set. The variable is used in the animation
     * method.
     */
    private void faceDirection()
    {
        if(getRotation() == WEST)
        {
            directionGoing = 0;
        }
        else if(getRotation() == EAST)
        {
            directionGoing = 1;
        }
    }
}
