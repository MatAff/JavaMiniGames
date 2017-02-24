package main;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class PedestrianSim extends JFrame {

    public PedestrianSim() {        
        initUI();
    }
    
    private void initUI() {
        
        add(new GameBoard());
        
        setResizable(false);
        pack();
        
        setTitle("Pedestrian Simulation");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                PedestrianSim sc = new PedestrianSim();
                sc.setVisible(true);
            }
        });
    }
}