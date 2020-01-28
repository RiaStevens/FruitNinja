import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.util.*;

public class Game extends JPanel implements MouseMotionListener
{
    private Player player;
    private ArrayList<Fruit> fruits;
    private ArrayList<Splat> splats;
    private JLabel label, tally, gameOver;
    private int timer, initialSize, score;
    private boolean done = false;
    private Background background;
    private Sound bombHit, slice;

    //constructor - sets the initial conditions for this Game object
    public Game(int width, int height)
    {
        this.setLayout( null );
        this.setPreferredSize( new Dimension( width, height ) );

        timer = 0; // keeps track of the time passed in the game
        score = 0; // keeps track of the amount of fruits sliced
        initialSize = 0; // helps to limit the amount of fruits in the game at a time
        
        player = new Player();
        bombHit = new Sound("bomb.wav");
        slice = new Sound("slice.wav");

        fruits = new ArrayList<Fruit>();
        fruits.add(randomFruit());
        fruits.add(new Bomb());

        splats = new ArrayList<Splat>();

        // adds the label of the timer to the game
        label = new JLabel("Time: " + timer);
        this.add(label);
        label.setVisible(true);
        label.setFont(new Font("Arial", Font.BOLD, 25));
        label.setBounds(30, 30, 200, 30);
        label.setForeground(Color.WHITE);

        // adds the label for the score to the game
        tally = new JLabel("Score: " + score);
        this.add(tally);
        tally.setBounds(Driver.WIDTH - 150, 0, 200, 70);
        tally.setVisible(true);
        tally.setFont(new Font("Arial", Font.BOLD, 25));
        tally.setForeground(Color.WHITE);

        // adds the gameover label to the game
        gameOver = new JLabel("GAME OVER!");
        this.add(gameOver);
        gameOver.setVisible(false);
        gameOver.setFont(new Font("Arial", Font.ITALIC, 30));
        gameOver.setBounds(400, 300, 200, 100);
        gameOver.setForeground(Color.RED);

        background = new Background();
        this.addMouseMotionListener(this);
        this.setFocusable(true);
    }

    // pre - the game has not yet ended
    // post - runs the game, moving each fruit and changing the conditions of the game as needed
    public void playGame()
    {
        while( !done )
        {
            initialSize = fruits.size();
            if(initialSize >= 6)
            {
                initialSize = 6;
            }
            for(int i = 0; i < initialSize; i ++)
            {                
                Fruit a = fruits.get(i);
                a.move();
                if(a.isHit(player))
                {
                    if(a instanceof Bomb)
                    {
                        bombHit.play();
                        gameOver();
                    }
                    else if(a instanceof Clock)
                    {
                        if(timer - 5000 > 0)
                            timer -= 5000;
                        else
                            timer = 0;
                    }
                    else
                    {
                        slice.play();
                        remove(i);
                    }
                }
                else if (a.hitsBottom())
                {
                    if(a instanceof Bomb)
                    {
                        fruits.set(i, new Bomb());
                    }
                    else
                    {
                        fruits.set(i, randomFruit());
                    }
                }
            }
            try
            {
                Thread.sleep(60);
                timer += 65;
                label.setText("Time: " + timer / 1000);
                if(timer >= 60000)
                {
                    gameOver();
                }
            }catch( InterruptedException ex ){}
            tally.setText("Score: " + score);
            this.repaint();
        }
    }

    //Precondition: executed when repaint() or paintImmediately is called
    //Postcondition: the screen has been updated with current player location
    public void paintComponent( Graphics page )
    {
        super.paintComponent( page );
        background.draw( page );
        player.draw( page );

        for(int i = 0; i < splats.size(); i++)
        {
            splats.get(i).draw(page);
        }

        for(int i = 0; i < fruits.size(); i ++)
        {
            fruits.get(i).draw(page);
        }

    }

    // pre - none
    // post - does nothing but fulfill the abstract methods
    public void mouseMoved( MouseEvent event )
    {
        int mouseX = event.getX();
        int mouseY = event.getY();

        player.setXX(mouseX);
        player.setYY(mouseY);
    }

    // pre - the mouse has been dragged
    // post - sets the Player according to the mouse’s movements
    public void mouseDragged(MouseEvent event)
    {
        int mouseX = event.getX();
        int mouseY = event.getY();

        player.setXX(mouseX);
        player.setYY(mouseY);

    }

    // pre - none
    // post - does nothing but fulfill the abstract methods
    public void keyTyped( KeyEvent event )
    {}
    
    // pre - the spot is a valid index in the Fruit ArrayList 
    // post - removes the fruit at the spot indicated, adds a splat where it was on the screen  
    //and adds more fruit accordingly
    public void remove(int spot)
    {
        Fruit a = fruits.get(spot);
        int splx = a.getX();
        int sply = a.getY();
        
        // add splats to replace the fruits
        if(a instanceof Watermelon)
        {
            splats.add(new RedSplat(splx, sply));
        }
        else if(a instanceof Strawberry)
        {
            splats.add(new PinkSplat(splx, sply));
        }
        else if(a instanceof Pineapple)
        {
            splats.add(new YellowSplat(splx, sply));
        }
        else 
        {
            splats.add(new GreenSplat(splx, sply));
        }

        // ensures that there are no more than 5 splats on the screen at once
        if(splats.size() > 5)
        {
            splats.remove(0);
        }

        fruits.set(spot, randomFruit());
        score++;
        for(int i = spot + 1; i <= initialSize; i ++)
        {
            fruits.add(randomFruit());
        }
    }

    // pre - none
    // post - stops the game, changes the labels displayed
    public void gameOver()
    {
        done = true;
        gameOver.setVisible(true);
        tally.setBounds(400, 375, 200, 70);
    }
    
    // pre - none
    // post - generates a random Fruit and adds it to the Fruit ArrayList
    public Fruit randomFruit()
    {
        double random = Math.random() * 4.1;
        if(random > 4.05)
        {
            return new Clock();
        }
        else if(random >= 3 && random <= 4)
        {
            return new Strawberry();
        }
        else if(random < 3 && random > 2)
        {
            return new Pineapple();
        }
        else if(random <= 2 && random >= 1)
        {
            return new Apple();
        }
        else
        {
            return new Watermelon();
        }
    }
}

