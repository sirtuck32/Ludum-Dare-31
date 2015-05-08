import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Effects here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Effects extends Actor
{
    int xsign = 1, ysign = 1, fade = 0;
    Random rndm;
        
    double i = 0;
    public void act() 
    {
        if(i < 1) {
            i += 0.1;
            setLocation(getX() + (int)(rndm.nextInt(4) + 2 * Math.sin(i)) * xsign, getY() - (int)(rndm.nextInt(3) + 2 * Math.sin(i)) * ysign);
        } else {
            fade++;
            
            if(fade % 2 == 0) getImage().setTransparency(getImage().getTransparency()-1);
            if(getImage().getTransparency() == 0) getWorld().removeObject(this);
        }
    }       
}
