import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Upgrade here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Upgrade extends Actor
{
    private String str;
    
    public Upgrade(String str) {
        this.str = str;    
    }
    
    protected void addedToWorld(World world) {
        if(str.equalsIgnoreCase("Upgrade Barn"))
            setImage(new GreenfootImage("UpgradeBarn.png"));
        if(str.equalsIgnoreCase("Upgrade Field"))
            setImage(new GreenfootImage("UpgradeField.png"));
        if(str.equalsIgnoreCase("Upgrade Farmer"))
            setImage(new GreenfootImage("UpgradeFarmer.png"));
    }
    
    public void act() {
        if(Greenfoot.mouseClicked(this))
            ((Screen)getWorld()).bonus(str);
    }
}
