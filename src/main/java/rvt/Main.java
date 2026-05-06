package rvt;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Drawing");
        DrawingCanvas canvas = new DrawingCanvas();

        frame.add(canvas);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

class DrawingCanvas extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Cast to Graphics2D for better control
        Graphics2D g2 = (Graphics2D) g;

        // Draw the house body
        g2.drawRect(200, 200, 200, 200);


        // Daw a roof
        g2.drawLine(150, 200, 300, 150);

        g2.drawLine(300, 150, 450, 200);

        g2.drawLine(150, 200, 450, 200);

        // Draw a door
        g2.drawRect(275, 300, 50, 100);

        // Draw a door handle
        g2.drawOval(310, 350, 10, 10);
    }
}