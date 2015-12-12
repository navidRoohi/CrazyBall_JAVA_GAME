/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CrazyBall;

/**
 *
 * @author navidroohibroojeni
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

/**
 *
 * @author navidroohibroojeni
 */


public class Enemy {
    int dx, dy;
    int x, y, radius;
    private CrazyBall sp;
    
    Image enemyImage;   // load image
   
    
    
    public Enemy(){
        x= 300;
        y= 300;
        radius = 10;
        dx = -2;
        dy = -1;
     
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public Enemy(int x, Image img){
        this.x = x;
        Random r =  new Random();
        y = r.nextInt(400)+radius;
        radius = 10;
        dx = -3;
        
        this.enemyImage = img;

    }
    
    public void update(CrazyBall sp, Jumper b){
        x += dx;
        this.sp = sp;
        checkCollision(b);
        if (x < 0 - radius){
            Random r = new Random();
            x = sp.getWidth() + 2000 + r.nextInt(300);
        }
        
    }
    public void checkCollision(Jumper b){
        
        int ballX = b.getX();
        int ballY = b.getY();
        int ballR = b.getRadius();
        
        int a  =  x - ballX;
        int bb = y - ballY;
        int collide = radius + ballR;
        // distance betwwen objects
        double c = Math.sqrt(((double)a*a) + (double)bb*bb);
        
        if (c < collide){
            performAction(b);
            x=0;
            
            CrazyBall.birdAudioClip.play();
            CrazyBall.score -= 3;
            
            // game over if score is -5
            if (CrazyBall.score < 5){
               // game over
               CrazyBall.gameOver = true;
            }
             
           
        }
    }
    
    public void performAction(Jumper b ){
        
    }
    
     public void paint(Graphics g) {
         
         String gameOverString = "GAME OVER";
         
         g.setColor(Color.GREEN);
         g.drawImage(enemyImage, x, y, null);
         
         if (CrazyBall.gameOver){
             g.drawString(gameOverString, sp.getWidth()/2, sp.getHeight()/2);
             
             
         }

    }
}
