import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    GreenfootImage img = new GreenfootImage("Farmer.png");
    GreenfootImage idle = new GreenfootImage("Farmer.png");
    int rCount = 0, cCount = 0, tick = 0;
    int max_frames = 4;
    int rows = 1;
    int fpr = 4;
    int pCount = 1, speed = 2;
    
    boolean animate = false, flip = false, stop = false;
    
    int x = 0,y = 0;
    String key = "";
    
    Pitchfork p, p2;
    
    public Player() {
        setImage(new GreenfootImage(32, 32));
        getImage().drawImage(img, 0, 0);
    }
    
    protected void addedToWorld(World world) {
        p = new Pitchfork();
        world.addObject(p, getX() + 8, getY() + 2);
    }
    
    public void addFork() {
        p2 = new Pitchfork();
        getWorld().addObject(p2, getX() - 10, getY() + 2);
    }
    
    public void act() 
    {
        if(!stop) {
            boolean pressed = false;
                   
            if( Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up")) {
                pressed = animate = true;
                if(getY() - speed > 10) setLocation(getX(), getY() - speed);
            } 
            if(Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left")) {
                pressed = animate = true;
                if(getX() - speed > 10) setLocation(getX() - speed, getY());
                flip = true;
            } 
            if(Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("down")) {
                pressed = animate = true;
                if(getY() + speed < 390) setLocation(getX(), getY() + speed);
            }
            if(Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right")) {
                pressed = animate = true;
                if(getX() + speed < 590) setLocation(getX() + speed, getY());
                flip = false;
            }
            if(Greenfoot.isKeyDown("space") && !p.attack) {
                p.attack();
                if(p2 != null) p2.attack();
            }
            
            if(pressed || p.attack) {
                p.image(flip);
                if(flip) p.setLocation(getX() - 8 - p.attack_x_mod, getY() + 2);
                else p.setLocation(getX() + 8 + p.attack_x_mod, getY() + 2);
                
                if(p2 != null) {
                    p2.image(!flip);
                    if(flip) p2.setLocation(getX() + 8 + p.attack_x_mod, getY() + 2);
                    else p2.setLocation(getX() - 10 - p.attack_x_mod, getY() + 2);
                }
            }
            
            if(animate && !pressed) {
                getImage().clear();
                getImage().drawImage(idle,0,0);
                if(flip) getImage().mirrorHorizontally();
                cCount = 0;
                rCount = 0;
                tick = 0;
                animate = false;
            }
            
            if(animate)
                animate();
        }
    }   
    
    public void animate() {
        tick++;
        
        if(tick > 3) {
            //setLocation(getX() + 4, getY());
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
            if(flip) getImage().mirrorHorizontally();
        }
    }  
}
