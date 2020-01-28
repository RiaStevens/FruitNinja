// Splat – blueprint for the Splat image that appears when Fruits are hit

import javax.swing.*;
import java.awt.*;

public class Splat extends JLabel
{
    private ImageIcon icon;
    private int x;
    private int y;
    
    // creates a Splat object at the given locations with the given image
    public Splat(int x, int y, ImageIcon icon)
    {
        this.x = x;
        this.y = y;
        this.icon = icon;
    }
    
    // pre - page is a valid Graphics page
    // post - draws the image of the splat on the page
    public void draw(Graphics page)
    {
        Image splat = icon.getImage();
        page.drawImage(splat, x, y, null);
    }
}