import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Turret here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Turret extends Actor
{
    GreenfootImage img = new GreenfootImage("Turret.png");
    //GreenfootImage idle = new GreenfootImage("Knight-Dle.png");
    int rCount = 0, cCount = 0, tick = 0;
    int max_frames = 2;
    int rows = 1;
    int fpr = 2;
    
    boolean animate = false;
    
    private double dx, dy;
    public boolean stop = false;
    Actor target;
    
    public Turret() {
        setImage(new GreenfootImage(16, 20));
        getImage().drawImage(img, 0, 0);
    }
    int fire = 0;
    public void act() {
        if(!stop) {
            fire++;
            List<Actor> animals = getObjectsInRange(150, Animal.class);
            
            if(animals.size() > 0 && fire > 40) {
                animate = true;
                target = animals.get(0);
                getWorld().addObject(new Bullet(animals.get(0)), getX(), getY() - 5);
                fire = 0;
            } else {
                //farm.damage(0.2);
            }
            
            if(animate) animate();
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
                animate = false;
            }
            
            getImage().clear();
            getImage().drawImage(img, img.getWidth()/fpr * cCount * -1, img.getHeight()/rows * rCount * -1);
            List<Animal> targets = getWorld().getObjects(Animal.class);
            if(target != null && targets.contains(target) && target.getX() > getX()) getImage().mirrorHorizontally();
        }
    } 
    
    public class Bullet extends Actor {
        public boolean stop = false, remove = false;
        Actor target;
        public Bullet(Actor target) {
            this.target = target;    
        }
        
        protected void addedToWorld(World world) {
            setImage(new GreenfootImage("Pitchfork.png"));
            getImage().scale(getImage().getWidth(),getImage().getHeight());
        }
        
        public void act() {
            if(!stop){
                List<Animal> targets = getWorld().getObjects(Animal.class);
                if(targets.contains(target)) {
                    
                    turnTowards(target.getX(),target.getY());
                    move(3);
                    setRotation(0);
                    if(target.getX() < getX()) getImage().mirrorHorizontally();
                    targets = getIntersectingObjects(Animal.class);
                    if(targets.contains(target)) {
                        ((Animal)target).damage(1);
                        remove = true;
                    }
                } else {
                    remove = true;
                }
                
                if(remove) getWorld().removeObject(this);
            }
        }
    }
}
