package main;

public class Line
{

    // Declare variable
    double[] xy;
    double[] xyOther;
    
    protected double xx1;
    protected double yy1;
    protected double xx2;
    protected double yy2;    
    protected double xx1Other;
    protected double xx2Other;
    protected Point p1;
    protected Point p2;
    
    public Line(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }
    
    public void computeXY() {
        p1.translate();
        p2.translate();
        xx1 = p1.getXX();
        yy1 = p1.getYY();
        xx2 = p2.getXX();
        yy2 = p2.getYY();
        xx1Other = p1.getXXOther();
        xx2Other = p2.getXXOther();
    }
    
    public double[] getXY() {
        computeXY();
        xyOther = new double[] {xx1Other, yy1, xx2Other, yy2};            
        return xyOther;
    }
    
    public double[] getXYOther() {
        computeXY();
        xy = new double[] {xx1, yy1, xx2, yy2};            
        return xy;
    }
    
    public void move(double dx, double dy, double dz) {
        p1.move(dx, dy, dz);
        p2.move(dx, dy, dz);
    }
    
    public void rotate(double rx, double ry, double rz) {
        p1.rotate(rx, ry, rz);
        p2.rotate(rx, ry, rz);
    }
    
    public boolean containsNegativeZ() {
        //System.out.println("line.containsNegativeZ");
        if (p1.getZ() <=0 || p2.getZ() <=0) {
            return true;
            //System.out.println("Found one");
        } else {
            return false;
            //System.out.println("Found one");
        }
    }
}
