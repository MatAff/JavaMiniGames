package main;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Sprite {

    // Image
    protected Image image;
    protected int imageWidth;
    protected int imageHeight;
    protected boolean vis;

    // Position and size
    protected double x;
    protected double y;
    protected double xSize;
    protected double ySize;
    
    // Constructor
    public Sprite(double x, double y) {
        this.x = x;
        this.y = y;
        vis = true;
    }
    
    // Constructor
    public Sprite(double x, double y, double xSize, double ySize) {
        this.x = x;
        this.y = y;
        this.xSize = xSize;
        this.ySize = ySize;
        vis = true;
    }

    // Load image
    protected void loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }

    protected void getImageDimensions() {
        imageWidth = image.getWidth(null);
        imageHeight = image.getHeight(null);
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
    
    public double getXSize() {
        return xSize;
    }

    public double getYSize() {
        return ySize;
    }
    
    public int getImageWidth() {
        return imageWidth;
    }
    
    public int getImageHeight() {
        return imageHeight;
    }

    public boolean isVisible() {
        return vis;
    }

    public void setVisible(Boolean visible) {
        vis = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, (int) xSize, (int) ySize);
    }
}