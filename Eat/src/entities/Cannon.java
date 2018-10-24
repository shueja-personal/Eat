package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Random;

import javax.naming.ldap.ManageReferralControl;

import org.omg.CORBA.IRObject;

import control.Eat;
import control.ObjectManager;



public class Cannon extends GameObject {
	public double rotation;
	public double CWbound;
	public double CCWbound;

	int rotateDirection = 1;
	//public boolean isCWdirection = true;
	public double degPerFrame=0.5;
	public AffineTransform rotate = new AffineTransform(); 
	public Cannon(int x, int y, int width, int height, double CW, double CCW) {
		super(x, y, width, height);
		CWbound=CW %360;
		CCWbound=CCW % 360;
		rotation = CWbound;
		//rotation = (CWbound+CCWbound)/2;
		// TODO Auto-generated constructor stub
	}
	public void update() {
		super.update();
		rotate();
		//System.out.println(rotation);
		rotation = rotation % 360;

	}
	public void draw(Graphics g) {
		
		Graphics2D graphics2d = (Graphics2D) g.create();
		graphics2d.setColor(Color.BLACK);
		//rotate.translate(/*(Eat.WIDTH-x)/2, (Eat.HEIGHT-y)/2*//*Eat.WIDTH/2,Eat.HEIGHT/2*/ /*50,50*/);
		rotate = new AffineTransform();
		rotate.concatenate(AffineTransform.getRotateInstance(Math.toRadians(rotation), x/*-width/2*/, y/*height/2*/ ));
		//rotate.rotate(Math.toRadians(degPerFrame));
		
		graphics2d.setTransform(rotate);
		rotate.setToIdentity();
		
		graphics2d.fillRect(x-width/2, y-height/2, width, height);
		
	}
	
	public void rotate () {
		 //increasing rotation moves clockwise
		if (rotation < CWbound) { // rotation decreasing and past clockwise bound.
			rotateDirection=1;
			//System.out.println("moving CCW");
			rotation += degPerFrame;
		}
		else if (rotation >CCWbound) {
			rotateDirection=-1;
			//System.out.println("moving CW");
			rotation -= degPerFrame;
		}
		else {
			rotation += degPerFrame*rotateDirection;
		}
    }
	
	public void shoot(ObjectManager manager) {
		manager.addFood(x, y, rotation);
	}
}