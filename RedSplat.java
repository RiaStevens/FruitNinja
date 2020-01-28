// RedSplat - blueprint for a red splat to replace watermelons

import javax.swing.*;

public class RedSplat extends Splat
{
    // constructor - sets up for a red splat at the indicated location
    public RedSplat(int x, int y)
    {
        super(x, y, new ImageIcon("RedSplat.png"));
    }
}