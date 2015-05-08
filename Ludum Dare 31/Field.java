import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Field here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Field extends Actor
{
    private double growth = 100, growth_max = 100;
    private int tick = 0;
    public boolean stop = false, upgrade = false;
    protected void addedToWorld(World world) {
        setImage(new GreenfootImage(100,100));
        updateGrowth();
    }
    
    public void upgrade() {
        setImage(new GreenfootImage(200,200));
        updateGrowth();
        upgrade = true;
    }
    
    public void act() {
        if(!stop) {
            List<Player> players = (List<Player>)getObjectsInRange(getImage().getWidth()/2, Player.class);
            if(players.size() > 0) {
                if(growth < growth_max) {
                    growth += 1;
                    updateGrowth();
                }
                tick++;
                
            } else {
                if(growth > 0) {
                    tick++;
                    growth -= 0.25;
                    updateGrowth();
                }
            }
            if(upgrade) {
                List<Animal> animals = (List<Animal>)getObjectsInRange(getImage().getWidth()/2, Animal.class);
                for(int i = 0; i < animals.size(); i++)
                    animals.get(i).slow = true;
            }
            if(tick >= 25) {
                ((Screen)getWorld()).addExp(1);
                tick = 0;
            }
        }
    }    
    
    private void updateGrowth() {
        getImage().clear();
        getImage().drawOval(0,0,getImage().getWidth(),getImage().getHeight());
        getImage().drawImage(new GreenfootImage("Field.png"), getImage().getWidth()/2 - 15,getImage().getHeight()/2 - 15);
        getImage().setColor(java.awt.Color.BLUE);
        getImage().fillRect(getImage().getWidth()/2 - 20,getImage().getHeight()/2 - 20,(int)((growth/growth_max) * 40.0), 5);
        getImage().setColor(java.awt.Color.BLACK);
        getImage().drawRect(getImage().getWidth()/2 - 20, getImage().getHeight()/2 - 20, 40, 5);
    }
}
