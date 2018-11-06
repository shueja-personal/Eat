package entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Base extends GameObject {
	Color baseColor = Color.BLACK;
	public int deliveredFood = 0;
	public Player ownPlayer;
	public Rectangle collisionBox;
	public Font scoreFont = new Font("Arial", Font.PLAIN, 48);
	
	public Base(int x, int y, int width, int height, Player ownPlayer) {
		super(x, y, width, height);
		this.ownPlayer = ownPlayer;
		collisionBox = new Rectangle(/*x + ownPlayer.width, y + ownPlayer.height, width - (2* ownPlayer.width), height - (2* ownPlayer.height)*/x, y, width, height);
		ownPlayer.ownBase = this;
	}
	
	public void update(){
		super.update();
	}
	
	public void draw(Graphics g) {
		g.setColor(ownPlayer.playerColor);
		g.drawRect(x, y, width, height);
		g.drawRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
		g.setColor(Color.BLACK);
		g.setFont(scoreFont);
		g.drawString("" + deliveredFood, (int) collisionBox.getCenterX(), (int) collisionBox.getCenterY()); 
	}
	
	

}
