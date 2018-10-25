package control;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.jar.Pack200;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.omg.CORBA.PUBLIC_MEMBER;

import entities.Base;
import entities.Cannon;
import entities.Player;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer timer;
	//entities.GameObject object;
	Player p1;
	Player p2;
	Base b1;
	Base b2;
	Cannon cannonTL;
	Cannon cannonTR;
	Cannon cannonBL;
	Cannon cannonBR;
	Font titleFont;
	Font subtitleFont;
	public Random random = new Random();
	public static Rectangle arenaNavMesh;
	public ObjectManager manager;
	
	final int MENU_STATE = 0;

	final int GAME_STATE = 1;

	final int END_STATE = 2;
	
	int currentState = MENU_STATE;
	
	public GamePanel() {
		timer = new Timer(1000/60, this);
		p1 = new Player(250, 700, 100, 100, Color.RED);
		p2 = new Player(750, 700, 100, 100, Color.BLUE);
		b1 = new Base(0, Eat.HEIGHT/2 -p1.height, p1.width*2, p1.height*2, p1);
		b2 = new Base(Eat.WIDTH-(p2.width*2), Eat.HEIGHT/2 -p2.height, p2.width*2, p2.height*2, p2);
		cannonTL = new Cannon(/*Eat.WIDTH/2 , Eat.HEIGHT/2*/ 50, 50, 40, 20, 1, 89, p2); 
		cannonTR = new Cannon(Eat.WIDTH - 50, 50, 40, 20, 91, 179, p1); 
		cannonBL = new Cannon(50, Eat.HEIGHT-50, 40, 20, 271, 359, p2); 
		cannonBR = new Cannon(Eat.WIDTH-50, Eat.HEIGHT-50, 40, 20, 181, 269, p1);
		//cannonBR = new Cannon(50, 50, 40, 20, 180, 270);
		
		titleFont = new Font("Arial", Font.PLAIN, 48);
		subtitleFont = new Font("Arial", Font.PLAIN, 24);
		manager = new ObjectManager(p1, p2, cannonTL, cannonTR, cannonBL, cannonBR, b1, b2);
		arenaNavMesh = new Rectangle(50, 50, Eat.WIDTH-100, Eat.HEIGHT-100);
	}
	
	public void startGame() {
		timer.start();
	}
	@Override
	public void actionPerformed(ActionEvent e) { //Runs each frame
		// TODO Auto-generated method stub
		switch(currentState) {
			case MENU_STATE:
			updateMenuState();
			resetGame();
			break;
			case GAME_STATE:
				updateGameState();
				break;
			case END_STATE:
				updateEndState();
				break;
		}
		
	}
	
	  public void paintComponent(Graphics g){
		  switch(currentState) {
			case MENU_STATE:
			drawMenuState(g);
			break;
			case GAME_STATE:
				drawGameState(g);
				break;
			case END_STATE:
				drawEndState(g);
				drawEndState(g);
				break;
		}

	  }

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			switch (currentState) {
				case MENU_STATE:
					currentState = GAME_STATE;
				break;
				case GAME_STATE:
					manager.gameOver = true;
				break;
				case END_STATE:
					manager.gameOver=false;
					currentState=MENU_STATE;
			}
		}
		
		
		if (e.getKeyCode() == KeyEvent.VK_A) {
			p1.speedX = -10;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			p1.speedX = 10;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			p1.speedY = 10;
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			p1.speedY = -10;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			p2.speedX = -10;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			p2.speedX = 10;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			p2.speedY = 10;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			p2.speedY = -10;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			JOptionPane.showMessageDialog(null, "The Red player uses WASD and eats red food\n The Blue player uses arrow keys and eats blue food.\n Deliver food to your base by going inside it.\n First to deliver 1000 food wins!");
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_A) {
			p1.speedX = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			p1.speedX = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			p1.speedY = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			p1.speedY = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			p2.speedX = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			p2.speedX = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			p2.speedY = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			p2.speedY = 0;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
	public void updateMenuState(){
		currentState = MENU_STATE;
		manager.gameOver = false;
	}
	
	public void updateGameState(){
		currentState = GAME_STATE;
		if(random.nextInt(50) == 1) {
			manager.shoot(manager);
		}
		manager.update();
		repaint();
		if (manager.gameOver) {
			updateEndState();
		}
	}
	
	public void updateEndState(){
		currentState = END_STATE;
	}
	
	public void drawMenuState(Graphics g){
		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		g.fillRect(0, 0, Eat.WIDTH, Eat.HEIGHT);
		g.setColor(Color.YELLOW);
		g.drawString("Eat or Be Eaten", 20, 250);
		g.setFont(subtitleFont);
		g.drawString("Press ENTER to start", 100, 500);
		g.drawString("Press SPACE for instructions", 75, 600);
	}
	
	public void drawGameState(Graphics g){
		g.setColor(Color.WHITE);

		g.fillRect(0, 0, Eat.WIDTH, Eat.HEIGHT);
		manager.draw(g);
	}
	
	public void drawEndState(Graphics g){
		g.setColor(manager.winningPlayer.playerColor);

		g.fillRect(0, 0, Eat.WIDTH, Eat.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("Game Over", 75, 250);
		g.setFont(subtitleFont);
		//g.drawString(" + "wins", 125, 500);
		g.drawString("Press Enter to restart.", 100, 600);
	}
	
	public void resetGame() {
		p1 = new Player(50, Eat.HEIGHT/2, 100, 100, Color.RED);
		p2 = new Player(Eat.WIDTH-150, Eat.HEIGHT/2, 100, 100, Color.BLUE);
		b1 = new Base(0, Eat.HEIGHT/2 -p1.height, p1.width*2, p1.height*2, p1);
		b2 = new Base(Eat.WIDTH-(p2.width*2), Eat.HEIGHT/2 -p2.height, p2.width*2, p2.height*2, p2);
		cannonTL = new Cannon(/*Eat.WIDTH/2 , Eat.HEIGHT/2*/ 50, 50, 40, 20, 0, 90, p2); 
		cannonTR = new Cannon(Eat.WIDTH - 50, 50, 40, 20, 90, 180, p1); 
		cannonBL = new Cannon(50, Eat.HEIGHT-50, 40, 20, 270, 359, p2); 
		cannonBR = new Cannon(Eat.WIDTH-50, Eat.HEIGHT-50, 40, 20, 180, 270, p1);
		//cannonBR = new Cannon(50, 50, 40, 20, 180, 270);
		manager = new ObjectManager(p1, p2, cannonTL, cannonTR, cannonBL, cannonBR, b1, b2);
	}
}