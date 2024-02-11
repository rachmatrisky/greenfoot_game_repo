import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameWorld extends World
{
    private int questionTransitionTimer = 60;
    private int currentLevel = 1;
    private int notificationTimer = 0;
    private Character mainChar = new Character();;
    private Door door1 = new Door("portal final.png");
    private Door door2 = new Door("portal danger.png");
    private ArrayList<String> questions;
    private ArrayList<String> answersA;
    private ArrayList<String> answersB;
    private ArrayList<String> correctAnswers;
    
    private int moveSpeed = 3;
    private boolean isMoving = false;
    private int targetX, targetY;
    
    private Board boardGame = new Board();
    private int chance = 3;
    private int score = 0;
    private boolean isLevelCompleted = false;
    private Win winWorld = new Win();
    
    private String lastDirection = "right";
    /**
     * Constructor for objects of class MyWorld.
     *
     */
    public GameWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        prepareQuestions();
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        addObject(boardGame, 90, 40);
        
        addObject(mainChar, getWidth() / 2, getHeight() - 230);
        
        addObject(door1, getWidth() - 150, getHeight() - 238);
        
        addObject(door2, getWidth() - 450, getHeight() - 238);
        
        // QuestionBackground questionBg = new QuestionBackground();
        // addObject(questionBg, getWidth() / 2, getHeight() - 80);

        Question question = new Question(questions.get(currentLevel - 1));
        addObject(question, getWidth() / 2, getHeight() - 150);

        Button firstOption = new Button("Pilihan 1: " + answersA.get(currentLevel - 1));
        addObject(firstOption, 120, getHeight() - 90);

        Button secondOption = new Button("Pilihan 2: " + answersB.get(currentLevel - 1));
        addObject(secondOption, 460, getHeight() - 90);
        
        setPaintOrder(Button.class, QuestionBackground.class);
    }
    
    public void act()
    {
        if (Greenfoot.mouseClicked(null))
        {
            checkAnswer();
        }
        
        if (notificationTimer > 0)
        {
            notificationTimer--;
            if (notificationTimer == 0)
            {
                removeObjects(getObjects(Notification.class));
            }
        }
        
        if (isMoving)
        {
            moveCharacter();
        }
        else if (isLevelCompleted || chance == 0)
        {
            Greenfoot.setWorld(winWorld);
        }
        
        // if (chance == 0)
        // {
            // moveCharacter();
            // Greenfoot.delay(50);
            // Greenfoot.setWorld(winWorld);
        // }
    }
    
    public int getChance()
    {
        return chance;
    }
    
    public int getScore()
    {
        return score;
    }
    
    private void checkLevelCompletion()
    {
        if (currentLevel > 5)
        {
            Greenfoot.stop();
        }
    }
    
    private void prepareQuestions()
    {
        questions = new ArrayList<>();
        answersA = new ArrayList<>();
        answersB = new ArrayList<>();
        correctAnswers = new ArrayList<>();
        
        questions.add("Apa yang mendasari Ahmad Dahlan untuk mendirikan\n organisasi Muhammadiyah?");
        answersA.add("Kesadaran akan perlunya\n pembaruan pendidikan dalam\nkehidupan Islam di Masyarakat");
        answersB.add("Desakan pihak pemerintah\nkolonial");
        correctAnswers.add("Kesadaran akan perlunya\n pembaruan pendidikan dalam\nkehidupan Islam di Masyarakat");
        
        questions.add("Apa tujuan utama yang ingin dicapai\n Ahmad Dahlan melalui Muhammadiyah?");
        answersA.add("mempertahankan status\n quo dan menghindari perubahan.");
        answersB.add("Meciptakan masyarakat yang\n lebih taat dan berakhlak\nkualitas pendidikan Islam");
        correctAnswers.add("Meciptakan masyarakat yang\n lebih taat dan berakhlak\nkualitas pendidikan Islam");
        
        questions.add("Apa peran kunci Ahmad Dahlan dalam\n perkembangan awal Muhammadiyah?");
        answersA.add("Pengamat luar yang\n tidak terlibat secara langsung\nPerkembangan Pendidikan\n di Muhammadiyah");
        answersB.add("Pendiri dan pemimpin\n utama yang mengarahkan visi\n dan misi organisasi");
        correctAnswers.add("Pendiri dan pemimpin\n utama yang mengarahkan visi\n dan misi organisasi");
        
        questions.add("Bagaimana Ahmad Dahlan memandang\n peran pendidikan\n dalam misi Muhammadiyah?");
        answersA.add("Sebagai sarana\n untuk meningkatkan\n pemahaman keagamaan\n dan kemajuan sosial");
        answersB.add("Hanya sebagai alat untuk\n mencetak elit politik Dinamika Hubungan\n dengan Masyarakat dan Pemerintah");
        correctAnswers.add("Sebagai sarana\n untuk meningkatkan\n pemahaman keagamaan\n dan kemajuan sosial");
        
        questions.add("Bagaimana hubungan antara Ahmad Dahlan\n Muhammadiyah, dan pemerintah kolonial pada masa itu?");
        answersA.add("Kerjasama yang\n erat dengan pemerintah\n tanpa ada gesekan");
        answersB.add("Hubungan yang kompleks\n dengan upaya Muhammadiyah\n untuk mempertahankan otonomi");
        correctAnswers.add("Hubungan yang kompleks\n dengan upaya Muhammadiyah\n untuk mempertahankan otonomi");
    }
    
    private void nextLevel()
    {
        removeObjects(getObjects(Question.class));
        removeObjects(getObjects(Button.class));
        
        currentLevel++;
        
        if (currentLevel <= questions.size())
        {
        Question question = new Question(questions.get(currentLevel - 1));
        addObject(question, getWidth() / 2, getHeight() - 150);
        
        Button firstOption = new Button("Pilihan 1: " + answersA.get(currentLevel - 1));
        addObject(firstOption, 120, getHeight() - 90);
        
        Button secondOption = new Button("Pilihan 2: " + answersB.get(currentLevel - 1));
        addObject(secondOption, 460, getHeight() - 90);
        }
        else
        {
            moveCharacter();
            Greenfoot.setWorld(new Win());
        }
    }
    
    private void checkAnswer()
    {
        List<Actor> clickedObjects = getObjectsAt(Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY(), Actor.class);
   
        for (Actor actor : clickedObjects)
        {
                Button clickedButton = (Button) actor;
                String buttonText = clickedButton.getText();
                
                if (clickedButton == getObjects(Button.class).get(0))
                {
                    if (buttonText.contains(correctAnswers.get(currentLevel - 1)))
                    {
                        showNotification("PopUp1.png");
                        setTargetPosition(door1.getX(), door1.getY());
                        nextLevel();
                        score++;
                    } else {
                        showNotification("PopUp2.png");
                        setTargetPosition(door2.getX(), door2.getY());
                        chance--;
                    }
                } else if (clickedButton == getObjects(Button.class).get(1))
                {
                    if (buttonText.contains(correctAnswers.get(currentLevel - 1)))
                    {
                        showNotification("PopUp1.png");
                        setTargetPosition(door1.getX(), door1.getY());
                        nextLevel();
                        score++;
                    }
                    else
                    {
                        showNotification("PopUp2.png");
                        setTargetPosition(door2.getX(), door2.getY());
                        chance--;
                    }
                }
                
                isMoving = true;
        }
    }
    
    private void setTargetPosition(int x, int y)
    {
        targetX = x;
        targetY = y;
    }
    
    private void showNotification(String imageType)
    {
        addObject(new Notification(imageType), getWidth() / 2, getHeight() - 350);
        notificationTimer = 180;
    }
    
    // private void moveCharacterToPortal(Portal portal)
    // {
        // mainChar.setLocation(portal.getX(), portal.getY());
    // }
    
    private void moveCharacter()
    {
        if (mainChar.getX() < targetX)
        {
            mainChar.setLocation(mainChar.getX() + moveSpeed, mainChar.getY());
            if (!lastDirection.equals("left"))
            {
                mainChar.getImage().mirrorHorizontally();
                lastDirection = "left";
            }
        }
        else if (mainChar.getX() > targetX)
        {
            mainChar.setLocation(mainChar.getX() - moveSpeed, mainChar.getY());
            if (!lastDirection.equals("right"))
            {
                mainChar.getImage().mirrorHorizontally();
                lastDirection = "right";
            }
        }
        
        if (mainChar.getX() == targetX)
        {
            isMoving = false;
            // isLevelCompleted = true;
            Greenfoot.delay(70);
            mainChar.setLocation(getWidth() / 2, getHeight() - 230);
        }
    }
}
