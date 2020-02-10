/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Framework.GameObject;

/**
 *
 * @author Do0m
 */
public class Camera {
   private float x, y;
   
   public Camera(float x, float y){
      this.x = x;
      this.y = y;
   }
   
   public void setX(float x){
      this.x = x;
   }
   
   public void getY(float y){
      this.y = y;
   }
   
   public float getX(){
      return x;
   }
   
   public float getY(){
      return y;
   }
   
   public void tick(GameObject player) {
      x = -player.getX() + Game.WIDTH/2;
      y = -player.getY() + Game.HEIGHT/2;
   }
}
