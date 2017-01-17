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
        dx = (Math.sin(Math.toRadians(direction)) * size/10);
        dy = (Math.cos(Math.toRadians(direction)) * size/10);
        
    }    
    
    private void initStar() {      
        loadImage("redball.png");
        getImageDimensions();
    }

    public void move() {

        x += dx;
        y += dy;

    }

    public void grow() {
        dx *= 1.0025;
        dy *= 1.0025;
        x -= 0.05;
        y -= 0.05;
        size += 0.1;
        
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