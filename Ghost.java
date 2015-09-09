import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ghost here.
 * 
 * @author (Daniel Chawla) 
 * @version (a version number or a date)
 */
public class Ghost extends Mover
{  
    private int timer = 0;
    private boolean tracking;//if tracking = true then that ghost will chase you
    private GreenfootImage image1;
    private GreenfootImage image2;
    private GreenfootImage image3;
    public boolean eatable = false;//if Pacman can eat it
    public int ghostDead = 0;
    private int moveWait = 0;//timer to delay movement
    private int moveTimer;
    private int respawnTimer;
    
    /**
     * Act - do whatever the Ghost wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Ghost(boolean track, int Speed)
    {
        super();
        moveTimer= Speed;
        tracking = track;
        image1 = new GreenfootImage("ghost2.png");
        image2 = new GreenfootImage("ghost1.png");
        image3 = new GreenfootImage("ghost_dead.png");
        setImage(image1);
    }
    public void act() 
    {
        if(Pacman.invincibleCounter == 1999)
        {
            eatable = true;//makes the ghost eatable
        }
        else if(Pacman.invincibleCounter <= 0)
        {
            eatable = false;//makes the ghost not eatable
        }
        ghostMove();
        if(canEat(Pacman.class))
        {
            if((Pacman.invincibleCounter < 1 || eatable == false) && ghostDead == 0)//if Pacman is not invincible and the Ghost isn't dead and he is eatable
            {
                eat(Pacman.class);
                Pacman.pacmanLife--;
            }
        }
        if(ghostDead == 1)//if ghost is dead
        {
            if(atHome() == true)
            {
                respawnTimer = 300;
                ghostDead = 0;//ghost is alive
                setImage(image1);//changes back to the original image
                setRotation(NORTH);//sets the direction to be north
                eatable = false;//no longer eatable
            }
            else
            {
                setImage(image3);//if he isn't at home and the ghost is dead, it will set image to image 3
                goHome();//the ghost will find his way home
            }
        }
        if(Pacman.invincibleCounter == 1999)//Switches image for ghost (after Pacman has eaten a SuperPill
        {
            setImage("ghost1.png");
        }
        if(Pacman.invincibleCounter == 1)//switches image back to normal when the ghost Pacman isn't invinvible
        {
            setImage("ghost2.png");
        }
        if(Pacman.invincibleCounter < 500 && ghostDead == 0 && eatable == true)//this is used so the when the invincible counter is less than five hundred the ghost will switch images
        {
            if(Pacman.invincibleCounter % 100 == 0 && Pacman.invincibleCounter > 1 )//when the counter is divisible by 100 and is greater than one
            {
                setImage(image2);
            }
            else if(Pacman.invincibleCounter % 50 == 0 && Pacman.invincibleCounter > 1 )//when counter is divisible by 50 and greater than one
            {
                setImage(image1);
            }
        }

        teleport();
        if(timer > 0)
        {
            timer--;//decrements timer
        }
            
    }

    /**
     * Detects if Ghost are at an intersections
     */
    public boolean atIntersection()
    {
        int m = 0;//int used to add up the number of possible turns a ghost can make
        if(moveNorth() == true){
            m++;
        }
        if(moveSouth() == true){
            m++;
        }
        if(moveEast() == true){
            m++;
        }
        if(moveWest() == true){
            m++;
        }
        if(m >= 3)// if the ghost can make more than 2 turns, he is at an intersection
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
    /**
     * Artificial Inteligence for the ghost. If the ghost is at an intersection and it can turn
     * and the timer is zero. Greenfoot will get a random number between zero and 3 and depending
     * on the number, the ghost will move a certain direction.
     */
    public void ai()
    {
        if(canTurn() == true && atIntersection() == true &&timer==0)
        {
            int i = 0;
            i = Greenfoot.getRandomNumber(4);
            switch(i) {
                case 0 :
                    if(getRotation() != NORTH) {
                        setRotation(SOUTH);//if the random number is zero, the ghost will go south
                    }
                    break;
               case 2 :
                    if(getRotation() != SOUTH) {
                        setRotation(NORTH);//if the random number is 2, the ghost will go north
                    }
                    break;
               case 1 :
                    if(getRotation() != WEST) {
                        setRotation(EAST);//if the random number is 1, the ghost will go east
                    }
                    break;
               case 3 :
                    if(getRotation() != EAST) {
                        setRotation(WEST);//if the random number is 3, the ghost will go west
                    }
                    break;
                }
             timer = 3;
        }              
    }
    /**
     * Checks if Ghost can Move in each direction. It does this by getting the Wall class
     * and seeing if is 11 pixels away in a certain direction.
     */
    private boolean moveNorth()
    {
        Actor Wall = getOneObjectAtOffset(0, -11, Wall.class);//checks if wall is 11 pixels above the ghost
        if(Wall != null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    private boolean moveSouth()
    {
        Actor Wall = getOneObjectAtOffset(0, 11, Wall.class);//checks if wall is 11 pixels south of the ghost
        if(Wall != null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    private boolean moveEast()
    {
        Actor Wall = getOneObjectAtOffset(11, 0, Wall.class);//checks if wall is 11 pixels to the right of the ghost
        if(Wall != null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    private boolean moveWest()
    {
        Actor Wall = getOneObjectAtOffset(-11, 0, Wall.class);//checks if wall is 11 pixels to the left of the ghost
        if(Wall != null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    /**
     * This method is used to find it's position is relation to Pacman's. It does this
     * by comparing the X & Y values using that information to determine if Pacman is
     * to the top left, top right, bottom left, or bottom right of the ghost.
     * (made with help from Cole Adams)
     */
    private int quadrantToPacman()
    {
        int x = Pacman.x_pos;//pacman's x coordinate
        int y = Pacman.y_pos;//pacman's y coordinate
        if(getX() > x)
        {
            if(getY() > y)
            {
                return 1;
            }
            else
            {
                return 2;
            }
        }
        else
        {
           if(getY() > y)
            {
                return 3;
            }
            else
            {
                return 4;
            }
        }

    }
    /**
     * Method used to chase Pacman. Depending on what values the quadrantToPacman method returned
     * this method will make ghost move to closer to Pacman. It does this by first trying to go to
     * in one of the two directions. If it can't move in those directions, the ghost will move away
     * from Pacman and try to follow Pacman again.
     */
    private void chase()
    {
        if(canTurn() == true)
        {
        switch(quadrantToPacman())
        {
            case 1: 
            if(moveNorth() == true && getRotation() != SOUTH)
            {
                setRotation(NORTH);
            }
            else if(moveWest() == true && getRotation() != EAST)
            {
                setRotation(WEST);
            }
            else if(moveEast() == true && getRotation() != WEST)
            {
                setRotation(EAST);
            }
            else if(moveSouth() == true && getRotation() != NORTH)
            {
                setRotation(SOUTH);
            }            
            break;
            
            case 2:
            if(moveWest() == true && getRotation() != EAST)
            {
                setRotation(WEST);
            }
            else if(moveSouth() == true && getRotation() != NORTH)
            {
                setRotation(SOUTH);
            }
            else if(moveNorth() == true && getRotation() != SOUTH)
            {
                setRotation(NORTH);
            }
            else if(moveEast() == true && getRotation() != WEST)
            {
                setRotation(EAST);
            }
            break;
            
            case 3:
            if(moveEast() == true && getRotation() != WEST)
            {
                setRotation(EAST);
            }
            else if(moveNorth() == true && getRotation() != SOUTH)
            {
                setRotation(NORTH);
            }
            else if(moveWest() == true && getRotation() != EAST)
            {
                setRotation(WEST);
            }
            else if(moveSouth() == true && getRotation() != NORTH)
            {
                setRotation(SOUTH);
            }
            break;
            
            case 4:
            if(moveSouth() == true && getRotation() != NORTH)
            {
                setRotation(SOUTH);
            }
            else if(moveEast() == true && getRotation() != WEST)
            {
                setRotation(EAST);
            }
            else if(moveNorth() == true && getRotation() != SOUTH)
            {
                setRotation(NORTH);
            }
            else if(moveWest() == true && getRotation() != EAST)
            {
                setRotation(WEST);
            }
            break;
        }
    }
}
    /**
     * Used to detect where the respawn zone in relation
     * to where the ghost is currently. Same principle as
     * the quadrantToPacman() method
     */
    private int quadrantToHome()
    {
         RespawnZone Respawn =RespawnZone.getHome();
         int x = Respawn.getX();//gets x coordinate of the respawn zone
         int y = Respawn.getY();//gets y coordinate of the respawn zone
         if(getX() > x)
         {
            if(getY() > y)
            {
                return 1;
            }
            else
            {
                return 2;
            }
        }
           else
           {
                if(getY() > y)
                {
                    return 3;
                }
                else
                {
                    return 4;
                }
            }
      }
      /**
       * Method to make the ghost return to their "home". This method uses
       * the same principle as the Chase method.
       */
      private void goHome()
    {
        if(canTurn() == true)
        {
        switch(quadrantToHome())
        {
            case 1: 
            if(moveNorth() == true && getRotation() != SOUTH && moveWest() == true && getRotation() != EAST) {
                if(Greenfoot.getRandomNumber(2)<1) {
                    setRotation(NORTH);
                }
                else {
                    setRotation(SOUTH);
                }
            }
            else if(moveNorth() == true && getRotation() != SOUTH)
            {
                setRotation(NORTH);
            }
            else if(moveWest() == true && getRotation() != EAST)
            {
                setRotation(WEST);
            }
            else if(moveEast() == true && getRotation() != WEST)
            {
                setRotation(EAST);
            }
            else if(moveSouth() == true && getRotation() != NORTH)
            {
                setRotation(SOUTH);
            }            
            break;
            
            case 2:
            if(moveWest() == true && getRotation() != EAST && moveSouth() == true && getRotation() != NORTH) {
                if(Greenfoot.getRandomNumber(2)<1) {
                    setRotation(SOUTH);
                }
                else {
                    setRotation(WEST);
                }
            }
            else if(moveWest() == true && getRotation() != EAST)
            {
                setRotation(WEST);
            }
            else if(moveSouth() == true && getRotation() != NORTH)
            {
                setRotation(SOUTH);
            }
            else if(moveNorth() == true && getRotation() != SOUTH)
            {
                setRotation(NORTH);
            }
            else if(moveEast() == true && getRotation() != WEST)
            {
                setRotation(EAST);
            }
            break;
            
            case 3:
            if(moveEast() == true && getRotation() != WEST && moveNorth() == true && getRotation() != SOUTH) {
                if(Greenfoot.getRandomNumber(2)<1) {
                    setRotation(EAST);
                }
                else {
                    setRotation(NORTH);
                }
            }
            else if(moveEast() == true && getRotation() != WEST)
            {
                setRotation(EAST);
            }
            else if(moveNorth() == true && getRotation() != SOUTH)
            {
                setRotation(NORTH);
            }
            else if(moveWest() == true && getRotation() != EAST)
            {
                setRotation(WEST);
            }
            else if(moveSouth() == true && getRotation() != NORTH)
            {
                setRotation(SOUTH);
            }
            break;
            
            case 4:
            if(moveSouth() == true && getRotation() != NORTH && moveEast() == true && getRotation() != WEST) {
                if(Greenfoot.getRandomNumber(2)<1) {
                    setRotation(EAST);
                }
                else {
                    setRotation(SOUTH);
                }
            }
            else if(moveSouth() == true && getRotation() != NORTH)
            {
                setRotation(SOUTH);
            }
            else if(moveEast() == true && getRotation() != WEST)
            {
                setRotation(EAST);
            }
            else if(moveNorth() == true && getRotation() != SOUTH)
            {
                setRotation(NORTH);
            }
            else if(moveWest() == true && getRotation() != EAST)
            {
                setRotation(WEST);
            }
            break;
        }
    }
  }
  /**
   * Checks to see if ghost are back at the respawn point by getting 
   * the x and y coordinates of the class RespawnZone
   */
    private boolean atHome()
    {
        RespawnZone Respawn =RespawnZone.getHome();
        if(getX() == Respawn.getX() && getY() == Respawn.getY())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * Insures that when this method is called the ghost can't
     * go back to the respawn zone. This creates a little secret
     * haven for Pacman
     */
    private void notHome()
    {
        if(getX() == 325 && getY() == 220)//coordinates of intersection above the respawn zone
        {
            if(Greenfoot.getRandomNumber(2) == 1)//if it reaches the intersection it will go in one of two directions
            {
                setRotation(EAST);
            }
            else
            {
                setRotation(WEST);
            }
        }        
    }
    /**
     * This method checks if the ghosts are at certain intersections
     * and it changes the rotation of the ghost so that they automatically
     * follow a pathway that leads them to the respawn zone. Once the ghost
     * are dead and they hit any of these coordinates, they are directed to 
     * the respawn zone.
     */
    private boolean enRoute()
    {
        if(ghostDead == 1)
        {
            if(getX() == 241 && getY() == 304)
            {
                setRotation(EAST);
                return true;
            }
            else if(getX() == 262 && getY() == 304)
            {
                setRotation(NORTH);
                return true;
            }
            else if(getX() == 262 && getY() == 220)
            {
                setRotation(EAST);
                return true;
            }
            else if(getX() == 220 && getY() == 388)
            {
                setRotation(WEST);
                return true;
            }
            else if(getX() == 220 && getY() == 262)
            {
                setRotation(EAST);
                return true;
            }
            else if(getX() == 220 && getY() == 346)
            {
                setRotation(EAST);
                return true;
            }
            else if(getX() == 472 && getY() == 220)
            {
                setRotation(WEST);
                return true;
            }
            else if(getX() == 199 && getY() == 220)
            {
                setRotation(EAST);
                return true;
            }
            else if(getX() == 388 && getY() == 220)
            {
                setRotation(WEST);
                return true;
            }
            else if(getX() == 388 && getY() == 304)
            {
                setRotation(NORTH);
                return true;
            }
            else if(getX() == 367 && getY() == 304)
            {
                setRotation(EAST);
                return true;
            }
            else if(getX() == 304 && getY() == 304)
            {
                setRotation(WEST);
                return true;
            }
            else if(getX() == 325 && getY() == 220)
            {
                setRotation(SOUTH);
                return true;
            }
            else if(getX() == 283 && getY() == 178)
            {
                setRotation(SOUTH);
                return true;
            }
            else if(getX() == 367 && getY() == 178)
            {
                setRotation(SOUTH);
                return true;
            }
            else if(getX() == 346 && getY() == 178)
            {
                setRotation(EAST);
                return true;
            }
            else if(getX() == 304 && getY() == 178)
            {
                setRotation(WEST);
                return true;
            }
            else if(getX() == 283 && getY() == 220)
            {
                setRotation(EAST);
                return true;
            }
            else if(getX() == 367 && getY() == 220)
            {
                setRotation(WEST);
                return true;
            }
            else if(getX() == 304 && getY() == 346)
            {
                setRotation(WEST);
                return true;
            }
            else if(getX() == 346 && getY() == 346)
            {
                setRotation(EAST);
                return true;
            }
            else if(getX() == 283 && getY() == 346)
            {
                setRotation(NORTH);
                return true;
            }
            else if(getX() == 283 && getY() == 304)
            {
                setRotation(WEST);
                return true;
            }
            else if(getX() == 304 && getY() == 346)
            {
                setRotation(WEST);
                return true;
            }
            else if(getX() == 367 && getY() == 304)
            {
                setRotation(EAST);
                return true;
            }
            else if(getX() == 367 && getY() == 346)
            {
                setRotation(NORTH);
                return true;
            }
            else if(getX() == 115 && getY() == 220)
            {
                setRotation(EAST);
                return true;
            }
            else if(getX() == 535 && getY() == 220)
            {
                setRotation(WEST);
                return true;
            }
            else if(canTurn() && getX() == 535 && getY() > 220 && getY() < 409)
            {
                setRotation(NORTH);
                return true;
            }
            else if(canTurn() && getX() == 115 && getY() > 220&& getY() < 409)
            {
                setRotation(NORTH);
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * Movement for the ghosts
     */
    private void ghostMove()
    {
        if(moveWait == 0)//if timer before each movement
        {
            if(tracking == true && ghostDead == 0)//if ghost are tracking Pacman and they are alive
                {
                    if(atHome() == true)
                    {
                        setRotation(NORTH);
                        eatable = false;
                    }
                        chase();//they will chase pacman
                        if(canMove(getRotation()) == true)
                        {
                            move();
                            if(eatable == false)
                            {
                                moveWait = moveTimer;//sets the movement timer
                            }
                            else
                            {
                                moveWait = 4;
                            }
                        }
//                     }
                }
                
            else if(tracking == true && ghostDead == 1 && respawnTimer==0)//if they ghost is a tracking ghost and they are dead
            {
                if(enRoute() == false)
                {
                    if(canMove(getRotation()) == true)
                    {
                        move();
                        moveWait = 6;
                    }
                    else if(canMove(getRotation()) == false)
                    {
                        setRotation(Greenfoot.getRandomNumber(4));//moves in random direction
                    }
                    ai();
                }
                else
                {
                    move();
                }
            }
            else if(enRoute() == true)
            {
//                 if(ghostDead == 1)
//                 {
//                     moveWait = 6;
//                 }
                move();
            }
            else
            {
                if(canMove(getRotation()) == true && respawnTimer==0)
                {
                    move();
                    if(eatable == false)
                        {
                            moveWait = moveTimer;
                        }
                    else
                        {
                            moveWait = 4;
                        }
                }
                else if(canMove(getRotation()) == false)
                {
                    setRotation(Greenfoot.getRandomNumber(4));
                }
                ai();
                notHome();
            }
        }
        if(moveWait > 0)
        {
            moveWait--;
        }
        if(respawnTimer > 0)
        {
            respawnTimer--;
        }
    }
}

