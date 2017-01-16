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
    private Star star;
    private boolean ingame;
    //private final int ISTAR_X = 40;
    //private final int ISTAR_Y = 60;
    private final int B_WIDTH = 400;
    private final int B_HEIGHT = 300;
    private final int DELAY = 15;   

    public GameBoard() {
        initBoard();
    }

    private void initBoard() {

        int randomDirection;
        
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        ingame = true;
        
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        randomDirection = (int)(Math.random() * 360);
        star = new Star(B_WIDTH / 2 - 25, B_HEIGHT / 2 - 25, 1, randomDirection);       // Start in middle of screen

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

        if (star.isVisible()) {
            //g.drawImage(star.getImage(), star.getX(), star.getY(),this);
            g.drawImage(star.getImage(), (int) star.getX(), (int) star.getY(), (int) star.getX() + (int) star.getSize(), (int) star.getY() + (int) star.getSize(), 0,0,star.getWidth(), star.getHeight(), this);
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

        updateStar();

        repaint();
    }

    private void inGame() {
        
        if (!ingame) {
            timer.stop();
        }
    }

    private void updateStar() {

        if (star.isVisible()) {
            star.move();
            star.grow();
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            star.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            star.keyPressed(e);
        }
    }
}
