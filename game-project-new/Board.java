import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Board here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Board extends Actor
{
    GreenfootImage background;
    private int WIDTH = 90;
    private int HEIGHT = 40;
    private String scorePrefix = "Skor: ";
    private String chancePrefix = "Kesempatan: ";
    
    private GreenfootImage scoreImage;
    private GreenfootImage chanceImage;
    /**
     * Act - do whatever the Board wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public Board()
    {
        background = getImage();
        background.scale(150, 50);
        scoreImage = new GreenfootImage(scorePrefix, 18, Color.BLACK, null);
        chanceImage = new GreenfootImage(chancePrefix, 18, Color.BLACK, null);
        background.drawImage(scoreImage, 10, 5);
        background.drawImage(chanceImage, 10, 25);
        setImage(background);
    }
    
    public void act()
    {
        updateImage();
    }
    
    private void updateImage()
    {
        GameWorld gameWorld = (GameWorld) getWorld();
        GreenfootImage image = new GreenfootImage(background);
        scoreImage = new GreenfootImage(scorePrefix + gameWorld.getScore(), 18, Color.BLACK, null);
        chanceImage = new GreenfootImage(chancePrefix + gameWorld.getChance(), 18, Color.BLACK, null);
        
        image.drawImage(scoreImage, 10, 5);
        image.drawImage(chanceImage, 10, 25);
        
        setImage(image);
    }
}
