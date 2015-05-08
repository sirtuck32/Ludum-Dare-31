import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class God here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class God extends Animal
{
    GreenfootImage img = new GreenfootImage("God.png");
    Random rndm;
    //GreenfootImage idle = new GreenfootImage("Knight-Dle.png");
    int rCount = 0, cCount = 0, tick = 0;
    int max_frames = 2;
    int rows = 1;
    int fpr = 2;
    int next = 0;
    
    boolean animate = false;
    
    private Farm farm;
    private int dx, dy, loop = 0, shoot = 0;
    
    public God() {
        rndm = new Random();
        setImage(new GreenfootImage(img.getWidth()/2, img.getHeight()));
        getImage().drawImage(img, 0, 0);
    }
    
    public void addedToWorld(World world) {
        farm = (Farm)world.getObjects(Farm.class).get(0);
        dx = 300;
        dy = 50;
    }
    
    public void act() {
        if(!stop) {
            animate();
            shoot++;
            if(shoot > 20) {
                shoot = 0;
                spawn();
            }
            
            if(getX() == dx && getY() == dy) {
                next++;
                if(dx == -50) {
                    ((Screen)getWorld()).win();
                }
                switch(next) {
                    case 1:
                        dx = 550;
                        dy = 50;
                        break;
                    case 2:
                        dx = 550;
                        dy = 350;
                        break;
                    case 3:
                        dx = 50;
                        break;
                    case 4:
                        dy = 50;
                        break;
                    case 5:
                        dx = 550;
                        loop++;
                        next = 0;
                        break;
                }
                if(loop >= 2) {
                    dx = -50;
                    dy = 50;
                }
            }
            turnTowards(dx,dy);
            move(2);
            setRotation(0);
        }
    }   
    
    public void animate() {
        tick++;
        
        if(tick > 3) {
            //setLocation(getX() - 3, getY());
            tick = 0;
            cCount++;
            
            if(cCount > fpr) {
                cCount = 0;
                rCount++;
            }
            
            if(rCount * fpr + cCount > max_frames - 1) {
                cCount = 0;
                rCount = 0;
            }
            
            getImage().clear();
            getImage().drawImage(img, img.getWidth()/fpr * cCount * -1, img.getHeight()/rows * rCount * -1);
            if(farm.getX() < getX()) getImage().mirrorHorizontally();
        }
    }  
    
    private void spawn() {
        int r = rndm.nextInt(3);
        if(r == 0) getWorld().addObject(new Pig(), getX(), getY());
        if(r == 1) getWorld().addObject(new Cow(), getX(), getY());
        if(r == 2) getWorld().addObject(new Chicken(), getX(), getY());
    }
}
