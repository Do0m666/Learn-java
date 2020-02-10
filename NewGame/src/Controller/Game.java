/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Framework.Audio;
import Framework.KeyInput;
import Framework.ObjectId;
import Framework.State;
import Framework.Texture;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Do0m
 */
public class Game extends Canvas implements Runnable {
   public static int WIDTH,HEIGHT;
   public static int LEVEL = 1;
   public static int coins = 0;
   public static int health =1000;
   private boolean running = false;
   private Thread thread;
   private BufferedImage level = null;
   private BufferedImage icon = null;
   private BufferedImage clouds = null;
   private Handler handler;
   private Camera camera;
   private Audio audio;
   static Texture texture;
   private Menu menu;
   public static State state = State.Menu; 

   private void init(){
      WIDTH = getWidth();
      HEIGHT = getHeight();
      texture = new Texture();
      menu = new Menu(handler);
      BufferedImageLoader loader = new BufferedImageLoader();
      level = loader.loadImage("/level.png");
      clouds = loader.loadImage("/cloud.jpg");
      icon = loader.loadImage("/1.png");
      handler = new Handler();
      camera = new Camera(0,0);
      audio = new Audio("res/level.wav",0.68);
      handler.LoadLevel(level);
      this.addKeyListener((new KeyInput(handler,menu)));
   }

   public void start(){
      if(running){
         return;
      }
      running = true;
      thread = new Thread(this);
      thread.start();
   }
    
   @Override
   public void run() {
      init();
      audio.sound();
      audio.setVolume();
      audio.Loop();
      this.requestFocus();
        
      long lastTime = System.nanoTime();
      double amountOfTicks = 60.0;
      double ns = 1000000000 / amountOfTicks;
      double delta = 0;
      long timer = System.currentTimeMillis();
      int updates = 0;
      int frames = 0;
      while(running){
         long now = System.nanoTime();
         delta += (now - lastTime) / ns;
         lastTime = now;
         while(delta >= 1){
	    tick();
            updates++;
            delta--;
         }
         render();
         frames++;
			
         if(System.currentTimeMillis() - timer > 1000){
            timer += 1000;
	    frames = 0;
	    updates = 0;
         }
      }
   }
 
   private void tick() {
      handler.tick();
      if(state == State.Game ){
         health--;
         if(health == 0){
            state = State.Dead;
         }  
         for(int i = 0; i < handler.objectList.size(); i++){
            if(handler.objectList.get(i).getId()==ObjectId.Player){
               camera.tick(handler.objectList.get(i)); 
            }
         }
      }
   }
   
   private void render() {
      BufferStrategy bs = this.getBufferStrategy();
      if(bs== null){
         this.createBufferStrategy(3);
         return;
      }
      Graphics g = bs.getDrawGraphics();
      Graphics2D g2d = (Graphics2D)g;
       
      g.setColor(new Color(71,174,191));
      g.fillRect(0, 0, getWidth(), getHeight());
       
      if(state == State.Game){   
         g2d.translate(camera.getX(), camera.getY());
         if(coins ==15){
            handler.LoadLevel(level);
         }
         for(int xx = -200 ; xx < clouds.getWidth() * 50; xx+=clouds.getWidth()){
            g.drawImage(clouds, xx - 700 + (int)camera.getX()/2, 3000, this);  
         }

         g.setColor(Color.white);
         Font currentFont = g.getFont();
         Font newFont = currentFont.deriveFont(currentFont.getSize() * 2.4F);
         g.setFont(newFont);
       
         handler.render(g);
         g.drawImage(icon, 630 - (int)camera.getX() , (int)43  - (int)camera.getY(), this); 
         g.drawString(health /100 + " / Time",640  - (int)camera.getX(),70 - (int)camera.getY());
         g.drawImage(icon,(int) 50 - (int)camera.getX() ,(int) 43 - (int)camera.getY(), this);   
         g.drawString(coins + " / 15",(int) 70 -(int) camera.getX() , (int)70 - (int)camera.getY());
      }
      if(state == State.Menu){
         menu.render(g);
      }
      if(state == State.Options){
         menu.render(g);		
      }
      if(state == State.Dead){
         menu.render(g);
      }
      g.dispose();
      bs.show();
   }
    
   public static Texture getTex(){
      return texture;
   }
    
   public static void main(String args[]){
      new Window(800,600,"My Game",new Game());
   }
}
