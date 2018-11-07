package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import control.Eat;

public class Food extends GameObject {
	int speed= 3;
	double angle;
	Color foodColor = Color.GREEN;
	public Player ownPlayer;
	Random random = new Random();

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
		angle = 180+angle % 360 + random.nextDouble() - 0.5;
	}
	
	public void bounceY() {
		angle = -angle + random.nextDouble() - 0.5;
	}
}
