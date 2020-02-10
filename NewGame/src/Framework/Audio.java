/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
/**
 *
 * @author Do0m
 */
public class Audio {
   private String path;
   private Clip clip = null;
   private FloatControl volume =null;
   private double d;
   private AudioInputStream ais = null;
   
   public Audio(String path, double d){
      this.path = path;
      this.d = d;
   }
   
   public void sound(){
      File f = new File(path);
      try { 
         ais = AudioSystem.getAudioInputStream(f); 
      }catch (Exception ex) {
         Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
      }
      try {
         clip = AudioSystem.getClip();
         clip.open(ais);
         volume = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
         clip.setFramePosition(0);
         clip.start();
      }catch (Exception ex) {
         Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
   
   public void setVolume(){
      if(d<0) d = 0;
      if(d>1) d = 1;
      float min = volume.getMinimum();
      float max = volume.getMaximum();
      volume.setValue((max-min)* (float)d + min);            
   }
   
   public void Loop(){
      clip.loop(5);
   }
}
