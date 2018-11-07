package entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import control.Eat;

// The Player class for Eat. 
public class Player extends GameObject {
	public int speedX;
	public int speedY;
	public int heldFood;
	public int diameter;
	public Font scoreFont = new Font("Arial", Font.PLAIN, 48);
	public Color playerColor;
	public Base ownBase;
	

	public Player(int x, int y, int width, int height, Color color) {
		super(x, y, width, height);
		playerColor = color;
		//collisionBox = new Rectangle(x, y, width, height);// TODO Auto-generated constructor stub
	}
	public void update() {
		super.update();
		diameter = (int) (Math.pow(heldFood, 0.75)+100);
		width = diameter;
		height = diameter;
		if( (x+speedX < Eat.WIDTH-50) &&  (x+speedX > 0)){
			x+=speedX;
		}
		if( (y+speedY < Eat.HEIGHT-50) &&  (y+speedY > 0)){
			y+=speedY;
		}


	}
	
	public void draw(Graphics g) {
		g.setColor(playerColor);
		g.fillOval(x, y, diameter, diameter);
		g.setFont(scoreFont);
		g.setColor(Color.BLACK);
		g.drawString("" + heldFood, (int) collisionBox.getCenterX(), (int) collisionBox.getCenterY()); 
	}

}
