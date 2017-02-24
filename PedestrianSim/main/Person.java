package main;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Person extends MovingSprite {

    protected double xAim;
    protected double yAim;
    protected double speed;
    
    public Person (double x, double y) {
        super(x, y);
        initPerson();
    }
    
    public Person (double x, double y, double size){
        super(x,y, size);
        initPerson();
    }
    
    public Person (double x, double y, double size, double dx, double dy){
        super(x,y, size, size, dx, dy);
        initPerson();
    }
    
    private void initPerson() {      
        loadImage("redball.png");
        getImageDimensions();
    }

    public void setAim(double xAim, double yAim) {
        setAim(xAim, yAim, 2);
    }
    
    public void setAim(double xAim, double yAim, double speed) {
        this.xAim = xAim;
        this.yAim = yAim;
        this.speed = speed;
    }
    
    public void updateDirection() {

        double diagonal;
        
        // Compute vector
        dx = (xAim - x) / 20;
        dy = (yAim - y) / 20;
        
        // Vector length
        diagonal = Math.sqrt(dx * dx + dy * dy);
        
        // Adjust for vector length
        dx *= speed / diagonal;
        dy *= speed / diagonal;
                     
    }
    
    public void avoid(ArrayList<Person> people){

        // Declare variables
        double otherX;
        double otherY;
        double otherDX;
        double otherDY;
        double difX;
        double difY;
        double dif;
        boolean collide;
        
        // Loop and identify potential collision
        for(MovingSprite s : people) {
                        
            otherX = s.getX();
            otherY = s.getY();
            otherDX = s.getDX();
            otherDY = s.getDY();            
            
            // Exclude self
            if(x!=otherX || y!=otherY) {
            
                collide = false;
            
                // Compute distance for next three postions
                for(int i = 1; i < 10; i++) {
                    difX = Math.abs((x + dx * i) - (otherX + otherDX * i));
                    difY = Math.abs((y + dy * i) - (otherY + otherDY * i));
                    dif = Math.sqrt(difX * difX + difY * difY);
                    
                    if(dif < 20 + Math.random() * 10) {
                        collide = true;
                    }           
                     
                }
                
                if(collide == true) {                
                    System.out.println("Boom");
                }
                
                // Do something about colision
                if(collide == true) {
                    dx = -dx;
                }
            }

        }
    }
        
}