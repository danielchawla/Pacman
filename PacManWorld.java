import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class PacManWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PacManWorld extends World
{
    private int fruitRespawn = 6500;
    private int fruitType;
    private int fruitLife = 1700;
    private int endgameDelay = 1;
    private int axisX = 0;
    private int axisY = 0;
    public int[][] Map = {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                          {1,6,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,10,1},
                          {1,0,1,1,1,0,1,1,1,9,1,1,1,1,0,1,0,1,1,1,1,9,1,1,1,0,1,1,1,0,1},
                          {1,0,1,1,0,5,1,1,1,9,1,1,1,1,0,1,0,1,1,1,1,9,1,1,1,0,0,1,1,0,1},
                          {1,0,0,0,0,1,0,0,0,0,0,1,1,1,0,9,0,1,1,1,0,0,0,0,0,1,0,0,0,5,1},
                          {1,1,1,1,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,0,0,1,1,1,1},
                          {9,9,9,1,0,1,1,1,0,1,0,1,1,1,0,1,0,1,1,1,0,1,0,1,1,1,0,1,9,9,9},
                          {1,1,1,1,0,1,1,1,0,1,0,1,1,1,0,1,0,1,1,1,0,1,0,1,1,1,0,1,1,1,1},
                          {1,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                          {1,0,1,1,1,9,1,9,1,1,1,9,1,9,1,1,1,9,1,9,1,1,1,9,1,9,1,1,1,0,1},
                          {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                          {1,1,1,1,1,0,1,1,1,0,1,1,0,2,2,7,2,2,0,1,1,1,0,1,1,0,1,1,1,1,1},
                          {0,0,0,0,0,0,1,1,1,0,1,1,0,2,9,4,9,2,0,1,1,1,0,1,1,0,0,0,0,0,0},
                          {1,1,1,1,1,0,1,1,1,0,1,1,0,2,2,2,2,2,0,1,1,1,0,1,1,0,1,1,1,1,1},
                          {1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,9,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1},
                          {1,0,1,1,1,9,1,9,1,1,1,9,1,9,1,1,1,9,1,9,1,1,1,9,1,9,1,1,1,0,1},
                          {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,5,1},
                          {1,1,1,1,0,1,1,1,0,1,0,1,1,1,0,1,0,1,1,1,0,1,0,1,1,1,0,1,1,1,1},
                          {9,9,9,1,0,1,1,1,0,1,0,1,1,1,0,1,0,1,1,1,0,1,0,1,1,1,0,1,9,9,9},
                          {1,1,1,1,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,0,0,1,1,1,1},
                          {1,5,0,0,0,1,0,0,0,0,0,1,1,1,0,9,0,1,1,1,0,0,0,0,0,1,0,0,0,0,1},
                          {1,0,1,1,0,0,1,1,1,9,1,1,1,1,0,1,0,1,1,1,1,9,1,1,1,0,5,1,1,0,1},
                          {1,0,1,1,1,0,1,1,1,9,1,1,1,1,0,1,0,1,1,1,1,9,1,1,1,0,1,1,1,0,1},
                          {1,10,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,7,1},
                          {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
    /**
     * Constructor for objects of class PacManWorld.
     */
    public PacManWorld()
    {    
        // Create a new world with 20x20 cells with a cell size of 10x10 pixels.
        super(651, 525, 1);
        
        // Put in your own background image by replacing "greenfoot.png" with the
        // image that should be used as background and uncommenting the line below.
        setBackground("background2.png");
        setPaintOrder(Scoreboard.class, LivesCounter.class, ScoreCounter.class, KillCounter.class, Ghost.class, Pacman.class, Fruit.class, Dots.class, Wall.class);
        MakeWorld();
    }
    private void MakeWorld()
    {
        while(axisX < 31)
        {
            axisY= 0;
            while(axisY < 25)
            {
                if(Map[axisY][axisX] == 1)
                {
                    addObject(new Wall(1), 10+axisX*21, 10+axisY*21);
                }
                else if(Map[axisY][axisX] == 2)
                {
                    addObject(new Wall(2), 10+axisX*21, 10+axisY*21);
                }
                else if(Map[axisY][axisX] == 3)
                {
                    addObject(new Pacman(), 10+axisX*21, 10+axisY*21);
                }
                else if(Map[axisY][axisX] == 4)
                {
                    addObject(new Ghost(false,2), 10+axisX*21, 10+axisY*21);
                }
                else if(Map[axisY][axisX] == 6)
                {
                    addObject(new Ghost(true, 3), 10+axisX*21, 10+axisY*21);
                }
                else if(Map[axisY][axisX] == 7)
                {
                    addObject(new Ghost(true, 2), 10+axisX*21, 10+axisY*21);
                }
                else if(Map[axisY][axisX] == 8)
                {
                    addObject(new Ghost(true, 4), 10+axisX*21, 10+axisY*21);
                }
                else if(Map[axisY][axisX] == 10)
                {
                    addObject(new Ghost(false, 3), 10+axisX*21, 10+axisY*21);
                }
                else if(Map[axisY][axisX] == 0)
                {
                    addObject(new Pill(), 10+axisX*21, 10+axisY*21);
                }
                else if(Map[axisY][axisX] == 5)
                {
                    addObject(new SuperPill(), 10+axisX*21, 10+axisY*21);
                }
                axisY++;
            }
            axisX++;
        }
        addObject(new ScoreCounter(), 87, 13);
        addObject(new LivesCounter(), 609, 13);
        addObject(new Pill(), 31, 493);
        addObject(new Pill(), 619, 493);
        addObject(new Pill(), 619, 31);
        addObject(new Pill(), 31, 31);
        addObject(new RespawnZone(), 325, 250);
        Pacman.pacmanLife = 6;
        Pacman.invincibleCounter = 0;
        Pacman.Counter = 0;
        Pacman.dotCounter = 0;
        Pacman.ghostCounter = 0;
    }
    public void act()
    {
        List Dots = getObjects(Dots.class);//gets the list of dots
        if (Dots.isEmpty())
        {
               gameOver1();//if there are no dots, it will call the gameOver1 method
        }
        List Pacman = getObjects(Pacman.class);//
        if (Pacman.isEmpty() && endgameDelay <=0)
        {
               gameOver2();
        }
        pacmanRespawn();
        if(fruitRespawn == 0)
        {
            if(fruitType > 4)
            {
                fruitType = 0;
            }
            addObject(new Fruit(11+fruitType),325,304);
            fruitLife = 1700;
            fruitType++;
        }
        if(fruitLife >= -2 && fruitLife <=0)
        {
           removeObjects(getObjects(Fruit.class));
        }
        if(fruitLife > 0 && fruitRespawn <= 0)
        {
            fruitLife--;
        }
        fruitRespawn--;
        if(fruitLife == 0)
        {
            fruitRespawn = 6500;
            fruitLife = -1;
        }
        
    }
    /**
     * First game over scenario
     */
    public void gameOver1()
    {
        Greenfoot.stop();
        addObject(new Scoreboard(Pacman.Counter, Pacman.dotCounter, Pacman.ghostCounter), getWidth()/2, getHeight()/2);
    }
    
    /**
     * Second game over scenario
     */
    public void gameOver2()
    {
        Greenfoot.stop();
        addObject(new Scoreboard(Pacman.Counter, Pacman.dotCounter, Pacman.ghostCounter), getWidth()/2, getHeight()/2);
    }
    
    /**
     * Pacman Respawn
     */   
    public void pacmanRespawn()
    {
        if(Pacman.pacmanLife == 5)
        {
            List ghost = getObjects(Ghost.class);
            removeObjects(ghost);
            addObject(new Ghost(false,2), 619, 493);
            addObject(new Ghost(false,2), 325, 262);
            addObject(new Ghost(true, 3), 31, 31);
            addObject(new Ghost(true, 2), 325, 241);
            addObject(new Ghost(true, 4), 325, 220);
            addObject(new Ghost(false, 3), 619, 31);
            addObject(new Ghost(false, 3), 31, 493);
            addObject (new Pacman(), 325, 346);
            Pacman.pacmanLife--;
            Greenfoot.stop();
        }
        else if(Pacman.pacmanLife == 3)
        {
            List ghost = getObjects(Ghost.class);
            removeObjects(ghost);
            addObject(new Ghost(false,2), 619, 493);
            addObject(new Ghost(false,2), 325, 262);
            addObject(new Ghost(true, 3), 31, 31);
            addObject(new Ghost(true, 2), 325, 241);
            addObject(new Ghost(true, 4), 325, 220);
            addObject(new Ghost(false, 3), 619, 31);
            addObject(new Ghost(false, 3), 31, 493);
            addObject (new Pacman(), 325, 346);
            Pacman.pacmanLife--;
            Greenfoot.stop();
        }
        else if(Pacman.pacmanLife == 1)
        {
            List ghost = getObjects(Ghost.class);
            removeObjects(ghost);
            addObject(new Ghost(false,2), 619, 493);
            addObject(new Ghost(false,2), 325, 262);
            addObject(new Ghost(true, 3), 31, 31);
            addObject(new Ghost(true, 2), 325, 241);
            addObject(new Ghost(true, 4), 325, 220);
            addObject(new Ghost(false, 3), 619, 31);
            addObject(new Ghost(false, 3), 31, 493);
            Pacman.pacmanLife--;
            endgameDelay--;
        }
    }
}
