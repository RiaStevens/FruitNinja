import javax.swing.JApplet;
import java.applet.*;
import java.net.URL;

public class Sound
{
    private AudioClip clip;
    
    //sound constructor
    public Sound(String file)
    {
        clip = Applet.newAudioClip(getClass().getResource(file));
    }
    //pre: none
    //post: plays audio clip
    public void play()
    {
        clip.play();
    }
}