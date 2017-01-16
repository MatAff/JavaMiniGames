package main;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class ApplicationStarCruiser extends JFrame {

    public ApplicationStarCruiser() {        
        initUI();
    }
    
    private void initUI() {
        
        add(new GameBoard());
        
        setResizable(false);
        pack();
        
        setTitle("Collision");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ApplicationStarCruiser sc = new ApplicationStarCruiser();
                sc.setVisible(true);
            }
        });
    }
}