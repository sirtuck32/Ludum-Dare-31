import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Start here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Start extends Actor
{
     protected void addedToWorld(World world) {
        setImage(new GreenfootImage("Start.png"));
    }
    
    public void act() {
        if(Greenfoot.mouseClicked(null))
            ((Screen)getWorld()).restart();
    }     
}
