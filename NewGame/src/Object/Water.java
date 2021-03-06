/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import Framework.GameObject;
import Framework.ObjectId;
import Framework.Texture;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import Controller.Animation;
import Controller.Game;

/**
 *
 * @author Do0m
 */

public class Water extends GameObject{
   Texture tex = Game.getTex();
   private Animation animation;
   
   public Water(float x, float y, ObjectId id) {
      super(x, y, id);  
      animation = new Animation(5,tex.Water[1],tex.Water[0]);
   }
   
   @Override
   public void tick(ArrayList<GameObject> object) {
      animation.StartAnimation();
   }

   @Override
   public void render(Graphics g) {
      animation.drawAnimation(g, (int)x, (int)y, 32, 32);
   }

   @Override
   public Rectangle getBounds() {
      return new Rectangle((int)x, (int)y, 32, 32);
   }    
}
