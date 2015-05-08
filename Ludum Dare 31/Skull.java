import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Skull here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Skull extends Effects
{
    protected void addedToWorld(World world) {
        rndm = new Random();
        setImage(new GreenfootImage("Skull.png"));
        setRotation(90 * rndm.nextInt(4));
        if(rndm.nextInt(2) == 0) xsign = -1;
        if(rndm.nextInt(2) == 0) ysign = -1;
    } 
}
