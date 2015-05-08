import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Screen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Screen extends World
{
    private Random rndm;
    private Exp exp;
    private boolean stop = false, god = false;
    private int barnLevel = 0, farmerLevel = 0, fieldLevel = 0;
    private Player player;
    private int level = 0;
    
    /**
     * Constructor for objects of class Screen.
     * 
     */
    public Screen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false); 
        addObject(new Farm(), 300, 200);
        player = new Player();
        addObject(player, 250, 250);
        addObject(new Field(), 400, 100);
        addObject(new Field(), 200, 100);
        addObject(new Field(), 200, 300);
        addObject(new Field(), 400, 300);
        exp = new Exp();
        addObject(exp, 52, 20);
        setPaintOrder(Start.class, Win.class, GameOver.class, Upgrade.class, Player.class, Animal.class, Weapon.class, Field.class, Turret.class, Turret.Bullet.class, Farm.class, Chicken.Egg.class, Effects.class);
        
        getBackground().setColor(java.awt.Color.GREEN.darker());
        getBackground().fill();
        getBackground().setColor(java.awt.Color.BLACK);
        rndm = new Random();
        
        Greenfoot.start();
        start();
    }
    int tick = 0;
    public void act() {
        if(!stop) {
            tick++;

            if( level >= 0) {
                if(tick % 100 == 0) addObject(new Pig(), 620 , rndm.nextInt(400));
                    
                if(tick % 150 == 0) addObject(new Pig(), -20 , rndm.nextInt(400));
            }
            if(level >= 1) {
                if(tick % 100 == 0) addObject(new Cow(), 620 , rndm.nextInt(400));
                
                if(tick % 150 == 0) addObject(new Cow(), -20 , rndm.nextInt(400));
                    
                if(tick % 200 == 0) addObject(new Pig(), rndm.nextInt(600) , -20);
                    
                if(tick % 250 == 0) addObject(new Pig(), rndm.nextInt(600) , 420);
            }
            if(level >= 2) {
                if(tick % 100 == 0) addObject(new Chicken(), 620 , rndm.nextInt(400));
                
                if(tick % 150 == 0) addObject(new Chicken(), -20 , rndm.nextInt(400));
                
                if(tick % 200 == 0) addObject(new Cow(), rndm.nextInt(600) , -20);
                
                if(tick % 250 == 0) addObject(new Cow(), rndm.nextInt(600) , 420);
                
                if(tick % 300 == 0) {
                    addObject(new Pig(), 620 , rndm.nextInt(400));
                    addObject(new Cow(), 620 , rndm.nextInt(400));
                }
                    
                if(tick % 350 == 0) {
                    addObject(new Pig(), -20 , rndm.nextInt(400));
                    addObject(new Cow(), -20 , rndm.nextInt(400));
                }
            }
            if(level >= 3) {
                if(tick % 200 == 0) addObject(new Chicken(), rndm.nextInt(600) , -20);
                
                if(tick % 250 == 0) addObject(new Chicken(), rndm.nextInt(600) , 420);
                
                if(tick % 300 == 0) {
                    addObject(new Pig(), rndm.nextInt(600) , -20);
                    addObject(new Cow(), rndm.nextInt(600) , -20);
                }
                    
                if(tick % 350 == 0) {
                    addObject(new Pig(), rndm.nextInt(600) , 420);
                    addObject(new Cow(), -20 , rndm.nextInt(400));
                }
            }
            if(level >= 4) {
                if(!god) {
                    addObject(new God(), 300, -20);
                    god = true;
                }
            }
        }
    }
    
    public void addExp(int amt) {
        exp.update(amt);
    }
    
    public void upgrade() {
        level++;
        stop();
    }
    
    public void bonus(String str) {
        removeObjects(getObjects(Upgrade.class));
        
        if(str.equalsIgnoreCase("Upgrade Barn")) {
            addObject(new Turret(), 300, 180);
            List<Farm> barns = (List<Farm>)getObjects(Farm.class);
            for(int i = 0; i < barns.size(); i++)
                barns.get(i).regen += 0.2;
            barnLevel++;
        }
        if(str.equalsIgnoreCase("Upgrade Farmer")) {
            player.addFork();
            player.speed = 4;
            exp.doubleExp = true;
            List<Farm> barns = (List<Farm>)getObjects(Farm.class);
            for(int i = 0; i < barns.size(); i++)
                barns.get(i).regen += 0.05;
            farmerLevel++;
        }
        if(str.equalsIgnoreCase("Upgrade Field")) {
            List<Field> fields = (List<Field>)getObjects(Field.class);
            for(int i = 0; i < fields.size(); i++)
                fields.get(i).upgrade();
            List<Farm> barns = (List<Farm>)getObjects(Farm.class);
            for(int i = 0; i < barns.size(); i++)
                barns.get(i).regen += 0.1;
            fieldLevel++;
        }
        
        ((Player)getObjects(Player.class).get(0)).stop = false;
        
        List<Animal> animals = (List<Animal>)getObjects(Animal.class);
        for(int i = 0; i < animals.size(); i++)
            animals.get(i).stop = false;
            
        List<Field> fields = (List<Field>)getObjects(Field.class);
        for(int i = 0; i < fields.size(); i++)
            fields.get(i).stop = false;
            
        List<Turret> turrets = (List<Turret>)getObjects(Turret.class);
        for(int i = 0; i < turrets.size(); i++)
            turrets.get(i).stop = false;
            
        List<Turret.Bullet> bullets = (List<Turret.Bullet>)getObjects(Turret.Bullet.class);
        for(int i = 0; i < bullets.size(); i++)
            bullets.get(i).stop = false;
            
        List<Chicken.Egg> eggs = (List<Chicken.Egg>)getObjects(Chicken.Egg.class);
        for(int i = 0; i < eggs.size(); i++)
            eggs.get(i).stop = false;
            
        stop = false;
    }
    
    private void stop() {
        if(barnLevel == 0 || fieldLevel == 0 || farmerLevel == 0) {
            stop = true;
            
            ((Player)getObjects(Player.class).get(0)).stop = true;
            
            List<Animal> animals = (List<Animal>)getObjects(Animal.class);
            for(int i = 0; i < animals.size(); i++)
                animals.get(i).stop = true;
                
            List<Field> fields = (List<Field>)getObjects(Field.class);
            for(int i = 0; i < fields.size(); i++)
                fields.get(i).stop = true;
                
            List<Turret> turrets = (List<Turret>)getObjects(Turret.class);
            for(int i = 0; i < turrets.size(); i++)
                turrets.get(i).stop = true;
                
            List<Turret.Bullet> bullets = (List<Turret.Bullet>)getObjects(Turret.Bullet.class);
            for(int i = 0; i < bullets.size(); i++)
                bullets.get(i).stop = true;
                
            List<Chicken.Egg> eggs = (List<Chicken.Egg>)getObjects(Chicken.Egg.class);
            for(int i = 0; i < eggs.size(); i++)
                eggs.get(i).stop = true;
                
            if(barnLevel == 0)  addObject(new Upgrade("Upgrade Barn"), 300, 75);
            if(fieldLevel == 0) addObject(new Upgrade("Upgrade Field"), 200, 200);
            if(farmerLevel == 0) addObject(new Upgrade("Upgrade Farmer"), 400, 200);
        }
    }
    
    public void gameover() {
        stop = true;
        
        ((Player)getObjects(Player.class).get(0)).stop = true;
        
        List<Animal> animals = (List<Animal>)getObjects(Animal.class);
        for(int i = 0; i < animals.size(); i++)
            animals.get(i).stop = true;
            
        List<Field> fields = (List<Field>)getObjects(Field.class);
        for(int i = 0; i < fields.size(); i++)
            fields.get(i).stop = true;
            
        List<Turret> turrets = (List<Turret>)getObjects(Turret.class);
        for(int i = 0; i < turrets.size(); i++)
            turrets.get(i).stop = true;
            
        List<Turret.Bullet> bullets = (List<Turret.Bullet>)getObjects(Turret.Bullet.class);
        for(int i = 0; i < bullets.size(); i++)
            bullets.get(i).stop = true;
         
        List<Chicken.Egg> eggs = (List<Chicken.Egg>)getObjects(Chicken.Egg.class);
        for(int i = 0; i < eggs.size(); i++)
            eggs.get(i).stop = true;
            
        addObject(new GameOver(), 300, 200);
    }
    
    public void restart() {
        removeObjects(getObjects(Actor.class));
        
        addObject(new Farm(), 300, 200);
        player = new Player();
        addObject(player, 250, 250);
        addObject(new Field(), 400, 100);
        addObject(new Field(), 200, 100);
        addObject(new Field(), 200, 300);
        addObject(new Field(), 400, 300);
        exp = new Exp();
        addObject(exp, 52, 20);
        
        tick = 0;
        barnLevel = 0;
        farmerLevel = 0;
        fieldLevel = 0;
        level = 0;
        
        stop = false;
    }
    
    public void win() {
        stop = true;
        
        ((Player)getObjects(Player.class).get(0)).stop = true;
        
        List<Animal> animals = (List<Animal>)getObjects(Animal.class);
        for(int i = 0; i < animals.size(); i++)
            animals.get(i).stop = true;
            
        List<Field> fields = (List<Field>)getObjects(Field.class);
        for(int i = 0; i < fields.size(); i++)
            fields.get(i).stop = true;
            
        List<Turret> turrets = (List<Turret>)getObjects(Turret.class);
        for(int i = 0; i < turrets.size(); i++)
            turrets.get(i).stop = true;
            
        List<Turret.Bullet> bullets = (List<Turret.Bullet>)getObjects(Turret.Bullet.class);
        for(int i = 0; i < bullets.size(); i++)
            bullets.get(i).stop = true;
         
        List<Chicken.Egg> eggs = (List<Chicken.Egg>)getObjects(Chicken.Egg.class);
        for(int i = 0; i < eggs.size(); i++)
            eggs.get(i).stop = true;
            
        addObject(new Win(), 300, 200);
    }
    
    public void start() {
        stop = true;
        
        ((Player)getObjects(Player.class).get(0)).stop = true;
        
        List<Animal> animals = (List<Animal>)getObjects(Animal.class);
        for(int i = 0; i < animals.size(); i++)
            animals.get(i).stop = true;
            
        List<Field> fields = (List<Field>)getObjects(Field.class);
        for(int i = 0; i < fields.size(); i++)
            fields.get(i).stop = true;
            
        List<Turret> turrets = (List<Turret>)getObjects(Turret.class);
        for(int i = 0; i < turrets.size(); i++)
            turrets.get(i).stop = true;
            
        List<Turret.Bullet> bullets = (List<Turret.Bullet>)getObjects(Turret.Bullet.class);
        for(int i = 0; i < bullets.size(); i++)
            bullets.get(i).stop = true;
         
        List<Chicken.Egg> eggs = (List<Chicken.Egg>)getObjects(Chicken.Egg.class);
        for(int i = 0; i < eggs.size(); i++)
            eggs.get(i).stop = true;
            
        addObject(new Start(), 300, 200);
    }
}
