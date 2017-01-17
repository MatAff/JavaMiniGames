package main;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Sprite {

    protected double x;
    protected double y;
    protected double size;
    protected int width;
    protected int height;
    protected boolean vis;
    protected Image image;

    public Sprite(double x, double y) {
        this.x = x;
        this.y = y;
        vis = true;
    }
    
    public Sprite(double x, double y, double size) {
        this.x = x;
        this.y = y;
        this.size = size;
        vis = true;
    }

    protected void getImageDimensions() {
        width = image.getWidth(null);
        height = image.getHeight(null);
        //size = image.getWidth(null);
    }

    protected void loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }

    public Image getImage() {
        return image;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    public double getSize() {
        return size;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }

    public boolean isVisible() {
        return vis;
    }

    public void setVisible(Boolean visible) {
        vis = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }
}