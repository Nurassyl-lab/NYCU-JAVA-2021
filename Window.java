//package com.adventure.game;

import javax.swing.JFrame;

public class Window extends JFrame {
    public Window()
    {
        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel(600, 600));
		setIgnoreRepaint(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
 