import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Weapon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Weapon extends Actor
{
    private boolean flip = false;
    //protected GreenfootImage img;
    
    public void act() 
    {
        // Add your action code here.
    }    
    
    public void image(boolean nFlip) {
        if(flip != nFlip) {
            getImage().mirrorHorizontally();
            flip = nFlip;
        }
    }
}
