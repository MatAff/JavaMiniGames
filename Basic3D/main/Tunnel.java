package main;

import java.util.ArrayList;

public class Tunnel
{

    ArrayList<Line> lines;
    ArrayList<Point> lastRect;
    int rectNr = 0;
    
    public Tunnel() {
        Vector offset;
        
        lines = new ArrayList<Line>();
        lastRect = createRectangle(-100,-100,100,100,100);
        connectRectangle(lastRect);
        
        offset = new Vector(0, 0, 100);
        
        for(int i = 1; i < 5; i++) {
            lastRect = createConnectRectangle(lastRect, offset);
        }        
    }
    
    private ArrayList<Point> createConnectRectangle(ArrayList<Point> rect, Vector offset) {
        //double lastZ;
        double dx;
        double dy;
        ArrayList<Point> newRect;
        
        rectNr++;
        dx = Math.sin(rectNr / 12.0) * 25;
        System.out.println(dx);
        dy = Math.cos(rectNr / 13.5) * 30;
        
        //lastZ = rect.get(0).getZ();
        //newRect = createRectangle(-100 + dx,-100 + dy,100 + dx,100 + dy, lastZ + offset);
        offset = new Vector(offset.getDX() + dx, offset.getDY() + dy, offset.getDZ());
        
        newRect = offsetRectangle(lastRect, offset);
        
        connectRectangle(newRect);
        connectTwoRectangles(rect,newRect);
        
        // Return statement
        return newRect;
        
    }
    
    private void connectTwoRectangles(ArrayList<Point> rect1, ArrayList<Point> rect2) {
    
        lines.add(new Line(rect1.get(0), rect2.get(0)));
        lines.add(new Line(rect1.get(1), rect2.get(1)));
        lines.add(new Line(rect1.get(2), rect2.get(2)));
        lines.add(new Line(rect1.get(3), rect2.get(3)));        
    
    }
    
    private void connectRectangle(ArrayList<Point> rect) {
        lines.add(new Line(rect.get(0), rect.get(1)));
        lines.add(new Line(rect.get(1), rect.get(2)));
        lines.add(new Line(rect.get(2), rect.get(3)));
        lines.add(new Line(rect.get(3), rect.get(0)));        
    }
    
    private ArrayList<Point> offsetRectangle(ArrayList<Point> rect, Vector vec) {
        ArrayList<Point> newRect = new ArrayList<Point>();
        newRect.add(rect.get(0).offset(vec));
        newRect.add(rect.get(1).offset(vec));
        newRect.add(rect.get(2).offset(vec));
        newRect.add(rect.get(3).offset(vec));
        return newRect;
    }
    
    private ArrayList<Point> createRectangle(double x1, double y1, double x2, double y2, double z) {
        ArrayList<Point> rect = new ArrayList<Point>();
        rect.add(new Point(x1,y1,z));
        rect.add(new Point(x1,y2,z));
        rect.add(new Point(x2,y2,z));
        rect.add(new Point(x2,y1,z));
        return(rect);
    }
    
    public void move(double dx, double dy, double dz) {
        
        // Move lines
        for(Line l : lines) {
            l.move(dx,dy,dz);
        }    
        
        // Move last rect
        for(int i = 0; i < 4; i++) {
            lastRect.get(i).move(dx,dy,dz);
        }
        
    }
    
    public void rotate(double rx, double ry, double rz) {
        
        // Rotate line points
        for(Line l : lines) {
            l.rotate(rx,ry,rz);
        }    
        
        // Rotate rectangle points
        for(int i = 0; i < 4; i++) {
            lastRect.get(i).rotate(rx,ry,rz);
        }
        
    }
    
    public void remove() {
        // Remove lines with negative z values
        
        int nrLines = lines.size() - 1;
        Line line;
        boolean containsNegativeZ;
        
        //System.out.println(nrLines);
        
        for(int lNr = nrLines; lNr >=0; lNr--) {
            //System.out.println(lNr);
            line = lines.get(lNr);
            containsNegativeZ = line.containsNegativeZ();
            if(containsNegativeZ == true) {
                lines.remove(lNr);
                //System.out.println("Removing line");
            }
        }
        
    }
    
    public void addSegment() {
        Vector v1;
        Vector v2;
        Vector crossVec;
        if(lastRect.get(0).getZ() < 900) {
            v1 = new Vector(lastRect.get(0), lastRect.get(1));
            v2 = new Vector(lastRect.get(1), lastRect.get(2));
            crossVec = v2.crossProduct(v1);
            crossVec.setLength(100); 
            
            lastRect = createConnectRectangle(lastRect, crossVec);
            
            // Debug
            //System.out.println(v1.toString());
            //System.out.println(v2.toString());
            //System.out.println(crossVec.toString());
        
        }
    }
    
    public ArrayList<Line> getLines() {
        return lines;
    }

}
