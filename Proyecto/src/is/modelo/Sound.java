package is.modelo;

import javax.sound.sampled.*;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;

public class Sound
{
    private static Sound miSound=null;

    private Sound(){}

    public static Sound getMiSound()
    {
        if(miSound==null)
        {
            miSound=new Sound();
        }
        return miSound;
    }

    public void moneySound()
    {
        AudioClip sonido;
        sonido = java.applet.Applet.newAudioClip(Sound.getMiSound().getClass().getResource("/resource/coin.wav"));
        sonido.play();
    }

    //Parece haber algun problema con el explosion.wav al bajarle de volumen, por eso uso el explosion1 a pesar de que se escuche alto
    public void bomSound()
    {
        AudioClip sonido;
        sonido = java.applet.Applet.newAudioClip(Sound.getMiSound().getClass().getResource("/resource/explosion1.wav"));
        sonido.play();
    }
}
