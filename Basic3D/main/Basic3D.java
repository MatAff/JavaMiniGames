package main;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Basic3D extends JFrame {

    public Basic3D() {        
        initUI();
    }
    
    private void initUI() {
        
        add(new GameBoard());
        
        setResizable(true);
        pack();
        
        setTitle("Basic3D");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Basic3D app = new Basic3D();
                app.setVisible(true);
            }
        });
    }
}