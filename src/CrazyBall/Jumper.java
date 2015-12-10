/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CrazyBall;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author navidroohibroojeni
 */
public class Jumper {

    private double gravity = 15;
    private double energyloss = 1;  // energy loss 
    private double dt = .2;  // changing time 
    private double xFriction = .9;
    private int x = 400;
    private int y = 25;
    private double dx = 0;
    private double dy = 0;
    private double gameDy = -75;
    private int radius = 20;

    Jumper() {
    }

    Jumper(int i, int j) {
        x = i;
        y = j;
    }

    public double getGameDy() {
        return gameDy;
    }

    public void setGameDy(double gameDy) {
        this.gameDy = gameDy;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public double getEnergyloss() {
        return energyloss;
    }

    public void setEnergyloss(double energyloss) {
        this.energyloss = energyloss;
    }

    public double getDt() {
        return dt;
    }

    public void setDt(double dt) {
        this.dt = dt;
    }

    public double getxFriction() {
        return xFriction;
    }

    public void setxFriction(double xFriction) {
        this.xFriction = xFriction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void moveRight() {
        if (dx + 1 < 20) {
            dx += 1;
        }
    }

    public void moveLeft() {
        if (dx - 1 > -20) {
            dx -= 1;
        }
    }

    public void update(CrazyBall sp) {

        if (x + dx > sp.getWidth() - radius) {
            x = sp.getWidth() - radius - 1;
            dx = -dx;

        } else if (x + dx < 0 + radius) {
            x = 0 + radius;
            dx = -dx;
        } else {
            x += dx;
        }

        if (y > sp.getHeight() - radius - 1) {
            y = sp.getHeight() - radius - 1;
            dx *= xFriction;
            dy *= energyloss;  // energy loss 
            dy = gameDy;
        } else {
            dy += gravity * dt;   // velocity formula
            y += dy * dt + .5 * gravity * dt * dt;  // position formula

        }
        

    }

    public void paint(Graphics g) {

        g.setColor(Color.RED);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
       

    }

}
