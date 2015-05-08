import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Spine here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spine extends Effects
{
    protected void addedToWorld(World world) {
        rndm = new Random();
        
        GreenfootImage spine = new GreenfootImage("Spine.png");
        setImage(new GreenfootImage(spine.getWidth()/2,18));
        getImage().drawImage(spine, spine.getWidth()/2 * (-1 * rndm.nextInt(2)), 0);
        setRotation(rndm.nextInt(360));
        if(rndm.nextInt(2) == 0) xsign = -1;
        if(rndm.nextInt(2) == 0) ysign = -1;
    } 
    
    public void act() 
    {
        if(i < 1) {
            i += 0.1;
            //setLocation(getX() + (int)(rndm.nextInt(4) + 2 * Math.sin(i)) * xsign, getY() - (int)(rndm.nextInt(3) + 2 * Math.sin(i)) * ysign);
        } else {
            fade++;
            
            if(fade % 2 == 0) getImage().setTransparency(getImage().getTransparency()-1);
            if(getImage().getTransparency() == 0) getWorld().removeObject(this);
        }
    }     
}
