/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CrazyBall;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author navidroohibroojeni
 */
public class Floor {
    int dx, dy;
    int x, y, width, height;

    
    public Floor(){
        x= 300;
        y= 300;
        width = 120;
        height = 10;
        dx = -1;
        dy = -1;
    }
    
    public Floor(int x, int y){
        this.x = x;
        this.y = y;
        width = 120;
        height = 40;
        dx = -1;
        dy = -1;

    }
    
    public void update(CrazyBall sp, Jumper b){
        //y -= dy;
    /*
        checkCollision(b);
        if (y > sp.getHeight()){
            Random r = new Random();
            y =  r.nextInt(300) * 4;
        }
            */

        x += dx;
        checkCollision(b);
        if (x < 0 - width){
            Random r = new Random();
            y= sp.getHeight() - 40 - r.nextInt(400);
            x = sp.getWidth() + r.nextInt(300);
        }
        
    }
    public void checkCollision(Jumper b){
        
        int ballX = b.getX();
        int ballY = b.getY();
        int radius = b.getRadius();
        
        if (ballY + radius > y  && ballY + radius < y +height ){
            if (ballX > x && ballX < x + width){
                
                 double newDY = b.getGameDy();
                 b.setDy(y-radius);
                 b.setDy(newDY);
                 CrazyBall.ballAudioClip.play();
        }
      }
    
    }
    
     public void paint(Graphics g) {
         
         g.setColor(Color.GREEN);
         g.fillRect(x, y, width, height);

    }
    
}
