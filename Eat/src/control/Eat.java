package control;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;

public class Eat {
	JFrame window;
	GamePanel panel;
	public static int WIDTH;
	public static int HEIGHT;
	public static void main(String[] args) {
		Eat game = new Eat();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		WIDTH = (int) screenSize.getWidth();
		HEIGHT = (int) screenSize.getHeight();
		game.setup();
		
	}
	
	public Eat() {
		window = new JFrame(); 
		panel = new GamePanel();
	}
	
	public void setup() {
		window.add(panel);
		window.addKeyListener(panel);
		/*window.getMaximizedBounds();
		final int WIDTH = window.getMaximizedBounds().width;
		final int HEIGHT = window.getMaximizedBounds().height;*/
		window.setSize(WIDTH,HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window .getContentPane().setPreferredSize(new Dimension(WIDTH,HEIGHT));
        window.pack();
		window.setVisible(true);
		panel.startGame();
	}
}
