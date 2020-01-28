// GreenSplat - blueprint for a green splat to replace apples

import javax.swing.*;

public class GreenSplat extends Splat
{
    // constructor - sets up for a green splat at the indicated location
    public GreenSplat(int x, int y)
    {
        super(x, y, new ImageIcon("GreenSplat.png"));
    }
}