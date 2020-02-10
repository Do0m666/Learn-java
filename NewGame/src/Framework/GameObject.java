/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
/**
 *
 * @author Do0m
 */
public abstract class GameObject {
   protected float x,y;
   protected ObjectId id;
   protected float velX = 0, velY = 0;
   protected boolean falling = true;
   protected boolean jumping = false;
   protected int facing = 1;
   
   public GameObject(float x, float y,ObjectId id){
      this.x = x;
      this.y = y;
      this.id = id;
   }
   
   public abstract void tick(ArrayList<GameObject> object);
   public abstract void render(Graphics g);
   public abstract Rectangle getBounds();
   
   public boolean isFalling(){
      return falling;
   }
   
   public boolean isJumping(){
      return jumping;
   }
   
   public void setFalling(boolean falling){
      this.falling = falling;
   }
   
   public void setJumping(boolean jumping){
      this.jumping = jumping;
   }
    
   public  float getX(){
      return x;
   }
   
   public  float getY(){
      return y;
   } 
   
   public void setX(float x){
      this.x = x;
   }
   
   public void setY(float y){
      this.y = y;
   }
  
   public float getVelX(){
      return velX;
   }
   
   public float getVelY(){
      return velY;
   }
   
   public void setVelX(float velx){
      this.velX  = velx;
   }
   
   public void setVelY(float vely){
      this.velY = vely;
   }

   public ObjectId getId(){
      return id;
   }

   public int getFacing() {
      return facing;
   }
}
