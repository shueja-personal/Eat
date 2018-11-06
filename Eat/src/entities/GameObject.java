package entities;

import java.awt.Graphics;
import java.awt.Rectangle;




public class GameObject {
	public int x;
	public int y;
	public int width;
	public int height;
	public boolean isAlive = true;
	public long startTime;
	public Rectangle collisionBox;

	public GameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y=y;
		this.width=width;
		this.height=height;
		collisionBox = new Rectangle(x, y, width, height);
		startTime = System.currentTimeMillis();
	}

	public void update() {
		collisionBox.setBounds(x, y, width, height);
	}

	public void draw(Graphics g) {
		
	}
	
	public long getAge() {
		return System.currentTimeMillis() - startTime;
	}
}
