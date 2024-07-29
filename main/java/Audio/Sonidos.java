package Audio;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sonidos {
    
    private Clip clip;
    
    public Sonidos(){
    }
 
    public void cargarAudio(String ruta) {
		
		try {
			
			File sonido_intermision = new File(ruta);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(sonido_intermision);
			
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			
		} catch (Exception e) {
			e.printStackTrace();
		}          
	}
    
    public void playSonido(){
        
        if (clip != null) {
            
            clip.setFramePosition(0);
            clip.start();
        }
    }
    
    public void detenerSonido(){
        
        if(clip != null && clip.isRunning()){
          clip.stop();
        }
    }
    
    public void playSonidoLoop(){
        
        if(clip != null){
            clip.setFramePosition(0);
            clip.start();
            clip.loop(clip.LOOP_CONTINUOUSLY);
        }
    }

    public Clip getClip() {
        return clip;
    }

    public Boolean isRunning() {
        return clip.isRunning();
    }
    
    
    
}   
