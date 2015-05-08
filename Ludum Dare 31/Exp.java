import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Exp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Exp extends Actor
{
    private int exp = 0, exp_next = 100;
    private String str;
    public boolean doubleExp = false;
    protected void addedToWorld(World world) {
        setImage(new GreenfootImage(100, 25));
        str = "" + exp + " / " + exp_next;
        getImage().drawString("Exp: " + str, 0, 10);
    }
        
    public void update(int amt) {
        if(doubleExp) amt *= 2;
        exp += amt;
        getImage().clear();
        str = "" + exp + " / " + exp_next;
        getImage().drawString("Exp: " + str, 0, 10);
        
        if(exp >= exp_next) {
            ((Screen)getWorld()).upgrade();
            exp = 0;
            exp_next = (int)(1.5 * exp_next);
        }
    }
}
