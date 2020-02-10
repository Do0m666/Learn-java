/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Framework.State;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import static Controller.Game.state;
import static Controller.Game.coins;
/**
 *
 * @author Do0m
 */
public class Menu{
   Handler controller;
   private int selected = 0;
   
   public Menu(Handler controller){
      this.controller = controller;
   }
   
   public int getSelected(){
      return selected;
   }
   
   public void setSelected(int selected){
      this.selected = selected;
   }

   void render(Graphics g) {
      if(state == State.Menu){
         if(selected < 0)
            selected = 2;
         else if(selected > 2)
                 selected = 0;
	 g.setColor(Color.white);
	 g.setFont(new Font("Myriad pro", 0, 120));
	 g.drawString("My Game:)", 130, 150);
	 if(selected == 0){
	    g.setFont(new Font("Myriad pro", 0, 100));
	    g.drawString("Play", 290, 300);
	 }		
	 else{
	    g.setFont(new Font("Myriad pro", 0, 70));
	    g.drawString("Play", 310, 300);
       	 }
	 if(selected == 1){
	    g.setFont(new Font("Myriad pro", 0, 100)); 
            g.drawString("Options", 220, 400);
         }		
         else{
            g.setFont(new Font("Myriad pro", 0, 70));
            g.drawString("Options", 255, 400);
	 }
         if(selected == 2){
            g.setFont(new Font("Myriad pro", 0, 100));
	    g.drawString("Quit", 300, 500);
         }
         else{
	    g.setFont(new Font("Myriad pro", 0, 70));
	    g.drawString("Quit", 312, 500);
	 }
      }
      if(state == State.Options){
         g.setColor(Color.white);
	 g.setFont(new Font("Myriad pro", 0, 120));
	 g.drawString("My Game:)", 130, 100);
         g.setFont(new Font("Myriad pro", 0, 50));
	 g.drawString("Control", 50, 200);    
	 g.setFont(new Font("Myriad pro", 0, 30));
         g.drawString("*W* -- Jump", 50, 240);    
	 g.setFont(new Font("Myriad pro", 0, 30));
         g.drawString("*D* -- Move forward", 50, 280);              
	 g.setFont(new Font("Myriad pro", 0, 30));
	 g.drawString("*A* -- Move backward", 50, 320);
         g.setColor(Color.yellow);
	 g.setFont(new Font("Myriad pro", 0, 30));
	 g.drawString("By Yury Dumiankou", 190, 380);
	 g.setColor(Color.white);
	 g.setFont(new Font("Myriad pro", 0, 50));
	 g.drawString("Press \"backspace\" key to return", 50, 460);
      }
      if(state == State.Dead){
	 g.setColor(Color.white);
	 g.setFont(new Font("Myriad pro", 0, 100));
	 g.drawString("GAME OVER", 80, 200);
         g.setFont(new Font("Myriad pro", 0, 40));
	 g.drawString("You collected " + coins + " Coins!!!", 215, 400);
	 g.setFont(new Font("Myriad pro", 0, 50));
         g.drawString("Press the \"Enter\" key to try again!", 30, 460);
      }
   }
}
