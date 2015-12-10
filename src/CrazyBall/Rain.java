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


public class Rain {
    int dx, dy;
    int x, y, radius;
    private CrazyBall sp;
    
    public Rain(){
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
    
    public Rain(int y){
        this.y = y;
        Random r =  new Random();
        x = r.nextInt(400)+radius;
        radius = 10;
        dy = -2;
        dx = -2;
   
       
    }
    public void update(CrazyBall sp){

        y += dy ;
        this.sp = sp;
        Random r = new Random();
         
        if (y < 0 - radius){
           
            x = sp.getHeight() + 2000 + r.nextInt(300);
        }

    }
 
    
     public void paint(Graphics g) {
         

         g.setColor(Color.BLUE);
         g.fillOval(x - radius, y - radius, 5, 5);

    }
     
}