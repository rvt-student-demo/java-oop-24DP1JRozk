package rvt;
import javax.swing.JFrame;

public class Frame {

    public Frame(){
		JFrame frame = new JFrame("Happy Coding");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
    public static void main(String[] args) {
        new Frame();
    }
}
