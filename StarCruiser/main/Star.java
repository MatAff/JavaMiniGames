package main;

import java.awt.event.KeyEvent;
import java.util.ArrayList;



public class Star extends Sprite {

    private double dx;
    private double dy;
    private double dsize;

    public Star(double x, double y) {
        super(x, y);
        initStar();
    }

    public Star(double x, double y, double size) {
        super(x, y, size);
        initStar();
    }

    public Star(double x, double y, double size, int direction) {
        super(x, y, size);
        initStar();
        
        // Set dx, dy based on direction
        dx = (Math.sin(Math.toRadians(direction)) * size);
        dy = (Math.cos(Math.toRadians(direction)) * size);
        System.out.println(dx);
        System.out.println(dy);
        
    }    
    
    private void initStar() {      
        loadImage("redsquare.png");
        getImageDimensions();
    }

    public void move() {

        x += dx;
        y += dy;

        //if (x < 1) {
        //    x = 1;
        //}

        //if (y < 1) {
        //    y = 1;
        //}
    }

    public void grow() {
        size *= 1.01;
    }
    
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
           grow();
        }

        if (key == KeyEvent.VK_LEFT) {
            
        }

        if (key == KeyEvent.VK_RIGHT) {
            
        }

        if (key == KeyEvent.VK_UP) {
            
        }

        if (key == KeyEvent.VK_DOWN) {
            
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            
        }

        if (key == KeyEvent.VK_RIGHT) {
            
        }

        if (key == KeyEvent.VK_UP) {
            
        }

        if (key == KeyEvent.VK_DOWN) {
            
        }
    }
}