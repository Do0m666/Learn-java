/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;

import java.awt.image.BufferedImage;

/**
 *
 * @author Do0m
 */
public class Sprite {
   private BufferedImage image;
   
   public Sprite(BufferedImage image){
      this.image = image;
   }
   
   public BufferedImage loadSpriteImage(int col, int row, int width, int height){
      BufferedImage img = image.getSubimage((col*width) -width,(row * height) - height, width, height);
      return img;
   }
}
