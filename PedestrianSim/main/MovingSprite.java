package main;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class MovingSprite extends Sprite {

    // Direction
    protected double dx;
    protected double dy;
    //protected double speed;

    public MovingSprite(double x, double y) {
        super(x, y);
    }

    public MovingSprite(double x, double y, double size) {
        super(x, y, size, size);
    }  

    public MovingSprite(double x, double y, double xSize, double ySize) {
        super(x, y, xSize, ySize);
    }  
    
    public MovingSprite(double x, double y, double xSize, double ySize, double dx, double dy) {
        super(x, y, xSize, ySize);
        this.dx = dx;
        this.dy = dy;
    }  
        
    public void move() {
        x += dx;
        y += dy;
    }
    
    public double getDX() {
        return dx;
    }
    
    public double getDY() {
        return dy;
    }
}