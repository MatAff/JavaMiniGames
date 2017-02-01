package main;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Star extends MovingSprite {

    public Star (double x, double y, double size, double dx, double dy){
        super(x,y, size, size, dx, dy);
        initStar();
    }
    
    private void initStar() {      
        loadImage("redball.png");
        getImageDimensions();
    }

}