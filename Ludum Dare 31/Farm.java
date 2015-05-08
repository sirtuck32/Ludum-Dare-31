import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Farm here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Farm extends Actor
{
    private double health = 100, health_max = 100;
    public double regen = 0.05;
    GreenfootImage barn = new GreenfootImage("barn.png");
    protected void addedToWorld(World world) {
        setImage(new GreenfootImage(barn.getWidth(), barn.getHeight() + 15));
        updateGrowth();
    }
    
    public void act() {
        /*List<Player> players = (List<Player>)getObjectsInRange(50, Player.class);
        if(players.size() > 0) {
            if(growth < growth_max) {
                growth += 0.5;
                updateGrowth();
            }
        } else {
            if(growth > 0) {
                growth -= 0.5;
                updateGrowth();
            }
        }*/
        if(health < health_max) {
            health += regen;
            updateGrowth();
        }
    } 
    
    public void damage(double dmg) {
        health -= dmg;
        updateGrowth();
        
        if(health <= 0) ((Screen)getWorld()).gameover();
    }
    
    private void updateGrowth() {
        getImage().clear();
        //getImage().drawOval(0,0,150,150);
        getImage().drawImage(new GreenfootImage("barn.png"), 0,15);
        getImage().setColor(java.awt.Color.BLUE);
        getImage().fillRect(15,0,(int)((health/health_max) * 40.0), 5);
        getImage().setColor(java.awt.Color.BLACK);
        getImage().drawRect(15, 0, 40, 5);
    }   
}
