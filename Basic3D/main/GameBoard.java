package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.*;
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
    private boolean ingame;
    private final int B_WIDTH = 1200;
    private final int B_HEIGHT = 700;
    private final int DELAY = 5;   
    //private Box box;
    private Tunnel tunnel;
    private ArrayList<Line> lines;
    private double rx;
    private double ry;
    
    public GameBoard() {
        initBoard();
    }

    private void initBoard() {
        
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.white);
        ingame = true;
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        
        // Create data and lines
        //box = new Box();
        //lines = box.getLines();
        
        // Create tunnel
        tunnel = new Tunnel();
        
        // Timer
        timer = new Timer(DELAY, this);
        timer.start();
        
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

        double[] xy;
        
        //g.setColor(Color.blue);
        //g.drawLine(0,0,100,100);
        
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        
        g.setColor(Color.cyan);
        
        for(Line l : lines) {            
            xy = l.getXY();
            //System.out.println(xy[0]);
            //System.out.println(xy[1]);
            //System.out.println(xy[2]);
            //System.out.println(xy[3]);
            g2.drawLine((int)(xy[0] * B_WIDTH / 2 + B_WIDTH / 2),(int)(xy[1] * B_HEIGHT / 2 + B_HEIGHT / 2),            
            (int)(xy[2] * B_WIDTH  / 2 + B_WIDTH / 2),(int)(xy[3] * B_HEIGHT  / 2 + B_HEIGHT / 2));
        }
                
        g.setColor(Color.red);
        
        for(Line l : lines) {            
            xy = l.getXYOther();
            g2.drawLine((int)(xy[0] * B_WIDTH / 2 + B_WIDTH / 2),(int)(xy[1] * B_HEIGHT / 2 + B_HEIGHT / 2 + 2),            
            (int)(xy[2] * B_WIDTH  / 2 + B_WIDTH / 2),(int)(xy[3] * B_HEIGHT  / 2 + B_HEIGHT / 2 + 2));
        }
        
        
        
        //for(Sprite s : people) {
            //if (s.isVisible()) {
                //g.drawImage(person.getImage(), (int)s.getX(), (int)s.getY(),this);
                //g.drawImage(s.getImage(), (int) s.getX() - (int) s.getXSize()/2, (int) s.getY() - (int) s.getYSize()/2, (int) s.getX() + (int) s.getXSize()/2, (int) s.getY() + (int) s.getYSize()/2, 0,0,s.getImageWidth(), s.getImageHeight(), this);
            //}
        //}

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

        // Update
        tunnel.move(0,0,-0.5);        
        tunnel.rotate(rx,ry,0);
        tunnel.remove();
        tunnel.addSegment();
        
        //move();
        
        lines = tunnel.getLines(); 
     
        repaint();
    }

    private void inGame() {
        
        if (!ingame) {
            timer.stop();
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {
            
            }

            if (key == KeyEvent.VK_LEFT) {
                ry = 0;
                //tunnel.rotate(0,-0.01,0);                
            }

            if (key == KeyEvent.VK_RIGHT) {
                ry = 0;
                //tunnel.rotate(0,0.01,0);                    
            }

            if (key == KeyEvent.VK_UP) {
                rx = 0;
                //tunnel.rotate(-0.01,0,0);
            }

            if (key == KeyEvent.VK_DOWN) {
                rx = 0;
                //tunnel.rotate(0.01,0,0);        
            }    

        }
            
        @Override
        public void keyPressed(KeyEvent e) {

                int key = e.getKeyCode();

                if (key == KeyEvent.VK_LEFT) {
                    ry = -0.001;
                    //tunnel.rotate(0,-0.01,0);                
                }

                if (key == KeyEvent.VK_RIGHT) {
                    ry = 0.001;
                    //tunnel.rotate(0,0.01,0);                    
                }

                if (key == KeyEvent.VK_UP) {
                    rx = -0.001;
                    //tunnel.rotate(-0.01,0,0);
                }

                if (key == KeyEvent.VK_DOWN) {
                    rx = 0.001;
                    //tunnel.rotate(0.01,0,0);        
                }       
        } 
    
    }
}