import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class QuestionBackground here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class QuestionBackground extends Actor
{
    /**
     * Act - do whatever the QuestionBackground wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public QuestionBackground()
    {
        GreenfootImage image = new GreenfootImage(600, 180);
        image.setColor(new Color(0, 0, 0, 128));
        image.fillRect(0, 0, 600, 180);
        setImage(image);
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
