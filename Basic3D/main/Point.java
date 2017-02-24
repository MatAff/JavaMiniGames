package main;

public class Point {

    // Declare variables
    protected double x;
    protected double y;
    protected double z;
    protected double xx;
    protected double yy;
    protected double xxOther;
    protected double fl = 2; // Focal lenth
    
    // Constructor
    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        translate();
    }
    
    // Translate 3d to 2d
    public void translate() {
        xx = x  * fl / (fl + z);
        yy = y  * fl / (fl + z);
        xxOther = (x + 15)   * fl / (fl + z);
    }
    
    public Point offset(Vector vec) {
        return new Point(x + vec.getDX(), y + vec.getDY(), z + vec.getDZ());
    }
    
    public double getXX() {
        return xx;
    }
    
    public double getYY() {
        return yy;
    }
    
    public double getXXOther() {
        return xxOther;
    }

    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public double getZ() {
        return z;
    }
        
    public void move(double dx, double dy, double dz) {
        x += dx;
        y += dy;
        z += dz;
        translate();
    }
    
    public void rotate(double rx, double ry, double rz) {
        if(rx!=0) {
            y = y * Math.cos(rx) - z * Math.sin(rx);
            z = y * Math.sin(rx) + z * Math.cos(rx);
        }
        if(ry!=0) {
            x = x * Math.cos(ry) - z * Math.sin(ry);
            z = x * Math.sin(ry) + z * Math.cos(ry);
        }
        if(rz!=0) {
            x = x * Math.cos(rz) - y * Math.sin(rz);
            y = x * Math.sin(rz) + y * Math.cos(rz);
        }
        translate();
    }
}









