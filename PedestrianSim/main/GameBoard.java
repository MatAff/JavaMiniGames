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
    private ArrayList<Person> people;
    private boolean ingame;
    private final int B_WIDTH = 600;
    private final int B_HEIGHT = 400;
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
        people = new ArrayList<>();

        // Add a number of stars
        for(int i = 0; i < 1; i++) {
            addPerson();
        }

        timer = new Timer(DELAY, this);
        timer.start();
        
        System.out.println("Start new run");
    }

    private void addPerson() {
        
        double xPos;
        double yPos;
        double aimX;
        double aimY;
        double speed;
        Person person;
        
        // Postion
        // xPos = B_WIDTH * Math.random();
        // yPos = 0;
        // aimX = B_WIDTH * Math.random();
        // aimY = B_HEIGHT;

        speed = Math.random() * 4 + 1;
        
        if(Math.random() < 0.5) {
            xPos = 0;
            yPos = 0;
            aimX = B_WIDTH;
            aimY = B_HEIGHT + 100;
        } else {
            xPos = B_WIDTH;
            yPos = 0;
            aimX = 0;
            aimY = B_HEIGHT + 100;
        }
        
        // Create start and add
        person = new Person(xPos, yPos, 20);
        person.setAim(aimX, aimY, speed);
        people.add(person);       
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

        for(Sprite s : people) {
            if (s.isVisible()) {
                //g.drawImage(person.getImage(), (int)s.getX(), (int)s.getY(),this);
                g.drawImage(s.getImage(), (int) s.getX() - (int) s.getXSize()/2, (int) s.getY() - (int) s.getYSize()/2, (int) s.getX() + (int) s.getXSize()/2, (int) s.getY() + (int) s.getYSize()/2, 0,0,s.getImageWidth(), s.getImageHeight(), this);
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

        // Update
        for(Person person : people) {
            person.updateDirection();
            person.avoid(people);
            person.move();
        }
        
        // Add new
        if (Math.random()<0.05) {
            addPerson();
        }
        
        // Check for stars to remove
        removePeople();
        
        repaint();
    }

    private void inGame() {
        
        if (!ingame) {
            timer.stop();
        }
    }

    private void removePeople() {
        Person person;
        
        for(int i = people.size()-1; i >=0; i--) {
            person = people.get(i);
            if(Math.abs(person.getX()) > B_WIDTH || Math.abs(person.getY()) > B_HEIGHT) {
                people.remove(i);
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
