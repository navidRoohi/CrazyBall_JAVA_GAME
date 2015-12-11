/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CrazyBall;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author navidroohibroojeni
 */


public class Award {
    int dx, dy;
    int x, y, radius;
    private CrazyBall sp;
    
    public Award(){
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
    
    public Award(int x){
        this.x = x;
        Random r =  new Random();
        y = r.nextInt(400)+radius;
        radius = 10;
        dx = -2;
        
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
            x = 0;
            y = sp.getHeight() + 100;
            CrazyBall.score++;                   // score numbers
            CrazyBall.coinAudioClip.play();
           
        }
    }
    
    public void performAction(Jumper b) {
        
    }
    
     public void paint(Graphics g) {
         
         Font font2 = new Font("Serif", Font.BOLD, 12);
         g.setFont(font2);

         g.setColor(Color.YELLOW);
         g.drawString("Award", x-3 , y- 10);
         g.fillOval(x - radius, y - radius, radius * 4, radius * 2);

    }

    
}
