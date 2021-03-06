package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameBoard extends JPanel implements ActionListener {

    private Timer timer;
    private ArrayList<Star> stars;
    private boolean ingame;
    private final int B_WIDTH = 1200;
    private final int B_HEIGHT = 900;
    private final int DELAY = 15;   

    public GameBoard() {
        initBoard();
    }

    private void initBoard() {
        
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        ingame = true;
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        
        // Initiate array list
        stars = new ArrayList<>();

        // Add a number of stars
        for(int i = 0; i < 1; i++) {
            addStar();
        }

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void addStar() {
        
        double xPos;
        double yPos;
        double size;
        double dxToMid;
        double dyToMid;
        double distToMid;
        double diagonal;
        double dx;
        double dy;
        Star star;
        
        // Postion
        xPos = (Math.random() * B_WIDTH);
        yPos = (Math.random() * B_HEIGHT);

        // Size
        size = 1; 
        
        // Random direction
        //dx = Math.random() - 0.5;
        //dy = Math.random() - 0.5;
                        
        // Direction and speed based on position
        dxToMid = xPos - B_WIDTH/2;
        dyToMid = yPos - B_HEIGHT/2;
        distToMid = Math.sqrt(dxToMid * dxToMid + dyToMid * dyToMid);
        diagonal = Math.sqrt(B_WIDTH * B_WIDTH / 4 + B_HEIGHT * B_HEIGHT / 4);
        dx = Math.signum(dxToMid) * (diagonal - Math.abs(distToMid)) / B_WIDTH;
        dy = Math.signum(dyToMid) * (diagonal - Math.abs(distToMid)) / B_WIDTH;
        
        // 400
        // 0 > -200 200 - 200 = 0
        // 400
        
        // Create start and add
        star = new Star(xPos, yPos, size * (diagonal - Math.abs(distToMid)) / diagonal, dx, dy);
        stars.add(star);       
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (ingame) {

            drawObjects(g);

        } else {

            drawGameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {

        for(Star star : stars) {
            if (star.isVisible()) {
                //g.drawImage(star.getImage(), star.getX(), star.getY(),this);
                g.drawImage(star.getImage(), (int) star.getX() - (int) star.getXSize()/2, (int) star.getY() - (int) star.getYSize()/2, (int) star.getX() + (int) star.getXSize()/2, (int) star.getY() + (int) star.getYSize()/2, 0,0,star.getImageWidth(), star.getImageHeight(), this);
            }
        }

    }

    private void drawGameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,B_HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        inGame();

        for(Star star : stars) {
            updateStar();
        }
        
        // Add new
        if (Math.random()<0.1) {
            addStar();
        }
        
        // Check for stars to remove
        removeStars();

        repaint();
    }

    private void inGame() {
        
        if (!ingame) {
            timer.stop();
        }
    }

    private void updateStar() {

        // Update position
        for(Star star : stars) {
            if (star.isVisible()) {
                star.move();
                star.grow();               
            }
        }
        
        
    }

    private void removeStars() {
        Star star;
        
        for(int i = stars.size()-1; i >=0; i--) {
            star = stars.get(i);
            if(Math.abs(star.getX()) > B_WIDTH || Math.abs(star.getY()) > B_WIDTH) {
                stars.remove(i);
            }
        }
        
    }

    
    private class TAdapter extends KeyAdapter {

        /*@Override
        public void keyReleased(KeyEvent e) {
            star.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            star.keyPressed(e);
        }*/
    } 
}
