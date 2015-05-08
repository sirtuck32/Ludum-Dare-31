import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Win here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Win extends Actor
{
    protected void addedToWorld(World world) {
        setImage(new GreenfootImage("Win.png"));
    }
    
    public void act() {
        if(Greenfoot.mouseClicked(null))
            ((Screen)getWorld()).restart();
    }     
}
