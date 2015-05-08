import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOver extends Actor
{
    protected void addedToWorld(World world) {
        setImage(new GreenfootImage("GameOver.png"));
    }
    
    public void act() {
        if(Greenfoot.mouseClicked(null))
            ((Screen)getWorld()).restart();
    }  
}
