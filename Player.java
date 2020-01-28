// Player - blueprint for a Player that is a line controlled by mouse drags

import java.awt.*;

public class Player
{
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    
    // default constructor – sets the Player's location at the top-left corner
    public Player()
    {
        x1 = 0;
        y1 = 0;
        x2 = 0;
        y2 = 0;       
    }
    
    // pre - none
    // post - returns the x1-value
    public int getX()
    {
        return x1;
    }
    
    // pre - none
    // post - returns the y1-value
    public int getY()
    {
        return y1;
    }

    // pre - a >= 0
    // post - sets the x1-value accordingly
    public void setX( int a )
    {
        x1 = a;
    }
    
    // pre - a >= 0
    // post - sets the x2-value accordingly
    public void setXX( int a ) 
    {
        setX(x2);
        x2 = a;
    }
    
    // pre - b >= 0
    // post - sets the y1-value accordingly
    public void setY( int b )
    {
        y1 = b;
    }
    
    // pre - b >= 0
    // post - sets the y2-value accordingly
    public void setYY( int b )
    {
        setY(y2);
        y2 = b;
    }
       
    // pre - page is a valid Graphics object, x1 ≠ x2, y1 ≠ y2
    // post - draws a white line between the coordinates (x1, y1) and (x2, y2) on the page
    public void draw(Graphics page)
    {
        page.setColor(Color.WHITE);
        page.drawLine(x1, y1, x2, y2);
    }
}



