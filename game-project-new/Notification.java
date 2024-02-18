import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Notification here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Notification extends Actor
{
    GreenfootImage image;
    
    public Notification(String imageType)
    {
        this.image = new GreenfootImage(imageType);
        setImage(image);
    }
    
    public void act()
    {
        // Add your action code here.
        // getWorld().removeObject(this);
    }
}
