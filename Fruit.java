// Fruit - blueprint for a Fruit object that moves up and then down on the screen

import java.awt.*;
import javax.swing.*;

public class Fruit extends JLabel
{
    private int x;        // x position
    private int y;        // y position
    private int startX;
    private double n;
    private boolean thing = false;
    private int interval;
    private ImageIcon image;
    
    // constructor - constructs a Fruit object with the given image at a random x-location at the bottom of the screen
    public Fruit(ImageIcon item)
    {
        y = (int) (Math.random() * 50) + Driver.HEIGHT - 1; //can be changed
        x = (int) (Math.random() * 901) + 1;
        startX = x;
        n = (int) (Math.random() * 10) + 1;
        interval = 12;//(int) (Math.random() * 2) + 11; //small number
        image = item;
        this.setBounds(x, y, 100, 100);
        this.setVisible(true);
        this.setIcon(image);
    }
    
    // pre - none
    // post - moves the fruit either up or down
    public void move()
    {
        if(thing)
        {
            negativeDirection();
        }
        else
        {
            positiveDirection();
        }
        this.setLocation(x, y);
    }
    
    // pre - none
    // post - moves the fruit down at an interval 
    public void negativeDirection()
    {
        y += (interval*interval);
        if(startX < 400)
        {
            x += (int) (Math.sqrt(y/n)) + n;
        }
        else
        {
            x -= (int) (Math.sqrt(y/n)) + n;
        }
        interval ++;
    }
    
    // pre - none
    // post - moves the fruit up at an interval
    public void positiveDirection()
    {
        y -= (interval*interval);
        y += 5;
        if(startX < 400)
        {
            x += (int) (Math.sqrt(y/n)) + n;
        }
        else
        {
            x -= (int) (Math.sqrt(y/n)) + n;
        }
        interval --;
        if(interval == 0)
        {
            thing = true;
        }
    }
    
    // pre - page is a valid Graphics object
    // post - draws the image of the Fruit on the page
     public void draw( Graphics page )
    {
        Image apple = image.getImage();
        page.drawImage(apple, x, y, null);
    }
    
    // pre - none
    // determines whether or not the player hits the fruit
    public boolean isHit(Player player)
    {
        if((player.getX() >= x && player.getX() <= x + 30) && (player.getY() >= y && player.getY() <= y + 30))
        {
            return true;
        }
        return false;
    }
    
    // pre - none
    // post - returns whether or not the fruit has reached the bottom of the screen
    public boolean hitsBottom()
    {
        return y >= Driver.HEIGHT;
    }
    
    // pre - none
    // post - returns the x-value
    public int getX()
    {
        return x;
    } 
    
    // pre - none
    // post - returns the y-value
    public int getY()
    {
        return y;
    }
   
}





