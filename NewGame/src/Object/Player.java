/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import Framework.Audio;
import Framework.GameObject;
import Framework.ObjectId;
import Framework.State;
import Framework.Texture;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import Controller.Animation;
import Controller.Game;
import static Controller.Game.state;
import Controller.Handler;

/**
 *
 * @author Do0m
 */
public class Player extends GameObject {
   private float width = 48, height = 96;
   private float gravity = 0.5f;
   private final float MAX_SPEED = 20;
   public Audio audio;    
   private Handler handler;
   Texture tex = Game.getTex();
   private Animation animation,animationLeft;
   
   public Player(float x, float y,Handler handler, ObjectId id) {
      super(x, y, id);
      this.handler = handler;
      init();
   }
   
   private void init(){
      audio = new Audio("res/Pickup_Coin.wav",0.68);
      animation = new Animation(5,tex.player[1],tex.player[2],tex.player[3],tex.player[4],tex.player[5],tex.player[6]);
      animationLeft = new Animation(5,tex.player[8],tex.player[9],tex.player[10],tex.player[11],tex.player[12],tex.player[13]);
   }
   
   @Override
   public void tick(ArrayList<GameObject> object) {
      x +=velX;
      y += velY;
      if(velX < 0) facing = -1;
      else if(velX > 0) facing = 1;
        
      if(falling || jumping){
         velY +=gravity;
         if(velY > MAX_SPEED){
            velY = MAX_SPEED;
         }
      }
      Collision(object);

      animation.StartAnimation();
      animationLeft.StartAnimation();
   }
   private void Collision(ArrayList<GameObject> object){
      for(int i = 0; i < handler.objectList.size();i++){
         GameObject tempObject = handler.objectList.get(i);
         if(tempObject.getId() == ObjectId.Block){
            if(this.getBoundsTop().intersects(tempObject.getBounds())){
               y = (int) (tempObject.getY()+ 10);
               velY = 0;
            }               
            if(this.getBounds().intersects(tempObject.getBounds())){
                y = (int) (tempObject.getY() - height);
                velY = 0;
                falling = false;
                jumping = false;
            }else{
               falling = true;
            }
            if(this.getBoundsRight().intersects(tempObject.getBounds())){
               x = (int) (tempObject.getX()-width);
            }
            if(this.getBoundsLeft().intersects(tempObject.getBounds())){
               x = (int) (tempObject.getX() + 35);
            }
         }else if(tempObject.getId() == ObjectId.Portal){
            if(getBounds().intersects(tempObject.getBounds())){
               handler.switchLevel();
            }
         }else if(tempObject.getId() == ObjectId.Monet){
            if(getBounds().intersects(tempObject.getBounds())){
               handler.objectList.remove(tempObject);
               Game.coins++;
               Game.health +=100;
               audio.sound();
               audio.setVolume();
            }
         }
         else if(tempObject.getId() == ObjectId.Water){
            if(getBounds().intersects(tempObject.getBounds())){
               state = State.Dead;
            }
         }
      }
   }
   
   @Override
   public void render(Graphics g) {
      if(jumping){
         if(facing == 1){
            g.drawImage(tex.playerJump[2],(int)x,(int)y, 48, 96, null);
         }else if(facing == -1){
            g.drawImage(tex.playerJump[3],(int)x,(int)y, 48, 96, null);
         }
      }else{
         if(velX != 0){
            if(facing == 1){
               animation.drawAnimation(g, (int)x, (int)y, 48, 96);
            }else
               animationLeft.drawAnimation(g, (int)x, (int)y, 48, 96);
         }else{
            if(facing == 1){
               g.drawImage(tex.player[0], (int)x, (int)y, 48, 96, null);
            }else if(facing == -1)
               g.drawImage(tex.player[7], (int)x, (int)y, 48, 96, null);
         }
      }
   }

   @Override
   public Rectangle getBounds() {
        return new Rectangle(
                   (int) ((int)x + (width/2) - ((width / 2) / 2)),
                   (int) ((int)y + (height/2)),
                   (int)width/2,
                   (int)height/2);
   }
   
   public Rectangle getBoundsTop() {
      return new Rectangle(
                 (int) ((int)x + (width/2) - ((width / 2) / 2)),
                 (int)y + 25,
                 (int)width/2,
                 (int)20);
   } 
   
   public Rectangle getBoundsRight() {
      return new Rectangle((int)(x + width -5),(int)y +25,(int)5,(int)height-30);
   }
   
   public Rectangle getBoundsLeft() {
      return new Rectangle((int)x,(int)y+25,(int)5,(int)height-30);
   } 
}
