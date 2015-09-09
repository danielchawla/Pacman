import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RespawnZone here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RespawnZone extends Actor
{
    public static RespawnZone Respawn;
    public RespawnZone()
    {
        getImage().scale(21,21);
        Respawn = this;
    }
    /**
     * Act - do whatever the RespawnZone wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {

    }
    public static RespawnZone getHome()
    {
        return Respawn;
    }
}
