import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WinWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WinWorld extends World
{
    Character mainChar = new Character();
    WinSign winSign = new WinSign();
    /**
     * Constructor for objects of class WinWorld.
     * 
     */
    public WinWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        prepare();
    }
    
    public void prepare()
    {
        addObject(mainChar, getWidth() / 2, 300);
        ButtonHome buttonHome = new ButtonHome();
        addObject(buttonHome, 206, 360);
        ButtonMain buttonMain = new ButtonMain();
        addObject(buttonMain, 393, 360);
        addObject(winSign, 280, getHeight() - 250);
    }
}
