import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Pitchfork here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pitchfork extends Weapon
{
    public boolean attack = false, reverse = false;
    public int tick = 0, attack_x_mod = 0, dis = 0, speed = 1;
    
    public void act() 
    {
        if(attack) {
            tick++;
        
            if(tick % 1 == 0) {
                if(reverse) attack_x_mod -= speed;
                else attack_x_mod += speed;
            }
            
            if(attack_x_mod >= 10 + dis)
                reverse = true;
            
            if(attack_x_mod <= 0 && reverse) {
                attack = false;
                tick = 0;
                attack_x_mod = 0;
                reverse = false;
            }
            
            
        }
        List<Animal> animals = getIntersectingObjects(Animal.class);
        if(animals.size() > 0)
            for(int i = 0; i < animals.size(); i++)
                animals.get(0).damage(10);
    }    
    
    public void attack() {
        attack = true;
    }
}
