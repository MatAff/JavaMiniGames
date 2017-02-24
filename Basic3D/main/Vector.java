package main;

public class Vector
{
    double dx;
    double dy;
    double dz;
    
    public Vector(double dx, double dy, double dz) {
        this.dx = dx;
        this.dy = dy;
        this.dz = dz;
    }
    
    public Vector(Point p1, Point p2) {
        dx = p1.getX() - p2.getX();
        dy = p1.getY() - p2.getY();
        dz = p1.getZ() - p2.getZ();
    }

    public double getDX() {
        return dx;
    }
    
    public double getDY() {
        return dy;
    }
    
    public double getDZ() {
        return dz;
    }
    
    public String toString() {
        return dx + "; " + dy + "; " + dz;
    }
    
    // Method to get line perpendicular to plane
    public Vector crossProduct(Vector other) {
        return new Vector(dy*other.getDZ() - dz*other.getDY(), dz*other.getDX() - dx*other.getDZ(), dx * other.getDY() - dy * other.getDX()); 
        //a2*b3 - a3*b2, a3*b1 - a1*b3, a1*b2 - a2*b1
    }
    
    public void setLength(double l) {
        double currentLength;
        currentLength = Math.sqrt(dx * dx + dy * dy + dz * dz);
        dx = dx * l / currentLength;
        dy = dy * l / currentLength;
        dz = dz * l / currentLength;
    }
    
}
