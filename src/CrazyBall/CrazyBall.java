/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CrazyBall;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author navidroohibroojeni
 */
// no main class for applet, we have init and start
// for running applet, Right click on the file and Run File
public class CrazyBall extends Applet implements Runnable, KeyListener {

    //Image img;   // load image
    //MediaTracker tr;   //The MediaTracker class is a utility class to track the status of 
    // a number of media objects. Media objects could include audio 
    // clips as well as images, though currently only images are supported.    
    private Image i;
    private Graphics doubleG;
    Jumper b;
    Floor[] floor = new Floor[10];
    Award item[] = new Award[10];
    Enemy enemy[] = new Enemy[5];
    Rain rain[] = new Rain[50];

    public static int score;

    double cityX = 0;
    double ciyuY = 0;
    double cityDx = 3;
    Image backGroundImage, enemyImage, iconScoreImage;

    Image imageListBack[] = new Image[2];

    int countImage = 0;

    public static boolean gameOver = false;

    public static AudioClip birdAudioClip, backgroundAudioClip,
            nightTimeAudioClip, thunderAudioClip,
            ballAudioClip, coinAudioClip;

    // init get call then will call start method
    @Override
    public void init() {
        setSize(800, 600);
        addKeyListener(this);

        // background 
        imageListBack[0] = getImage(getCodeBase(), "MediaFiles/b1.png");
        imageListBack[1] = getImage(getCodeBase(), "MediaFiles/b2.png");

        enemyImage = getImage(getCodeBase(), "MediaFiles/enemy.gif");
        iconScoreImage = getImage(getCodeBase(), "MediaFiles/imageSmall.gif");

        birdAudioClip = getAudioClip(getCodeBase(), "MediaFiles/bird_caw1.wav");  // bird sounds, when they collide 
        backgroundAudioClip = getAudioClip(getCodeBase(), "MediaFiles/candy.wav");  // background music 
        nightTimeAudioClip = getAudioClip(getCodeBase(), "MediaFiles/night_time.wav");
        thunderAudioClip = getAudioClip(getCodeBase(), "MediaFiles/thunder.wav");
        ballAudioClip = getAudioClip(getCodeBase(), "MediaFiles/ball.wav");  // ball hit the platforms sound
        coinAudioClip = getAudioClip(getCodeBase(), "MediaFiles/coin.wav");    // award sounds, when ball catch other balls
        /*
         img = getImage(getCodeBase(), "images/ball.gif");  // laod the image, image in Applet are diferent by swing imageIcone
         MediaTracker tr = new MediaTracker(this);
         tr.addImage(img, 0);    // add the image to media tracker
         */

    }

    // start method start after init method
    // 
    @Override
    public void start() {

        b = new Jumper();
        score = 0;
        Random r = new Random();
        for (int i = 0; i < floor.length; i++) {
            floor[i] = new Floor(getWidth() + 200 * i, getHeight() - 40 - r.nextInt(400));
        }
        for (int i = 0; i < item.length; i++) {
            item[i] = new Award(getWidth() + 200 * i);
        }
        for (int i = 0; i < enemy.length; i++) {
            enemy[i] = new Enemy(getWidth() + 200 * i, enemyImage);
        }
        for (int i = 0; i < rain.length; i++) {
            rain[i] = new Rain(10 * i );
        }

        Thread thread = new Thread(this);
        thread.start();

    }

    @Override
    public void run() {

        backgroundAudioClip.loop();
        nightTimeAudioClip.loop();
        thunderAudioClip.loop();

        while (true) {

            if (gameOver) {
                b.setDt(0);
                b.setX(-30);
                b.setY(-30);

            }

            if (cityX > getWidth() * -1) {
                cityX -= cityDx;
            } else {
                cityX = 0;
            }

            for (int i = 0; i < item.length; i++) {
                if (item[i].getY() == this.getHeight() + 100) {
                    item[i] = null;
                    item[i] = new Award(getWidth());
                }
            }

            b.update(this);

            for (int i = 0; i < floor.length; i++) {
                floor[i].update(this, b);
            }
            for (int i = 0; i < item.length; i++) {
                item[i].update(this, b);
            }
            for (int i = 0; i < enemy.length; i++) {
                enemy[i].update(this, b);
            }
            for (int i = 0; i < rain.length; i++) {
                rain[i].update(this);
                
                if (rain[i].getY() > getHeight()){
                    rain[i].setY(i);
                }
                
                
            }

            repaint();
            try {
                Thread.sleep(17);  // millisecond
            } catch (InterruptedException ex) {
                Logger.getLogger(CrazyBall.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public void stop() {
        super.stop(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
    }

    //" Douoble Bufferring "
    // copy the image, and put on topof the this copy
    // this technic called  " Douoble Bufferring ", , means we paint it
    // two times, which blinnking wont happen for our image
    // this method can be copy past, for most situations, 
    @Override
    public void update(Graphics g) {
        if (i == null) {
            i = createImage(this.getSize().width, this.getSize().height);
            doubleG = i.getGraphics();
        }
        doubleG.setColor(getBackground());  // same back ground color
        doubleG.fillRect(0, 0, this.getSize().width, this.getSize().height);

        doubleG.setColor(getForeground());
        paint(doubleG);

        g.drawImage(i, 0, 0, this);
    }

    @Override
    public void paint(Graphics g) {

        //  g.setColor(new Color(15,77,147));
        g.setColor(Color.BLACK);

        g.fillRect(0, 0, getWidth() + 2400, getHeight());
        g.drawImage(imageListBack[0], (int) cityX, 0, this);
        g.drawImage(imageListBack[1], (int) cityX + getWidth(), 0, this);

        // g.drawImage(backGroundImage,(int)cityX + getWidth(),0, this);
        b.paint(g);
        for (int i = 0; i < floor.length; i++) {
            floor[i].paint(g);
        }
        for (int i = 0; i < item.length; i++) {
            item[i].paint(g);
        }
        for (int i = 0; i < enemy.length; i++) {
            enemy[i].paint(g);
        }
        for (int i = 0; i < rain.length; i++) {
            rain[i].paint(g);
        }

        String scoretext = "Score: " + Integer.toString(score);
        Font font = new Font("Serif", Font.BOLD, 32);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString(scoretext, 70 + 2, 70 + 2);  // ceate shadow for the score text
        g.setColor(new Color(198, 255, 255));
        g.drawString(scoretext, 70, 70);
        g.drawImage(iconScoreImage, 20, 20, this);  // icon next to the score text 
        Font font3 = new Font("Serif", Font.BOLD, 32);
        g.setFont(font3);

        
        // I need to add the user if he want to continiu the game
        
        String gameOverString = "GAME OVER";
        if (CrazyBall.gameOver) {
            g.drawString(gameOverString, getWidth() / 2 - 75, getHeight() / 2);

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                b.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                b.moveRight();
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
