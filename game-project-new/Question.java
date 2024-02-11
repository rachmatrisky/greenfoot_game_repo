import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Question here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Question extends Actor
{
    private String questionText;
    /**
     * Act - do whatever the Question wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Question(String questionText)
    {
        this.questionText = questionText;
        setImage(new GreenfootImage(questionText, 20, Color.YELLOW, null));
    }
    
    public void act()
    {
        // Add your action code here.
        
    }
}
