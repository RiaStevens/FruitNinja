// Background - blueprint for a JLabel to represent the game's wooden background

import javax.swing.*;
import java.awt.*;

public class Background extends JLabel
{
    private ImageIcon image;
    
    // constructor - sets up a background using a specific image and occupying the whole game area
    public Background()
    {
        image = new ImageIcon("Background.png");
        this.setBounds(0, 0, Driver.WIDTH, Driver.HEIGHT);
        this.setVisible(true);
        this.setIcon(image);
    }
    
    // pre - page is a valid Graphics object
    // post - draws the image on the screen
    public void draw(Graphics page)
    {               
        Image background = image.getImage();
        page.drawImage(background, 0, 0, null);
    }
}
