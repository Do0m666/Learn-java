/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import Controller.Game;
import Controller.Handler;
import Controller.Menu;

/**
 *
 * @author Do0m
 */
public class KeyInput extends KeyAdapter {
   private Handler handler;
   private Menu menu;
   private Audio audio;
   
   public KeyInput(Handler handler,Menu menu){
      this.handler = handler;
      this.menu = menu;
      audio = new Audio("res/jump.wav",0.68);
   }

   @Override
   public void keyPressed(KeyEvent e) {
      int key = e.getKeyCode();
      if(Game.state == State.Game){
         for(int i = 0; i < handler.objectList.size(); i++){
            GameObject tempObject = handler.objectList.get(i);
            if(tempObject.getId()== ObjectId.Player){
               if(key == KeyEvent.VK_D)tempObject.setVelX(5);
               if(key == KeyEvent.VK_A)tempObject.setVelX(-5);
               if(key == KeyEvent.VK_W && !tempObject.isJumping()){
                  audio.sound();
                  audio.setVolume();
                  tempObject.setJumping(true);
                  tempObject.setVelY(-15);
               }
               if(key == KeyEvent.VK_RIGHT)tempObject.setVelX(5);
               if(key == KeyEvent.VK_LEFT)tempObject.setVelX(-5);
               if(key == KeyEvent.VK_UP && !tempObject.isJumping()){
                  audio.sound();
                  audio.setVolume();
                  tempObject.setJumping(true);
                  tempObject.setVelY(-15);
               }
            }
         }
      }
      if(Game.state == State.Menu){
	 if(key == KeyEvent.VK_W){  menu.setSelected(menu.getSelected() - 1);}
         if(key == KeyEvent.VK_S){ menu.setSelected(menu.getSelected() + 1);}
         if(key == KeyEvent.VK_UP){ menu.setSelected(menu.getSelected() - 1);}
	 if(key == KeyEvent.VK_DOWN){ menu.setSelected(menu.getSelected() + 1);}
         if(key == KeyEvent.VK_ENTER && menu.getSelected() == 0) handler.createGame();
         if(key == KeyEvent.VK_ENTER && menu.getSelected() == 1) {Game.state = State.Options; }
	 if(key == KeyEvent.VK_ENTER && menu.getSelected() == 2) System.exit(1);
      }
      if(Game.state == State.Options){
	 if(key == KeyEvent.VK_BACK_SPACE){
            Game.state = State.Menu;
	 }
      }
      if(Game.state == State.Dead){
         if(key == KeyEvent.VK_ENTER){
	    handler.createGame();
	 }
      }
      if(key == KeyEvent.VK_ESCAPE){
         System.exit(1);
      }
   
   }
   
   @Override
   public void keyReleased(KeyEvent e) {
      int key = e.getKeyCode();
      for(int i = 0; i < handler.objectList.size(); i++){
          GameObject tempObject = handler.objectList.get(i);
          if(tempObject.getId()== ObjectId.Player){
             if(key == KeyEvent.VK_D)tempObject.setVelX(0);
             if(key == KeyEvent.VK_A)tempObject.setVelX(0);
             if(key == KeyEvent.VK_RIGHT)tempObject.setVelX(0);
             if(key == KeyEvent.VK_LEFT)tempObject.setVelX(0);
          }
      }
   }
}

