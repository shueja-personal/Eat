package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

import control.Eat;

public class Food extends GameObject {
	int speed= 3;
	double angle;
	Color foodColor = Color.GREEN;
	public Player ownPlayer;

	public Food(int x, int y, int width, int height, double angle, Player ownPlayer) {
		super(x, y, width, height);
		this.angle = angle;
		this.ownPlayer = ownPlayer;
		
		foodColor = ownPlayer.playerColor;
		
		//collisionBox = new Rectangle(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	public void update(){
		super.update();
		if(x >= Eat.WIDTH - width || x <= 0) {
			bounceX();
		}
		
		if(y >= Eat.HEIGHT - height || y <= 0) {
			bounceY();
		}
		x += speed* Math.cos(Math.toRadians(angle /*+Math.PI*/ ));
		y += speed* Math.sin(Math.toRadians(angle /*+Math.PI*/));
		// TODO Collisions, deactivation
		
		
	}
	
	public void draw(Graphics g) {
		g.setColor(foodColor);
		g.fillOval(x, y, width, height);
	}
	
	public void bounceX() { 
		angle = 180+angle % 360;
	}
	
	public void bounceY() {
		angle = -angle;
	}
}
