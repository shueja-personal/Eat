package control;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.Random;

import javax.naming.ldap.ManageReferralControl;

import entities.Base;
import entities.Cannon;
import entities.Food;
import entities.Player;

public class ObjectManager {
	Player p1;
	Player p2;
	Base b1;
	Base b2;
	ArrayList<Food> FoodList;
	ArrayList<Cannon> CannonList = new ArrayList<Cannon>();
	ArrayList<Player> PlayerList = new ArrayList<Player>();
	ArrayList<Base> BaseList = new ArrayList<Base>();
	Random random =  new Random();
	int foodDiameter=20;
	int foodToSpew= 0;
	public boolean gameOver;
	Player spewingPlayer;
	
	public ObjectManager(Player p1, Player p2, Cannon cannonTL, Cannon cannonTR, Cannon cannonBL, Cannon cannonBR, Base p1Base, Base p2Base) {
		this.p1 = p1;
		this.p2 = p2;
		b1 = p1Base;
		b2 = p2Base;
		FoodList = new ArrayList<Food>();
		CannonList.add(cannonTL);
		CannonList.add(cannonTR);
		CannonList.add(cannonBL);
		CannonList.add(cannonBR);
		PlayerList.add(p1);
		PlayerList.add(p2);
		BaseList.add(p1Base);
		BaseList.add(p2Base);
	}
	
	public void update() {
		p1.update();
		p2.update();
		b1.update();
		b2.update();
		for (Player player : PlayerList) {
			player.update();
			for (Food food : FoodList ) {
				food.update();
				if (food.collisionBox.intersects(player.collisionBox) && food.getAge() > 1000) {
					food.isAlive=false;
					player.heldFood++;
					//System.out.println(player + " has " + player.heldFood + " food ");
				}
				
			}
			for( Player hitPlayer : PlayerList) {
				if(player.collisionBox.intersects(hitPlayer.collisionBox)) {
					if(player.heldFood > hitPlayer.heldFood) { // player has more loses food.
						spewingPlayer = player;
						foodToSpew = player.heldFood-hitPlayer.heldFood; //Average of scores minus lesser score = 
						player.heldFood -= foodToSpew;
					}
				}
				else {
					spewingPlayer =null;
				}
			}

		}
		for(Base base : BaseList) {
				if (base.ownPlayer.collisionBox.intersects(base.collisionBox)) {
					if(base.ownPlayer.heldFood > 0) {
						base.ownPlayer.heldFood--;
						base.deliveredFood += 1;
					}
				}
				if (base.deliveredFood >= 1000) {
					gameOver=true;
				}
			}
		
		for (Cannon cannon: CannonList) {
			cannon.update();
			
		}
		if (spewingPlayer != null) {
			for (int i = 0; i < foodToSpew; i++) {
				addFood(spewingPlayer.x, spewingPlayer.y, (double) random.nextInt(360));
			}
			foodToSpew = 0;
			spewingPlayer = null;
		}
		purgeObjects();
	}
	
	public void draw(Graphics g) {
		p1.draw(g);
		p2.draw(g);
		b1.draw(g);
		b2.draw(g);
		for (Food food: FoodList) {
			food.draw(g);
		}
		for (Cannon cannon: CannonList) {
			cannon.draw(g);
		}
	}
	
	public void shoot(ObjectManager manager) {
		for (Cannon cannon: CannonList) {
			cannon.shoot(manager);
		}
	}
	public void addFood(int x, int y, double angle) {
		FoodList.add(new Food(x, y, foodDiameter, foodDiameter, angle));
	}
	
	public void purgeObjects() {
		
		FoodList.removeIf(f -> f.isAlive == false);

		
			
	}
	
}

