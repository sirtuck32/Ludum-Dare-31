import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Chicken here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Chicken extends Animal
{
    GreenfootImage img = new GreenfootImage("Chicken.png");
    //GreenfootImage idle = new GreenfootImage("Knight-Dle.png");
    int rCount = 0, cCount = 0, tick = 0, shoot = 0;
    int max_frames = 2;
    int rows = 1;
    int fpr = 2;
    
    boolean animate = false;
    
    private Farm farm;
    private double dx, dy;
    
    public Chicken() {
        setImage(new GreenfootImage(img.getWidth()/2, img.getHeight()));
        getImage().drawImage(img, 0, 0);
    }
    
    public void addedToWorld(World world) {
        farm = (Farm)world.getObjects(Farm.class).get(0);
    }
    
    public void act() {
        if(!stop) {
            animate();
            List<Actor> farms = getObjectsInRange(150, Farm.class);
            if(farms.size() == 0) {
                turnTowards(farm.getX(),farm.getY());
                if(slow) move(2);
                else move(4);
                setRotation(0);
            } else {
                shoot++;
                if(shoot >= 50) {
                    shoot = 0;
                    getWorld().addObject(new Egg(farms.get(0)), getX(), getY());
                }
            }
            
            if(remove) {
                getWorld().addObject(new Guts(), getX(), getY());
                getWorld().addObject(new Guts(), getX(), getY());
                getWorld().addObject(new Guts(), getX(), getY());
                getWorld().addObject(new Guts(), getX(), getY());
                getWorld().addObject(new Guts(), getX(), getY());
                getWorld().addObject(new Spine(), getX(), getY());
                getWorld().addObject(new Skull(), getX(), getY());
                getWorld().removeObject(this);
            }
        }
    }   
    
    public void animate() {
        tick++;
        
        if(tick > 5) {
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
    
    public class Egg extends Actor {
        public boolean stop = false, remove = false;
        Actor target;
        public Egg(Actor target) {
            this.target = target;    
        }
        
        protected void addedToWorld(World world) {
            setImage(new GreenfootImage("Egg.png"));
            getImage().scale(getImage().getWidth(),getImage().getHeight());
        }
        int a = 0;
        public void act() {
            if(!stop){
                List<Player> p = (List<Player>)getIntersectingObjects(Player.class);
                if(p.size() > 0)
                    remove = true;
                a += 45;
                List<Farm> targets = getWorld().getObjects(Farm.class);
                setRotation(a);
                if(targets.contains(target)) {
                    turnTowards(target.getX(),target.getY());
                    if(slow) move(1);
                    else move(3);
                    
                    targets = getIntersectingObjects(Farm.class);
                    if(targets.contains(target)) {
                        ((Farm)target).damage(1);
                        remove = true;
                    }
                } else {
                    remove = true;
                }
                
                if(remove) {
                    getWorld().removeObject(this);
                }
            }
        }
    }
}
