/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;

import java.awt.image.BufferedImage;
import Controller.BufferedImageLoader;

/**
 *
 * @author Do0m
 */
public class Texture {
   private Sprite blockSprite,playerSprite;
   private BufferedImage blockImage = null;
   private BufferedImage playerImage = null;
   public BufferedImage[] block = new BufferedImage[2];
   public BufferedImage[] monet = new BufferedImage[6];
   public BufferedImage[] player = new BufferedImage[14];
   public BufferedImage[] playerJump = new BufferedImage[6];
   public BufferedImage[] Water = new BufferedImage[2];
   public BufferedImage[] portal = new BufferedImage[2];
   public Texture(){
      BufferedImageLoader loader = new BufferedImageLoader();
      try{
         blockImage = loader.loadImage("/blockImage.png");
         playerImage = loader.loadImage("/playerImage.png");
      }catch(Exception e){
         e.printStackTrace();
      }
      blockSprite = new Sprite(blockImage);
      playerSprite = new Sprite(playerImage);   
      getTextures();
   }

   private void getTextures() {
      block[0] = blockSprite.loadSpriteImage(1, 1, 32, 32);
      block[1] = blockSprite.loadSpriteImage(2, 1, 32, 32);
       
      player[0] = playerSprite.loadSpriteImage(1, 1, 32, 64);
      player[1] = playerSprite.loadSpriteImage(2, 1, 32, 64);
      player[2] = playerSprite.loadSpriteImage(3, 1, 32, 64);
      player[3] = playerSprite.loadSpriteImage(4, 1, 32, 64);
      player[4] = playerSprite.loadSpriteImage(5, 1, 32, 64);
      player[5] = playerSprite.loadSpriteImage(6, 1, 32, 64);
      player[6] = playerSprite.loadSpriteImage(7, 1, 32, 64);
          
      player[7] = playerSprite.loadSpriteImage(20, 1, 32, 64);
      player[8] = playerSprite.loadSpriteImage(19, 1, 32, 64);
      player[9] = playerSprite.loadSpriteImage(18, 1, 32, 64);
      player[10] = playerSprite.loadSpriteImage(17, 1, 32, 64);
      player[11] = playerSprite.loadSpriteImage(16, 1, 32, 64);
      player[12] = playerSprite.loadSpriteImage(15, 1, 32, 64);
      player[13] = playerSprite.loadSpriteImage(14, 1, 32, 64);
       
      playerJump[0] = playerSprite.loadSpriteImage(8, 1, 32, 64);
      playerJump[1] = playerSprite.loadSpriteImage(9, 1, 32, 64);
      playerJump[2] = playerSprite.loadSpriteImage(10, 1, 32, 64);
      playerJump[3] = playerSprite.loadSpriteImage(11, 1, 32, 64);
      playerJump[4] = playerSprite.loadSpriteImage(12, 1, 32, 64);
      playerJump[5] = playerSprite.loadSpriteImage(13, 1, 32, 64);
       
      monet[0] = blockSprite.loadSpriteImage(1, 2, 32, 32);
      monet[1] = blockSprite.loadSpriteImage(2, 2, 32, 32);
      monet[2] = blockSprite.loadSpriteImage(3, 2, 32, 32);
      monet[3] = blockSprite.loadSpriteImage(4, 2, 32, 32);
      monet[4] = blockSprite.loadSpriteImage(5, 2, 32, 32);
      monet[5] = blockSprite.loadSpriteImage(6, 2, 32, 32);
      
      Water[0] = blockSprite.loadSpriteImage(3, 1, 32, 32);
      Water[1] = blockSprite.loadSpriteImage(4, 1, 32, 32);
       
      portal[0] = blockSprite.loadSpriteImage(1, 3, 32, 32);
      portal[1] = blockSprite.loadSpriteImage(2, 3, 32, 32);           
   }
}
