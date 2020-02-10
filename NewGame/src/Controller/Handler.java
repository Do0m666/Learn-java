/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Framework.GameObject;
import Framework.ObjectId;
import Framework.State;
import Object.Water;
import Object.Block;
import Object.Portal;
import Object.Monet;
import Object.Player;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import static Controller.Game.health;
import static Controller.Game.coins;
/**
 *
 * @author Do0m
 */
public class Handler {
   public ArrayList<GameObject> objectList = new ArrayList<GameObject>();
   private GameObject tempGameObject;
   private BufferedImage level = null, level2 = null;
    
   public Handler(){
      BufferedImageLoader loader = new BufferedImageLoader();
      level = loader.loadImage("/level.png");
      level2 = loader.loadImage("/level2.png");
   }
    
   public void tick(){
      for(int i = 0; i < objectList.size();i++){
         tempGameObject = objectList.get(i);
         tempGameObject.tick(objectList);      
      }
   }
    
   public void render(Graphics g){
      for(int i = 0; i < objectList.size();i++){
         tempGameObject = objectList.get(i);
         tempGameObject.render(g);      
      }
   }
    
   public void switchLevel(){
      coins = 0;
      health = 1000;
      clearLevel();
      switch(Game.LEVEL){
         case 1:
            LoadLevel(level2);
               break;
      }
      Game.LEVEL++;
      Game.state = State.Game;
   }
        
   public void createGame(){
      objectList.clear();
      health = 1000;
      coins=0;
      Game.state = State.Game;
      if(Game.LEVEL == 2){
         LoadLevel(level2);
      }else
         LoadLevel(level);
	      
   }
    
   public void LoadLevel(BufferedImage image){
      int w = image.getWidth();
      int h = image.getHeight();

      for(int x = 0; x < h; x++ ){
          for(int y = 0; y < w; y++){
             int px = image.getRGB(x, y);       
             int r = (px >> 16) & 255;
             int g = (px >> 8) & 255;
             int b = (px) & 255;
             if(Game.coins < 15){
                if(r == 255 && g == 255 & b == 255){
                   addObject(new Block(x*32, y*32,0, ObjectId.Block));
                }
                if(r == 128 && g == 128 & b == 128){
                   addObject(new Block(x*32, y*32,1, ObjectId.Block));
                }
                if(r == 0 && g == 0 & b ==255){
                   addObject(new Player(x*32, y*32, this,  ObjectId.Player));
                }
                if(r == 48 && g == 48 & b == 48){
                   addObject(new Monet(x*32, y*32,ObjectId.Monet));
                } 
                if(r == 33 && g == 0 & b == 127){
                   addObject(new Water(x*32, y*32,ObjectId.Water));
                }
             }
             if(Game.coins == 15){
                if(r == 255 && g == 215 & b == 0){
                   addObject(new Portal(x*32, y*32, ObjectId.Portal));
                }
             }
          }
      }
   }

   public void addObject(GameObject object){
      this.objectList.add(object);
   }
    
   public void removeObject(GameObject object){
      this.objectList.remove(object);
   }
    
   public void clearLevel(){
      objectList.clear();
   }
}

